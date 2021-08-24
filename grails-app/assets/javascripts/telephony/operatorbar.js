$(document).ready(function() {
    var pauseFont= "";
    var animateOperatorBar = true;
    var showKeypadButton = false;
    var showPauseButton = false;
    var toggleModal;

    $min = $("#extension");
    $display = $('#keypad-display');
    $display.focus();

    $('#operator-btn-keypad-show').click(function() {
        hideModal('#modal-clock, #modal-pause');
        toggelInactiveButton('operator-btn-pause-show', false);
        toggleOperatorBar();
        showModal('keypad');
        toggleKeypadButton();
        $display.focus();
        isModalOpen = true;
    });

    $('#operator-btn-keypad-hide').click(function() {
        hideModal('#modal-keypad, #modal-calling, #modal-message');
        toggelInactiveButton('operator-btn-pause-show', true);
        toggleOperatorBar();
        toggleKeypadButton();
        $('#keypad-display').val('');
        $display.css('border', '1px solid #666');
    });

    $('#modal-keypad .col-sm-4 .col-number').click(function(event) {
        $display.val($display.val() + $(this).text());
        $display.focus();
    });

    $('#keypad-backward .col-key').click(function() {
        $number = $('#keypad-display').val();
        $new_number = $number.slice(0, -1);
        $('#keypad-display').val($new_number);
        $display.focus();
    });

    $('#keypad-message .col-key').click(function(){
        $number = $('#keypad-display').val();
        if ($number) {
            showModal('message');
            $('#message-number').text($number);
            $('#message-box').focus();
            $display.css('border', '1px solid #666')
        } else {
            $display.css('border', '2px solid red').focus();
        }
    });

    $('#message-cancel .col-key').click(function() {
        hideModal('#modal-message');
        $display.focus();
    });

    $('#operator-btn-pause-show').click(function() {
        toggleOperatorBar();
        togglePauseButton();
        hideModal('#modal-keypad, #modal-calling, #modal-message');
        toggelInactiveButton('operator-btn-keypad-show', false);
        showModal('pause');
    });

    $('#operator-btn-pause-hide').click(function() {
        toggleOperatorBar();
        togglePauseButton();
        hideModal('#modal-keypad, #modal-calling, #modal-message');
        toggelInactiveButton('operator-btn-keypad-show', true);
        hideModal('#modal-clock, #modal-pause');
    });

    $('#modal-pause .box-pause').click(function(e) {
        $id = $(this).attr('id').split('-');
        $pauseText = $('#pause-' + $id[1] + '-text').text();
        localStorage.setItem("typeBreak", $pauseText)
        pauseFont = 'fa-' + $id[2];
        pause(true)

        $('.modal-content').modal('toggle');
        $('.modal-dialog').hide();
        showModal('clock');
        $('#pause-type-font').addClass(pauseFont);
        $('#pause-type-text').text($pauseText);
        $('#show-timer').timer({
            format: '%H:%M:%S'
        });
    });

    $('#pause-stop').click(function() {
        localStorage.setItem("timeBreak", $('#show-timer').text())
        pause(false)
        saveCurrentBreak();  //save in db break time for a user
        toggleOperatorBar();
        togglePauseButton();
        $('.modal-content').modal('toggle');
        hideModal('#modal-clock, #modal-pause');
        $('#pause-type-font').removeClass(pauseFont);
        $('#show-timer').timer('remove');
        toggelInactiveButton('operator-btn-keypad-show', true);
    });

    function toggleOperatorBar() {
        if (animateOperatorBar) {
            $('#box-operatorbar').animate({right: '220px'});
            animateOperatorBar = false;
        } else {
            $('#box-operatorbar').animate({right: '0px'});
            animateOperatorBar = true;
        }
    }

    function toggleKeypadButton() {
        if (showKeypadButton) {
            $('#operator-btn-keypad-show').show();
            $('#operator-btn-keypad-hide').hide();
            showKeypadButton = false;
        } else {
            $('#operator-btn-keypad-show').hide();
            $('#operator-btn-keypad-hide').show();
            showKeypadButton = true;
        }
    }

    function togglePauseButton() {
        if (showPauseButton) {
            $('#operator-btn-pause-show').show();
            $('#operator-btn-pause-hide').hide();
            showPauseButton = false;
        } else {
            $('#operator-btn-pause-show').hide();
            $('#operator-btn-pause-hide').show();
            showPauseButton = true;
        }
    }

    function toggelInactiveButton(id, action) {
        action ? $('#' + id).show() : $('#' + id).hide();
    }
});


function showModal(type) {
    $('#modal-' + type).animate({right: '0px'});
}

function hideModal(types) {
    $(types).animate({right: '-220px'});
}

/**
 * Allow to save in break time table
 * @author Esteban Preciado
 */
function saveCurrentBreak() {

    $tBreak = localStorage.getItem("timeBreak");
    $tyBreak = localStorage.getItem("typeBreak");

    //Convert time in seconds
    $separator = $tBreak.split(':');
    $seconds = (+$separator[0]) * 60 * 60 + (+$separator[1]) * 60 + (+$separator[2]);

    $.get(baseUrl + '/AjaxTelephony/saveBreakTime', {
        timeBreak: $seconds,
        typeBreak: $tyBreak
    })
}