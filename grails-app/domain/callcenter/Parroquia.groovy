package callcenter

class Parroquia {

    String nombre
    Ciudad ciudad
    String codigo
    static constraints = {
        codigo nullable: true
    }

    static mapping = {
        version false
    }

}
