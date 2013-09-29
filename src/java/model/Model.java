/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DatabaseException;
import database.MenuService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jryder
 */
public class Model {
    
    private double tax;
    private double orderGrandTotal;
    private static final double TAXRATE = 0.051;
    private double orderSubTotal;

    List<MenuItem> menuOptions = new ArrayList();
    List<MenuItem> menuSelection = new ArrayList();
    MenuService menuService;
    
    //database actions
    
    
    
    
    public List<MenuItem> findAllMenuItems() throws DatabaseException {
	return menuService.findAllMenuItems();
	
	
	
    }

 
    public MenuItem findItemById(int menuId) throws DatabaseException, IllegalArgumentException {
	return menuService.findItemById(menuId);
    }



    public void insertMenu(MenuItem menuItem) throws DatabaseException {
	menuService.insertMenu(menuItem);
    }

 
    public void updateMenu(MenuItem menuItem) throws DatabaseException {
	menuService.updateMenu(menuItem);
    }

    
    public void deleteMenu(MenuItem menuItem) throws DatabaseException {
	menuService.deleteMenu(menuItem);
    }    
    
    
    
    
    
    public double getTax() {
	return tax;
    }

    public double getOrderGrandTotal() {
	return orderGrandTotal;
    }

    public List<MenuItem> getMenuSelection() {
	return menuSelection;
    }

    public double getOrderSubTotal() {
	return orderSubTotal;
    }

    public List<MenuItem> getMenuOptions() {
	return menuOptions;
    }

    public Model() throws DatabaseException {

	menuOptions = new ArrayList();
	menuSelection = new ArrayList();
	menuService = new MenuService();
	menuOptions = menuService.findAllMenuItems();
    }

    
    public void calculateOrder(HttpServletRequest request) {
	
	for (MenuItem m: menuOptions) {
	    String x = request.getParameter(String.valueOf(m.getName()));
	    
	    System.out.println("LKJ:LKJ:LKJ");
	    System.out.println(x);
	    
	    if (x != null) {
		double d = m.getPrice();
		orderSubTotal += d;
		menuSelection.add(m); //add to selection		
	    } else {
	    }   
	}

	
	
	
	orderSubTotal =  (double)Math.round(orderSubTotal * 100 ) / 100;
	
	//now calculate tax and grand total
	tax = orderSubTotal * TAXRATE;
	tax = (double)Math.round(tax * 100 ) / 100;
	
	orderGrandTotal = orderSubTotal + tax;
	orderGrandTotal = (double)Math.round(orderGrandTotal * 100 ) / 100;

    }
}
