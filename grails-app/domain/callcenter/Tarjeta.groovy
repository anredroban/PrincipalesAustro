package callcenter

class Tarjeta {

    String bin
    String nombre
    String afinidad
    String descripcion
    String genero
    String nivel
    String marca

    static constraints = {
        genero nullable: true
        nivel nullable: true
        marca nullable: true
    }
    static mapping = {
        version false
    }
}
