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



public  class xwfPerformerConfig extends boObject implements  Serializable {  

   
    public HandlerPARENT PARENT;       
   
   
    public netgest.bo.runtime.attributes.boAttributeObject performer;    
    public netgest.bo.runtime.attributes.boAttributeObject PARENTCTX;    
    public netgest.bo.runtime.attributes.boAttributeObject TEMPLATE;    
    public netgest.bo.runtime.attributes.boAttributeNumber BOUI;    
    public netgest.bo.runtime.attributes.boAttributeString CLASSNAME;    
    public netgest.bo.runtime.attributes.boAttributeObject CREATOR;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTCREATE;    
    public netgest.bo.runtime.attributes.boAttributeDate SYS_DTSAVE;    
    public netgest.bo.runtime.attributes.boAttributeString SYS_ORIGIN;    
    public netgest.bo.runtime.attributes.boAttributeObject SYS_FROMOBJ;    
   
     
    public xwfPerformerConfig() {
        super();
        bo_version      = "1.0";
        bo_name         = "xwfPerformerConfig";
        bo_classregboui = "#BO.CLSREGBOUI#";
        bo_definition   = boDefHandler.getBoDefinition("xwfPerformerConfig");
        bo_statemanager = bo_definition.getBoClsState() != null ? bo_definition.getBoClsState().getStateManager( this ) : null;  

        boAttributesArray atts = super.getAttributes();
        boAttributesArray stat = super.getStateAttributes();

       
        atts.add(PARENT = new HandlerPARENT(this));
        
        
        
        atts.add(new boBridgeMasterAttribute(this,this.getBoDefinition().getAttributeRef("wfQueues"))); 

       
        
       
        atts.add(performer = new netgest.bo.runtime.attributes.boAttributeObject(this,"performer"));
        
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
           
                
                    if( PARENT.haveDefaultValue()) 
                    {
                         PARENT._setValues(boConvertUtils.convertToArrayOfBigDecimal(PARENT.defaultValue(), PARENT));
                         PARENT.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
                    }
                
            
           
           
                if( performer.haveDefaultValue()) 
                {
                	if(performer.defaultValue() != null)
                	{
                    	performer._setValue(boConvertUtils.convertToBigDecimal(performer.defaultValue(), performer));
                    }
                    else
                    {
                    	performer._setValue(null);
                    }
                    performer.setInputType( AttributeHandler.INPUT_FROM_DEFAULT );
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
            
    
            if("xwfPerformerConfig".equals(this.getName()))
            {
                calculateFormula(null);
            }
        }
    }
   
    public bridgeHandler wfQueues()
    {
        return getBridge("wfQueues");        
    }
    
    
    public bridgeHandler getBridge( String bridgeName ) 
    {
        boBridgesArray    brig = super.getBridges();        	
        bridgeHandler     ret  = brig.get(bridgeName);
        if( ret == null )
        {
        
        	if( bridgeName.equals("wfQueues") && (ret=brig.get("wfQueues"))==null )
        	{
            	brig.add( ret=new BridgeHandlerwfQueues(new DataResultSet( getBridgeDataSet("wfQueues") ) , this ) );        
        	}
        	if( ret == null )
        	{
				ret = super.getBridge( bridgeName );
        	}
        }
        return ret;
    }
   
