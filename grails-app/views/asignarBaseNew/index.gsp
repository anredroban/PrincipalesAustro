<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <asset:javascript src="asignarBaseNew/index.js" />
    <title>Tarjetas Austro - Asignar Base</title>
</head>

<body>

<g:form controller="asignarBaseNew" action="index">
    <div class="col-lg-6">
        <div class="form-group">
            <label>
                Agentes<span class="required-indicator">*</span>
            </label>
            <g:select class="form-control" name="consultants" size="35" id="consultants" required="true" multiple="multiple"
                      from="${utilitarios.Util.getOperDisponiblesAsignacion()}" optionKey="id"
                      optionValue="${{ it.nombre }}">
            </g:select>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Easy Code
                </div>
                <div class="panel-body">
                    <div class="form-group col-lg-6">
                        <label>
                            Desde
                        </label>
                        <g:textField class="form-control" name="desde"></g:textField>
                    </div>
                    <div class="form-group col-lg-6">
                        <label>
                            Hasta
                        </label>
                        <g:textField class="form-control" name="hasta"></g:textField>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Intentos
                </div>
                <div class="panel-body">
                    <div class="form-group col-lg-6">
                        <label>
                            Desde
                        </label>
                        <g:textField class="form-control" name="intentosDesde"></g:textField>
                    </div>
                    <div class="form-group col-lg-6">
                        <label>
                            Hasta
                        </label>
                        <g:textField class="form-control" name="intentosHasta"></g:textField>
                    </div>
                </div>
            </div>
        </div>

        <br/>
        <div class="col-lg-12">
            <br/>
            <div class="panel panel-default col-lg-12">
                <div class="form-group col-lg-12">
                    <label>
                        Base<span class="required-indicator">*</span>
                    </label>
                    <g:select class="form-control" name="dbName" id="dbName" required="true"
                              from="${utilitarios.Util.getNombresBase()}"
                              noSelection="['': '-Seleccione-']">
                    </g:select>
                </div>
                <div id="tipoRegDiv" class="form-group col-lg-12">
                    <label>
                        Tipo<span class="required-indicator">*</span>
                    </label>
                    <g:select class="form-control" name="tipoRegx" id="tipoRegx" required="true"
                              from="${['SIN GESTION', 'REGESTIONABLES', 'RELLAMADAS']}"
                              noSelection="['': '-Seleccione-']">
                    </g:select>
                </div>
                <div id="substatusRegDiv" class="form-group col-lg-12">
                    <label>
                        Subestados
                    </label>
                    <g:select class="form-control" name="subestadosRegestionables" id="subestadosRegestionables"
                              from="${utilitarios.Util.getSubestadosRegestionables()}"
                              noSelection="['': '-Seleccione-']" optionKey="name" optionValue="${{it.name}}">
                    </g:select>
                </div>
                <div class="form-group col-lg-12">
                    <a href="#" class="btn btn-warning col-lg-4" id="calcularLnk" onclick="return false;">Calcular Registros</a>
                    <div id="resultadoCalculo" class="col-lg-8"></div>
                </div>
                <div class="form-group col-lg-6">
                    <label>
                        Registros a Asignar<span class="required-indicator">*</span>
                    </label>
                    <g:textField required="true" class="form-control" name="allocationNumber"></g:textField>
                </div>
                <div class="form-group col-lg-6">
                    <label>
                        &nbsp;
                    </label>
                    <input class="form-control btn btn-primary" type="submit" value="Asignar" id="bt_assign" name="bt_assign">
                </div>
                <g:if test="${baseAsignada}">
                    <div class="col-lg-12">
                        <h2>${registrosAsignados} registros asignados</h2>
                    </div>
                </g:if>
            </div>
        </div>
    </div>
</g:form>
</body>
</html>