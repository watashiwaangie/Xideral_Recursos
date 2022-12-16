package com.luv2code.springboot.cruddemo.dao;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entity.Chef;

@Repository
public class ChefDAOJdbcImpl implements ChefDAO {

	@Autowired
	DataSource dataSource;

	@Override
	public List<Chef> findAll() {
		System.out.println("Implementación DAO con JDBC findAll(): " + dataSource);

		List<Chef> listaChefs = new ArrayList<>();

		String sql = "select * from chef";

		try (Connection myConn = dataSource.getConnection();
			Statement myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql);) {

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// create new student object
				Chef tempChef = new Chef(id, firstName, lastName, email);

				// add it to the list of students
				listaChefs.add(tempChef);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaChefs;
	}

	@Override
	public Chef findById(int theId) {
		
		System.out.println("Implementación DAO con JDBC findById(): ");
		Chef theChef = null;

		try (Connection myConn = dataSource.getConnection();
			PreparedStatement myStmt = createPreparedStatement(myConn, theId);
			ResultSet myRs = myStmt.executeQuery();) {

			// retrieve data from result set row
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				// use the studentId during construction
				theChef = new Chef(theId, firstName, lastName, email);
			} else {
				throw new SQLException("Could not find chef id: " + theId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theChef;
	}

	private PreparedStatement createPreparedStatement(Connection con, int chefId) throws SQLException {
		String sql = "select * from chef where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, chefId);
		return ps;
	}

	@Override
	public void save(Chef theChef) {
		String sql = "";

		if (theChef.getId() == 0)
			sql = "insert into chef (first_name, last_name, email) values (?, ?, ?)";
		else
			sql = "update chef set first_name=?, last_name=?, email=? where id=?";

		try (Connection myConn = dataSource.getConnection();
			 PreparedStatement myStmt = myConn.prepareStatement(sql)) {

			myStmt.setString(1, theChef.getFirstName());
			myStmt.setString(2, theChef.getLastName());
			myStmt.setString(3, theChef.getEmail());

			if (theChef.getId() != 0)
				myStmt.setInt(4, theChef.getId());

			myStmt.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(int theId) {
		
		try (Connection myConn = dataSource.getConnection(); 
			 PreparedStatement myStmt = myConn.prepareStatement("delete from chef where id=?")) {
			
			myStmt.setInt(1, theId);
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
