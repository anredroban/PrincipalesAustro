package callcenter

class Provincia {

    String nombre
    String prefijo
    String regional
    String agencia

    static constraints = {
        prefijo nullable: true
        regional nullable: true
        agencia nullable: true
    }

    static mapping = {
        version false
    }

}
