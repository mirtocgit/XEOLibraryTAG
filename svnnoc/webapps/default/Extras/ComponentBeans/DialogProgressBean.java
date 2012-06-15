package org.xeoframework.components.dialogProgress;

import netgest.bo.xwc.xeo.beans.Dialogs;
import netgest.bo.xwc.xeo.beans.ProgressBean;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class DialogProgressBean extends XEOBaseBean {

	public ProgressBean p;
	private String textHtml = "Setting your preferences...";
	
	public void setTextHtml(String textHtml) {
		this.textHtml = textHtml;
	}

	public String getTextHtml() {
		return textHtml;
	}
	
	public void executeAction() throws InterruptedException {
		createProgress();
	}

	public void createProgress() {
		p = Dialogs.createProgress("#{viewBean.executeProgress}",
				"Demonstration", "Loading Preferences...", "Starting...");

		p.setUpdateInterval(1);

	}

	public void executeProgress() throws InterruptedException {

		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			p.setProgressPercent((i + 1) * 10);
			p.setProgressText(((i + 1) * 10) + " %");
		}
		p.setProgressText("");

		p.setFinished(true);
		setTextHtml("<font color='red'>Settings loaded.</font>");
	}
	
}