    public final class HandlerPARENT extends ObjAttHandler {
        public HandlerPARENT(boObject parent) {
            super(parent,parent.getBoDefinition().getAttributeRef("PARENT"));
        }

        
            public BigDecimal[] getValues() throws boRuntimeException {
                try
                {
                    if (getParent().isCheckSecurity() && !this.hasRights()) 	                
                    	throw new boRuntimeException(HandlerPARENT.class.getName() +
	        	".getValues()", "BO-3230", null, "");                      	                   	
                    fireEvent( boEvent.EVENT_BEFORE_GETVALUE, null );
                    DataSet xdataSet =  getParent().getDataRow().getRecordChild( getEboContext(), "PARENT" );
                    DataResultSet xnode = new DataResultSet( xdataSet );
                    xnode.beforeFirst();
                    BigDecimal[] toRet = null;
                    if( xnode.getRowCount() > 0 )
                    {
                        toRet = new BigDecimal[ xnode.getRowCount() ];
                        while( xnode.next() )
                        {
                            toRet[ xnode.getRow() - 1 ] = xnode.getBigDecimal("PARENT$");
                        }
                    }
                    fireEvent( boEvent.EVENT_BEFORE_GETVALUE, toRet );
                    return toRet;
                } catch(SQLException e) {
                    String[] args = {getParent().getName(),"PARENT"};
                    throw new boRuntimeException(this.getClass().getName()+".load(EboContext,long)","BO-3003",e,args);
                }
            }
            public void setValues(BigDecimal[] newvalue) throws boRuntimeException 
            {
            	if (getParent().isCheckSecurity() && !this.hasRights()) 	          
            	      throw new boRuntimeException(HandlerPARENT.class.getName() +
	        	".setValues(BigDecimal[])", "BO-3230", null, "");                      	
                if(!ClassUtils.compare(newvalue,this.getValues())) {
                    BigDecimal[] chgval = this.getValues();
                    BigDecimal[] newval = newvalue;
        
                    if ( fireBeforeChangeEvent( this, chgval, newval) )
                    {
                        _setValues( newvalue );
                        setInputType(AttributeHandler.INPUT_FROM_USER);
                        getParent().setChanged(true);
                        
                        fireAfterChangeEvent( this, chgval, newval );
                        
                    }
                }
            }
            public void _setValues( BigDecimal[] newvalues  ) throws boRuntimeException 
            {
                try
                {
                    DataSet xdataSet =  getParent().getDataRow().getRecordChild( getEboContext(), "PARENT" );
                    DataResultSet xnode = new DataResultSet( xdataSet ); 
                    
                    if( newvalues == null || newvalues.length == 0 )
                    {
                        while( xnode.getRowCount() > 0 )
                        {
                            xnode.first();
                            xnode.deleteRow();
                        }
                    }
                    else
                    {
                        int i;
                        for (i = 0; i < newvalues.length ; i++) 
                        {
                            if( xnode.absolute( i + 1 ) )
                            {
                                if( !ClassUtils.compare(newvalues,this.getValues()) )
                                {
                                    xnode.updateLong( "T$PARENT$" , getParent().getBoui() );
                                    xnode.updateBigDecimal( "PARENT$" , newvalues[i] );
                                    xnode.updateRow();
                                }
                            }
                            else
                            {
                                xnode.moveToInsertRow();
                                xnode.updateBigDecimal( "PARENT$" , newvalues[i] );
                                xnode.updateLong( "T$PARENT$" , getParent().getBoui() );
                                xnode.insertRow();
                            }
                        }
                        for ( ; i < xdataSet.getRowCount() ; i++ )
                        {
                            xdataSet.deleteRow( i + 1 );
                        }
                        
                    }

					if( getParent().getBridges().get( "PARENT" )!=null )
					{
						getParent().getBridge("PARENT").refreshBridgeData();
					}
                }
                catch (SQLException e)
                {
                    String[] args = {getParent().getName() ,"PARENT"};
                    throw new boRuntimeException(boObject.class.getName()+"set(long)","BO-3002",e,args);
                }
                finally
                {
                    
                }
            }
            public void setValuesString(String[] value)  throws boRuntimeException 
            {
                this.setValues(boConvertUtils.convertToArrayOfBigDecimal(value,this));
            }
            public void setValueString(String value) {
                try {
                    this.setValues(boConvertUtils.convertToArrayOfBigDecimal(value,this));
                } catch (Exception e) {
                    super.setInvalid(e.getMessage(),value);
                }
            }
            public String getValueString() throws boRuntimeException {
                try {
                    return boConvertUtils.convertToString(getValues(),this);
                } catch (Exception e) {
                    String[] args = { getParent().getName() ,"PARENT"};
                    throw new boRuntimeException(this.getClass().getName()+".load(EboContext,long)","BO-3003",e,args);
                }
            }
            
        
        

        
public  boolean haveDefaultValue() {
return false;
}
public  String formula() throws boRuntimeException {
return null;
}



        
        
    }
    
    
    
    public final class BridgeHandlerwfQueues extends bridgeHandler {
        private DataResultSet p_node;
        private String        p_fatherfield;
        private String        p_childfield;
        private boObject      p_parent;

        
        public BridgeHandlerwfQueues(DataResultSet data,boObject parent) {
            super("wfQueues",
                  data,
                  parent.getBoDefinition().getAttributeRef("wfQueues").getBridge().getChildFieldName(),
                  parent
                 );

            p_fatherfield = parent.getBoDefinition().getAttributeRef("wfQueues").getBridge().getFatherFieldName();
            p_childfield  = parent.getBoDefinition().getAttributeRef("wfQueues").getBridge().getChildFieldName();
            
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

                  if(getParent().onChangeSubmit("wfQueues"))
                  {
                      getParent().calculateFormula("wfQueues");
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
					if(getParent().onChangeSubmit("wfQueues"))
					{
					   getParent().calculateFormula("wfQueues");
					}

                    att.fireAfterChangeEvent( att, chgval, null);
				}
           
            return ret;
        }




