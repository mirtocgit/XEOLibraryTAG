package org.xeoframework.components.editToolBar;

import netgest.bo.xwc.xeo.beans.XEOEditBean;

/**
 * 
 * Bean to exemplify an EditToolbar for Orphan Objects
 * 
 * @author Pedro Rio
 *
 */
public class NonOrphanExampleBean extends XEOEditBean {

	@Override
	public boolean getEditInOrphanMode(){
		return false;
	}
	
	
}
