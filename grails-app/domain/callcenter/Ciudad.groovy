package callcenter

class Ciudad {

    String nombre
    String estado
    String codigo
    Provincia provincia

    static constraints = {
        codigo nullable: true
    }

    static mapping = {
        version false
    }

}
