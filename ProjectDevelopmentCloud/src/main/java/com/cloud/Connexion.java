package com.cloud;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * Servlet implementation class Connexion
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter keyFilter = new FilterPredicate("pseudo", FilterOperator.EQUAL, request.getParameter("pseudo"));
  		Filter keyFilter2 = new FilterPredicate("mdp", FilterOperator.EQUAL, request.getParameter("mdp"));

  	    CompositeFilter connexionFilter = CompositeFilterOperator.and(keyFilter, keyFilter2);
  		
  		Query q = new Query("User").setFilter(connexionFilter);

  		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
  		
  		if(results.size() > 0 ) {
  			Entity e = results.get(0);
  			User u = new User(e.getProperty("pseudo").toString(), e.getProperty("mdp").toString());
  			HttpSession s = request.getSession(true);
  			s.setAttribute("user", u);
  			response.sendRedirect("/sendMessage");
  		}
  		else {
  			PrintWriter out = response.getWriter();
  			out.println("Identifiants erronés");
  		}
	}

}
