package org.xeoframework.demo;

import netgest.bo.runtime.ObjectListManager;
import netgest.bo.runtime.boObjectList;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOEditBean;

public class DemoBean extends XEOEditBean {

	/**
	 * 
	 * The data source for the panel
	 * 
	 * @return A list of Demo instances
	 */
	public DataListConnector getDataSource(){
		boObjectList list = ObjectListManager.list(getEboContext(), "select Demo");
		return new XEOObjectListConnector(list);
	}
	
	/**
	 * Opens a new viewer
	 */
	public void openViewer(){
		
		//Code to open a new viewer
		
	}
	
}
