package com.cloud;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class Message implements java.io.Serializable {
	String contenu;
	Key parent;
	List<Hashtag> listHashtag;
	
	public Message () {
		
	}
	
	public Message (String contenu, Key cle) {
		this.contenu = contenu;
		this.parent = cle;
	}
	
	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public Key getParent() {
		return parent;
	}

	public void setParent(Key parent) {
		this.parent = parent;
	}

	public List<Hashtag> getListHashtag() {
		return listHashtag;
	}

	public void setListHashtag(List<Hashtag> listHashtag) {
		this.listHashtag = listHashtag;
	}

	public void createMessage() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  		Entity msg = new Entity("Message",this.parent);
  		msg.setProperty("contenu",this.contenu);
  		
  		detectHashtag();
  		//TODO ADD HASHTAG
  		datastore.put(msg);
	}
	
	public void detectHashtag() {
		List<String> list = new ArrayList<String>();
		if(this.contenu.contains("#")) {
			String s= "";
			Boolean currentHashtag = false;
			for(int i = 0; i < this.contenu.length(); i++) {
				if(currentHashtag) {
					if(this.contenu.charAt(i) == ' ') {
						currentHashtag = false;
						list.add(s);
						s ="";
					}else if (this.contenu.charAt(i) == '#') {
						list.add(s);
						s ="";
					}else {
						s += this.contenu.charAt(i);
					}
				}
				else if (this.contenu.charAt(i) == '#') {
					currentHashtag = true;
				}
			}
			
			for( String toAdd : list) {
				Hashtag h = new Hashtag(toAdd);
				this.listHashtag.add(h);
				if(!Hashtag.hashtagAlreadyExist(h.getTag())) {
					h.createHashtag();
				}
			}
		}
	}
}
