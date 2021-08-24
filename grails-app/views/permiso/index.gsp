
<%@ page import="com.pw.security.Permiso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permiso.label', default: 'Permiso')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-permiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav sombra" role="navigation">
			<ul>				
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div><br>
		<div id="list-permiso" class="content scaffold-list" role="main">
<%--			<h1><g:message code="default.list.label" args="[entityName]" /></h1>--%>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="row">
                    <div class="col-lg-12 col-md-6">
                        <div class="panel panel-border-blue-u">
                            <div class="panel-heading panel-body-blue-u">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-star fa-fw"></i> Listado de Permisos</h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
			<table id="permisoslista">
			<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'permiso.descripcion.label', default: 'Descripcion')}" />
						
						<g:sortableColumn property="descripcion" title="${message(code: 'permiso.descripcion.label', default: 'Acciones')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${permisoInstanceList}" status="i" var="permisoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${permisoInstance.id}">${fieldValue(bean: permisoInstance, field: "descripcion")}</g:link></td>
						
						<td><g:link action="edit" class="fa fa-pencil" id="${permisoInstance.id}"> Editar</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			</div>
			<%--table responsive--%>
									
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
<%--			<div class="pagination">--%>
<%--				<g:paginate total="${permisoInstanceCount ?: 0}" />--%>
<%--			</div>--%>
		</div>
	</body>
</html>
