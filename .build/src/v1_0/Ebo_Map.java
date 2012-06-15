package v1_0;

import netgest.bo.runtime.*;
import netgest.bo.data.*;
import netgest.bo.def.*;
import netgest.bo.security.*;
import netgest.utils.*;
import netgest.io.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.ArrayList;
import java.io.*;
import java.math.*;



public  class Ebo_Map extends boObject implements  Serializable {  

   
   
    public Handlerdescription description;    
   
    public netgest.bo.runtime.attributes.boAttributeString id;    
    public netgest.bo.runtime.attributes.boAttributeString name;    
    public netgest.bo.runtime.attributes.boAttributeString objectAttributeName;    
    public netgest.bo.runtime.attributes.boAttributeString value;    
    public netgest.bo.runtime.attributes.boAttributeString transformation;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENT;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENTCTX;    
    public netgest.bo.runtime.attributes.boAttributeNumber BOUI;    
    public netgest.bo.runtime.attributes.boAttributeString CLASSNAME;    
    public netgest.bo.runtime.attributes.boAttributeObject CREATOR;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTCREATE;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTSAVE;    
    public netgest.bo.runtime.attributes.boAttributeString SYS_ORIGIN;    
    public netgest.bo.runtime.attributes.boAttributeObject SYS_FROMOBJ;    
   
    public HandlerprimaryState primaryState;    
     
