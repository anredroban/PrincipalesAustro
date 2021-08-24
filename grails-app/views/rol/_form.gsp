<%@ page import="com.pw.security.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'nombre', 'error')} required col-lg-3">
	<label for="nombre">
		<g:message code="rol.nombre.label" default="Nombre" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField name="nombre" required="" value="${rolInstance?.nombre}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'permisos', 'error')} col-lg-3">
	<label for="permisos">
		<g:message code="rol.permisos.label" default="Permisos" />
		
	</label>
	<g:select name="permisos" from="${com.pw.security.Permiso.list()}" multiple="multiple" optionKey="id" size="30" value="${rolInstance?.permisos*.id}" class="many-to-many" optionValue="${{it.descripcion}}"/>
<br><br>
</div>

