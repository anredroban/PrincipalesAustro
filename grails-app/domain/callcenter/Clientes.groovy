package callcenter
import java.util.Date;
import com.pw.security.*

class Clientes {

	//Campos de la base
	String tipoProducto
	String cupo
	String identificacion
	String nombre
	String fechaNacimiento
	String genero
	String estadoCivil
	String profesion
	String email
	String lugarTrabajo
	String direccionTrabajo
	String direccionDomicilio
	String provincia
	String ciudadCanton
	String celular
	String telefono1
	String telefono2
	String telefono3
	String telefono4
	String telefono5
	String telefono6
	String telefono7
	String telefono8
	String telefono9
	String telefono10
	String telefono11
	String telefono12
	String telefono13
	String telefono14
	String telefono15
	String telefono16
	String telefono17

	//Datos que recoge el asesor
	String cedulaVerificada
	String nombreVerificado
	String provinciaDomicilio
	String ciudadDomicilio
	String sectorDomicilio
	String callePrincipalNumeracionDomicilio
	String calleTransversalDomicilio
	String referenciaDomicilio
	String tipoPredioDomicilio
	String provinciaTrabajo
	String ciudadTrabajo
	String sectorTrabajo
	String callePrincipalNumeracionTrabajo
	String calleTransversalTrabajo
	String referenciaTrabajo
	String tipoPredioTrabajo
	String telefonoCasa
	String telefonoTrabajo

	String lugarEntrega
	String horarioEntrega
	String sueldoIngresos
	String gastosBasicos

	Date fechaEntrega //10 días laborables luego de la venta

	//Nuevos campos agregados para TXT 20190919
	String tipoIdentificacion
	String apellido1
	String apellido2
	String nombre2Verificado
	String lugarEntregaECuenta
	String emailPersonal

	String sucursal
	String oficina
	String nacionalidad
	String nivelEstudios
	String actividadEconomica
	String origenIngresos
	String rangoIngresos
	String patrimonio
	String tipoVivienda
	String valorVivienda
	String fechaInicioResidencia
	String relacionLaboral
	String fechaInicioTrabajoActual
	String fechaInicioTrabajoAnterior
	String fechaFinTrabajoAnterior
	String cargasFamiliares
	String binPlastico
	String afinidad
	String nombreImpreso
	String tipoIdentificacionRefPersonal
	String identificacionRefPersonal
	String apellido1RefPersonal
	String apellido2RefPersonal
	String nombre1RefPersonal
	String nombre2RefPersonal
	String callePrincipalRefPersonal
	String calleSecundariaRefPersonal
	String referenciaRefPersonal
	String telefonoRefPersonal
	String parentescoRefPersonal
	String tipoIdentificacionConyuge
	String identificacionConyuge
	String apellido1Conyuge
	String apellido2Conyuge
	String nombre1Conyuge
	String nombre2Conyuge
	String paisConyuge
	String generoConyuge
	String fechaNacimientoConyuge
	String estadoCivilConyuge
	String paisNacimientoConyuge

	String nombreEmpresa
	String comisiones
	String otrosIngresos
	String ingresosConyugue
	String alimentacionGestion
	String arriendo
	String cuotaArriendo
	String vestimenta
	String educacion
	String salud
	String cuotaVehiculo
	String totalEgresos
	String codigoCiudad
	String codigoParroquia

	String producto1V
	String producto2M
	String productoAceptado
	String aceptaCambioProducto
	String telefonoContactado
	String tipoMarca



	//Campos que SIEMPRE van en la gestión
	Date fechaGestion
	int intentos
	String estadoGestion
	Subestado subestadoGestion
	String subSubEstado
	Usuario usuario
	String nombreBase
	String nombreVendedor
	Date fechaRellamada
	String observaciones
	String registroExitoso
	String motivoSubSubEstado

	String codigoAsignacion
	String agendamientoAsesor

	boolean isActive //Será falso cuando se deshabilite una base

