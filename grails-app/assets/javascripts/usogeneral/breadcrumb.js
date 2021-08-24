/**
 * Flow tab detector widget.
 * Please do not modify the code of the script at least
 * you are sure that it is necessary.
 * 
 * @author Kleber Salgado <kleber.salgado@wireless.com>
 * @version 1.0
 */
$(document).ready(function() {
	var next;
	var back;
	
	(function() {
		if ($('.breadcrumb-load').val() !== undefined) {
			var actualTab = $('.breadcrumb-load').val();
			activeTab(actualTab);
		} else {
			activeTabPartial();
		}
	})();
	
	function activeTabPartial() {
		activeTab(1);
		
		$('.flow-step').click(function(event) {
			if ($(this).attr('data-flow-next') !== undefined) {
				next = $(this).attr('data-flow-next');
				activeTabFlow(parseInt(next) - 1, parseInt(next));
			}
			
			if ($(this).attr('data-flow-back') !== undefined) {
				back = $(this).attr('data-flow-back');
				activeTabFlow(parseInt(back) + 1, parseInt(back));
			}
		});
	}
	
	function activeTab(tab) {
		$('.flow-step-' + tab).addClass('step-active');
	}
});

/**
 * This function is declared global because sometimes
 * is necessary to access direct to this function after
 * to load or reload a page.
 * 
 * @param deactive integer
 * @param active integer
 */
function activeTabFlow(deactive, active) {
	$('.flow-step-' + deactive).removeClass('step-active');
	$('.flow-step-' + active).addClass('step-active');
}

function showEndMessage(message, deactive, active) {
	$('#main-content').hide();
	$('#div-end-message').show();
    $('#end-message').text(message);
    activeTabFlow(deactive, active)
}