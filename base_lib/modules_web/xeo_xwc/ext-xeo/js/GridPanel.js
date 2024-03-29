Ext.ns('ExtXeo','ExtXeo.grid');
Ext.ns('ExtXeo','ExtXeo.data'); 

ExtXeo.grid.GridPanel = Ext.extend(Ext.grid.GridPanel,
	{
		recordIds : null,
		suspendUploadCondig : false,
		minHeight : 0,
		constructor: function( opts ) {
        	if(!this.recordIds) {
        		this.recordIds = [];
        	}
        	ExtXeo.grid.GridPanel.superclass.constructor.apply(this, arguments);
        },
		getMinHeight : function() {
			return this.minHeight;
		},
		setMinHeight : function( minHeight ) {
			this.minHeight = minHeight; 
		},
		multiSelection : false,
		getMultiSelections : function(){
			return this.multiSelection;
		},
		maxSelections : -1,
		getMaxSelections : function(){
			return this.maxSelections;
		},
		multiPagePreserve : false,
		getMultiPageSelections : function(){
			return this.multiPagePreserve;
		},
		updateColumnConfig : function( submit ) {
			var cm = this.getColumnModel();
			var cc = cm.getColumnCount();
			if( !this.columnsSavedConfig )
				this.columnsSavedConfig = [];
			
			var colsCfg = this.columnsSavedConfig;
			for(var i=0;i<cc;i++) {
				if(!colsCfg[i]) colsCfg[i] = {};
				colsCfg[i].dataField = cm.getDataIndex(i);
				colsCfg[i].position = i;
				colsCfg[i].width = cm.getColumnWidth( i );
				colsCfg[i].hidden = cm.isHidden( i );
			}
			this.getStore().setColumnsConfig( colsCfg, submit );
		},
		onColumnConfigChange : function( evname, idx, newvalue ) {
			this.getView().updateHeaderSortState();
			this.getView().updateGroupByState();
			if(!this.suspendUploadCondig) {
				this.updateColumnConfig( true );
			}
		},
		//Return the Id of the selected rows hidden input
		getSelectedRowsInputId : function(){
			return this.id + "_srs";
		},
		//Return the Id of the active hidden input
		getActiveRowInputId : function(){
			return this.id + "_act";
		},
		//Return the Id of the label with the number of selected elements
		getNumberSelectionsCounterId : function(){
			return this.id + "_selections";
		},
		reset : function () {
			this.recordIds = [];
			var selectedRecorsdInput = Ext.get(this.getSelectedRowsInputId());
			var activeRecorsdInput = Ext.get(this.getActiveRowInputId());
			
			if (selectedRecorsdInput !== undefined && selectedRecorsdInput !== null)
				selectedRecorsdInput.set({value:''});
			if (activeRecorsdInput !== undefined && activeRecorsdInput !== null)
				activeRecorsdInput.set({value:''});
			
			var selectionModel = this.getSelectionModel();
			if (selectionModel !== undefined && selectionModel !== null){
				selectionModel.clearSelections(true);
			}
			//Reset the label with the counter if we have multi-selections
			if (this.getMultiSelections()){
				var label = Ext.getCmp(this.getNumberSelectionsCounterId());
				label.setText('0');
			}
		}
		
		
	}
);

ExtXeo.grid.GridView = Ext.extend(Ext.grid.GridView, {
	aggregateText     : 'Agregar Valores',
	aggregateSumText  : 'Somat&oacute;rio' ,
	aggregateMinText  : 'M&iacute;nimo' ,
	aggregateMaxText  : 'M&aacute;ximo' ,
	aggregateAvgText  : 'M&eacute;dia' ,
	aggregateCountText: 'Total',
	aggregateState	  : '',
    restoreDefsText   : 'Rep&ocirc;r Defini&ccedil;&otilde;es',
    selectColsText    : 'Seleccionar Colunas',
    enableSummary     : false,	
	constructor: function( opts ) {
		this.onSelColumns = opts.onSelColumns;
		this.onResetDefaults = opts.onResetDefaults;
		this.onSum = opts.onSum;
		this.onMin = opts.onMin;
		this.onMax = opts.onMax;
		this.onAvg = opts.onAvg;
		ExtXeo.grid.GridView.superclass.constructor.apply(this, arguments);
	},
    initTemplates : function(){
        var ts = this.templates || {};
        if(!ts.hcell){
            ts.hcell = new Ext.Template(
                    '<td class="x-grid3-hd x-grid3-cell x-grid3-td-{id} {css}" style="{style}"><div {tooltip} {attr} class="x-grid3-hd-inner x-grid3-hd-{id}" unselectable="on" style="{istyle}">', this.grid.enableHdMenu ? '<a class="x-grid3-hd-btn" href="#"></a>' : '',
                    '{value}',
                    '<img class="x-grid3-group-icon" src="', Ext.BLANK_IMAGE_URL, '" />',
                    '<img class="x-grid3-sort-icon" src="', Ext.BLANK_IMAGE_URL, '" />',
                    "</div></td>"
                    );
        }
        this.templates = ts;
		ExtXeo.grid.GridView.superclass.initTemplates.apply(this, arguments);
	},
    renderUI : function() {
        ExtXeo.grid.GridView.superclass.renderUI.call( this );
        
        this.grid.getStore().grid = this.grid;
                
        var g = this.grid;
        if(g.enableColumnHide !== false) {
	        this.hmenu.items.key( 'columns' ).hide();
	        var kidx = this.hmenu.items.indexOfKey('columns');
	        var selCols = new Ext.menu.Item(        		
	        	{
					id:'selCols', 
					cls:'xwc-grid-sel-cols',
					text:this.selectCols, 
					iconCls: 'x-cols-icon' 
				} 
	        ); 
	        this.hmenu.insert( kidx, selCols );
	        selCols.on('click', this.onSelColumns, this );
        }
        
        this.hmenu.add('-', 
        	{
				id:'resetDefinitions', 
				cls:'xwc-reset-grid-defs',
				text:this.resetDefs,
				handler : this.onResetDefaults
			} 
        );
        this.updateGroupByState();
        
    },
    // private
    handleHdMenuClick : function(item){
        var index = this.hdCtxIndex;
        var cm = this.cm, ds = this.ds;
        switch(item.id){
            case "asc":
                ds.sort(cm.getDataIndex(index), "ASC");
                break;
            case "desc":
                ds.sort(cm.getDataIndex(index), "DESC");
                break;
            default:
                index = cm.getIndexById(item.id.substr(4));
                if(index != -1){
                    if(item.checked && cm.getColumnsBy(this.isHideableColumn, this).length <= 1){
                        this.onDenyColumnHide();
                        return false;
                    }
                    cm.setHidden(index, item.checked);
                }
        }
        return true;
    },
    // private
    updateHeaderSortState : function(){
        var state = this.ds.getSortState();
        if(!state){
            return;
        }
        
        // TODO: Compare sort state
        //if(!this.sortState || (this.sortState.field != state.field || this.sortState.direction != state.direction)){
        //    this.grid.fireEvent('sortchange', this.grid, state);
        //}
        
        this.sortState = state;
    	var sc = this.sortClasses;
    	var hds = this.mainHd.select('td').removeClass(sc);
    	for( var i=0; i < state.length; i++ ) { 
	        var sortColumn = this.cm.findColumnIndex(state[i].field);
	        if(sortColumn != -1){
	            var sortDir = state[i].direction;
	            this.updateSortIcon(sortColumn, sortDir);
	        }
    	}
    },
    // private
    updateSortIcon : function(col, dir){
        if( col > -1 ) {
	        var sc = this.sortClasses;
	        var hds = this.mainHd.select('td');
        	hds.item(col).addClass(sc[dir == "DESC" ? 1 : 0]);
        }
    },
    findRowIndex : function(el) {
    	var groupBy = this.grid.getStore().groupField;
    	if( groupBy && groupBy.length > 0 ) {
    		return ExtXeo.grid.GridView.superclass.findRowIndex.apply(this, arguments);
    	}
    	else {
    		return ExtXeo.grid.GridView.superclass.findRowIndex.apply(this, arguments);
    	}
    },
    updateGroupByState : function() {
    	var groupBy = this.grid.getStore().groupField;
    	var hds = this.mainHd.select('td').removeClass('grid-group');
    	if( groupBy && groupBy.length > 0 ) {
    		for( var i=0; i < groupBy.length; i++ ) {
    	        var col = this.cm.findColumnIndex(groupBy[i]);
    			if( col > -1 ) {
    				hds.item(col).addClass( 'grid-group' );
    			}
    		}
    	}
    }
});


