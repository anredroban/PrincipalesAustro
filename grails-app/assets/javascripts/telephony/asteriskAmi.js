$(document).ready(function () {
    $display = $('#keypad-display');
    $callManageUniqueId = $('#callManageUniqueId');
    $exten = $("#extension").val();
    var alertModal = $('#modal-show-alert');
    var alertMessage = $('#alert-message-container');

    //Spy channel
    $('.spy-call-agent').click(function (event) {
        $.get(baseUrl + '/AccionesAsterisk/makeASpyChannel', {
            exten: "SIP/" + $exten,
            min: "SIP/" +  $(this).attr('id')
        }, function (data) {
            console.log(data);
        });
    });

    //make a call
    $('#keypad-calling .col-key').click(function () {
        $number = $display.val();
        if ($number) {
            showModal('calling');
            $('#calling-number').text($number);
            $display.css('border', '1px solid #666');
            $.get(baseUrl + '/AccionesAsterisk/makeACall', {numero: $number, exten: $exten}, function (data) {
                localStorage.setItem("uniqueId", data) //save uniqueId in local storage
                if ($callManageUniqueId != 'undefined') {
                    $callManageUniqueId.val(data)
                }
            });
        } else {
            $display.css('border', '2px solid red').focus();
        }
    });

    //hangup a call
    $('#calling-hangout .col-key').click(function () {
        hideModal('#modal-calling');
        $.get(baseUrl + '/AccionesAsterisk/makeAHangup', {extension: $exten}, function (data) {
            $('#operator-btn-call').show();
            $('#operator-btn-hangout').hide();
            $display.focus();
        });
    });

    //Mute/unmute microphone
    $('#calling-mute').click(function () {
        if ($(this).hasClass('mute')) {
            $.get(baseUrl + '/AccionesAsterisk/muteAChannel', {extension: $exten, muted: "on"}, function (data) {
                $('#calling-mute > div > i').removeClass('fa-microphone-slash').addClass('fa-microphone');
                $('#calling-mute > div').css('background-color', 'red');
                $('#calling-mute').removeClass('mute').addClass('unmute');
            });
        } else if ($(this).hasClass('unmute')) {
            $.get(baseUrl + '/AccionesAsterisk/muteAChannel', {extension: $exten, muted: "off"}, function (data) {
                $('#calling-mute > div > i').removeClass('fa-microphone').addClass('fa-microphone-slash');
                $('#calling-mute > div').css('background-color', 'green');
                $('#calling-mute').removeClass('unmute').addClass('mute');
            });
        }
    });

    //click to call in mouse over
    $("#service-calling").click(function () {

        $phoneNumber = $("#phone-number-" + $management_phone_number_id[2]).text()
        $number = verifyNumber($phoneNumber)

        if($number != '') {
            showModal('calling');
            $('#calling-number').text($number);
            $('#box-call-services').hide();

            $.get(baseUrl + '/AccionesAsterisk/makeACall',
                {
                    numero: $number,
                    exten: $exten
                }, function (data) {
                    //Get a uniqueId for recorders
                    localStorage.setItem("uniqueId", data) //save uniqueId in local storage

                    if ($callManageUniqueId != 'undefined') {
                        $callManageUniqueId.val(data)
                    }

                });
        }else{
            alertModal.modal();
            alertMessage.text('¡Número de teléfono invalido!');
        }
    });

    $("#service-sms").click(function () {
        alertModal.modal();
        alertMessage.text('¡SMS!');
    });

    $("#service-whatsapp").click(function () {
        alertModal.modal();
        alertMessage.text('¡Whatsapp!');
    })
});

function pause(paused) {
    $.get(baseUrl + '/AccionesAsterisk/memberPause', {
        extension: $exten,
        paused: paused
    });
}

/**
 *Verify whether a number is complete
 * @author Esteban Preciado
 * @param number
 * @returns {string}
 */
function verifyNumber(number) {

    $completeNumber = ''

    //Convencional - Celular
    if (number.length == 8 || number.length == 9) {
        $completeNumber = '0' + number
    }

    //Start with 593
    if (number.startsWith("593")) {
        $completeNumber = number.replace("593", "0")
    }

    //Start with +593
    if (number.startsWith("+593")) {
        $completeNumber = number.replace("+593", "0")
    }

    //Start with 5930
    if (number.startsWith("5930")) {
        $completeNumber = number.replace("+5930", "0")
    }
    return $completeNumber
}