package callcenter

//import javafx.scene.control.Cell
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableCell
import jxl.write.WritableCellFormat
import jxl.write.WritableFont
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import utilitarios.ExcelUtils


import com.pw.security.*
import groovy.sql.Sql
import org.hibernate.criterion.CriteriaSpecification
import utilitarios.Util

import java.text.DecimalFormat
import java.text.SimpleDateFormat

//import pl.touk.excel.export.WebXlsxExporter

class ReportesController {

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

    def baseGestionada() {
        if(params.fechas){
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            //def nombresBase = params.list("nombreBase")
            def subestados = params.list("subestados")
            def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1]]
            String sql = "from Clientes where fechaGestion between :fechaInicio and :fechaFin "
            def base = Clientes.executeQuery(sql, condiciones)

            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("Cp1252")
            WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
            workbook.createSheet("baseGestionada", 0)
            WritableSheet sheet = workbook.getSheet(0)
            String[] headers = [
                    "CEDULA",
                    "NOMBRES",
                    "ESTADO",
                    "SUBESTADO",
                    "SUBSUBESTADO",
                    "FECHA GESTION",
                    "NOMBRE BASE",
                    "NOMBRE VENDEDOR",
                    "INTENTOS",
                    "OBSERVACIONES",
                    "TELEFONO CONTACTADO",

            ]
            ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)
            for(int i = 0; i < base.size(); i++){
                String[] campos = new String[headers.length]
                Clientes c = base.get(i)
                campos[0] = c.identificacion
                campos[1] = c.nombre
                campos[2] = c.estadoGestion
                campos[3] = c.subestadoGestion.nombre
                campos[4] = c.subSubEstado
                campos[5] = c.fechaGestion.toString()
                campos[6] = c.nombreBase
                campos[7] = c.nombreVendedor
                campos[8] = c.intentos
                campos[9] = c.observaciones
                campos[10] = c.telefonoContactado
                ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
            }
            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=baseGestionada.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return

        }
    }

    def bitacoraPrincipales() {
        if (params.fechas) {
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
            def nombresBase = params.list("nombreBase")
            def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
            String sqlPrincipales = "from Clientes where fechaGestion between :fechaInicio and :fechaFin and subestadoGestion in (:subestados)"
            def principalesList = Clientes.executeQuery(sqlPrincipales, condiciones)
            double sueldoIngresos = 0
            double comisiones = 0
            double otrosIngresos = 0
            double ingresosConyugue = 0
            double alimentacion = 0
            double arriendo = 0
            double cuotaArriendo = 0
            double gastosBasicos = 0
            double vestimenta = 0
            double educacion = 0
            double salud = 0
            double cuotaVehiculo = 0
            double totalIngresos = 0
            double totalEgresos = 0
            DecimalFormat df = new DecimalFormat("#.00")
            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("UTF-8")
            WritableWorkbook workbook = Workbook.createWorkbook(file)
            workbook.createSheet("Clientes Efectivos", 0)
            WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
            cellFont.setColour(Colour.WHITE);
            WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
            WritableSheet sheetPrincipales = workbook.getSheet(0)
            String[] headersPrincipales = ["CEDULA", "NOMBRES", "APELLIDOS", "NOMBRES Y APELLIDOS", "DIRECCION DOMICILIO", "DIRECCION OFICINA", "TELEFONO DOMICILIO", "TELEFONO OFICINA",
                                           "CELULAR", "PROVINCIA DOMICILIO", "CIUDAD DOMICILIO", "PROVINCIA OFICINA", "CIUDAD OFICINA", "EMPRESA DONDE TRABAJA", "LUGAR DE ENTREGA", "TIPO",
                                           "CEDULA PRINCIPAL", "NOMBRE PRINCIPAL",
                                           "DIRECCION DOMICILIO TITULAR", "DIRECCION OFICINA TITULAR", "TELEFONO DOMICILIO TITULAR", "TELEFONO OFICINA TITULAR", "CELULAR TITULAR", "PROVINCIA DOMICILIO TITULAR", "CIUDAD DOMICILIO TITULAR", "PROVINCIA OFICINA TITULAR", "CIUDAD OFICINA TITULAR", "EMPRESA DONDE TRABAJA TITULAR", "LUGAR DE ENTREGA TITULAR",
                                           "CUPO TC PRINCIPAL", "CUPO TC ADICIONAL", "CANAL", "MARCA", "SEXO", "FECHA", "SUELDO PROPIO", "COMISIONES / COMPENSACIONES", "OTROS INGRESOS", "INGRESOS CONYUGUE", "TOTAL INGRESOS",
                                           "ALIMENTACION", "ARRIENDO", "CUOTA ARRIENDO", "SERVICIOS BASICOS", "VESTIMENTA", "EDUCACION", "SALUD", "CUOTA / VEHICULO TRANSPORTE", "TOTAL EGRESOS", "NOMBRE VENDEDOR", "FECHA/HORA GESTION","", "ID CLIENTE", "REGIONAL", "AGENCIA",
                                           "CREADAS NO CREADAS", "IMPUTABLE", "DETALLE IMPUTABLE", "FECHA ENVIO CREACION",'NOMBRE DE LA BASE','CODIGO CAMPANIA','STATUS COURIER', 'CICLO COURIER','TELEFONO CONTACTADO','SECTOR DOMICILIO','SECTOR TRABAJO', 'GUIA COURIER']
            ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GREEN, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.THIN)
            for (int i = 0; i < principalesList.size(); i++) {
                String[] campos = new String[headersPrincipales.length]
                Clientes cli = principalesList.get(i)
                campos[0] = cli.cedulaVerificada
                campos[1] = cli.nombreVerificado + ' ' + cli.nombre2Verificado
                campos[2] = cli.apellido1 + ' ' + cli.apellido2
                campos[3] = cli.nombreVerificado + ' ' + cli.nombre2Verificado + ' ' + cli.apellido1 + ' ' + cli.apellido2
                campos[4] = cli.callePrincipalNumeracionDomicilio + ' ' + cli.calleTransversalDomicilio + ' ' + cli.referenciaDomicilio
                campos[5] = cli.callePrincipalNumeracionTrabajo + ' ' + cli.calleTransversalTrabajo + ' ' + cli.referenciaTrabajo
                campos[6] = cli.telefonoCasa
                campos[7] = cli.telefonoTrabajo
                campos[8] = cli.celular
                campos[9] = cli.provinciaDomicilio
                campos[10] = cli.ciudadDomicilio
                campos[11] = cli.provinciaTrabajo
                campos[12] = cli.ciudadTrabajo
                campos[13] = cli.nombreEmpresa
                campos[14] = cli.lugarEntrega
                campos[15] = "PRINCIPAL"
                campos[16] = cli.cedulaVerificada
                campos[17] = cli.nombreVerificado + ' ' + cli.nombre2Verificado + ' ' + cli.apellido1 + ' ' + cli.apellido2
                campos[18] = cli.callePrincipalNumeracionDomicilio + ' ' + cli.calleTransversalDomicilio + ' ' + cli.referenciaDomicilio
                campos[19] = cli.callePrincipalNumeracionTrabajo + ' ' + cli.calleTransversalTrabajo + ' ' + cli.referenciaTrabajo
                campos[20] = cli.telefonoCasa
                campos[21] = cli.telefonoTrabajo
                campos[22] = cli.celular
                campos[23] = cli.provinciaDomicilio
                campos[24] = cli.ciudadDomicilio
                campos[25] = cli.provinciaTrabajo
                campos[26] = cli.ciudadTrabajo
                campos[27] = cli.nombreEmpresa
                campos[28] = cli.lugarEntrega

                campos[29] = cli.cupo
                campos[30] = ""
                campos[31] = "PW"
                campos[32] = cli.productoAceptado
                campos[33] = cli.genero
                campos[34] = cli.fechaGestion.toString().substring(0, 10)
                campos[35] = cli.sueldoIngresos
                campos[36] = cli.comisiones
                campos[37] = cli.otrosIngresos
                campos[38] = cli.ingresosConyugue
                sueldoIngresos = Double.parseDouble(cli.sueldoIngresos)
                comisiones = Double.parseDouble(cli.comisiones)
                otrosIngresos = Double.parseDouble(cli.otrosIngresos)
                ingresosConyugue = Double.parseDouble(cli.ingresosConyugue)
                totalIngresos = sueldoIngresos + comisiones + otrosIngresos + ingresosConyugue
                campos[39] = df.format(totalIngresos)
                campos[40] = cli.alimentacionGestion
                campos[41] = cli.arriendo
                campos[42] = cli.cuotaArriendo
                campos[43] = cli.gastosBasicos
                campos[44] = cli.vestimenta
                campos[45] = cli.educacion
                campos[46] = cli.salud
                campos[47] = cli.cuotaVehiculo
                alimentacion = Double.parseDouble(cli.alimentacionGestion)
                arriendo = Double.parseDouble(cli.arriendo)
                cuotaArriendo = Double.parseDouble(cli.cuotaArriendo)
                gastosBasicos = Double.parseDouble(cli.gastosBasicos)
                vestimenta = Double.parseDouble(cli.vestimenta)
                educacion = Double.parseDouble(cli.educacion)
                salud = Double.parseDouble(cli.salud)
                cuotaVehiculo = Double.parseDouble(cli.cuotaVehiculo)
                totalEgresos = alimentacion + arriendo + cuotaArriendo + gastosBasicos + vestimenta + educacion + salud + cuotaVehiculo
                campos[48] = df.format(totalEgresos)
                campos[49] = cli.nombreVendedor
                campos[50] = formatearFecha(cli.fechaGestion)
                campos[51] = ""
                campos[52] = cli.id
                if(cli.lugarEntrega.equalsIgnoreCase("DIRECCION DE RESIDENCIA")){
                    campos[53] = Provincia.findByNombre(cli.provinciaDomicilio.toString()).regional
                    campos[54] = Provincia.findByNombre(cli.provinciaDomicilio.toString()).agencia
                }else{
                    campos[53] = Provincia.findByNombre(cli.provinciaTrabajo.toString()).regional
                    campos[54] = Provincia.findByNombre(cli.provinciaTrabajo.toString()).agencia
                }
                campos[55] = cli.creadas_nocreadas
                campos[56] = cli.imputable
                campos[57] = cli.detalle_imputable
                campos[58] = cli.fecha_envio_creacion
                campos[59] = cli.nombreBase
                campos[60] = cli.codigoCampania
                campos[61] = cli.statusCourier
                campos[62] = cli.cicloCourier
                campos[63] = cli.telefonoContactado
                //campos[64] = cli.sectorDomicilio
                campos[64] = Parroquia.findById(cli.sectorDomicilio.toLong()).nombre
                //campos[65] = cli.sectorTrabajo
                campos[65] = Parroquia.findById(cli.sectorTrabajo.toLong()).nombre
                campos[66] = cli.guia_courier

                ExcelUtils.addCells(campos, sheetPrincipales, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
            }
            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=ventasTarjetasAustro.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return
        }
    }


    def bitacoraPlanificacion(){
        if(params.fechas){

            //Obtenemos los datos
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            ArrayList<SubSubestado> subestados = Subestado.findAllByEnableManagement(true)
            def nombresBase = params.list("nombreBase")
            def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
            String sql = "from Clientes a where fechaGestion between :fechaInicio and :fechaFin and subestadoGestion in (:subestados)"
            def clientesList = Clientes.executeQuery(sql, condiciones)
            int secuencial = 1
            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("Cp1252")
            WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
            workbook.createSheet("BitacoraAdicionales", 0)
            WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
            WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
            WritableSheet sheet = workbook.getSheet(0)
            String[] headers = ['CEDULA',	'NOMBRES',	'APELLIDOS',	'NOMBRES Y APELLIDOS',	'PROVINCIA DOMICILIO',
                                'CIUDAD DOMICILIO',	'PARROQUIA DOMICILIO',	'CLL P DOMICILIO',	'NEMERACION',
                                'CALL SEUNDARIA',	'REEERENCIAS',	'DIRECCION COMPLETA DOMICILIO',
                                'PROVINCIA TRABAJO',	'CIUDAD TRABAJO',	'PARROQUIA TRABAJO',
                                'CLL P TRABAJO',	'NEMERACION',	'CALL SEUNDARIA',	'REEERENCIAS',
                                'DIRECCION COMPLETA TRABAJO',	'TELEFONO CONTACTADO',	'TELEFONO REFERENCIA',
                                'TELEFONO 1',	'TELEFONO 2',	'TELEFONO 3',	'TELEFONO 4',	'TELEFONO 5',
                                'TELEFONO 6',	'TELEFONO 7',	'TELEFONO 8',	'TELEFONO 9',	'TELEFONO 10',
                                'ID CLIENTE',	'FECHA GESTION',	'CREADAS NO CREADAS',	'IMPUTABLE',
                                'DETALLE IMPUTABLE',	'FECHA ENVIO CREACION',	'NOMBRE DE LA BASE',	'LOTE',
                                'CODIGO CAMPANIA',	'STATUS COURIER',	'CICLO COURIER',	'CIERRE DE CICLO',
                                'GUIA COURIER',	' CEDULA TITULAR']
            ExcelUtils.addCells(headers, sheet, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
            for(int i = 0; i < clientesList.size(); i++){
                String[] campos = new String[headers.length]
                Clientes cli = clientesList.get(i)
                campos[0] = cli.identificacion
                campos[1] = cli.nombreVerificado + ' ' + cli.nombre2Verificado
                campos[2] = cli.apellido1 + ' ' + cli.apellido2
                campos[3] = cli.nombre
                campos[4] = cli.provinciaDomicilio
                campos[5] = cli.ciudadDomicilio
                campos[6] = Parroquia.findById(cli.sectorDomicilio.toLong()).nombre
                campos[7] = cli.callePrincipalNumeracionDomicilio
                campos[8] = cli.callePrincipalNumeracionDomicilio
                campos[9] = cli.calleTransversalDomicilio
                campos[10] = cli.referenciaDomicilio
                campos[11] = cli.ciudadDomicilio + ' ' + cli.sectorDomicilio + ' ' + cli.callePrincipalNumeracionDomicilio + ' ' + cli.calleTransversalDomicilio + ' ' + cli.referenciaDomicilio
                campos[12] = cli.provinciaTrabajo
                campos[13] = cli.ciudadTrabajo
                campos[14] = Parroquia.findById(cli.sectorTrabajo.toLong()).nombre
                campos[15] = cli.callePrincipalNumeracionTrabajo
                campos[16] = cli.callePrincipalNumeracionTrabajo
                campos[17] = cli.calleTransversalTrabajo
                campos[18] = cli.referenciaTrabajo
                campos[19] = cli.ciudadTrabajo + ' ' + cli.sectorTrabajo + ' ' + cli.callePrincipalNumeracionTrabajo + ' ' + cli.calleTransversalTrabajo + ' ' + cli.referenciaTrabajo
                campos[20] = cli.telefonoContactado
                campos[21] = cli.telefonoRefPersonal
                campos[22] = cli.telefono1
                campos[23] = cli.telefono2
                campos[24] = cli.telefono3
                campos[25] = cli.telefono4
                campos[26] = cli.telefono5
                campos[27] = cli.telefono6
                campos[28] = cli.telefono7
                campos[29] = cli.telefono8
                campos[30] = cli.telefono9
                campos[31] = cli.telefono10
                campos[32] = cli.id
                campos[33] = cli.fechaGestion.toString()
                campos[34] = cli.creadas_nocreadas
                campos[35] = cli.imputable
                campos[36] = cli.detalle_imputable
                campos[37] = cli.fecha_envio_creacion
                campos[38] = cli.nombreBase
                campos[39] = "105"
                campos[40] = cli.codigoCampania
                campos[41] = cli.statusCourier
                campos[42] = cli.cicloCourier
                campos[43] = cli.cierre_ciclo
                campos[44] = cli.guia_courier
                campos[45] = cli.identificacion

                ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
            }

            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=BitacoraPlanificacion.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return
        }
    }

    static String formatearFecha(Date fechas){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fechaTexto = formatter.format(fechas);
        return fechaTexto
    }

    def bitacoraTrama() {
        if (params.fechas) {
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
            def nombresBase = params.list("nombreBase")
            def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
            String sqlPrincipales = "from Clientes where fechaGestion between :fechaInicio and :fechaFin and subestadoGestion in (:subestados)"
            def principalesList = Clientes.executeQuery(sqlPrincipales, condiciones)
            def binTarjetas = Tarjeta.executeQuery("from Tarjeta")
            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("UTF-8")
            WritableWorkbook workbook = Workbook.createWorkbook(file)
            workbook.createSheet("Creditos", 0)
            WritableSheet sheet = workbook.getSheet(0)
            String[] headers = ["TIPO ID/ ", "NUMERO ID/ ", "APELLIDO1/ ", "APELLIDO2/ ", "NOMBRE1/ ", "NOMBRE2/ ", "DIRECCION RESIDENCIA/ ",
                                "DIRECCION TRABAJO/ ", "INDICADOR RUTA ENTREGA/ ", "INDICADOR ESTADO CUENTA/ ", "TELEFONO/ ", "TELEFONO2/ ", "CELULAR/ ", "CORREO PERSONAL/ ", "SUCURSAL/ ",
                                "OFICINA/ ", "CODCUIDAD/ ", "COD PARROQUIA/ ", "NACIONALIDAD/ ", "FECHA NACIMIENTO/ ", "GENERO/ ", "ESTADO CIVIL/ ", "NIVEL ESTUDIOS/ ", "PROFESION/ ", "ACTIV ECONOMICA/ ",
                                "ORIGEN INGRESOS/ ", "RANGO INGRESOS MES/ ", "PATRIMONIO/ ", "TIPO VIVIENDA/ ", "VALOR VIVIENDA/ ", "FECHA INICIO RESIDENCIA/ ", "RELACION LABORAL/ ", "FCH INI TRAB ACTUAL/ ",
                                "FCH INI TRAB ANTERIOR/ ", "FCH FIN TRAB ANTERIOR/ ", "CARGAS FAMILIARES/ ", "BIN PLASTICO/ ", "AFINIDAD/ ", "CUPO/ ", "NOMBRE IMPRESO/ ", "VENDEDOR/ ", "TIPO ID REF PERSONA/ ", "NUMERO ID REF PERSONAL/ ", "APELLIDO1 REF PERSONAL/ ",
                                "APELLIDO2 REF PERSONAL/ ", "NOMBRE1 REF PERSONAL/ ", "NOMBRE2 REF PERSONAL/ ", "DIRECCION REF PERSONAL/ ", "TELEFONO REF PERSONAL/ ", "PARENTESCO REF PERSONAL/ ", "INDICADOR PRINC/ADIC/CONV/ ", "TIPO ID PRINCIPAL/ ", "NUM ID PRINCIPAL/ ",
                                "BIN PLASTICO PRINC/ ", "PARENTESCO/ ", "TIPO ID CONYUGE/ ", "NUMERO IDCONYUGE/ ", "APELLIDO1CONYUGE/ ", "APELLIDO2CONYUGE/ ", "NOMBRE1CONYUGE/ ", "NOMBRE2CONYUGE/ ", "PAIS CONYUGE/ ", "GENERO CONYUGE/ ", "FECHA DE NACIMIENTOCONYUGE/ ",
                                "ESTADO CIVIL CONYUGE/ ", "PAIS DE NACIMIENTOCONYUGE/ ",
                                'CODCIUDAD DE DOMICILIO/ ',
                                'CPARROQUIA DE DOMICILIO/ ',
                                'SECTOR DE DOMICILIO/ ',
                                'CODCIUDAD DE TRABAJO/ ',
                                'PARROQUIA DE TRABAJO/ ',
                                'SECTOR DE TRABAJO/ ',
                                'LUGAR DE TRABAJO/ ',
                                'CARGOPERSONA/ ',
                                'OCUPACIONPERSONA/ ',
                                'MONTOMENSUALACTIVO/ ',
                                'MONTOMENSUALPASIVO/ ',
                                'MONTOMENSUALINGRESO/ ',
                                'MONTOMENSUALGASTOMENSUAL/ ',
                                'CCIUDADREFERENCIA/ ', "NOMBRE VENDEDOR/ ", "FECHA GESTION/ ","UPGRADE/ "]
            ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)
            for (int i = 0; i < principalesList.size(); i++) {
                String[] campos = new String[headers.length]
                Clientes cli = principalesList.get(i)
                int valor = 2
                String binPlatico = ""
                String tipoIdentificacion = cli.tipoIdentificacion
                for (int l = 0; l < valor; l++) {
                    if (tipoIdentificacion == "") {
                        tipoIdentificacion = "   "
                    }
                    for (int k = 0; k < tipoIdentificacion.length(); k++) {
                        if (tipoIdentificacion.length() < 3) {
                            tipoIdentificacion = tipoIdentificacion + " "
                        } else {
                            if (tipoIdentificacion.length() > 3) {
                                tipoIdentificacion = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (tipoIdentificacion.length() == 3 || tipoIdentificacion.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[0] = tipoIdentificacion
                String cedulaVerificada = cli.cedulaVerificada
                for (int l = 0; l < valor; l++) {
                    if (cedulaVerificada == "") {
                        cedulaVerificada = "                   "
                    }
                    for (int k = 0; k < cedulaVerificada.length(); k++) {
                        if (cedulaVerificada.length() < 19) {
                            cedulaVerificada = cedulaVerificada + " "
                        }else{
                            if(cedulaVerificada.length() > 19){
                                cedulaVerificada = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cedulaVerificada.length() == 19 || cedulaVerificada.length() > 19) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[1] = cedulaVerificada
                String apellido1 = cli.apellido1
                for (int l = 0; l < valor; l++) {
                    if (apellido1 == "") {
                        apellido1 = "                         "
                    }
                    for (int k = 0; k < apellido1.length(); k++) {
                        if (apellido1.length() < 25) {
                            apellido1 = apellido1 + " "
                        }else{
                            if(apellido1.length() > 25){
                                apellido1 = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido1.length() == 25 || apellido1.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[2] = apellido1
                String apellido2 = cli.apellido2
                for (int l = 0; l < valor; l++) {
                    if (apellido2 == "") {
                        apellido2 = "                         "
                    }
                    for (int k = 0; k < apellido2.length(); k++) {
                        if (apellido2.length() < 25) {
                            apellido2 = apellido2 + " "
                        }else{
                            if(apellido2.length() > 25){
                                apellido1 = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido2.length() == 25 || apellido2.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[3] = apellido2
                String nombreVerificado = cli.nombreVerificado
                for (int l = 0; l < valor; l++) {
                    if (nombreVerificado == "") {
                        nombreVerificado = "                    "
                    }
                    for (int k = 0; k < nombreVerificado.length(); k++) {
                        if (nombreVerificado.length() < 20) {
                            nombreVerificado = nombreVerificado + " "
                        }else{
                            if(nombreVerificado.length() > 20){
                                nombreVerificado = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombreVerificado.length() == 20 || nombreVerificado.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[4] = nombreVerificado
                String nombre2Verificado = cli.nombre2Verificado
                for (int l = 0; l < valor; l++) {
                    if (nombre2Verificado == "") {
                        nombre2Verificado = "                              "
                    }
                    for (int k = 0; k < nombre2Verificado.length(); k++) {
                        if (nombre2Verificado.length() < 30) {
                            nombre2Verificado = nombre2Verificado + " "
                        }else{
                            if(nombre2Verificado.length() > 30){
                                nombre2Verificado = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre2Verificado.length() == 30 || nombre2Verificado.length() > 30) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[5] = nombre2Verificado
                String direccionDomicilio = cli.callePrincipalNumeracionDomicilio + ' ' + cli.calleTransversalDomicilio + ' ' + cli.referenciaDomicilio
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < direccionDomicilio.length(); k++) {
                        if (direccionDomicilio.length() < 150) {
                            direccionDomicilio = direccionDomicilio + " "
                        }else{
                            if(direccionDomicilio.length() > 150){
                                direccionDomicilio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (direccionDomicilio.length() == 150 || direccionDomicilio.length() > 150) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[6] = direccionDomicilio
                String direccionTrabajo = cli.callePrincipalNumeracionTrabajo + ' ' + cli.calleTransversalTrabajo + ' ' + cli.referenciaTrabajo
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < direccionTrabajo.length(); k++) {
                        if (direccionTrabajo.length() < 150) {
                            direccionTrabajo = direccionTrabajo + " "
                        }else{
                            if(direccionTrabajo.length() > 150){
                                direccionTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (direccionTrabajo.length() == 150 || direccionTrabajo.length() > 150) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[7] = direccionTrabajo
                if (cli.lugarEntrega.equalsIgnoreCase("DIRECCION DE RESIDENCIA")) {
                    campos[8] = "1"
                } else {
                    campos[8] = "2"
                }
                if (cli.lugarEntregaECuenta.equalsIgnoreCase("DIRECCION DE RESIDENCIA")) {
                    campos[9] = "1"
                } else {
                    campos[9] = "2"
                }
                String telefonoCasa = cli.telefonoCasa
                for (int l = 0; l < valor; l++) {
                    if (telefonoCasa == "") {
                        telefonoCasa = "            "
                    }
                    for (int k = 0; k < telefonoCasa.length(); k++) {
                        if (telefonoCasa.length() < 12) {
                            telefonoCasa = telefonoCasa + " "
                        }else{
                            if(telefonoCasa.length() > 12){
                                telefonoCasa = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (telefonoCasa.length() == 12 || telefonoCasa.length() > 12) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[10] = telefonoCasa
                String telefonoTrabajo = cli.telefonoTrabajo
                for (int l = 0; l < valor; l++) {
                    if (telefonoTrabajo == "") {
                        telefonoTrabajo = "            "
                    }
                    for (int k = 0; k < telefonoTrabajo.length(); k++) {
                        if (telefonoTrabajo.length() < 12) {
                            telefonoTrabajo = telefonoTrabajo + " "
                        }else{
                            if(telefonoTrabajo.length() > 12){
                                telefonoTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (telefonoTrabajo.length() == 12 || telefonoTrabajo.length() > 12) {
                        break;
                    }
                    valor = valor + 1
                }

                campos[11] = telefonoTrabajo
                String celular = cli.celular
                for (int l = 0; l < valor; l++) {
                    if (celular == "") {
                        celular = "            "
                    }
                    for (int k = 0; k < celular.length(); k++) {
                        if (celular.length() < 12) {
                            celular = celular + " "
                        }else{
                            if(celular.length() > 12){
                                celular = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (celular.length() == 12 || celular.length() == 12) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[12] = celular
                String emailPersonal = cli.emailPersonal
                for (int l = 0; l < valor; l++) {
                    if (emailPersonal == "") {
                        emailPersonal = "                                                                     "
                    }
                    for (int k = 0; k < emailPersonal.length(); k++) {
                        if (emailPersonal.length() < 70) {
                            emailPersonal = emailPersonal + " "
                        }else {
                            if(emailPersonal.length() > 70){
                                emailPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (emailPersonal.length() == 70 || emailPersonal.length() > 70) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[13] = emailPersonal
                campos[14] = cli.sucursal
                campos[15] = cli.oficina
                String codigoCiudad = cli.codigoCiudad
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < codigoCiudad.length(); k++) {
                        if (codigoCiudad.length() < 6) {
                            codigoCiudad = codigoCiudad + " "
                        }else{
                            if(codigoCiudad.length() > 6){
                                codigoCiudad = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoCiudad.length() == 6 || codigoCiudad.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[16] = codigoCiudad
                String codigoParroquia = cli.codigoParroquia
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < codigoParroquia.length(); k++) {
                        if (codigoParroquia.length() < 6) {
                            codigoParroquia = codigoParroquia + " "
                        }else{
                            if(codigoParroquia.length() > 6){
                                codigoParroquia = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoParroquia.length() == 6 || codigoParroquia.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[17] = codigoParroquia
                String nacionalidad = cli.nacionalidad
                for (int l = 0; l < valor; l++) {
                    if (nacionalidad == "") {
                        nacionalidad = "   "
                    }
                    for (int k = 0; k < nacionalidad.length(); k++) {
                        if (nacionalidad.length() < 3) {
                            nacionalidad = nacionalidad + " "
                        }else{
                            if(nacionalidad.length() > 3){
                                nacionalidad = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nacionalidad.length() == 3 || nacionalidad.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[18] = nacionalidad
                campos[19] = cli.fechaNacimiento
                campos[20] = cli.genero
                campos[21] = cli.estadoCivil
                String nivelEstudios = cli.nivelEstudios
                for (int l = 0; l < valor; l++) {
                    if (nivelEstudios == "") {
                        nivelEstudios = "  "
                    }
                    for (int k = 0; k < nivelEstudios.length(); k++) {
                        if (nivelEstudios.length() < 2) {
                            nivelEstudios = nivelEstudios + " "
                        }else{
                            if(nivelEstudios.length() > 2){
                                nivelEstudios = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nivelEstudios.length() == 2 || nivelEstudios.length() > 2) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[22] = nivelEstudios
                String profesion = cli.profesion
                for (int l = 0; l < valor; l++) {
                    if (profesion == "") {
                        profesion = "    "
                    }
                    for (int k = 0; k < profesion.length(); k++) {
                        if (profesion.length() < 4) {
                            profesion = profesion + " "
                        }else{
                            if(profesion.length() > 4){
                                profesion = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (profesion.length() == 4 || profesion.length() > 4) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[23] = profesion
                String actividadEconomica = cli.actividadEconomica
                for (int l = 0; l < valor; l++) {
                    if (actividadEconomica == "") {
                        actividadEconomica = "          "
                    }
                    for (int k = 0; k < actividadEconomica.length(); k++) {
                        if (actividadEconomica.length() < 10) {
                            actividadEconomica = actividadEconomica + " "
                        }else{
                            if(actividadEconomica.length() > 10){
                                actividadEconomica = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (actividadEconomica.length() == 10 || actividadEconomica.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[24] = actividadEconomica
                String origenIngresos = cli.origenIngresos
                for (int l = 0; l < valor; l++) {
                    if (origenIngresos == "") {
                        origenIngresos = "   "
                    }
                    for (int k = 0; k < origenIngresos.length(); k++) {
                        if (origenIngresos.length() < 3) {
                            origenIngresos = origenIngresos + " "
                        }else{
                            if(origenIngresos.length() > 3){
                                origenIngresos = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (origenIngresos.length() == 3 || origenIngresos.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[25] = origenIngresos
                String rangoIngresos = cli.rangoIngresos
                for (int l = 0; l < valor; l++) {
                    if (rangoIngresos == "") {
                        rangoIngresos = "   "
                    }
                    for (int k = 0; k < rangoIngresos.length(); k++) {
                        if (rangoIngresos.length() < 3) {
                            rangoIngresos = rangoIngresos + " "
                        }else{
                            if(rangoIngresos.length() > 3){
                                rangoIngresos = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (rangoIngresos.length() == 3 || rangoIngresos.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[26] = rangoIngresos
                String patrimonio = cli.patrimonio
                /*for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < patrimonio.length(); k++) {
                        if (patrimonio.length() < 10) {
                            patrimonio = patrimonio + " "
                        }
                    }
                    if (patrimonio.length() == 10) {
                        break;
                    }
                    valor = valor + 1
                }*/
                for (int l = 0; l < valor; l++) {
                    if (patrimonio == "") {
                        patrimonio = "          "
                    }
                    for (int k = 0; k < patrimonio.length(); k++) {
                        if (patrimonio.length() < 8) {
                            patrimonio = "0" + patrimonio
                        }else{
                            if(patrimonio.length() > 8){
                                patrimonio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (patrimonio.length() == 8) {
                        patrimonio = patrimonio + "00"
                    }
                    if (patrimonio.length() == 10 || patrimonio.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[27] = patrimonio
                campos[28] = cli.tipoVivienda//cli.tipoVivienda
                String valorVivienda = cli.valorVivienda
                /*for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < valorVivienda.length(); k++) {
                        if (valorVivienda.length() < 10) {
                            valorVivienda = valorVivienda + " "
                        }
                    }
                    if (valorVivienda.length() == 10) {
                        break;
                    }
                    valor = valor + 1
                }*/
                for (int l = 0; l < valor; l++) {
                    if (valorVivienda == "") {
                        valorVivienda = "          "
                    }
                    for (int k = 0; k < valorVivienda.length(); k++) {
                        if (valorVivienda.length() < 8) {
                            valorVivienda = "0" + valorVivienda
                        }else{
                            if(valorVivienda.length() > 8){
                                valorVivienda = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (valorVivienda.length() == 8) {
                        valorVivienda = valorVivienda + "00"
                    }
                    if (valorVivienda.length() == 10 || valorVivienda.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[29] = valorVivienda
                campos[30] = cli.fechaInicioResidencia//cli.fechaInicioResidencia
                campos[31] = cli.relacionLaboral
                campos[32] = cli.fechaInicioTrabajoActual
                campos[33] = cli.fechaInicioTrabajoAnterior
                campos[34] = cli.fechaFinTrabajoAnterior
                String cargasFamiliares = cli.cargasFamiliares
                for (int l = 0; l < valor; l++) {
                    if (cargasFamiliares == "") {
                        cargasFamiliares = "  "
                    }
                    for (int k = 0; k < cargasFamiliares.length(); k++) {
                        if (cargasFamiliares.length() < 2) {
                            cargasFamiliares = "0" + cargasFamiliares
                        }else{
                            if(cargasFamiliares.length() > 2){
                                cargasFamiliares = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cargasFamiliares.length() == 2 || cargasFamiliares.length() > 2) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[35] = cargasFamiliares
                //int contador = 0;
                for (int j = 0; j < binTarjetas.size(); j++) {
                    Tarjeta tar = binTarjetas.get(j)
                    if (cli.productoAceptado == tar.nombre) {
                        campos[36] = tar.bin
                        campos[37] = tar.afinidad
                        binPlatico = tar.bin
                        //contador ++
                      //  println(contador + " " + tar.bin + " " + tar.afinidad)
                    }
                    if (cli.productoAceptado == "VISA INTERNACIONAL" && cli.genero == tar.genero) {
                        campos[36] = tar.bin
                        campos[37] = tar.afinidad
                        binPlatico = tar.bin
                        break
                    }
                }
                String cupo = cli.cupo
                /*for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < cupo.length(); k++) {
                        if (cupo.length() < 5) {
                            cupo = cupo + " "
                        }
                    }
                    if (cupo.length() == 5) {
                        break;
                    }
                    valor = valor + 1
                }*/
                for (int l = 0; l < valor; l++) {
                    if (cupo == "") {
                        cupo = "     "
                    }
                    for (int k = 0; k < cupo.length(); k++) {
                        if (cupo.length() < 5) {
                            cupo = "0" + cupo
                        }else{
                            if(cupo.length() > 5){
                                cupo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cupo.length() == 5) {
                        cupo = cupo + "00"
                    }
                    if (cupo.length() == 7 || cupo.length() > 7) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[38] = cupo
                String nombreImpreso = cli.nombreImpreso
                for (int l = 0; l < valor; l++) {
                    if (nombreImpreso == "") {
                        nombreImpreso = "                   "
                    }
                    for (int k = 0; k < nombreImpreso.length(); k++) {
                        if (nombreImpreso.length() < 19) {
                            nombreImpreso = nombreImpreso + " "
                        } else {
                            if (nombreImpreso.length() > 19) {
                                nombreImpreso = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombreImpreso.length() == 19 || nombreImpreso.length() > 19) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[39] = nombreImpreso// cli.cupo
                /*String vendedor = cli.usuario.id
                for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < vendedor.length(); k++) {
                        if (vendedor.length() < 5) {
                            vendedor = vendedor + " "
                        }
                    }
                    if (vendedor.length() == 5) {
                        break;
                    }
                    valor = valor + 1
                }*/
                campos[40] = "1340 "
                campos[41] = cli.tipoIdentificacionRefPersonal
                String identificacionRefPersonal = cli.identificacionRefPersonal
                for (int l = 0; l < valor; l++) {
                    if (identificacionRefPersonal == "") {
                        identificacionRefPersonal = "          "
                    }
                    for (int k = 0; k < identificacionRefPersonal.length(); k++) {
                        if (identificacionRefPersonal.length() < 10) {
                            identificacionRefPersonal = identificacionRefPersonal + " "
                        } else {
                            if (identificacionRefPersonal.length() > 10) {
                                identificacionRefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (identificacionRefPersonal.length() == 10 || identificacionRefPersonal.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[42] = identificacionRefPersonal
                String apellido1RefPersonal = cli.apellido1RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (apellido1RefPersonal == "") {
                        apellido1RefPersonal = "                    "
                    }
                    for (int k = 0; k < apellido1RefPersonal.length(); k++) {
                        if (apellido1RefPersonal.length() < 20) {
                            apellido1RefPersonal = apellido1RefPersonal + " "
                        }else{
                            if (apellido1RefPersonal.length() > 20) {
                                apellido1RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido1RefPersonal.length() == 20 || apellido1RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[43] = apellido1RefPersonal
                String apellido2RefPersonal = cli.apellido2RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (apellido2RefPersonal == "") {
                        apellido2RefPersonal = "                    "
                    }
                    for (int k = 0; k < apellido2RefPersonal.length(); k++) {
                        if (apellido2RefPersonal.length() < 20) {
                            apellido2RefPersonal = apellido2RefPersonal + " "
                        }else{
                            if (apellido2RefPersonal.length() > 20) {
                                apellido2RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido2RefPersonal.length() == 20 || apellido2RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[44] = apellido2RefPersonal
                String nombre1RefPersonal = cli.nombre1RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (nombre1RefPersonal == "") {
                        nombre1RefPersonal = "                    "
                    }
                    for (int k = 0; k < nombre1RefPersonal.length(); k++) {
                        if (nombre1RefPersonal.length() < 20) {
                            nombre1RefPersonal = nombre1RefPersonal + " "
                        }else{
                            if (nombre1RefPersonal.length() > 20) {
                                nombre1RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre1RefPersonal.length() == 20 || nombre1RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[45] = nombre1RefPersonal
                String nombre2RefPersonal = cli.nombre2RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (nombre2RefPersonal == "") {
                        nombre2RefPersonal = "                    "
                    }
                    for (int k = 0; k < nombre2RefPersonal.length(); k++) {
                        if (nombre2RefPersonal.length() < 20) {
                            nombre2RefPersonal = nombre2RefPersonal + " "
                        }else{
                            if (nombre2RefPersonal.length() > 20) {
                                nombre2RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre2RefPersonal.length() == 20 || nombre2RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[46] = nombre2RefPersonal
                String direccionReferencia = cli.callePrincipalRefPersonal + ' ' + cli.calleSecundariaRefPersonal + ' ' + cli.referenciaRefPersonal
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < direccionReferencia.length(); k++) {
                        if (direccionReferencia.length() < 150) {
                            direccionReferencia = direccionReferencia + " "
                        }else{
                            if (direccionReferencia.length() > 150) {
                                direccionReferencia = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (direccionReferencia.length() == 150 || direccionReferencia.length() > 150) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[47] = direccionReferencia
                String telefonoRefPersonal = cli.telefonoRefPersonal
                for (int l = 0; l < valor; l++) {
                    if (telefonoRefPersonal == "") {
                        telefonoRefPersonal = "                    "
                    }
                    for (int k = 0; k < telefonoRefPersonal.length(); k++) {
                        if (telefonoRefPersonal.length() < 20) {
                            telefonoRefPersonal = telefonoRefPersonal + " "
                        }else{
                            if (telefonoRefPersonal.length() > 20) {
                                telefonoRefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (telefonoRefPersonal.length() == 20 || telefonoRefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[48] = telefonoRefPersonal
                campos[49] = cli.parentescoRefPersonal
                campos[50] = "P"

                campos[51] = " "
                /*String cedulaVerificada2 = cli.cedulaVerificada
                for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < cedulaVerificada2.length(); k++) {
                        if (cedulaVerificada2.length() < 10) {
                            cedulaVerificada2 = cedulaVerificada2 + " "
                        }
                    }
                    if (cedulaVerificada2.length() == 10) {
                        break;
                    }
                    valor = valor + 1
                }*/
                campos[52] = "          "
                campos[53] = "      "
                campos[54] = "  "
                String tipoIdentificacionConyuge = cli.tipoIdentificacionConyuge
                for (int l = 0; l < valor; l++) {
                    if (tipoIdentificacionConyuge == "") {
                        tipoIdentificacionConyuge = "   "
                    }
                    for (int k = 0; k < tipoIdentificacionConyuge.length(); k++) {
                        if (tipoIdentificacionConyuge.length() < 3) {
                            tipoIdentificacionConyuge = tipoIdentificacionConyuge + " "
                        }else{
                            if (tipoIdentificacionConyuge.length() > 3) {
                                tipoIdentificacionConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (tipoIdentificacionConyuge.length() == 3 || tipoIdentificacionConyuge.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[55] = tipoIdentificacionConyuge
                String identificacionConyuge = cli.identificacionConyuge
                for (int l = 0; l < valor; l++) {
                    if (identificacionConyuge == "") {
                        identificacionConyuge = "                   "
                    }
                    for (int k = 0; k < identificacionConyuge.length(); k++) {
                        if (identificacionConyuge.length() < 19) {
                            identificacionConyuge = identificacionConyuge + " "
                        }else{
                            if (identificacionConyuge.length() > 19) {
                                identificacionConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (identificacionConyuge.length() == 19 || identificacionConyuge.length() > 19) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[56] = identificacionConyuge
                String apellido1Conyuge = cli.apellido1Conyuge
                for (int l = 0; l < valor; l++) {
                    if (apellido1Conyuge == "") {
                        apellido1Conyuge = "                         "
                    }
                    for (int k = 0; k < apellido1Conyuge.length(); k++) {
                        if (apellido1Conyuge.length() < 25) {
                            apellido1Conyuge = apellido1Conyuge + " "
                        }else{
                            if (apellido1Conyuge.length() > 25) {
                                apellido1Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido1Conyuge.length() == 25 || apellido1Conyuge.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[57] = apellido1Conyuge
                String apellido2Conyuge = cli.apellido2Conyuge
                for (int l = 0; l < valor; l++) {
                    if (apellido2Conyuge == "") {
                        apellido2Conyuge = "                         "
                    }
                    for (int k = 0; k < apellido2Conyuge.length(); k++) {
                        if (apellido2Conyuge.length() < 25) {
                            apellido2Conyuge = apellido2Conyuge + " "
                        }else{
                            if (apellido2Conyuge.length() > 25) {
                                apellido2Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido2Conyuge.length() == 25 || apellido2Conyuge.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[58] = apellido2Conyuge
                String nombre1Conyuge = cli.nombre1Conyuge
                for (int l = 0; l < valor; l++) {
                    if (nombre1Conyuge == "") {
                        nombre1Conyuge = "                    "
                    }
                    for (int k = 0; k < nombre1Conyuge.length(); k++) {
                        if (nombre1Conyuge.length() < 20) {
                            nombre1Conyuge = nombre1Conyuge + " "
                        }else{
                            if (nombre1Conyuge.length() > 20) {
                                nombre1Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre1Conyuge.length() == 20 || nombre1Conyuge.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[59] = nombre1Conyuge
                String nombre2Conyuge = cli.nombre2Conyuge
                for (int l = 0; l < valor; l++) {
                    if (nombre2Conyuge == "") {
                        nombre2Conyuge = "                              "
                    }
                    for (int k = 0; k < nombre2Conyuge.length(); k++) {
                        if (nombre2Conyuge.length() < 30) {
                            nombre2Conyuge = nombre2Conyuge + " "
                        }else{
                            if (nombre2Conyuge.length() > 30) {
                                nombre2Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre2Conyuge.length() == 30 || nombre2Conyuge.length() > 30) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[60] = nombre2Conyuge
                String paisConyuge = cli.paisConyuge
                for (int l = 0; l < valor; l++) {
                    if (paisConyuge == "") {
                        paisConyuge = "   "
                    }
                    for (int k = 0; k < paisConyuge.length(); k++) {
                        if (paisConyuge.length() < 3) {
                            paisConyuge = paisConyuge + " "
                        }else{
                            if (paisConyuge.length() > 3) {
                                paisConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (paisConyuge.length() == 3 || paisConyuge.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[61] = paisConyuge
                String generoConyuge = cli.generoConyuge
                for (int l = 0; l < valor; l++) {
                    if (generoConyuge == "") {
                        generoConyuge = " "
                    }
                    for (int k = 0; k < generoConyuge.length(); k++) {
                        if (generoConyuge.length() < 1) {
                            generoConyuge = generoConyuge + " "
                        }else{
                            if (paisConyuge.length() > 1) {
                                paisConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (generoConyuge.length() == 1 || generoConyuge.length() > 1) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[62] = generoConyuge
                String fechaNacimientoConyuge = cli.fechaNacimientoConyuge
                for (int l = 0; l < valor; l++) {
                    if (fechaNacimientoConyuge == "") {
                        fechaNacimientoConyuge = "          "
                    }
                    for (int k = 0; k < fechaNacimientoConyuge.length(); k++) {
                        if (fechaNacimientoConyuge.length() < 10) {
                            fechaNacimientoConyuge = fechaNacimientoConyuge + " "
                        }else{
                            if (fechaNacimientoConyuge.length() > 10) {
                                fechaNacimientoConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (fechaNacimientoConyuge.length() == 10 || fechaNacimientoConyuge.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[63] = fechaNacimientoConyuge
                String estadoCivilConyuge = cli.estadoCivilConyuge
                for (int l = 0; l < valor; l++) {
                    if (estadoCivilConyuge == "") {
                        estadoCivilConyuge = " "
                    }
                    for (int k = 0; k < estadoCivilConyuge.length(); k++) {
                        if (estadoCivilConyuge.length() < 1) {
                            estadoCivilConyuge = estadoCivilConyuge + " "
                        }else{
                            if (estadoCivilConyuge.length() > 1) {
                                estadoCivilConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (estadoCivilConyuge.length() == 1 || estadoCivilConyuge.length() > 1) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[64] = estadoCivilConyuge
                String paisNacimientoConyuge = cli.paisNacimientoConyuge
                for (int l = 0; l < valor; l++) {
                    if (paisNacimientoConyuge == "") {
                        paisNacimientoConyuge = "   "
                    }
                    for (int k = 0; k < paisNacimientoConyuge.length(); k++) {
                        if (paisNacimientoConyuge.length() < 3) {
                            paisNacimientoConyuge = paisNacimientoConyuge + " "
                        }else {
                            if (paisNacimientoConyuge.length() > 3) {
                                paisNacimientoConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (paisNacimientoConyuge.length() == 3 || paisNacimientoConyuge.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[65] = paisNacimientoConyuge
                String codCiudadDomicilio = cli.codigoCiudad
                for (int l = 0; l < valor; l++) {
                    if (codCiudadDomicilio == "") {
                        codCiudadDomicilio = "      "
                    }
                    for (int k = 0; k < codCiudadDomicilio.length(); k++) {
                        if (codCiudadDomicilio.length() < 6) {
                            codCiudadDomicilio = codCiudadDomicilio + " "
                        }else {
                            if (codCiudadDomicilio.length() > 6) {
                                codCiudadDomicilio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codCiudadDomicilio.length() == 6 || codCiudadDomicilio.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[66] = codCiudadDomicilio
                String codParroquiaDomicilio = cli.codigoParroquia
                for (int l = 0; l < valor; l++) {
                    if (codParroquiaDomicilio == "") {
                        codParroquiaDomicilio = "  "
                    }
                    for (int k = 0; k < codParroquiaDomicilio.length(); k++) {
                        if (codParroquiaDomicilio.length() < 2) {
                            codParroquiaDomicilio = codParroquiaDomicilio + " "
                        }else {
                            if (codParroquiaDomicilio.length() > 2) {
                                codParroquiaDomicilio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codParroquiaDomicilio.length() == 2 || codParroquiaDomicilio.length() > 2) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[67] = codParroquiaDomicilio
                String sectorDomicilio = Parroquia.findById(cli.sectorDomicilio.toString().toLong()).nombre
                for (int l = 0; l < valor; l++) {
                    if (sectorDomicilio == "") {
                        sectorDomicilio = "                                                  "
                    }
                    for (int k = 0; k < sectorDomicilio.length(); k++) {
                        if (sectorDomicilio.length() < 50) {
                            sectorDomicilio = sectorDomicilio + " "
                        }else {
                            if (sectorDomicilio.length() > 50) {
                                sectorDomicilio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (sectorDomicilio.length() == 50 || sectorDomicilio.length() > 50) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[68] = sectorDomicilio

                String codigoCiudadTrabajo = cli.codigoCiudadTrabajo
                for (int l = 0; l < valor; l++) {
                    if (codigoCiudadTrabajo == "") {
                        codigoCiudadTrabajo = "      "
                    }
                    for (int k = 0; k < codigoCiudadTrabajo.length(); k++) {
                        if (codigoCiudadTrabajo.length() < 6) {
                            codigoCiudadTrabajo = codigoCiudadTrabajo + " "
                        }else {
                            if (codigoCiudadTrabajo.length() > 6) {
                                codigoCiudadTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoCiudadTrabajo.length() == 6 || codigoCiudadTrabajo.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[69] = codigoCiudadTrabajo

                String codigoParroquiaTrabajo = cli.codigoParroquiaTrabajo
                for (int l = 0; l < valor; l++) {
                    if (codigoParroquiaTrabajo == "") {
                        codigoParroquiaTrabajo = "  "
                    }
                    for (int k = 0; k < codigoParroquiaTrabajo.length(); k++) {
                        if (codigoParroquiaTrabajo.length() < 2) {
                            codigoParroquiaTrabajo = codigoParroquiaTrabajo + " "
                        }else {
                            if (codigoParroquiaTrabajo.length() > 2) {
                                codigoParroquiaTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoParroquiaTrabajo.length() == 2 || codigoParroquiaTrabajo.length() > 2) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[70] = codigoParroquiaTrabajo

                String sectorTrabajo = Parroquia.findById(cli.sectorTrabajo.toString().toLong()).nombre
                for (int l = 0; l < valor; l++) {
                    if (sectorTrabajo == "") {
                        sectorTrabajo = "                                                  "
                    }
                    for (int k = 0; k < sectorTrabajo.length(); k++) {
                        if (sectorTrabajo.length() < 50) {
                            sectorTrabajo = sectorTrabajo + " "
                        }else {
                            if (sectorTrabajo.length() > 50) {
                                sectorTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (sectorTrabajo.length() == 50 || sectorTrabajo.length() > 50) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[71] = sectorTrabajo

                String lugarTrabajo = cli.nombreEmpresa
                for (int l = 0; l < valor; l++) {
                    if (lugarTrabajo == "") {
                        lugarTrabajo = "                                                  "
                    }
                    for (int k = 0; k < lugarTrabajo.length(); k++) {
                        if (lugarTrabajo.length() < 50) {
                            lugarTrabajo = lugarTrabajo + " "
                        }else {
                            if (lugarTrabajo.length() > 50) {
                                lugarTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (lugarTrabajo.length() == 50 || lugarTrabajo.length() > 50) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[72] = lugarTrabajo

                String cargoPersona = cli.cargo
                for (int l = 0; l < valor; l++) {
                    if (cargoPersona == "") {
                        cargoPersona = "    "
                    }
                    for (int k = 0; k < cargoPersona.length(); k++) {
                        if (cargoPersona.length() < 4) {
                            cargoPersona = cargoPersona + " "
                        }else {
                            if (cargoPersona.length() > 4) {
                                cargoPersona = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cargoPersona.length() == 4 || cargoPersona.length() > 4) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[73] = cargoPersona

                String ocupacionPersona = cli.ocupacion
                for (int l = 0; l < valor; l++) {
                    if (ocupacionPersona == "") {
                        ocupacionPersona = "    "
                    }
                    for (int k = 0; k < ocupacionPersona.length(); k++) {
                        if (ocupacionPersona.length() < 4) {
                            ocupacionPersona = ocupacionPersona + " "
                        }else {
                            if (ocupacionPersona.length() > 4) {
                                ocupacionPersona = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (ocupacionPersona.length() == 4 || ocupacionPersona.length() > 4) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[74] = ocupacionPersona

                String montoMensualActivo = cli.valorActivo
                for (int l = 0; l < valor; l++) {
                    if (montoMensualActivo == "") {
                        montoMensualActivo = "        "
                    }
                    for (int k = 0; k < montoMensualActivo.length(); k++) {
                        if (montoMensualActivo.length() < 8) {
                            montoMensualActivo = montoMensualActivo + " "
                        }else {
                            if (montoMensualActivo.length() > 8) {
                                montoMensualActivo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (montoMensualActivo.length() == 8 || montoMensualActivo.length() > 8) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[75] = montoMensualActivo

                String montoMensualPasivo = cli.valorPasivo
                for (int l = 0; l < valor; l++) {
                    if (montoMensualPasivo == "") {
                        montoMensualPasivo = "        "
                    }
                    for (int k = 0; k < montoMensualPasivo.length(); k++) {
                        if (montoMensualPasivo.length() < 8) {
                            montoMensualPasivo = montoMensualPasivo + " "
                        }else {
                            if (montoMensualPasivo.length() > 8) {
                                montoMensualPasivo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (montoMensualPasivo.length() == 8 || montoMensualPasivo.length() > 8) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[76] = montoMensualPasivo

                String montoMensualIngresos = calculaValores('INGRESOS', cli.id)
                for (int l = 0; l < valor; l++) {
                    if (montoMensualIngresos == "") {
                        montoMensualIngresos = "        "
                    }
                    for (int k = 0; k < montoMensualIngresos.length(); k++) {
                        if (montoMensualIngresos.length() < 8) {
                            montoMensualIngresos = montoMensualIngresos + " "
                        }else {
                            if (montoMensualIngresos.length() > 8) {
                                montoMensualIngresos = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (montoMensualIngresos.length() == 8 || montoMensualIngresos.length() > 8) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[77] = montoMensualIngresos

                String montoMensualEgresos = calculaValores('EGRESOS', cli.id)
                for (int l = 0; l < valor; l++) {
                    if (montoMensualEgresos == "") {
                        montoMensualEgresos = "        "
                    }
                    for (int k = 0; k < montoMensualEgresos.length(); k++) {
                        if (montoMensualEgresos.length() < 8) {
                            montoMensualEgresos = montoMensualEgresos + " "
                        }else {
                            if (montoMensualEgresos.length() > 8) {
                                montoMensualEgresos = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (montoMensualEgresos.length() == 8 || montoMensualEgresos.length() > 8) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[78] = montoMensualEgresos

                String codigoCiudadReferencia = cli.codigoCiudadReferencia
                for (int l = 0; l < valor; l++) {
                    if (codigoCiudadReferencia == "") {
                        codigoCiudadReferencia = "      "
                    }
                    for (int k = 0; k < codigoCiudadReferencia.length(); k++) {
                        if (codigoCiudadReferencia.length() < 6) {
                            codigoCiudadReferencia = codigoCiudadReferencia + " "
                        }else {
                            if (codigoCiudadReferencia.length() > 6) {
                                codigoCiudadReferencia = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoCiudadReferencia.length() == 6 || codigoCiudadReferencia.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[79] = codigoCiudadReferencia

                /***Inicio de nuevos cambios en la generación del TXT**/
                /***Campos que son libres y despues de la trama**/
                campos[80] = cli.nombreVendedor
                campos[81] = cli.fechaGestion.toString()
                campos[82] = cli.aceptaCambioProducto
                ExcelUtils.addCells(campos, sheet, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
            }
            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=TramaTxtTarjetasAustro.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return
        }
    }

    def calculaValores(String tipo, long paramCliente){
        long idCliente = paramCliente.toLong()
        Clientes cliente = Clientes.findById(idCliente)
        String valorCalculo = ''
        if(tipo == 'INGRESOS'){
            int sueldoIngresos = cliente.sueldoIngresos.toInteger()
            int comisiones = cliente.comisiones.toInteger()
            int otrosIngresos = cliente.otrosIngresos.toInteger()
            int ingresosConyugue = cliente.ingresosConyugue.toInteger()
            int sumaTotal = sueldoIngresos + comisiones + otrosIngresos + ingresosConyugue
            //println(sumaTotal + ' ' + cliente.id)
            valorCalculo = sumaTotal
        }
        if(tipo == 'EGRESOS'){
            int alimentacion = cliente.alimentacionGestion.toInteger()
            int arriendo = cliente.arriendo.toInteger()
            int cuotaArriendo = cliente.cuotaArriendo.toInteger()
            int gastosBasicos = cliente.gastosBasicos.toInteger()
            int vestimenta = cliente.vestimenta.toInteger()
            int educacion = cliente.educacion.toInteger()
            int salud = cliente.salud.toInteger()
            int cuotaVehiculo = cliente.cuotaVehiculo.toInteger()
            int sumaTotal = alimentacion + arriendo + cuotaArriendo + gastosBasicos + vestimenta + educacion + salud + cuotaVehiculo
            //println(sumaTotal + ' ' + cliente.id)
            valorCalculo = sumaTotal

        }
        return valorCalculo
    }


    def bitacoraTrama2() {
        if (params.fechas) {
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
            def nombresBase = params.list("nombreBase")
            def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], nombresBase: nombresBase, subestados: subestados]
            String sqlPrincipales = "from Clientes where nombreBase in (:nombresBase) and fechaGestion between :fechaInicio and :fechaFin and subestadoGestion in (:subestados)"
            def principalesList = Clientes.executeQuery(sqlPrincipales, condiciones)
            def binTarjetas = Tarjeta.executeQuery("from Tarjeta")
            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("UTF-8")
            WritableWorkbook workbook = Workbook.createWorkbook(file)
            workbook.createSheet("Creditos", 0)
            WritableSheet sheet = workbook.getSheet(0)
            WritableCell valueCell
            String[] headers = ["TIPO ID", "NUMERO ID", "APELLIDO1", "APELLIDO2", "NOMBRE1", "NOMBRE2", "DIRECCION RESIDENCIA",
                                "DIRECCION TRABAJO", "INDICADOR RUTA ENTREGA", "INDICADOR ESTADO CUENTA", "TELEFONO", "TELEFONO2", "CELULAR", "CORREO PERSONAL", "SUCURSAL",
                                "OFICINA", "CODCIUDAD", "COD PARROQUIA", "NACIONALIDAD", "FECHA NACIMIENTO", "GENERO", "ESTADO CIVIL", "NIVEL ESTUDIOS", "PROFESION", "ACTIV ECONOMICA",
                                "ORIGEN INGRESOS", "RANGO INGRESOS MES", "PATRIMONIO", "TIPO VIVIENDA", "VALOR VIVIENDA", "FECHA INICIO RESIDENCIA", "RELACION LABORAL", "FCH INI TRAB ACTUAL",
                                "FCH INI TRAB ANTERIOR", "FCH FIN TRAB ANTERIOR", "CARGAS FAMILIARES", "BIN PLASTICO", "AFINIDAD", "CUPO", "NOMBRE IMPRESO", "VENDEDOR", "TIPO ID REF PERSONAL", "NUMERO ID REF PERSONAL", "APELLIDO1 REF PERSONAL",
                                "APELLIDO2 REF PERSONAL", "NOMBRE1 REF PERSONAL", "NOMBRE2 REF PERSONAL", "DIRECCION REF PERSONAL", "TELEFONO REF PERSONAL", "PARENTESCO REF PERSONAL", "INDICADOR PRINC/ADIC/CONV", "TIPO ID PRINCIPAL", "NUM ID PRINCIPAL",
                                "BIN PLASTICO PRINC", "PARENTESCO", "TIPO ID CONYUGE", "NUMERO ID CONYUGE", "APELLIDO1 CONYUGE", "APELLIDO2 CONYUGE", "NOMBRE1 CONYUGE", "NOMBRE2 CONYUGE", "PAIS CONYUGE", "GENERO CONYUGE", "FECHA DE NACIMIENTO CONYUGE",
                                "ESTADO CIVIL CONYUGE", "PAIS DE NACIMIENTO CONYUGE", "NOMBRE VENDEDOR"]
            ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)
            for (int i = 0; i < principalesList.size(); i++) {
                String[] campos = new String[headers.length]
                Clientes cli = principalesList.get(i)
                int valor = 2
                String binPlatico = ""
                String tipoIdentificacion = cli.tipoIdentificacion
                for (int l = 0; l < valor; l++) {
                    if (tipoIdentificacion == "") {
                        tipoIdentificacion = "   "
                    }
                    for (int k = 0; k < tipoIdentificacion.length(); k++) {
                        if (tipoIdentificacion.length() < 3) {
                            tipoIdentificacion = tipoIdentificacion + " "
                            valueCell.cellFormat = getCellFormat(Colour.WHITE, Alignment.LEFT)
                        } else {
                            if (tipoIdentificacion.length() > 3) {
                                tipoIdentificacion = "EXCEDE DE LONGITUD"
                                valueCell.setCellFormat(getCellFormat(Colour.RED, Alignment.LEFT))
                            }
                        }
                    }
                    if (tipoIdentificacion.length() == 3 || tipoIdentificacion.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[0] = tipoIdentificacion
                String cedulaVerificada = cli.cedulaVerificada
                for (int l = 0; l < valor; l++) {
                    if (cedulaVerificada == "") {
                        cedulaVerificada = "                   "
                    }
                    for (int k = 0; k < cedulaVerificada.length(); k++) {
                        if (cedulaVerificada.length() < 19) {
                            cedulaVerificada = cedulaVerificada + " "
                        }else{
                            if(cedulaVerificada.length() > 19){
                                cedulaVerificada = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cedulaVerificada.length() == 19 || cedulaVerificada.length() > 19) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[1] = cedulaVerificada
                String apellido1 = cli.apellido1
                for (int l = 0; l < valor; l++) {
                    if (apellido1 == "") {
                        apellido1 = "                         "
                    }
                    for (int k = 0; k < apellido1.length(); k++) {
                        if (apellido1.length() < 25) {
                            apellido1 = apellido1 + " "
                        }else{
                            if(apellido1.length() > 25){
                                apellido1 = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido1.length() == 25 || apellido1.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[2] = apellido1
                String apellido2 = cli.apellido2
                for (int l = 0; l < valor; l++) {
                    if (apellido2 == "") {
                        apellido2 = "                         "
                    }
                    for (int k = 0; k < apellido2.length(); k++) {
                        if (apellido2.length() < 25) {
                            apellido2 = apellido2 + " "
                        }else{
                            if(apellido2.length() > 25){
                                apellido1 = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido2.length() == 25 || apellido2.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[3] = apellido2
                String nombreVerificado = cli.nombreVerificado
                for (int l = 0; l < valor; l++) {
                    if (nombreVerificado == "") {
                        nombreVerificado = "                    "
                    }
                    for (int k = 0; k < nombreVerificado.length(); k++) {
                        if (nombreVerificado.length() < 20) {
                            nombreVerificado = nombreVerificado + " "
                        }else{
                            if(nombreVerificado.length() > 20){
                                nombreVerificado = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombreVerificado.length() == 20 || nombreVerificado.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[4] = nombreVerificado
                String nombre2Verificado = cli.nombre2Verificado
                for (int l = 0; l < valor; l++) {
                    if (nombre2Verificado == "") {
                        nombre2Verificado = "                              "
                    }
                    for (int k = 0; k < nombre2Verificado.length(); k++) {
                        if (nombre2Verificado.length() < 30) {
                            nombre2Verificado = nombre2Verificado + " "
                        }else{
                            if(nombre2Verificado.length() > 30){
                                nombre2Verificado = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre2Verificado.length() == 30 || nombre2Verificado.length() > 30) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[5] = nombre2Verificado
                String direccionDomicilio = 'PRRQ ' + cli.callePrincipalNumeracionDomicilio + ' ' + cli.calleTransversalDomicilio + ' ' + cli.referenciaDomicilio
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < direccionDomicilio.length(); k++) {
                        if (direccionDomicilio.length() < 150) {
                            direccionDomicilio = direccionDomicilio + " "
                        }else{
                            if(direccionDomicilio.length() > 150){
                                direccionDomicilio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (direccionDomicilio.length() == 150 || direccionDomicilio.length() > 150) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[6] = direccionDomicilio
                String direccionTrabajo = 'PRRQ ' + cli.callePrincipalNumeracionTrabajo + ' ' + cli.calleTransversalTrabajo + ' ' + cli.referenciaTrabajo
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < direccionTrabajo.length(); k++) {
                        if (direccionTrabajo.length() < 150) {
                            direccionTrabajo = direccionTrabajo + " "
                        }else{
                            if(direccionTrabajo.length() > 150){
                                direccionTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (direccionTrabajo.length() == 150 || direccionTrabajo.length() > 150) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[7] = direccionTrabajo
                if (cli.lugarEntrega.equalsIgnoreCase("DIRECCION DE RESIDENCIA")) {
                    campos[8] = "1"
                } else {
                    campos[8] = "2"
                }
                if (cli.lugarEntregaECuenta.equalsIgnoreCase("DIRECCION DE RESIDENCIA")) {
                    campos[9] = "1"
                } else {
                    campos[9] = "2"
                }
                String telefonoCasa = cli.telefonoCasa
                for (int l = 0; l < valor; l++) {
                    if (telefonoCasa == "") {
                        telefonoCasa = "            "
                    }
                    for (int k = 0; k < telefonoCasa.length(); k++) {
                        if (telefonoCasa.length() < 12) {
                            telefonoCasa = telefonoCasa + " "
                        }else{
                            if(telefonoCasa.length() > 12){
                                telefonoCasa = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (telefonoCasa.length() == 12 || telefonoCasa.length() > 12) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[10] = telefonoCasa
                String telefonoTrabajo = cli.telefonoTrabajo
                for (int l = 0; l < valor; l++) {
                    if (telefonoTrabajo == "") {
                        telefonoTrabajo = "            "
                    }
                    for (int k = 0; k < telefonoTrabajo.length(); k++) {
                        if (telefonoTrabajo.length() < 12) {
                            telefonoTrabajo = telefonoTrabajo + " "
                        }else{
                            if(telefonoTrabajo.length() > 12){
                                telefonoTrabajo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (telefonoTrabajo.length() == 12 || telefonoTrabajo.length() > 12) {
                        break;
                    }
                    valor = valor + 1
                }

                campos[11] = telefonoTrabajo
                String celular = cli.celular
                for (int l = 0; l < valor; l++) {
                    if (celular == "") {
                        celular = "            "
                    }
                    for (int k = 0; k < celular.length(); k++) {
                        if (celular.length() < 12) {
                            celular = celular + " "
                        }else{
                            if(celular.length() > 12){
                                celular = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (celular.length() == 12 || celular.length() == 12) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[12] = celular
                String emailPersonal = cli.emailPersonal
                for (int l = 0; l < valor; l++) {
                    if (emailPersonal == "") {
                        emailPersonal = "                                                                     "
                    }
                    for (int k = 0; k < emailPersonal.length(); k++) {
                        if (emailPersonal.length() < 70) {
                            emailPersonal = emailPersonal + " "
                        }else {
                            if(emailPersonal.length() > 70){
                                emailPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (emailPersonal.length() == 70 || emailPersonal.length() > 70) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[13] = emailPersonal
                campos[14] = cli.sucursal
                campos[15] = cli.oficina
                String codigoCiudad = cli.codigoCiudad
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < codigoCiudad.length(); k++) {
                        if (codigoCiudad.length() < 6) {
                            codigoCiudad = codigoCiudad + " "
                        }else{
                            if(codigoCiudad.length() > 6){
                                codigoCiudad = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoCiudad.length() == 6 || codigoCiudad.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[16] = codigoCiudad
                String codigoParroquia = cli.codigoParroquia
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < codigoParroquia.length(); k++) {
                        if (codigoParroquia.length() < 6) {
                            codigoParroquia = codigoParroquia + " "
                        }else{
                            if(codigoParroquia.length() > 6){
                                codigoParroquia = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (codigoParroquia.length() == 6 || codigoParroquia.length() > 6) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[17] = codigoParroquia
                String nacionalidad = cli.nacionalidad
                for (int l = 0; l < valor; l++) {
                    if (nacionalidad == "") {
                        nacionalidad = "   "
                    }
                    for (int k = 0; k < nacionalidad.length(); k++) {
                        if (nacionalidad.length() < 3) {
                            nacionalidad = nacionalidad + " "
                        }else{
                            if(nacionalidad.length() > 3){
                                nacionalidad = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nacionalidad.length() == 3 || nacionalidad.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[18] = nacionalidad
                campos[19] = cli.fechaNacimiento
                campos[20] = cli.genero
                campos[21] = cli.estadoCivil
                String nivelEstudios = cli.nivelEstudios
                for (int l = 0; l < valor; l++) {
                    if (nivelEstudios == "") {
                        nivelEstudios = "  "
                    }
                    for (int k = 0; k < nivelEstudios.length(); k++) {
                        if (nivelEstudios.length() < 2) {
                            nivelEstudios = nivelEstudios + " "
                        }else{
                            if(nivelEstudios.length() > 2){
                                nivelEstudios = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nivelEstudios.length() == 2 || nivelEstudios.length() > 2) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[22] = nivelEstudios
                String profesion = cli.profesion
                for (int l = 0; l < valor; l++) {
                    if (profesion == "") {
                        profesion = "    "
                    }
                    for (int k = 0; k < profesion.length(); k++) {
                        if (profesion.length() < 4) {
                            profesion = profesion + " "
                        }else{
                            if(profesion.length() > 4){
                                profesion = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (profesion.length() == 4 || profesion.length() > 4) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[23] = profesion
                String actividadEconomica = cli.actividadEconomica
                for (int l = 0; l < valor; l++) {
                    if (actividadEconomica == "") {
                        actividadEconomica = "          "
                    }
                    for (int k = 0; k < actividadEconomica.length(); k++) {
                        if (actividadEconomica.length() < 10) {
                            actividadEconomica = actividadEconomica + " "
                        }else{
                            if(actividadEconomica.length() > 10){
                                actividadEconomica = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (actividadEconomica.length() == 10 || actividadEconomica.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[24] = actividadEconomica
                String origenIngresos = cli.origenIngresos
                for (int l = 0; l < valor; l++) {
                    if (origenIngresos == "") {
                        origenIngresos = "   "
                    }
                    for (int k = 0; k < origenIngresos.length(); k++) {
                        if (origenIngresos.length() < 3) {
                            origenIngresos = origenIngresos + " "
                        }else{
                            if(origenIngresos.length() > 3){
                                origenIngresos = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (origenIngresos.length() == 3 || origenIngresos.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[25] = origenIngresos
                String rangoIngresos = cli.rangoIngresos
                for (int l = 0; l < valor; l++) {
                    if (rangoIngresos == "") {
                        rangoIngresos = "   "
                    }
                    for (int k = 0; k < rangoIngresos.length(); k++) {
                        if (rangoIngresos.length() < 3) {
                            rangoIngresos = rangoIngresos + " "
                        }else{
                            if(rangoIngresos.length() > 3){
                                rangoIngresos = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (rangoIngresos.length() == 3 || rangoIngresos.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[26] = rangoIngresos
                String patrimonio = cli.patrimonio
                /*for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < patrimonio.length(); k++) {
                        if (patrimonio.length() < 10) {
                            patrimonio = patrimonio + " "
                        }
                    }
                    if (patrimonio.length() == 10) {
                        break;
                    }
                    valor = valor + 1
                }*/
                for (int l = 0; l < valor; l++) {
                    if (patrimonio == "") {
                        patrimonio = "          "
                    }
                    for (int k = 0; k < patrimonio.length(); k++) {
                        if (patrimonio.length() < 10) {
                            patrimonio = "0" + patrimonio
                        }else{
                            if(patrimonio.length() > 10){
                                patrimonio = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (patrimonio.length() == 10) {
                        patrimonio = patrimonio + "00"
                    }
                    if (patrimonio.length() == 12 || patrimonio.length() > 12) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[27] = patrimonio
                campos[28] = cli.tipoVivienda//cli.tipoVivienda
                String valorVivienda = cli.valorVivienda
                /*for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < valorVivienda.length(); k++) {
                        if (valorVivienda.length() < 10) {
                            valorVivienda = valorVivienda + " "
                        }
                    }
                    if (valorVivienda.length() == 10) {
                        break;
                    }
                    valor = valor + 1
                }*/
                for (int l = 0; l < valor; l++) {
                    if (valorVivienda == "") {
                        valorVivienda = "          "
                    }
                    for (int k = 0; k < valorVivienda.length(); k++) {
                        if (valorVivienda.length() < 10) {
                            valorVivienda = "0" + valorVivienda
                        }else{
                            if(valorVivienda.length() > 10){
                                valorVivienda = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (valorVivienda.length() == 10) {
                        valorVivienda = valorVivienda + "00"
                    }
                    if (valorVivienda.length() == 12 || valorVivienda.length() > 12) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[29] = valorVivienda
                campos[30] = cli.fechaInicioResidencia//cli.fechaInicioResidencia
                campos[31] = cli.relacionLaboral
                campos[32] = cli.fechaInicioTrabajoActual
                campos[33] = cli.fechaInicioTrabajoAnterior
                campos[34] = cli.fechaFinTrabajoAnterior
                String cargasFamiliares = cli.cargasFamiliares
                for (int l = 0; l < valor; l++) {
                    if (cargasFamiliares == "") {
                        cargasFamiliares = "  "
                    }
                    for (int k = 0; k < cargasFamiliares.length(); k++) {
                        if (cargasFamiliares.length() < 2) {
                            cargasFamiliares = "0" + cargasFamiliares
                        }else{
                            if(cargasFamiliares.length() > 2){
                                cargasFamiliares = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cargasFamiliares.length() == 2 || cargasFamiliares.length() > 2) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[35] = cargasFamiliares
                for (int j = 0; j < binTarjetas.size(); j++) {
                    Tarjeta tar = binTarjetas.get(j)
                    if (cli.productoAceptado == tar.nombre) {
                        campos[36] = tar.bin
                        campos[37] = tar.afinidad
                        binPlatico = tar.bin
                    }
                    if (cli.productoAceptado == "VISA INTERNACIONAL" && cli.genero == tar.genero) {
                        campos[36] = tar.bin
                        campos[37] = tar.afinidad
                        binPlatico = tar.bin
                        break
                    }
                }
                String cupo = cli.cupo
                /*for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < cupo.length(); k++) {
                        if (cupo.length() < 5) {
                            cupo = cupo + " "
                        }
                    }
                    if (cupo.length() == 5) {
                        break;
                    }
                    valor = valor + 1
                }*/
                for (int l = 0; l < valor; l++) {
                    if (cupo == "") {
                        cupo = "     "
                    }
                    for (int k = 0; k < cupo.length(); k++) {
                        if (cupo.length() < 5) {
                            cupo = "0" + cupo
                        }else{
                            if(cupo.length() > 5){
                                cupo = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (cupo.length() == 5) {
                        cupo = cupo + "00"
                    }
                    if (cupo.length() == 7 || cupo.length() > 7) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[38] = cupo
                String nombreImpreso = cli.nombreImpreso
                for (int l = 0; l < valor; l++) {
                    if (nombreImpreso == "") {
                        nombreImpreso = "                   "
                    }
                    for (int k = 0; k < nombreImpreso.length(); k++) {
                        if (nombreImpreso.length() < 19) {
                            nombreImpreso = nombreImpreso + " "
                        } else {
                            if (nombreImpreso.length() > 19) {
                                nombreImpreso = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombreImpreso.length() == 19 || nombreImpreso.length() > 19) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[39] = nombreImpreso// cli.cupo
                /*String vendedor = cli.usuario.id
                for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < vendedor.length(); k++) {
                        if (vendedor.length() < 5) {
                            vendedor = vendedor + " "
                        }
                    }
                    if (vendedor.length() == 5) {
                        break;
                    }
                    valor = valor + 1
                }*/
                campos[40] = "1340 "
                campos[41] = cli.tipoIdentificacionRefPersonal
                String identificacionRefPersonal = cli.identificacionRefPersonal
                for (int l = 0; l < valor; l++) {
                    if (identificacionRefPersonal == "") {
                        identificacionRefPersonal = "          "
                    }
                    for (int k = 0; k < identificacionRefPersonal.length(); k++) {
                        if (identificacionRefPersonal.length() < 10) {
                            identificacionRefPersonal = identificacionRefPersonal + " "
                        } else {
                            if (identificacionRefPersonal.length() > 10) {
                                identificacionRefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (identificacionRefPersonal.length() == 10 || identificacionRefPersonal.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[42] = identificacionRefPersonal
                String apellido1RefPersonal = cli.apellido1RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (apellido1RefPersonal == "") {
                        apellido1RefPersonal = "                    "
                    }
                    for (int k = 0; k < apellido1RefPersonal.length(); k++) {
                        if (apellido1RefPersonal.length() < 20) {
                            apellido1RefPersonal = apellido1RefPersonal + " "
                        }else{
                            if (apellido1RefPersonal.length() > 20) {
                                apellido1RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido1RefPersonal.length() == 20 || apellido1RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[43] = apellido1RefPersonal
                String apellido2RefPersonal = cli.apellido2RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (apellido2RefPersonal == "") {
                        apellido2RefPersonal = "                    "
                    }
                    for (int k = 0; k < apellido2RefPersonal.length(); k++) {
                        if (apellido2RefPersonal.length() < 20) {
                            apellido2RefPersonal = apellido2RefPersonal + " "
                        }else{
                            if (apellido2RefPersonal.length() > 20) {
                                apellido2RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido2RefPersonal.length() == 20 || apellido2RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[44] = apellido2RefPersonal
                String nombre1RefPersonal = cli.nombre1RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (nombre1RefPersonal == "") {
                        nombre1RefPersonal = "                    "
                    }
                    for (int k = 0; k < nombre1RefPersonal.length(); k++) {
                        if (nombre1RefPersonal.length() < 20) {
                            nombre1RefPersonal = nombre1RefPersonal + " "
                        }else{
                            if (nombre1RefPersonal.length() > 20) {
                                nombre1RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre1RefPersonal.length() == 20 || nombre1RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[45] = nombre1RefPersonal
                String nombre2RefPersonal = cli.nombre2RefPersonal
                for (int l = 0; l < valor; l++) {
                    if (nombre2RefPersonal == "") {
                        nombre2RefPersonal = "                    "
                    }
                    for (int k = 0; k < nombre2RefPersonal.length(); k++) {
                        if (nombre2RefPersonal.length() < 20) {
                            nombre2RefPersonal = nombre2RefPersonal + " "
                        }else{
                            if (nombre2RefPersonal.length() > 20) {
                                nombre2RefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre2RefPersonal.length() == 20 || nombre2RefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[46] = nombre2RefPersonal
                String direccionReferencia = cli.callePrincipalRefPersonal + ' ' + cli.calleSecundariaRefPersonal + ' ' + cli.referenciaRefPersonal
                for (int l = 0; l < valor; l++) {
                    for (int k = 0; k < direccionReferencia.length(); k++) {
                        if (direccionReferencia.length() < 150) {
                            direccionReferencia = direccionReferencia + " "
                        }else{
                            if (direccionReferencia.length() > 150) {
                                direccionReferencia = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (direccionReferencia.length() == 150 || direccionReferencia.length() > 150) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[47] = direccionReferencia
                String telefonoRefPersonal = cli.telefonoRefPersonal
                for (int l = 0; l < valor; l++) {
                    if (telefonoRefPersonal == "") {
                        telefonoRefPersonal = "                    "
                    }
                    for (int k = 0; k < telefonoRefPersonal.length(); k++) {
                        if (telefonoRefPersonal.length() < 20) {
                            telefonoRefPersonal = telefonoRefPersonal + " "
                        }else{
                            if (telefonoRefPersonal.length() > 20) {
                                telefonoRefPersonal = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (telefonoRefPersonal.length() == 20 || telefonoRefPersonal.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[48] = telefonoRefPersonal
                campos[49] = cli.parentescoRefPersonal
                campos[50] = "P"

                campos[51] = " "
                /*String cedulaVerificada2 = cli.cedulaVerificada
                for(int l = 0; l < valor ; l++) {
                    for (int k = 0; k < cedulaVerificada2.length(); k++) {
                        if (cedulaVerificada2.length() < 10) {
                            cedulaVerificada2 = cedulaVerificada2 + " "
                        }
                    }
                    if (cedulaVerificada2.length() == 10) {
                        break;
                    }
                    valor = valor + 1
                }*/
                campos[52] = "          "
                campos[53] = "      "
                campos[54] = "  "
                String tipoIdentificacionConyuge = cli.tipoIdentificacionConyuge
                for (int l = 0; l < valor; l++) {
                    if (tipoIdentificacionConyuge == "") {
                        tipoIdentificacionConyuge = "   "
                    }
                    for (int k = 0; k < tipoIdentificacionConyuge.length(); k++) {
                        if (tipoIdentificacionConyuge.length() < 3) {
                            tipoIdentificacionConyuge = tipoIdentificacionConyuge + " "
                        }else{
                            if (tipoIdentificacionConyuge.length() > 3) {
                                tipoIdentificacionConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (tipoIdentificacionConyuge.length() == 3 || tipoIdentificacionConyuge.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[55] = tipoIdentificacionConyuge
                String identificacionConyuge = cli.identificacionConyuge
                for (int l = 0; l < valor; l++) {
                    if (identificacionConyuge == "") {
                        identificacionConyuge = "                   "
                    }
                    for (int k = 0; k < identificacionConyuge.length(); k++) {
                        if (identificacionConyuge.length() < 19) {
                            identificacionConyuge = identificacionConyuge + " "
                        }else{
                            if (identificacionConyuge.length() > 19) {
                                identificacionConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (identificacionConyuge.length() == 19 || identificacionConyuge.length() > 19) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[56] = identificacionConyuge
                String apellido1Conyuge = cli.apellido1Conyuge
                for (int l = 0; l < valor; l++) {
                    if (apellido1Conyuge == "") {
                        apellido1Conyuge = "                         "
                    }
                    for (int k = 0; k < apellido1Conyuge.length(); k++) {
                        if (apellido1Conyuge.length() < 25) {
                            apellido1Conyuge = apellido1Conyuge + " "
                        }else{
                            if (apellido1Conyuge.length() > 25) {
                                apellido1Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido1Conyuge.length() == 25 || apellido1Conyuge.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[57] = apellido1Conyuge
                String apellido2Conyuge = cli.apellido2Conyuge
                for (int l = 0; l < valor; l++) {
                    if (apellido2Conyuge == "") {
                        apellido2Conyuge = "                         "
                    }
                    for (int k = 0; k < apellido2Conyuge.length(); k++) {
                        if (apellido2Conyuge.length() < 25) {
                            apellido2Conyuge = apellido2Conyuge + " "
                        }else{
                            if (apellido2Conyuge.length() > 25) {
                                apellido2Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (apellido2Conyuge.length() == 25 || apellido2Conyuge.length() > 25) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[58] = apellido2Conyuge
                String nombre1Conyuge = cli.nombre1Conyuge
                for (int l = 0; l < valor; l++) {
                    if (nombre1Conyuge == "") {
                        nombre1Conyuge = "                    "
                    }
                    for (int k = 0; k < nombre1Conyuge.length(); k++) {
                        if (nombre1Conyuge.length() < 20) {
                            nombre1Conyuge = nombre1Conyuge + " "
                        }else{
                            if (nombre1Conyuge.length() > 20) {
                                nombre1Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre1Conyuge.length() == 20 || nombre1Conyuge.length() > 20) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[59] = nombre1Conyuge
                String nombre2Conyuge = cli.nombre2Conyuge
                for (int l = 0; l < valor; l++) {
                    if (nombre2Conyuge == "") {
                        nombre2Conyuge = "                              "
                    }
                    for (int k = 0; k < nombre2Conyuge.length(); k++) {
                        if (nombre2Conyuge.length() < 30) {
                            nombre2Conyuge = nombre2Conyuge + " "
                        }else{
                            if (nombre2Conyuge.length() > 30) {
                                nombre2Conyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (nombre2Conyuge.length() == 30 || nombre2Conyuge.length() > 30) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[60] = nombre2Conyuge
                String paisConyuge = cli.paisConyuge
                for (int l = 0; l < valor; l++) {
                    if (paisConyuge == "") {
                        paisConyuge = "   "
                    }
                    for (int k = 0; k < paisConyuge.length(); k++) {
                        if (paisConyuge.length() < 3) {
                            paisConyuge = paisConyuge + " "
                        }else{
                            if (paisConyuge.length() > 3) {
                                paisConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (paisConyuge.length() == 3 || paisConyuge.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[61] = paisConyuge
                String generoConyuge = cli.generoConyuge
                for (int l = 0; l < valor; l++) {
                    if (generoConyuge == "") {
                        generoConyuge = " "
                    }
                    for (int k = 0; k < generoConyuge.length(); k++) {
                        if (generoConyuge.length() < 1) {
                            generoConyuge = generoConyuge + " "
                        }else{
                            if (paisConyuge.length() > 1) {
                                paisConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (generoConyuge.length() == 1 || generoConyuge.length() > 1) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[62] = generoConyuge
                String fechaNacimientoConyuge = cli.fechaNacimientoConyuge
                for (int l = 0; l < valor; l++) {
                    if (fechaNacimientoConyuge == "") {
                        fechaNacimientoConyuge = "          "
                    }
                    for (int k = 0; k < fechaNacimientoConyuge.length(); k++) {
                        if (fechaNacimientoConyuge.length() < 10) {
                            fechaNacimientoConyuge = fechaNacimientoConyuge + " "
                        }else{
                            if (fechaNacimientoConyuge.length() > 10) {
                                fechaNacimientoConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (fechaNacimientoConyuge.length() == 10 || fechaNacimientoConyuge.length() > 10) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[63] = fechaNacimientoConyuge
                String estadoCivilConyuge = cli.estadoCivilConyuge
                for (int l = 0; l < valor; l++) {
                    if (estadoCivilConyuge == "") {
                        estadoCivilConyuge = " "
                    }
                    for (int k = 0; k < estadoCivilConyuge.length(); k++) {
                        if (estadoCivilConyuge.length() < 1) {
                            estadoCivilConyuge = estadoCivilConyuge + " "
                        }else{
                            if (estadoCivilConyuge.length() > 1) {
                                estadoCivilConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (estadoCivilConyuge.length() == 1 || estadoCivilConyuge.length() > 1) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[64] = estadoCivilConyuge
                String paisNacimientoConyuge = cli.paisNacimientoConyuge
                for (int l = 0; l < valor; l++) {
                    if (paisNacimientoConyuge == "") {
                        paisNacimientoConyuge = "   "
                    }
                    for (int k = 0; k < paisNacimientoConyuge.length(); k++) {
                        if (paisNacimientoConyuge.length() < 3) {
                            paisNacimientoConyuge = paisNacimientoConyuge + " "
                        }else {
                            if (paisNacimientoConyuge.length() > 3) {
                                paisNacimientoConyuge = "EXCEDE DE LONGITUD"
                            }
                        }
                    }
                    if (paisNacimientoConyuge.length() == 3 || paisNacimientoConyuge.length() > 3) {
                        break;
                    }
                    valor = valor + 1
                }
                campos[65] = paisNacimientoConyuge
                campos[66] = cli.nombreVendedor
                ExcelUtils.addCells(campos, sheet, i + 1, valueCell, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
            }
            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=TramaTxtTarjetasAustro.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return
        }
            }

    private WritableCellFormat getCellFormat(Colour colour, Alignment alignment){

        WritableCellFormat cellFormat = new WritableCellFormat()
        cellFormat.setBackground(colour)
        cellFormat.setAlignment(alignment)
        return cellFormat
    }

    def indicadoresGestion(){
        boolean visibilizar = false
        if(params.fechas){
            visibilizar = true
            DecimalFormat df = new DecimalFormat("#.00")
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            def nombresBase = params.list("nombreBase")
            Date fechaInicio = fechas[0]
            Date fechaFin = fechas[1]

            int Exitosas = 0
            int Contactados = 0
            int NoDesea = 0
            int exitosasPdp = 0
            int exitosasCredito = 0
            double Efectividad = 0
            double EfectividadPdp = 0
            double EfectividadCredito = 0
            double NoDeseaPorcentaje = 0

            int totalGestionados = 0
            int totalContactados = 0
            int totalNoContactados = 0
            int totalExitosos = 0
            int totalCruzadasCU2 = 0
            int totalCruzadasCU3 = 0
            int totalNoDeseaCU5 = 0
            int totalTarjetasCreadas = 0
            int totalDiferenciaTarjetas = 0
            String totalPorcentajeContactabilidad = ""
            String totalPorcentajeEfectividad = ""
            String totalPorcentajeEfectividadPdp = ""
            String totalPorcentajeEfectividadCredito = ""
            String totalPorcentajeReales = ""
            def subestadosEfectivos = Subestado.executeQuery("from Subestado where id in (1)")
            def subestadosCruzadas = Subestado.executeQuery("from Subestado where id in (2)")
            def subestadosCU3 = Subestado.executeQuery("from Subestado where id in (3)")
            def subestadosCU5 = Subestado.executeQuery("from Subestado where id in (5)")
            def ventasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where subestadoGestion = :subestadosEfectivos and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin, subestadosEfectivos: subestadosEfectivos])
            def cruzadasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where (subSubEstado = 'PDP' or subSubEstado = 'CREDITO+PDP') and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor , substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
            def CU3PorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where (subSubEstado = 'CREDITO' or subSubEstado = 'TDC+CREDITO' or subSubEstado = 'CREDITO+PDP') and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
            def noDeseaPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where subestadoGestion in (:subestadosCU5) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor , substring(fechaGestion,1,10) order by nombreVendedor", [subestadosCU5: subestadosCU5, fechaInicio: fechaInicio, fechaFin: fechaFin])
            def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
            def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
            def noContactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and estadoGestion = 'NO CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])

            String[][] tablaResult = new String[gestionadosAgente.size()][12]
            //Lleno la matriz de resultados con los resultados de las onsultas anteriores
            for(int i = 0; i < tablaResult.size(); i++){
                tablaResult[i][0] = gestionadosAgente[i][0]
                tablaResult[i][1] = gestionadosAgente[i][1]
                tablaResult[i][10] = gestionadosAgente[i][2]

                //Lleno información de contactados y % de contactabilidad
                for(int j = 0; j < contactadosAgente.size(); j++){
                    if(contactadosAgente[j][0] == gestionadosAgente[i][0] && contactadosAgente[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][2] = contactadosAgente[j][1]
                        Contactados = tablaResult[i][2].toInteger()
                        break
                    }
                }
                if(tablaResult[i][2] == null){
                    Contactados = 0
                }
                //Lleno información de no contactados
                /*for(int j = 0; j < noContactadosAgente.size(); j++){
                    if(noContactadosAgente[j][0] == gestionadosAgente[i][0] && noContactadosAgente[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][3] = noContactadosAgente[j][1]
                        break
                    }
                }*/
                //LLeno la información de las ventas
                for(int j = 0; j < ventasPorUsuario.size(); j++){
                    if(ventasPorUsuario[j][0] == gestionadosAgente[i][0] && ventasPorUsuario[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][4] = ventasPorUsuario[j][1]
                        Exitosas = tablaResult[i][4].toInteger()
                        break
                    }
                }
                if(tablaResult[i][4] == null){
                    Exitosas = 0
                }
                //LLeno la información de las ventas cruzadas
                for(int j = 0; j < cruzadasPorUsuario.size(); j++){
                    if(cruzadasPorUsuario[j][0] == gestionadosAgente[i][0] && cruzadasPorUsuario[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][5] = cruzadasPorUsuario[j][1]
                        exitosasPdp = tablaResult[i][5].toInteger()
                        break
                    }
                }
                if(tablaResult[i][5] == null){
                    exitosasPdp = 0
                }

                //LLeno la información de las ventas CU3
                for(int j = 0; j < CU3PorUsuario.size(); j++){
                    if(CU3PorUsuario[j][0] == gestionadosAgente[i][0] && CU3PorUsuario[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][6] = CU3PorUsuario[j][1]
                        exitosasCredito = tablaResult[i][6].toInteger()
                        break
                    }
                }
                if(tablaResult[i][6] == null){
                    exitosasCredito = 0
                }

                //LLeno la información de los No Desea
                for(int j = 0; j < noDeseaPorUsuario.size(); j++){
                    if(noDeseaPorUsuario[j][0] == gestionadosAgente[i][0] && noDeseaPorUsuario[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][7] = noDeseaPorUsuario[j][1]
                        NoDesea = tablaResult[i][7].toInteger()
                        break
                    }
                }
                if(tablaResult[i][7] == null){
                    NoDesea = 0
                }

                //Pra calcular los procentajes de efectividad y no desea
                if(Exitosas != 0 && Exitosas != null){
                    Efectividad = (Exitosas / Contactados) * 100
                    tablaResult[i][8] = df.format(Double.parseDouble(Efectividad.toString())).replace(",", ".")
                    //tablaResult[i][8] = 0
                }else{
                    tablaResult[i][8] = 0
                }

                if(exitosasPdp != 0 && exitosasPdp != null){
                    EfectividadPdp = (exitosasPdp / Contactados) * 100
                    tablaResult[i][3] = df.format(Double.parseDouble(EfectividadPdp.toString())).replace(",", ".")
                }else{
                    tablaResult[i][3] = 0
                }

                if(NoDesea != 0 && NoDesea != null){
                    NoDeseaPorcentaje = (NoDesea / Contactados) * 100
                    tablaResult[i][9] = df.format(Double.parseDouble(NoDeseaPorcentaje.toString())).replace(",", ".")
                    //tablaResult[i][9] = 0
                }else{
                    tablaResult[i][9] = 0
                }

                if(exitosasCredito != 0 && exitosasCredito != null){
                    EfectividadCredito = (exitosasCredito / Contactados) * 100
                    tablaResult[i][11] = df.format(Double.parseDouble(EfectividadCredito.toString())).replace(",", ".")
                    //tablaResult[i][9] = 0
                }else{
                    tablaResult[i][11] = 0
                }
                //println(Contactados + " " + Exitosas + " " + NoDesea)
            }

            //Recorro la matriz de resultados para obtener los totales
            for(int i = 0; i < tablaResult.size(); i++){
                totalGestionados = totalGestionados + tablaResult[i][1].toInteger()
                if(tablaResult[i][2] != null)
                    totalContactados = totalContactados + tablaResult[i][2].toInteger()
                /*if(tablaResult[i][3] != null)
                    totalNoContactados = totalNoContactados + tablaResult[i][3].toInteger()*/
                if(tablaResult[i][4] != null)
                    totalExitosos = totalExitosos + tablaResult[i][4].toInteger()
                if(tablaResult[i][5] != null)
                    totalCruzadasCU2 = totalCruzadasCU2 + tablaResult[i][5].toInteger()
                if(tablaResult[i][6] != null)
                    totalCruzadasCU3 = totalCruzadasCU3 + tablaResult[i][6].toInteger()
                if(tablaResult[i][7] != null)
                    totalNoDeseaCU5 = totalNoDeseaCU5 + tablaResult[i][7].toInteger()
            }

            if(totalGestionados == 0){
                totalPorcentajeContactabilidad = "0.00"
                totalPorcentajeEfectividad = "0.00"
                totalPorcentajeEfectividadPdp = "0.00"
                totalPorcentajeEfectividadCredito = "0.00"
            }else{
                totalPorcentajeContactabilidad = df.format((totalExitosos/totalContactados)*100)
                totalPorcentajeEfectividad = df.format((totalNoDeseaCU5/totalContactados)*100)
                totalPorcentajeEfectividadPdp = df.format((totalCruzadasCU2/totalContactados)*100)
                totalPorcentajeEfectividadCredito = df.format((totalCruzadasCU3/totalContactados)*100)
            }
            [visibilizar: visibilizar, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados,
             totalNoContactados: totalNoContactados, totalExitosos:totalExitosos, totalCruzadasCU2: totalCruzadasCU2, totalCruzadasCU3: totalCruzadasCU3,
             totalNoDeseaCU5: totalNoDeseaCU5, totalPorcentajeContactabilidad: totalPorcentajeContactabilidad, totalPorcentajeEfectividad: totalPorcentajeEfectividad,
             totalPorcentajeEfectividadPdp: totalPorcentajeEfectividadPdp, totalPorcentajeEfectividadCredito: totalPorcentajeEfectividadCredito]
        }
    }

    def tiemposBreak() {
        if (params.fechas) {
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
            def nombresBase = params.list("nombreBase")
            def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1]]
            String sqlPrincipales = "from BreakTime where dateBreak between :fechaInicio and :fechaFin"
            def principalesList = BreakTime.executeQuery(sqlPrincipales, condiciones)
            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("UTF-8")
            WritableWorkbook workbook = Workbook.createWorkbook(file)
            workbook.createSheet("Clientes Efectivos", 0)
            WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
            cellFont.setColour(Colour.WHITE);
            WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
            WritableSheet sheetPrincipales = workbook.getSheet(0)
            String[] headersPrincipales = ["FECHA/HORA", "TIEMPO", "OPCION", "NOMBRE USUARIO"]
            ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GREEN, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.THIN)
            for (int i = 0; i < principalesList.size(); i++) {
                String[] campos = new String[headersPrincipales.length]
                BreakTime cli = principalesList.get(i)
                campos[0] = cli.dateBreak.toString()
                campos[1] = cli.timeBreak.toString()
                campos[2] = cli.typeBreak
                campos[3] = cli.user.nombre
                ExcelUtils.addCells(campos, sheetPrincipales, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
            }
            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=tiemposBreakAsesores.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return
        }
    }

    def loginAgentes(){
        boolean visibilizar = false
        if(params.fechas) {
            visibilizar = true
            Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
            def nombresBase = params.list("nombreBase")
            Date fechaInicio = fechas[0]
            Date fechaFin = fechas[1]
            println(fechaInicio)
            def consulta = Clientes.executeQuery("select substring(fechaGestion,1,10), nombreVendedor, substring(min(fechaGestion),11,12), substring(max(fechaGestion),11,12) from Clientes where fechaGestion between :fechaInicio and :fechaFin group by substring(fechaGestion,1,10), nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
            String[][] tablaResult = new String[consulta.size()][5]
            //Lleno la matriz de resultados con los resultados de las onsultas anteriores
            for(int i = 0; i < tablaResult.size(); i++) {
                tablaResult[i][0] = consulta[i][0]
                tablaResult[i][1] = consulta[i][1]
                tablaResult[i][2] = consulta[i][2]
                tablaResult[i][3] = consulta[i][3]
            }
            [visibilizar: visibilizar, tablaResult: tablaResult]
        }
    }

    def bitacoraGestion(){
        if(params.nombreBase){
            //Obtenemos los datos
            ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1,2,3)")
            def nombresBase = params.list("nombreBase")
            def condiciones = [nombresBase: nombresBase]
            String sqlPrincipales = "from Clientes where codigoCampania in (:nombresBase) order by subestadoGestion"
            def principalesList = Clientes.executeQuery(sqlPrincipales, condiciones)

            //Empezamos a crear y llenar el Excel
            def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
            File file = new File(webrootDir, "temporal.xls")
            WorkbookSettings workbookSettings = new WorkbookSettings()
            workbookSettings.setLocale(new Locale("es", "ES"))
            workbookSettings.setEncoding("Cp1252")
            workbookSettings.setUseTemporaryFileDuringWrite(true)
            WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
            workbook.createSheet("GESTION", 0)
            WritableFont cellFont = new WritableFont(WritableFont.TAHOMA, 10)
            WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
            WritableSheet sheetPrincipales = workbook.getSheet(0)
            String[] headersPrincipales = ['CODIGOCAMPANA',	'AGENTE',	'USUARIO ID',	'TIPO_GESTION',	'IDENTIFICACION',	'NOMBRECLIENTE',
                                           'FECHAGESTION',	'ESTATUS',	'ULTIMOTELEFONOCONTACTO',	'OBSERVACIONMOTIVONODESEA',	'NUMEROINTENTOS',
                                           'ESTADO_GESTION',	'NOMBRE BASE',	'TELEFONO1',	'TELEFONO2',	'TELEFONO3',	'TELEFONO4',
                                           'TELEFONO5',	'ADICIONALES',	'GAMA',	'REGIONAL',	'RANGO EDAD',	'RANGO CUPO',	'CADUCIDAD',	'ID SISTEMA', 'ALIMENTACION']
            ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
            for (int i = 0; i < principalesList.size(); i++){
                String[] campos = new String[headersPrincipales.length]
                Clientes princ = principalesList.get(i)
                campos[0] = princ.codigoCampania
                campos[1] = princ.nombreVendedor
                if (princ.usuario == null){
                    campos[2] = princ.usuario
                }else{
                    campos[2] = princ.usuario.id
                }
                campos[3] = princ.tipoGestion
                campos[4] = princ.identificacion
                campos[5] = princ.nombre
                if (princ.fechaGestion == null){
                    campos[6] = princ.fechaGestion
                }else{
                    campos[6] = princ.fechaGestion.toString().substring(0,10).replace("/","-")
                }
                if (princ.subestadoGestion == null){
                    campos[7] = princ.subestadoGestion
                }else{
                    campos[7] = princ.subestadoGestion.nombre
                }
                campos[8] = princ.telefonoContactado
                campos[9] = princ.subSubEstado
                campos[10] = princ.intentos
                campos[11] = princ.estadoGestion
                campos[12] = princ.nombreBase
                campos[13] = princ.telefono1
                campos[14] = princ.telefono2
                campos[15] = princ.telefono3
                campos[16] = princ.telefono4
                campos[17] = princ.telefono5
                campos[18] = ''
                campos[19] = princ.gama
                campos[20] = princ.regional
                campos[21] = princ.rangoEdad
                campos[22] = princ.rangoCupo
                campos[23] = princ.fechaCaducidad
                campos[24] = princ.id
                campos[25] = princ.alimentacion
                ExcelUtils.addCells(campos, sheetPrincipales, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
            }

            workbook.write()
            workbook.close()
            response.setHeader("Content-disposition", "filename=bitacoraGestion.xls")
            response.setContentType("application/octet-stream")
            response.outputStream << file.getBytes()
            return
        }
    }

}
