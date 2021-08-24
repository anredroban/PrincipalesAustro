package com.pw.security



import static org.springframework.http.HttpStatus.*

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

import com.mysql.jdbc.Util
import com.pw.security.Permiso
import com.pw.security.Rol
import com.pw.security.Sesion
import com.pw.security.Usuario
import utilitarios.Util
import callcenter.*

import grails.transaction.Transactional

@Transactional(readOnly = false)
class UsuarioController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


	static beforeInterceptor = {
		String accion = actionUri
		if (!accion.equals("/usuario/login") && !accion.equals("/usuario/logout") && !accion.equals('/usuario/cargaConfiguracion')) {
			if (!session.user) {
				redirect(uri: "/usuario/login")
				return false
			} else {
				boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
				if (!tienePermiso) {
					render "No tienes permiso para ingresar a este sitio-> " + accion
				}
			}
		}
	}
	
	
	def login(){
		
		if(session.user){
			redirect(action:'dashboard')
		}
		
		if(params.usuario && params.password){
			def usuario = Usuario.findByUsuarioAndPasswordAndEstado(params.usuario.toString(), params.password.toString(), "ACTIVO");
			if(usuario){
				session.user = usuario
				def sesion = new Sesion()
				sesion.usuario = usuario
				sesion.idSesion = session.id
				sesion.fechaInicio = new Date()
				sesion.direccionIp = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
						.getRequest().getRemoteHost()

				if(!sesion.save(flush: true))
					println sesion.errors
				redirect(action:'dashboard')
			}else{
			flash.errorMessage = "Error al iniciar sesion"
				Util.saveLog(1, "Intento fallido de ingreso con usuario ${params.usuario} y password ${params.password}")
				usuario = new Usuario(params)
				[usuario: usuario]
			}
			
		}
	}
	
	def logout(){
		Sesion sesion = Sesion.findByIdSesion(session.id)
		sesion?.fechaFin = new Date()
		sesion?.save(flush: true)
		session.invalidate()
		redirect(uri: "/usuario/login");
	}
	def dashboard(){

			Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
			Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
			def subestados = Subestado.findAllByType("Exitoso")
			Rol rol = Rol.findByNombre("OPERADOR")
			def ventasPorUsuario = Historial.executeQuery("select nombreVendedor, count(*) from Historial where subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
			def inicioSesion = Sesion.executeQuery("select usuario.nombre, time(fechaInicio), time(fechaFin) from Sesion where fechaInicio between :fechaInicio and :fechaFin and usuario.rol = :rol order by fechaInicio desc", [fechaInicio: fechaInicio, fechaFin: fechaFin, rol: rol])
			
			//Para la tabla de gestionados contactados y no contactados
			int totalGestionados = 0
			int totalContactados = 0
			int totalNoContactados = 0
			def gestionadosAgente = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def contactadosAgente = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def noContactadosAgente = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and estadoGestion = 'NO CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			String[][] tablaResult = new String[gestionadosAgente.size()][4]
			//Lleno la matriz de resultados con los resultados de las onsultas anteriores
			for(int i = 0; i < tablaResult.size(); i++){
				tablaResult[i][0] = gestionadosAgente[i][0];
				tablaResult[i][1] = gestionadosAgente[i][1];
				for(int j = 0; j < contactadosAgente.size(); j++){
					if(contactadosAgente[j][0] == gestionadosAgente[i][0]){
						tablaResult[i][2] = contactadosAgente[j][1];
						break;						
					}					
				}
				for(int k = 0; k < noContactadosAgente.size(); k++){
					if(noContactadosAgente[k][0] == gestionadosAgente[i][0]){
						tablaResult[i][3] = noContactadosAgente[k][1];
						break;
					}
				}
			}
			//Recorro la matriz de resultados para obtener los totales
			for(int i = 0; i < tablaResult.size(); i++){
				totalGestionados = totalGestionados + tablaResult[i][1].toInteger();
				if(tablaResult[i][2] != null)
					totalContactados = totalContactados + tablaResult[i][2].toInteger();
				if(tablaResult[i][3] != null)
				totalNoContactados = totalNoContactados + tablaResult[i][3].toInteger();
			}


		//Para la tabla de gestionados, exitosos y contactados por base
		def contactadosPorBase = Clientes.executeQuery("select nombreBase, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreBase order by nombreBase", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def exitososPorBase = Clientes.executeQuery("select nombreBase, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin group by nombreBase order by nombreBase", [fechaInicio: fechaInicio, fechaFin: fechaFin, subestados: subestados])
		String[][] tablaResult1 = new String[contactadosPorBase.size()][4]
		//Lleno la matriz de resultados con los resultados de las consultas anteriores
		for(int i = 0; i < tablaResult1.size(); i++){
			tablaResult1[i][0] = contactadosPorBase[i][0]
			tablaResult1[i][1] = contactadosPorBase[i][1]
			for(int j = 0; j < exitososPorBase.size(); j++){
				if(exitososPorBase[j][0] == contactadosPorBase[i][0]){
					tablaResult1[i][2] = exitososPorBase[j][1]
					break
				}
			}
		}


		def queryTiemposAgentes = TiemposAgentes.executeQuery("select nombreAgente, efectivos, meta, faltantes, porcentajeMeta, promedioHora, inicioConexion, finConexion, tiempoConexion, tiempoBreak, tiempoMuerto, tiempoEfectivo, observacion, totalGrupoEfectivo, totalGrupoMeta, totalGrupoFaltantes, totalGrupoPorcentajesMeta, totalGrupoPorcentajesPromedioHora, fecha from TiemposAgentes")
		String[][] tableTiemposAgentes = new String[queryTiemposAgentes.size()][20]
		String totalEfectivosGrupo = "";
		String totalMetaGrupo = ""
		String totalFaltantesGrupo = ""
		String totalPorcentajeMetaGrupo = ""
		String totalPromedioHoraGrupo = ""
		String fechaHoraActualizacion = ""

		for(int o = 0; o < tableTiemposAgentes.size(); o++){
			tableTiemposAgentes[o][0] = queryTiemposAgentes[o][0];
			tableTiemposAgentes[o][1] = queryTiemposAgentes[o][1];
			tableTiemposAgentes[o][2] = queryTiemposAgentes[o][2];
			tableTiemposAgentes[o][3] = queryTiemposAgentes[o][3];
			tableTiemposAgentes[o][4] = queryTiemposAgentes[o][4];
			tableTiemposAgentes[o][5] = queryTiemposAgentes[o][5];
			tableTiemposAgentes[o][6] = queryTiemposAgentes[o][6];
			tableTiemposAgentes[o][7] = queryTiemposAgentes[o][7];
			tableTiemposAgentes[o][8] = queryTiemposAgentes[o][8];
			tableTiemposAgentes[o][9] = queryTiemposAgentes[o][9];
			tableTiemposAgentes[o][10] = queryTiemposAgentes[o][10];
			tableTiemposAgentes[o][11] = queryTiemposAgentes[o][11];
			tableTiemposAgentes[o][12] = queryTiemposAgentes[o][12];
			totalEfectivosGrupo = queryTiemposAgentes[o][13];
			totalMetaGrupo = queryTiemposAgentes[o][14];
			totalFaltantesGrupo =  queryTiemposAgentes[o][15];
			totalPorcentajeMetaGrupo =  queryTiemposAgentes[o][16];
			totalPromedioHoraGrupo =  queryTiemposAgentes[o][17];
			fechaHoraActualizacion =  queryTiemposAgentes[o][18];
		}

						
			[ventasPorUsuario: ventasPorUsuario, inicioSesion: inicioSesion, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados, totalNoContactados: totalNoContactados, tablaResult1: tablaResult1
			 ,tableTiemposAgentes: tableTiemposAgentes, totalEfectivosGrupo: totalEfectivosGrupo, totalMetaGrupo: totalMetaGrupo, totalFaltantesGrupo: totalFaltantesGrupo,
			  totalPorcentajeMetaGrupo: totalPorcentajeMetaGrupo, totalPromedioHoraGrupo: totalPromedioHoraGrupo, fechaHoraActualizacion: fechaHoraActualizacion];

	}

	private String[][] consolidarExitAdic(ArrayList exitosos, ArrayList adicionales){
		String[][] consolidado = new String[exitosos.size()][3]
		for(int i = 0; i < exitosos.size(); i++){
			boolean encontrado = false
			for(int j = 0; j < adicionales.size(); j++){
				if(exitosos[i][0] == adicionales[j][0]){
					consolidado[i][0] = exitosos[i][0]
					consolidado[i][1] = exitosos[i][1]
					consolidado[i][2] = adicionales[j][1]
					encontrado = true
					break
				}
			}
			if(!encontrado){
				consolidado[i][0] = exitosos[i][0]
				consolidado[i][1] = exitosos[i][1]
				consolidado[i][2] = "0"
			}
		}
		return consolidado
	}
	
	def dashboardAgente(){
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"));
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"));
		Subestado subestado = Subestado.findByNombre("ACEPTA");
		Rol rol = Rol.findByNombre("OPERADOR");
		def ventasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where subestadoGestion = :subestado and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [subestado: subestado, fechaInicio: fechaInicio, fechaFin: fechaFin]);
		def inicioSesion = Sesion.executeQuery("select usuario.nombre, time(min(dateCreated)) from Sesion where dateCreated between :fechaInicio and :fechaFin and usuario.rol = :rol group by usuario.nombre order by usuario.nombre", [fechaInicio: fechaInicio, fechaFin: fechaFin, rol: rol]);
		
		//Para la tabla de gestionados contactados y no contactados
		int totalGestionados = 0;
		int totalContactados = 0;
		int totalNoContactados = 0;
		def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin]);
		def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin]);
		def noContactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and estadoGestion = 'NO CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin]);
		String[][] tablaResult = new String[gestionadosAgente.size()][4];
		//Lleno la matriz de resultados con los resultados de las onsultas anteriores
		for(int i = 0; i < tablaResult.size(); i++){
			tablaResult[i][0] = gestionadosAgente[i][0];
			tablaResult[i][1] = gestionadosAgente[i][1];
			for(int j = 0; j < contactadosAgente.size(); j++){
				if(contactadosAgente[j][0] == gestionadosAgente[i][0]){
					tablaResult[i][2] = contactadosAgente[j][1];
					break;
				}
			}
			for(int k = 0; k < noContactadosAgente.size(); k++){
				if(noContactadosAgente[k][0] == gestionadosAgente[i][0]){
					tablaResult[i][3] = noContactadosAgente[k][1];
					break;
				}
			}
		}
		//Recorro la matriz de resultados para obtener los totales
		for(int i = 0; i < tablaResult.size(); i++){
			totalGestionados = totalGestionados + tablaResult[i][1].toInteger();
			if(tablaResult[i][2] != null)
				totalContactados = totalContactados + tablaResult[i][2].toInteger();
			if(tablaResult[i][3] != null)
			totalNoContactados = totalNoContactados + tablaResult[i][3].toInteger();
		}
					
		[ventasPorUsuario: ventasPorUsuario, inicioSesion: inicioSesion, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados, totalNoContactados: totalNoContactados];
	}

    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
		Usuario usuario = Usuario.findById(session.user.id);
		if(Util.isAdmin(usuario.usuario))
        	respond Usuario.list(), model:[usuarioInstanceCount: Usuario.count()]
		else		
			respond Usuario.executeQuery("from Usuario where rol.nombre != 'ADMINISTRADOR'"), model:[usuarioInstanceCount: Usuario.count()]
    }

    def show(Usuario usuarioInstance) {
        respond usuarioInstance
    }

    def create() {
        respond new Usuario(params)
    }

    @Transactional
    def save(Usuario usuarioInstance) {
        if (usuarioInstance == null) {
            notFound()
            return
        }

        if (usuarioInstance.hasErrors()) {
            respond usuarioInstance.errors, view:'create'
            return
        }

		if (usuarioInstance.save(flush: true)) {
			Util.saveLog(session.user.id, "Creado usuario ${usuarioInstance.nombre}")
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect usuarioInstance
            }
            '*' { respond usuarioInstance, [status: CREATED] }
        }
    }

    def edit(Usuario usuarioInstance) {
        respond usuarioInstance
    }

    @Transactional
    def update(Usuario usuarioInstance) {
        if (usuarioInstance == null) {
            notFound()
            return
        }

        if (usuarioInstance.hasErrors()) {
            respond usuarioInstance.errors, view:'edit'
            return
        }

		if (usuarioInstance.save(flush: true)) {
			Util.saveLog(session.user.id, "Actualizado usuario ${usuarioInstance.nombre}")
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect usuarioInstance
            }
            '*'{ respond usuarioInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Usuario usuarioInstance) {

        if (usuarioInstance == null) {
            notFound()
            return
        }

        usuarioInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
