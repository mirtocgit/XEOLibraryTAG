package org.xeoframework.components.attributes;

import java.awt.TextArea;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

import org.apache.xmlgraphics.image.loader.Image;

import netgest.bo.lovmanager.lovObject;
import netgest.bo.xwc.components.classic.AttributeOutput;
import netgest.bo.xwc.components.classic.AttributePassword;
import netgest.bo.xwc.xeo.beans.XEOBaseBean;
import netgest.io.iFile;

public class AttributesBean extends XEOBaseBean {
String text;
BigDecimal number;
boolean bol;
Date date;
Date dateTime;
iFile file;

Image image;
lovObject lov;
AttributeOutput output;
AttributePassword password;
TextArea area;
Time time;

String submit;

public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

public BigDecimal getNumber() {
	return number;
}

public void setNumber(BigDecimal number) {
	this.number = number;
}

public boolean isBol() {
	return bol;
}

public void setBol(boolean bol) {
	this.bol = bol;
}

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
}

public Date getDateTime() {
	return dateTime;
}

public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
}

public iFile getFile() {
	return file;
}

public void setFile(iFile file) {
	this.file = file;
}

public Image getImage() {
	return image;
}

public void setImage(Image image) {
	this.image = image;
}

public lovObject getLov() {
	return lov;
}

public void setLov(lovObject lov) {
	this.lov = lov;
}

public AttributeOutput getOutput() {
	return output;
}

public void setOutput(AttributeOutput output) {
	this.output = output;
}

public AttributePassword getPassword() {
	return password;
}

public void setPassword(AttributePassword password) {
	this.password = password;
}

public TextArea getArea() {
	return area;
}

public void setArea(TextArea area) {
	this.area = area;
}

public Time getTime() {
	return time;
}

public void setTime(Time time) {
	this.time = time;
}

public String getSubmit() {
	return submit;
}

public void setSubmit(String submit) {
	this.submit = submit;
}












}
