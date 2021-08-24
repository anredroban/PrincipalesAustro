package com.pw.security

class Permiso {
	
	String descripcion;	
	
	static belongsTo = Rol;

    static constraints = {
		descripcion unique: true
    }
	
	static mapping = {
		version false
	  }
}