ExtXeo.grid.GroupingView = Ext.extend(ExtXeo.grid.GridView, {
    hideGroupedColumn:false,
    showGroupName:true,
    enableGrouping:true,
    enableGroupingMenu:true,
    enableNoGroups:true,
    emptyGroupText : '(None)',
    ignoreAdd: false,
    _groupTextTpl : '{text} ({count})',
    gidSeed : 1000,
    loadingMsg : 'A Carregar...',    
    groupByText: 'Agrupar por esta coluna',
    showGroupsText: 'Agrupar',
    initTemplates : function(){
        ExtXeo.grid.GroupingView.superclass.initTemplates.call(this);

        if(!this.startGroup){
	        this.startGroup = new Ext.XTemplate(
	            '<div id="{elemId}" class="x-grid-group {cls}">',
	                '<div id="{elemId}-hd" class="x-grid-group-hd" style="{style}">',
	            		'<table style="table-layout:auto"><tr><td><div>', this.groupTextTpl ,'</div></td><td>',
	            			'<SPAN style="margin-left:20px;" id="{elemId}-tb"></span>',
	            		'</td></tr></table>',
	                '</div>',
	            '<div id="{elemId}-bd" class="x-grid-group-body">'
	        );
	    }
	    this.startGroup.compile();
	    this.endGroup = '</div></div>';
        
        this.state = {};
        	
        var sm = this.grid.getSelectionModel();
        sm.on(sm.selectRow ? 'beforerowselect' : 'beforecellselect',
                this.onBeforeRowSelect, this);

    },
    findGroup : function(el){
        return Ext.fly(el).up('.x-grid-group', this.mainBody.dom);
    },
    getGroups : function(){
        return this.hasRows() ? this.mainBody.dom.childNodes : [];
    },
    onAdd : function(){
        if(this.enableGrouping && !this.ignoreAdd){
            var ss = this.getScrollState();
            this.refresh();
            this.restoreScroll(ss);
        }else if(!this.enableGrouping){
            ExtXeo.grid.GroupingView.superclass.onAdd.apply(this, arguments);
        }
    },
    onRemove : function(ds, record, index, isUpdate){
        ExtXeo.grid.GroupingView.superclass.onRemove.apply(this, arguments);
        var g = document.getElementById(record._groupId);
        if(g && g.childNodes[1].childNodes.length < 1){
            Ext.removeNode(g);
        }
        this.applyEmptyText();
    },
    refreshRow : function(record){
        if(this.ds.getCount()==1){
            this.refresh();
        }else{
            this.isUpdating = true;
            ExtXeo.grid.GroupingView.superclass.refreshRow.apply(this, arguments);
            this.isUpdating = false;
        }
    },
    beforeMenuShow : function(){
        var grouped = this.isGroupByField( this.cm.getDataIndex(this.hdCtxIndex) );
        var g = this.hmenu.items.get('groupBy');
        if(g){
            g.setDisabled(this.cm.config[this.hdCtxIndex].groupable === false);
        }
        var s = this.hmenu.items.get('showGroups');
        if(s){
           s.setDisabled( this.cm.config[this.hdCtxIndex].groupable === false);
           s.setChecked(grouped, true);
        }
        
        var s2 = this.hmenu.items.get('aggregate');
        
        if(s2){        	
        	 var colIdText = this.cm.getDataIndex(this.hdCtxIndex);        	 
        	 var colHeaderText = this.cm.config[this.hdCtxIndex].header;
        	         	         	 
        	 s2.menu.items.get('aggregateSum').itemId = 'SUM:' + colIdText + ':' + colHeaderText; 
        	 s2.menu.items.get('aggregateSum').checkHandler = null;        	 
        	 s2.menu.items.get('aggregateSum').setChecked(this.grid.store.isCheckedAggregate('SUM:' + colIdText + ':' + colHeaderText));
        	 s2.menu.items.get('aggregateSum').checkHandler = this.checkHandlerAggregate; 
        	         	        	 
        	 s2.menu.items.get('aggregateMin').itemId = 'MIN:' + colIdText + ':' + colHeaderText;         	 
        	 s2.menu.items.get('aggregateMin').checkHandler = null;        	  
        	 s2.menu.items.get('aggregateMin').setChecked(this.grid.store.isCheckedAggregate('MIN:' + colIdText + ':' + colHeaderText)); 
        	 s2.menu.items.get('aggregateMin').checkHandler = this.checkHandlerAggregate;    	 
        	 
        	 s2.menu.items.get('aggregateMax').itemId = 'MAX:' + colIdText + ':' + colHeaderText;    	 
        	 s2.menu.items.get('aggregateMax').checkHandler = null;   
      	 	 s2.menu.items.get('aggregateMax').setChecked(this.grid.store.isCheckedAggregate('MAX:' + colIdText + ':' + colHeaderText));
        	 s2.menu.items.get('aggregateMax').checkHandler = this.checkHandlerAggregate;    	      	 
        	 
        	 s2.menu.items.get('aggregateAvg').itemId = 'AVG:' + colIdText + ':' + colHeaderText;    	 
        	 s2.menu.items.get('aggregateAvg').checkHandler = null;  
        	 s2.menu.items.get('aggregateAvg').setChecked(this.grid.store.isCheckedAggregate('AVG:' + colIdText + ':' + colHeaderText));
        	 s2.menu.items.get('aggregateAvg').checkHandler = this.checkHandlerAggregate;    	 
        	
           s2.setVisible( this.cm.config[this.hdCtxIndex].aggregate === true);
        }
    },
    renderUI : function(){
        ExtXeo.grid.GroupingView.superclass.renderUI.call(this);
        this.mainBody.on('mousedown', this.interceptMouse, this);

        if(this.enableGroupingMenu && this.hmenu){
            if(this.enableNoGroups){
                this.hmenu.add('-',{
                    id:'showGroups',
                    cls:'xwc-grid-group-by-column',
                    text: this.showGroupsText,
                    checked: true,
                    checkHandler: this.onShowGroupsClick,
                    scope: this
                },{
                    id:'removeGroups',
                    cls:'xwc-grid-reset-group-by',
                    text: this.removeGroupText,
                    handler: this.onClearGroups,
                    scope: this
                }
                );
            }
            
            
            if(this.enableAggregate)
            {
	            var aggregateMenu = new Ext.menu.Menu({
	            id: 'aggregateMenu', // the menu's id we use later to assign as submenu
	            items: [
	                new Ext.menu.CheckItem(        		
			        			{
			                id:'aggregateSum',
					        		itemId:'aggregateSum', 
											text:this.aggregateSumText, 
											checkHandler: this.checkHandlerAggregate,
				              scope: this 
										} 
		        			),
		        			new Ext.menu.CheckItem(        		
					        	{
			                id:'aggregateMin',
					        		itemId:'aggregateMin', 
											text:this.aggregateMinText,
											checkHandler: this.checkHandlerAggregate,
						          scope: this
										} 
				        	),
				        	new Ext.menu.CheckItem(        		
					        	{
	                		id:'aggregateMax',
					        		itemId:'aggregateMax', 
											text:this.aggregateMaxText, 
											checkHandler: this.checkHandlerAggregate ,
				              scope: this
										} 
					        ),
				        	new Ext.menu.CheckItem(        		
					        	{
			                id:'aggregateAvg',
							        itemId:'aggregateAvg', 
											text:this.aggregateAvgText, 
											checkHandler: this.checkHandlerAggregate,
						          scope: this
										} 
					        )
			            ]
			        });
			        		        
			        this.hmenu.add( 
			            	{
			    				id:'aggregate', 
			    				text:this.aggregateText,
			    				menu: 'aggregateMenu',
	                scope: this
			    			} 
	            );
            }
            
            // Fill summary fields saved
            try
            {
	            var aggregateObjectsArray = this.aggregateState.SVALS;
	            for (var i = 0; i < aggregateObjectsArray.length; i++) {
	            	var fieldId = aggregateObjectsArray[i].VALUEAGG;
	            	
	            	var idx = this.grid.store.aggregateFieldsOn.indexOf( fieldId );
	    		
		    				if(idx == -1)
		    				{
		    					this.grid.store.aggregateFieldsOn[this.grid.store.aggregateFieldsOn.length] = fieldId;
		      			}	            	
	            }
      			}
      			catch(err)
      			{ alert(err);}
            
            this.hmenu.on('beforeshow', this.beforeMenuShow, this);
        }
    },  
    checkHandlerAggregate: function(mi, checked){
    	if(checked)
    	{
    		this.grid.store.addAggregateField('T:' + mi.itemId, mi.itemId, checked);  
              		
    		if(this.grid.store.groupField.length	== 0)
    		{    			
      		this.grid.store.addGroupBy("/*DUMMY_AGGREGATE*/");
    		}	
      }
      else
    	{
    		this.grid.store.addAggregateField('F:' + mi.itemId, mi.itemId, checked);
    		
    		if(this.grid.store.groupField.length == 1 && this.grid.store.groupField[0] == "/*DUMMY_AGGREGATE*/" &&  this.grid.store.aggregateFieldsOn.length == 0)
    		{
    			this.grid.store.clearGroupBy();
    		}
      }
    },
    onGroupByClick : function(){
        this.grid.store.addGroupBy(this.cm.getDataIndex(this.hdCtxIndex));
        this.beforeMenuShow(); 
    },
    onShowGroupsClick : function(mi, checked){
        if(checked){
        	  if(this.grid.store.aggregateFieldsOn.length > 0 && 
        	  	(this.grid.store.groupField.length == 0 || 
        	  	(this.grid.store.groupField.length == 1 && this.grid.store.groupField[0] == "/*DUMMY_AGGREGATE*/" ))
        	  )
        	  {
        	  	this.grid.store.removeGroupBy("/*DUMMY_AGGREGATE*/");     
        	  }
            this.onGroupByClick();
        }else{
        		this.grid.store.removeGroupBy(this.cm.getDataIndex(this.hdCtxIndex)); 
        		
            if(this.grid.store.groupField.length	== 0 && this.grid.store.aggregateFieldsOn.length > 0)
			    	{ 			 
			      	this.grid.store.addGroupBy("/*DUMMY_AGGREGATE*/");		       	  	     	
			    	}	        
        }
    },
    onClearGroups : function() {
    	this.grid.store.clearGroupBy();
    	if(this.grid.store.aggregateFieldsOn.length > 0)
    	{    			
      	this.grid.store.addGroupBy("/*DUMMY_AGGREGATE*/");   	  	
    	}	
    },
    toggleGroup : function(groupEl, expanded){
        this.grid.stopEditing(true);
        var group = Ext.getDom(groupEl);
        var gel = Ext.fly(group);
        var groupId = groupEl.id;
        
        var group = this.rootView.getGroupView(groupId);
        groupId = group.groupId;
        
        expanded = expanded !== undefined ?
                expanded : gel.hasClass('x-grid-group-collapsed');

        this.state[gel.dom.id] = expanded;
        gel[expanded ? 'removeClass' : 'addClass']('x-grid-group-collapsed');
        
        var eg = this.grid.getStore().getExpandedGroups();;
        if( expanded ) {
            if( group.parentGroup ) {
            	if(!eg[group.parentGroup.groupId])
                	eg[group.parentGroup.groupId] = {};
            	eg[ group.parentGroup.groupId ][groupId] = 1;
            }
            else {
            	eg[ '__root__' ][groupId] = 1;
            }
	        group.expandGroup();
        }
        else {
            if( group.parentGroup ) {
            	delete eg[group.parentGroup.groupId][groupId];
            } else {
            	delete eg['__root__'][groupId];
            }
        	delete eg[groupId];
	        group.collapseGroup();
	        this.grid.getStore().uploadConfig();
        }
    },
    onDataChanged : function( store ) {
    	this.groupLoaded( store.grpIdx, store )
    },
    groupLoaded : function( gridGroup ) {
        var buf = [];
		var g = Ext.get( gridGroup.elemId );
        var gel = Ext.fly(g);
        this.state[gel.dom.id] = true;
        gel['removeClass']('x-grid-group-collapsed');
		var store = gridGroup.groupStore;
    	if( store.groupByLevel == store.groupField.length ) {
    		// Render Lines
	        buf[buf.length] = this.doRenderLines(
	    		  this.cs, store.getRange(0,50), store, 0, this.colCount, this.stripe);
    	}
    	else {
    		// Render SubGroup
    		var gv = this.rootView.getGroupView( gridGroup.groupId );
    		buf[buf.length] = gv.groupingView.doRender( gridGroup, this.cs, store.getRange(0,50), store, 0, this.colCount, this.stripe);    		
    	}
        
        if( g ) {
        	g.dom.firstChild.nextSibling.innerHTML = buf.join('');
	        this.grid.view.processRows(0,true);
        }
        
        this.autoExpandGroups( gridGroup.groupId );
    },
    autoExpandGroups : function( gid ) {
        // Expande automaticamente todos os grupos expandidos.
    	try {
	    	if( !this.rootView ) 
	    		return;
	    	
	        var eg = this.grid.getStore().getExpandedGroups();
	        var g = eg[gid]
	        if( g ) {
	        	for(var k in g ) {
	        		var group = this.rootView.getGroupView(k);
	        		if( group ) {
	        			group.expandGroup();
	        		}
	        		else {
	        			this.expandedGroupDeepClear( eg, k );
	        			delete g[k];
	        		}
	        	}
	        }
    	} catch(e) {}
    },
    expandedGroupDeepClear : function( eg, a ) {
    	var c = eg[a];
    	if( !c ) return;
    	for(var k in c ) {
    		this.expandedGroupDeepClear( eg, k );
        	delete eg[k];
    	}
    	delete eg[a]
    },
    toggleAllGroups : function(expanded){
        var groups = this.getGroups();
        for(var i = 0, len = groups.length; i < len; i++){
            this.toggleGroup(groups[i], expanded);
        }
    },
    expandAllGroups : function(){
        this.toggleAllGroups(true);
    },
    collapseAllGroups : function(){
        this.toggleAllGroups(false);
    },
    interceptMouse : function(e){
    	var x = e.getTarget();
    	if( x && x.className.length == 0 ) {
	        var hd = e.getTarget('.x-grid-group-hd', this.mainBody);
	        if(hd){
	            e.stopEvent();
	            this.toggleGroup(hd.parentNode);
	        }
    	}
    },
    getGroup : function(v, r, groupRenderer, rowIndex, colIndex, ds){
        var g = groupRenderer ? groupRenderer(v, {}, r, rowIndex, colIndex, ds) : String(v);
        if(g === ''){
            g = this.cm.config[colIndex].emptyGroupText || this.emptyGroupText;
        }
        return g;
    },
    getGroupField : function(){
        return this.grid.store.getGroupState();
    },
    isGroupByField : function( field ){
        return this.grid.store.isGroupByField(field);
    },
    renderRows : function(){
        var groupField = this.getGroupField();
        var eg = !!groupField;
        if(this.hideGroupedColumn) {
            var colIndex = this.cm.findColumnIndex(groupField);
            if(!eg && this.lastGroupField !== undefined) {
                this.mainBody.update('');
                this.cm.setHidden(this.cm.findColumnIndex(this.lastGroupField), false);
                delete this.lastGroupField;
            }else if (eg && this.lastGroupField === undefined) {
                this.lastGroupField = groupField;
                this.cm.setHidden(colIndex, true);
            }else if (eg && this.lastGroupField !== undefined && groupField !== this.lastGroupField) {
                this.mainBody.update('');
                var oldIndex = this.cm.findColumnIndex(this.lastGroupField);
                this.cm.setHidden(oldIndex, false);
                this.lastGroupField = groupField;
                this.cm.setHidden(colIndex, true);
            }
        }
        return ExtXeo.grid.GroupingView.superclass.renderRows.apply(
                    this, arguments);
    },
    doRender : function(cs, rs, ds, startRow, colCount, stripe) {
    	this.cs = cs;
    	this.rs = rs;
    	this.ds = ds;
    	this.startRow = startRow;
    	this.colCount = colCount;
    	this.stripe = stripe;
    	
        if(rs.length < 1){
            return '';
        }
        var groupField = this.getGroupField();
        this.enableGrouping = groupField.length > 0;
        if(!this.enableGrouping || this.isUpdating){
            return ExtXeo.grid.GroupingView.superclass.doRender.apply(
                    this, arguments);
        }
        var me = this;
        if( !this.rootView ) { 
	    	this.rootView = new ExtXeo.grid.ViewGroup({
	    		parentView : me,
	    		grid : this.grid
	    	});
        }
        return this.rootView.doRender( null, cs, rs, ds, startRow, colCount, stripe);
    },
    isInConfigExpanded : function( value ) {
    	if( this.expandedGroups )
			return this.expandedGroups.indexOf(value) > -1;
    	return false;
    },
    getRows : function(){
        if(!this.enableGrouping){
            return ExtXeo.grid.GroupingView.superclass.getRows.call(this);
        }
        var r = [];
        if( this.rootView ) {
        	r = this.rootView.getRows();
        }
        else {
	        var g, gs = this.getGroups();
	        for(var i = 0, len = gs.length; i < len; i++){
	            g = gs[i].childNodes[1].childNodes;
	            for(var j = 0, jlen = g.length; j < jlen; j++){
	                r[r.length] = g[j];
	            }
	        }
        }
        return r;
    },
    updateGroupWidths : function(){
        if(!this.enableGrouping || !this.hasRows()){
            return;
        }
        var tw = Math.max(this.cm.getTotalWidth(), this.el.dom.offsetWidth-this.scrollOffset) +'px';
        var gs = this.getGroups();
        for(var i = 0, len = gs.length; i < len; i++){
            gs[i].firstChild.style.width = tw;
        }
    },
    onColumnWidthUpdated : function(col, w, tw){
        this.updateGroupWidths();
    },
    onAllColumnWidthsUpdated : function(ws, tw){
        this.updateGroupWidths();
    },
    onColumnHiddenUpdated : function(col, hidden, tw){
        this.updateGroupWidths();
    },
    onLayout : function(){
        this.updateGroupWidths();
    },
    onColWidthChange : function(cm, col, width){
        this.updateColumnWidth(col, width);
    },
    onBeforeRowSelect : function(sm, rowIndex){
        if(!this.enableGrouping){
            return;
        }
        var row = this.getRow(rowIndex);
        if(row && !row.offsetParent){
            var g = this.findGroup(row);
            this.toggleGroup(g, true);
        }
    },
    processRows : function(startRow, skipStripe){
    	var rowMap = [];
        if(this.ds.getCount() < 1){
            return;
        }
        var rows = this.getRows();
        if( rows.length == 0 ) {
        	return;
        }
        //ExtXeo.grid.GroupingView.superclass.processRows.call( this, startRow, skipStripe);
        
        if(this.ds.getCount() < 1){
            return;
        }
        skipStripe = skipStripe || !this.grid.stripeRows;
        startRow = startRow || 0;
        var rows = this.getRows();
        var cls = ' x-grid3-row-alt ';
        rows[0].className += ' x-grid3-row-first';
        rows[rows.length - 1].className += ' x-grid3-row-last';
        for(var i = startRow, len = rows.length; i < len; i++){
            var row = rows[i];
            if( row.storeId ) {
            	rowMap[i] = { sId:row.storeId, i:row.storeIndex };
            }
            row.rowIndex = i;
            if(!skipStripe){
                var isAlt = ((i+1) % 2 == 0);
                var hasAlt = (' '+row.className + ' ').indexOf(cls) != -1;
                if(isAlt == hasAlt){
                    continue;
                }
                if(isAlt){
                    row.className += " x-grid3-row-alt";
                }else{
                    row.className = row.className.replace("x-grid3-row-alt", "");
                }
            }
        }
        this.grid.getStore().rowMap = rowMap;        
    },
    onDataChange : function(){
    	this.rootView = null;
		this.refresh();
		this.updateHeaderSortState();
		this.updateGroupByState();
		
		var eg = this.grid.getStore().getExpandedGroups(); 
		
        this.autoExpandGroups( '__root__' );
		//this.syncFocusEl(0);
    },
    doRenderLines : function(cs, rs, ds, startRow, colCount, stripe){
        var ts = this.templates, ct = ts.cell, rt = ts.row, last = colCount-1;
        var tstyle = 'width:'+this.getTotalWidth()+';';
        // buffers
        var buf = [], cb, c, p = {}, rp = {tstyle: tstyle}, r;
        for(var j = 0, len = rs.length; j < len; j++){
            r = rs[j]; cb = [];
            var rowIndex = (j+startRow);
            
            var alt = [];
            rp.cols = colCount;
            if(this.getRowClass){
                alt[2] = this.getRowClass(r, rowIndex, rp, ds);
            }
            else {
            	alt[2] = "";
            }
            for(var i = 0; i < colCount; i++){
                c = cs[i];
                p.id = c.id;
                p.css = [ alt[2], ( i == 0 ? 'x-grid3-cell-first ' : (i == last ? 'x-grid3-cell-last ' : '') )].join();;
                p.attr = p.cellAttr = "";
                p.value = c.renderer(r.data[c.name], p, r, rowIndex, i, ds);
                p.style = c.style;
                if(p.value == undefined || p.value === "") p.value = "&#160;";
                if(r.dirty && typeof r.modified[c.name] !== 'undefined'){
                    p.css += ' x-grid3-dirty-cell';
                }
                cb[cb.length] = ct.apply(p);
            }
            if(stripe && ((rowIndex+1) % 2 == 0)){
                alt[0] = "x-grid3-row-alt";
            }
            if(r.dirty){
                alt[1] = " x-grid3-dirty-row";
            }
            rp.alt = alt.join(" ");
            rp.cells = cb.join("");
            buf[buf.length] =  rt.apply(rp);
        }
        return buf.join("");
    }
});

