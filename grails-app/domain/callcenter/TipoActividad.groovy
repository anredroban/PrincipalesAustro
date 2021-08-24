package callcenter

class TipoActividad {

    String nombre

    static constraints = {
        nombre nullable: true
    }
    static mapping = {
        version false
    }
}
