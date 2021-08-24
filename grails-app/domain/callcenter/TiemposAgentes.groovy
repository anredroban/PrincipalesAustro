package callcenter

class TiemposAgentes {

    String nombreAgente
    String efectivos
    String meta
    String faltantes
    String porcentajeMeta
    String promedioHora
    String inicioConexion
    String finConexion
    String tiempoConexion
    String tiempoBreak
    String tiempoMuerto
    String tiempoEfectivo
    String observacion
    String fecha
    String horaActualizacion
    String totalGrupoEfectivo
    String totalGrupoMeta
    String totalGrupoFaltantes
    String totalGrupoPorcentajesMeta
    String totalGrupoPorcentajesPromedioHora

    static constraints = {
        nombreAgente nullable: true
        efectivos nullable: true
        meta nullable: true
        faltantes nullable: true
        porcentajeMeta nullable: true
        promedioHora nullable: true
        inicioConexion nullable: true
        finConexion nullable: true
        tiempoConexion nullable: true
        tiempoBreak nullable: true
        tiempoMuerto nullable: true
        tiempoEfectivo nullable: true
        observacion nullable: true
        fecha nullable: true
        horaActualizacion nullable: true
        totalGrupoEfectivo nullable: true
        totalGrupoMeta nullable: true
        totalGrupoFaltantes nullable: true
        totalGrupoPorcentajesMeta nullable: true
        totalGrupoPorcentajesPromedioHora nullable: true
    }

    static mapping = {
        version false
    }

}
