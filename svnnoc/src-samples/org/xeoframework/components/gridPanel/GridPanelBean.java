package org.xeoframework.components.gridPanel;

import netgest.bo.runtime.boObjectList;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class GridPanelBean extends XEOBaseBean {
	
	DataListConnector dataSource;
	
	private String rowSelectionMode = "name";
	
	public DataListConnector getDataSource() {
		dataSource = new XEOObjectListConnector(boObjectList.list(this.getEboContext(), 
		"select Ebo_Perf where 1=1"));
		return dataSource;
	}
	
	public DataListConnector getDataSource2() {
		dataSource = new XEOObjectListConnector(boObjectList.list(this.getEboContext(), 
		"select Ebo_Attribute where 1=1"));
		return dataSource;
	}
	
	public DataListConnector getDataSource3() {
		dataSource = new XEOObjectListConnector(boObjectList.list(this.getEboContext(), 
		"select Ebo_Lov where 1=1"));
		return dataSource;
	}
	
	public void buttonClick() {
		
	}
	
	public void rowDoubleClick(  ) throws Exception {
		
	}
	
	public void rowClick(  ) throws Exception {
		
	}

	public void setRowSelectionMode(String rowSelectionMode) {
		this.rowSelectionMode = rowSelectionMode;
	}

	public String getRowSelectionMode() {
		return rowSelectionMode;
	}

}
