package com.pw.security



import static org.springframework.http.HttpStatus.*

import com.pw.security.Permiso;
import com.pw.security.Rol;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class RolController {

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
//        params.max = Math.min(max ?: 10, 100)
        respond Rol.list(params), model:[rolInstanceCount: Rol.count()]
    }

    def show(Rol rolInstance) {
        respond rolInstance
    }

    def create() {
        respond new Rol(params)
    }

    @Transactional
    def save(Rol rolInstance) {
        if (rolInstance == null) {
            notFound()
            return
        }

        if (rolInstance.hasErrors()) {
            respond rolInstance.errors, view:'create'
            return
        }

        rolInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rol.label', default: 'Rol'), rolInstance.id])
                redirect rolInstance
            }
            '*' { respond rolInstance, [status: CREATED] }
        }
    }

    def edit(Rol rolInstance) {
        respond rolInstance
    }

    @Transactional
    def update(Rol rolInstance) {
        if (rolInstance == null) {
            notFound()
            return
        }

        if (rolInstance.hasErrors()) {
            respond rolInstance.errors, view:'edit'
            return
        }				
		
        rolInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Rol.label', default: 'Rol'), rolInstance.id])
                redirect rolInstance
            }
            '*'{ respond rolInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Rol rolInstance) {

        if (rolInstance == null) {
            notFound()
            return
        }

        rolInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Rol.label', default: 'Rol'), rolInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rol.label', default: 'Rol'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
