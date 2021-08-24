<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=${encoding}"/>
	<title>Principales Austro - Ingreso de Usuarios</title>
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.ico')}"
		  type="image/x-icon">
	<script type="text/javascript">
        var baseUrl = "http://" + "${request.getServerName()}" + ":" + "${request.getServerPort()}" + "${request.getContextPath()}";
	</script>
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
	<asset:stylesheet src="usuario/login/reset.css" />
	<asset:stylesheet src="usuario/login/style.css" />
	<asset:stylesheet src="usuario/login/supersized.css" />
</head>
<body>

<div class="page-container">
	<h1 style="color: #666666">TARJETAS PRINCIPALES AUSTRO</h1>
	<g:form action="login">
		<div>
			<g:if test="${flash.errorMessage}">
				<div style="color: red">${flash.errorMessage}</div>
			</g:if>
		</div>
		<input type="text" name="usuario" class="username" placeholder="Usuario">
		<input type="password" name="password" class="password" placeholder="Password">
		<button type="submit">Ingresar</button>
		<div class="error"><span>+</span></div>
	</g:form>
	<div class="connectx">
		<p>
			<div style="text-align: center; margin-top: 10px">
				<img class="logo-crm" src="${assetPath(src: 'logo-big.png')}" alt="Plus Wireless" />
			</div>

		</p>
	</div>
</div>
<asset:javascript src="main/jquery.js" />
<asset:javascript src="usuario/login/scripts.js" />
<asset:javascript src="usuario/login/supersized-init.js" />
<asset:javascript src="usuario/login/supersized.3.2.7.min.js" />
</body>
</html>