package database;

/**
 * Custom exception class that handles all SQL/database related exceptions.
 * This simplifies the error handling process for the application.
 * 
 * @author jordan ryder
 */
public class DatabaseException extends Exception {
    public DatabaseException(String msg) {
        super(msg);
    }
   
    
}
