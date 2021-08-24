<nav id="flow-breadcrumb" class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand">Progreso:</a>
		</div>
		<ul class="nav navbar-nav">
			<g:each var="name" in="${totalTabs}" status="i">
				<li class="flow-step-${i + 1}"><a>${name}</a></li>
			</g:each>
		</ul>
	</div>
</nav>
