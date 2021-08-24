$(document).ready(function () {
    $("#overlay").hide();
    $("#set-columns").click(function () {
        var path = $(this).attr('data-source');
        var table = document.getElementById("fieldsTable");
        var dict = {};
        var selections = [];
        for (var i = 1, row; row = table.rows[i]; i++) {
            selections[i-1] = row.cells[0].getElementsByTagName("select")[0];
        }
        for (var i in selections) {
            var field = selections[i];
            var fieldName = field.name;
            var csvPos = field.selectedIndex;
            dict[fieldName] = csvPos;
        }
        $("#overlay").show();
        $.post(baseUrl + "/FuncionesAjax/uploadDB", {
            data:JSON.stringify(dict),
            path:path,
            dbName:$("#dbName").val()
        }).done(function(data){
            $("#overlay").hide();
            var message
            if(data){
                message = data;
            }else{
                message = "La base no fue cargada correctamente";
            }
            showEndMessage(message,2,3)
        });
    })
})