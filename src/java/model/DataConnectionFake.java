/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jryder
 */
public class DataConnectionFake {
    
    
     private MenuItem[] menu = {
        new MenuItem("Cheeseburger with 17 strips of bacon",14.99,2100),
        new MenuItem("Salad",2.99,50),
        new MenuItem("IPA",5.50,100)};

    public MenuItem[] getMenu() {
        return menu;
    }
    
    
 
     
}
