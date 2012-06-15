package org.xeoframework.components.errorMessages;

import java.util.HashMap;
import java.util.Map;

import netgest.bo.xwc.components.classic.Window;
import netgest.bo.xwc.components.classic.scripts.XVWScripts;
import netgest.bo.xwc.framework.XUIMessage;
import netgest.bo.xwc.framework.XUIRequestContext;
import netgest.bo.xwc.framework.components.XUIViewRoot;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

/**
 * Bean to support the error messages examples
 * 
 * 
 * @author Pedro Rio
 * @author jquintas
 *
 */
public class ErrorMessagesBean extends XEOBaseBean {
	
	private String selectedType = "1";
	private String selectedBoxType = "11";
	private String title = "Error Message";
	private String body = "Example Error Message";
	private Map<String, String> messageType = new HashMap<String, String>();
	private Map<String, String> messageBoxType = new HashMap<String, String>();
	private String messageCode = "";
	
	public ErrorMessagesBean() {
		messageType.put(XUIMessage.TYPE_ALERT + "", "ALERT");
		messageType.put(XUIMessage.TYPE_MESSAGE + "", "MESSAGE");
		messageType.put(XUIMessage.TYPE_POPUP_MESSAGE + "", "POPUP_MESSAGE");
		
		messageBoxType.put(XUIMessage.SEVERITY_CRITICAL + "", "CRITICAL");
		messageBoxType.put(XUIMessage.SEVERITY_ERROR + "", "ERROR");
		messageBoxType.put(XUIMessage.SEVERITY_WARNING + "", "WARNING");
		messageBoxType.put(XUIMessage.SEVERITY_INFO + "", "INFO");
	}
	
	/**
	 * 
	 * Given a type of message retrieves the name of the public static variable to show in the code display
	 * 
	 * @param val The number representing the type of message
	 * @return A string with the name of the variable in the {@link XUIMessage} class
	 */
	private String getTypeFromNumber(int val){
		if (val == XUIMessage.TYPE_ALERT)
			return "XUIMessage.TYPE_ALERT";
		else if (val == XUIMessage.TYPE_MESSAGE)
			return "XUIMessage.TYPE_MESSAGE";
		else if (val == XUIMessage.TYPE_POPUP_MESSAGE)
			return "XUIMessage.TYPE_POPUP_MESSAGE";
		else
			return "";
	}
	
	/**
	 * 
	 * Given a type of box retrieves the name of the public static variable to show in the code display
	 * 
	 * @param val The number representing the type of message box
	 * @return A string with the name of the variable in the {@link XUIMessage} class
	 */
	private String getBoxTypeFromNumber(int val){
		if (val == XUIMessage.SEVERITY_CRITICAL)
			return "XUIMessage.SEVERITY_CRITICAL";
		else if (val == XUIMessage.SEVERITY_ERROR)
			return "XUIMessage.SEVERITY_ERROR";
		else if (val == XUIMessage.SEVERITY_INFO)
			return "XUIMessage.SEVERITY_INFO";
		else if (val == XUIMessage.SEVERITY_WARNING)
			return "XUIMessage.SEVERITY_WARNING";
		else
			return "";
	}
	
	
	/**
	 * Displays the message and gets the java code to display
	 */
	public void buttonClick() {
		XUIRequestContext.getCurrentContext().addMessage(
                "Bean",
                new XUIMessage(Integer.parseInt(getSelectedType()), Integer.parseInt(getSelectedBoxType()), 
                                title, body));
		String type = "";
		String boxType = "";
		
		int typeInt = Integer.parseInt(getSelectedType());
		int boxInt = Integer.parseInt(getSelectedBoxType());
		
		type = getTypeFromNumber(typeInt);
		boxType = getBoxTypeFromNumber(boxInt);
		
		//Set the XUI Code to show
		messageCode = "XUIRequestContext.getCurrentContext().addMessage( " +
                "\"Bean\","+
                " new XUIMessage("+type+","+ boxType+","+ 
                "                '"+ title+"', '"+body+"'));";
		
	}
	
	public void canCloseTab() {

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

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public String getSelectedType() {
		return selectedType;
	}
	
	public Map<String, String> getMessageType() {
		return messageType;
	}
	
	public Map<String, String> getMessageBoxType() {
		return messageBoxType;
	}

	public void setSelectedBoxType(String selectedBoxType) {
		this.selectedBoxType = selectedBoxType;
	}

	public String getSelectedBoxType() {
		return selectedBoxType;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}
	
	
	/**
	 * 
	 * Retrieves the Java Code to create the displayed message
	 * 
	 * @return
	 */
	public String getMessageCode(){
		return messageCode;
	}
}
