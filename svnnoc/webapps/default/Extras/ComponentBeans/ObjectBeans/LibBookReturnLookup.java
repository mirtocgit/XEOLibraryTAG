package org.example.viewer.beans;

import netgest.bo.runtime.boObjectList;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOBaseLookupList;

public class LibBookReturnLookup extends XEOBaseLookupList {

	
	private Long parentBoui;
	
	public void setParentBoui(Long val){
		parentBoui = val;
	}
	
	public Long getParentBoui(){
		return parentBoui;
	}
	
	public DataListConnector getBooks(){

		String boqlExpression = "select LIB_Book where state='0' and boui in (select LIB_Movement.books where boui = " +
		getParentBoui() + ")";
		
		boObjectList list = boObjectList.list(getEboContext(), boqlExpression);
		return new XEOObjectListConnector(list);

	}
	
}
