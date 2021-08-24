package callcenter

import com.pw.security.*

class Historial {

	String estadoGestion
	Subestado subestadoGestion
	String subSubEstado
	Date fechaGestion
	int intentos
	String nombreVendedor
	String observacionesGestion
	Usuario usuario
	Clientes cliente
	int duracionLlamada
	String plataforma
	String detalleTelefono1
	String detalleTelefono2
	String detalleTelefono3
	String detalleTelefono4
	String detalleTelefono5
	String detalleTelefono6
	String detalleTelefono7
	String detalleTelefono8
	String detalleTelefono9
	String detalleTelefono10
	String estadoTelefonoContactado

    static constraints = {
		estadoGestion nullable: true
		subestadoGestion nullable: true
		subSubEstado nullable: true
		fechaGestion nullable: true
		intentos nullable: true
		nombreVendedor nullable: true
		observacionesGestion nullable: true
		usuario nullable: true
		cliente nullable: true
		plataforma nullable: true
		detalleTelefono1 nullable: true
		detalleTelefono2 nullable: true
		detalleTelefono3 nullable: true
		detalleTelefono4 nullable: true
		detalleTelefono5 nullable: true
		detalleTelefono6 nullable: true
		detalleTelefono7 nullable: true
		detalleTelefono8 nullable: true
		detalleTelefono9 nullable: true
		detalleTelefono10 nullable: true
		estadoTelefonoContactado nullable: true
    }
	
	static mapping = {
		version false
		observacionesGestion type: "text"
	}
}
