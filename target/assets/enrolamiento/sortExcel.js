$(document).ready(function(){$("#overlay").hide();$("#set-columns").click(function(){var e=$(this).attr("data-source");var t=document.getElementById("fieldsTable");var n={};var r=[];for(var i=1,s;s=t.rows[i];i++){r[i-1]=s.cells[0].getElementsByTagName("select")[0]}for(var i in r){var o=r[i];var u=o.name;var a=o.selectedIndex;n[u]=a}$("#overlay").show();$.post(baseUrl+"/FuncionesAjax/uploadDBEnrolamiento",{data:JSON.stringify(n),path:e,dbName:$("#dbName").val()}).done(function(e){$("#overlay").hide();var t;if(e){t=e}else{t="La base no fue cargada correctamente"}showEndMessage(t,2,3)})})})