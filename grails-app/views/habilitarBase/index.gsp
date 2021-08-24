<meta name="layout" content="main">
<div class="content-fluid col-lg-12 col-md-12 col-xs-12">
    <div class="form-group col-lg-6 col-md-6 col-xs-6">
        <label>
            Bases Hábiles
        </label>
        <g:select name="habiles" size="10" class="form-control" multiple="" from="${utilitarios.Util.getNombresBase()}" />
        <br/>
        <input id="inhabilitar" type="button" class="form-control btn btn-warning" value="Inhabilitar">
    </div>
    <div class="form-group col-lg-6 col-md-6 col-xs-6">
        <label>
            Bases No Hábiles
        </label>
        <g:select name="noHabiles" size="10" class="form-control" multiple="" from="${utilitarios.Util.getBasesNoHabiles()}" />
        <br/>
        <input id="habilitar" type="button" class="form-control btn btn-success" value="Habilitar">
    </div>
</div>
<asset:javascript src="habilitarBase/index.js" />