ExtXeo.grid.ViewGroup = function( opts ) {
	this.rows = {};
	this.rowsIndex = [];
	this.parentView = null;
	this.rawValue = null;
	this.parentValues = [];
	this.parentFields = [];
	this.childViews = [];
	this.rowIndex = 0;
	this.collapsed = true;
	this.grid = null;
	this.displayVaue = null;
	this.count = null;
	this.aggregate = null;
	this.text = null;
	this.groupId = null;
	this.cls = null;
	this.style = null;
	Ext.apply( this, opts );
	this.initTemplates();
}

ExtXeo.grid.ViewGroup = Ext.extend( ExtXeo.grid.ViewGroup, {
    aggregateTbField    : 'Agregar Valores',
    aggregateTbSumText  : 'Somat&oacute;rio' ,
    aggregateTbAvgText  : 'M&eacute;dia' ,
    aggregateTbMinText  : 'M&iacute;nimo',
    aggregateTbMaxText  : 'M&aacute;ximo',
	uniqueId : 0,
    groupTextTpl : '{aggregateDetailAction}{displayValue} ({count})',
    groupTextTplAggregate : '{aggregate}',
	initTemplates : function() {
	    if(!this.startGroup){
	        this.startGroup = new Ext.XTemplate(
	            '<div id="{elemId}" style="{groupStyle}" class="x-grid-group {cls}">',
	                '<div id="{elemId}-hd" class="x-grid-group-hd" style="{style}">',
	            		'<table style="table-layout:auto"><tr><td><div>', this.groupTextTpl ,'</div></td><td>',
	            			'<SPAN style="margin-left:20px;" id="{elemId}-tb"></span>',
	            		'</td></tr></table>',
	                this.groupTextTplAggregate,
	                '</div>',
	            '<div id="{elemId}-bd" class="x-grid-group-body">'
	        );
	    }
	    this.startGroup.compile();
	    this.endGroup = '</div></div>';
	},
	utf8_decode : function ( str ) {
 
    var histogram = {};
    var ret = str.toString();
 
    var replacer = function(search, replace, str) {
        var tmp_arr = [];
        tmp_arr = str.split(search);
        return tmp_arr.join(replace);
    };
 
    // The histogram is identical to the one in urlencode.
    histogram["'"]   = '%27';
    histogram['(']   = '%28';
    histogram[')']   = '%29';
    histogram['*']   = '%2A';
    histogram['~']   = '%7E';
    histogram['!']   = '%21';
    histogram['%20'] = '+';
    histogram['\u20AC'] = '%80';
    histogram['\u0081'] = '%81';
    histogram['\u201A'] = '%82';
    histogram['\u0192'] = '%83';
    histogram['\u201E'] = '%84';
    histogram['\u2026'] = '%85';
    histogram['\u2020'] = '%86';
    histogram['\u2021'] = '%87';
    histogram['\u02C6'] = '%88';
    histogram['\u2030'] = '%89';
    histogram['\u0160'] = '%8A';
    histogram['\u2039'] = '%8B';
    histogram['\u0152'] = '%8C';
    histogram['\u008D'] = '%8D';
    histogram['\u017D'] = '%8E';
    histogram['\u008F'] = '%8F';
    histogram['\u0090'] = '%90';
    histogram['\u2018'] = '%91';
    histogram['\u2019'] = '%92';
    histogram['\u201C'] = '%93';
    histogram['\u201D'] = '%94';
    histogram['\u2022'] = '%95';
    histogram['\u2013'] = '%96';
    histogram['\u2014'] = '%97';
    histogram['\u02DC'] = '%98';
    histogram['\u2122'] = '%99';
    histogram['\u0161'] = '%9A';
    histogram['\u203A'] = '%9B';
    histogram['\u0153'] = '%9C';
    histogram['\u009D'] = '%9D';
    histogram['\u017E'] = '%9E';
    histogram['\u0178'] = '%9F';
 
    for (replace in histogram) {
        search = histogram[replace]; // Switch order when decoding
        ret = replacer(search, replace, ret) // Custom replace. No regexing   
    }
 
    // End with decodeURIComponent, which most resembles PHP's encoding functions
    
    ret = decodeURIComponent(ret);
 
    return ret;
},	
	doRender : function( parentGroup, cs, rs, ds, startRow, colCount, stripe ) {
    	var level = parentGroup?parentGroup.groupLevel + 1:0; 
    	this.cs = cs;
    	this.rs = rs;
    	this.ds = ds;
    	this.startRow = startRow;
    	this.colCount = colCount;
    	this.stripe = stripe;
    	
        var groupFields = this.parentView.getGroupField();
        var groupField = groupFields[ level ]; 
        
    	var cm = this.grid.getColumnModel();
        var colIndex = cm.findColumnIndex( groupField );
        var gidPrefix = this.grid.getId()+"-";
//        var cfg = cm.config[colIndex];
//        var groupRenderer = cfg.groupRenderer || cfg.renderer;
        var preFix = "";
        var pv = ds.parentValues; 
        if( pv ) {
        	for( var i=0;i<ds.parentValues.length;i++ ) {
        		if( i>0 ) preFix += '|';
        		preFix += Ext.util.Format.htmlEncode(ds.parentValues[i]);
        	}
        }
        var buf = [];
        var group, i, len, gid;
        for(i = 0, len = rs.length; i < len; i++) {
            var rowIndex = startRow + i;
            var r = rs[i];
            var value = r.json[groupField + "__value"];
            
            //var gid = gidPrefix + '-gp-' + (parentGroup!=null?parentGroup.uniqueId:"") + "-" + groupField + "-" + Ext.util.Format.htmlEncode(value);
            var gid ;
            if( preFix )
            	gid = new Array(preFix, Ext.util.Format.htmlEncode(value)).join('|');
            else
            	gid = new Array(Ext.util.Format.htmlEncode(value)).join('|');
            
            var group = this.rows[gid]; 
            var aggregateGrid;
            if( !group ) {
            	var elemId = gidPrefix + gid;
            	
            	
            	var aggregateFormatted = '';
            	var aggregateDetailAction = '';
            	
            	if(r.json[groupField + "__aggregate"] != "none")
            	{
            		var hasSum = false;
            		var hasMin = false;
            		var hasMax = false;
            		var hasAvg = false;
            		
            		aggregateDetailAction = '';
            	
	            	var aggregateArrJson = eval("(" + "{aggregateArr: [" + r.json[groupField + "__aggregate"] + "]}" + ")");
	            	var aggregateArr = aggregateArrJson.aggregateArr;
	            	var aggregateData = new Array();
	            	
	            	var colOdd = "#EDEDED";
	            	var colEven  = "#FFFFFF";
	            	
	            	for(l = 0, lenA = aggregateArr.length; l < lenA; l++) {
	            		 var aggregateR = aggregateArr[l];
	            		 if(aggregateR != null)
						 {
	            		 	var aggregateSUM = aggregateR.SUM;	            		 
	            			var aggregateAVG = aggregateR.AVG;	            		 
	            		 	var aggregateMIN = aggregateR.SMIN;	            		 
	            		 	var aggregateMAX = aggregateR.SMAX;
	            		 
	            		 
	            		 	if(aggregateSUM != null && aggregateSUM != '')
	            		 	{
	            				 hasSum = true;
	            		 	}
	            		 	if(aggregateAVG != null && aggregateAVG != '')
	            		 	{
	            				 hasAvg = true;
	            		 	}
	            		 	if(aggregateMIN != null && aggregateMIN != '')
	            		 	{
	            				 hasMin = true;
	            		 	}
	            		 	if(aggregateMAX != null && aggregateMAX != '')
	            		 	{
	            				 hasMax = true;
	            		 	}
	            		 
	            		 
	            		 	if(hasSum && hasMin && hasMax && hasAvg)
	            		 	{
	            				 break;
	            		 	}
	            		 }
	            	 }
	            	
	            	 if(hasSum || hasMin || hasMax || hasAvg)
            		 {
	             		aggregateFormatted = '<table name="agg_id_table" id="agg_tb-' + elemId + '" style="display:block"><thead><tr class="x-grid3-hd-row">' + 
						 '<td class="x-grid3-hd-row x-grid3-cell x-grid3-cell-first " bgcolor="#3764A0" align="center" width="auto" style="color: #FFFFFF">&nbsp;' + this.aggregateTbField + '&nbsp;</td>';
	             		
	             		
	             		if(hasSum) 
	             		{
	             			aggregateFormatted = aggregateFormatted +
						 '<td class="x-grid3-hd x-grid3-cell" bgcolor="#3764A0" align="center" width="100px" style="color: #FFFFFF">&nbsp;' + this.aggregateTbSumText + '&nbsp;</td>';
	             		}
	             		if(hasAvg) 
	             		{
	             		aggregateFormatted = aggregateFormatted +
						 '<td class="x-grid3-hd x-grid3-cell" bgcolor="#3764A0" align="center" width="100px" style="color: #FFFFFF">&nbsp;' + this.aggregateTbAvgText + '&nbsp;</td>'; 
	             		}
	             		if(hasMin) 
	             		{
						 aggregateFormatted = aggregateFormatted +
						 '<td class="x-grid3-hd x-grid3-cell" bgcolor="#3764A0" align="center" width="100px" style="color: #FFFFFF">&nbsp;' + this.aggregateTbMinText + '&nbsp;</td>'; 
	             		}
	             		if(hasMax) 
	             		{
						 aggregateFormatted = aggregateFormatted +
						 '<td class="x-grid3-hd x-grid3-cell" bgcolor="#3764A0" align="center" width="100px" style="color: #FFFFFF">&nbsp;' + this.aggregateTbMaxText + '&nbsp;</td>'; 
	             		}
	             		aggregateFormatted = aggregateFormatted + '</tr></thead><tbody>';
		            	
		            	
		            	var curColour = colOdd;
	            		 
		            	 for(k = 0, len2 = aggregateArr.length; k < len2; k++) {
		            	 	
	            		   if(k%2 == 0)
	            		   {
	            		 		 curColour = colEven;
	            		   }
	            		   else
	            		   {
	            		 		 curColour = colOdd;
	            		   }
		            	 	
		            		 var aggregateR = aggregateArr[k];
		            		 var aggregateSUM = aggregateR.SUM;	            		 
		            		 var aggregateAVG = aggregateR.AVG;	            		 
		            		 var aggregateMIN = aggregateR.SMIN;	            		 
		            		 var aggregateMAX = aggregateR.SMAX;
		            		 var aggregateFieldName = aggregateR.name;
		            		 var aggregateFieldDesc = aggregateR.desc;
		            		 
		            		 var aggregateDataTemp = new Array();
		            		 aggregateDataTemp[0] = aggregateFieldDesc;
		            		 aggregateDataTemp[1] = aggregateSUM;
		            		 aggregateDataTemp[2] = aggregateAVG;
		            		 aggregateDataTemp[3] = aggregateMIN;
		            		 aggregateDataTemp[4] = aggregateMAX;
		            		 
		            		 aggregateData[k] = aggregateDataTemp;
		            		 
		            		 aggregateFormatted = aggregateFormatted + '<tr class="x-grid3-hd-row" bgcolor="' + curColour + '"><td class="x-grid3-col x-grid3-cell">&nbsp;' + 
		            		 										this.utf8_decode(aggregateFieldDesc) + '&nbsp;</td>';
		            		 
		            		 if(hasSum) 
		              		 {
		              			aggregateFormatted = aggregateFormatted + '<td class="x-grid3-col x-grid3-cell" align="right">&nbsp;' + aggregateSUM + '&nbsp;</td>';
		              		 }
		            		 if(hasAvg) 
		              		 {
		              			aggregateFormatted = aggregateFormatted + '<td class="x-grid3-col x-grid3-cell" align="right">&nbsp;' + aggregateAVG + '&nbsp;</td>';
		              		 }
		            		 if(hasMin) 
		              		 {
		              			aggregateFormatted = aggregateFormatted + '<td class="x-grid3-col x-grid3-cell" align="right">&nbsp;' + aggregateMIN + '&nbsp;</td>';
		              		 }
		            		 if(hasMax) 
		              		 {
		              			aggregateFormatted = aggregateFormatted + '<td class="x-grid3-col x-grid3-cell" align="right">&nbsp;' + aggregateMAX + '&nbsp;</td>';
		              		 }
		            		 aggregateFormatted = aggregateFormatted + '</tr>';
		            		 
		            	 }
		            	 aggregateFormatted = aggregateFormatted + "</tbody></table>";	
            		 }
            	}
            	
	            group = new ExtXeo.grid.GridGroup({
	            	uniqueId : ++ExtXeo.grid.ViewGroup.prototype.uniqueId,
	            	elemId : gidPrefix + gid,
	            	groupId : gid,
	        		rawValue : value,
	        		cls : 'x-grid-group-collapsed',
	        		groupLevel : level,
	        		displayValue : r.json[groupField].indexOf('/*DUMMY_AGGREGATE*/') > 0 ? '' :  r.json[groupField] ,
	        		count : r.json[groupField + "__count"],
	        		aggregate : aggregateFormatted,
	        		aggregateDetailAction : aggregateDetailAction,
	        		parentFields : null,
	        		rowIndex : rowIndex,
	        		collapsed : true,
	        		groupStyle : level>0?"margin-left:10px":"",
	        		groupingView :new ExtXeo.grid.ViewGroup({
	            		parentView : this.parentView,
	            		grid : this.grid
	            	}),
	        		parentGroup : parentGroup
	            });
	            this.rows[gid] = group;
	            this.rowsIndex[i] = gid;
            }
            this.doGroupStart(buf, group, cs, ds, colCount);
            this.doGroupEnd(buf, group, cs, ds, colCount);
            //TODO: Analisar para que � que � necess�rio esta propriedade!
            r._groupId = gid;
        }
        return buf.join('');		
	},
    doGroupStart : function(buf, g, cs, ds, colCount){
        buf[buf.length] = this.startGroup.apply(g);
    },
    doGroupEnd : function(buf, g, cs, ds, colCount){
        buf[buf.length] = this.endGroup;
    },
    doRenderLines : function(cs, rs, ds, startRow, colCount, stripe){
        var ts = this.templates, ct = ts.cell, rt = ts.row, last = colCount-1;
        var tstyle = 'width:'+this.grid.getTotalWidth()+';';
        // buffers
        var buf = [], cb, c, p = {}, rp = {tstyle: tstyle}, r;
        for(var j = 0, len = rs.length; j < len; j++){
            r = rs[j]; cb = [];
            var rowIndex = (j+startRow);
            
            var alt = [];
            rp.cols = colCount;
            if(this.getRowClass){
                alt[2] = this.getRowClass(r, rowIndex, rp, ds);
            }
            else {
            	alt[2] = "";
            }
            for(var i = 0; i < colCount; i++){
                c = cs[i];
                p.id = c.id;
                p.css = [ alt[2], ( i == 0 ? 'x-grid3-cell-first ' : (i == last ? 'x-grid3-cell-last ' : '') )].join();;
                p.attr = p.cellAttr = "";
                p.value = c.renderer(r.data[c.name], p, r, rowIndex, i, ds);
                p.style = c.style;
                if(p.value == undefined || p.value === "") p.value = "&#160;";
                if(r.dirty && typeof r.modified[c.name] !== 'undefined'){
                    p.css += ' x-grid3-dirty-cell';
                }
                cb[cb.length] = ct.apply(p);
            }
            if(stripe && ((rowIndex+1) % 2 == 0)){
                alt[0] = "x-grid3-row-alt";
            }
            if(r.dirty){
                alt[1] = " x-grid3-dirty-row";
            }
            rp.alt = alt.join(" ");
            rp.cells = cb.join("");
            buf[buf.length] =  rt.apply(rp);
        }
        return buf.join("");
    },getGroupView : function( groupId ) {
    	var gid = this.grid.getId() + "-";
    	if( groupId.indexOf( gid ) == 0  ) {
    		groupId = groupId.substring( gid.length );
    	}
    	return this._getGroupView( groupId );
    },
    _getGroupView : function( groupId ) {
    	var gv = this.rows[ groupId ]
    	if ( !gv ) {
	        var g, gs, gsIdx = this.rowsIndex;
	        for(var i=0;i<this.rowsIndex.length; i++ ){
	        	// Prevent infinit recursive calls.. just to debug
	        	if( this.rows[ this.rowsIndex[i] ].groupingView != this ) {
	        		if( this.rows[ this.rowsIndex[i] ].groupingView ) {
			        	gv = this.rows[ this.rowsIndex[i] ].groupingView.getGroupView( groupId );
			        	if( gv ) {
			        		break;
			        	}
	        		}
	        	}
	        }
    	}
    	return gv;
    },
    getRows : function() {
    	var r = [];
        var g, gs, gsIdx = this.rowsIndex;
        for(var i=0;i<this.rowsIndex.length; i++ ){
        	gs = this.rows[ this.rowsIndex[i] ];
        	if( gs.groupStore && gs.groupStore.groupByLevel == gs.groupStore.groupField.length ) {
	            g = Ext.get( gs.elemId );
	            if( g ) {
	            	var cn = g.dom.childNodes[1].childNodes;
	            	for(var j=0;j<cn.length;j++) {
	            		cn[j].storeIndex = j;
	            		cn[j].storeId = gs.groupStore.storeId;
	            		r.push( cn[j] );
	            	}
	            }
        	}
        	else {
        		var r1 = gs.groupingView.getRows();
        		for(var j=0;j<r1.length;j++)
            		r.push( r1[j] );
        	}
        }
        return r;
    }
});

