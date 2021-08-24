$(document).ready(function(){

    //Recojo elementos del DOM
    $managementData = $("#managementData");

    $lugarEntrega = $("#lugarEntrega");
    $horarioEntrega = $("#horarioEntrega");

    $cedulaVerificada = $("#cedulaVerificada");
    $nombreVerificado = $("#nombreVerificado");

    $provinciaDomicilio = $("#provinciaDomicilio");
    $ciudadDomicilio = $("#ciudadDomicilio");
    $sectorDomicilio = $("#sectorDomicilio");
    $callePrincipalDomicilio = $("#callePrincipalDomicilio");
    $numeracionDomicilio = $("#numeracionDomicilio");
    $calleTransversalDomicilio = $("#calleTransversalDomicilio");
    $referenciaDomicilio = $("#referenciaDomicilio");
    $tipoPredioDomicilio = $("#tipoPredioDomicilio");

    $provinciaTrabajo = $("#provinciaTrabajo");
    $provinciaReferencia = $("#provinciaReferencia");
    $ciudadReferencia = $("#ciudadReferencia");

    $ciudadTrabajo = $("#ciudadTrabajo");
    $sectorTrabajo = $("#sectorTrabajo");
    $callePrincipalTrabajo = $("#callePrincipalTrabajo");
    $numeracionTrabajo = $("#numeracionTrabajo");
    $calleTransversalTrabajo = $("#calleTransversalTrabajo");
    $referenciaTrabajo = $("#referenciaTrabajo");
    $tipoPredioTrabajo = $("#tipoPredioTrabajo");

    $telefonoCasa = $("#telefonoCasa");
    $prefijoCasa = $("#prefijoCasa");
    $telefonoTrabajo = $("#telefonoTrabajo");
    $prefijoTrabajo = $("#prefijoTrabajo");
    $celular = $("#celular");

    $sueldoIngresos = $("#sueldoIngresos");
    $gastosBasicos = $("#gastosBasicos");

    $observaciones = $("#observaciones");

    $send = $("#send");

    $scriptContactado = $("#scriptContactado");

    $sucursal = $("#sucursal");
    $oficina = $("#oficina");
    $estadoCivil = $("#estadoCivil");
    $datosConyugue = $("#datosConyugue");

    $telefonoContactadoDiv = $("#telefonoContactadoDiv");
    $telefonoContactado = $("#telefonoContactado");

    init();

    //Cuando cambia el ESTADO
    $("#status").change(function(){
        esconderCamposEstados();
        if ($("#status").val() == "") {
            emptySelect('substatus');
        }else{
            $statusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/getSubStatusByStatus", {
                id: $statusId
            }, function (data) {
                fillSelect('substatus', data)
            });
        }
        if(this.value == 1){
            $scriptContactado.show();
            $telefonoContactadoDiv.show();
        }else{
            $scriptContactado.hide();
            $telefonoContactadoDiv.hide();
        }
    });

    //Cuando cambia el SUBESTADO
    $("#substatus").change(function () {
        esconderCamposEstados();
        if($("#substatus").val() == ""){
            emptySelect("subSubStatus");
        }else{
            $subStatusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/getSubSubStatusBySubStatus", {
                id: $subStatusId
            }, function (data) {
                if(fillSelect('subSubStatus', data) > 0){
                    $("#subSubStatusDiv").show();
                }else{
                    $("#subSubStatusDiv").hide();
                }
                if(data[2] == 'Rellamada'){ //Tipo de subestado
                    $("#recallDiv").show();
                }else{
                    $("#recallDiv").hide();
                }
                if(data[3]){ //Si el estado habilita la gestión
                    $managementData.show();
                }else{
                    $managementData.hide();
                }
            });
        }
    });

    //Cuando cambia el SUBSUBESTADO
    $("#subSubStatus").change(function () {
        //esconderCamposEstados();
        if($("#subSubStatus").val() == ""){
            emptySelect("motivosSubSubEstados");
        }else{
            $motivosubStatusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/geMotivoSubStatusBySubSubStatus", {
                id: $motivosubStatusId
            }, function (data) {
                if(fillSelect('motivosSubSubEstados', data) > 0){
                    $("#motivosSubSubEstadosDiv").show();
                }else{
                    $("#motivosSubSubEstadosDiv").hide();
                }
            });
        }
    });

    //Cuando cambia la ACTIVIDAD ECONOMICA
    $("#tipoActividad").change(function(){
        if($("#tipoActividad").val() == ""){
            emptySelect('actividadEconomica');
            // emptySelect('sectorDomicilio');
            $prefijoCasa.html("");
        }else {
            $prefijoCasa.html('('+this.value.split('-')[1]+')');
            $id = this.value.split('-')[0];
            $.get(baseUrl + "/FuncionesAjax/getActividadEconomica", {id: $id}, function (data) {
                fillSelect('actividadEconomica', data);
            });
        }
    });

    //Cuando cambia la PROVINCIA DE DOMICILIO
    $provinciaDomicilio.change(function(){
        if($provinciaDomicilio.val() == ""){
            emptySelect('ciudadDomicilio');
           // emptySelect('sectorDomicilio');
            $prefijoCasa.html("");
        }else {
            $prefijoCasa.html('('+this.value.split('-')[1]+')');
            $id = this.value.split('-')[0];
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadDomicilio', data);
            });
        }
    });

    //Cuando se cambia la CIUDAD DE DOMICILIO
    $ciudadDomicilio.change(function () {
        if($ciudadDomicilio.val() == ""){
            emptySelect('sectorDomicilio');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquias", {id: $id}, function (data) {
                fillSelect('sectorDomicilio', data);
            });
        }
    });

    //Cuando se cambia la producto actual.
   /*$("#productoAceptado").change(function () {
        texto1 = this.value;
        //$("#marcasTarjetas").add(texto1, null);
        $("#marcasTarjetas").val(texto1);
        alert(texto1);
    });*/

    //Cuando se cambia la TARJETAS
    $("#productoAceptado").change(function () {
        if($("#productoAceptado").val() == ""){
            emptySelect('tipoTarjeta');
        }else {
            $nombreTarjeta = this.value;
            //$.get(baseUrl + "/FuncionesAjax/getTipoTarjetas", {id: $id}, function (data) {
                $.get(baseUrl + "/FuncionesAjax/getUpdateTarjeta", {nombreTarjeta: $nombreTarjeta}, function (data) {
                fillSelect('tipoTarjeta', data);
            });
        }
    });


    //Cuando cambia la PROVINCIA DE TRABAJO
    $provinciaTrabajo.change(function(){
        if($provinciaTrabajo.val() == ""){
            emptySelect('ciudadTrabajo');
            emptySelect('sectorTrabajo');
            $prefijoTrabajo.html("");
        }else {
            $prefijoTrabajo.html('('+this.value.split('-')[1]+')');
            $id = this.value.split('-')[0];
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadTrabajo', data);
            });
        }
    });

    //Cuando se cambia la CIUDAD DE TRABAJO
    $ciudadTrabajo.change(function () {
        if($ciudadTrabajo.val() == ""){
            emptySelect('sectorTrabajo');
        }else {
            $id = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquias", {id: $id}, function (data) {
                fillSelect('sectorTrabajo', data);
            });
        }
    });

    $provinciaReferencia.change(function(){
        if($provinciaReferencia.val() == ""){
            emptySelect('ciudadReferencia');
            $prefijoTrabajo.html("");
        }else {
            $prefijoTrabajo.html('('+this.value.split('-')[1]+')');
            $id = this.value.split('-')[0];
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $id}, function (data) {
                fillSelect('ciudadReferencia', data);
            });
        }
    });

    //Cuando se cambia la Sucursal
    $sucursal.change(function(){
        if($sucursal.val() == ""){
            emptySelect('oficina');
        }else{
            $id = this.value;
            $.get(baseUrl + "/funcionesAjax/getOficinas", {id: $id}, function(data){
                fillSelect('oficina', data);
            });
        }
    });

    $estadoCivil.change(function(){
        if($estadoCivil.val() == "2" || $estadoCivil.val() == "5"){
            $datosConyugue.show();
        }else{
            $datosConyugue.hide();
        }
    });

    $("#aceptaCambioProducto").change(function(){
        if($("#aceptaCambioProducto").val() == "SI"){
            $("#divMarcas").show();
        }else{
            $("#divMarcas").hide();
        }
    });

    //---------------------------------------------------------------------------------------------
    //Cuando se quiere GUARDAR LA GESTION
    $("#send").click(function (e) {
        if(!validateManagementData()){
            e.preventDefault();
            return false;
        }else{
            $("#send").hide();
        }
    });

    $("#calcularLnk").click(function(e){

        if($("#sueldoIngresos").val() == ""){
            alert("Ingrese valores en el campo Ingresos personales para poder calcular.");
            e.preventDefault();
            return false;
        }

        $("#resultadoCalculo").html("Espere...");

        $ingresos = $("#sueldoIngresos").val().trim();
        $.post(baseUrl + "/funcionesAjax/calcularPorcentajeIngresos", { ingresos: $ingresos}, function(data){
            $("#resultadoCalculo").html("El valor de los egresos no debe exceder los $ " + data);
        })
    });

});

