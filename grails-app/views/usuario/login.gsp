<!DOCTYPE html>
<html lang="en">
<head>
	<title>Principales Austro</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<asset:stylesheet src="vendor/bootstrap/css/bootstrap.min.css"/>
	<asset:stylesheet src="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
	<asset:stylesheet src="fonts/iconic/css/material-design-iconic-font.min.css"/>
	<asset:stylesheet src="vendor/animate/animate.css"/>
	<asset:stylesheet src="vendor/css-hamburgers/hamburgers.min.css"/>
	<asset:stylesheet src="vendor/animsition/css/animsition.min.css"/>
	<asset:stylesheet src="vendor/select2/select2.min.css"/>
	<asset:stylesheet src="vendor/daterangepicker/daterangepicker.css"/>
	<asset:stylesheet src="css/main.css"/>
	<asset:stylesheet src="css/util.css"/>
	<link rel="shortcut icon" href="${assetPath(src: 'favicon.png')}"
		  type="image/png">
</head>
<body>

<div class="limiter">
	<div class="container-login100">
		<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
			<g:form action="login" class="login100-form validate-form">
				<div>
					<g:if test="${flash.errorMessage}">
					<%--<div style="color: red; text-align: center">${flash.errorMessage}</div>--%>
						<div class="col-md-16" style="text-align: center">
							<div class="alert alert-danger" role="alert">
								<i class="fa fa-fw fa-exclamation-triangle"></i>
								<span style="font-weight: bold; color: red;text-align: center"> ${flash.errorMessage} </span>
								<i class="fa fa-fw fa-exclamation-triangle" ></i>
							</div>
						</div>
					</g:if>

				</div>

				<span class="login100-form-title p-b-32">
					PRINCIPALES AUSTRO
				</span>

				<span class="txt1 p-b-11">
					Usuario
				</span>
				<div class="wrap-input100 validate-input m-b-36" data-validate = "Usuario es requerido">
					<input class="input100" type="text" name="usuario" id="usuario" >
					<span class="focus-input100"></span>
				</div>

				<span class="txt1 p-b-11">
					Password
				</span>
				<div class="wrap-input100 validate-input m-b-12" data-validate = "Password es requerido">
					<span class="btn-show-pass">
						<i class="fa fa-eye"></i>
					</span>
					<input class="input100" type="password" name="password" id="password" >
					<span class="focus-input100"></span>
				</div>

				<div class="flex-sb-m w-full p-b-48">
					<div class="contact100-form-checkbox">
						<input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
						<label class="label-checkbox100" for="ckb1">
							Recordar
						</label>
					</div>

				</div>

				<div class="container-login100-form-btn">
					<button class="login100-form-btn" id="boton">
						INGRESAR
					</button>
				</div>
				<div id="copyright" class="txt3" >&copy;Copyright 2021 Plus Wireless - Todos los derechos reservados</div>
			</g:form>

		</div>
	</div>
</div>


<div id="dropDownSelect1"></div>

<asset:javascript src="vendor/jquery/jquery-3.2.1.min.js" />
<asset:javascript src="vendor/animsition/js/animsition.min.js" />
<asset:javascript src="vendor/bootstrap/js/popper.js" />
<asset:javascript src="vendor/bootstrap/js/bootstrap.min.js" />
<asset:javascript src="vendor/select2/select2.min.js" />
<asset:javascript src="vendor/daterangepicker/moment.min.js" />
<asset:javascript src="vendor/daterangepicker/daterangepicker.js" />
<asset:javascript src="vendor/countdowntime/countdowntime.js" />
<asset:javascript src="js/main.js" />

</body>
</html>