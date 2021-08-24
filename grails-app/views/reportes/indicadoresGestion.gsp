<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<!--This is what you should include-->
<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">
<meta name="layout" content="main"/>
<asset:javascript src="usogeneral/moment.min.js" />
<asset:javascript src="usogeneral/daterangepicker.js" />
<asset:stylesheet src="usogeneral/daterangepicker.css" />
<asset:javascript src="usogeneral/customdaterangepicker.js" />
<asset:stylesheet src="usogeneral/customdaterangepicker.css" />
<div class="container-fluid">
    <g:form action = "indicadoresGestion" class="col-lg-12">
        <div class="form-group col-lg-12">
            <label>
                Fecha:
            </label>
            <input name="fechas" class="reportrange form-control">
        </div>
        <div class="col-lg-12">
            <g:submitButton class="btn btn-success" name = "btnEnviar" value = "Generar Reporte"></g:submitButton>
        </div>
       <%-- <div class="col-lg-12 form-group">
            <label>
                Nombre Base:
            </label>
            <g:select class="form-control" name="nombreBase" multiple="true" size="10" required="" from="${utilitarios.Util.getNombresBase()}"></g:select>
        </div>--%>
    </g:form>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <g:if test="${visibilizar}">
        <g:link style="margin-bottom: 10px" class="btn btn-default exportarIndicadores" url="#" onclick="return false;">Exportar a Excel</g:link>
            <div id="dvData">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                    <tr>
                        <th style="font-size: 12px">Fecha</th>
                        <th style="font-size: 12px">Agente</th>
                        <th style="font-size: 12px">Gestionados</th>
                        <th style="font-size: 12px">Contactados</th>
                        <th style="font-size: 12px">No Desea</th>
                        <th style="font-size: 12px">Oferta TDC</th>
                        <%--<th style="font-size: 12px">Oferta PDP</th>
                        <th style="font-size: 12px">Oferta Credito</th>--%>
                        <th style="font-size: 12px">% No Desea</th>
                        <th style="font-size: 12px">% Efectividad TDC</th>
                        <%--<th style="font-size: 12px">% Efectividad PDP</th>
                        <th style="font-size: 12px">% Efectividad CRE</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${tablaResult}">
                        <tr>
                            <td>${it[10]?:0}</td><td>${it[0]}</td><td>${it[1]}</td><td>${it[2]?:0}</td><td>${it[7]?:0}</td><td>${it[4]?:0}</td><td>${it[9]?:0}</td><td>${it[8]?:0}</td><%--<td>${it[5]?:0}</td><td>${it[6]?:0}</td><td>${it[3]?:0}</td><td>${it[11]?:0}</td>--%>
                        </tr>
                    </g:each>
                    <tr style="color: blue; background-color: #D3D3D3;">
                        <td></td><td><strong>TOTAL</strong></td><td><strong>${totalGestionados}</strong></td><td><strong>${totalContactados}</strong></td><td><strong>${totalNoDeseaCU5}</strong></td><td><strong>${totalExitosos}</strong></td><%--<td><strong>${totalCruzadasCU2}</strong></td><td><strong>${totalCruzadasCU3}</strong></td>--%>
                        <td><strong>${totalPorcentajeEfectividad}</strong></td><td><strong>${totalPorcentajeContactabilidad}</strong></td><%--<td><strong>${totalPorcentajeEfectividadPdp}</strong></td><td><strong>${totalPorcentajeEfectividadCredito}</strong></td>--%>
                    </tr>
                    </tbody>
                </table>
            </div>
    </g:if>
    <script>
        $(".exportarIndicadores").click(function(){
            window.open('data:application/vnd.ms-excel,' + encodeURIComponent($('#dvData').html().toUpperCase().replace(/Ñ/g, 'N')));
            e.preventDefault();
        });

        function exportData(report_id){
            var blob = new Blob([document.getElementById(report_id).innerHTML], {
                type: "text/plain;charset=utf-8;"
            });
            saveAs(blob, "Report.xls");}

    </script>
</div>