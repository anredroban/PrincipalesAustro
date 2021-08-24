package com.pw.security



import static org.springframework.http.HttpStatus.*

import com.pw.security.Sesion;

import grails.transaction.Transactional

@Transactional(readOnly = true)
class SesionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Sesion.list(params), model:[sesionInstanceCount: Sesion.count()]
    }

    def show(Sesion sesionInstance) {
        respond sesionInstance
    }

    def create() {
        respond new Sesion(params)
    }

    @Transactional
    def save(Sesion sesionInstance) {
        if (sesionInstance == null) {
            notFound()
            return
        }

        if (sesionInstance.hasErrors()) {
            respond sesionInstance.errors, view:'create'
            return
        }

        sesionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sesion.label', default: 'Sesion'), sesionInstance.id])
                redirect sesionInstance
            }
            '*' { respond sesionInstance, [status: CREATED] }
        }
    }

    def edit(Sesion sesionInstance) {
        respond sesionInstance
    }

    @Transactional
    def update(Sesion sesionInstance) {
        if (sesionInstance == null) {
            notFound()
            return
        }

        if (sesionInstance.hasErrors()) {
            respond sesionInstance.errors, view:'edit'
            return
        }

        sesionInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Sesion.label', default: 'Sesion'), sesionInstance.id])
                redirect sesionInstance
            }
            '*'{ respond sesionInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Sesion sesionInstance) {

        if (sesionInstance == null) {
            notFound()
            return
        }

        sesionInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Sesion.label', default: 'Sesion'), sesionInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sesion.label', default: 'Sesion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
