package org.example.viewer.beans;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;


public class MyBean extends XEOBaseBean {
	
	private String html;
    
    public String getHtml(){
        
       String result = "<b>";
       result += Math.random();    
        result += "</b>";
      html = result;
     return html;
  }

}
