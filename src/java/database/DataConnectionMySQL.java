/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;


/**
 *
 * @author jryder
 */
public class DataConnectionMySQL {
   	private Connection conn;
	private String driverClassName;
	private String url;
	private String userName;
	private String password;
 		
    
    	public static void main(String[] args) {

		DataConnectionMySQL db = new DataConnectionMySQL();
		db.driverClassName = "com.mysql.jdbc.Driver";
		db.url = "jdbc:mysql://192.185.41.212:3306/jryder_java";
		db.userName = "jryder_java";
		db.password = "hearts";

		try {
			  Class.forName (db.driverClassName);
			  db.conn = DriverManager.getConnection(db.url, db.userName, db.password);
		}
                
		catch ( ClassNotFoundException cnfex ) {
		   System.err.println(
			  "Error: Failed to load JDBC driver!" );
		   cnfex.printStackTrace();
		   System.exit( 1 );  // terminate program
		}
		catch ( SQLException sqlex ) {
		   System.err.println( "Error: Unable to connect to database!" );
		   sqlex.printStackTrace();
		   System.exit( 1 );  // terminate program
		}
                
		Statement stmt = null;
		ResultSet rs = null;
                
                String sql = "SELECT * FROM Menu";

		try {
			stmt = db.conn.createStatement();
			rs = stmt.executeQuery(sql);
                                                
			int count = 0;
			while( rs.next() ) {
                      System.out.println("\nRecord No: " + (count + 1));
				System.out.println( "ID: " + rs.getInt("item_id") ); // named field
				System.out.println( "Description: " + rs.getString("description") ); // named field
				System.out.println( "Calories: " + rs.getString("calories") );
				System.out.println( "Price: " + rs.getBigDecimal("price") );
				count++;
			}
			System.out.println( "==================\n" + count + " records found." );
                                                
                        
		} catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
                    
			try {
				stmt.close();
				db.conn.close();
			} catch(Exception e) {
				System.out.println(e);
			}
		}

	}
    
}