	/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
	String gama
	String regional
	String rangoEdad
	String rangoCupo
	String segmentacionAd1
	String segmentacionAd2
	String segmentacionAd3
	String segmentacionAd4
	String segmentacionAd5
	String easyCodeRegional
	String easyCodeEdad
	String easyCodeCupo
	String easyCodeEdadCupo
	String easyCodeGamaEdad
	String easyCodeAd1
	String easyCodeAd2
	String easyCodeAd3
	String easyCodeAd4
	String easyCodeAd5
	String prioridadCampania
	String fechaCaducidad
	String deudaProtegida
	String metaContactabilidad
	String metaEfectividadTelefonica
	String metaEfectividadReal
	String tipoGestion
	String plataforma
	String alimentacion
	String codigoCampania
	String estadoTelefonoContactado
	String creadas_nocreadas
	String imputable
	String detalle_imputable
	String fecha_envio_creacion
	//actualizacion Iess
	String ingresoIess
	String porcentajeIess
	String statusCourier
	String cicloCourier
	String barrioDomicilio
	String barrioTrabajo
	String codigoCiudadTrabajo
	String codigoParroquiaTrabajo
	String codigoProfesión
	String codigoCiudadReferencia
	String valorActivo
	String valorPasivo
	String montoIngresoMensualTotal
	String montoEgresoMensualTotal



