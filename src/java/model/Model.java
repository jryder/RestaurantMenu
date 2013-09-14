/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jryder
 */
public class Model {

    private double orderSubTotal;

    public double getTax() {
	return tax;
    }

    public double getOrderGrandTotal() {
	return orderGrandTotal;
    }

    public HashMap<String, String> getMenuSelection() {
	return menuSelection;
    }

    public double getOrderSubTotal() {
	return orderSubTotal;
    }

    public HashMap<String, String> getMenuOptions() {
	return menuOptions;
    }
    private double tax;
    private double orderGrandTotal;
    private static final double TAXRATE = 0.051;
    private HashMap<String, String> menuOptions;
    private HashMap<String, String> menuSelection;

    public Model() {

	menuOptions = new HashMap<>();
	menuSelection = new HashMap<>();
	
	menuOptions.put("Burger", "15.99");
	menuOptions.put("Salad", "12.99");
	menuOptions.put("IPA", "5.50");
	
	
    }

    public void calculateOrder(HttpServletRequest request) {


	//menuSelection = menuOptions; //set selection to all items by default

	System.out.println(menuSelection.toString());
	
	//loop through all of the items and see if they were checked
	//if they were checked, get the price and add new a new list
	Iterator it = menuOptions.entrySet().iterator();
	while (it.hasNext()) {
	    Map.Entry pairs = (Map.Entry) it.next();
	    //run through parameters and see if it exists
	    String x = request.getParameter(pairs.getKey().toString());

	    System.out.println(menuOptions.toString());
	    
	    if (x == null) {
		//System.out.println("removed " + pairs.getKey().toString());
	    } else {
		double d = Double.valueOf(pairs.getValue().toString());
		orderSubTotal += d;
		menuSelection.put(pairs.getKey().toString(), pairs.getValue().toString()); //add to selection
	    }
	    it.remove(); // avoids a ConcurrentModificationException	    
	}

	
	
	
	orderSubTotal =  (double)Math.round(orderSubTotal * 100 ) / 100;
	
	//now calculate tax and grand total
	tax = orderSubTotal * TAXRATE;
	tax = (double)Math.round(tax * 100 ) / 100;
	
	
	orderGrandTotal = orderSubTotal + tax;
	orderGrandTotal = (double)Math.round(orderGrandTotal * 100 ) / 100;

    }
}
