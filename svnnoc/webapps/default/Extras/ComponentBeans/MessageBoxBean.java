package org.xeoframework.components.messageBox;

import java.util.HashMap;
import java.util.Map;

import netgest.bo.xwc.components.classic.MessageBox;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class MessageBoxBean extends XEOBaseBean {
	
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
	
	public void showMessageBox(String buttonType) {
		String component = "myForm:testMessageBox" + buttonType;
		MessageBox mb = (MessageBox)getViewRoot().findComponent(component);
        mb.setMessageBoxType( getSelectedType() );
        mb.setTitle(getMessageBoxTitle());
        mb.setMessage(getMessageBoxMessage());
        //mb.setActions("['#{viewBean.yes}','#{viewBean.no}','#{viewBean.cancel}']");
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
		System.out.println("Yes");
	}
	public void no() {
		System.out.println("No");
	}
	public void cancel() {
		System.out.println("Cancel");
	}
	
}
