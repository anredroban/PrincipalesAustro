<%@page import="utilitarios.Util"%>

<div id="box-operatorbar" class="social">
	<ul>
		<li id="operator-btn-keypad-show">
			<a href="#" class="icon-keypad-show">
				<i class="fa fa-fw fa-phone"></i>
			</a>
		</li>
		<li id="operator-btn-keypad-hide" class="collapse">
			<a href="#" class="icon-keypad-hide">
				<i class="fa fa-fw fa-arrow-right"></i>
			</a>
		</li>
		<li id="operator-btn-pause-show">
			<a href="#" class="icon-pausar">
				<i class="fa fa-fw fa-pause"></i>
			</a>
		</li>
		<li id="operator-btn-pause-hide" class="collapse">
			<a href="#" class="icon-play">
				<i class="fa fa-fw fa-arrow-right"></i>
			</a>
		</li>
	</ul>
</div>

<div id="modal-keypad">
	<div class="row-fluid">
		<input id="keypad-display" class="col-sm-12  col-xs-12" value="" />
	</div>
	<div class="row-fluid">
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">1</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">2</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">3</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">4</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">5</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">6</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">7</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">8</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">9</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">*</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">0</div>
		</div>
		<div class="col-sm-4 col-xs-4">
			<div class="col-number">#</div>
		</div>
	</div>
	<div class="row-fluid">
		<div id="keypad-calling" class="col-sm-4 col-xs-4">
			<div class="col-key">
				<i class="fa fa-fw fa-phone"></i>
			</div>
		</div>
		<div id="keypad-backward" class="col-sm-4 col-xs-4">
			<div class="col-key">
				<i class="fa fa-fw fa-times-circle"></i>
			</div>
		</div>
		<div id="keypad-message" class="col-sm-4 col-xs-4">
			<div class="col-key">
				<i class="fa fa-fw fa-comment-o"></i>
			</div>
		</div>
	</div>
</div>
<div id="modal-calling">
	<div class="calling-avatar">
		<img src="${assetPath(src: 'calling-avatar.png')}" />
	</div>
	<div id="calling-number"></div>
	<div class="calling-text">Llamando...</div>
	<div class="row-fluid">
		<div id="calling-mute" class="col-sm-4 col-xs-4 mute">
			<div class="col-key">
				<i class="fa fa-fw fa-microphone-slash"></i>
			</div>
		</div>
		<div id="calling-message" class="col-sm-4 col-xs-4">
			<div class="col-key">
				<i class="fa fa-fw fa-comment-o"></i>
			</div>
		</div>
		<div id="calling-hangout" class="col-sm-4 col-xs-4">
			<div class="col-key">
				<i class="fa fa-fw fa-phone-hang-out"></i>
			</div>
		</div>
	</div>
</div>
<div id="modal-message">
	<div id="message-number"></div>
	<div class="row-fluid">
		<textarea id="message-box" class="col-sm-12 col-xs-12"></textarea>
	</div>
	<div class="row-fluid">
		<div id="message-send" class="col-sm-5 col-xs-5 col-sm-offset-1 col-xs-offset-1">
			<div class="col-key">
				<i class="fa fa-fw fa-paper-plane-o"></i>
			</div>
		</div>
		<div id="message-cancel" class="col-sm-6 col-xs-6">
			<div class="col-key">
				<i class="fa fa-fw fa-arrow-right"></i>
			</div>
		</div>
	</div>
</div>
<div id="modal-pause" class="text-center">
	<h3>Pausar gestión</h3>
	<div class="row-fluid">
		<div id="pause-1-cutlery" class="col-sm-6 col-xs-6 box-pause">
			<div><i class="fa fa-cutlery fa-2x"></i></div>
			<div id="pause-1-text">Almuerzo</div>
		</div>
		<div id="pause-2-male" class="col-sm-6 col-xs-6 box-pause">
			<div><i class="fa fa-male fa-2x"></i></div>
			<div id="pause-2-text">Baño</div>
		</div>
	</div>
	<div class="row-fluid">
		<div id="pause-3-question" class="col-sm-6 col-xs-6 box-pause">
			<div><i class="fa fa-question fa-2x"></i></div>
			<div id="pause-3-text">Pregunta</div>
		</div>
		<div id="pause-4-users" class="col-sm-6 col-xs-6 box-pause">
			<div><i class="fa fa-users fa-2x"></i></div>
			<div id="pause-4-text">Reunión</div>
		</div>
	</div>
	<div class="row-fluid">
		<div id="pause-5-music" class="col-sm-6 col-xs-6 box-pause">
			<div><i class="fa fa-music fa-2x"></i></div>
			<div id="pause-5-text">Descanso</div>
		</div>
		<div id="pause-6-coffee" class="col-sm-6 col-xs-6 box-pause">
			<div><i class="fa fa-coffee fa-2x"></i></div>
			<div id="pause-6-text">Café</div>
		</div>
	</div>
</div>
<div id="modal-clock" class="text-center">
	<div class="row-fluid">
		<div id="pause-type">
			<div><i id="pause-type-font" class="fa fa-2x"></i></div>
			<div id="pause-type-text"></div>
		</div>
	</div>
	<div class="row-fluid">
		<div id="show-timer" class="col-sm-12 col-xs-12">00:00:00</div>
	</div>
	<div class="row-fluid">
		<div class="col-sm-12 col-xs-12">
			<div id="pause-stop" class="box-play">
				<i class="fa fa-play fa-4x"></i>
			</div>
		</div>
	</div>
</div>