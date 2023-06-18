 
<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: DodgerBlue">
			<div>
				<a class="navbar-brand"  href="index.jsp">NCCU FitBud </a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
			</div>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">Today's Record<span class="badge badge-danger">${cart_list.size()}</span> </a></li>
				<li class="nav-item"><a class="nav-link" href="btrack.jsp">Expense Record</a></li>
				<li class="nav-item"><a class="nav-link" href="ctrack.jsp">Diet Record</a></li>
				<li class="nav-item"><a class="nav-link" href="p_info.jsp">Personal Info</a></li>
				
			</ul>
		</div>
	
</nav>