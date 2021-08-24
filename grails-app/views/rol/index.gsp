
<%@ page import="com.pw.security.Rol" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'rol.label', default: 'Rol')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-rol" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav sombra" role="navigation">
			<ul>				
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div><br>
		<div id="list-rol" class="content scaffold-list" role="main">
<%--			<h1><g:message code="default.list.label" args="[entityName]" /></h1>--%>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="row">
                    <div class="col-lg-12 col-md-6">
                        <div class="panel panel-border-blue-u">
                            <div class="panel-heading panel-body-blue-u">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-th fa-fw"></i> Listado de Roles</h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">	
			<table id="roleslista">
			<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'rol.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'rol.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'rol.lastUpdated.label', default: 'Last Updated')}" />
						
						<g:sortableColumn property="Acciones" title="${message(code: 'rol.lastUpdated.label', default: 'Acciones')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rolInstanceList}" status="i" var="rolInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rolInstance.id}">${fieldValue(bean: rolInstance, field: "nombre")}</g:link></td>
					
						<td><g:formatDate date="${rolInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${rolInstance.lastUpdated}" /></td>
						
						<td><g:link action="edit" class="fa fa-pencil" id="${rolInstance.id}"> Editar</g:link></td>
					
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
<%--				<g:paginate total="${rolInstanceCount ?: 0}" />--%>
<%--			</div>--%>
		</div>
	</body>
</html>
