package org.example.viewer.beans;


import netgest.bo.runtime.boObjectList;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class LibPublisherEditBean extends XEOEditBean {

	/**
	 * 
	 * Retrieves the list of books for this publisher
	 * 
	 * @return
	 */
	public DataListConnector getDataList(){
		String boqlExpression = "select LIB_book where publisher = " + getXEOObject().bo_boui;
		boObjectList listBooksOfPublisher = boObjectList.list(getEboContext(), boqlExpression);
		return new XEOObjectListConnector(listBooksOfPublisher);
	}
	
	
}
