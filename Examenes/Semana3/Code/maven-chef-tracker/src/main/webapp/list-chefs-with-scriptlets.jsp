<%@ page import="java.util.*, com.luv2code.web.jdbc.*" %>
<!DOCTYPE html>
<html>

<head>
	<title>Student Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<%
	// get the students from the request object (sent by servlet)
	List<Chef> theChefs = 
							(List<Chef>) request.getAttribute("CHEF_LIST");
%>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Hemingway Restaurant</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
				</tr>
				
				<% for (Chef tempChef : theChefs) { %>
				
					<tr>
						<td> <%= tempChef.getFirstName() %> </td>
						<td> <%= tempChef.getLastName() %> </td>
						<td> <%= tempChef.getEmail() %> </td>
					</tr>
				
				<% } %>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