ExtXeo.grid.GridGroup = function( opts ) {
	this.groupId = null;
	this.rawValue = null;
	this.groupLevel = null;
	this.displayValue = null;
	this.count = 0;
	this.parentGroup = null;
	this.rowIndex = 0;
	this.collapsed = true;
	this.toolBar = null;
	this.groupStore = null;
	this.groupingView = null;
	this.aggregate = null;
	this.aggregateDetailAction = null;
	ExtXeo.grid.GridGroup.uniqueId++;
	Ext.apply( this, opts );
}

ExtXeo.grid.GridGroup = Ext.extend( ExtXeo.grid.GridGroup, {
	uniqueId : 0,
	expandGroup : function() {
		
		var elem = Ext.get( this.elemId );
        var gel = Ext.fly(elem);
        gel['removeClass']('x-grid-group-collapsed');
		
		if( !elem ) return;
    	elem.dom.firstChild.nextSibling.innerHTML = "<div>&nbsp;<img align='base' src='extjs/images/default/grid/wait.gif'/></div>";
		
		var parentValues = [];
		var parentGroups = [];
		
		var scope = this;
		
		var g = this;
		while( g != null ) {
			parentValues.unshift( g.rawValue );
			g = g.parentGroup;
		}
		
    	if( this.groupStore ) {
    		this.groupStore.destroy();
    		this.groupStore = null;
    	}
		
    	var pgs;
		if( this.parentGroup ) {
			pgs = this.parentGroup.groupStore;
		}
		else {
			pgs = this.groupingView.grid.store;
		}
        this.groupStore = pgs.createGroupStore( 
	    		function(i,s) { 
	    			scope.groupLoaded( i, s ) 
	    		},
	    		this.rowIndex,
	    		parentValues,
	    		this.groupLevel+1,
	    		this.toolBar?this.toolBar.cursor:0,
	    		50
	        );
        this.groupStore.addListener("datachanged",this.groupLoaded, this );
        this.groupStore.load();
    	this.toolBar = this.createGroupToolbar( this.elemId, this.groupStore );
	},
	groupLoaded : function() {
		if( this.toolBar ) {
			if( this.groupStore.getTotalCount() > 50 ) {
				this.toolBar.show();
			}
			else {
				this.toolBar.hide();
			}
			this.groupingView.parentView.groupLoaded( this );
		}
	},
	collapseGroup : function() {
    	if( this.toolBar ) {
    		this.toolBar.destroy();
    		this.toolBar = null;
    	}
	},
    createGroupToolbar : function( elemId, store ) {
    	var ret = new ExtXeo.PagingToolbar( { beforePageText:'', style:'border:1px solid lightgray;padding-top:0px;padding-bottom:0px;', cls:'x-grid-group-tb', hidden:true ,ctCls:'x-grid-group-tb' ,autoWidth:false, width:270,renderTo:elemId + '-tb', store: store } );
    	return ret;
    }
});

