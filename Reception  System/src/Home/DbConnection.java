

package Home;
//STEP 1. Import required packages
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
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