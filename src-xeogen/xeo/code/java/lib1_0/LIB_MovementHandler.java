package xeo.code.java.lib1_0;

import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;

import netgest.bo.runtime.*;

public class LIB_MovementHandler {

	public boolean onBeforeSave(boObject obj,boEvent event) throws boRuntimeException {
		
		//Only execute this action
        if (!obj.exists()
                && obj.getAttribute("state").getValueString().equals("0")) {
            //Retrieve the collection attribute and put the handler in
            //the first position
            bridgeHandler books = obj.getBridge("books");
            books.beforeFirst();
        
            //Iterate through all books and change state to 1 (see Lov values from LIB_Lov)
            while (books.next()) {
                boObject currentBook = books.getObject();
                currentBook.getAttribute("state").setValueString("1");
            }
        }
		
		return true;

		
	}

	public void onAfterLoad(boObject obj,boEvent event) throws boRuntimeException {
		
		//Only apply the condition if the object is saved
        if (obj.exists()) {
            
            //Get an enumeration of all attributes
            Enumeration atts = obj.getAllAttributes().elements();

            //Iterate through all of them and disable them
            while (atts.hasMoreElements()) {
                AttributeHandler currAtt = (AttributeHandler) atts.nextElement();
                currAtt.setDisabled();
            }
        }
		
	}

	public String books_DefaultValue(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		
		 //Retrieve a calendar for current time
        Calendar dueDate = Calendar.getInstance();
        //Add seven days
        dueDate.add(Calendar.DATE, 7);
        //Return the date
        return new Date(dueDate.getTimeInMillis()).toString();

		
	}
	
}

	
	