ExtXeo.grid.GroupingView.GROUP_ID = 10000;

ExtXeo.data.GroupingStore = Ext.extend( Ext.data.Store, {
	changePageControl: false, 
    grid : null,
	rowIdentifier : '',
	constructor: function( opts ) {
		this.groupStores = [];
		if( opts.groupField ) 
			this.groupField = opts.groupField;
		if( opts.expandedGroups ) 
			this.groupField = opts.expandedGroups;
			
		Ext.apply( this, {
			url : null,
			groupByParentValues : null,
			groupByLevel : 0,
			expandedGroups : { '__root__' : {} },
			columnsConfig : null,
			remoteGroup : true,
			rootStore : null,
			groupField : [],
			aggregateField : null,
			aggregateFieldsOn : []
		});
		this.storeId = (ExtXeo.grid.GroupingView.GROUP_ID++);
		ExtXeo.data.GroupingStore.superclass.constructor.apply(this, arguments);
	},
    clearGroupBy : function( field ){
		if( this.groupField.length > 0 ) {
			this.groupField = [];
	        if(this.baseParams){
	            delete this.baseParams.groupBy;
	            if( this.lastOptions.params )
	            	delete this.lastOptions.params.groupBy;
	        }
	        this.reload();
		}

		// Clear expanded groups
		try {
			var eg = this.expandedGroups;
			for( var k in eg ) {
				for( k1 in eg[k] )
					delete eg[k][k1];
				if( k !='__root__' )
					delete eg[k];
			}
		}
		catch(e) {}
	},
    removeGroupBy : function( field ){
		if( this.groupField.indexOf( field ) != -1 ) {
			this.groupField.remove( field );
	        if(this.baseParams){
	            delete this.baseParams.groupBy;
	            if( this.lastOptions.params )
	            	delete this.lastOptions.params.groupBy;
	        }
	        this.reload();
		}
    },
    getGroupByRootStore : function() {
    	if( this.rootStore != null )
    		return this.rootStore;
    	return this;
    },
    getAtByStoreId : function( storeId, rowIndex ) {
    	var s = this.findStore( storeId );
    	if( s ) {
    		return s.getAt( rowIndex );
    	}
    	return -1;
    },
    findStore : function( storeId ) {
    	var r;
    	if( this.storeId == storeId )
    		return this;
    	
    	for( var s=0;!r && s<this.groupStores.length;s++ ) {
    		var gs = this.groupStores[s];
    		if( gs ) {
    			r = gs.findStore( storeId );
    		}
    	}
    	return r;
    },
    getAt : function( index ) {
    	var r;
    	if( this.rowMap && this.rowMap[ index ] ) {
    		r = this.getAtByStoreId( this.rowMap[ index ].sId, this.rowMap[ index ].i );
    	}
    	else {
    		r = ExtXeo.data.GroupingStore.superclass.getAt.call( this, index );
    	}
    	return r;
    },
    indexOfId : function(id){
    	if( this.groupField.length > 0 ) {
        	var r = [];
        	r[0] = 0;
        	r[1] = false;
    		var r = this.indexOfId_( id, r ); 
    		return r[1]?r[0]:-1;
    	}
    	else {
    		return ExtXeo.data.GroupingStore.superclass.indexOfId.call( this, id );
    	}
    },
    indexOfId_ : function( id, r ) {
    	for( var s=0;!r[1] && s<this.groupStores.length;s++ ) {
    		var gs = this.groupStores[s];
    		if( gs ) {
	    		if( gs.groupField.length == gs.groupByLevel ) {
		    		if( gs && gs.data ) {
		    			var x = gs.data.indexOfKey(id);
		    			if( x == -1 ) {
		    				r[0] += gs.getCount();
		    			}
		    			else {
		    				r[0] += x;
		    				r[1] = true;
		    				break;
		    			}
		    		}
	    		}
	    		else {
	    			r = gs.indexOfId_( id, r );
	    		}
    		}
    	}
    	return r;
    },
    getCount : function() {
    	if( this.groupField.length == this.groupByLevel ) {
    		c = ExtXeo.data.GroupingStore.superclass.getCount.call(this);
    		return c;
    	}
    	return 9999;
    },
    addGroupBy : function( field, forceRegroup ){
    	if(this.groupField.indexOf( field ) > -1 && !forceRegroup){
            return; 
        }
        
        if( this.groupField.indexOf( field ) == -1 ) {
        	if( this.groupByLevel == -1 ) {
        		this.groupByLevel = 0;
        	}
        	this.groupField[this.groupField.length] = field ;
        }
        
        if(this.remoteGroup){
            if(!this.baseParams){
                this.baseParams = {};
            }
            this.baseParams['groupBy'] = this.groupField;
        }
        this.reload();
    }, 
    isCheckedAggregate : function (fieldId)
    {
    	return this.aggregateFieldsOn.indexOf( fieldId ) > -1;
    },
    addAggregateField:  function( field, fieldId, check ){
    	if(check === true)
    	{
    		var idx = this.aggregateFieldsOn.indexOf( fieldId );
    		
    		if(idx == -1)
    		{
    			this.aggregateFieldsOn[this.aggregateFieldsOn.length] = fieldId;
    			this.aggregateField = field;
      		this.baseParams['aggregateField'] = field;
      	}
    	}
    	else if(this.aggregateFieldsOn.indexOf( fieldId ) > -1)
    	{   
		  	var idx = this.aggregateFieldsOn.indexOf( fieldId );
		    this.aggregateFieldsOn.splice (idx,1);
		    this.aggregateField = field;
      	this.baseParams['aggregateField'] = field;
    	}    		
    	
      this.reload();
    },
    applyGrouping : function(alwaysFireChange){
        if(this.groupField.length > 0 ){
            this.groupBy(this.groupField, true);
            return true;
        }else{
            if(alwaysFireChange === true){
                this.fireEvent('datachanged', this);
            }
            return false;
        }
    },
    load : function( options ) {
    	options = options || {};
    	options.params = options.params || {};
    	this.preparedHttpParams( options.params );
        if(this.fireEvent("beforeload", this, options) !== false){
            this.storeOptions(options);
            var p = Ext.apply(options.params || {}, this.baseParams);
            if(this.sortInfo && this.remoteSort){
                var pn = this.paramNames;
                p[pn["sort"]] = Ext.util.JSON.encode( this.sortInfo );
            }
            this.proxy.load(p, this.reader, this.loadRecords, this, options);
            return true;
        } else {
          return false;
        }
    },
    isGroupByField : function( fieldName ) {
    	return this.groupField.indexOf( fieldName ) > -1
    },
    getGroupState : function(){
        return this.groupOnSort && this.groupField.length > 0 ?
               (this.sortInfo ? this.sortInfo.field : undefined) : this.groupField;
    },
    getRange : function(s,e){
		return ExtXeo.data.GroupingStore.superclass.getRange.call(this,s,e);
    },
    createGroupStore : function( callback, idx, parentValues, level, start, limit ) {

    	var fitems = this.fields.items?this.fields.items:this.fields;
    	var cf = [];
    	for(var i=0;i<fitems.length; i++ ) {
    		var nf = fitems[i];
    		cf[i] = { name: nf.name, type: nf.type };
    	}

    	var ss = this.getSortState();

    	// Create config for the new groupStore.
    	var c = Ext.apply({},{ parentValues: parentValues, groupByLevel: level }, this.reader.meta );
    	c.fields = cf;
    	c.proxy  = new Ext.data.HttpProxy(this.reader.meta);
    	c.groupField = this.groupField;
    	c.expandedGroups = this.expandedGroups;
    	
    	var gs = new ExtXeo.data.GroupingStore( c );
    	gs.reader = this.reader;
    	gs.baseParams = this.baseParams;
    	gs.callback = callback;
    	gs.groupByParentValues = parentValues;
    	gs.loading = true;
    	if( !this.rootStore ) {
    		gs.rootStore = this;
    	}
    	else {
    		gs.rootStore = this.rootStore;
    	}
    	//gs.groupStores = new Array();
    	this.groupStores[ idx ] = gs;
    	return gs;
    },
    groupLoaded : function( idx ) {
    	var g = this.groupStores[ idx ];
    	// Groups arrays was reset... maybe because a refresh... cancel callback event
    	if( g != null ) {
	    	g.loading = false;
	    	g.callback( idx, g  );
    	}
    },
    setExpandedGroups : function ( g, upload ) {
    	this.expandedGroups = g;
    	if( upload )
    		this.uploadConfig();
    },
    getExpandedGroups : function() {
    	return this.expandedGroups;
    },
    setColumnsConfig : function( c, upload ) {
    	this.columnsConfig = c;
    	if( upload )
    		this.uploadConfig();
    },
    preparedHttpParams : function(p) {
    	this.sortInfo = this.getGroupByRootStore().sortInfo;
    	
    	if( this.lastOptions && this.lastOptions.params.filters )
    		p.filters = this.lastOptions.params.filters;
    	
		p.expandedGroups = Ext.encode(this.expandedGroups);

		if( this.columnsConfig ) 
    		p.columnsConfig = Ext.encode(this.columnsConfig);
    	
    	if( this.groupField && this.groupField.length > 0 ) {
    		p.groupBy 		= this.groupField;
    		p.groupByLevel 	= this.groupByLevel;
    		if( this.groupByParentValues ) {
    			p.groupByParentValues = this.groupByParentValues;
    		}
    	}
    },
    uploadConfig : function() {
    	var params = {};
    	this.preparedHttpParams(params);
    	params.updateConfig = true;
        var p = Ext.apply(params || {}, this.baseParams);
        var url = this.url;
		Ext.Ajax.request( { 
            	url: this.url,
            	params: p
        	}
        );    		
    },
    setDefaultSort : function(field, dir){
    	this.sortInfo = [];
    	this.sortToggle = [];
    	for( var i=0; i<field.length;i++ ) {
	        dir[i] = dir[i] ? dir[i].toUpperCase() : "ASC";
	        this.sortInfo[i] = {field: field[i], direction: dir[i]};
	        this.sortToggle[field[i]] = dir[i];
    	}
    },
    sort : function(fieldName, dir){
        var f = this.fields.get(fieldName);
        if(!f){
            return false;
        }
        
        if (!this.sortInfo)	
        	this.sortInfo=[];
        
        if(!dir){
            if(this.sortToggle[ f.name ] ){ // toggle sort dir
            	if (this.sortToggle[f.name] == "DESC" ) {
            		dir = "NONE";
            	}
            	else {
            		dir = (this.sortToggle[f.name] || "ASC").toggle("ASC", "DESC");
            	}
            }else{
                dir = f.sortDir;
            }
        }
        
        /*
        var st = (this.sortToggle) ? this.sortToggle[f.name] : null;
        var si = (this.sortInfo) ? this.sortInfo : null;
        */
        this.sortToggle[f.name] = dir;
        
        var nsinf =  [];
    	for( var i=0;i<this.sortInfo.length;i++ )
    		if( this.sortInfo[i].field != f.name && this.groupField.indexOf( this.sortInfo[i].field ) > -1 )
    			nsinf[nsinf.length] = this.sortInfo[i];
    	
    	this.sortInfo = nsinf;
    	
        if( dir != "NONE" ) {
            this.sortInfo[this.sortInfo.length] = {field: f.name, direction: dir};
        }
        
        if(!this.remoteSort){
            this.applySort();
            this.fireEvent("datachanged", this);
        }else{
            if (!this.load(this.lastOptions)) {
            	/*
                if (st) {
                    this.sortToggle[f.name] = st;
                }
                if (si) {
                    this.sortInfo = si;
                }
                */
            }
        }
    }, 
    setSelectedPageRows: function() {	
		this.changePageControl = true; 
		if(this.grid) 
		{	
			var rows = [];
			for(var i = 0, len = this.grid.recordIds.length; i < len; i++){
				var index = this.find(this.rowIdentifier, this.grid.recordIds[i]);
				if(index >= 0){
					rows.push(index);
				}
			}
			this.grid.getSelectionModel().selectRows(rows);
		}
		this.changePageControl = false; 
	}
});