	static constraints = {
		tipoProducto nullable: true
		cupo nullable: true
		identificacion nullable: true
		nombre nullable: true
		fechaNacimiento nullable: true
		genero nullable: true
		estadoCivil nullable: true
		profesion nullable: true
		email nullable: true
		lugarTrabajo nullable: true
		direccionTrabajo nullable: true
		direccionDomicilio nullable: true
		provincia nullable: true
		ciudadCanton nullable: true
		telefono1 nullable: true
		telefono2 nullable: true
		telefono3 nullable: true
		telefono4 nullable: true
		telefono5 nullable: true
		telefono6 nullable: true
		telefono7 nullable: true
		telefono8 nullable: true
		telefono9 nullable: true
		telefono10 nullable: true
		telefono11 nullable: true
		telefono12 nullable: true
		telefono13 nullable: true
		telefono14 nullable: true
		telefono15 nullable: true
		telefono16 nullable: true
		telefono17 nullable: true

		cedulaVerificada nullable: true
		nombreVerificado nullable: true
		provinciaDomicilio nullable: true
		ciudadDomicilio nullable: true
		sectorDomicilio nullable: true
		callePrincipalNumeracionDomicilio nullable: true
		calleTransversalDomicilio nullable: true
		referenciaDomicilio nullable: true
		tipoPredioDomicilio nullable: true
		provinciaTrabajo nullable: true
		ciudadTrabajo nullable: true
		sectorTrabajo nullable: true
		callePrincipalNumeracionTrabajo nullable: true
		calleTransversalTrabajo nullable: true
		referenciaTrabajo nullable: true
		tipoPredioTrabajo nullable: true
		telefonoCasa nullable: true
		telefonoTrabajo nullable: true
		celular nullable: true
		lugarEntrega nullable: true
		horarioEntrega nullable: true
		sueldoIngresos nullable: true
		gastosBasicos nullable: true
		fechaEntrega nullable: true
		tipoMarca nullable: true

		tipoIdentificacion nullable: true
		apellido1 nullable: true
		apellido2 nullable: true
		nombre2Verificado nullable: true
		lugarEntregaECuenta nullable: true
		emailPersonal nullable: true

		sucursal nullable: true
		oficina nullable: true
		nacionalidad nullable: true
		nivelEstudios nullable: true
		actividadEconomica nullable: true
		origenIngresos nullable: true
		rangoIngresos nullable: true
		patrimonio nullable: true
		tipoVivienda nullable: true
		valorVivienda nullable: true
		fechaInicioResidencia nullable: true
		relacionLaboral nullable: true
		fechaInicioTrabajoActual nullable: true
		fechaInicioTrabajoAnterior nullable: true
		fechaFinTrabajoAnterior nullable: true
		cargasFamiliares nullable: true
		binPlastico nullable: true
		afinidad nullable: true
		nombreImpreso nullable: true
		tipoIdentificacionRefPersonal nullable: true
		identificacionRefPersonal nullable: true
		apellido1RefPersonal nullable: true
		apellido2RefPersonal nullable: true
		nombre1RefPersonal nullable: true
		nombre2RefPersonal nullable: true
		callePrincipalRefPersonal nullable: true
		calleSecundariaRefPersonal nullable: true
		referenciaRefPersonal nullable: true
		telefonoRefPersonal nullable: true
		parentescoRefPersonal nullable: true
		tipoIdentificacionConyuge nullable: true
		identificacionConyuge nullable: true
		apellido1Conyuge nullable: true
		apellido2Conyuge nullable: true
		nombre1Conyuge nullable: true
		nombre2Conyuge nullable: true
		paisConyuge nullable: true
		generoConyuge nullable: true
		fechaNacimientoConyuge nullable: true
		estadoCivilConyuge nullable: true
		paisNacimientoConyuge nullable: true

		//Campos que SIEMPRE van en la gestión
		fechaGestion nullable: true
		intentos nullable: true
		estadoGestion nullable: true
		subestadoGestion nullable: true
		subSubEstado nullable: true
		usuario nullable: true
		nombreBase nullable: true
		nombreVendedor nullable: true
		fechaRellamada nullable: true
		observaciones nullable: true

		nombreEmpresa nullable: true
		comisiones nullable: true
		otrosIngresos nullable: true
		ingresosConyugue nullable: true
		alimentacionGestion nullable: true
		alimentacion nullable: true
		arriendo nullable: true
		cuotaArriendo nullable: true
		vestimenta nullable: true
		educacion nullable: true
		salud nullable: true
		cuotaVehiculo nullable: true
		totalEgresos nullable: true
		codigoCiudad nullable: true
		codigoParroquia nullable: true

		producto1V nullable: true
		producto2M nullable: true
		productoAceptado nullable: true
		aceptaCambioProducto nullable: true
		telefonoContactado nullable: true
		registroExitoso nullable: true
		motivoSubSubEstado nullable: true
		agendamientoAsesor nullable: true

		/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
		gama nullable: true
		regional nullable: true
		rangoEdad nullable: true
		rangoCupo nullable: true
		segmentacionAd1 nullable: true
		segmentacionAd2 nullable: true
		segmentacionAd3 nullable: true
		segmentacionAd4 nullable: true
		segmentacionAd5 nullable: true
		easyCodeRegional nullable: true
		easyCodeEdad nullable: true
		easyCodeCupo nullable: true
		easyCodeEdadCupo nullable: true
		easyCodeGamaEdad nullable: true
		easyCodeAd1 nullable: true
		easyCodeAd2 nullable: true
		easyCodeAd3 nullable: true
		easyCodeAd4 nullable: true
		easyCodeAd5 nullable: true
		prioridadCampania nullable: true
		fechaCaducidad nullable: true
		deudaProtegida nullable: true
		metaContactabilidad nullable: true
		metaEfectividadTelefonica nullable: true
		metaEfectividadReal nullable: true
		tipoGestion nullable: true
		plataforma nullable: true
		codigoCampania nullable: true
		estadoTelefonoContactado nullable: true
		creadas_nocreadas nullable: true
		imputable nullable: true
		detalle_imputable nullable: true
		fecha_envio_creacion nullable: true
		ingresoIess nullable: true
		porcentajeIess nullable: true
		statusCourier nullable: true
		cicloCourier nullable: true
		barrioDomicilio nullable: true
		barrioTrabajo nullable: true
		codigoCiudadTrabajo nullable: true
		codigoParroquiaTrabajo nullable: true
		codigoProfesión nullable: true
		codigoCiudadReferencia nullable: true
		valorActivo nullable: true
		valorPasivo nullable: true
		montoIngresoMensualTotal nullable: true
		montoEgresoMensualTotal nullable: true

	}
	
	static mapping = {
		version false
		observaciones type: 'text'
	}

