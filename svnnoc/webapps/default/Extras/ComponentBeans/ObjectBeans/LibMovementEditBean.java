package org.example.viewer.beans;

import netgest.bo.runtime.boObjectList;
import netgest.bo.runtime.boRuntimeException;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class LibMovementEditBean extends XEOEditBean {

	/**
	 * The list of books to return
	 */
	@SuppressWarnings("unused")
	private DataListConnector books;
	
	/**
	 * 
	 * Checks if the movement has a state defined 
	 * 
	 * @return True if movement has a state and false otherwise
	 */
	public boolean getMovementInitated(){
		
		try {
			if (!getXEOObject().exists())
				return false;
		} catch (boRuntimeException e) {
			log.severe(e);
		} 
		return true;
	}
	
	
	/**
	 * 
	 * Retrieves the list of books that haven't been returned
	 * 
	 * @return A list of books not returned in this movement
	 */
	public DataListConnector getBooks(){
		
			long boui = getXEOObject().getBoui();
			String xeoql = "select LIB_Movement.books where boui = " + boui + " and books.state = '0'";
			boObjectList result = boObjectList.list(getEboContext(), xeoql);
			return new XEOObjectListConnector(result);
	}
	
	
	
}