ExtXeo.PagingToolbar = Ext.extend(Ext.Toolbar, {
    pageSize:50,
    displayMsg : 'Mostrando {0} - {1} de {2}',
    emptyMsg : 'Sem dados para mostrar',
    beforePageText : "P&aacute;gina",
    afterPageText : "de {0} ({1})",
    firstText : "Primeira P&aacute;gina",
    prevText : "P&aacute;gina Aterior",
    nextText : "Pr&oacute;xima P&aacute;gina",
    lastText : "&Uacute;ltima p&aacute;gina",
    refreshText : "Actualizar",
    paramNames : {start: 'start', limit: 'limit'},
    initComponent : function(){
        this.addEvents('change', 'beforechange');
        ExtXeo.PagingToolbar.superclass.initComponent.call(this);
        this.cursor = 0;
        this.bind(this.store);
    },
    onRender : function(ct, position){
    	ExtXeo.PagingToolbar.superclass.onRender.call(this, ct, position);
        this.first = this.addButton({
            tooltip: this.firstText,
            iconCls: "x-tbar-page-first",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["first"])
        });
        this.prev = this.addButton({
            tooltip: this.prevText,
            iconCls: "x-tbar-page-prev",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["prev"])
        });
        this.addSeparator();
        this.add(this.beforePageText);
        this.field = Ext.get(this.addDom({
           tag: "input",
           type: "text",
           size: "3",
           value: "1",
           cls: "x-tbar-page-number"
        }).el);
        this.field.on("keydown", this.onPagingKeydown, this);
        this.field.on("focus", function(){this.dom.select();});
        this.afterTextEl = this.addText(String.format(this.afterPageText, 1));
        this.field.setHeight(18);
        this.addSeparator();
        this.next = this.addButton({
            tooltip: this.nextText,
            iconCls: "x-tbar-page-next",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["next"])
        });
        this.last = this.addButton({
            tooltip: this.lastText,
            iconCls: "x-tbar-page-last",
            disabled: true,
            handler: this.onClick.createDelegate(this, ["last"])
        });
        this.addSeparator();
        this.loading = this.addButton({
            tooltip: this.refreshText,
            iconCls: "x-tbar-loading",
            handler: this.onClick.createDelegate(this, ["refresh"])
        });

        if(this.displayInfo){
            this.displayEl = Ext.fly(this.el.dom).createChild({cls:'x-paging-info'});
        }
        if(this.dsLoaded){
            this.onLoad.apply(this, this.dsLoaded);
        }
    },
    updateInfo : function(){
        if(this.displayEl){
            var count = this.store.getTotalCount();
            var msg = count == 0 ?
                this.emptyMsg :
                String.format(
                    this.displayMsg,
                    this.cursor+1, this.cursor+count, this.store.getTotalCount()
                );
            this.displayEl.update(msg);
        }
    },
    onLoad : function(store, r, o){
        if(!this.rendered){
            this.dsLoaded = [store, r, o];
            return;
        }
		this.cursor = o.params && o.params[this.paramNames.start] ? o.params[this.paramNames.start] : 0;
		var d = this.getPageData(), ap = d.activePage, ps = d.pages;
		this.afterTextEl.el.innerHTML = String.format(this.afterPageText, d.pages, d.total );
		
		this.field.dom.value = ap;
		
		this.first.setDisabled(ap == 1);
		this.prev.setDisabled(ap == 1);
		this.next.setDisabled(ap == ps);
		this.last.setDisabled(ap == ps);
		this.loading.enable();
		this.updateInfo();
		this.fireEvent('change', this, d);
    },
    getPageData : function(){
        var total = this.store.getTotalCount();
        return {
            total : total,
            activePage : Math.ceil((this.cursor+this.pageSize)/this.pageSize),
            pages :  total < this.pageSize ? 1 : Math.ceil(total/this.pageSize)
        };
    },

    onLoadError : function(){
        if(!this.rendered){
            return;
        }
        this.loading.enable();
    },

    readPage : function(d){
        var v = this.field.dom.value, pageNum;
        if (!v || isNaN(pageNum = parseInt(v, 10))) {
            this.field.dom.value = d.activePage;
            return false;
        }
        return pageNum;
    },

    onPagingKeydown : function(e){
        var k = e.getKey(), d = this.getPageData(), pageNum;
        if (k == e.RETURN) {
            e.stopEvent();
            pageNum = this.readPage(d);
            if(pageNum !== false){
                pageNum = Math.min(Math.max(1, pageNum), d.pages) - 1;
                this.doLoad(pageNum * this.pageSize);
            }
        }else if (k == e.HOME || k == e.END){
            e.stopEvent();
            pageNum = k == e.HOME ? 1 : d.pages;
            this.field.dom.value = pageNum;
        }else if (k == e.UP || k == e.PAGEUP || k == e.DOWN || k == e.PAGEDOWN){
            e.stopEvent();
            if(pageNum = this.readPage(d)){
                var increment = e.shiftKey ? 10 : 1;
                if(k == e.DOWN || k == e.PAGEDOWN){
                    increment *= -1;
                }
                pageNum += increment;
                if(pageNum >= 1 & pageNum <= d.pages){
                    this.field.dom.value = pageNum;
                }
            }
        }
    },
    beforeLoad : function(){
        if(this.rendered && this.loading){
            this.loading.disable();
        }
    },
    doLoad : function(start){
    	this.store.changePageControl = true;
        var o = {}, pn = this.paramNames;
        o[pn.start] = start;
        o[pn.limit] = this.pageSize;
        if(this.fireEvent('beforechange', this, o) !== false){
            this.store.load({params:o});
        }
    },
    changePage: function(page){
        this.doLoad(((page-1) * this.pageSize).constrain(0, this.store.getTotalCount()));  
    },

        onClick : function(which){
        var store = this.store;
        switch(which){
            case "first":
                this.doLoad(0);
            break;
            case "prev":
                this.doLoad(Math.max(0, this.cursor-this.pageSize));
            break;
            case "next":
                this.doLoad(this.cursor+this.pageSize);
            break;
            case "last":
                var total = store.getTotalCount();
                var extra = total % this.pageSize;
                var lastStart = extra ? (total - extra) : total-this.pageSize;
                this.doLoad(lastStart);
            break;
            case "refresh":
                this.doLoad(this.cursor);
            break;
        }
    },
    unbind : function(store){
        store = Ext.StoreMgr.lookup(store);
        store.un("beforeload", this.beforeLoad, this);
        store.un("load", this.onLoad, this);
        store.un("loadexception", this.onLoadError, this);
        this.store = undefined;
    },
    bind : function(store){
        store = Ext.StoreMgr.lookup(store);
        store.on("beforeload", this.beforeLoad, this);
        store.on("load", this.onLoad, this);
        store.on("loadexception", this.onLoadError, this);
        this.store = store;
    },
	listeners: {
        change: {
            fn: function(){		
                this.store.setSelectedPageRows();	
            }
        },
        scope:this
    }
});

