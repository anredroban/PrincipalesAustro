<%--
  Created by IntelliJ IDEA.
  User: Giovanny
  Date: 25/04/2017
  Time: 16:44
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Tarjetas Austro - Cargar Base</title>
    <asset:stylesheet src="usogeneral/breadcrumb.css" />
    <asset:javascript src="usogeneral/breadcrumb.js" />
</head>

<body>
<g:if test="${flash.error}">
    <div class="alert alert-error" style="display: block">${flash.error}</div>
</g:if>
<div>
    <input class="breadcrumb-load" type="hidden" value="1" />
    <g:render template="/layouts/breadcrumb" model="[totalTabs:['Cargar archivo', 'Asignar Campos', 'Completar']]"/>
    <g:if test="${!success}">
        <fieldset class="form">
            <g:uploadForm action="saveFile" class="form-horizontal">
                <div class="form-group">
                    <label class="control-label col-lg-2">Seleccionar archivo</label>
                    <div class="col-lg-6">
                        <input type="file" name="file" id="file" required="" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-lg-2">&nbsp;</label>
                    <div class="col-lg-6">
                        <g:submitButton name="load" class="save btn btn-navigation" value="Siguiente" />
                    </div>
                </div>
            </g:uploadForm>
        </fieldset>
    </g:if>
    <g:else>
        <input class="breadcrumb-load" type="hidden" value="3" />
        <div id="div-end-message">
            <g:render template="/layouts/completeMessage" />
        </div>
        <g:javascript>showEndMessage("La base se cargó correctamente", 1, 3)</g:javascript>
    </g:else>
</div>
</body>
</html>