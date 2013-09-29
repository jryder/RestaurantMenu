/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DatabaseException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MenuItem;
import model.Model;

/**
 *
 * @author jryder
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class RestaurantController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();

	try {
	    
	    Model model = new Model();

	    String s = request.getParameter("Action");

	    String order = null;
	    order = request.getParameter("placeOrder");

	    System.out.println(order);

	    if (order != null) { //actually placing an order

		model.calculateOrder(request);
		
		
		request.setAttribute("orderSubTotal", model.getOrderSubTotal());
		request.setAttribute("orderGrandTotal", model.getOrderGrandTotal());
		request.setAttribute("tax", model.getTax());



		request.setAttribute("menu", model.getMenuSelection());
		RequestDispatcher view =
			request.getRequestDispatcher("bill.jsp");

		view.forward(request, response);

	   } else if (request.getParameter("Delete") != null) {

	       int itemId = Integer.valueOf(request.getParameter("Delete"));
	       
	       //get the specified model
	       MenuItem mx = model.findItemById(itemId);
	           
	       
	       //delete record
	       model.deleteMenu(mx);
	      
	       
		List<MenuItem> menu = model.getMenuOptions(); //get menu options from model

		request.setAttribute("menu", menu);
		RequestDispatcher view =
			request.getRequestDispatcher("admin.jsp");

		view.forward(request, response);
		
		
	   } else if (request.getParameter("Add") != null) {

	       double price = Double.valueOf(request.getParameter("Price"));
	       String name = request.getParameter("Name");
	       
	       MenuItem newMenu = new MenuItem();
	       newMenu.setPrice(price);
	       newMenu.setName(name);
	       newMenu.setCalories(0);
	       
	       //delete record
	       model.insertMenu(newMenu);
	      
	       
		List<MenuItem> menu = model.getMenuOptions(); //get menu options from model

		request.setAttribute("menu", menu);
		RequestDispatcher view =
			request.getRequestDispatcher("admin.html");

		view.forward(request, response);		
		
		
		
	    } else if (s != null && s.equals("Admin")) {

		List<MenuItem> menu = model.getMenuOptions(); //get menu options from model

		request.setAttribute("menu", menu);
		RequestDispatcher view =
			request.getRequestDispatcher("admin.jsp");

		view.forward(request, response);
		
		
	    } else {

		List<MenuItem> menu = model.getMenuOptions(); //get menu options from model

		request.setAttribute("menu", menu);
		RequestDispatcher view =
			request.getRequestDispatcher("checkbox.jsp");

		view.forward(request, response);
		
		
	    }

	} catch (DatabaseException ex) {
	    Logger.getLogger(RestaurantController.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>
}