ExtXeo.grid.rowClickHndlr = function( oGrid, rowIndex, oEvent, sGridInputId, sRowIdentifier ) {
    var sSelRecs = "";
    var oInput = document.getElementById( sGridInputId );
    if( oInput != null ) {
        var oSelRec = oGrid.getStore().getAt( rowIndex );
        sSelRecs = oSelRec.get( sRowIdentifier );
        oInput.value = sSelRecs;
    }
}

ExtXeo.grid.activeRowHndlr = function( oSelModel, oGridInputSelId, rowIdentifier ) {
	var oInput = document.getElementById( oGridInputSelId );
    var activeRow = "";
    if( oInput != null ) {
        var oSelRecs = oSelModel.getSelections();
        for (var i = 0; i < oSelRecs.length; i++)  {
            activeRow = oSelRecs[i].get( rowIdentifier );
        }
        oInput.value = activeRow;
    }
}


/**
 * 
 * Handles the before row selection event, prevents row selection if the
 * max selections are reached
 * 
 * */
ExtXeo.grid.beforeRowSelectionHndlr = function (oSelModel){
	
	
	var maxSelections = oSelModel.grid.getMaxSelections();
	var oSelRecs = oSelModel.getSelections();
	var label = Ext.getCmp(oSelModel.grid.getNumberSelectionsCounterId());
	var gridPanel = oSelModel.grid;
	
	if (maxSelections == -1){
		return true;
	}
	if (oSelRecs.length < maxSelections || oSelRecs.length == 0){
		return true;
	} 
	
	return false;
	
}