//Validación de los DATOS DE GESTION
function validateManagementData() {
    $isValid = true;

    if ($("#number1").is(":visible")) {
        if ($("#estadoTel1").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 1");
            $isValid = false;
            return
        }
    }
    if ($("#number2").is(":visible")) {
        if ($("#estadoTel2").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 2");
            $isValid = false;
            return
        }
    }
    if ($("#number3").is(":visible")) {
        if ($("#estadoTel3").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 3");
            $isValid = false;
            return
        }
    }
    if ($("#number4").is(":visible")) {
        if ($("#estadoTel4").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 4");
            $isValid = false;
            return
        }
    }
    if ($("#number5").is(":visible")) {
        if ($("#estadoTel5").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 5");
            $isValid = false;
            return
        }
    }
    if ($("#number6").is(":visible")) {
        if ($("#estadoTel6").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 6");
            $isValid = false;
            return
        }
    }
    if ($("#number7").is(":visible")) {
        if ($("#estadoTel7").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 7");
            $isValid = false;
            return
        }
    }
    if ($("#number8").is(":visible")) {
        if ($("#estadoTel8").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 8");
            $isValid = false;
            return
        }
    }
    if ($("#number9").is(":visible")) {
        if ($("#estadoTel9").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 9");
            $isValid = false;
            return
        }
    }
    if ($("#number10").is(":visible")) {
        if ($("#estadoTel10").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 10");
            $isValid = false;
            return
        }
    }

    if($("#status").val() == ""){
        alert("Debe escoger un estado");
        $isValid = false;
        return
    }else{
        if($("#substatus").val() == ""){
            alert("Debe escoger un subestado");
            $isValid = false;
            return
        }else{
            if($("#recallDiv").is(":visible")){
                if($("#agendamiento").val() == ""){
                    alert("Campo agendamiento vacio")
                    $isValid = false;
                    return
                }
                if($("#recall").val() == ""){
                    alert("Debe ingresar una fecha para la rellamada");
                    $isValid = false;
                    return
                }else{
                    if (calcularDias($("#recall").val()) > 3){
                        alert("La fecha de rellamada no puede sobrepasar los tres días")
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#subSubStatus").is(":visible")){
                if($("#subSubStatus").val() == ""){
                    alert("Debe escoger un sub subestado");
                    $isValid = false;
                    return
                }
            }

            if($("#motivosSubSubEstadosDiv").is(":visible")){
                if($("#motivosSubSubEstados").val() == ""){
                    alert("Debe escoger un motivo SubSubestado");
                    $isValid = false;
                    return
                }
            }

            if($telefonoContactadoDiv.is(":visible")){
                if($telefonoContactado.val().trim() === ""){
                    alert("Ingrese el teléfono al cual pudo contactar al cliente");
                    $isValid = false;
                    return
                }else{
                    if($telefonoContactado.val().substring(0, 1) != 0){
                        alert("El teléfono contactado es incorrecto");
                        $isValid = false;
                        return
                    }else{
                        if(!validarSiNumero($telefonoContactado.val())){
                            alert("El teléfono contactado no es un número válido");
                            $isValid = false;
                            return
                        }
                    }
                }
            }

        }
    }
    if($managementData.is(":visible")){

        if($("#productoAceptado").val() == ""){
            alert("Ingrese el producto que acepto el cliente.")
            $isValid = false;
            return
        }

        if($("#aceptaCambioProducto").val() == ""){
            alert("Ingrese si el cliente desea el cambio de producto.")
            $isValid = false;
            return
        }
        if($("#divMarcas").is(":visible")){
            if($("#marcasTarjetas").val() == ""){
                alert("Elija la marca de la tarjeta que desea cambiar el cliente.")
                $isValid = false;
                return
            }
            if($("#tipoTarjeta").val() == ""){
                alert("Escoja el tipo de tarjeta que desea cambiar el cliente.")
                $isValid = false;
                return
            }
        }

        if($lugarEntrega.val() == ""){
            alert("Seleccione un lugar de entrega");
            $isValid = false;
            return
        }

        if($horarioEntrega.val() == ""){
            alert("Seleccione un horario de entrega");
            $isValid = false;
            return
        }

        if($("#tipoIdentificacion").val() == ""){
            alert("Campo Tipo identificación vacio")
            $isValid = false;
            return
        }

        if($cedulaVerificada.val().trim() == ""){
            alert("Verifique la cédula del cliente");
            $isValid = false;
            return
        }else{
            if(!esCedulaValida($cedulaVerificada.val().trim())){
                alert("Verificación de cédula incorrecta.");
                $isValid = false;
                return
            }
        }
        if($("#apellido1").val() == ""){
            alert("Campo Apellido 1 vacio")
            $isValid = false;
            return
        }else{
            if(!validarSiSoloLetras($("#apellido1").val().trim())){
                alert("El apellido 1 del cliente debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($("#apellido1").val().charAt(0) == " "){
                    alert("El campo Apellido 1 no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }
   if($("#apellido2").val().trim() != ""){
       if(!validarSiSoloLetras($("#apellido2").val().trim())){
           alert("El apellido 2 del cliente debe contener sólo letras sin números ni tildes");
           $isValid = false;
           return
       }else{
           if($("#apellido2").val().charAt(0) == " "){
               alert("El campo Apellido 2 no debe contener espacios en blanco al inicio.");
               $isValid = false;
               return
           }
       }
   }
        if($nombreVerificado.val().trim() == ""){
            alert("Verifique el nombre 1 del cliente");
            $isValid = false;
            return
        }else{
            if(!validarSiSoloLetras($nombreVerificado.val().trim())){
                alert("El nombre 1 del cliente debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($nombreVerificado.val().charAt(0) == " "){
                    alert("El campo Nombre 1 no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }

        if($("#nombre2Verificado").val() != ""){
            if(!validarSiSoloLetras($("#nombre2Verificado").val().trim())){
                alert("El nombre 2 del cliente debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($("#nombre2Verificado").val().charAt(0) == " "){
                    alert("El campo Nombre 2 no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }

        if($nombreVerificado.val().trim() == $("#apellido1").val().trim()){
            alert("El Nombre 1 no puede ser igual al Apellido, verifique los nombres.");
            $isValid = false;
            return
        }

        if($("#nacionalidad").val() == ""){
            alert("Campo nacionalidad vacio")
            $isValid = false;
            return
        }
        if($("#sexo").val() == ""){
            alert("Campo Sexo vacio")
            $isValid = false;
            return
        }
        if($("#estadoCivil").val() == ""){
            alert("Campo Estado Civil vacio")
            $isValid = false;
            return
        }
        if($("#fechaNacimientoGestion").val() == ""){
            alert("Campo Fecha Nacimiento vacio")
            $isValid = false;
            return
        }else{
            if(calcularEdad($("#fechaNacimientoGestion").val()) < 18 || calcularEdad($("#fechaNacimientoGestion").val()) > 66){
                alert("El rango de edad de la persona debe estar entre los 18 a 66 años de edad. Verifique la fecha de Nacimiento");
                $isValid = false;
                return;
            }
        }
        if($("#profesion").val() == ""){
            alert("Campo Profesion vacio")
            $isValid = false;
            return
        }
        if($("#nivelEstudios").val() == ""){
            alert("Campo Nivel de Estudios vacio")
            $isValid = false;
            return
        }
        if($("#cargasFamiliares").val() == ""){
            alert("Campo Cargas Familiares vacio")
            $isValid = false;
            return
        }
        if($("#tipoActividad").val() == ""){
            alert("Campo Tipo de Actividad vacio")
            $isValid = false;
            return
        }
        if($("#actividadEconomica").val() == ""){
            alert("Campo Actividad Económica vacio")
            $isValid = false;
            return
        }

        if($("#datosConyugue").is(":visible")){
            if($("#tipoIdentificacionConyuge").val() == ""){
                alert("Campo Tipo identificación Conyugue vacio")
                $isValid = false;
                return
            }
            if($("#identificacionConyuge").val() == ""){
                alert("Campo Tipo identificación Conyugue vacio")
                $isValid = false;
                return
            }else{
                if(!esCedulaValida($("#identificacionConyuge").val().trim())){
                    alert("Verificación de cédula de conyugue incorrecta.");
                    $isValid = false;
                    return
                }
            }
            if($("#apellido1Conyuge").val() == ""){
                alert("Campo Apellido 1 Conyugue vacio")
                $isValid = false;
                return
            }else{
                if(!validarSiSoloLetras($("#apellido1Conyuge").val().trim())){
                    alert("El apellido 1 del conyugue debe contener sólo letras sin números ni tildes");
                    $isValid = false;
                    return
                }else{
                    if($("#apellido1Conyuge").val().charAt(0) == " "){
                        alert("El campo Apellido Conyugue 1 no debe contener espacios en blanco al inicio.");
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#apellido2Conyuge").val().trim() != ""){
                if(!validarSiSoloLetras($("#apellido2Conyuge").val().trim())){
                    alert("El apellido 2 del conyugue debe contener sólo letras sin números ni tildes");
                    $isValid = false;
                    return
                }else{
                    if($("#apellido2Conyuge").val().charAt(0) == " "){
                        alert("El campo Apellido 2 del Conyugue no debe contener espacios en blanco al inicio.");
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#nombre1Conyuge").val() == ""){
                alert("Campo Nombre 1 Conyugue vacio")
                $isValid = false;
                return
            }else{
                if(!validarSiSoloLetras($("#nombre1Conyuge").val().trim())){
                    alert("El Nombre 1 del conyugue debe contener sólo letras sin números ni tildes");
                    $isValid = false;
                    return
                }else{
                    if($("#nombre1Conyuge").val().charAt(0) == " "){
                        alert("El campo Nombre 1 del Conyugue no debe contener espacios en blanco al inicio.");
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#nombre2Conyuge").val().trim() != ""){
                if(!validarSiSoloLetras($("#nombre2Conyuge").val().trim())){
                    alert("El Nombre 2 del conyugue debe contener sólo letras sin números ni tildes");
                    $isValid = false;
                    return
                }else{
                    if($("#nombre2Conyuge").val().charAt(0) == " "){
                        alert("El campo Nombre 2 del Conyugue no debe contener espacios en blanco al inicio.");
                        $isValid = false;
                        return
                    }
                }
            }
            if($("#paisConyuge").val() == ""){
                alert("Campo Pais Conyugue vacio")
                $isValid = false;
                return
            }
            if($("#generoConyuge").val() == ""){
                alert("Campo Genero Conyugue vacio")
                $isValid = false;
                return
            }
            if($("#fechaNacimientoConyuge").val() == ""){
                alert("Campo Fecha Nacimiento Conyugue vacio")
                $isValid = false;
                return
            }
            if($("#estadoCivilConyuge").val() == ""){
                alert("Campo Estado Civil Conyugue vacio")
                $isValid = false;
                return
            }
            if($("#paisNacimientoConyuge").val() == ""){
                alert("Campo País Nacimiento Conyugue vacio")
                $isValid = false;
                return
            }
        }



     //   if($lugarEntrega.val() == "DIRECCION DE RESIDENCIA"){
            if($provinciaDomicilio.val() == ""){
                alert("Seleccione la provincia de domicilio");
                $isValid = false;
                return
            }
            if($ciudadDomicilio.val() == ""){
                alert("Seleccione la ciudad de domicilio");
                $isValid = false;
                return
            }
            if($sectorDomicilio.val() == ""){
                alert("Seleccione el sector de domicilio");
                $isValid = false;
                return
            }
            if($callePrincipalDomicilio.val().trim() == ""){
                alert("Especifique la calle principal de domicilio");
                $isValid = false;
                return
            }
            /*if($calleTransversalDomicilio.val().trim() == ""){
                alert("Especifique la calle transversal de domicilio");
                $isValid = false;
                return
            }*/
            if($referenciaDomicilio.val().trim() == ""){
                alert("Especifique la referencia de domicilio");
                $isValid = false;
                return
            }
            if($tipoPredioDomicilio.val() == ""){
                alert("Seleccione tipo de lugar de domicilio");
                $isValid = false;
                return
            }
            $infoConcDomicilio = $callePrincipalDomicilio.val() + ' ' + $numeracionDomicilio.val() + ' ' + $calleTransversalDomicilio.val() + ' ' + $("#sectorDomicilio option:selected").html() + ' ' + $referenciaDomicilio.val();
            $infoConcDomicilio = replaceMore2Spaces($infoConcDomicilio);
            if ($infoConcDomicilio.length > 145 || $infoConcDomicilio.length < 85) {
                alert("El total de la dirección de domicilio tiene " + $infoConcDomicilio.length + " caracteres. El número de caracteres permitido es de 85 a 145.");
                $isValid = false;
                return
            }

        if($("#sucursal").val() == ""){
            alert("Campo Sucursal vacio")
            $isValid = false;
            return
        }

        if($("#oficina").val() == ""){
            alert("Campo Oficina vacio")
            $isValid = false;
            return
        }

        //}
     //   if($lugarEntrega.val() == "DIRECCION DE TRABAJO"){

        if($("#relacionLaboral").val() == ""){
            alert("Campo Relacion Laboral vacio")
            $isValid = false;
            return
        }
        if($("#nombreEmpresa").val() == ""){
            alert("Campo Nombre Empresa vacio")
            $isValid = false;
            return
        }else{
            if(!validarSiSoloLetras($("#nombreEmpresa").val().trim())){
                alert("El nombre de la empresa debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }
        }
        if($("#fechaInicioTrabajoActual").val() == ""){
            alert("Campo Fecha Inicio Trabajo Actual vacio")
            $isValid = false;
            return
        }
        if($("#fechaInicioTrabajoAnterior").val() == ""){
            alert("Campo Fecha Inicio Trabajo Anterior vacio")
            $isValid = false;
            return
        }
        if($("#fechaFinTrabajoAnterior").val() == ""){
            alert("Campo Fecha Inicio Trabajo Anterior vacio")
            $isValid = false;
            return
        }

        if(validateFechaMayorQue($("#fechaInicioTrabajoActual").val(), $("#fechaInicioTrabajoAnterior").val())){
            alert("La fecha Inicia trabajo anterior no puede ser mayor a la fecha inicio trabajo actual.")
            $isValid = false;
            return
        }

        if(validateFechaMayorQue($("#fechaFinTrabajoAnterior").val(), $("#fechaInicioTrabajoAnterior").val())){
            alert("La fecha fin trabajo anterior no puede ser menor a la fecha inicio trabajo anterior.")
            $isValid = false;
            return
        }

        if(validateFechaMayorQue($("#fechaInicioTrabajoActual").val(), $("#fechaInicioTrabajoAnterior").val()) || validateFechaMayorQue($("#fechaInicioTrabajoActual").val(), $("#fechaFinTrabajoAnterior").val())){
            alert("Las fechas de trabajo anterior son mayores que la fecha de inicio trabajo actual.")
            $isValid = false;
            return
        }

            if($provinciaTrabajo.val() == ""){
                alert("Seleccione la provincia de trabajo");
                $isValid = false;
                return
            }
            if($ciudadTrabajo.val() == ""){
                alert("Seleccione la ciudad de trabajo");
                $isValid = false;
                return
            }
            if($sectorTrabajo.val() == ""){
                alert("Seleccione el sector de trabajo");
                $isValid = false;
                return
            }
            if($callePrincipalTrabajo.val().trim() == ""){
                alert("Especifique la calle principal de trabajo");
                $isValid = false;
                return
            }
            /*if($calleTransversalTrabajo.val().trim() == ""){
                alert("Especifique la calle transversal de trabajo");
                $isValid = false;
                return
            }*/
            if($referenciaTrabajo.val().trim() == ""){
                alert("Especifique la referencia de trabajo");
                $isValid = false;
                return
            }
            if($tipoPredioTrabajo.val() == ""){
                alert("Seleccione tipo de lugar de trabajo");
                $isValid = false;
                return
            }
            $infoConcTrabajo = $callePrincipalTrabajo.val() + ' ' + $numeracionTrabajo.val() + ' ' + $calleTransversalTrabajo.val() + ' ' + $("#sectorTrabajo option:selected").html() + ' ' + $referenciaTrabajo.val();
            $infoConcTrabajo = replaceMore2Spaces($infoConcTrabajo);

            if ($infoConcTrabajo.length > 145 || $infoConcTrabajo.length < 85) {
                alert("El total de la dirección de trabajo tiene " + $infoConcTrabajo.length + " caracteres. El número de caracteres permitido es de 85 a 145.");
                $isValid = false;
                return
            }
      //  }
        if($telefonoCasa.val().trim() == ""){
            alert("El teléfono de casa del cliente es obigatorio");
            $isValid = false;
            return
        }
        if($telefonoCasa.val().trim() != ""){
            if(!validarSiNumero($telefonoCasa.val().trim())){
                alert("El teléfono de casa debe contener sólo números");
                $isValid = false;
                return
            }else{
              /*  if($prefijoCasa.html() != ""){
                    if($telefonoCasa.val().trim().substring(0,2) != $prefijoCasa.html().replace('(', '').replace(')', '')){
                        alert("El prefijo del teléfono de casa no concuerda con la provincia");
                        $isValid = false;
                        return
                    }
                }else{*/
                    if(!validarPrimerCaracterEsCero($telefonoCasa.val().trim())){
                        alert("El teléfono de casa es incorrecto");
                        $isValid = false;
                        return
                    }else{
                        if($telefonoCasa.val().trim().substring(0,2) == '09'){
                            alert("No se puede ingresar celulares en el campo Telefono Convencional Casa");
                            $isValid = false;
                            return
                        }
                    }
               // }
            }
        }
        if($telefonoTrabajo.val().trim() == ""){
            alert("El teléfono de trabajo del cliente es obigatorio");
            $isValid = false;
            return
        }
        if($telefonoTrabajo.val().trim() != ""){
            if(!validarSiNumero($telefonoTrabajo.val().trim())){
                alert("El teléfono de trabajo debe contener sólo números");
                $isValid = false;
                return
            }else{
              /*  if($prefijoTrabajo.html() != ""){
                    if($telefonoTrabajo.val().trim().substring(0,2) != $prefijoTrabajo.html().replace('(', '').replace(')', '')){
                        alert("El prefijo del teléfono de trabajo no concuerda con la provincia");
                        $isValid = false;
                        return
                    }
                }else{*/
                    if(!validarPrimerCaracterEsCero($telefonoTrabajo.val().trim())){
                        alert("El teléfono de trabajo es incorrecto");
                        $isValid = false;
                        return
                    }else{
                        if($telefonoTrabajo.val().trim().substring(0,2) == '09'){
                            alert("No se puede ingresar celulares en el campo Telefono Convencional Trabajo");
                            $isValid = false;
                            return
                        }
                    }
               // }
            }
        }

        if($celular.val().trim() == ""){
            alert("El celular del cliente es obigatorio");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($celular.val().trim())){
                alert("El celular debe contener sólo números");
                $isValid = false;
                return
            }else{
                if($celular.val().trim().substring(0, 2) != "09"){
                    alert("El teléfono celular es incorrecto");
                    $isValid = false;
                    return
                }
            }
        }

        if($("#emailPersonal").val() == ""){
            alert("Ingrese la dirección del email");
            $isValid = false;
            return
        }else{
            if(!validarEmail($("#emailPersonal").val())){
                alert("El Email ingresado es incorrecto.");
                $isValid = false;
                return;
            }
        }

        if($sueldoIngresos.val().trim() == ""){
            alert("Solicite los ingresos al cliente");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($sueldoIngresos.val().trim())){
                alert("El campo ingresos sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#comisiones").val() == ""){
            alert("Solicite comisiones al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#comisiones").val().trim())){
                alert("El campo comisiones sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#otrosIngresos").val() == ""){
            alert("Solicite otros ingresos al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#otrosIngresos").val().trim())){
                alert("El campo otros ingresos sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#ingresosConyugue").val() == ""){
            alert("Solicite ingresos de conyugue al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#ingresosConyugue").val().trim())){
                alert("El campo ingresos conyugue sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#alimentacion").val() == ""){
            alert("Solicite gastos de alimentacion al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#alimentacion").val().trim())){
                alert("El campo alimentacion sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#arriendo").val() == ""){
            alert("Solicite gastos de arriendo al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#arriendo").val().trim())){
                alert("El campo arriendo sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#cuotaArriendo").val() == ""){
            alert("Solicite gastos de cuota de arriendo al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#cuotaArriendo").val().trim())){
                alert("El campo cuota arriendo sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#cuotaArriendo").val() == ""){
            alert("Solicite gastos de cuota de arriendo al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#cuotaArriendo").val().trim())){
                alert("El campo cuota arriendo sólo debe contener números");
                $isValid = false;
                return
            }
        }

        if($gastosBasicos.val().trim() == ""){
            alert("Solicite los gastos básicos al cliente");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($gastosBasicos.val().trim())){
                alert("El campo gastos básicos sólo debe contener números");
                $isValid = false;
                return
            }
        }

        if($("#vestimenta").val() == ""){
            alert("Solicite gastos de vestimenta al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#vestimenta").val().trim())){
                alert("El campo vestimenta sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#educacion").val() == ""){
            alert("Solicite gastos de educacion al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#educacion").val().trim())){
                alert("El campo educacion sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#salud").val() == ""){
            alert("Solicite gastos de salud al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#salud").val().trim())){
                alert("El campo salud sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#cuotaVehiculo").val() == ""){
            alert("Solicite gastos de cuota vehiculo al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#cuotaVehiculo").val().trim())){
                alert("El campo cuota vehiculo sólo debe contener números");
                $isValid = false;
                return
            }
        }
        if($("#origenIngresos").val() == ""){
            alert("Campo origen de ingresos vacío");
            $isValid = false;
            return
        }
        if($("#rangoIngresos").val() == ""){
            alert("Campo rango de ingresos vacío");
            $isValid = false;
            return
        }

        if($("#patrimonio").val() == ""){
            alert("Solicite el patrimonio al cliente.");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#patrimonio").val().trim())){
                alert("El campo patrimonio sólo debe contener números");
                $isValid = false;
                return
            }else{
                if($("#patrimonio").val() == "0"){
                    alert("No puede ingresar valores en 0 en el patrimonio, ingrese el valor del sueldo en caso de no tener información.");
                    $isValid = false;
                    return
                }
            }
        }
        if($("#tipoVivienda").val() == ""){
            alert("Campo tipo vivienda vacío");
            $isValid = false;
            return
        }
        if($("#valorVivienda").val() == ""){
            alert("Solicite el valor de vivienda al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#valorVivienda").val().trim())){
                alert("El campo valor vivienda sólo debe contener números");
                $isValid = false;
                return
            }else{
                if($("#tipoVivienda").val() == "P" && $("#valorVivienda").val().trim() == "0" || $("#tipoVivienda").val() == "N" && $("#valorVivienda").val().trim() == "0"){
                    alert("Si la vivienda es propia el valor debe ser mayor a $ 25000" );
                    $isValid = false;
                    return
                }else{
                    if($("#tipoVivienda").val() == "P" && $("#valorVivienda").val() < 25000 || $("#tipoVivienda").val() == "N" && $("#valorVivienda").val() < 25000){
                        alert("El valor de la vivienda debe ser mayor a $ 25000 si es propia." );
                        $isValid = false;
                        return
                    }else{
                        if($("#tipoVivienda").val() == "F" && $("#valorVivienda").val().trim() != "0" || $("#tipoVivienda").val() == "A" && $("#valorVivienda").val().trim() != "0" || $("#tipoVivienda").val() == "S" && $("#valorVivienda").val().trim() != "0"){
                            alert("Si la vivienda no es propia, el valor debe ser 0." );
                            $isValid = false;
                            return
                        }
                    }
                }
            }
        }

        if($("#fechaInicioResidencia").val() == ""){
            alert("Campo Fecha Inicio Residencia vacío");
            $isValid = false;
            return
        }/*else{
            if(!validateDateReal($("#fechaInicioResidencia").val().trim())){
                alert("Campo Fecha Inicio Residencia incorrecta");
                $isValid = false;
                return
            }
        }*/

        if($("#valorActivo").val() == ""){
            alert("Solicite el valor de activo al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#valorActivo").val().trim())){
                alert("El campo Valor Activo sólo debe contener números");
                $isValid = false;
                return
            }
        }

        if($("#valorPasivo").val() == ""){
            alert("Solicite el valor de pasivo al cliente, si no brinda informacion ingrese 0");
            $isValid = false;
            return
        }else{
            if(!validarSiNumero($("#valorPasivo").val().trim())){
                alert("El campo Valor Pasivo sólo debe contener números");
                $isValid = false;
                return
            }
        }

        if($("#tipoIdentificacionRefPersonal").val() == ""){
            alert("Campo tipo Identificacion Ref. Perso. vacio");
            $isValid = false;
            return
        }
        if($("#identificacionRefPersonal").val() == ""){
            alert("Campo tipo Identificacion Ref. Perso. vacio");
            $isValid = false;
            return
        }else{
            if(!esCedulaValida($("#identificacionRefPersonal").val().trim())){
                alert("Verificación de cédula de Ref. Perso. incorrecta.");
                $isValid = false;
                return
            }
        }
        if($("#apellido1RefPersonal").val() == ""){
            alert("Campo Apellido 1 Ref. Rerso. vacio")
            $isValid = false;
            return
        }else{
            if(!validarSiSoloLetras($("#apellido1RefPersonal").val().trim())){
                alert("El Apellido 1 de Ref. Rerso. debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($("#apellido1RefPersonal").val().charAt(0) == " "){
                    alert("El campo Apellido 1 del Ref. Per. no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }
        if($("#apellido2RefPersonal").val().trim() != ""){
            if(!validarSiSoloLetras($("#apellido2RefPersonal").val().trim())){
                alert("El Apellido 2 de Ref. Rerso. debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($("#apellido2RefPersonal").val().charAt(0) == " "){
                    alert("El campo Apellido 2 del Ref. Per. no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }
        if($("#nombre1RefPersonal").val() == ""){
            alert("Campo Nombre 1 Ref. Rerso. vacio")
            $isValid = false;
            return
        }else{
            if(!validarSiSoloLetras($("#nombre1RefPersonal").val().trim())){
                alert("El Nombre 1 de Ref. Rerso. debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($("#nombre1RefPersonal").val().charAt(0) == " "){
                    alert("El campo Nombre 1 del Ref. Per. no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }
        if($("#nombre2RefPersonal").val().trim() != ""){
            if(!validarSiSoloLetras($("#nombre2RefPersonal").val().trim())){
                alert("El Nombre 2 de Ref. Rerso. debe contener sólo letras sin números ni tildes");
                $isValid = false;
                return
            }else{
                if($("#nombre2RefPersonal").val().charAt(0) == " "){
                    alert("El campo Nombre 2 del Ref. Per. no debe contener espacios en blanco al inicio.");
                    $isValid = false;
                    return
                }
            }
        }
        if($provinciaReferencia.val() == ""){
            alert("Seleccione la provincia de la referencia");
            $isValid = false;
            return
        }
        if($ciudadReferencia.val() == ""){
            alert("Seleccione la ciudad de la referencia");
            $isValid = false;
            return
        }
        if($("#callePrincipalRefPersonal").val() == ""){
            alert("Campo Calle Principal Ref. Perso. vacio")
            $isValid = false;
            return
        }
       /* if($("#calleSecundariaRefPersonal").val() == ""){
            alert("Campo Calle Secundaria Ref. Perso. vacio")
            $isValid = false;
            return
        }*/
        if($("#referenciaRefPersonal").val() == ""){
            alert("Campo Referencia Ref. Perso. vacio")
            $isValid = false;
            return
        }
        if($("#telefonoRefPersonal").val() == ""){
            alert("Campo Telefono Ref. Perso. vacio")
            $isValid = false;
            return
        }
        if($("#telefonoRefPersonal").val().trim() != ""){
            if(!validarSiNumero($("#telefonoRefPersonal").val().trim())){
                alert("El teléfono de Ref. Perso. debe contener sólo números");
                $isValid = false;
                return
            }else{
                /*  if($prefijoTrabajo.html() != ""){
                 if($telefonoTrabajo.val().trim().substring(0,2) != $prefijoTrabajo.html().replace('(', '').replace(')', '')){
                 alert("El prefijo del teléfono de trabajo no concuerda con la provincia");
                 $isValid = false;
                 return
                 }
                 }else{*/
                if(!validarPrimerCaracterEsCero($("#telefonoRefPersonal").val().trim())){
                    alert("El teléfono de Ref. Perso. es incorrecto");
                    $isValid = false;
                    return
                }
                // }
            }
        }

        if($("#parentescoRefPersonal").val() == ""){
            alert("Campo Parentesco Ref. Perso. vacio")
            $isValid = false;
            return
        }

        $infoConcReferencia = $("#callePrincipalRefPersonal").val() + ' ' + $("#numeracionDomicilio").val() + ' ' + $("#calleSecundariaRefPersonal").val() + ' '  + $("#referenciaRefPersonal").val();
        $infoConcReferencia = replaceMore2Spaces($infoConcReferencia);

        if ($infoConcReferencia.length > 150 || $infoConcReferencia.length < 85) {
            alert("El total de la dirección de referencia personal tiene " + $infoConcReferencia.length + " caracteres. El número de caracteres permitido es de 85 a 145.");
            $isValid = false;
            return
        }

        if($("#identificacionRefPersonal").val().trim() == $("#cedulaTitular").text()){
            alert("La cédula de la referencia personal no puede ser igual a la del titular")
            $isValid = false;
            return
        }
    }
    return $isValid;
}

$('#sueldoIngresos').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#comisiones').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#otrosIngresos').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#ingresosConyugue').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#alimentacion').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#arriendo').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#cuotaArriendo').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#gastosBasicos').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#vestimenta').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#educacion').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#salud').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#cuotaVehiculo').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#patrimonio').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#valorVivienda').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});

function validateFechaMayorQue(fechaInicial,fechaFinal) {
    $esVaido = true;
    valuesStart = fechaInicial.split("/");
    valuesEnd = fechaFinal.split("/");
    // Verificamos que la fecha no sea posterior a la actual
    var dateStart=new Date(valuesStart[0],(valuesStart[1]-1),valuesStart[2]);
    var dateEnd=new Date(valuesEnd[0],(valuesEnd[1]-1),valuesEnd[2]);
    if(dateStart>=dateEnd) {
        $esVaido = false;
    }
    return $esVaido;
}


/**
 * Función que bloquea la tecla Enter del campo de Referencia de Domicilio
 * @param referenciaDomicilio
 * @author Andres Redroban
 */
$('#referenciaDomicilio').keydown(function(event) {
    if(event.keyCode == 13){
        return false;
        //carry on...
    }else{
        return;
    }
});

$('#referenciaTrabajo').keydown(function(event) {
    if(event.keyCode == 13){
        return false;
        //carry on...
    }else{
        return;
    }
});

$('#referenciaRefPersonal').keydown(function(event) {
    if(event.keyCode == 13){
        return false;
        //carry on...
    }else{
        return;
    }
});

$('#fechaNacimientoGestion').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});
$('#fechaNacimientoGestion').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#fechaInicioTrabajoActual').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});
$('#fechaInicioTrabajoActual').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#fechaInicioTrabajoAnterior').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});
$('#fechaInicioTrabajoAnterior').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#fechaFinTrabajoAnterior').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});
$('#fechaFinTrabajoAnterior').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});
$('#fechaNacimientoConyuge').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});
$('#fechaNacimientoConyuge').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});

$('#fechaInicioResidencia').on('keydown', function (e)
{
    try {
        if ((e.keyCode == 8 || e.keyCode == 46))
            return false;
        else
            return true;
    }
    catch (Exception)
    {
        return false;
    }
});
$('#fechaInicioResidencia').on('keypress', function(e) {
    if(checkIfNumberNoSpace(e.which, e)==0){
        return false;
    }else{
        return;
    }
});


/**
 *@description Funcion que evita que puedan ingresar numeros en campos
 * @author Andres Redroban
 * */

function checkIfNumberNoSpace(codeKey,e){
    if (codeKey == 32)
        return 0;
    // Asignando numero y no espacios
    if ($.inArray(codeKey, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A
        (codeKey == 97 && e.ctrlKey === true) ||
        // Allow: Ctrl+C
        (codeKey == 99 && e.ctrlKey === true) ||
        // Allow: Ctrl+X
        (codeKey == 120 && e.ctrlKey === true) ||
        // Allow: home, end, left, right, tab
        (codeKey == 0)) {
        // let it happen, don't do anything
        return 1;
    }
    if ((codeKey < 48 || codeKey > 57)) {
        return 0;
    }
}
//Para cuando se INICIA LA GESTION
function init() {
    esconderCamposEstados();
    $("#status").val($("#status option:first").val());
    $provinciaDomicilio.val($("#provinciaDomicilio option:first").val());
    $provinciaTrabajo.val($("#provinciaTrabajo option:first").val());
    $provinciaReferencia.val($("#provinciaReferencia option:first").val());
    $sucursal.val($("#sucursal option:first").val());
    $scriptContactado.hide();
    $datosConyugue.hide();
    $('#divMarcas').hide()
    $telefonoContactadoDiv.hide();

}

/**
 * Función bajada de internet https://es.stackoverflow.com/questions/31373/obtener-la-edad-a-partir-de-la-fecha-de-nacimiento-con-javascript-y-php
 * @param fecha
 * @returns {number}
 */
function calcularEdad(fecha) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}

//This function empties a select component
function emptySelect(idSelect) {

    var select = document.getElementById(idSelect);
    var option = document.createElement('option');
    var NumberItems = select.length;

    while (NumberItems > 0) {
        NumberItems--;
        select.remove(NumberItems);
    }

    option.text = '-- Seleccione --';
    option.value = ''
    select.add(option, null);
}

//This function fills a select component
function fillSelect(idSelect, data) {

    var select = document.getElementById(idSelect);
    var numberSubstatus = data[0].length;

    emptySelect(idSelect)

    if (numberSubstatus > 0) {
        for (i = 0; i < numberSubstatus; i++) {
            var option = document.createElement('option');
            option.text = data[1][i];
            option.value = data[0][i];
            select.add(option, null);
        }
    }

    return numberSubstatus;
}

function esconderCamposEstados(){
    $("#recallDiv").hide();
    $("#subSubStatusDiv").hide();
    $("#motivosSubSubEstadosDiv").hide();
    $managementData.hide();
}

/**
 * Valida si el valor ingresado es numérico
 * @param numero
 */
function validarSiNumero(numero){
    $esNumero = true;
    if (!/^([0-9])*$/.test(numero)){
        $esNumero = false;
    }
    return $esNumero;
}

/**
 * Valida que en el valor ingresado sólo hayan letras y espacios
 * @param entrada
 * @returns {boolean}
 */

/**
 * Valida si el correo ingresado es correcto
 * @param email
 * @author Andres Redroban
 */
function validarEmail(email)
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}

function validarSiSoloLetras(entrada){
    $esSoloTexto = true;
    $filtro = /^([A-Za-z ])*$/;
    if(!$filtro.test(entrada)){
        $esSoloTexto = false;
    }
    return $esSoloTexto;
}

/**
 * Valida que el primer caracter de una cadena sea 0
 * @param numero
 * @returns {boolean}
 */
function validarPrimerCaracterEsCero(numero) {
    $esCero = true;
    var res = numero.charAt(0);
    if (res != '0'){
        $esCero = false;
    }
    return $esCero;
}

/**
 * Función basada en función de internet: https://gist.github.com/vickoman/7800717
 */
function esCedulaValida($entrada){
    $esValida = true;
    if($entrada.length != 10){
        $esValida = false;
    }else{
        //Los dos primeros dígitos me indican la provincia
        $region = parseInt($entrada.substring(0, 2));
        if ($region <= 0 || $region > 30){
            $esValida = false;
        }else{
            $ultimoDigito = $entrada.substring(9, 10);
            //Saco los pares y los sumo
            $pares = parseInt($entrada.substring(1,2)) + parseInt($entrada.substring(3,4)) + parseInt($entrada.substring(5,6)) + parseInt($entrada.substring(7,8));

            //Agrupo los impares, los multiplico por un factor de 2, si la resultante es > que 9 le restamos el 9 a la resultante
            $numero1 = $entrada.substring(0,1);
            $numero1 = ($numero1 * 2);
            if( $numero1 > 9 ){ $numero1 = ($numero1 - 9); }

            $numero3 = $entrada.substring(2,3);
            $numero3 = ($numero3 * 2);
            if( $numero3 > 9 ){ $numero3 = ($numero3 - 9); }

            $numero5 = $entrada.substring(4,5);
            $numero5 = ($numero5 * 2);
            if( $numero5 > 9 ){ $numero5 = ($numero5 - 9); }

            $numero7 = $entrada.substring(6,7);
            $numero7 = ($numero7 * 2);
            if( $numero7 > 9 ){ $numero7 = ($numero7 - 9); }

            $numero9 = $entrada.substring(8,9);
            $numero9 = ($numero9 * 2);
            if( $numero9 > 9 ){ $numero9 = ($numero9 - 9); }

            $impares = $numero1 + $numero3 + $numero5 + $numero7 + $numero9;

            //Suma total
            $suma_total = ($pares + $impares);


            //extraemos el primero digito
            if($suma_total >= 10) //Si la suma total es de dos cifras
                $primer_digito_suma = String($suma_total).substring(0,1);
            else
                $primer_digito_suma = '0';


            //Obtenemos la decena inmediata
            $decena = (parseInt($primer_digito_suma) + 1)  * 10;


            //Obtenemos la resta de la decena inmediata - la suma_total esto nos da el digito validador
            $digito_validador = $decena - $suma_total;

            //Si el digito validador es = a 10 toma el valor de 0
            if($digito_validador == 10)
                $digito_validador = 0;

            if($digito_validador != $ultimoDigito){
                $esValida = false;
            }
        }
    }

    return $esValida;
}

function replaceMore2Spaces(text) {
    var resultText = text;
    while (true) {
        if (resultText.indexOf("  ") != -1) {
            resultText = resultText.replace(/\s{2,}/, " ");
        }else{
            break;
        }
    }
    return resultText.trim();
}

/**
 * Función tomada como ejemplo de internet https://www.lawebdelprogramador.com/foros/JavaScript/1594805-Calcular-la-cantidad-de-dias-entre-dos-fechas-Javascript-y-HTML.html
 * @param fecha
 * @returns {contdias}
 * @author Andres Redrobán
 * @description La siguiente función calcula el numero de dias tomando como referencia el facha actual y la fecha ingresada desde el sistema.
 */
function calcularDias(fecha){
    var fechaini = new Date();
    var fechafin = new Date(fecha);
    var diasdif= fechafin.getTime()-fechaini.getTime();
    var contdias = Math.round(diasdif/(1000*60*60*24));
    return contdias;
}


function soloLetras(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for(var i in especiales){
        if(key == especiales[i]){
            tecla_especial = true;
            break;
        }
    }

    if(letras.indexOf(tecla)==-1 && !tecla_especial){
        return false;
    }
} /*onkeypress="return soloLetras(event)" */