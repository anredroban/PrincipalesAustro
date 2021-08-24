package com.pw.security

class Sesion {
	
	String idSesion
	Date fechaInicio
	Date fechaFin
	Usuario usuario
	String direccionIp

    static constraints = {
		idSesion nullable: true
		fechaInicio nullable: true
		fechaFin nullable: true
		usuario nullable: true
		direccionIp nullable: true
    }
	
	static mapping = {
		version false
	  }
}