    public Ebo_Map() {
        super();
        bo_version      = "1.0";
        bo_name         = "Ebo_Map";
        bo_classregboui = "#BO.CLSREGBOUI#";
        bo_definition   = boDefHandler.getBoDefinition("Ebo_Map");
        bo_statemanager = bo_definition.getBoClsState() != null ? bo_definition.getBoClsState().getStateManager( this ) : null;  

        boAttributesArray atts = super.getAttributes();
        boAttributesArray stat = super.getStateAttributes();

       
        
       

       
        atts.add(description = new Handlerdescription(this));
        
        
       
        atts.add(id = new netgest.bo.runtime.attributes.boAttributeString(this,"id"));
        
        atts.add(name = new netgest.bo.runtime.attributes.boAttributeString(this,"name"));
        
        atts.add(objectAttributeName = new netgest.bo.runtime.attributes.boAttributeString(this,"objectAttributeName"));
        
        atts.add(value = new netgest.bo.runtime.attributes.boAttributeString(this,"value"));
        
        atts.add(transformation = new netgest.bo.runtime.attributes.boAttributeString(this,"transformation"));
        
        atts.add(PARENT = new netgest.bo.runtime.attributes.boAttributeObject(this,"PARENT"));
        
        atts.add(PARENTCTX = new netgest.bo.runtime.attributes.boAttributeObject(this,"PARENTCTX"));
        
        atts.add(BOUI = new netgest.bo.runtime.attributes.boAttributeNumber(this,"BOUI"));
        
        atts.add(CLASSNAME = new netgest.bo.runtime.attributes.boAttributeString(this,"CLASSNAME"));
        
        atts.add(CREATOR = new netgest.bo.runtime.attributes.boAttributeObject(this,"CREATOR"));
        
        atts.add(SYS_DTCREATE = new netgest.bo.runtime.attributes.boAttributeDate(this,"SYS_DTCREATE"));
        
        atts.add(SYS_DTSAVE = new netgest.bo.runtime.attributes.boAttributeDate(this,"SYS_DTSAVE"));
        
        atts.add(SYS_ORIGIN = new netgest.bo.runtime.attributes.boAttributeString(this,"SYS_ORIGIN"));
        
        atts.add(SYS_FROMOBJ = new netgest.bo.runtime.attributes.boAttributeObject(this,"SYS_FROMOBJ"));
        



       
        atts.add(primaryState = new HandlerprimaryState(this));
        stat.add(primaryState);
        
    }
		private BigDecimal verifyTransformer(ObjAttHandler att, BigDecimal boui)
    {
        try
        {
            netgest.bo.transformers.Transformer transfClass = null;
            if((transfClass = att.getDefAttribute().getTransformClassMap()) != null)
            {
                if(boui != null && att.getParent().getMode() != boObject.MODE_EDIT_TEMPLATE )
                {
                    boui = new BigDecimal(transfClass.transform(getEboContext(), att.getParent(), boui.longValue()));
                }
            }
        }
        catch (boRuntimeException e)
        {
            
        }
        return boui;
    }
    public void init() throws boRuntimeException {
        super.init();
        setModeView();
        if(p_mode == MODE_NEW && !p_clone)
        {
           
           
                
                    if( description.haveDefaultValue()) 
                    {
                    	if(description.defaultValue() != null)
                    	{
                        	description._setValue(boConvertUtils.convertToString(description.defaultValue(), description));
                        }
                        else
                        {
                        	description._setValue(null);
                        }
                        description.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                    }
                
            
           
                if( id.haveDefaultValue()) 
                {
                	if(id.defaultValue() != null)
                	{
                    	id._setValue(boConvertUtils.convertToString(id.defaultValue(), id));
                    }
                    else
                    {
                    	id._setValue(null);
                    }
                    id.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( name.haveDefaultValue()) 
                {
                	if(name.defaultValue() != null)
                	{
                    	name._setValue(boConvertUtils.convertToString(name.defaultValue(), name));
                    }
                    else
                    {
                    	name._setValue(null);
                    }
                    name.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( objectAttributeName.haveDefaultValue()) 
                {
                	if(objectAttributeName.defaultValue() != null)
                	{
                    	objectAttributeName._setValue(boConvertUtils.convertToString(objectAttributeName.defaultValue(), objectAttributeName));
                    }
                    else
                    {
                    	objectAttributeName._setValue(null);
                    }
                    objectAttributeName.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( value.haveDefaultValue()) 
                {
                	if(value.defaultValue() != null)
                	{
                    	value._setValue(boConvertUtils.convertToString(value.defaultValue(), value));
                    }
                    else
                    {
                    	value._setValue(null);
                    }
                    value.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( transformation.haveDefaultValue()) 
                {
                	if(transformation.defaultValue() != null)
                	{
                    	transformation._setValue(boConvertUtils.convertToString(transformation.defaultValue(), transformation));
                    }
                    else
                    {
                    	transformation._setValue(null);
                    }
                    transformation.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( PARENT.haveDefaultValue()) 
                {
                	if(PARENT.defaultValue() != null)
                	{
                    	PARENT._setValue(boConvertUtils.convertToBigDecimal(PARENT.defaultValue(), PARENT));
                    }
                    else
                    {
                    	PARENT._setValue(null);
                    }
                    PARENT.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( PARENTCTX.haveDefaultValue()) 
                {
                	if(PARENTCTX.defaultValue() != null)
                	{
                    	PARENTCTX._setValue(boConvertUtils.convertToBigDecimal(PARENTCTX.defaultValue(), PARENTCTX));
                    }
                    else
                    {
                    	PARENTCTX._setValue(null);
                    }
                    PARENTCTX.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( BOUI.haveDefaultValue()) 
                {
                	if(BOUI.defaultValue() != null)
                	{
                    	BOUI._setValue(boConvertUtils.convertToBigDecimal(BOUI.defaultValue(), BOUI));
                    }
                    else
                    {
                    	BOUI._setValue(null);
                    }
                    BOUI.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( CLASSNAME.haveDefaultValue()) 
                {
                	if(CLASSNAME.defaultValue() != null)
                	{
                    	CLASSNAME._setValue(boConvertUtils.convertToString(CLASSNAME.defaultValue(), CLASSNAME));
                    }
                    else
                    {
                    	CLASSNAME._setValue(null);
                    }
                    CLASSNAME.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( CREATOR.haveDefaultValue()) 
                {
                	if(CREATOR.defaultValue() != null)
                	{
                    	CREATOR._setValue(boConvertUtils.convertToBigDecimal(CREATOR.defaultValue(), CREATOR));
                    }
                    else
                    {
                    	CREATOR._setValue(null);
                    }
                    CREATOR.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_DTCREATE.haveDefaultValue()) 
                {
                	if(SYS_DTCREATE.defaultValue() != null)
                	{
                    	SYS_DTCREATE._setValue(boConvertUtils.convertToTimestamp(SYS_DTCREATE.defaultValue(), SYS_DTCREATE));
                    }
                    else
                    {
                    	SYS_DTCREATE._setValue(null);
                    }
                    SYS_DTCREATE.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_DTSAVE.haveDefaultValue()) 
                {
                	if(SYS_DTSAVE.defaultValue() != null)
                	{
                    	SYS_DTSAVE._setValue(boConvertUtils.convertToTimestamp(SYS_DTSAVE.defaultValue(), SYS_DTSAVE));
                    }
                    else
                    {
                    	SYS_DTSAVE._setValue(null);
                    }
                    SYS_DTSAVE.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_ORIGIN.haveDefaultValue()) 
                {
                	if(SYS_ORIGIN.defaultValue() != null)
                	{
                    	SYS_ORIGIN._setValue(boConvertUtils.convertToString(SYS_ORIGIN.defaultValue(), SYS_ORIGIN));
                    }
                    else
                    {
                    	SYS_ORIGIN._setValue(null);
                    }
                    SYS_ORIGIN.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( SYS_FROMOBJ.haveDefaultValue()) 
                {
                	if(SYS_FROMOBJ.defaultValue() != null)
                	{
                    	SYS_FROMOBJ._setValue(boConvertUtils.convertToBigDecimal(SYS_FROMOBJ.defaultValue(), SYS_FROMOBJ));
                    }
                    else
                    {
                    	SYS_FROMOBJ._setValue(null);
                    }
                    SYS_FROMOBJ.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
    
            if("Ebo_Map".equals(this.getName()))
            {
                calculateFormula(null);
            }
        }
    }
   
    
    public bridgeHandler getBridge( String bridgeName ) 
    {
        boBridgesArray    brig = super.getBridges();        	
        bridgeHandler     ret  = brig.get(bridgeName);
        if( ret == null )
        {
        
        	if( ret == null )
        	{
				ret = super.getBridge( bridgeName );
        	}
        }
        return ret;
    }
   
    
   
    

   
    public final class Handlerdescription extends AttributeHandler {
        private boolean isdbbinding=true;
        private String fieldvalue;
        public Handlerdescription(boObject parent) {
            super(parent,parent.getBoDefinition().getAttributeRef("description"));
        }
        
        public String getValueString() throws boRuntimeException {
            return boConvertUtils.convertToString(this.getValue(),this);
        }
        
            public String getValue() throws boRuntimeException {
                String ret = null;
                
                if (getParent().isCheckSecurity() && !this.hasRights())
	            	throw new boRuntimeException(Handlerdescription.class.getName() +
	        	".getValue()", "BO-3230", null, "");                      	                   	                                 

                fireEvent( boEvent.EVENT_BEFORE_GETVALUE, null );
                if(isdbbinding) {
                    ret = this.getParent().getDataRow().getString("DESCRIPTION");
                } else {
                    ret = fieldvalue;
                }
                fireEvent( boEvent.EVENT_AFTER_GETVALUE, ret );
                return ret;
            }
            
            public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
            {
                if(getParent().alreadyCalculated(table, dependence)) 
                {                       
                    if(getParent().isWaiting(table, "description"))
                    {
                       getParent().setCalculated(table, "description");
                       setValue(boConvertUtils.convertToString(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "description"))
                    {
                        getParent().setCalculated(table, "description");
                        setValue(boConvertUtils.convertToString(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("description"))
                    {
                        getParent().setCalculated(table, "description"); 
                    }
                    else
                    {
                        getParent().clear(table, "description"); 
                        setValue(boConvertUtils.convertToString(formula(), this), false );
                    } 
                }
                else
                {    
                    setValue(boConvertUtils.convertToString(formula(), this), false );                                    
                    if(getParent().onChangeSubmit("description"))
                    {
                        getParent().setWaiting(table, "description");
                        getParent().calculateFormula(new Hashtable(table), "description");
                        //getParent().setCalculated(table, "description");
                    }                    
                }
            }
            public void setValue(String  newval ) throws boRuntimeException
            {
                setValue( newval, true );
            }
            
            public void setValue(String newval, boolean recalc) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights()) 
	            	throw new boRuntimeException(Handlerdescription.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                 
                if(!ClassUtils.compare(newval,this.getValue())) {
                    String chgval = this.getValue();
                    
                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
											if(canAlter(recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL))
											{
	                        _setValue(newval);
				        	setInputType( recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL );
	                        
	                        if( recalc && getParent().onChangeSubmit("description"))
	                        {
	                            Hashtable table = new Hashtable();
	                            getParent().setCalculated(table, "description");
	                            getParent().calculateFormula(table, "description");
	                        }
						    //vou verificar se o objecto se encontra numa bridge
							if ( getParent().p_parentBridgeRow != null )
							{
								getParent().getParentBridgeRow().getBridge().lineChanged("description");
							}
	
                            fireAfterChangeEvent( this, chgval, newval);
											}
                    }
                }
            }
            
            private void setValue(String newval, Hashtable table) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights()) 
	            	throw new boRuntimeException(Handlerdescription.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                 
                if(!ClassUtils.compare(newval,this.getValue())) {
                    String chgval = this.getValue();
                    
                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
											if(canAlter(AttributeHandler.INPUT_FROM_INTERNAL))
											{
                        _setValue(newval);
                        setInputType( AttributeHandler.INPUT_FROM_INTERNAL );
                        
                        if(getParent().onChangeSubmit("description"))
                        {
                            getParent().setCalculated(table, "description");
                            getParent().calculateFormula(table, "description");
                        }

                        //vou verificar se o objecto se encontra numa bridge
						if ( getParent().p_parentBridgeRow != null )
						{
							getParent().getParentBridgeRow().getBridge().lineChanged("description");
						}

                        fireAfterChangeEvent( this, chgval, newval);
                    }
									}
                }
            }
            
            public void _setValue(String newval) throws boRuntimeException {
                if(isdbbinding) {
                    this.getParent().getDataRow().updateString("DESCRIPTION",newval);
                    getParent().setChanged(true);
                    super.setValid();
                } else {
                    fieldvalue = newval;
                }
            }
        
        public void setValueString(String value)  throws boRuntimeException 
        {
            
                this.setValue(boConvertUtils.convertToString(value,this));
            
        }

        public void setValueObject(Object value) throws boRuntimeException {
            
                this.setValue((String)value);
            
        }
        public Object getValueObject() throws boRuntimeException 
        {
            return this.getValue();
        }

        // SIMPLEDATATYPE

        
public  boolean disableWhen() throws boRuntimeException {
try {
return true;
}
catch (Exception e){}
return false;
}
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



        
    }
    

   
    public final class HandlerprimaryState extends boObjectStateHandler {
       
        public final String STATE_open="0";
        
        public final String STATE_resolution="1";
        
        public final String STATE_close="90";
        
        public final String STATE_cancel="95";
        
        public final String STATE_archive="99";
        
        public HandlerprimaryState(boObject bobj) {
            super(bobj,bobj.getBoDefinition().getBoClsState().getChildStateAttributes("primaryState"));
        }
    }

    
        
    
public  String[] getDependences(String attributeName) {
return null;
}
public  String[] addDefaultDependencesFields() {
return new String[] {"objectAttributeName"} ;
}
public  void calculateFormula(Hashtable table, String from) throws boRuntimeException {
if ((p_mode != MODE_EDIT_TEMPLATE)) 
{
callObjBridgeCalculate(from);
if ((from != null)) 
{
}
}
}
	
	
    
    
}
