
<%@ page import="com.pw.security.Usuario" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav sombra" role="navigation">
			<ul>				
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div><br>
		<div id="list-usuario" class="content scaffold-list" role="main">
			
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-border-blue-u">
                            <div class="panel-heading panel-body-blue-u">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-user fa-fw"></i> Listado de Usuarios</h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">	
			<table id="usuarioslista">
			<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'usuario.nombre.label', default: 'Nombre')}" />
						
						<g:sortableColumn property="usuario" title="${message(code: 'usuario.usuario.label', default: 'Usuario')}" />
					
						<g:sortableColumn property="estado" title="${message(code: 'usuario.rol.label', default: 'Rol')}" />						
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'usuario.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="lastUpdated" title="${message(code: 'usuario.lastUpdated.label', default: 'Last Updated')}" />						
					
						<g:sortableColumn property="estado" title="${message(code: 'usuario.estado.label', default: 'Estado')}" />
						
						<g:sortableColumn property="estado" title="${message(code: 'usuario.estado.label', default: 'Acciones')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${usuarioInstanceList}" status="i" var="usuarioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${usuarioInstance.id}">${fieldValue(bean: usuarioInstance, field: "nombre")}</g:link></td>
						
						<td>${fieldValue(bean: usuarioInstance, field: "usuario")}</td>
					
						<td>${fieldValue(bean: usuarioInstance, field: "rol.nombre")}</td>						
					
						<td><g:formatDate date="${usuarioInstance.dateCreated}" /></td>
					
						<td><g:formatDate date="${usuarioInstance.lastUpdated}" /></td>						
					
						<td>${fieldValue(bean: usuarioInstance, field: "estado")}</td>
						
						<td><g:link action="edit" class="fa fa-pencil" id="${usuarioInstance.id}"> Editar</g:link></td>
					
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
<%--				<g:paginate total="${usuarioInstanceCount ?: 0}" />--%>
<%--			</div>--%>
		</div>
	</body>
</html>
