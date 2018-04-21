package com.cloud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

/**
 * Servlet implementation class Inscription
 */
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  
		try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/createUser.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User u = new User(request.getParameter("pseudo"), request.getParameter("mdp"));

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  		Entity user = new Entity("User",request.getParameter("pseudo"));
  		user.setProperty("pseudo",request.getParameter("pseudo"));
  		user.setProperty("mdp",request.getParameter("mdp"));
  	
  		datastore.put(user);
		
		HttpSession s = request.getSession(true);
		s.setAttribute("user", u);
		response.sendRedirect("/sendMessage");
	}

}
