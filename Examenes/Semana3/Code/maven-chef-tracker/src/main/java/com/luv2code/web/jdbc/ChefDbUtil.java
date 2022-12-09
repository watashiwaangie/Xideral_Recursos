package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ChefDbUtil {

	private DataSource dataSource;

	public ChefDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Chef> getChefs() throws Exception {
		
		List<Chef> chefs = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			// get a connection
			myConn = dataSource.getConnection();
			
			// create sql statement
			String sql = "select * from chef order by Apellido_";
			
			myStmt = myConn.createStatement();
			
			// execute query
			myRs = myStmt.executeQuery(sql);
			
			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("Nombre_");
				String lastName = myRs.getString("Apellido_");
				String email = myRs.getString("Correo");
				
				// create new chef object
				Chef tempChef = new Chef(id, firstName, lastName, email);
				
				// add it to the list of chefs
				chefs.add(tempChef);				
			}
			
			return chefs;		
		}
		finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			
			if (myStmt != null) {
				myStmt.close();
			}
			
			if (myConn != null) {
				myConn.close();   // doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addChef(Chef theChef) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			String sql = "insert into chef "
					   + "(Nombre_, Apellido_, Correo) "
					   + "values (?, ?, ?)";
			
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values for the chef
			myStmt.setString(1, theChef.getFirstName());
			myStmt.setString(2, theChef.getLastName());
			myStmt.setString(3, theChef.getEmail());
			
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Chef getChef(String theChefId) throws Exception {

		Chef theChef = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int chefId;
		
		try {
			// convert chef id to int
			chefId = Integer.parseInt(theChefId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to get selected chef
			String sql = "select * from chef where id=?";
			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, chefId);
			
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("Nombre_");
				String lastName = myRs.getString("Apellido_");
				String email = myRs.getString("Correo");
				
				// use the studentId during construction
				theChef = new Chef(chefId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find chef id: " + chefId);
			}				
			
			return theChef;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateChef(Chef theChef) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			
			// create SQL update statement
			String sql = "update chef "
						+ "set Nombre_=?, Apellido_=?, Correo=? "
						+ "where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setString(1, theChef.getFirstName());
			myStmt.setString(2, theChef.getLastName());
			myStmt.setString(3, theChef.getEmail());
			myStmt.setInt(4, theChef.getId());
			
			// execute SQL statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public void deleteChef(String theChefId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// convert chef id to int
			int chefId = Integer.parseInt(theChefId);
			
			// get connection to database
			myConn = dataSource.getConnection();
			
			// create sql to delete chef
			String sql = "delete from chef where id=?";
			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);
			
			// set params
			myStmt.setInt(1, chefId);
			
			// execute sql statement
			myStmt.execute();
		}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);
		}	
	}
}















