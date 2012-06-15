package org.xeoframework.components.dialogProgress;

import netgest.bo.xwc.xeo.beans.Dialogs;
import netgest.bo.xwc.xeo.beans.ProgressBean;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class ButtonDialogProgressBean extends XEOBaseBean {
	
	public ProgressBean p;
	
	public void executeAction() {
		createProgress();
	}

	public void createProgress() {
		p = Dialogs.createProgress("#{viewBean.executeProgress}",
				"Demonstration", "Sending...", "Starting...");

		p.setUpdateInterval(1);

	}

	public void executeProgress() throws InterruptedException {

		p.setWidth(500);
		
		for (int i = 0; i < 10; i++) {
			Thread.sleep(1000);
			p.setProgressPercent((i + 1) * 10);
			p.setProgressText("Processing ..." +((i + 1) * 10) + " %");
			
		}
		p.setProgressText("");

		p.setFinished(true);
	}

}
