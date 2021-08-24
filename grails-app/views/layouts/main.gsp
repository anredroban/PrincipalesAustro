<%@page import="utilitarios.Util"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title><g:layoutTitle default="Bienvenida Austro" /></title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.png')}" type="image/x-icon">
	
	<%--Javascripts--%>
	<asset:javascript src="main/jquery.js" />
	<asset:javascript src="main/custom.js" />
	<asset:javascript src="main/datatable.js" />
	<asset:javascript src="main/customdatatables.js" />
	<asset:javascript src="main/timer.js" />
	
	<%--Stylesheets--%>
	<asset:stylesheet src="main/main.css" />
	<asset:stylesheet src="main/bootstrap.min.css" />
	<asset:stylesheet src="main/sb-admin.css" />
	<asset:stylesheet src="main/sidebar.css" />
	<asset:stylesheet src="telephony/operatorbar.css" />
	<asset:stylesheet src="main/font-awesome.css" />
	<asset:stylesheet src="main/font-awesome-animation.css" />
	<asset:stylesheet src="main/custom.css" />
	<asset:stylesheet src="main/portlet.css" />
	<asset:stylesheet src="main/datatable.css" />
	
	<script type="text/javascript">		
			var baseUrl = "http://" + "${request.getServerName()}" + ":" + "${request.getServerPort()}" + "${request.getContextPath()}";		
		</script>
	<g:layoutHead />
</head>

<body>
<div id="wrapper">
		<nav class="navbar navbar-fixed-top" role="navigation">
			<a class="img-logo navbar-brand" href="#">
				<img class="logo-crm" src="${assetPath(src: 'logo-big.png')}" alt="Plus Wireless" />
			</a>
			<ul class="nav navbar-right box-user">
				<g:if test="${session.user}">

					<li class="dropdown">
						<a href="#" class="dropdown-toggle" style="color: grey" data-toggle="dropdown">
							<img style="max-width: 40px; max-height: 40px" src="${assetPath(src: 'avatarsmall.png')}" /><span class="hidden-xs"> ${session.user.nombre}</span><b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<g:link action="logout" controller="usuario">
									<i class="fa fa-fw fa-power-off"></i> Salir
								</g:link>
							</li>
						</ul>
					</li>

					<g:if test="${Util.isOperator(session.user.usuario)}">

						<li class="recallNotification">
							<a href="#">
								<i class="fa fa-bell faa-ring animated fa-2x faa-slow text-danger">
									&nbsp;<span id="number-recalls"></span>
								</i>
							</a>
						</li>
						<div id="recall-list" class="collapse"></div>

					</g:if>
				</g:if>
			</ul>
		</nav>

		<g:render template="/layouts/sidebar" />

		<div id="page-wrapper" class="container-section">
			<div class="panel-default">
				<g:layoutBody />
			</div>
		</div>
	</div>

	<g:render template="/layouts/operatorbar" />

	<div id="modal-delete-item" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Borrar registro</h4>
				</div>
				<div class="modal-body">
					<p>¡Se eliminará el registro seleccionado!</p>
				</div>
				<div class="modal-footer">
					<button id="btn-delete-item" type="button" class="btn btn-success">Confirmar</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>

	<asset:javascript src="main/bootstrap.min.js" />
	<asset:javascript src="telephony/operatorbar.js" />
	<asset:javascript src="telephony/asteriskAmi.js" />
	<div id="scriptjq"></div>
</body>
</html>
