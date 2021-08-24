<%@ page import="com.pw.security.Permiso" %>



<div class="fieldcontain ${hasErrors(bean: permisoInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="permiso.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField name="descripcion" required="" value="${permisoInstance?.descripcion}"/>

</div>

