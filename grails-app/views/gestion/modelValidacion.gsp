<%--
  Created by IntelliJ IDEA.
  User: Desarrollo
  Date: 28/10/2020
  Time: 12:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <meta name="layout" content="main" />
    <title>Principales Austro</title>
    <asset:stylesheet src="bootstrap.min.css" />
</head>

<body>
<br>
<div class="col-md-8">
    <div class="alert alert-danger" role="alert">
        <label style="font-size: 30px"><strong><i class="fa fa-fw fa-exclamation-triangle"></i> ¡ERROR!</strong></label><br>
        <p>El registro ya se encuentra guardado con el estado: <strong>${estado}</strong> >> <strong>${subestado}.</strong></p>
        <p>No se puede continuar, por favor consulte con el Administrador.</p>
        <p><strong>ID REGISTRO: </strong> ${idCliente}</p>
    </div>
</div>
</body>
</html>