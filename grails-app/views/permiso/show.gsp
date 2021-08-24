
<%@ page import="com.pw.security.Permiso" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'permiso.label', default: 'Permiso')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-permiso" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav sombra" role="navigation">
			<ul>				
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-permiso" class="content scaffold-show" role="main">
<%--			<h1><g:message code="default.show.label" args="[entityName]" /></h1>--%>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list permiso">
			
				<div class="row">
                    <div class="col-lg-12 col-md-6">
                        <div class="panel panel-border-blue-u">
                            <div class="panel-heading panel-body-blue-u">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-star fa-fw"></i> <g:fieldValue bean="${permisoInstance}" field="descripcion"/></h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">									  	

										<table class="table table-striped">
											<tbody>											  
											  <tr>
											    <td class="active"><strong>Descripción</strong></td>
											    <td class="active"><span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${permisoInstance}" field="descripcion"/></span></td>											    
											  </tr>											  
											</tbody>
										</table>									  	
								</div>
<%--								table responsive--%>
									
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
			
			</ol>
			<g:form url="[resource:permisoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${permisoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
