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



public  class LIB_Message extends boObject implements  Serializable {  

   
   
    public Handlerdate date;    
   
    public netgest.bo.runtime.attributes.boAttributeString subject;    
    public netgest.bo.runtime.attributes.boAttributeString bodyMessage;    
    public netgest.bo.runtime.attributes.boAttributeString urgent;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENT;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENTCTX;    
    public netgest.bo.runtime.attributes.boAttributeObject TEMPLATE;    
    public netgest.bo.runtime.attributes.boAttributeNumber BOUI;    
    public netgest.bo.runtime.attributes.boAttributeString CLASSNAME;    
    public netgest.bo.runtime.attributes.boAttributeObject CREATOR;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTCREATE;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTSAVE;    
    public netgest.bo.runtime.attributes.boAttributeString SYS_ORIGIN;    
    public netgest.bo.runtime.attributes.boAttributeObject SYS_FROMOBJ;    
   
     
    public LIB_Message() {
        super();
        bo_version      = "1.0";
        bo_name         = "LIB_Message";
        bo_classregboui = "#BO.CLSREGBOUI#";
        bo_definition   = boDefHandler.getBoDefinition("LIB_Message");
        bo_statemanager = bo_definition.getBoClsState() != null ? bo_definition.getBoClsState().getStateManager( this ) : null;  

        boAttributesArray atts = super.getAttributes();
        boAttributesArray stat = super.getStateAttributes();

       
        
        
        atts.add(new boBridgeMasterAttribute(this,this.getBoDefinition().getAttributeRef("recipients"))); 

       
        atts.add(date = new Handlerdate(this));
        
        
       
        atts.add(subject = new netgest.bo.runtime.attributes.boAttributeString(this,"subject"));
        
        atts.add(bodyMessage = new netgest.bo.runtime.attributes.boAttributeString(this,"bodyMessage"));
        
        atts.add(urgent = new netgest.bo.runtime.attributes.boAttributeString(this,"urgent"));
        
        atts.add(PARENT = new netgest.bo.runtime.attributes.boAttributeObject(this,"PARENT"));
        
        atts.add(PARENTCTX = new netgest.bo.runtime.attributes.boAttributeObject(this,"PARENTCTX"));
        
        atts.add(TEMPLATE = new netgest.bo.runtime.attributes.boAttributeObject(this,"TEMPLATE"));
        
        atts.add(BOUI = new netgest.bo.runtime.attributes.boAttributeNumber(this,"BOUI"));
        
        atts.add(CLASSNAME = new netgest.bo.runtime.attributes.boAttributeString(this,"CLASSNAME"));
        
        atts.add(CREATOR = new netgest.bo.runtime.attributes.boAttributeObject(this,"CREATOR"));
        
        atts.add(SYS_DTCREATE = new netgest.bo.runtime.attributes.boAttributeDate(this,"SYS_DTCREATE"));
        
        atts.add(SYS_DTSAVE = new netgest.bo.runtime.attributes.boAttributeDate(this,"SYS_DTSAVE"));
        
        atts.add(SYS_ORIGIN = new netgest.bo.runtime.attributes.boAttributeString(this,"SYS_ORIGIN"));
        
        atts.add(SYS_FROMOBJ = new netgest.bo.runtime.attributes.boAttributeObject(this,"SYS_FROMOBJ"));
        



       
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
           
           
                
                    if( date.haveDefaultValue()) 
                    {
                    	if(date.defaultValue() != null)
                    	{
                        	date._setValue(boConvertUtils.convertToTimestamp(date.defaultValue(), date));
                        }
                        else
                        {
                        	date._setValue(null);
                        }
                        date.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                    }
                
            
           
                if( subject.haveDefaultValue()) 
                {
                	if(subject.defaultValue() != null)
                	{
                    	subject._setValue(boConvertUtils.convertToString(subject.defaultValue(), subject));
                    }
                    else
                    {
                    	subject._setValue(null);
                    }
                    subject.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( bodyMessage.haveDefaultValue()) 
                {
                	if(bodyMessage.defaultValue() != null)
                	{
                    	bodyMessage._setValue(boConvertUtils.convertToString(bodyMessage.defaultValue(), bodyMessage));
                    }
                    else
                    {
                    	bodyMessage._setValue(null);
                    }
                    bodyMessage.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                }
            
                if( urgent.haveDefaultValue()) 
                {
                	if(urgent.defaultValue() != null)
                	{
                    	urgent._setValue(boConvertUtils.convertToString(urgent.defaultValue(), urgent));
                    }
                    else
                    {
                    	urgent._setValue(null);
                    }
                    urgent.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
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
            
                if( TEMPLATE.haveDefaultValue()) 
                {
                	if(TEMPLATE.defaultValue() != null)
                	{
                    	TEMPLATE._setValue(boConvertUtils.convertToBigDecimal(TEMPLATE.defaultValue(), TEMPLATE));
                    }
                    else
                    {
                    	TEMPLATE._setValue(null);
                    }
                    TEMPLATE.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
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
            
    
            if("LIB_Message".equals(this.getName()))
            {
                calculateFormula(null);
            }
        }
    }
   
    public bridgeHandler recipients()
    {
        return getBridge("recipients");        
    }
    
    
    public bridgeHandler getBridge( String bridgeName ) 
    {
        boBridgesArray    brig = super.getBridges();        	
        bridgeHandler     ret  = brig.get(bridgeName);
        if( ret == null )
        {
        
        	if( bridgeName.equals("recipients") && (ret=brig.get("recipients"))==null )
        	{
            	brig.add( ret=new BridgeHandlerrecipients(new DataResultSet( getBridgeDataSet("recipients") ) , this ) );        
        	}
        	if( ret == null )
        	{
				ret = super.getBridge( bridgeName );
        	}
        }
        return ret;
    }
   
    
    
    public final class BridgeHandlerrecipients extends bridgeHandler {
        private DataResultSet p_node;
        private String        p_fatherfield;
        private String        p_childfield;
        private boObject      p_parent;

        
        public BridgeHandlerrecipients(DataResultSet data,boObject parent) {
            super("recipients",
                  data,
                  parent.getBoDefinition().getAttributeRef("recipients").getBridge().getChildFieldName(),
                  parent
                 );

            p_fatherfield = parent.getBoDefinition().getAttributeRef("recipients").getBridge().getFatherFieldName();
            p_childfield  = parent.getBoDefinition().getAttributeRef("recipients").getBridge().getChildFieldName();
            
            p_parent = parent;
            p_node = data;

			refreshBridgeData();
            first();
        }
        public void add(BigDecimal boui, byte type) throws boRuntimeException {
            add(boui,-1, type);
        }
        public void add(BigDecimal boui,int xnrow, byte type) throws boRuntimeException {

              //tranform ao adicionar a brige
              netgest.bo.transformers.Transformer transfClass = null;
			  if ( getParent().getMode() != boObject.MODE_EDIT_TEMPLATE )
				{
				  if(( transfClass = this.getDefAttribute().getTransformClassMap()) != null)
				  {
					 if(boui != null)
					 {
						boui = new BigDecimal(transfClass.transform(getEboContext(), getParent(), boui.longValue()));
					 }
				  }
				}

			  boBridgeRow brow = createRow();
			  ObjAttHandler att = (ObjAttHandler)brow.getAttribute( this.getName() );

			  BigDecimal newval = boui;

			  if( att.fireBeforeChangeEvent( att, null, newval) ) 
			  {
				  
				  brow.getDataRow().updateBigDecimal(p_childfield,boui);
  		          this.insertRow( brow );
				  this.moveTo( this.getRowCount() );

				  if(xnrow !=-1)
                  {
                      this.moveRowTo(xnrow);
                  }

				  att.checkParent( null, newval );

				  getParent().setChanged( true );

                  if(getParent().onChangeSubmit("recipients"))
                  {
                      getParent().calculateFormula("recipients");
                  }
                  att.fireAfterChangeEvent( att, null, newval );
			  }

        }

		public boolean remove() throws boRuntimeException 
        {
            boolean ret = false;

          

                boBridgeRow rowtodelete = rows( this.getRow() );
				ObjAttHandler att = (ObjAttHandler)rowtodelete.getAttribute( this.getName() );

				BigDecimal chgval = this.getValue();

                if( att.fireBeforeChangeEvent( att, chgval, null) ) 
                {
					rowtodelete.getDataRow().updateBigDecimal(p_childfield,null);
					att.checkParent( chgval, null );

				    getParent().setChanged( true );

                    Enumeration  lineatts = rowtodelete.getLineAttributes().elements();

                    _remove( rowtodelete );  // remove the line

					
                    while( lineatts.hasMoreElements() )
                    {
                        AttributeHandler xatt = ( AttributeHandler )lineatts.nextElement();
                        if ( xatt.getValueObject() != null )
                        {
                            xatt.setValueObject( null );
                        }
                    }
                    
                    ret = true;
					//calculateFormulas
					if(getParent().onChangeSubmit("recipients"))
					{
					   getParent().calculateFormula("recipients");
					}

                    att.fireAfterChangeEvent( att, chgval, null);
				}
           
            return ret;
        }




		public boBridgeRow createRow( netgest.bo.data.DataRow row )
		{
			return new BridgeHanlderrecipientsRow( this, row );
		}

		public final class BridgeHanlderrecipientsRow extends boBridgeRow
		{
			public BridgeHanlderrecipientsRow( bridgeHandler bridge, netgest.bo.data.DataRow row )
			{
				super( bridge , row );
				addLine();
			}

			public void addLine() 
			{
				boAttributesArray xatt = super.getLineAttributes();
			
				if(this.getAttribute("recipients")==null)
					xatt.add( new Handlerrecipients( getParent(), this ) );
				
		
			
				if(this.getAttribute("LIN")==null)
					xatt.add( new HandlerLIN( getParent(), this ) );
				
			} 


		}


        
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



        
       
        public final class Handlerrecipients extends BridgeObjAttributeHandler {
            public Handlerrecipients( boObject parent , boBridgeRow bridgerow ) {
                super( parent , bridgerow , bridgerow.getBridge().getDefAttribute().getName().equals("recipients")?parent.getBoDefinition().getAttributeRef("recipients"):bridgerow.getBridge().getDefAttribute().getBridge().getAttributeRef("recipients") );
            }
            public void setValueString(String value) {
                try {
                    this.setValue(boConvertUtils.convertToBigDecimal(value,this));
                    if(getParent().onChangeSubmit("recipients"))
                    {
                        Hashtable table = new Hashtable();                    
                        getParent().setCalculated(table, "recipients");
                        getParent().calculateFormula(table, "recipients");
                    }
                } catch (Exception e) {
                    super.setInvalid(e.getMessage(),value);
                }
            }
            public String getValueString() throws boRuntimeException {
                try {
                    return boConvertUtils.convertToString(this.getValue(),this);
                } catch (Exception e) {
                    String[] args = {getParent().getName(),"recipients"};
                    throw new boRuntimeException(this.getClass().getName()+".load(EboContext,long)","BO-3003",e,args);
                }
            }
            
            public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
            {
                if(getParent().alreadyCalculated(table, dependence)) 
                {                       
                    if(getParent().isWaiting(table, "recipients"))
                    {
                       getParent().setCalculated(table, "recipients");
                       this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "recipients"))
                    {
                        getParent().setCalculated(table, "recipients");
                        this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("recipients"))
                    {
                        getParent().setCalculated(table, "recipients"); 
                    }
                    else
                    {
                        getParent().clear(table, "recipients"); 
                        this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                }
                else
                {   
                    this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    if(getParent().onChangeSubmit("recipients"))
                    {                 
                        getParent().setWaiting(table, "recipients");
                        getParent().calculateFormula(new Hashtable(table), "recipients");
                    }                    
                }
            }

            public void setValue(BigDecimal newval, boolean recalc) throws boRuntimeException 
            {
                this.setValue(newval, recalc ? AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL);
	        	
                if( recalc && getParent().onChangeSubmit("recipients"))
                {
                    Hashtable table = new Hashtable();
                    getParent().setCalculated(table, "recipients");
                    getParent().calculateFormula(table, "recipients");
                }
        
				if ( getParent().p_parentBridgeRow != null )
				{
					getParent().getParentBridgeRow().getBridge().lineChanged("recipients");
				}
            }

            private void setValue(BigDecimal newval, Hashtable table) throws boRuntimeException 
            {
                this.setValue(newval, AttributeHandler.INPUT_FROM_INTERNAL);
                if( getParent().onChangeSubmit("recipients"))
                {
                    getParent().setCalculated(table, "recipients");
                    getParent().calculateFormula(table, "recipients");
                }
                
				//vou verificar se o objecto se encontra numa bridge
				if ( getParent().p_parentBridgeRow != null )
				{
					getParent().getParentBridgeRow().getBridge().lineChanged("recipients");
				}

            }
            
            

            
        }
        

       
        public final class HandlerLIN extends BridgeAttributeHandler {

            private boolean isdbbinding=true;
            private BigDecimal fieldvalue;
            private boBridgeRow p_bridgerow;

			public HandlerLIN(boObject parent, boBridgeRow bridgerow ) {
                super( parent , bridgerow ,bridgerow.getBridge().getDefAttribute().getBridge().getAttributeRef("LIN") );
                p_bridgerow = bridgerow;
            }
            
            public BigDecimal getValue() throws boRuntimeException {
                BigDecimal ret = null;

                fireEvent( boEvent.EVENT_BEFORE_GETVALUE, null );
                if(isdbbinding) {
					ret=p_bridgerow.getDataRow().getBigDecimal("LIN");
                } else {
                    ret = fieldvalue;
                }
                fireEvent( boEvent.EVENT_AFTER_GETVALUE, ret );
                return ret;
            }
            public void setValueObject(Object value) throws boRuntimeException {
                this.setValue((BigDecimal)value);
            }
            public Object getValueObject() throws boRuntimeException 
            {
                return this.getValue();
            }
            public String getValueString() throws boRuntimeException {
                return boConvertUtils.convertToString(this.getValue(),this);
            }
            
            public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
            {
                if(getParent().alreadyCalculated(table, dependence)) 
                {                       
                    if(getParent().isWaiting(table, "LIN"))
                    {
                       getParent().setCalculated(table, "LIN");
                       setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "LIN"))
                    {
                        getParent().setCalculated(table, "LIN");
                        setValue(boConvertUtils.convertToBigDecimal(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("LIN"))
                    {
                        getParent().setCalculated(table, "LIN"); 
                    }
                    else
                    {
                        getParent().clear(table, "LIN"); 
                        setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    } 
                }
                else
                {    
                    setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );                
                    if(getParent().onChangeSubmit("LIN"))
                    {
                        getParent().setWaiting(table, "LIN");
                        getParent().calculateFormula(new Hashtable(table), "LIN");
                    }                    
                }
            }

            public void setValue(BigDecimal newval ) throws boRuntimeException
            {
                setValue( newval, true );
            }

            public void setValue(BigDecimal newval, boolean recalc) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights())
                    	throw new boRuntimeException(HandlerLIN.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                   	
                if(!ClassUtils.compare(newval,this.getValue())) {
                    BigDecimal chgval = this.getValue();

                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
                            if(canAlter(recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL))
                            {
    	                        _setValue(newval);
                                setInputType( recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL );
	                    	                        
                                if( recalc && getParent().onChangeSubmit("LIN"))
                                {
                                    Hashtable table = new Hashtable();
                                    getParent().setCalculated(table, "LIN");
                                    getParent().calculateFormula(table, "LIN");
                                }
	                        
                                //vou verificar se o objecto se encontra numa bridge
                                if ( getParent().p_parentBridgeRow != null )
                                {
                                    getParent().getParentBridgeRow().getBridge().lineChanged("LIN");
                                }

                                fireAfterChangeEvent( this, chgval, newval);
                            }
                    }
                }
            }

            private void setValue(BigDecimal newval, Hashtable table) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights())
                    	throw new boRuntimeException(HandlerLIN.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                   	
                if(!ClassUtils.compare(newval,this.getValue())) {
                    BigDecimal chgval = this.getValue();

                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
											  if(canAlter(AttributeHandler.INPUT_FROM_INTERNAL))
												{
	                        _setValue(newval);
	                        setInputType( AttributeHandler.INPUT_FROM_INTERNAL );
	                        if( getParent().onChangeSubmit("LIN"))
	                        {
	                            getParent().setCalculated(table, "LIN");
	                            getParent().calculateFormula(table, "LIN");
	                        }

							//vou verificar se o objecto se encontra numa bridge
							if ( getParent().p_parentBridgeRow != null )
							{
								getParent().getParentBridgeRow().getBridge().lineChanged("LIN");
							}
	
                            fireAfterChangeEvent( this, chgval, newval);
	                    }
	                }
							}
            }

