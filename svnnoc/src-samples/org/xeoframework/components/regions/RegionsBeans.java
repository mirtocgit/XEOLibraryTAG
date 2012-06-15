package org.xeoframework.components.regions;

import netgest.bo.runtime.boObjectList;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOMainAdminBean;

public class RegionsBeans extends XEOMainAdminBean {

	
	public DataListConnector getDataList(){
		return new XEOObjectListConnector(boObjectList.list(getEboContext(),"select Ebo_Perf"));
	}
	
}
