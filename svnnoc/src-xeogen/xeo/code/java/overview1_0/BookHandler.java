package xeo.code.java.overview1_0;

import netgest.bo.runtime.*;

public class BookHandler {

	
	
	public boolean onBeforeSave(boObject obj,boEvent event) throws boRuntimeException {
		
		if (!obj.exists()){
		//Retrieve the collection attribute
		bridgeHandler authors = obj.getBridge("authors");
		//Position the list in the first position
		authors.beforeFirst();
		//Iterate through all elements
		while (authors.next()){
			//Retrieve the current object
			boObject currentAuthor = authors.getObject();
			//Retrieve the handler for the attribute
			AttributeHandler numberBooks = currentAuthor.getAttribute("numberBooks");
			//Retrieve the attribute value
			Long currentNumber = numberBooks.getValueLong();
			//Check if the value is not null
			if (currentNumber != null){
				//Increment the number
				currentNumber++;
				//Set the value
				numberBooks.setValueLong(currentNumber);
			}
			else
				currentNumber = Long.valueOf(1); //If the value is
		}
		return true;
		}
		return true;	
	}


}