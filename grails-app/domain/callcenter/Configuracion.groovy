package callcenter

class Configuracion {

    Date fechaInicioGestion

    static constraints = {
        fechaInicioGestion nullable: true
    }

    static mapping = {
        version false
    }
}
