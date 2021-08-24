<meta name="layout" content="main">
<div class="content-fluid col-lg-12 col-md-12 col-xs-12">
    <g:form action="enrolamientos">
        <div class="form-group col-lg-6 col-md-6 col-xs-12">
            <label>Escoja la base</label>
            <g:select class="form-control" multiple="" size="10" required="" name="nombreBase" from="${utilitarios.Util.getNombresBase()}" />
        </div>

        <div class="col-lg-12 col-md-12 col-xs-12">
            <g:submitButton class="btn btn-success" name="btnReporteEnrolamientos" value="Enviar" />
        </div>
    </g:form>
</div>