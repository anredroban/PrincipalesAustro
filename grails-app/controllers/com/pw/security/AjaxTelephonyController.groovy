package com.pw.security

class AjaxTelephonyController {

    /**
     * @author Esteban Preciado.
     * @return
     */
    def saveBreakTime() {

        def user = Usuario.findById(session.user.id)
        def type = params.typeBreak.toString().replace("ñ","ni").replace("é","e").replace("ó","o")

        def breakTime = new BreakTime()
        breakTime.timeBreak = params.timeBreak.toString().toInteger()
        breakTime.typeBreak = type
        breakTime.dateBreak = new Date()
        breakTime.user = user
        breakTime.save()

        render true
    }
}
