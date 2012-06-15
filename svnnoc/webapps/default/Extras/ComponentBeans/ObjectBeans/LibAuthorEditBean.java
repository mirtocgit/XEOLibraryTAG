package org.example.viewer.beans;

import netgest.bo.runtime.boObjectList;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class LibAuthorEditBean extends XEOEditBean {

	
		
	
	public DataListConnector getDataBooks(){
		String query = "select LIB_Book where authors = " + getXEOObject().getBoui();
		boObjectList list = boObjectList.list(getEboContext(), query);
		return new XEOObjectListConnector(list);
		
	}
	
}
