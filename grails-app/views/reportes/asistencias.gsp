<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<asset:javascript src="usogeneral/moment.min.js" />
<asset:javascript src="usogeneral/daterangepicker.js" />
<asset:stylesheet src="usogeneral/daterangepicker.css" />
<asset:javascript src="usogeneral/customdaterangepicker.js" />
<asset:stylesheet src="usogeneral/customdaterangepicker.css" />
<div class="container-fluid">
    <g:form action = "asistencias" class="col-lg-12">
        <div class="col-lg-12">
            <label>
                Fecha:
            </label>
            <input name="fechas" class="reportrange">
        </div>
        <div class="col-lg-12">
            <label>
                Nombre Base:
            </label>
            <g:select name="nombreBase" multiple="true" size="10" required="" from="${utilitarios.Util.getNombresBase()}"></g:select>
        </div>
        <div class="col-lg-12">
            <g:submitButton name = "btnEnviar" value = "Enviar"></g:submitButton>
        </div>
    </g:form>
</div>