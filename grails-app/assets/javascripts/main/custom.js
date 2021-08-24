var message_timeout = 3000;

$(document).ready(function(){	
	var sidebar_collapse = false;
	var recall_fired = false;
	
	$min = $("#extension")
	
	//Tablas de datos				
	$('#tabladedatos').DataTable({
		"order":[]
	});
	
	//MenusDesplegables
	$('#menu-catalogos').click(function(){
		$('.desplegable').not($("ul", this)).slideUp("fast")
		.next()
		$(this).find('ul').slideToggle("fast")
		.end();
	});

	/* My stuff */
	if($('.nav-campaign').hasClass('subnav-expanded')) {
		$('#sub-campaign').attr('aria-expanded', true).addClass('in');
	};
    if($('.nav-reports').hasClass('subnav-expanded')) {
        $('#sub-reportes').attr('aria-expanded', true).addClass('in');
    };
	if($('.nav-security').hasClass('subnav-expanded')) {
		$('#sub-security').attr('aria-expanded', true).addClass('in');
	};
	if($('.nav-catalogos').hasClass('subnav-expanded')) {
		$('#sub-catalogos').attr('aria-expanded', true).addClass('in');
	};
    if($('.nav-licence').hasClass('subnav-expanded')) {
        $('#sub-licence').attr('aria-expanded', true).addClass('in');
    };

	$('#cancel-call, #btn-close-operator-call').click(function() {
		$('#operator-btn-call').show();
		$('#operator-btn-hangout').hide();
	});
	
	$(".delete-item").click(function(e){
		var info = $(this).attr('id').split('-');
		var item = $('#modal-delete-item');
		
		item.modal();
		$('#btn-delete-item').click(function() {
			$.get(baseUrl + '/' + info[0] + '/deleteItem', {id: info[1]}, function(data) {
				if (data) {
					$("#modal-delete-item .close").click()
					$('#row-item-' + info[1]).css('background-color', 'orange').delay(1000).slideUp();
				}
			});
		});
	});
	
	$('.menu-header').click(function() {
		if(!sidebar_collapse) {
			$('#page-wrapper').css('padding-left', '77px');
			$('#sidebar-links').css({'overflow-y': 'auto', 'width': '68px'});
			$('.menu-name').hide();
			$('.navbar-nav > li > a').css('padding', '15px');
			sidebar_collapse = true;
		} else {
			$('#page-wrapper').css('padding-left', '260px');
			$('#sidebar-links').css({'width': '235px'});
			$('.menu-name').show();
			sidebar_collapse = false;
		}
	});

    (function recall_notification() {
        $('.recallNotification').hide();

        $.ajax({
            url : baseUrl + '/FuncionesAjax/rellamadas',
            success : function(data) {
                if (data.length) {
                    $('.recallNotification').show();
                    $('#number-recalls').text(data.length);
                    $('.recallNotification').click(function() {
                        generateRecallList(data);
                    });
                }
            },
            complete : function() {
                setTimeout(recall_notification, 60000);
            }
        });
    })();

});

function generateRecallList(data) {
	if($("#recall-list").is(":visible")){
        $("#recall-list").hide().html('');
	}else{
    $("#recall-list").show().html('');
    $.each(data, function(index, value) {
        $("#recall-list").append('<a class="recall-link" href="'+ baseUrl + '/gestion/gestionCliente/' + value.id + '">' + value.nombre + '</a><br>');
    });
    }
}