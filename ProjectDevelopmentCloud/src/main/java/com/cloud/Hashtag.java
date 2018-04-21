package com.cloud;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Hashtag implements java.io.Serializable{
	private String tag;

	public Hashtag(String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public static Boolean hashtagAlreadyExist(String s) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Filter keyFilter = new FilterPredicate("tag", FilterOperator.EQUAL, s);
  		
  		Query q = new Query("Hashtag").setFilter(keyFilter);

  		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
  		
  		if(!results.isEmpty()) {
  			return true;
  		}
  		return false;
	}
	
	public void createHashtag() {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  		Entity tag = new Entity("Hashtag",this.tag);
  		tag.setProperty("tag",this.tag);
  	
  		datastore.put(tag);
	}
}
