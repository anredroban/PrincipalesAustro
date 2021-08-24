package callcenter

import com.pw.security.Rol
import com.pw.security.Usuario
import utilitarios.Util

class AsignarBaseNewController {

    //Evita que ingrese mediante url
    static beforeInterceptor = {
        String accion = actionUri;
        if (!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")) {
            if (!session.user) {
                redirect(uri: "/usuario/login");
                return false;
            } else {
                boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
                if (!tienePermiso) {
                    render "No tienes permiso para ingresar a este sitio-> " + accion;
                }
            }
        }
    }

    def index() {
        boolean baseAsignada = false
        if (params.consultants && params.dbName && params.tipoRegx && params.allocationNumber) {
            String nombreBase = params.dbName
            String tipoRegistros = params.tipoRegx
            String subestado = params.subestadosRegestionables
            String easyDesde = params.desde
            String easyHasta = params.hasta
            String intentosDesde = params.intentosDesde
            String intentosHasta = params.intentosHasta
            String sql = ""
            String condicionEasyDesde = ""
            String condicionEasyHasta = ""
            Rol rol = Rol.findByNombre("OPERADOR")
            def condiciones = [nombreBase: nombreBase, rol: rol, offset: 0, max: params.allocationNumber.toString().toInteger()]

            if(easyDesde != ""){
                condicionEasyDesde = "and cast(codigoAsignacion as integer) >= :easyDesde"
                condiciones.put("easyDesde", easyDesde.toInteger())
            }

            if(easyHasta != ""){
                condicionEasyHasta = "and cast(codigoAsignacion as integer) <= :easyHasta"
                condiciones.put("easyHasta", easyHasta.toInteger())
            }



            if(tipoRegistros.equalsIgnoreCase("SIN GESTION")){
                sql = "from Clientes where usuario.rol != :rol and intentos = 0 and nombreBase = :nombreBase and plataforma != 'PURE CLOUD' ${condicionEasyDesde} ${condicionEasyHasta}"
            }else{
                String condicionIntentosDesde = ""
                String condicionIntentosHasta = ""
                String condicionSubestado = ""
                if(intentosDesde != ""){
                    condicionIntentosDesde = "and intentos >= :intentosDesde"
                    condiciones.put("intentosDesde", intentosDesde.toInteger())
                }
                if(intentosHasta != ""){
                    condicionIntentosHasta = "and intentos <= :intentosHasta"
                    condiciones.put("intentosHasta", intentosHasta.toInteger())
                }
                if(tipoRegistros.equalsIgnoreCase("RELLAMADAS")){
                    sql = "from Clientes where subestadoGestion.type = 'Rellamada' and usuario.rol != :rol and nombreBase = :nombreBase and intentos != 0 and agendamientoAsesor = 'AGENDAR PARA CUALQUIERA' and plataforma != 'PURE CLOUD' ${condicionEasyDesde} ${condicionEasyHasta} ${condicionIntentosDesde} ${condicionIntentosHasta}"
                }else{
                    if(subestado != ""){
                        condicionSubestado = "and subSubEstado = :subestado"
                        condiciones.put("subestado", subestado)
                    }
                    sql = "from Clientes where subestadoGestion.type = 'Regestionable' and usuario.rol != :rol and nombreBase = :nombreBase and intentos != 0 and plataforma != 'PURE CLOUD' ${condicionEasyDesde} ${condicionEasyHasta} ${condicionIntentosDesde} ${condicionIntentosHasta}  ${condicionSubestado} order by intentos"
                }
            }

            def consultants = params.list('consultants')

            int registrosAsignados = 0

            for (int i = 0; i < consultants.size(); i++) {
                Usuario usuario = Usuario.findById(consultants.get(i).toString().toInteger())

                def clientes = Clientes.executeQuery(sql, condiciones)

                for (int j = 0; j < clientes.size(); j++) {
                    Clientes cliente = Clientes.findById(clientes.get(j).id)
                    cliente.usuario = usuario
                    cliente.nombreVendedor = usuario.nombre
                    cliente.save(flush: true)
                    registrosAsignados++
                }
            }
            Util.saveLog(session.user.id, "Base asignada: ${registrosAsignados} registros")
            baseAsignada = true
            [baseAsignada: baseAsignada, registrosAsignados: registrosAsignados]
        }
    }
}
