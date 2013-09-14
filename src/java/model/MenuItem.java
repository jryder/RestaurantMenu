/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jryder
 */
public class MenuItem {
    
    private double price;
    private String name;
    private int calories;

    public MenuItem(String name, double price, int calories){
        this.price = price;
        this.calories = calories;
        this.name = name;
    }
    
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    
    
    
    
}
