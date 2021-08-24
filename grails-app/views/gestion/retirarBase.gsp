<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="layout" content="main"/>
<title>Tarjetas Austro - Retirar Base</title>
<asset:stylesheet src="gestion/retirarBase.css"></asset:stylesheet>
</head>
<body>
  <div class="body">   
			<div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-border-orange">
                            <div class="panel-heading panel-body-orange">
                                <h3 class="panel-title" style="color:white"><i class="fa fa-users fa-fw"></i> Retirar Base</h3>								
                            </div>
                            <div class="panel-body">
                                <div class="table-responsive">
	                                <g:form action="retirarBase">
	                                	<div class="form-group col-lg-3">
	                                		<label>Usuario: </label>
	                                		<g:select class="form-control" name="usuario" optionKey="id" from="${com.pw.security.Usuario.findAll{rol.nombre == 'OPERADOR'; estado == 'ACTIVO'}}" optionValue="${{it.nombre}}" noSelection="${['': 'TODOS']}"></g:select>
	                                	</div>	
	                                	<div class="form-group col-lg-3">
	                                		<label>Tipo: </label>
	                                		<g:select class="form-control" name="tipo" from="${['REGESTIONABLE', 'SIN GESTION', 'RELLAMADAS']}" noSelection="${['': 'SIN GESTION Y REGESTIONABLES']}"></g:select>
	                                	</div>
										<div class="form-group col-lg-3">
											<label>Cod desde: </label>
											<g:textField class="form-control" name="desde"></g:textField>
										</div>
										<div class="form-group col-lg-3">
											<label>Cod hasta: </label>
											<g:textField class="form-control" name="hasta"></g:textField>
										</div>
										<div class="form-group col-lg-9">
	                                		<strong>Nombre Base: </strong>
	                                		<g:select class="form-control" name="nombrebase" from="${utilitarios.Util.getNombresBase()}" noSelection="${['': 'TODOS']}"></g:select>
	                                	</div>
	                                	<div class="form-group col-lg-3">
											<label></label>
	                                		<input class="form-control btn btn-primary" type="submit" name="btnenviar" id="btnenviar" value="Retirar"/>
	                                	</div>
	                                </g:form>
	                                <g:if test="${updateRealizado == true}">
	                                	<div id="divresultado" class="col-lg-12">
	                                		${resultado} registros fueron retirados
	                                	</div>
	                                </g:if>
                                </div>
								<%--table responsive--%>
									
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /.row -->
  </div>
</body>
</html>