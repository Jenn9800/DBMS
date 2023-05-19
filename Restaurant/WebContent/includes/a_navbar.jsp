<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="a_add_rest.jsp">Admin Page</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="a_add_rest.jsp">Add Restaurant</a></li>
				<li class="nav-item"><a class="nav-link" href="a_all_rest.jsp">Restaurants<span class="badge badge-danger">${cart_list.size()}</span> </a></li>

			</ul>
		</div>
	</div>
</nav>