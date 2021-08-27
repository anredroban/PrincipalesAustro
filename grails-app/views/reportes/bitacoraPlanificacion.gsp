<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<asset:javascript src="usogeneral/moment.min.js" />
<asset:javascript src="usogeneral/daterangepicker.js" />
<asset:stylesheet src="usogeneral/daterangepicker.css" />
<asset:javascript src="usogeneral/customdaterangepicker.js" />
<asset:stylesheet src="usogeneral/customdaterangepicker.css" />
<div class="container-fluid">
    <g:form action = "bitacoraPlanificacion" class="col-lg-12">
        <div class="form-group col-lg-12">
            <label>
                Fecha:
            </label>
            <input name="fechas" class="reportrange form-control">
        </div>
      <%--  <div class="col-lg-12 form-group">
            <label>
                Nombre Base:
            </label>
            <g:select class="form-control" name="nombreBase" multiple="true" size="25" required="" from="${utilitarios.Util.getNombresBase()}"></g:select>
        </div>--%>
        <div class="form-group col-lg-12">
            <g:submitButton class="btn btn-success" style="height: 40px; width: 170px; font-weight: bold; font-family: 'Myriad Pro'; font-size: 16px" name = "btnEnviar" value = "Descargar Reporte"></g:submitButton>
        </div>
    </g:form>
</div>