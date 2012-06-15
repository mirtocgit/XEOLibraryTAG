package org.xeoframework.components.actionButton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import netgest.bo.xwc.components.annotations.Visible;
import netgest.bo.xwc.components.classic.Window;
import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.XUISessionContext;
import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

/**
 * 
 * A Bean to support the examples in the ActionButton 
 * 
 * @author Pedro Rio
 *
 */
public class ActionButtonBean extends XEOBaseBean {

	/**
	 * A counter for how many times the button was clicked
	 */
	private int count = 0;
	
	
	/**
	 * Increase the number of clicks of the button
	 */
	public void getButtonClick() {
		count++;
	}
	
	/**
	 * 
	 * Retrieves a text with the number of clicks in the button
	 * 
	 * @return
	 */
	public String getDisplayClick() {
		if(count == 1)
			return "<br> You clicked " + count + " time </br><br/>";
		else
			return "<br>You clicked " + count + " times</br><br/>";
	}
	
	
	/**
	 * Downloads a sample file
	 */
	public void downloadFile(){
		try {
			HttpServletResponse response = (HttpServletResponse) getRequestContext().getResponse();
			
	  	  	response.setContentType("application/pdf");
	  	  	response.setHeader("Content-disposition","attachment; filename=downloadExample.pdf"); 
    	  
			OutputStream out = response.getOutputStream();
			InputStream p = getRequestContext().getServletContext().getResourceAsStream("/Extras/fileExamples/examplePresentation.pdf");
			byte[] buff = new byte[2048];
			
			while (p.read(buff) > 0){
				out.write(buff);
			}
			getRequestContext().responseComplete();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
  		
  	  }
	
	/**
	 * Opens a form as window
	 */
	public void openWindowForm(){
		try{
			
		XUIRequestContext   oRequestContext;
        XUISessionContext   oSessionContext;
        XUIViewRoot         oViewRoot;

        oRequestContext = XUIRequestContext.getCurrentContext();
        oSessionContext = oRequestContext.getSessionContext();

        oViewRoot = oSessionContext.createView( "Samples/ActionButton/FormWindow.xvw" );
        
        oRequestContext.setViewRoot( oViewRoot );
        oViewRoot.processInitComponents();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void canCloseTab(){
		XUIRequestContext oRequestContext = XUIRequestContext.getCurrentContext();
        XUIViewRoot viewRoot = oRequestContext.getViewRoot();
        Window xWnd = (Window)viewRoot.findComponent(Window.class);
        if( xWnd != null ) {
                if( xWnd.getOnClose() != null ) {
                        xWnd.getOnClose().invoke( oRequestContext.getELContext(), null);
                }
        }
        XVWScripts.closeView( viewRoot );
        oRequestContext.getViewRoot().setRendered( false );
        oRequestContext.getViewRoot().setTransient( true );
        oRequestContext.renderResponse();
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
