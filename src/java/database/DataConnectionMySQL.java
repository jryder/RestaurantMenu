/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


/**
 *
 * @author jryder
 */
public class DataConnectionMySQL implements DataConnectionStrategy{
   private Connection conn;
    private String driverClassName = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://192.185.41.21:3306/jryder_java";
    private String userName = "jryder_java";
    private String password = "hearts!@#$$4";
    List<LinkedHashMap<String, String>> link;
    Statement stmt = null;
    
    
    /**
     * This runs any SQL code on the current connection.  All exceptions are 
     * converted to DatabaseExceptions, for easy error handling.  This does not
     * return any results, it is a query execution only. (inserts, updates, deletes)
     * 
     * @param sql
     * @throws DatabaseException 
     */
    @Override
    public void runCode(String sql)
            throws DatabaseException {
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, userName, password);
            
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException("Could not load JDBC driver");
        }
        catch(SQLException sq){
            throw new DatabaseException("Connection Failed");
        }
  
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);      
        } catch (SQLException sqle) {
            throw new DatabaseException("Execution of SQL failed");
        } catch (Exception e) {
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                throw new DatabaseException("Unknown error");
            }
        }
    }
  
    
    
    
    
    
    
    
    /**
     * Runs query and returns the results.  This is a query, which means that it will
     * return results.  The results are converted to a List of LinkedHashMaps.
     * 
     * @param sql
     * @return
     * @throws DatabaseException 
     */
    @Override
    public List<LinkedHashMap<String, String>> runQuery(String sql)
            throws DatabaseException {
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, userName, password);
            
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException("Could not load JDBC driver");
        }
        catch(SQLException sq){
            throw new DatabaseException("Connection Failed");
        }
  
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            link = resultSetToArrayList(rs);
      
        } catch (SQLException sqle) {
            throw new DatabaseException("Execution of SQL failed");
        } catch (Exception e) {
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                throw new DatabaseException("Unknown error");
            }
        }
        return link;
    }

    
    
    
    
    
    
    
    
    /**
     * This converts a resultset into a linkedHashMap. I considered just using a
     * regular hashmap, but wasn't sure if I would need to be able to iterate
     * through it in the same order
     *
     * @param rs
     * @return
     * @throws SQLException
     */
    private List<LinkedHashMap<String, String>> resultSetToArrayList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        ArrayList list = new ArrayList(50);
        while (rs.next()) {
            LinkedHashMap row = new LinkedHashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }

    
    
    
    
    /**
     * Testing only
     * 
     * @param args
     * @throws DatabaseException 
     */
    public static void main(String[] args) throws DatabaseException {

        //retrieve record from the database
        DataConnectionMySQL s = new DataConnectionMySQL();
        
        // <Awesomeness>
        // Well... everything here seems to be working quite nice
        List<LinkedHashMap<String, String>> link = s.runQuery("Select * from Menu");

        // </Awesomeness>
        
        
        String printString = "";
        //loop through all rows
        for (LinkedHashMap h : link) {
            //loop through all the values
            Collection c = h.values();
            Iterator itr = c.iterator();
            int i = 0;
            while (itr.hasNext()) {
                printString = printString + itr.next();
                i++;
                //print commas inbetween
                if (i != h.size()) {
                    printString = printString + ",";
                } else {
                    printString = printString + "\n";
                }
            }
        }

        System.out.println(printString);


    }
}

    