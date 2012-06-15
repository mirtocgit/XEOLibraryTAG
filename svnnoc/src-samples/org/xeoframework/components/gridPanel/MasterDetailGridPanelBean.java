package org.xeoframework.components.gridPanel;

import netgest.bo.runtime.boObject;
import netgest.bo.runtime.boObjectList;
import netgest.bo.runtime.boRuntimeException;
import netgest.bo.xwc.components.classic.GridPanel;
import netgest.bo.xwc.components.connectors.DataListConnector;
import netgest.bo.xwc.components.connectors.DataRecordConnector;
import netgest.bo.xwc.components.connectors.XEOObjectListConnector;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

/**
 * 
 * Bean to support the gridPanel Master Detail Example
 * 
 * @author Pedro Rio
 *
 */
public class MasterDetailGridPanelBean extends XEOBaseBean {

	/**
	 * The HTML content to display in the detail view
	 */
	private String content = "";
	
	/**
	 * Boui of the current select object
	 */
	private Long bouiCurrent = null;
	
	/**
	 * 
	 * Retrieves the List to display in the grid panel
	 * 
	 * @return A list of object
	 */
	public DataListConnector getDataSource(){
		boObjectList oBoObjectList = boObjectList.list(getEboContext(), "select LIB_Book");
		return new XEOObjectListConnector(oBoObjectList);
	}
	
	
	/**
	 * 
	 * Retrieves the HTML content of the detail view
	 * 
	 * @return A string with the HTML content to display
	 */
	public String getContent(){
		if (content == null){
			return "";
		}
		else
			return content;
	}
	
	public void rowClickComps(){
		
		try {
			GridPanel grid = (GridPanel)getViewRoot().findComponent("form:grid");
			DataRecordConnector currRow = grid.getActiveRow();
			if (currRow != null){
				this.bouiCurrent = Long.valueOf(currRow.getAttribute("BOUI").getDisplayValue());
				boObject toShow = boObject.getBoManager().loadObject(getEboContext(), bouiCurrent);
				StringBuilder b = new StringBuilder();
				
				b.append("<table>");
					b.append("<tr>");
						b.append("<td>");
							b.append("Nome");
						b.append("</td>");
						b.append("<td>");
							b.append(toShow.getTextCARDID());
						b.append("</td>");
					b.append("</tr>");
					b.append("<tr>");
						b.append("<td>");
							b.append("Data Criação");
						b.append("</td>");
						b.append("<td>");
							b.append(toShow.getAttribute("SYS_DTCREATE").getValueDate().toString());
						b.append("</td>");
				b.append("</tr>");
				b.append("</table>");
				
				content = b.toString();
			}
			else
				content = "";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (boRuntimeException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * When a user clicks in the row, loads the current object and shows details of the object
	 */
	public void rowClick(){
		
		try {
			GridPanel grid = (GridPanel)getViewRoot().findComponent("form:grid");
			DataRecordConnector currRow = grid.getActiveRow();
			long boui = Long.valueOf(currRow.getAttribute("BOUI").getDisplayValue());
			boObject toShow = boObject.getBoManager().loadObject(getEboContext(), boui);
			StringBuilder b = new StringBuilder();
			b.append(toShow.getTextCARDID());
			
			content = b.toString();
		} catch (boRuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
