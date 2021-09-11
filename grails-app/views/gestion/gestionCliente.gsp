<meta name="layout" content="main">
<asset:stylesheet src="usogeneral/bootstrap-datepicker.min.css"></asset:stylesheet>
<div class="container-fluid">
	<title>Gestionar Cliente</title>

	<asset:stylesheet src="usogeneral/datetimepicker.css" />
	<asset:stylesheet src="gestion/gestionCliente.css" />

	<%--<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<link rel="stylesheet" href="/resources/demos/style.css">
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	    <script>
        $( function() {
            $( "#tags" ).autocomplete({
                    source: '<g:createLink controller='FuncionesAjax' action='getParroquiasNew'/>'
                  });
              } );
          </script>--%>
          <script>
              window.setInterval (BlinkIt, 500);
              var color = "red";
              function BlinkIt () {
                  var blink = document.getElementById ("blink");
                  color = (color == "#e4e4e4")? "red" : "#e4e4e4";
                  blink.style.color = color;
                  blink.style.fontSize='36px';}
          </script>

          <g:if test="${cliente.registroExitoso == 'SI'}">
              <div class="col-lg-12 col-md-12 col-xs-12">
                  <label id="blink" style="font-size: 28px; font-weight: bold; color: red" >¡AVISO! </label><span id="priodidadTc" style="font-size: 28px; font-weight: bold; color: red">CLIENTE EXITOSO NO GESTIONAR</span>
              </div>
          </g:if>
	<g:form action="guardarCliente">
          <div class="col-lg-12 col-md-12 col-xs-12">
              <h1><span class="fa fa-phone"></span> Gestionar Cliente</h1>
          </div>
          <div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Producto 1</label>
                  ${cliente.producto1V}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Producto 2</label>
                  ${cliente.producto2M}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Cupo</label>
                  ${cliente.cupo}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Cédula</label>
                  <span id="cedulaTitular">${cliente.identificacion}</span>
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Nombre</label>
                  ${cliente.nombre}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Fecha Nacimiento</label>
                  ${cliente.fechaNacimiento}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Estado Civil</label>
                  ${cliente.estadoCivil}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Profesión</label>
                  ${cliente.profesion}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Provincia</label>
                  ${cliente.provincia}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Ciudad</label>
                  ${cliente.ciudadCanton}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Lugar Trabajo</label>
                  ${cliente.lugarTrabajo}
              </div>
              <div class="form-group col-lg-4 col-md-6 col-xs-12">
                  <label>Email</label>
                  ${cliente.email}
              </div>


			  <g:if test="${cliente.ingresoIess && cliente.ingresoIess.trim() != ''}">
				  <div class="form-group col-lg-4 col-md-6 col-xs-12">
					  <label>Ingreso Iess</label>
					  ${cliente.ingresoIess}
				  </div>
			  </g:if>
			  <g:if test="${cliente.porcentajeIess && cliente.porcentajeIess.trim() != ''}">
				  <div class="form-group col-lg-4 col-md-6 col-xs-12">
					  <label>Porcentaje Iess</label>
					  ${cliente.porcentajeIess}
				  </div>
			  </g:if>


           <g:if test="${cliente.direccionTrabajo && cliente.direccionTrabajo.trim() != ''}">
              <div class="form-group col-lg-12 col-md-12 col-xs-12">
                  <label>Dirección Trabajo</label>
                  ${cliente.direccionTrabajo}
              </div>
            </g:if>
            <g:if test="${cliente.direccionDomicilio && cliente.direccionDomicilio.trim() != ''}">
              <div class="form-group col-lg-12 col-md-12 col-xs-12">
                  <label>Dirección Domicilio</label>
                  ${cliente.direccionDomicilio}
              </div>
            </g:if>
          </div>
          <div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">

              <g:if test="${cliente.telefono1}">
                  <div id="number1" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-mobile-phone"></span> Teléfono 1: </label>
                      ${cliente.telefono1}
					  <g:select  class="form-control" id="estadoTel1" name="estadoTel1" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />

                  </div>
              </g:if>
              <g:if test="${cliente.telefono2 && cliente.telefono2.trim() != ''}">
                  <div id="number2" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 2: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono2}
                      <g:select  class="form-control" id="estadoTel2" name="estadoTel2" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono3 && cliente.telefono3.trim() != ''}">
                  <div id="number3" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 3: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono3}
                      <g:select  class="form-control" id="estadoTel3" name="estadoTel3" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono4 && cliente.telefono4.trim() != ''}">
                  <div id="number4" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 4: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono4}
                      <g:select  class="form-control" id="estadoTel4" name="estadoTel4" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono5 && cliente.telefono5.trim() != ''}">
                  <div id="number5" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 5: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono5}
                      <g:select  class="form-control" id="estadoTel5" name="estadoTel5" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono6 && cliente.telefono6.trim() != ''}">
                  <div id="number6" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 6: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono6}
                      <g:select  class="form-control" id="estadoTel6" name="estadoTel6" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono7 && cliente.telefono7.trim() != ''}">
                  <div id="number7" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 7: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono7}
                      <g:select  class="form-control" id="estadoTel7" name="estadoTel7" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono8 && cliente.telefono8.trim() != ''}">
                  <div id="number8" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 8: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono8}
                      <g:select  class="form-control" id="estadoTel8" name="estadoTel8" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono9 && cliente.telefono9.trim() != ''}">
                  <div id="number9" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 9: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono9}
                      <g:select  class="form-control" id="estadoTel9" name="estadoTel9" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>
              <g:if test="${cliente.telefono10 && cliente.telefono10.trim() != ''}">
                  <div id="number10" class="form-group col-lg-4 col-md-6 col-xs-12">
                      <label><span class="fa fa-phone"></span> Teléfono 10: </label>
                      ${utilitarios.Util.functionAsterisk(cliente.id.toString())}${cliente.telefono10}
                      <g:select  class="form-control" id="estadoTel10" name="estadoTel10" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
                  </div>
              </g:if>

          </div>
		<g:if test="${cliente.tipoMarca == 'VISA INTERNACIONAL' || cliente.tipoMarca == 'MASTERCARD INTERNACIONAL'}">
			<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5><strong>SCRIPT VENTA TC BANCO DEL AUSTRO VISA-MASTERCARD INTERNACIONAL</strong></h5></div>
					<p>Buenos días me comunica por favor con el Sr(a) (ita) <strong>${cliente.nombre}</strong>? mucho gusto le saluda <strong>${session?.user?.nombre}</strong>  del Banco del Austro.</p>
					<p>
						En días anteriores nos comunicamos con usted para poner a su consideración la Tarjeta Visa-MasterCard Internacional.
						El Banco considerando la importancia de tenerle a usted como uno de nuestros clientes preferentes,
						ha mejorado la propuesta inicial con beneficios que estamos seguros será de mucho interés para usted
					</p>
					<p>
						Me permite 30 segundos, le comento de lo qué se trata y usted decide si continuamos la conversación, ¿le parece?
					</p>
					<p>
						<strong>NO</strong>: ¿Cuándo sería un buen momento para poder llamarle? Gestionar en agenda
					</p>
					<p>
						<strong>SI</strong>: Realizar las siguientes 3 Ofertas de Valor para el cliente
					</p>
					<p>
					<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 1</h4></strong>
					<blockquote style="font-size: 15px">
						<ul>
							<li><i>Cómo le habíamos mencionado anteriormente, tenemos aprobada una TC: Visa/MasterCard Internacional con un cupo de <strong>${cliente.cupo}</strong> Esta Oferta se mantiene.</i></li>
						</ul>
					</blockquote>
					</p>
					<p>
						<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 2</h4></strong>
					<blockquote style="font-size: 15px">
						<ul>
							<li><i>Además su Tarjeta estaría acompañada de un Bono Supermaxi de $15 (QUINCE DÓLARES) que representan
							los 4.000 puntos de Bienvenida, en nuestro Plan de Recompensas Austroclass. Que le entregamos
							con la aceptación de la Tarjeta y con su primer consumo mínimo del mismo valor del
							Bono $15 (QUINCE DÓLARES)</i></li>
							<li><i>Es decir si usted acepta la Tarjeta y realiza un consumo de $15 como mínimo le devolvemos ese valor a través de un bono Supermaxi,
							canjeando con los puntos de Bienvenida en el Plan de Recompensas Austroclass que le estamos otorgando</i></li>

						</ul>
					</blockquote>
					</p>
					<p>
						<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 3</h4></strong>
					<blockquote style="font-size: 15px">
						<ul>
							<li><i>Y lo mejor de esto es que la Tarjeta no tiene ningún costo por mantenimiento mensual o anual ya que sólo cancelaría el valor de  $4.89 por el chip de seguridad.</i></li>
							<li><i>Le entregamos la oportunidad de disfrutar del Plan de Recompensas y Prestaciones al Exterior, sin costo alguno el primer año, para que pueda disfrutar de los beneficios de Austroclass.  Y si a partir del siguiente año no desea continuar con este beneficio simplemente, se lo cancelaría  y usted continúa con su Tarjeta sin pagar valor alguno.</i></li>

						</ul>
					</blockquote>
					</p>
					<p>
						¿Le parece que éstas 3 ofertas pueden ser una opción válida para usted?
					</p>
					<p>
						<strong>NO</strong>: Ver manejo de objeciones
					</p>
					<p>
						<strong>SI</strong>: Solo necesito validar que sus datos sean correctos, ¿Dónde le gustaría recibir su Tarjetas en su lugar de trabajo o domicilio?
					</p>
					<p>
						Después de llenar datos
					</p>
					<p>
						Esto sería todo, en aproximadamente 10 días se le hace entrega de la tarjeta en: (Dirección de Trabajo o Domicilio) por favor tenga en mano una copia de cédula, papeleta de votación, planilla de servicio básico actual (máximo de los últimos tres meses) donde conste la dirección que ingresamos en este momento
					</p>
					<p>
						Le comento también Sr <strong>${cliente.nombre}</strong> que adicional a esto el día de hoy tiene aprobada una tarjeta adicional con los mismos beneficios, que puede otorgar a cualquier persona solo necesito los nombres completos,  para quien le gustaría la tarjeta
					</p>
					<p>
						Esto sería todo recuerde no proporcionar información de su Tarjeta como el número, fecha de caducidad o código de seguridad.
					</p>
					<p>
						Cualquier duda o inquietud al 1800 22 87 87
					</p>

				</div>
				</div>
			</div>
		</g:if>

		<%--SCRIPT GOLD--%>
		<g:if test="${cliente.tipoMarca == 'VISA GOLD'}">
			<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="line"><h5><strong>SCRIPT VENTA TC BANCO DEL AUSTRO VISA-MASTERCARD GOLD</strong></h5></div>
						<p>Buenos días me comunica por favor con el Sr(a) (ita) <strong>${cliente.nombre}</strong>? mucho gusto le saluda <strong>${session?.user?.nombre}</strong>  del Banco del Austro.</p>
						<p>
							En días anteriores nos comunicamos con usted para poner a su consideración a Tarjeta Visa-MasterCard Gold.
							El Banco considerando la importancia de tenerle a usted como uno de nuestros clientes preferentes,
							ha mejorado la propuesta inicial con beneficios que estamos seguros será de mucho interés para usted
						</p>
						<p>
							Me permite 30 segundos, le comento de lo qué se trata y usted decide si continuamos la conversación, ¿le parece?
						</p>
						<p>
							<strong>NO</strong>: ¿Cuándo sería un buen momento para poder llamarle? Gestionar en agenda
						</p>
						<p>
							<strong>SI</strong>: Realizar las siguientes 3 Ofertas de Valor para el cliente
						</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 1</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Cómo le habíamos mencionado anteriormente, tenemos aprobada una Visa/MasterCard Gold con un cupo de <strong>${cliente.cupo}</strong> Esta Oferta se mantiene.</i></li>
							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 2</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Además su Tarjeta estaría acompañada de un Bono Supermaxi de $20 (VEINTE DÓLARES) que representan los 5.000 puntos de Bienvenida, en nuestro Plan de Recompensas Austroclass. Que le entregamos con la aceptación de la Tarjeta y con su primer consumo mínimo del mismo valor del Bono $20 (VEINTE DÓLARES)</i></li>
								<li><i>Es decir si usted acepta la Tarjeta y realiza un consumo de $20 como mínimo le devolvemos ese valor a través de un bono Supermaxi, canjeando con los puntos de Bienvenida en el Plan de Recompensas Austroclass que le estamos otorgando</i></li>

							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 3</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Y lo mejor de esto es que la Tarjeta no tiene ningún costo por mantenimiento mensual o anual ya que sólo cancelaría el valor de  $4.89 por el chip de seguridad.</i></li>
								<li><i>Le entregamos la oportunidad de disfrutar del Plan de Recompensas y Prestaciones al Exterior, sin costo alguno el primer año, para que pueda disfrutar de los beneficios de Austroclass.  Y si a partir del siguiente año no desea continuar con este beneficio simplemente, se lo cancelaría  y usted continúa con su Tarjeta sin pagar valor alguno.</i></li>

							</ul>
						</blockquote>
					</p>
						<p>
							¿Le parece que éstas 3 ofertas pueden ser una opción válida para usted?
						</p>
						<p>
							<strong>NO</strong>: Ver manejo de objeciones
						</p>
						<p>
							<strong>SI</strong>: Solo necesito validar que sus datos sean correctos, ¿Dónde le gustaría recibir su Tarjetas en su lugar de trabajo o domicilio?
						</p>
						<p>
							Después de llenar datos
						</p>
						<p>
							Esto sería todo, en aproximadamente 10 días se le hace entrega de la tarjeta en: (Dirección de Trabajo o Domicilio) por favor tenga en mano una copia de cédula, papeleta de votación, planilla de servicio básico actual (máximo de los últimos tres meses) donde conste la dirección que ingresamos en este momento
						</p>
						<p>
							Le comento también Sr <strong>${cliente.nombre}</strong> que adicional a esto el día de hoy tiene aprobada una tarjeta adicional con los mismos beneficios, que puede otorgar a cualquier persona solo necesito los nombres completos,  para quien le gustaría la tarjeta
						</p>
						<p>
							Esto sería todo recuerde no proporcionar información de su Tarjeta como el número, fecha de caducidad o código de seguridad.
						</p>
						<p>
							Cualquier duda o inquietud al 1800 22 87 87
						</p>

					</div>
				</div>
			</div>
		</g:if>
	<%--SCRIPT PLAINUM--%>
		<g:if test="${cliente.tipoMarca == 'VISA PLATINUM' || cliente.tipoMarca == 'MASTERCARD PLATINUM'}">
			<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="line"><h5><strong>SCRIPT VENTA TC BANCO DEL AUSTRO VISA-MASTERCARD PLATINUM</strong></h5></div>
						<p>Buenos días me comunica por favor con el Sr(a) (ita) <strong>${cliente.nombre}</strong>? mucho gusto le saluda <strong>${session?.user?.nombre}</strong>  del Banco del Austro.</p>
						<p>
							En días anteriores nos comunicamos con usted para poner a su consideración la Tarjeta Visa/MasterCard Platinum.
							El Banco considerando la importancia de tenerle a usted como uno de nuestros clientes preferentes,
							ha mejorado la propuesta inicial con beneficios que estamos seguros será de mucho interés para usted
						</p>
						<p>
							Me permite 30 segundos, le comento de lo qué se trata y usted decide si continuamos la conversación, ¿le parece?
						</p>
						<p>
							<strong>NO</strong>: ¿Cuándo sería un buen momento para poder llamarle? Gestionar en agenda
						</p>
						<p>
							<strong>SI</strong>: Realizar las siguientes 3 Ofertas de Valor para el cliente
						</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 1</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Cómo le habíamos mencionado anteriormente, tenemos aprobada una TC: Visa/MasterCard Platinum con un cupo de <strong>${cliente.cupo}</strong> Esta Oferta se mantiene.</i></li>
							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 2</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Además su Tarjeta estaría acompañada de un Bono Supermaxi de $30 (TREINTA DÓLARES) que representan los 7.000 puntos de Bienvenida, en nuestro Plan de Recompensas Austroclass. Que le entregamos con la aceptación de la Tarjeta y con su primer consumo mínimo del mismo valor del Bono ($30 TREINTA DÓLARES)</i></li>
								<li><i>Es decir si usted acepta la Tarjeta y realiza un consumo de $30 como mínimo le devolvemos ese valor a través de un bono Supermaxi, canjeando sus puntos de Bienvenida en el Plan de Recompensas Austroclass que le estamos otorgando.</i></li>

							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 3</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Y lo mejor de esto es que la Tarjeta no tiene ningún costo por mantenimiento mensual o anual ya que sólo cancelaría el valor de  $4.89 por el chip de seguridad.</i></li>
								<li><i>Le entregamos la oportunidad de disfrutar del Plan de Recompensas y Prestaciones al Exterior, sin costo alguno el primer año, para que pueda disfrutar de los beneficios de Austroclass.  Y si a partir del siguiente año no desea continuar con este beneficio simplemente, se lo cancelaría  y usted continúa con su Tarjeta sin pagar valor alguno.</i></li>

							</ul>
						</blockquote>
					</p>
						<p>
							¿Le parece que éstas 3 ofertas pueden ser una opción válida para usted?
						</p>
						<p>
							<strong>NO</strong>: Ver manejo de objeciones
						</p>
						<p>
							<strong>SI</strong>: Solo necesito validar que sus datos sean correctos, ¿Dónde le gustaría recibir su Tarjetas en su lugar de trabajo o domicilio?
						</p>
						<p>
							Después de llenar datos
						</p>
						<p>
							Esto sería todo, en aproximadamente 10 días se le hace entrega de la tarjeta en: (Dirección de Trabajo o Domicilio) por favor tenga en mano una copia de cédula, papeleta de votación, planilla de servicio básico actual (máximo de los últimos tres meses) donde conste la dirección que ingresamos en este momento
						</p>
						<p>
							Le comento también Sr <strong>${cliente.nombre}</strong> que adicional a esto el día de hoy tiene aprobada una tarjeta adicional con los mismos beneficios, que puede otorgar a cualquier persona solo necesito los nombres completos,  para quien le gustaría la tarjeta
						</p>
						<p>
							Esto sería todo recuerde no proporcionar información de su Tarjeta como el número, fecha de caducidad o código de seguridad.
						</p>
						<p>
							Cualquier duda o inquietud al 1800 22 87 87
						</p>

					</div>
				</div>
			</div>
		</g:if>

	<%--SCRIPT SIGNATURE--%>
		<g:if test="${cliente.tipoMarca == 'VISA SIGNATURE'}">
			<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="line"><h5><strong>SCRIPT VENTA TC BANCO DEL AUSTRO VISA SIGNATURE</strong></h5></div>
						<p>Buenos días me comunica por favor con el Sr(a) (ita) <strong>${cliente.nombre}</strong>? mucho gusto le saluda <strong>${session?.user?.nombre}</strong>  del Banco del Austro.</p>
						<p>
							En días anteriores nos comunicamos con usted para poner a su consideración la Tarjeta Visa Signature.
							El Banco considerando la importancia de tenerle a usted como uno de nuestros clientes preferentes,
							ha mejorado la propuesta inicial con beneficios que estamos seguros será de mucho interés para usted
						</p>
						<p>
							Me permite 30 segundos, le comento de lo qué se trata y usted decide si continuamos la conversación, ¿le parece?
						</p>
						<p>
							<strong>NO</strong>: ¿Cuándo sería un buen momento para poder llamarle? Gestionar en agenda
						</p>
						<p>
							<strong>SI</strong>: Realizar las siguientes 3 Ofertas de Valor para el cliente
						</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 1</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Cómo le habíamos mencionado anteriormente, tenemos aprobada una TC: Visa/MasterCard con un cupo de <strong>${cliente.cupo}</strong> Esta Oferta se mantiene.</i></li>
							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 2</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Además su Tarjeta estaría acompañada de un Bono Supermaxi de $40 (CUARENTA DÓLARES) que representan los 9.000 puntos de Bienvenida, en nuestro Plan de Recompensas Austroclass. Que le entregamos con la aceptación de la Tarjeta y con su primer consumo mínimo del mismo valor del Bono ($40 CUARENTA DÓLARES).</i></li>
								<li><i>Es decir si usted acepta la Tarjeta y realiza un consumo de $40 como mínimo le devolvemos ese valor a través de un bono Supermaxi, canjeando con los puntos de Bienvenida en el Plan de Recompensas Austroclass que le estamos otorgando.</i></li>

							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 3</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Y lo mejor de esto es que la Tarjeta no tiene ningún costo por mantenimiento mensual o anual ya que sólo cancelaría el valor de  $4.89 por el chip de seguridad.</i></li>
								<li><i>Le entregamos la oportunidad de disfrutar del Plan de Recompensas y Prestaciones al Exterior, sin costo alguno el primer año, para que pueda disfrutar de los beneficios de Austroclass.  Y si a partir del siguiente año no desea continuar con este beneficio simplemente, se lo cancelaría  y usted continúa con su Tarjeta sin pagar valor alguno.</i></li>

							</ul>
						</blockquote>
						</p>

						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 4</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Finalmente le entrega la Tarjeta Priority Pass sin costo alguno con 4 accesos gratuitos.</i></li>
							</ul>
						</blockquote>
						</p>

						<p>
							¿Le parece que éstas 4 ofertas pueden ser una opción válida para usted?
						</p>
						<p>
							<strong>NO</strong>: Ver manejo de objeciones
						</p>
						<p>
							<strong>SI</strong>: Solo necesito validar que sus datos sean correctos, ¿Dónde le gustaría recibir su Tarjetas en su lugar de trabajo o domicilio?
						</p>
						<p>
							Después de llenar datos
						</p>
						<p>
							Esto sería todo, en aproximadamente 10 días se le hace entrega de la tarjeta en: (Dirección de Trabajo o Domicilio) por favor tenga en mano una copia de cédula, papeleta de votación, planilla de servicio básico actual (máximo de los últimos tres meses) donde conste la dirección que ingresamos en este momento
						</p>
						<p>
							Le comento también Sr <strong>${cliente.nombre}</strong> que adicional a esto el día de hoy tiene aprobada una tarjeta adicional con los mismos beneficios, que puede otorgar a cualquier persona solo necesito los nombres completos,  para quien le gustaría la tarjeta
						</p>
						<p>
							Esto sería todo recuerde no proporcionar información de su Tarjeta como el número, fecha de caducidad o código de seguridad.
						</p>
						<p>
							Cualquier duda o inquietud al 1800 22 87 87
						</p>

					</div>
				</div>
			</div>
		</g:if>

	<%--SCRIPT INFINITE--%>
		<g:if test="${cliente.tipoMarca == 'VISA INFINITE' || cliente.tipoMarca == 'MASTERCARD BLACK'}">
			<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="line"><h5><strong>SCRIPT VENTA TC BANCO DEL AUSTRO VISA INFINITE- MASTERCARD BLACK</strong></h5></div>
						<p>Buenos días me comunica por favor con el Sr(a) (ita) <strong>${cliente.nombre}</strong>? mucho gusto le saluda <strong>${session?.user?.nombre}</strong>  del Banco del Austro.</p>
						<p>
							En días anteriores nos comunicamos con usted para poner a su consideración la Tarjeta Visa Infinite-MasterCard Black.
							El Banco considerando la importancia de tenerle a usted como uno de nuestros clientes preferentes,
							ha mejorado la propuesta inicial con beneficios que estamos seguros será de mucho interés para usted
						</p>
						<p>
							Me permite 30 segundos, le comento de lo qué se trata y usted decide si continuamos la conversación, ¿le parece?
						</p>
						<p>
							<strong>NO</strong>: ¿Cuándo sería un buen momento para poder llamarle? Gestionar en agenda
						</p>
						<p>
							<strong>SI</strong>: Realizar las siguientes 3 Ofertas de Valor para el cliente
						</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 1</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Cómo le habíamos mencionado anteriormente, tenemos aprobada una TC: Visa Infinite MasterCard Black con un cupo de <strong>${cliente.cupo}</strong> Esta Oferta se mantiene.</i></li>
							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 2</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Además su Tarjeta estaría acompañada de un Bono Supermaxi de $50 (CINCUENTA DÓLARES) que representan los 11.000 puntos de Bienvenida, en nuestro Plan de Recompensas Austroclass. Que le entregamos con la aceptación de la Tarjeta y con su primer consumo mínimo del mismo valor del Bono ($50 CINCUENTA DÓLARES).</i></li>
								<li><i>Es decir si usted acepta la Tarjeta y realiza un consumo de $50 como mínimo le devolvemos ese valor a través de un bono Supermaxi, canjeando con los puntos de Bienvenida en el Plan de Recompensas Austroclass que le estamos otorgando.</i></li>

							</ul>
						</blockquote>
					</p>
						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 3</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Y lo mejor de esto es que la Tarjeta no tiene ningún costo por mantenimiento mensual o anual ya que sólo cancelaría el valor de  $4.89 por el chip de seguridad.</i></li>
								<li><i>Le entregamos la oportunidad de disfrutar del Plan de Recompensas y Prestaciones al Exterior, sin costo alguno el primer año, para que pueda disfrutar de los beneficios de Austroclass.  Y si a partir del siguiente año no desea continuar con este beneficio simplemente, se lo cancelaría  y usted continúa con su Tarjeta sin pagar valor alguno.</i></li>

							</ul>
						</blockquote>
					</p>

						<p>
							<strong> <h4 style="color: #11265b; font-size: 16px">OFERTA 4</h4></strong>
						<blockquote style="font-size: 15px">
							<ul>
								<li><i>Finalmente le entrega la Tarjeta Priority Pass sin costo alguno con 4 accesos gratuitos.</i></li>
							</ul>
						</blockquote>
					</p>

						<p>
							¿Le parece que éstas 4 ofertas pueden ser una opción válida para usted?
						</p>
						<p>
							<strong>NO</strong>: Ver manejo de objeciones
						</p>
						<p>
							<strong>SI</strong>: Solo necesito validar que sus datos sean correctos, ¿Dónde le gustaría recibir su Tarjetas en su lugar de trabajo o domicilio?
						</p>
						<p>
							Después de llenar datos
						</p>
						<p>
							Esto sería todo, en aproximadamente 10 días se le hace entrega de la tarjeta en: (Dirección de Trabajo o Domicilio) por favor tenga en mano una copia de cédula, papeleta de votación, planilla de servicio básico actual (máximo de los últimos tres meses) donde conste la dirección que ingresamos en este momento
						</p>
						<p>
							Le comento también Sr <strong>${cliente.nombre}</strong> que adicional a esto el día de hoy tiene aprobada una tarjeta adicional con los mismos beneficios, que puede otorgar a cualquier persona solo necesito los nombres completos,  para quien le gustaría la tarjeta
						</p>
						<p>
							Esto sería todo recuerde no proporcionar información de su Tarjeta como el número, fecha de caducidad o código de seguridad.
						</p>
						<p>
							Cualquier duda o inquietud al 1800 22 87 87
						</p>

					</div>
				</div>
			</div>
		</g:if>

              <input type="hidden" name="idCliente" value="${cliente.id}" />
              <div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">

                  <div class="col-lg-4 col-md-6 col-xs-12 form-group">
                      <label>Estado Gestion</label>
                      <span class="required-indicator">*</span>
                      <g:select class="form-control" name="status" id="status"
                                from="${callcenter.Estado.list()}" optionKey="id"
                                optionValue="${{it.nombre	}}"
                                noSelection="${['': '-- Seleccione --']}" />
                  </div>

                  <div id="subStatusDiv" class="col-lg-4 col-md-6 col-xs-12 form-group">
                      <label>Subestado Gestion</label>
                      <span class="required-indicator">*</span>
                      <g:select class="form-control" name="substatus" id="substatus" from="" noSelection="${['': '-- Seleccione --']}" />
                  </div>

                  <div id="subSubStatusDiv" class="col-lg-4 col-md-6 col-xs-12 form-group">
                      <label>Sub subestado</label>
                      <span class="required-indicator">*</span>
                      <g:select id="subSubStatus" class="form-control" name="subSubStatus" from=""></g:select>
                  </div>

                  <div id="motivosSubSubEstadosDiv" class="col-lg-4 col-md-6 col-xs-12 form-group">
                      <label>Motivos SubEstados</label>
                      <span class="required-indicator">*</span>
                      <g:select id="motivosSubSubEstados" class="form-control" name="motivosSubSubEstados" from="" optionKey="id" noSelection="${['': '-- Seleccione --']}" ></g:select>
                  </div>

                  <div id="recallDiv">
                      <div class="col-lg-4 col-md-6 col-xs-12 form-group">
                          <label>Agendamiento</label>
                          <span class="required-indicator">*</span>
                          <g:select class="form-control" name="agendamiento" id="agendamiento" from="${['AGENDAR PARA MI':'AGENDAR PARA MI', 'AGENDAR PARA CUALQUIERA':'AGENDAR PARA CUALQUIERA']}" optionKey="key"
                                    optionValue="value"
                                    noSelection="${['': '-- Seleccione --']}" />
                      </div>
                      <%--<div class="col-lg-4 col-md-6 form-group">
                          <label>Fecha Rellamada</label>
                          <span class="required-indicator">*</span>
                          <div class='input-group time' id='datetimepicker1'>
                              <input id="recall" name="recall" type='text' class="form-control" />
                              <span class="input-group-addon">
                                  <span class="glyphicon glyphicon-calendar"></span>
                              </span>
                          </div>
                      </div>--%>
				<div class="col-lg-4 col-md-6 form-group">
					<label>Fecha Rellamada</label>
					<span class="required-indicator">*</span>
					<g:textField id="recall" name="recall" class="recall form-control"/>
				</div>

			</div>

			<div id="telefonoContactadoDiv" class="col-lg-4 col-md-6 form-group">
				<label style="color: red">Teléfono Contactado</label>
				<span class="required-indicator">*</span>
				<g:textField maxlength="10" minlength="9" id="telefonoContactado" name="telefonoContactado" class="form-control"/>
			</div>
		</div>
	<%--	<div id="scriptContactado" style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<p>
					<strong>PARA GARANTIZAR NUESTROS NIVELES DE CALIDAD ESTA CONVERSACIÓN ESTÁ SIENDO GRABADA.
						<span style="color: red">(MANDATORIO)</span>
					</strong>
				</p>
				<p>
					Sr/Sra/Srta ${cliente.nombre} el motivo de mi llamada es para felicitarle ya que tenemos pre aprobada
					la emisión de una tarjeta de crédito ${cliente.tipoProducto}, la misma que goza de muchos beneficios como:
					<ul>
						<li>Avances en efectivo</li>
						<li>Puede realizar compras en establecimientos afiliados con su tarjeta, según su saldo disponible,
						dentro y fuera del país.</li>
						<li>Diferido de Consumos Corrientes y del Exterior desde 3 hasta 36 meses plazo</li>
					</ul>
				</p>
				<p>
					<strong>¿QUÉ LE PARECE ESTA EXCELENTE NOTICIA? <span style="color: red">(MANDATORIO)</span></strong>
				</p>
			</div>
		</div>--%>
		<div style="border-radius: 30px" id="managementData" class="panel panel-default col-lg-12 col-md-12 col-xs-12">

                <div class="col-lg-12 col-md-12 col-xs-12">
                    <div class="line"><h5>Productos y cambio de producto</h5></div>
                    <div class="form-group col-lg-3 col-md-6 col-xs-12">
                        <label>Producto Aceptado</label>
                        <span class="required-indicator"> *</span>
                        <%--<g:select class="form-control" optionKey="key" optionValue="value" name="marcasTarjetas" from="${callcenter.Clientes.getMarcas()}" />--%>
                        <g:select class="form-control" optionValue="value"  id="productoAceptado" name="productoAceptado" from="${utilitarios.Util.getProductos(cliente.id)}" noSelection="${['': '-- Seleccione --']}" />
                    </div>

                    <div class="form-group col-lg-3 col-md-6 col-xs-12">
                        <label>Cambio de Producto</label>
                        <span class="required-indicator"> *</span>
                        <g:select class="form-control" name="aceptaCambioProducto" from="${['SI', 'NO']}" noSelection="${['': '-- Seleccione --']}" />
                    </div>
                    <div id="divMarcas">
                        <%--<div class="form-group col-lg-3 col-md-6 col-xs-12">
                            <label>Marca</label>
                            <span class="required-indicator"> *</span>
                            <%--<g:select class="form-control" optionKey="key" optionValue="value" name="marcasTarjetas" from="${callcenter.Clientes.getMarcas()}" />--%>
                        <%--<g:select class="form-control" optionValue="value" name="marcasTarjetas" from="" noSelection="${['': '-- Seleccione --']}" />
                    </div>--%>
                        <div class="form-group col-lg-3 col-md-6 col-xs-12">
                            <label>Productos Disponibles</label>
                            <span class="required-indicator"> *</span>
                            <g:select class="form-control" id="tipoTarjeta" name="tipoTarjeta" optionKey="nombre" noSelection="${['': '-- Seleccione --']}" from="" />
                        </div>
                    </div>
                </div>
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Datos Entrega</h5></div>
					<%--<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Pruebita</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="tags" id="tags"/>
					</div>--%>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Lugar Entrega Tarjeta</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="lugarEntrega" from="${['DIRECCION DE RESIDENCIA', 'DIRECCION DE TRABAJO']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Horario Entrega Tarjeta</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="horarioEntrega" from="${['MAÑANA', 'TARDE']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Lugar Entrega Estado Cuenta</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="lugarEntregaECuenta" from="${['DIRECCION DE RESIDENCIA', 'DIRECCION DE TRABAJO']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
				</div>

				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Datos del Cliente</h5></div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Tipo Identificación</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="tipoIdentificacion" from="${['CED': 'CEDULA', 'CEX': 'CODIGO EXTRANJERIA', 'PAS': 'PASAPORTE', 'RUC': 'RUC']}"  value="CEDULA" optionKey="key" optionValue="value" disabled="true"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Identificación</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="cedulaVerificada" value="${cliente?.identificacion}" maxlength="10" disabled="true"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Apellido 1</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="apellido1" maxlength="25" value="${apellido1}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Apellido 2</label>
						<g:textField class="form-control" name="apellido2" maxlength="25" value="${apellido2}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre 1</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="nombreVerificado" maxlength="20" value="${nombre1}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre 2</label>
						<g:textField class="form-control" name="nombre2Verificado" maxlength="30" value="${nombre2}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nacionalidad</label>
						<span class="required-indicator"> *</span>
						<g:select name="nacionalidad" class="form-control" optionKey="codigo" optionValue="nombre" from="${callcenter.Nacionalidad.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Sexo</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="sexo" optionKey="key" optionValue="value" from="${['M':'MASCULINO', 'F':'FEMENINO']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Estado Civil</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="estadoCivil" name="estadoCivil" from="${['1': 'SOLTERO', '2': 'CASADO', '3' : 'DIVORCIADO', '4' : 'VIUDO', '5' : 'UNION LIBRE']}" noSelection="${['': '-- Seleccione --']}" optionKey="key" optionValue="value"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Fecha Nacimiento</label>
						<span class="required-indicator">*</span>
						<g:textField id="fechaNacimientoGestion" name="fechaNacimientoGestion" class="form-control datepicker pointer" onkeypress="return soloLetras(event)"/>
					</div>
						<div class="col-lg-4 col-md-6 col-xs-12 form-group">
                            <label>Nivel de Estudios</label>
                            <span class="required-indicator"> *</span>
                            <g:select class="form-control" name="nivelEstudios" optionKey="key" optionValue="value" from="${callcenter.Clientes.getNivelesEstudio()}" />
                        </div>
                        <div class="col-lg-4 col-md-6 col-xs-12 form-group">
                            <label>Profesión</label>
                            <span class="required-indicator"> *</span>
                            <g:select name="profesion" class="form-control" optionKey="codigo" optionValue="nombre" from="${callcenter.Profesion.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}"/>
                        </div>
						<div class="col-lg-4 col-md-6 col-xs-12 form-group">
							<label>Cargo</label>
							<span class="required-indicator"> *</span>
							<g:select name="cargo" class="form-control" optionKey="codigo" optionValue="nombre" from="${callcenter.Cargo.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}"/>
						</div>
						<div class="col-lg-4 col-md-6 col-xs-12 form-group">
							<label>Ocupación</label>
							<span class="required-indicator"> *</span>
							<g:select name="ocupacion" class="form-control" optionKey="codigo" optionValue="nombre" from="${callcenter.Ocupacion.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}"/>
						</div>
                        <div class="col-lg-4 col-md-6 col-xs-12 form-group">
                            <label>Cargas Familiares</label>
                            <span class="required-indicator"> *</span>
                            <g:textField class="form-control" name="cargasFamiliares" maxlength="2" />
                        </div>
                        <div class="col-lg-4 col-md-6 col-xs-12 form-group">
                            <label>Nombre Impreso</label>
                            <span class="required-indicator"> *</span>
                            <g:textField class="form-control" name="nombreImpreso" value="${cliente.nombre}" disabled="true"/>
                        </div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Tipo Actividad</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="tipoActividad" name="tipoActividad" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="${callcenter.TipoActividad.list(sort: "nombre", order: "asc")}" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Actividad Económica</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="actividadEconomica" name="actividadEconomica" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
				</div>


				<div id="datosConyugue" class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Datos del Conyugue</h5></div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Tipo Identificación</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="tipoIdentificacionConyuge" from="${['CED': 'CEDULA', 'CEX': 'CODIGO EXTRANJERIA', 'PAS': 'PASAPORTE', 'RUC': 'RUC']}" value="CEDULA" optionKey="key" optionValue="value" disabled="true"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Identificación</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="identificacionConyuge"/>
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Apellido 1</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="apellido1Conyuge"/>
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Apellido 2</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="apellido2Conyuge"/>
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre 1</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="nombre1Conyuge"/>
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre 2</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="nombre2Conyuge"/>
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>País</label>
						<span class="required-indicator"> *</span>
						<g:select name="paisConyuge" class="form-control" optionKey="codigo" optionValue="nombre" from="${callcenter.Pais.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Género</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="generoConyuge" optionKey="key" optionValue="value" from="${['M':'MASCULINO', 'F':'FEMENINO']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Fecha Nacimiento</label>
						<span class="required-indicator">*</span>
						<g:textField id="fechaNacimientoConyuge" name="fechaNacimientoConyuge" class="form-control datepicker pointer" onkeypress="return soloLetras(event)"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Estado Civil</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="estadoCivilConyuge" name="estadoCivilConyuge" from="${['1': 'SOLTERO', '2': 'CASADO', '3' : 'DIVORCIADO', '4' : 'VIUDO', '5' : 'UNION LIBRE']}" noSelection="${['': '-- Seleccione --']}" optionKey="key" optionValue="value"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>País Nacimiento</label>
						<span class="required-indicator"> *</span>
						<g:select name="paisNacimientoConyuge" class="form-control" optionKey="codigo" optionValue="nombre" from="${callcenter.Pais.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}"/>
					</div>
				</div>


				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Dirección Domicilio</h5></div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Provincia Domicilio</label>
						<span class="required-indicator"> *</span>
						<%--<g:select class="form-control" id="provinciaDomicilio" name="provinciaDomicilio" optionKey="${{it.id+'-'+it.prefijo+'-'+it.nombre}}" noSelection="${['': '-- Seleccione --']}" from="${callcenter.Provincia.list(sort: "nombre", order: "asc")}" optionValue="${{it.nombre}}"/>--%>
						<g:select class="form-control" id="provinciaDomicilio" name="provinciaDomicilio" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="${callcenter.Provincia.list(sort: "nombre", order: "asc")}" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Ciudad Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="ciudadDomicilio" name="ciudadDomicilio" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Sector Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="sectorDomicilio" name="sectorDomicilio" optionKey="${{it.id}}" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Barrio Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="barrioDomicilio" name="barrioDomicilio" value=""/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Calle Principal Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="callePrincipalDomicilio" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nomenclatura Domicilio</label>
						<g:textField class="form-control" name="numeracionDomicilio" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Calle Transversal Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="calleTransversalDomicilio" />
					</div>
					<div class="form-group col-lg-12 col-md-12 col-xs-12">
						<label>Referencia Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:textArea class="form-control" name="referenciaDomicilio" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Tipo Lugar</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="tipoPredioDomicilio" from="${['CASA', 'EDIFICIO', 'DEPARTAMENTO', 'CONJUNTO']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Sucursal</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="sucursal" from="${callcenter.Sucursal.findAllByIsActive(true)}" noSelection="${['': '-- Seleccione --']}" optionKey="id" optionValue="nombre" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Oficina</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" optionKey="id" name="oficina" from="" noSelection="${['':'-- Seleccione --']}" />
					</div>
				</div>

				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Datos Trabajo</h5></div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Relación Laboral</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" optionKey="key" optionValue="value" name="relacionLaboral" from="${['01': 'Dependiente', '02': 'Independiente']}" noSelection="${['': '-- Seleccione --']}" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre Empresa</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="nombreEmpresa" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Inicio Trab Actual</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control datepicker pointer" name="fechaInicioTrabajoActual" onkeypress="return soloLetras(event)" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Inicio Trab Anterior</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control datepicker pointer" name="fechaInicioTrabajoAnterior" onkeypress="return soloLetras(event)" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Fin Trab Anterior</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control datepicker pointer" name="fechaFinTrabajoAnterior" onkeypress="return soloLetras(event)" />
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Provincia Trabajo</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="provinciaTrabajo" name="provinciaTrabajo"  optionKey="id" noSelection="${['': '-- Seleccione --']}" from="${callcenter.Provincia.list(sort: "nombre", order: "asc")}" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Ciudad Trabajo</label>
						<g:select class="form-control" id="ciudadTrabajo" name="ciudadTrabajo" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Sector Trabajo</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="sectorTrabajo" name="sectorTrabajo" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Barrio Trabajo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="barrioTrabajo" name="barrioTrabajo" value=""/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Calle Principal Trabajo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="callePrincipalTrabajo" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nomenclatura Trabajo</label>
						<g:textField class="form-control" name="numeracionTrabajo" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Calle Transversal Trabajo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="calleTransversalTrabajo" />
					</div>
					<div class="form-group col-lg-12 col-md-6 col-xs-12">
						<label>Referencia Trabajo</label>
						<span class="required-indicator"> *</span>
						<g:textArea class="form-control" name="referenciaTrabajo" />
					</div>
				<%--	<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Tipo Lugar</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="tipoPredioTrabajo" from="${['CASA', 'EDIFICIO', 'DEPARTAMENTO', 'CONJUNTO']}" noSelection="${['': '-- Seleccione --']}" />
					</div>--%>
				</div>

				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Datos de Contacto</h5></div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Teléfono Convencional Casa <%--<span id="prefijoCasa"></span>--%></label>
						<g:textField maxlength="9" minlength="9" class="form-control" name="telefonoCasa" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Teléfono Convencional Trabajo <%--<span id="prefijoTrabajo"></span>--%></label>
						<g:textField maxlength="9" minlength="9" class="form-control" name="telefonoTrabajo" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Teléfono Celular</label>
						<span class="required-indicator"> *</span>
						<g:textField maxlength="10" minlength="10" class="form-control" name="celular" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Email Personal</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="emailPersonal" name="emailPersonal"/>
					</div>
				</div>

				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Ingresos</h5></div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Sueldo Propio</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="sueldoIngresos" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Comisiones</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="comisiones" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Otros Ingresos</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="otrosIngresos" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Ingresos Conyugue</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="ingresosConyugue" />
					</div>
					<div class="line"><h5>Egresos</h5></div>
					<div class="col-lg-12 col-md-12 col-xs-12">
						<div class="form-group col-lg-6">
							<a href="#" class="btn btn-warning col-lg-4" id="calcularLnk" onclick="return false;">Calcular Egresos</a>
							<div id="resultadoCalculo" class="col-lg-8"></div>
						</div>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Alimentacion</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="alimentacion" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Arriendo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="arriendo" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Cuota Arriendo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="cuotaArriendo" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Servicios Básicos</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="gastosBasicos" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Vestimenta</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="vestimenta" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Educacion</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="educacion" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Salud</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="salud" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Cuota Vehículo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="cuotaVehiculo" />
					</div>

					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Origen de Ingresos</label>
						<g:select class="form-control" optionKey="key" optionValue="value" name="origenIngresos" from="${callcenter.Clientes.getOrigenesIngresos()}" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Rango de Ingresos</label>
						<g:select class="form-control" optionKey="key" optionValue="value" name="rangoIngresos" from="${callcenter.Clientes.getRangosIngresos()}" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Patrimonio</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="patrimonio" maxlength="10"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Tipo Vivienda</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" optionKey="key" optionValue="value" name="tipoVivienda" from="${callcenter.Clientes.getTiposVivienda()}" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Valor Vivienda</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="valorVivienda" maxlength="10"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Fecha Inicio Residencia</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control datepicker pointer" name="fechaInicioResidencia" onkeypress="return soloLetras(event)"/>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Activos y Pasivos</h5></div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Valor Activo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="valorActivo" name="valorActivo" maxlength="8" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Valor Pasivo</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="valorPasivo" name="valorPasivo" maxlength="8"/>
					</div>
				</div>
				<div class="col-lg-12 col-md-12 col-xs-12">
					<div class="line"><h5>Referencia Personal</h5></div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Tipo Identificación Referencia</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" name="tipoIdentificacionRefPersonal" from="${['CED': 'CEDULA', 'CEX': 'CODIGO EXTRANJERIA', 'PAS': 'PASAPORTE', 'RUC': 'RUC']}" value="CEDULA" optionKey="key" optionValue="value" disabled="true"/>
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Identificación</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="identificacionRefPersonal" maxlength="10" minlength="10" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Apellido 1</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="apellido1RefPersonal"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Apellido 2</label>
						<g:textField class="form-control" name="apellido2RefPersonal"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre 1</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="nombre1RefPersonal"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nombre 2</label>
						<g:textField class="form-control" name="nombre2RefPersonal"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Provincia Referencia</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="provinciaReferencia" name="provinciaReferencia"  optionKey="id" noSelection="${['': '-- Seleccione --']}" from="${callcenter.Provincia.list(sort: "nombre", order: "asc")}" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Ciudad Referencia</label>
						<g:select class="form-control" id="ciudadReferencia" name="ciudadReferencia" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Calle Principal Referencia</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="callePrincipalRefPersonal" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Nomenclatura Referencia</label>
						<g:textField class="form-control" name="numeracionDomicilioRef" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Calle Transversal Referencia</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" name="calleSecundariaRefPersonal" />
					</div>
					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Teléfono Ref Personal</label>
						<g:textField class="form-control" name="telefonoRefPersonal" maxlength="10"/>
					</div>
					<div class="col-lg-12 col-md-12 col-xs-12 form-group">
						<label>Referencia</label>
						<span class="required-indicator"> *</span>
						<g:textArea class="form-control" name="referenciaRefPersonal" />
					</div>

					<div class="col-lg-4 col-md-6 col-xs-12 form-group">
						<label>Parentesco Ref Personal</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" from="${callcenter.Clientes.getTiposParientes()}" name="parentescoRefPersonal" optionValue="value" optionKey="key"/>
					</div>
				</div>



			<%--<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
				<div class="col-lg-12 col-md-12 col-xs-12">
					<strong>DOCUMENTOS PARA ENTREGA DE LA TARJETA DE CRÉDITO <span style="color: red"> (MANDATORIO)</span></strong>
					<p>Tiempo de entrega 10 días hábiles</p>
					<p>El cliente debe tener los siguientes documentos:</p>
					<ul>
						<li>Copia de la cédula de identidad legible</li>
						<li>Copia papeleta de votación</li>
						<li>Copias de cartilla servicio básico de los 3 últimos meses</li>
						<li>Firmar documento habilitante</li>
					</ul>
					<p>Gracias por su atención, y recuerde que somos el banco líder en que tenga una buena tarde/día/noche</p>
				</div>
			</div>--%>

		</div>
		<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="form-group col-lg-12 col-md-12 col-xs-12">
				<label>Observaciones</label>
				<g:textArea class="form-control" name="observaciones" value="${cliente?.observaciones}"/>
			</div>
		</div>
		<div class="col-lg-12 col-md-12 col-xs-12">
			<g:submitButton id="send" name="btnGuardarCliente" class="btn btn-success" value="Guardar Gestión" />
		</div>
	</g:form>
</div>
<asset:javascript src="gestion/gestionCliente.js"/>
<asset:javascript src="usogeneral/datetimepicker.js" />
<asset:javascript src="usogeneral/customdatetimepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.min.js" />
<asset:javascript src="usogeneral/customdatepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.es.min.js" />