	static String[] getFields(){
		String[] fields = [
				"cupo",
				"producto1V",
				"producto2M",
				"identificacion",
				"nombre",
				"fechaNacimiento",
				"genero",
				"estadoCivil",
				"profesion",
				"email",
				"lugarTrabajo",
				"direccionTrabajo",
				"direccionDomicilio",
				"provincia",
				"ciudadCanton",
				"telefono1",
				"telefono2",
				"telefono3",
				"telefono4",
				"telefono5",
				"telefono6",
				"telefono7",
				"telefono8",
				"telefono9",
				"telefono10",
				"observaciones",
				"codigoAsignacion"
				, "gama"
				, "regional"
				, "rangoEdad"
				, "rangoCupo"
				, "segmentacionAd1"
				, "segmentacionAd2"
				, "segmentacionAd3"
				, "segmentacionAd4"
				, "segmentacionAd5"
				, "easyCodeRegional"
				, "easyCodeEdad"
				, "easyCodeCupo"
				, "easyCodeEdadCupo"
				, "easyCodeGamaEdad"
				, "easyCodeAd1"
				, "easyCodeAd2"
				, "easyCodeAd3"
				, "easyCodeAd4"
				, "easyCodeAd5"
				, "prioridadCampania"
				, "fechaCaducidad"
				, "deudaProtegida"
				, "metaContactabilidad"
				, "metaEfectividadTelefonica"
				, "metaEfectividadReal"
				, "tipoGestion"
				, "plataforma"
				, "codigoCampania"
				, "tipoMarca"
		]
		return fields
	}

	static HashMap getNivelesEstudio(){
		return ['': '-- Seleccione --', '1': 'NINGUNO', '2': 'PRIMARIA', '3': 'SECUNDARIA', '4': 'TECNICA', '5': 'SUPERIOR', '6': 'POSTGRADO', '7': 'MASTERADO', '8': 'P.H.D.', '9': 'OTROS']
	}

	static HashMap getOrigenesIngresos(){
		return ['': '-- Seleccione --', '1': 'EMPLEADO PUBLICO', '2': 'EMPLEADO PRIVADO', '3': 'INDEPENDIENTE', '4': 'AMA DE CASA', '5': 'RENTISTA', '6': 'JUBILADO O PENSIONISTA', '7': 'REMESAS DEL EXTERIOR']
	}

	static HashMap getRangosIngresos(){
		return ['': '-- Seleccione --', '1': 'MENOS DE 600', '2': '600 - 1000', '3': '1000 - 2000', '4': '2000 - 3500', '5': '3500 - 5000', '6': '5000 - 7500', '7': ' MAS DE 7500']
	}

	static HashMap getTiposVivienda(){
		return ['': '-- Seleccione --', 'F': 'VIVE CON FAMILIARES', 'P': 'PROPIA HIPOTECADA', 'N': 'PROPIA NO HIPOTECADA', 'A': 'ARRENDADA', 'S': 'PRESTADA']
	}

	static HashMap getTiposParientes(){
		return ['': '-- Seleccione --', '00':'Ninguno', '01': 'Padre', '02': 'Madre', '03': 'Hermano(a)', '04': 'Primo(a)',
		'05': 'Tío(a)', '06': 'Sobrino(a)', '07': 'Esposa(o)', '08': 'Cuñado', '09': 'Yerno (Nuera)', '10': 'Suegro(a)', '11': 'Hijo(a)',
		'12': 'Amigo(a)', '13': 'Abuelo(a)', '14': 'Novio (a)', '15': 'Nieto (a)', '16': 'Compañero de Trabajo', '17': 'Familiar',
		'18': 'Representante Legal', '19': 'Relación Comercial', '20': 'Relación Laboral', '21': 'Presidente', '22': 'Vice-presidente',
		'23': 'Funcionario', '24': 'Ejecutivo']
	}

	static HashMap getMarcas(){
		return ['': '-- Seleccione --', '1': 'VISA PLATINUM', '2': 'MASTERCARD PLATINUM']
	}
	static HashMap getTipoVisa(){
		return ['': '-- Seleccione --', 'VISA SIGNATURE': 'VISA SIGNATURE', 'VISA INFINITI': 'VISA INFINITI']
	}
	static HashMap getTipoMastercard(){
		return ['': '-- Seleccione --', 'MASTERCARD BLACK': 'MASTERCARD BLACK']
	}

}
