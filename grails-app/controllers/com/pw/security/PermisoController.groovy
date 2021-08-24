package com.pw.security



import static org.springframework.http.HttpStatus.*

import com.pw.security.Permiso;
import com.pw.security.Rol;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class PermisoController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

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

	def index(Integer max) {
		//        params.max = Math.min(max ?: 1000, 10000)
		respond Permiso.list(params), model:[permisoInstanceCount: Permiso.count()]
	}

	def show(Permiso permisoInstance) {
		respond permisoInstance
	}

	def create() {
		respond new Permiso(params)
	}

	@Transactional
	def save(Permiso permisoInstance) {
		if (permisoInstance == null) {
			notFound()
			return
		}

		if (permisoInstance.hasErrors()) {
			respond permisoInstance.errors, view:'create'
			return
		}

		permisoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [
					message(code: 'permiso.label', default: 'Permiso'),
					permisoInstance.id
				])
				redirect permisoInstance
			}
			'*' { respond permisoInstance, [status: CREATED] }
		}
	}

	def edit(Permiso permisoInstance) {
		respond permisoInstance
	}

	@Transactional
	def update(Permiso permisoInstance) {
		if (permisoInstance == null) {
			notFound()
			return
		}

		if (permisoInstance.hasErrors()) {
			respond permisoInstance.errors, view:'edit'
			return
		}

		permisoInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [
					message(code: 'Permiso.label', default: 'Permiso'),
					permisoInstance.id
				])
				redirect permisoInstance
			}
			'*'{ respond permisoInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Permiso permisoInstance) {

		if (permisoInstance == null) {
			notFound()
			return
		}

		permisoInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [
					message(code: 'Permiso.label', default: 'Permiso'),
					permisoInstance.id
				])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [
					message(code: 'permiso.label', default: 'Permiso'),
					params.id
				])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
