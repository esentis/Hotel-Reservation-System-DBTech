

package Home;
//STEP 1. Import required packages
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	static CallableStatement  callstatement       = null;
	public static void main(String args[]) {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
							"it123973", "ad1e35c1368e4d298abae3a73f37a424");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}
	public  void getRooms() throws SQLException {
		Connection c = null;
		c = DriverManager
				.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
						"it123973", "ad1e35c1368e4d298abae3a73f37a424");
		String query = "{call getrooms ()}";
		callstatement = c.prepareCall(query);
		callstatement.executeQuery();
		ResultSet rooms = callstatement.getResultSet();

		while (rooms.next()) {
			rooms.getArray(1);
			System.out.println(rooms.getArray(3));
		}
		callstatement.close();
		c.close();
		System.out.println("Called from getRooms");
	}

	public  void getCustomer(String firstName, String lastName) throws SQLException {
		Connection c = null;
		c = DriverManager
				.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
						"it123973", "ad1e35c1368e4d298abae3a73f37a424");
		String query = "{call getcustomer (?,?)}";
		callstatement = c.prepareCall(query);
		callstatement.setString(1, lastName);
		callstatement.setString(2, firstName);
		callstatement.executeQuery();
		ResultSet customer = callstatement.getResultSet();
		while (customer.next()) {
			System.out.println(customer.getArray(1));
			System.out.println(customer.getArray(2));
			System.out.println(customer.getArray(3));
			System.out.println(customer.getArray(4));
		}
		callstatement.close();
		c.close();
		System.out.println("Called from getCustomer");
	}

	public  void addCustomer(String firstName, String lastName, String email) throws SQLException {
		Connection c = null;
		c = DriverManager
				.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
						"it123973", "ad1e35c1368e4d298abae3a73f37a424");
		String query = "{call addcustomer (?,?,?)}";
		callstatement = c.prepareCall(query);
		callstatement.setString(1, email);
		callstatement.setString(2, firstName);
		callstatement.setString(3, lastName);
		callstatement.executeQuery();
		ResultSet customer = callstatement.getResultSet();
		while (customer.next()) {
			System.out.println(customer.getArray(1));
		}
		callstatement.close();
		c.close();
		System.out.println("Called from addCustomer");
	}

	public  void findRoom(int roomNumber) throws SQLException {
		Connection c = null;
		c = DriverManager
				.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
						"it123973", "ad1e35c1368e4d298abae3a73f37a424");
		String query = "{call findroom (?)}";
		callstatement = c.prepareCall(query);
		callstatement.setInt(1, roomNumber);
		callstatement.executeQuery();
		ResultSet customer = callstatement.getResultSet();
		while (customer.next()) {
			System.out.println(customer.getArray(1));
			System.out.println(customer.getArray(2));
			System.out.println(customer.getArray(3));
			System.out.println(customer.getArray(4));
		}
		callstatement.close();
		c.close();
		System.out.println("Called from findRoom");
	}

	void createTable(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager
					.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
							"it123973", "ad1e35c1368e4d298abae3a73f37a424");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE Dummyy " +
					"(ID INT PRIMARY KEY     NOT NULL," +
					" NAME           TEXT    NOT NULL, " +
					" AGE            INT     NOT NULL, " +
					" ADDRESS        CHAR(50), " +
					" SALARY         REAL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}
}