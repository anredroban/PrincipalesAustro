<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<div class="container-fluid">
    <g:form action = "baseSinGestion" class="col-lg-12">
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