		public boBridgeRow createRow( netgest.bo.data.DataRow row )
		{
			return new BridgeHanlderwfQueuesRow( this, row );
		}

		public final class BridgeHanlderwfQueuesRow extends boBridgeRow
		{
			public BridgeHanlderwfQueuesRow( bridgeHandler bridge, netgest.bo.data.DataRow row )
			{
				super( bridge , row );
				addLine();
			}

			public void addLine() 
			{
				boAttributesArray xatt = super.getLineAttributes();
			
				if(this.getAttribute("wfQueues")==null)
					xatt.add( new HandlerwfQueues( getParent(), this ) );
				
		
			
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



        
       
        public final class HandlerwfQueues extends BridgeObjAttributeHandler {
            public HandlerwfQueues( boObject parent , boBridgeRow bridgerow ) {
                super( parent , bridgerow , bridgerow.getBridge().getDefAttribute().getName().equals("wfQueues")?parent.getBoDefinition().getAttributeRef("wfQueues"):bridgerow.getBridge().getDefAttribute().getBridge().getAttributeRef("wfQueues") );
            }
            public void setValueString(String value) {
                try {
                    this.setValue(boConvertUtils.convertToBigDecimal(value,this));
                    if(getParent().onChangeSubmit("wfQueues"))
                    {
                        Hashtable table = new Hashtable();                    
                        getParent().setCalculated(table, "wfQueues");
                        getParent().calculateFormula(table, "wfQueues");
                    }
                } catch (Exception e) {
                    super.setInvalid(e.getMessage(),value);
                }
            }
            public String getValueString() throws boRuntimeException {
                try {
                    return boConvertUtils.convertToString(this.getValue(),this);
                } catch (Exception e) {
                    String[] args = {getParent().getName(),"wfQueues"};
                    throw new boRuntimeException(this.getClass().getName()+".load(EboContext,long)","BO-3003",e,args);
                }
            }
            
            public void setValueFormula(Hashtable table, String[]dependence) throws boRuntimeException
            {
                if(getParent().alreadyCalculated(table, dependence)) 
                {                       
                    if(getParent().isWaiting(table, "wfQueues"))
                    {
                       getParent().setCalculated(table, "wfQueues");
                       this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                    else if(!getParent().isCalculated(table, "wfQueues"))
                    {
                        getParent().setCalculated(table, "wfQueues");
                        this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), table );
                    }
                }
                else if(getParent().dependsFromWaiting(table, dependence))
                {
                    if(getParent().onChangeSubmit("wfQueues"))
                    {
                        getParent().setCalculated(table, "wfQueues"); 
                    }
                    else
                    {
                        getParent().clear(table, "wfQueues"); 
                        this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    }
                }
                else
                {   
                    this.setValue(boConvertUtils.convertToBigDecimal(formula(), this), false );
                    if(getParent().onChangeSubmit("wfQueues"))
                    {                 
                        getParent().setWaiting(table, "wfQueues");
                        getParent().calculateFormula(new Hashtable(table), "wfQueues");
                    }                    
                }
            }

            public void setValue(BigDecimal newval, boolean recalc) throws boRuntimeException 
            {
                this.setValue(newval, recalc ? AttributeHandler.INPUT_FROM_USER:AttributeHandler.INPUT_FROM_INTERNAL);
	        	
                if( recalc && getParent().onChangeSubmit("wfQueues"))
                {
                    Hashtable table = new Hashtable();
                    getParent().setCalculated(table, "wfQueues");
                    getParent().calculateFormula(table, "wfQueues");
                }
        
				if ( getParent().p_parentBridgeRow != null )
				{
					getParent().getParentBridgeRow().getBridge().lineChanged("wfQueues");
				}
            }

            private void setValue(BigDecimal newval, Hashtable table) throws boRuntimeException 
            {
                this.setValue(newval, AttributeHandler.INPUT_FROM_INTERNAL);
                if( getParent().onChangeSubmit("wfQueues"))
                {
                    getParent().setCalculated(table, "wfQueues");
                    getParent().calculateFormula(table, "wfQueues");
                }
                
				//vou verificar se o objecto se encontra numa bridge
				if ( getParent().p_parentBridgeRow != null )
				{
					getParent().getParentBridgeRow().getBridge().lineChanged("wfQueues");
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