/**
 * 
 * Handles row selection (incrementing/decrementing counter if needed)
 * 
 * */
ExtXeo.grid.updateCounter = function (oSelModel){
	
	
	var gridPanel = oSelModel.grid;
	var label = Ext.getCmp(gridPanel.getNumberSelectionsCounterId());
	var oSelRecs = oSelModel.getSelections();
	var maxSelections = gridPanel.getMaxSelections();
	if (gridPanel.getMultiSelections()){
		
		var countItems = 0;
		
		if (gridPanel.getMultiPageSelections()){
			countItems = gridPanel.recordIds.length; 
		}
		else{
			countItems = oSelRecs.length;
		}
		
		if (countItems <= maxSelections - 1 || maxSelections == -1)
       		label.setText(countItems);
       	else
       		label.setText("<span style='color:red'>" + countItems + "</span>",false);
	}
}

ExtXeo.grid.rowSelectionHndlr = function( oSelModel, oGridInputSelId, rowIdentifier) {
	
	
	var sSelRecs = "";
    var oInput = document.getElementById( oGridInputSelId );
    
    if( oInput != null ) {
        var oSelRecs = oSelModel.getSelections();
        for (var i = 0; i < oSelRecs.length; i++)  {
            if(i>0) sSelRecs += "|";
            sSelRecs += oSelRecs[i].get( rowIdentifier );
        }
       	oInput.value = sSelRecs;
        
       	var gridPanel = oSelModel.grid;
       	
       	if (gridPanel.getMultiPageSelections())
       	{
	        try
	        {
		        if(oSelModel.grid.getStore().changePageControl == false)
				{
					oSelModel.grid.getStore().rowIdentifier = rowIdentifier;
					for(var j = 0; j < oSelModel.grid.getStore().getCount(); j++)
					{
						var currRow = oSelModel.grid.getStore().getAt(j);
						var rId = currRow.get( rowIdentifier );				
						var isRowSelected = false;
						 
						for (var k = 0; k < oSelRecs.length; k++)  {
							var tempId = oSelRecs[k].get( rowIdentifier );
							
							if(tempId == rId)
							{
								isRowSelected = true;
								break;
							}
						} 
						if(isRowSelected == true)
						{
							var curIdx = oSelModel.grid.recordIds.indexOf(rId);
							
							if(curIdx < 0)
							{
								oSelModel.grid.recordIds[oSelModel.grid.recordIds.length] = rId;
							}					
						}
						else
						{ 
							var curIdx = oSelModel.grid.recordIds.indexOf(rId);
							
							if(curIdx >= 0)
							{			
								oSelModel.grid.recordIds.splice(curIdx, 1);
							}
						}				 
					}
					
					var tempSelectRecs = "";
					var selectedControl = false;
					for (var k = 0; k < oSelModel.grid.recordIds.length; k++)  {
						if(k>0) tempSelectRecs += "|";
						tempSelectRecs += oSelModel.grid.recordIds[k];
						selectedControl = true;
					}
					if(selectedControl)
					{
						oInput.value = tempSelectRecs;
					}
				}
	        }
	        catch(err)
	        {console.log(err);}
       	}
       	
       	//Update Counter
       	ExtXeo.grid.updateCounter(oSelModel);
       	
    }
    else {
        alert('Select rows input not found!!');
    }
}

/**
 * 
 * Renderer for a column so that it wraps text
 * 
 * */
ExtXeo.grid.nowrap = function(text){
    return '<div style="white-space:normal !important;">'+ text +'</div>';
}