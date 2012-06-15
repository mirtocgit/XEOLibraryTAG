package org.xeoframework.components.gridExplorer;

import netgest.bo.runtime.boObjectListBuilder;
import netgest.bo.xwc.components.annotations.Visible;
import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class GridExplorerBean extends XEOBaseBean {

	
	public DataListConnector getDataList(){
		return new XEOObjectListConnector(new boObjectListBuilder(getEboContext(), "select LIB_Book").build());
	}
	
	public void canCloseTab(){
		closeView();
	}
	
	@Visible
    public void closeView() {
    	XUIRequestContext oRequestContext;
		oRequestContext = XUIRequestContext.getCurrentContext();
		XVWScripts.closeView( oRequestContext.getViewRoot() );
    	XUIViewRoot viewRoot = oRequestContext.getSessionContext().createView("netgest/bo/xwc/components/viewers/Dummy.xvw");
    	oRequestContext.setViewRoot( viewRoot );
    	oRequestContext.renderResponse();
    }
	
}
