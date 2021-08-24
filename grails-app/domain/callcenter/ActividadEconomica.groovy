package callcenter

class ActividadEconomica {

    String codigo
    String descripcion
    TipoActividad tipoActividad

    static constraints = {
    }

    static mapping = {
        version false
        descripcion type: 'text'
    }

}
