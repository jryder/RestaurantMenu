/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import model.MenuItem;




public class MenuDAO {
    
    private DataConnectionStrategy data;
    public MenuDAO(DataConnectionStrategy data) {
	this.data = data;
    }


    public List<MenuItem> findAllMenuItems() throws DatabaseException {


	String sqlString = "Select * "
		+ " From MenuItems";

	List<LinkedHashMap<String, String>> list = data.runQuery(sqlString);
	MenuItem menuItem = null;
	List<MenuItem> menu = new ArrayList<>();

	//loop through customer list and convert to objects
	for (Map m : list) {
	    menuItem = new MenuItem();

	    //add attributes to the customer class
	    int itemId = (int) m.get("tranID");
	    menuItem.setItemId(itemId);

	    //set other items
	    
	    
	    menu.add(menuItem);
	}
	return menu;

    }

    
    
    
    
 
    public MenuItem findItemById(int menuId) throws DatabaseException, IllegalArgumentException {
	List<LinkedHashMap<String, String>> list;
	
	
	String sqlString =   " Select item_id,description,price,calories from Menu "
		+ " where item_id = " + menuId;

	list = data.runQuery(sqlString);

	Map mp;

	try {
	    mp = list.get(0);
	} catch (IndexOutOfBoundsException e) {
	    throw new IllegalArgumentException("Invalid Menu Item: " + menuId); //if an incorrect transaction was passed in
	}

	MenuItem menuItem = new MenuItem();

	//add attributes to the customer class
	menuItem.setItemId((int) mp.get("item_id"));
	menuItem.setName((String) mp.get("description"));
	menuItem.setPrice((double) mp.get("price"));
	menuItem.setCalories((int) mp.get("calories"));
	//set other items

	
	return menuItem;
    }

    
    
    

    public void insertMenu(MenuItem menuItem) throws DatabaseException {

	//convert datetimes to timestamps, which are more database friendly
	//also handles the nulls appropriately

	String sqlString = "Insert into Menu(item_id,description,price,calories)"
		+ " Select " + menuItem.getItemId() + "," + menuItem.getName() + "," + menuItem.getPrice()+ "," + menuItem.getCalories();
	//run update

	data.runCode(sqlString);
    }

 
    public void updateMenu(MenuItem menu) throws DatabaseException {

	//convert datetimes to timestamps, which are more database friendly

	String sqlString = "UPDATE Menu"
		+ " SET description = '" + menu.getName() + "',"
		+ " price = " + menu.getPrice() + ","
		+ " calories = " + menu.getCalories()
		+ " WHERE item_id = " + menu.getItemId();
	//run update

	System.out.println(sqlString);
	data.runCode(sqlString);
    }

    
    public void deleteMenu(MenuItem menu) throws DatabaseException {
	
	String sqlString = "delete Menu"
		+ " WHERE item_id = " + menu.getItemId();

	System.out.println(sqlString);
	data.runCode(sqlString);
    }    
    
    
    
}
