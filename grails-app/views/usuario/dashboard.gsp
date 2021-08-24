<!DOCTYPE html>
<%@page import="callcenter.Clientes"%>
<%@page import="com.pw.security.*"%>
<%@page import="utilitarios.Util"%>

<html>
	<head>		
		<meta name="layout" content="main"/>
		<title>Tarjetas Austro - Dashboard</title>
		<asset:javascript src="usogeneral/moment.min.js" />
		<asset:javascript src="usuario/dashboard.js" />
			
	</head>
	<body>
        <div class="container-fluid">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Dashboard <small></small>
                    </h1>
                    <ol class="breadcrumb">
                        <li class="active">
                            <i class="fa fa-dashboard"></i> Dashboard
                        </li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <a href="${createLink(uri:'#')}">
                    <div class="col-lg-6 col-md-6 col-xs-12 cuadro">
                        <div class="panel panel-personalizado">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-phone fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getContactabilidadMensual()}</div>
                                        <div>¡Contactabilidad Mensual!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                <a href="${createLink(uri:'#')}">
                    <div class="col-lg-6 col-md-6 col-xs-12 cuadro">
                        <div class="panel panel-personalizado">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-phone fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getContactabilidadDiaria()}</div>
                                        <div>¡Contactabilidad Diaria!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                <a href="${createLink(uri:'/usuario/')}">
                    <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-user fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getOperadoresLogueados()}</div>
                                        <div>¡Agentes!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>

                <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                        <div class="panel panel-red">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-check fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getContactados()}</div>
                                        <div>¡Contactados!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>


                <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                        <div class="panel panel-green">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-dollar fa-fw fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getCantidadCu1()}</div>
                                        <div>¡CU1!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
                <a href="${createLink(uri:'#')}">
                    <div class="col-lg-3 col-md-6 col-xs-12 cuadro">
                        <div class="panel panel-yellow">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-3">
                                        <i class="fa fa-credit-card fa-5x"></i>
                                    </div>
                                    <div class="col-xs-9 text-right">
                                        <div class="huge">${Util.getAExitosasMes()}</div>
                                        <div>¡Ventas CU1 en el mes!</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </a>

            </div>

            <div class="row">
                <div class="col-lg-8 col-md-6 col-xs-12">
                    <div class="panel panel-border-yellow">
                        <div class="panel-heading panel-body-yellow">
                            <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Exitosos por Hora</h3>
                            <a class="btn btn-yellow btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="panel panel-border-blue-u">
                        <div class="panel-heading panel-body-blue-u">
                            <h3 class="panel-title"><i class="fa fa-clock-o fa-fw"></i> Sesiones</h3>
                            <a class="btn btn-blue-u btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                        <tr>
                                            <th>Agente</th>
                                            <th>Inicio</th>
                                            <th>Fin</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <g:each in="${inicioSesion}">
                                            <tr>
                                                <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:'Activa'}</td>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12 col-md-6 col-xs-12">
                    <div class="panel panel-border-red1">
                        <div class="panel-heading panel-body-red1">
                            <h3 class="panel-title"><i class="fa fa-remove fa-fw"></i> Estatus</h3>
                            <a class="btn btn-red1 btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsivex">
                                <div class="col-lg-4" id="containerpieCu1" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                                <div class="col-lg-4" id="containerpieCu5" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                                <div class="col-lg-4" id="containerpieCu6" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                                <div class="col-lg-4" id="containerpieNu1" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                                <div class="col-lg-12" id="containerpieNu2" style="min-width: 310px; height: 350px; max-width: 600px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="panel panel-border-cyan">
                        <div class="panel-heading panel-body-cyan">
                            <h3 class="panel-title"><i class="fa fa-dollar fa-fw"></i> Exitosos por Agente</h3>
                            <a class="btn btn-cyan btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                        <tr>
                                            <th>Agente</th>
                                            <th>Exitosos</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <g:each in="${ventasPorUsuario}">
                                            <tr>
                                                <td>${it[0]}</td><td>${it[1]}</td>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-8 col-md-6 col-xs-12 col-xs-12">
                    <div class="panel panel-border-orange">
                        <div class="panel-heading panel-body-orange">
                            <h3 class="panel-title"><i class="fa fa-check fa-fw"></i> Contactados VS No Contactados</h3>
                            <a class="btn btn-orange btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div id="containerpiedd" style="min-width: 310px; height: 400px; max-width: 1000px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-8 col-md-6 col-xs-12 col-xs-12">
                    <div class="panel panel-border-black">
                        <div class="panel-heading panel-body-black">
                            <h3 class="panel-title" style="color: white"><i class="fa fa-table fa-fw"></i> Detalles Gestion</h3>
                            <a class="btn btn-black btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                        <tr>
                                            <th>AGENTE</th>
                                            <th>GESTIONADOS</th>
                                            <th>CONTACTADOS</th>
                                            <th>NO CONTACTADOS</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <g:each in="${tablaResult}">
                                            <tr>
                                                <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td>
                                            </tr>
                                        </g:each>
                                        <tr style="color: blue; background-color: #D3D3D3;">
                                            <td><strong>TOTAL</strong></td><td><strong>${totalGestionados}</strong></td><td><strong>${totalContactados}</strong></td><td><strong>${totalNoContactados}</strong></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-xs-12">
                    <div class="panel panel-border-rgb">
                        <div class="panel-heading panel-body-rgb">
                            <h3 class="panel-title"><i class="fa fa-bar-chart fa-fw"></i> Ventas por Provincia</h3>
                            <a class="btn btn-rgb btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div id="containerpieProv" style="min-width: 310px; height: 400px; max-width: 1000px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="panel panel-border-black">
                        <div class="panel-heading panel-body-black">
                            <h3 class="panel-title" style="color: white"><i class="fa fa-table fa-fw"></i> Detalles por Base</h3>
                            <a class="btn btn-black btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; margin: 0 auto">
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                        <tr>
                                            <th>NOMBRE BASE</th>
                                            <th>CONTACTADOS</th>
                                            <th>EXITOSOS</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <g:each in="${tablaResult1}">
                                            <tr>
                                                <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:0}</td>
                                            </tr>
                                        </g:each>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-lg-12 col-md-12">
                    <div class="panel panel-border-green2">
                        <div class="panel-heading panel-body-green2">
                            <h3 class="panel-title"><i class="fa fa-money fa-fw"></i> Venta de Servicios Diarios</h3>
                            <a class="btn btn-green2 btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div id="containerlinereport" style="min-width: 310px; height: 400px; max-width: 1000px; margin: 0 auto"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12 col-md-12 col-xs-12">
                    <div class="panel panel-border-blue-u">
                        <div class="panel-heading panel-body-blue-u">
                            <%--<h3 class="panel-title" style="color: #f4f6f7"><i class="fa fa-database fa-fw"></i> Exitos por Agente/Hora</h3>--%>
                            <h3 class="panel-title" style="color: #f4f6f7"><i class="fa fa-headphones fa-fw"></i><strong> TABLA DE TIEMPOS</strong></h3>
                            <a class="btn btn-warning btn-clickable pull-right" onclick="return false;" href="#">
                                <i class="fa fa-chevron-down"></i>
                            </a>
                        </div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <div style="min-width: 310px; height: 400px; max-width: 1200px; margin: 0 auto">
                                    <div class="form-group col-lg-12 col-md-12 col-xs-12">
                                        <label><i style="font-size: 18px;">Fecha y hora de actualización: </i></label>
                                        <span style="font-size: 18px; font-weight: bold; color: #006dba">${fechaHoraActualizacion}</span>
                                    </div>
                                    <table class="table table-bordered table-hover table-striped">
                                        <thead>
                                        <tr>
                                            <th colspan="1" style="text-align: center; font-size: 11px;">TOTALES DEL GRUPO</th>
                                            <th colspan="1" style="background-color: #868e96; text-align: center;">${totalEfectivosGrupo}</th>
                                            <th colspan="1" style="background-color: #868e96; text-align: center;">${totalMetaGrupo}</th>
                                            <th colspan="1" style="background-color: #868e96; text-align: center;">${totalFaltantesGrupo}</th>
                                            <th colspan="1" style="background-color: #868e96; text-align: center;">${totalPorcentajeMetaGrupo}</th>
                                            <th colspan="1" style="background-color: #868e96; text-align: center;">${totalPromedioHoraGrupo}</th>
                                            <th colspan="7" style="background-color: white;  border-collapse: collapse;"></th>
                                            <%--<th colspan="4" style="text-align: center; font-size: 11px;">TARIFA DE AVANCE INFORMATIVA</th>

                                            <th colspan="2" style="background-color: white; border-color: white"></th>--%>
                                        </tr>
                                        <tr>
                                            <th style="font-size: 11px" >Nombre Agente</th>
                                            <th style="font-size: 11px" >CU1</th>
                                            <th style="font-size: 11px" >Meta</th>
                                            <th style="font-size: 11px" >Faltantes</th>
                                            <th style="font-size: 11px" >% Meta</th>
                                            <th style="font-size: 11px" >Pro / Hora</th>
                                            <th style="font-size: 11px" >Inicio Conexión</th>
                                            <th style="font-size: 11px" >Fin Conexión</th>
                                            <th style="font-size: 11px" >Tiempo Conexión</th>
                                            <th style="font-size: 11px" >Tiempo Break</th>
                                            <th style="font-size: 11px" >Tiempo Muerto</th>
                                            <th style="font-size: 11px" >Tiempo Efectivo</th>
                                            <th style="font-size: 11px" >Observacion</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <g:each in="${tableTiemposAgentes}">
                                            <tr>
                                                <td style="font-size: 11px; font-weight: bold">${it[0]}</td><td>${it[1]?:0}</td><td>${it[2]?:0}</td><td>${it[3]?:0}</td><td>${it[4]?:0}</td>
                                                <td>${it[5]?:0}</td><td>${it[6]?:0}</td><td>${it[7]?:0}</td><td>${it[8]?:0}</td><td>${it[9]?:0}</td>
                                                <td>${it[10]?:0}</td><td>${it[11]?:0}</td><td>${it[12]?:0}</td>
                                                <%--<td>${it[13]?:0}</td><td style="color: black;text-align: center; font-weight: bold;">12</td><td style="color: green;text-align: center; font-weight: bold">${it[14]?:0}</td>
                                                <td style="color: black;text-align: center; font-weight: bold">12</td><td style="color: #0058a2;text-align: center; font-weight: bold">${it[15]?:0}</td>--%>
                                            </tr>
                                        </g:each>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>


        </div>
    <asset:javascript src="highcharts/data.js" />
    <asset:javascript src="highcharts/drilldown.js" />
    <asset:javascript src="highcharts/exporting.js" />
    <asset:javascript src="highcharts/highcharts.js" />
    <%--
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://code.highcharts.com/modules/data.js"></script>
    <script src="https://code.highcharts.com/modules/drilldown.js"></script>--%>
	</body>
</html>