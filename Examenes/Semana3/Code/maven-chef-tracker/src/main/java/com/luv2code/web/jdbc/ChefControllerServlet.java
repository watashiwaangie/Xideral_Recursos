package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/ChefControllerServlet")
public class ChefControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChefDbUtil chefDbUtil;
	
	@Resource(name="jdbc/chef_tracker") //SE COMENTO PARA HACER USO DE JNDI
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		// create our chef db util ... and pass in the conn pool / datasource
		try {
			//https://www.digitalocean.com/community/tutorials/tomcat-datasource-jndi-example-java
			//Context ctx = new InitialContext(); //USO DE JNDI
			//dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/javatechie"); //USO DE JNDI
			//System.out.println("Demo con JNDI, Datasource: "+dataSource);
			chefDbUtil = new ChefDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
						
			// if the command is missing, then default to listing students
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listChefs(request, response);
				break;
				
			case "ADD":
				addChef(request, response);
				break;
				
			case "LOAD":
				loadChef(request, response);
				break;
				
			case "UPDATE":
				updateChef(request, response);
				break;
			
			case "DELETE":
				deleteChef(request, response);
				break;
				
			default:
				listChefs(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	private void deleteChef(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read chef id from form data
		String theChefId = request.getParameter("chefId");
		
		// delete chef from database
		chefDbUtil.deleteChef(theChefId);
		
		// send them back to "list chefs" page
		listChefs(request, response);
	}

	private void updateChef(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read chef info from form data
		int id = Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// create a new chef object
		Chef theChef = new Chef(id, firstName, lastName, email);
		
		// perform update on database
		chefDbUtil.updateChef(theChef);
		
		// send them back to the "list chefs" page
		listChefs(request, response);
		
	}

	private void loadChef(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read chef id from form data
		String theChefId = request.getParameter("chefId");
		
		// get chef from database (db util)
		Chef theChef = chefDbUtil.getChef(theChefId);
		
		// place chef in the request attribute
		request.setAttribute("THE_CHEF", theChef);
		
		// send to jsp page: update-chef-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-chef-form.jsp");//REVISIÓN
		dispatcher.forward(request, response);		
	}

	private void addChef(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read chef info from form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");		
		
		// create a new chef object
		Chef theChef = new Chef(firstName, lastName, email);
		
		// add the chef to the database
		chefDbUtil.addChef(theChef);
				
		// send back to main page (the chef list)
		listChefs(request, response);
	}

	private void listChefs(
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get chefs from db util
		List<Chef> chefs = chefDbUtil.getChefs();
		
		for (Chef c:chefs) {
			System.out.println(c);
		}
		
		chefs.add(new Chef(999, "FirstName","lastName", "email@gmail"));
		// add chefs to the request
		request.setAttribute("LISTA_CHEFS", chefs);
		
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-chefs.jsp");//cambié
		dispatcher.forward(request, response);
	}

}













