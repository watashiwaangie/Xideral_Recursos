<!DOCTYPE html>
<html>

<head>
	<title>Update Chef</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-chef-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>Hemingway Restaurant</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Chef</h3>
		
		<form action="ChefControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="chefId" value="${THE_CHEF.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td><input type="text" name="firstName" 
								   value="${THE_CHEF.firstName}" /></td>
					</tr>

					<tr>
						<td><label>Last name:</label></td>
						<td><input type="text" name="lastName" 
								   value="${THE_CHEF.lastName}" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="email" 
								   value="${THE_CHEF.email}" /></td>
					</tr>
					
					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="ChefControllerServlet">Back to List</a>
		</p>
	</div>
</body>

</html>











