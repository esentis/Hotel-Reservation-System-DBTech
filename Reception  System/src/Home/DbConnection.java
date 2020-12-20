package Home;
//STEP 1. Import required packages
import java.sql.*;

import java.sql.Connection;


public class DbConnection {
	static CallableStatement callstatement = null;


	public static Connection getConnection() {
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://dblabs.iee.ihu.gr/it123973",
					"it123973", "ad1e35c1368e4d298abae3a73f37a424");

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
		return c;


	}
}