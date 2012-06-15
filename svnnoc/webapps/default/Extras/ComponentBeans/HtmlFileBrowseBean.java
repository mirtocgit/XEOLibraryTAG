package org.xeoframework.components.htmlFileBrowse;

import java.io.File;
import java.net.MalformedURLException;

import netgest.bo.xwc.components.classic.HTMLFileBrowse;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class HtmlFileBrowseBean extends XEOBaseBean {

	
	private String fileName = "";
	private long size = 0;
	
	public String getShowFileInfo() throws MalformedURLException {
		refreshPage();
		String res = "";
		
		res = "<br>File Name: " + fileName + "</br><br>File Size: " + size + " bytes</br>";
		
		return res;
	}
	
	public void refreshPage() throws MalformedURLException {
		
		XUIRequestContext r = XUIRequestContext.getCurrentContext();
		HTMLFileBrowse fb = (HTMLFileBrowse)r.getViewRoot().findComponent( HTMLFileBrowse.class );
		File file = fb.getSubmitedFile();
		
		if(file != null) { 
			fileName = file.getName();
			size = file.length();
		}
	}
	
}
