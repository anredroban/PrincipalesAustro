package com.pw.security

class Log {
	
	String hostName
	Date dateCreated
	Usuario user
	String description
	
	static constraints = {
		
	}
	
	static mapping = {
		version false
		description type: 'text'
	  }
}
