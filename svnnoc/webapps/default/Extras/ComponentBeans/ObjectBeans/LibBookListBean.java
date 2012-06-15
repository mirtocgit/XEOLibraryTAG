package org.example.viewer.beans;

import netgest.bo.xwc.components.classic.GridColumnRenderer;
import netgest.bo.xwc.components.classic.GridPanel;
import netgest.bo.xwc.components.connectors.DataFieldConnector;
import netgest.bo.xwc.components.connectors.DataRecordConnector;
import netgest.bo.xwc.xeo.beans.XEOBaseList;

public class LibBookListBean extends XEOBaseList {

	public LibBookListBean(){
	}
	
	
	
	public GridColumnRenderer getMyRenderer() {    
        
		return new GridColumnRenderer() {
			
			@Override
			public String render(GridPanel grid, DataRecordConnector record, DataFieldConnector field) {
				
				
				String state = field.getValue().toString();
				if(state.equals("1"))
					return "<img src='Extras/Icons/book_add.png' title='Available'/>";
				else
					return "<img src='Extras/Icons/book_delete.png' title='Unavailable'/>";
			}
			
         };
		
	}
	
	
}