            public void _setValue(BigDecimal newval) throws boRuntimeException {
                if(isdbbinding) {

                        p_bridgerow.getDataRow().updateBigDecimal("LIN",newval);
                        getParent().setChanged(true);
                        super.setValid();

                } else {
                    fieldvalue = newval;
                }
            }
            public void setValueString(String value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
            }

            //@IF SIMPLEDATATYPE
            public double getValueDouble() throws boRuntimeException {
                return boConvertUtils.convertTodouble(this.getValue(),this);
            }
            public void setValue(double value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
            }
            public void setValuedouble(double value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToBigDecimal(value,this));
            }
            //@ENDIF SIMPLEDATATYPE

            
            
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



            
        }
        
    } 
    
    

   
    public final class Handlerdate extends AttributeHandler {
        private boolean isdbbinding=true;
        private Timestamp fieldvalue;
        public Handlerdate(boObject parent) {
            super(parent,parent.getBoDefinition().getAttributeRef("date"));
        }
        
        public String getValueString() throws boRuntimeException {
            return boConvertUtils.convertToString(this.getValue(),this);
        }
        
            public Timestamp getValue() throws boRuntimeException {
                Timestamp ret = null;
                
                if (getParent().isCheckSecurity() && !this.hasRights())
	            	throw new boRuntimeException(Handlerdate.class.getName() +
	        	".getValue()", "BO-3230", null, "");                      	                   	                                 

                fireEvent( boEvent.EVENT_BEFORE_GETVALUE, null );
                if(isdbbinding) {
                    ret = this.getParent().getDataRow().getTimestamp("DATE");
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
                    if(getParent().isWaiting(table, "date"))
                    {
                       getParent().setCalculated(table, "date");
                       setValue(boConvertUtils.convertToTimestamp(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "date"))
                    {
                        getParent().setCalculated(table, "date");
                        setValue(boConvertUtils.convertToTimestamp(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("date"))
                    {
                        getParent().setCalculated(table, "date"); 
                    }
                    else
                    {
                        getParent().clear(table, "date"); 
                        setValue(boConvertUtils.convertToTimestamp(formula(), this), false );
                    } 
                }
                else
                {    
                    setValue(boConvertUtils.convertToTimestamp(formula(), this), false );                                    
                    if(getParent().onChangeSubmit("date"))
                    {
                        getParent().setWaiting(table, "date");
                        getParent().calculateFormula(new Hashtable(table), "date");
                        //getParent().setCalculated(table, "date");
                    }                    
                }
            }
            public void setValue(Timestamp  newval ) throws boRuntimeException
            {
                setValue( newval, true );
            }
            
            public void setValue(Timestamp newval, boolean recalc) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights()) 
	            	throw new boRuntimeException(Handlerdate.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                 
                if(!ClassUtils.compare(newval,this.getValue())) {
                    Timestamp chgval = this.getValue();
                    
                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
											if(canAlter(recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL))
											{
	                        _setValue(newval);
				        	setInputType( recalc?AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL );
	                        
	                        if( recalc && getParent().onChangeSubmit("date"))
	                        {
	                            Hashtable table = new Hashtable();
	                            getParent().setCalculated(table, "date");
	                            getParent().calculateFormula(table, "date");
	                        }
						    //vou verificar se o objecto se encontra numa bridge
							if ( getParent().p_parentBridgeRow != null )
							{
								getParent().getParentBridgeRow().getBridge().lineChanged("date");
							}
	
                            fireAfterChangeEvent( this, chgval, newval);
											}
                    }
                }
            }
            
            private void setValue(Timestamp newval, Hashtable table) throws boRuntimeException 
            {
                if (getParent().isCheckSecurity() && !this.hasRights()) 
	            	throw new boRuntimeException(Handlerdate.class.getName() +
	        	".setValue()", "BO-3230", null, "");                      	                   	                                 
                if(!ClassUtils.compare(newval,this.getValue())) {
                    Timestamp chgval = this.getValue();
                    
                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
											if(canAlter(AttributeHandler.INPUT_FROM_INTERNAL))
											{
                        _setValue(newval);
                        setInputType( AttributeHandler.INPUT_FROM_INTERNAL );
                        
                        if(getParent().onChangeSubmit("date"))
                        {
                            getParent().setCalculated(table, "date");
                            getParent().calculateFormula(table, "date");
                        }

                        //vou verificar se o objecto se encontra numa bridge
						if ( getParent().p_parentBridgeRow != null )
						{
							getParent().getParentBridgeRow().getBridge().lineChanged("date");
						}

                        fireAfterChangeEvent( this, chgval, newval);
                    }
									}
                }
            }
            
            public void _setValue(Timestamp newval) throws boRuntimeException {
                if(isdbbinding) {
                    this.getParent().getDataRow().updateTimestamp("DATE",newval);
                    getParent().setChanged(true);
                    super.setValid();
                } else {
                    fieldvalue = newval;
                }
            }
        
        public void setValueString(String value)  throws boRuntimeException 
        {
            
                this.setValue(boConvertUtils.convertToTimestamp(value,this));
            
        }

        public void setValueObject(Object value) throws boRuntimeException {
            
                this.setValue((Timestamp)value);
            
        }
        public Object getValueObject() throws boRuntimeException 
        {
            return this.getValue();
        }

        //@IF SIMPLEDATATYPE
            
            public Date getValueDate() throws boRuntimeException {
            	
                return boConvertUtils.convertToDate(this.getValue(),this);
            }
            public void setValue(Date value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToTimestamp(value,this));
            }
            public void setValueDate(Date value)  throws boRuntimeException 
            {
                this.setValue(boConvertUtils.convertToTimestamp(value,this));
            }
            
        //@ENDIF SIMPLEDATATYPE

        
public  boolean required() throws boRuntimeException {/*XEOJAVACLASS*/
return new 
xeo.code.java.lib1_0.
LIB_MessageHandler().
date_Required
(this.getParent(),this);}
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



        
    }
    

   
        
    
public  String[] getDependences(String attributeName) {
return null;
}
public  String[] addDefaultDependencesFields() {
return null;
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
