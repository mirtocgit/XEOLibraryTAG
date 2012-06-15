package org.xeoframework.components.messageBox;

import java.util.HashMap;
import java.util.Map;

import netgest.bo.xwc.components.classic.MessageBox;
import netgest.bo.xwc.components.classic.grid.HTMLEntityDecoder;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class MessageBoxBean extends XEOBaseBean {
	
	final String BREAK_LINE = "zxpt";
	
	/**
	 * A variable to keep html content to be displayed
	 */
	private String content = "";
	
	/**
	 * A variable to keep the XML declaration of a message box to be displayed
	 */
	private String xmlContent = "";
	
	private Map<String, String> messageBoxType = new HashMap<String, String>();
	private Map<String, String> messageBoxButtton = new HashMap<String, String>();
	private String selectedType = MessageBox.MessageBoxType.ERROR.toString();
	private String selectedButton =MessageBox.MessageBoxButtons.OK.toString();
	private String messageBoxTitle = "Example Title";
	private String messageBoxMessage = "Example Message";
	
	public MessageBoxBean() {
		super();
		
		messageBoxType.put(MessageBox.MessageBoxType.ERROR.toString(), MessageBox.MessageBoxType.ERROR.toString());
		messageBoxType.put(MessageBox.MessageBoxType.INFO.toString(), MessageBox.MessageBoxType.INFO.toString());
		messageBoxType.put(MessageBox.MessageBoxType.QUESTION.toString(), MessageBox.MessageBoxType.QUESTION.toString());
		messageBoxType.put(MessageBox.MessageBoxType.WARNING.toString(), MessageBox.MessageBoxType.WARNING.toString());
		
		messageBoxButtton.put(MessageBox.MessageBoxButtons.OK.toString(), MessageBox.MessageBoxButtons.OK.toString());
		messageBoxButtton.put(MessageBox.MessageBoxButtons.OKCANCEL.toString(), MessageBox.MessageBoxButtons.OKCANCEL.toString());
		messageBoxButtton.put(MessageBox.MessageBoxButtons.YES.toString(), MessageBox.MessageBoxButtons.YES.toString());
		messageBoxButtton.put(MessageBox.MessageBoxButtons.YESNO.toString(), MessageBox.MessageBoxButtons.YESNO.toString());
		messageBoxButtton.put(MessageBox.MessageBoxButtons.YESNOCANCEL.toString(), MessageBox.MessageBoxButtons.YESNOCANCEL.toString());
	}



	public Map<String, String> getMessageBoxType() {
		return messageBoxType;
	}
	
	public Map<String, String> getMessageBoxButton() {
		return messageBoxButtton;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedButton(String selectedButton) {
		this.selectedButton = selectedButton;
	}

	public String getSelectedButton() {
		return selectedButton;
	}
	
	public void buttonClick() {
		showMessageBox(getSelectedButton());
	}
	
	/**
	 * 
	 * Displays a message box with the selected options from the
	 * viewer and creates the XML representing that message box
	 * 
	 * @param buttonType
	 */
	public void showMessageBox(String buttonType) {
		
		
		String component = "myForm:testMessageBox" + buttonType;
		MessageBox mb = (MessageBox)getViewRoot().findComponent(component);
		
		//Build the messageBox XML
		StringBuilder b = new StringBuilder();
		
		b.append("<xvw:messageBox");
		
		b.append(" id='"+component+"' " + BREAK_LINE);
		b.append(" title='"+mb.getTitle()+"' " + BREAK_LINE);
		b.append(" message='"+mb.getMessage()+"' " + BREAK_LINE);
		b.append(" buttons='"+mb.getButtons()+"' " + BREAK_LINE);
		b.append(" actions='"+mb.getActions()+"' " + BREAK_LINE);
		b.append(" messageBoxType='"+mb.getMessageBoxType()+"' " + BREAK_LINE);
		b.append(">");
		b.append("</xvw:messageBox>");
		
		b.append(BREAK_LINE);
		b.append(BREAK_LINE);
		
		b.append("Java Code to display the message " + BREAK_LINE);
		b.append("MessageBox mb = (MessageBox)getViewRoot().findComponent(\""+component+"\");" + BREAK_LINE);
		b.append("mb.show();");
		
		xmlContent = HTMLEntityDecoder.charsToHtmlEntity(b);
		xmlContent = xmlContent.replace(BREAK_LINE, "<br>");
		
		//Show the actual messagebox
		mb.setMessageBoxType( getSelectedType() );
        mb.setTitle(getMessageBoxTitle());
        mb.setMessage(getMessageBoxMessage());
        mb.show();
        
        
        
	}

	public void showCustomBox(){
		String component = "myForm:testMessageBoxCustomBtns";
		
		StringBuilder b = new StringBuilder();
		
		b.append("<xvw:messageBox");
		
		b.append(" id='"+component+"' " + BREAK_LINE);
		b.append(" title='Example Title' " + BREAK_LINE);
		b.append(" message='Example message' " + BREAK_LINE);
		b.append(" messageBoxType='INFO' " + BREAK_LINE);
		b.append(">");
		b.append("</xvw:messageBox>");
		
		b.append(BREAK_LINE);
		b.append(BREAK_LINE);
		
		b.append("Java Code to display the message " + BREAK_LINE);
		b.append("MessageBox mb = (MessageBox)getViewRoot().findComponent(\""+component+"\");" + BREAK_LINE);
		b.append("mb.show();");
		
		
		xmlContent = HTMLEntityDecoder.charsToHtmlEntity(b);
		xmlContent = xmlContent.replace(BREAK_LINE, "<br>");
		
		MessageBox mb = (MessageBox)getViewRoot().findComponent(component);
        mb.show();
	}
	
	public void setMessageBoxTitle(String messageBoxTitle) {
		this.messageBoxTitle = messageBoxTitle;
	}

	public String getMessageBoxTitle() {
		return messageBoxTitle;
	}

	public void setMessageBoxMessage(String messageBoxMessage) {
		this.messageBoxMessage = messageBoxMessage;
	}
	
	public String getMessageBoxMessage() {
		return messageBoxMessage;
	}
	
	public void yes() {
		content = "Yes was pressed";
	}
	public void no() {
		content = "No was pressed";
	}
	public void cancel() {
		content = "Cancel was pressed";
	}
	
	public void ok(){
		content = "Ok was pressed";
	}
	
	/**
	 * 
	 * Retrieves an HTML String to display in the viewer
	 * 
	 * @return
	 */
	public String getContent(){
		return content;
	}
	
	public String getXmlCurrentBox(){
		return xmlContent;
		
	}
	
	/**
	 * Action for a custom button
	 */
	public void btn1Action(){
		content = "<span style='color:blue'>Button *Action* was pressed</span>";
	}
	
	/**
	 * Another action for a custom button
	 */
	public void btn2Action(){
		content = "<span style='color:red'>Button *Test* was pressed</span>";
	}
	
}
