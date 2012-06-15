package xeo.code.java.lib1_0;

import java.sql.Date;
import java.util.Calendar;
import java.util.Enumeration;

import netgest.bo.runtime.AttributeHandler;
import netgest.bo.runtime.boEvent;
import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boRuntimeException;
import netgest.bo.runtime.bridgeHandler;

public class LIB_MovementHandler {

	/**
	 * 
	 * Before a new Movement is saved, change the state of all books
	 * 
	 * @param obj The Movement instance
	 * @param event The triggered event
	 * @return True if the movement can be saved
	 * 
	 * @throws boRuntimeException If an error occurs while updating 
	 * a book
	 */
	public boolean onBeforeSave(boObject obj,boEvent event) throws boRuntimeException {

		//Only execute this action
		if (!obj.exists()
				&& obj.getAttribute("state").getValueString().equals("0")) {
			//Retrieve the collection attribute and put the handler in
			//the first position
			bridgeHandler books = obj.getBridge("books");
			books.beforeFirst();
		
			//Iterate through all books and change state to 0 (see Lov values from LIB_Lov)
			while (books.next()) {
				boObject currentBook = books.getObject();
				currentBook.getAttribute("state").setValueString("0");
			}
		}
		else{ //If the Movement does exist
			
			//If it's closed, there's nothing else to do
			if ("1".equalsIgnoreCase(obj.getAttribute("state").getValueString()))
				return true;
			
			//Check if all returns have their books with the correct state
			bridgeHandler returnsCollection = obj.getBridge("returns");
			returnsCollection.beforeFirst();
			//Iterate through all returns to check if there are books which
			//haven't been updated
			while (returnsCollection.next()){
				boObject currentReturn = returnsCollection.getObject();
				//Get the books for the current return
				bridgeHandler booksOfReturnCollection = currentReturn.getBridge("books");
				booksOfReturnCollection.beforeFirst();
				//Iterate through them all
				while (booksOfReturnCollection.next()){
					boObject book = booksOfReturnCollection.getObject();
					//If there's a book that's unavailable (0) set it as available (1)
					if ("0".equalsIgnoreCase(book.getAttribute("state").getValueString())){
						book.getAttribute("state").setValueString("1");
					}
				}
			}
			
			//Finally we must check from all the books in the movement, if there's anyone
			//which hasn't been returned
			bridgeHandler booksCollection = obj.getBridge("books");
			boolean closed = true;
			booksCollection.beforeFirst();
			while (booksCollection.next()){
				boObject currentBook = booksCollection.getObject();
				if ("0".equalsIgnoreCase(currentBook.getAttribute("state").getValueString())){
					closed = false;
					break;
				}
			}
			//If we don't find any book to return, the movement can be closed 
			if (closed){
				obj.getAttribute("state").setValueString("1");
			}
		}
		
		
		//Return true so that the 
		return true;
	}

	/**
	 * 
	 * Disables all attributes of an existing movement
	 * 
	 * @param obj The instance movement
	 * @param event The event 
	 * 
	 * @throws boRuntimeException If a problem occurs while disabling the attributes
	 */
	public void onAfterLoad(boObject obj,boEvent event) throws boRuntimeException {
		
		//Only apply the condition if the object is saved
		if (obj.exists()) {
			
			//Get an enumeration of all attributes
			@SuppressWarnings("rawtypes")
			Enumeration atts = obj.getAllAttributes().elements();

			//Iterate through all of them and disable them
			while (atts.hasMoreElements()) {
				AttributeHandler currAtt = (AttributeHandler) atts.nextElement();
				currAtt.setDisabled();
			}
		}
	}

	/**
	 * 
	 * Default value for dueDate attribute, which is seven days from the current
	 * day
	 * 
	 * @param obj The instance movement
	 * @param attribute The attribute handler for dueDate
	 * 
	 * @return A string with the date seven days from today
	 * 
	 * @throws boRuntimeException
	 */
	public String dueDate_DefaultValue(boObject obj,AttributeHandler attribute) throws boRuntimeException {
		//Retrieve a calendar for current time
		Calendar dueDate = Calendar.getInstance();
		//Add seven days
		dueDate.add(Calendar.DATE, 7);
		//Return the date
		return new Date(dueDate.getTimeInMillis()).toString();
	}

	/**
	 * 
	 * Disables the return bridge if the movement is not created or if the 
	 * movement is already closed
	 * 
	 * @param obj
	 * @param bridge
	 * @return
	 * @throws boRuntimeException
	 */
	public boolean returns_DisableWhen(boObject obj,bridgeHandler bridge) throws boRuntimeException {
		if (!obj.exists())
			return true;
		else
		{
			if ("1".equalsIgnoreCase(obj.getAttribute("state").getValueString()))
				return true;
		}
		return false;
	}

	
}