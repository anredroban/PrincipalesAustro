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
    <g:form action = "loginAgentes" class="col-lg-12">
        <div class="form-group col-lg-12">
            <label>
                Fecha:
            </label>
            <input name="fechas" class="reportrange form-control">
        </div>
        <div class="col-lg-12">
            <g:submitButton class="btn btn-success" name = "btnEnviar" value = "Generar Reporte"></g:submitButton>
        </div>
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
                    <th style="font-size: 12px">FECHA</th>
                    <th style="font-size: 12px">AGENTE</th>
                    <th style="font-size: 12px">HORA MÍNIMA</th>
                    <th style="font-size: 12px">HORA MÁXIMA</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${tablaResult}">
                    <tr>
                        <td>${it[0]}</td><td>${it[1]}</td><td>${it[2]}</td><td>${it[3]}</td>
                    </tr>
                </g:each>
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