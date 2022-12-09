<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Chefs Tracker App</title>
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Chefs List</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!-- put new button: Add Student -->
			
			<input type="button" value="Agregar Chef" 
				   onclick="window.location.href='add-chef-form.html'; return false;"
				   class="add-chef-button"
			/>
			
			<table>
			
				<tr>
					<th>Nombre</th>
					<th>Apellido</th>
					<th>Correo</th>
					<th>Actualizar</th>
				</tr>
				
				<c:forEach var="tempChef" items="${LISTA_CHEFS}"> 
					
					<!-- set up a link for each student -->
					<c:url var="tempLink" value="ChefControllerServlet">
						<c:param name="command" value="LOAD" />
						<c:param name="chefId" value="${tempChef.id}" />
					</c:url>

					<!--  set up a link to delete a student -->
					<c:url var="deleteLink" value="ChefControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="chefId" value="${tempChef.id}" />
					</c:url>
																		
					<tr>
						<td> ${tempChef.firstName} </td>
						<td> ${tempChef.lastName} </td>
						<td> ${tempChef.email} </td>
						<td> 
							<a href="${tempLink}">Actualizar</a> 
							 
							<a href="${deleteLink}"
							onclick="if (!(confirm('Seguro que quiere eliminar a este chef?'))) return false">
							Borrar</a>	
						</td>
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








