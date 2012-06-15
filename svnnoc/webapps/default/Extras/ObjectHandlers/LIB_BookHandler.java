package xeo.code.java.lib1_0;

import netgest.bo.runtime.*;

public class LIB_BookHandler {

	public void removeAllCategories(boObject obj) throws boRuntimeException {
		//Check if the object is saved in the database, new objects cannot remove categories
		if(obj.exists()) {
			//Get the collection handler (bridgeHandler) for the categories attribute
			bridgeHandler categories = obj.getBridge("categories"); 
			//Truncate the collection, removing all elements
			categories.truncate();
			//Save the object
			obj.update();
		}
	}


}