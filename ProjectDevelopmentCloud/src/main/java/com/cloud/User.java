package com.cloud;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class User implements java.io.Serializable {
	private String pseudo;
	private String mdp;
	
	public User() {
		
	}

	public User(String pseudo, String mdp) {
		this.pseudo = pseudo;
		this.mdp = mdp;
	}
	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public void createUser() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  		Entity user = new Entity("User",this.pseudo);
  		user.setProperty("pseudo",this.pseudo);
  		user.setProperty("mdp",this.mdp);
  	
  		datastore.put(user);
	}
	
}
