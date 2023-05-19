<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<%@include file="includes/head.jsp"%>
<style type="text/css">
.card-registration .select-input.form-control[readonly]:not([disabled])
	{
	font-size: 1rem;
	line-height: 2.15;
	padding-left: .75em;
	padding-right: .75em;
}

.card-registration .select-arrow {
	top: 13px;
	h3
	{
	text-align
	:
	center;
}
</style>

</head>
<body>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Student Registration</div>
			<div class="card-body">
				<form>

					<div class="row">
						<div class="col-md-6 mb-4">

							<div class="form-outline">
								<label>First Name</label> <input type="text" id="firstName"
									class="form-control form-control-lg" />
							</div>

						</div>
						<div class="col-md-6 mb-4">

							<div class="form-outline">
								<label>Last Name</label> <input type="text" id="lastName"
									class="form-control form-control-lg" />
							</div>

						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-4 pb-2">

							<div class="form-outline">
								<label class="form-label" for="username">User Name</label> <input
									type="text" id="username" class="form-control form-control-lg" />
							</div>

						</div>
						<div class="col-md-6 mb-4 pb-2">

							<div class="form-outline">
								<label class="form-label" for="password">Password</label> <input
									type="text" id="password" class="form-control form-control-lg" />
							</div>

						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-4 d-flex align-items-center">

							<div class="form-outline datepicker w-100">
								<label for="budget">Expense Budget</label> <input type="text"
									class="form-control form-control-lg" id="budget" />
							</div>

						</div>
						<div class="col-md-6 mb-4">

							<h6 class="mb-2 pb-1">Gender:</h6>

							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="femaleGender" value="option1"
									checked /> <label class="form-check-label" for="femaleGender">Female</label>
							</div>

							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="maleGender" value="option2" /> <label
									class="form-check-label" for="maleGender">Male</label>
							</div>

							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="inlineRadioOptions" id="otherGender" value="option3" />
								<label class="form-check-label" for="otherGender">Other</label>
							</div>

						</div>
					</div>



					<div class="row">
						<div class="col-md-6 mb-4 pb-2">

							<div class="form-outline">
								<label class="form-label" for="height">Height</label> <input
									type="text" id="height" class="form-control form-control-lg" />
							</div>

						</div>
						<div class="col-md-6 mb-4 pb-2">

							<div class="form-outline">
								<label class="form-label" for="weight">Weight</label> <input
									type="text" id="weight" class="form-control form-control-lg" />
							</div>

						</div>
					</div>

					<div class="row">
						<div class="col-md-6 mb-4">

							<div class="form-outline">
								<label class="form-label" for="emailAddress">Types of
									Diet</label>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Mediterranean </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Keto </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Vegetarian </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Vegan </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Low Carb </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> No Sugar </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Raw </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Paleo </label>
								</div>


							</div>

						</div>
						<div class="col-md-6 mb-4">

							<div class="form-outline">
								<label class="form-label" for="phoneNumber">Allergy</label>

								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Dairy </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Nuts </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Egg </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Soya </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Seafood </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value=""
										id="flexCheckDefault"> <label class="form-check-label"
										for="flexCheckDefault"> Gluten </label>
								</div>


							</div>

						</div>
					</div>

			<div class="mt-4 pt-2" >
						<div class="col-md-12 text-center">
							<a href="index.jsp" class="btn btn-primary">Submit</a>
								
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>

</body>
</html>
