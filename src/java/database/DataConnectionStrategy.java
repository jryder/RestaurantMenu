
package database;

import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * This is a strategy object for the data connection.  This requires that the 
 * subclass has both a query code execution, and an execution only method.
 * 
 * @author Jordan Ryder
 */
public interface DataConnectionStrategy {
    	

	public List<LinkedHashMap<String, String>> runQuery(String sql)throws DatabaseException;
	public abstract void runCode(String sql) throws DatabaseException;	
		
}
