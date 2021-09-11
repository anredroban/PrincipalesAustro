package callcenter
import com.pw.security.*
import grails.converters.JSON
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import liquibase.util.file.FilenameUtils
import utilitarios.Util

import java.text.SimpleDateFormat

class GestionController {



	static beforeInterceptor = {
		String accion = actionUri;
		if(!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")){
			if(!session.user){
				redirect(uri: "/usuario/login");
				return false;
			}else{
				boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
				if(!tienePermiso){
					render "No tienes permiso para ingresar a este sitio-> "+accion;
				}
			}
		}
	}

	/**
	 * @author Giovanny Granda
	 * Muestra en pantalla los clientes asignados
	 * @return
	 */
	def index() {
		Usuario usuario = Usuario.findById(session.user.id);
		def plataforma = 'PURE CLOUD'
//		def clientesGestionar = Clientes.executeQuery("from Clientes c where subestadoGestion.rellamar = 'SI' and usuario = :usuario order by c.intentos", [usuario: usuario])

		def clients = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			notEqual('plataforma', plataforma)
			subestadoGestion {
				or{
					eq('type', Subestado.constraints.type.inList[0].toString())
					eq('type', Subestado.constraints.type.inList[1].toString())
				}
			}
			order("intentos")
		}
		def clientsNoManagement = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			isNull('subestadoGestion')
		}

		clients.each {client ->
			clientsNoManagement.add(client)
		}


		[clientesGestionar: clientsNoManagement]
	}

	/**
	 * @author Giovanny Granda
	 * Muestra la pantalla de gestion donde se hara rectificación de datos
	 * @param id
	 * @return
	 */
	def gestionCliente(long id){
		long idCliente = id
		Clientes cliente = Clientes.findById(idCliente)
        session.user
        String[] nombres = cliente.nombre.split(' ')
        String apellido1 = ""
        String apellido2 = ""
        String nombre1 = ""
        String nombre2 = ""
        for(int i = 0; i < nombres.size(); i++){
            if (nombres.size() == 4){
                apellido1 = nombres[0]
                apellido2 = nombres[1]
                nombre1 = nombres[2]
                nombre2 = nombres[3]
            }else{
                apellido1 = ""
                apellido2 = ""
                nombre1 = ""
                nombre2 = ""
            }
        }

		[cliente: cliente,usuario: session.user, apellido1: apellido1, apellido2: apellido2, nombre1: nombre1, nombre2: nombre2]
	}

	/**
	 * @author Giovanny Granda
	 * Guarda la gestion del call center
	 * @param id
	 * @return
	 */
	def guardarCliente(){

		Usuario usuario = Usuario.findById(session.user.id.toString().toLong())
		Date fechaActual = new Date()
		long idCliente = params.idCliente.toLong()
		long idEstadoGestion = params.status.toLong()
		long idSubestadoGestion = params.substatus.toLong()
		String estadoGestion = Estado.findById(idEstadoGestion).nombre
		Subestado objSubestadoGestion = Subestado.findById(idSubestadoGestion)
		int tiempoLlamada = 0
		if(objSubestadoGestion.rangoTiempo){
			String[] tiempos = objSubestadoGestion.rangoTiempo.split('-')
			int t0 = tiempos[0].toInteger()
			int t1 = tiempos[1].toInteger()
			tiempoLlamada = Math.floor(Math.random()*(t1-t0+1)+t0)
		}

		//Busco el cliente por su id
		Clientes cliente = Clientes.findById(idCliente)
		int intentos = cliente.intentos?: 0

		if(cliente.registroExitoso != "SI"){

		if(objSubestadoGestion.enableManagement){
			/*cliente.lugarEntrega = params.lugarEntrega
			cliente.horarioEntrega = params.horarioEntrega
			cliente.cedulaVerificada = params.cedulaVerificada
			cliente.nombreVerificado = params.nombreVerificado.toString().toUpperCase()
			if(params.provinciaDomicilio != ""){
				cliente.provinciaDomicilio = params.provinciaDomicilio.toString().split("-")[2]
			}
			if(params.ciudadDomicilio != ""){
				cliente.ciudadDomicilio = Ciudad.findById(params.ciudadDomicilio.toString().toLong()).nombre
			}
			cliente.sectorDomicilio = params.sectorDomicilio
			cliente.callePrincipalNumeracionDomicilio = removeSpecialCharacters(params.callePrincipalDomicilio.toString()+" "+params.numeracionDomicilio.toString())
			cliente.calleTransversalDomicilio = removeSpecialCharacters(params.calleTransversalDomicilio.toString())
			cliente.referenciaDomicilio = removeSpecialCharacters(params.referenciaDomicilio.toString())
			cliente.tipoPredioDomicilio = params.tipoPredioDomicilio

			if(params.provinciaTrabajo != ""){
				cliente.provinciaTrabajo = params.provinciaTrabajo.toString().split("-")[2]
			}
			if(params.ciudadTrabajo != ""){
				cliente.ciudadTrabajo = Ciudad.findById(params.ciudadTrabajo.toString().toLong()).nombre
			}
			cliente.sectorTrabajo = params.sectorTrabajo
			cliente.callePrincipalNumeracionTrabajo = removeSpecialCharacters(params.callePrincipalTrabajo.toString()+" "+params.numeracionTrabajo.toString())
			cliente.calleTransversalTrabajo = removeSpecialCharacters(params.calleTransversalTrabajo.toString())
			cliente.referenciaTrabajo = removeSpecialCharacters(params.referenciaTrabajo.toString())
			cliente.tipoPredioTrabajo = params.tipoPredioTrabajo

			cliente.telefonoCasa = params.telefonoCasa
			cliente.telefonoTrabajo = params.telefonoTrabajo
			cliente.celular = params.celular

			cliente.sueldoIngresos = params.sueldoIngresos
			cliente.gastosBasicos = params.gastosBasicos*/
			cliente.lugarEntrega = params.lugarEntrega
			cliente.lugarEntregaECuenta = params.lugarEntregaECuenta
			cliente.tipoIdentificacion = 'CED'
			cliente.cedulaVerificada = cliente.identificacion
			cliente.apellido1 =  removeSpecialCharacters(params.apellido1.toString()).toUpperCase().replace("  "," ")
			cliente.apellido2 = removeSpecialCharacters(params.apellido2.toString()).toUpperCase().replace("  "," ")
			cliente.nombreVerificado = removeSpecialCharacters(params.nombreVerificado.toString()).toUpperCase().replace("  "," ")
			cliente.nombre2Verificado = removeSpecialCharacters(params.nombre2Verificado.toString()).toUpperCase().replace("  "," ")
			cliente.nacionalidad = params.nacionalidad
			cliente.genero = params.sexo
			cliente.emailPersonal = params.emailPersonal.toString().toLowerCase()
			cliente.provinciaDomicilio = Provincia.findById(params.provinciaDomicilio.toString().toLong()).nombre
			cliente.ciudadDomicilio = Ciudad.findById(params.ciudadDomicilio.toString().toLong()).nombre
			cliente.sectorDomicilio = params.sectorDomicilio
			cliente.barrioDomicilio = params.barrioDomicilio
			cliente.sucursal = Sucursal.findById(params.sucursal.toString().toLong()).codigo
			cliente.oficina = Oficina.findById(params.oficina.toString().toLong()).codigo
			cliente.codigoCiudad = Ciudad.findById(params.ciudadDomicilio.toString().toLong()).codigo
			cliente.codigoParroquia = Parroquia.findById(params.sectorDomicilio.toString().toLong()).codigo
			//cliente.sectorDomicilio = params.sectorDomicilio
			cliente.callePrincipalNumeracionDomicilio = removeSpecialCharacters(params.callePrincipalDomicilio.toString()+" "+params.numeracionDomicilio.toString())
			cliente.calleTransversalDomicilio = removeSpecialCharacters(params.calleTransversalDomicilio.toString())
			cliente.referenciaDomicilio = removeSpecialCharacters(params.referenciaDomicilio.toString())
			cliente.provinciaTrabajo = Provincia.findById(params.provinciaTrabajo.toString().toLong()).nombre
			cliente.ciudadTrabajo = Ciudad.findById(params.ciudadTrabajo.toString().toLong()).nombre
			cliente.codigoCiudadTrabajo = Ciudad.findById(params.ciudadTrabajo.toString().toLong()).codigo
			cliente.codigoParroquiaTrabajo = Parroquia.findById(params.sectorTrabajo.toString().toLong()).codigo
			cliente.sectorTrabajo = params.sectorTrabajo
			cliente.barrioTrabajo = params.barrioTrabajo

			cliente.nombreEmpresa = formatearTexto(params.nombreEmpresa.toString()).toUpperCase()
			cliente.callePrincipalNumeracionTrabajo = removeSpecialCharacters(params.callePrincipalTrabajo.toString()+" "+params.numeracionTrabajo.toString()).toUpperCase()
			cliente.calleTransversalTrabajo = removeSpecialCharacters(params.calleTransversalTrabajo.toString()).toUpperCase()
			cliente.referenciaTrabajo = removeSpecialCharacters(params.referenciaTrabajo.toString()).toUpperCase()
			cliente.telefonoCasa = params.telefonoCasa
			cliente.telefonoTrabajo = params.telefonoTrabajo
			cliente.celular = params.celular
			cliente.sueldoIngresos = params.sueldoIngresos
			cliente.gastosBasicos = params.gastosBasicos
			cliente.comisiones = params.comisiones
			cliente.otrosIngresos = params.otrosIngresos
			cliente.ingresosConyugue = params.ingresosConyugue
			cliente.alimentacionGestion = params.alimentacion
			cliente.arriendo = params.arriendo
			cliente.cuotaArriendo = params.cuotaArriendo
			cliente.vestimenta = params.vestimenta
			cliente.educacion = params.educacion
			cliente.salud = params.salud
			cliente.cuotaVehiculo = params.cuotaVehiculo


			cliente.fechaNacimiento = params.fechaNacimientoGestion.toString().replace("/","-")
			cliente.estadoCivil = params.estadoCivil
			cliente.nivelEstudios = params.nivelEstudios
			cliente.profesion = params.profesion
			cliente.cargo = params.cargo
			cliente.ocupacion = params.ocupacion
			cliente.origenIngresos = params.origenIngresos
			cliente.rangoIngresos = params.rangoIngresos
			cliente.patrimonio = params.patrimonio
			cliente.tipoVivienda = params.tipoVivienda
			cliente.valorVivienda = params.valorVivienda
			cliente.fechaInicioResidencia = params.fechaInicioResidencia.toString().replace("/","-")
			cliente.relacionLaboral = params.relacionLaboral
			cliente.fechaInicioTrabajoActual = params.fechaInicioTrabajoActual.toString().replace("/","-")
			cliente.fechaInicioTrabajoAnterior = params.fechaInicioTrabajoAnterior.toString().replace("/","-")
			cliente.fechaFinTrabajoAnterior = params.fechaFinTrabajoAnterior.toString().replace("/","-")
			cliente.cargasFamiliares = params.cargasFamiliares
			cliente.nombreImpreso = removeSpecialCharacters(params.nombreVerificado.toString().replace("  "," ") + " " + params.apellido1.toString().replace("  "," ")).toUpperCase()
			cliente.valorActivo = params.valorActivo
			cliente.valorPasivo = params.valorPasivo
			cliente.tipoIdentificacionRefPersonal =  'CED'
			cliente.identificacionRefPersonal = removeSpecialCharacters(params.identificacionRefPersonal.toString()).replace(" ", "")
			cliente.apellido1RefPersonal = removeSpecialCharacters(params.apellido1RefPersonal.toString()).toUpperCase().replace("  "," ")
			cliente.apellido2RefPersonal = removeSpecialCharacters(params.apellido2RefPersonal.toString()).toUpperCase().replace("  "," ")
			cliente.nombre1RefPersonal = removeSpecialCharacters(params.nombre1RefPersonal.toString()).toUpperCase().replace("  "," ")
			cliente.nombre2RefPersonal = removeSpecialCharacters(params.nombre2RefPersonal.toString()).toUpperCase().replace("  "," ")
			cliente.codigoCiudadReferencia = Ciudad.findById(params.ciudadReferencia.toString().toLong()).codigo
			cliente.callePrincipalRefPersonal = removeSpecialCharacters(params.callePrincipalRefPersonal.toString()+" "+params.numeracionDomicilioRef.toString()).toUpperCase()
			cliente.calleSecundariaRefPersonal = removeSpecialCharacters(params.calleSecundariaRefPersonal.toString()).toUpperCase()
			cliente.telefonoRefPersonal = params.telefonoRefPersonal
			cliente.referenciaRefPersonal = removeSpecialCharacters(params.referenciaRefPersonal.toString()).toUpperCase()
			cliente.parentescoRefPersonal = params.parentescoRefPersonal

			cliente.tipoIdentificacionConyuge = 'CED'
			cliente.identificacionConyuge = params.identificacionConyuge.toString().replace(" ", "").replace("  ", "")
			cliente.apellido1Conyuge = removeSpecialCharacters(params.apellido1Conyuge.toString()).toUpperCase().replace("  "," ")
			cliente.apellido2Conyuge = removeSpecialCharacters(params.apellido2Conyuge.toString()).toUpperCase().replace("  "," ")
			cliente.nombre1Conyuge = removeSpecialCharacters(params.nombre1Conyuge.toString()).toUpperCase().replace("  "," ")
			cliente.nombre2Conyuge = removeSpecialCharacters(params.nombre2Conyuge.toString()).toUpperCase().replace("  "," ")
			cliente.paisConyuge = params.paisConyuge
			cliente.generoConyuge = params.generoConyuge
			cliente.fechaNacimientoConyuge = params.fechaNacimientoConyuge.toString().replace("/","-")
			cliente.estadoCivilConyuge = params.estadoCivilConyuge
			cliente.paisNacimientoConyuge = params.paisNacimientoConyuge

			cliente.productoAceptado = params.productoAceptado
			if(params.aceptaCambioProducto == "SI"){
				cliente.aceptaCambioProducto = params.aceptaCambioProducto
				cliente.productoAceptado = params.tipoTarjeta
			}


			cliente.actividadEconomica = ActividadEconomica.findById(params.actividadEconomica.toString().toLong()).codigo
			//cliente.prueba = ActividadEconomica.findByDescripcion(params.tags.toString()).codigo

			cliente.registroExitoso = 'SI'

		}
		if(estadoGestion == "CONTACTADO"){
			cliente.telefonoContactado = params.telefonoContactado
			cliente.estadoTelefonoContactado = params.estadoTelefonoContactado
		}
		if(objSubestadoGestion.type.toString().equalsIgnoreCase("Rellamada")){
			cliente.fechaRellamada = new Date().parse('yyyy-MM-dd HH:mm:ss', params.recall.toString().replace('/','-') + ':00')
			//cliente.fechaRellamada =  new Date().parse('yyyy-MM-dd HH:mm:ss', formatearRellamada(params.recall.toString()))
			cliente.agendamientoAsesor = params.agendamiento
			cliente.codigoAsignacion = '100'
		}else{
			cliente.fechaRellamada = null
		}
		cliente.estadoGestion = estadoGestion
		cliente.subestadoGestion = objSubestadoGestion
		if (params.subSubStatus != ""){
			String nombreSubSubEstado = SubSubestado.findById(params.subSubStatus.toString().toLong()).name
			cliente.subSubEstado = nombreSubSubEstado
		}
		else
			cliente.subSubEstado = ""


			if (params.motivosSubSubEstados != ""){
				String nombreMotivoSubSubEstado = MotivoSubEstado.findById(params.motivosSubSubEstados.toString().toLong()).nombre
				cliente.motivoSubSubEstado = nombreMotivoSubSubEstado
			}
			else
				cliente.motivoSubSubEstado = ""


		cliente.intentos = intentos+1
		cliente.fechaGestion = fechaActual
		cliente.usuario = usuario
		cliente.nombreVendedor = usuario.nombre.toUpperCase()
		cliente.observaciones = formatearTexto(params.observaciones.toString())

		cliente.save(flush: true)

		//Se guarda informacion en el historial
		Historial historial = new Historial()
		historial.cliente = Clientes.findById(cliente.id.toLong())
		historial.estadoGestion = cliente.estadoGestion
		historial.subestadoGestion = cliente.subestadoGestion
		historial.subSubEstado = cliente.subSubEstado
		historial.fechaGestion = fechaActual
		historial.intentos = cliente.intentos
		historial.nombreVendedor = cliente.nombreVendedor
		historial.observacionesGestion = cliente.observaciones.toString().toUpperCase()
		historial.usuario = cliente.usuario
		historial.duracionLlamada = tiempoLlamada
		historial.plataforma = cliente.plataforma
		historial.detalleTelefono1 = params.estadoTel1
		historial.detalleTelefono2 = params.estadoTel2
		historial.detalleTelefono3 = params.estadoTel3
		historial.detalleTelefono4 = params.estadoTel4
		historial.detalleTelefono5 = params.estadoTel5
		historial.detalleTelefono6 = params.estadoTel6
		historial.detalleTelefono7 = params.estadoTel7
		historial.detalleTelefono8 = params.estadoTel8
		historial.detalleTelefono9 = params.estadoTel9
		historial.detalleTelefono10 = params.estadoTel10
		historial.estadoTelefonoContactado = cliente.estadoTelefonoContactado
		historial.save(flush: true)
		redirect(uri: "/gestion/index")
		}
		else{
			render(view: "modelValidacion",  model: [estado:cliente.estadoGestion, subestado: cliente.subestadoGestion.nombre, idCliente: cliente.id])
		}
	}

	private String formatearTexto(String entrada){
		return entrada.toUpperCase().replace('Ñ', 'N').replace('-', ' ').replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U')
	}

	/**
	 * @author Andres Redroban
	 * Convierte el formato de campo Fecha Rellamada
	 * @return resultado
	 */
	private String formatearRellamada(String variable){
		String[] arrayFechas = variable.trim().split(' ')
		String fecha = arrayFechas[0].replace('/', '-')
		String hora =  arrayFechas[1] + ' ' + arrayFechas[2]
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		Date date = parseFormat.parse(hora);
		String  horaFinal = displayFormat.format(date)+':00'
		String resultado = fecha + ' ' + horaFinal
		return resultado
	}

	def retirarBase(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null){

			String desde = params.desde
			String hasta = params.hasta

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)

			def subestados
			if(params.tipo != "RELLAMADAS"){
				subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			}else {
				subestados = Subestado.executeQuery("from Subestado where type = 'Rellamada'")
			}

			String sql = "update Clientes set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario and isActive = true and plataforma != 'PURE CLOUD'"


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""
			String condicionDesde = ""
			String condicionHasta = ""

			if(params.desde != ""){
				condicionDesde = " and cast(codigoAsignacion as integer) >= :desde"
				condiciones.put("desde", desde.toString().toInteger())
			}

			if(params.hasta != ""){
				condicionHasta = " and cast(codigoAsignacion as integer) <= :hasta"
				condiciones.put("hasta", hasta.toString().toInteger())
			}

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "RELLAMADAS"){
					condicionTipo = " and intentos > 0 and agendamientoAsesor = 'AGENDAR PARA CUALQUIERA'"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}

			Util.saveLog(session.user.id, "Se ha retirado la base ${params.nombrebase}")

			resultado = Clientes.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase+condicionDesde+condicionHasta, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def cargarBase(){

	}

	def saveFile(){
		String[] formFields = Clientes.getFields()
		def file = request.getFile('file')
		Cell[] cells
		String[] headers
		if(file.empty){
			flash.message = "Por favor selecciona un archivo"
		}else{
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder) //app directory
			File fileDest = new File(webrootDir,file.getOriginalFilename())
			if(fileDest.mkdirs()){
				println "directory created"
			}else{
				println "directory not created or already exists"
			}
			file.transferTo(fileDest)

			//Reading Excel
			String ext = FilenameUtils.getExtension(fileDest.path)
			if(ext.equalsIgnoreCase("xls")){
				try {
					WorkbookSettings ws = new WorkbookSettings()
					ws.setEncoding("Cp1252")
					Workbook workbook = Workbook.getWorkbook(fileDest, ws)
						Sheet sheet = workbook.getSheet(0)
						cells = sheet.getRow(0)
						workbook.close()
				}catch (IOException ex){
					flash.error = "Problemas al cargar el archivo"
					render(view: "cargarBase")
				}
				headers = new String[cells.length]
				for(int i = 0; i < cells.length; i++){
					headers[i] = cells[i].getContents()
				}
				render(view: "sortExcel", model: [headers: headers, formFields:formFields, filePath:fileDest.path])
			}else{
				flash.error = "El archivo debe ser una hoja de cálculo de Excel 97-2003"
				render(view: "cargarBase")
			}
		}
	}

	/**
	 * Status
	 * @return
	 */
	def getSubStatusByStatus(){
		if(params.id) {
			def status = Estado.findById(params.id)
			def subStatus = Subestado.findAllByEstado(status)
			def array = [subStatus.id, subStatus.nombre, subStatus.type, subStatus.enableManagement]
			render array as JSON
		}
	}

	/**
	 *
	 */


	/**
	 * make by someone
	 * @param value
	 * @return
	 */
	private removeSpecialCharacters(String value){
		if(value != null){
			while(true){
				if(value.indexOf("  ") >= 0){
					value = value.replace("  ", " ")
				}else{
					break
				}
			}

			def newValue = value.toUpperCase().replace("!","").replace("@","").replace("\$","")
					.replace("&","").replace("(","").replace(")","").replace("=","")
					.replace("?","").replace("¿","").replace("ç","").replace("{","").replace("}","")
					.replace("\\","").replace("\"","").replace("Á","A").replace("É","E").replace("Í","I")
					.replace("Ó","O").replace("Ú","U").replace("\'","").replace("  "," ").replace("  "," ")
					.replace("  "," ").replace("%","").replace(",","").replace("º","")
					.replace("ª","").replace("|","").replace("\$","").replace("¬","").replace("%","")
					.replace("*","").replace("+","").replace("_","").replace("-", " ").replace("#", "").replace(".", "")
					.replace(",", "")

			return newValue
		}
	}

}
