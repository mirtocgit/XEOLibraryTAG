package org.xeoframework.components.progressBar;

import netgest.bo.xwc.xeo.beans.XEOBaseBean;

public class ProgressBarBean extends XEOBaseBean {
	
	private Float progress = (float) 0;
	private String percentage = "0%";
	private String stepNumber = "You are currently at the begining. Please press 'Next' to continue.";
	private String buttonText = "Start";
	private boolean previousDisabled = true;
	private boolean nextDisabled = false;
	int step = 0;
	
	public void buttonClickPre() {
		if(step > 0) {
			step--;
			setProgress("-");
			setPercentage("");
			setStepNumber("");
		}
		setPreviousDisabled();
		setNextDisabled();
	}
	
	public void buttonClickNext() {
		if(step < 10) {
			step++;
			setProgress("+");
			setPercentage("");
			setStepNumber("");
		}
		setNextDisabled();
		setPreviousDisabled();
	}
	
	public void setProgress(String type) {
		if(type.equals("+"))
			this.progress += (float) 0.100000000000;
		else
			this.progress -= (float) 0.100000000000;
	}

	public Float getProgress() {
		return progress;
	}

	public void setPercentage(String percentage) {
		this.percentage = (step * 10) + "%";
	}

	public String getPercentage() {
		return percentage;
	}

	public void setStepNumber(String stepNember) {
		this.stepNumber = "You are currently in step " + step +"/10.";
	}

	public String getStepNumber() {
		return stepNumber;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = "Step " + step;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setPreviousDisabled() {
		if(step == 0)
			this.previousDisabled = true;
		else
			this.previousDisabled = false;
		}

	public boolean isPreviousDisabled() {
		return previousDisabled;
	}

	public void setNextDisabled() {
		if(step == 10)
			this.nextDisabled = true;
		else
			this.nextDisabled = false;
	}

	public boolean isNextDisabled() {
		return nextDisabled;
	}
	
}
