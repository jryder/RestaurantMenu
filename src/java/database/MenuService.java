
package database;


import java.util.List;
import model.MenuItem;

/**
 *
 * @author jorda_000
 */
public class MenuService {

    
    private MenuDAO menuDAO;

    public MenuService() {
	DataConnectionStrategy db = new DataConnectionMySQL();
	menuDAO = new MenuDAO(db);
    }
    
    public List<MenuItem> findAllMenuItems() throws DatabaseException {
	return menuDAO.findAllMenuItems();
	
	
	
    }

 
    public MenuItem findItemById(int menuId) throws DatabaseException, IllegalArgumentException {
	return menuDAO.findItemById(menuId);
    }



    public void insertMenu(MenuItem menuItem) throws DatabaseException {
	menuDAO.insertMenu(menuItem);
    }

 
    public void updateMenu(MenuItem menuItem) throws DatabaseException {
	menuDAO.updateMenu(menuItem);
    }

    
    public void deleteMenu(MenuItem menuItem) throws DatabaseException {
	menuDAO.deleteMenu(menuItem);
    }    
    
    
    
}
