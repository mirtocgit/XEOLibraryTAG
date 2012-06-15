<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0">
<xsl:output method="html" encoding="UTF-8"/>
    
    <xsl:template match="/">
        <html>
        <head>
            <title><xsl:value-of select="./xeoModel/general/@name"/></title>
            <link rel="stylesheet" type="text/css" href="ext/resources/css/ext-all.css"/>
            <link rel="stylesheet" type="text/css" href="css/my.css"/>
            
            <style type="text/css">
                .hidden {display:none}
                .shown {display:inline}
            </style>
            
            <script type="text/javascript" src="ext/adapter/ext/ext-base.js"></script>
            <!-- ...then you need the Ext itself, either debug or production version. -->
            <script type="text/javascript" src="ext/ext-all.js"></script>
            
            <script type="text/Javascript">
                function togglecomments (postid) {
                var whichpost = document.getElementById(postid);
                if (whichpost.className=="shown") { whichpost.className="hidden"; } else { whichpost.className="shown"; }
                } 
            </script>
            
            
            
            <!-- Panel for Events -->
            <script type="text/javascript">
                Ext.onReady(function(){
                var eventsPanel = new Ext.Panel({
                title: 'Events',
                collapsible:true,
                iconCls : 'events-icon',
                contentEl: 'eventsDiv',
                renderTo: 'eventsPanel',
                autoWidth : true
                });
                });	
            </script>
            
            <!-- Panel for Methods -->
            <script type="text/javascript">
                Ext.onReady(function(){
                var methodsPanel = new Ext.Panel({
                title: 'Methods',
                collapsible:true,
                iconCls : 'methods-icon',
                contentEl: 'methodsDiv',
                renderTo: 'methodsPanel',
                autoWidth : true
                });
                });	
            </script>
            
            <!-- Panel for Attributes -->
            <script type="text/javascript">
                Ext.onReady(function(){
                var attPanel = new Ext.Panel({
                title: 'Attributes',
                collapsible:true,
                iconCls : 'attributes-icon',
                contentEl: 'attributesDiv',
                renderTo: 'attributesPanel',
                autoWidth : true
                });
                });	
            </script>
            
            <!-- Panel for OPL -->
            <script type="text/javascript">
                Ext.onReady(function(){
                var oplPanel = new Ext.Panel({
                title: 'Object Policy Labelling',
                collapsible:true,
                contentEl: 'oplDiv',
                renderTo: 'oplPanel',
                autoWidth : true
                });
                });	
            </script>
            
            <!-- Panel for General Definitions -->
            <script type="text/javascript">
                Ext.onReady(function(){
                var generalPanel = new Ext.Panel({
                title: 'General Definitions',
                collapsible:true,
                contentEl: 'generalDiv',
                renderTo: 'generalPanel',
                autoWidth : true
                });
                });	
            </script>
        </head>    
        <body>
            <xsl:apply-templates select="./xeoModel"></xsl:apply-templates>
        </body>
        </html>
    </xsl:template>
    
    
    <xsl:template match="xeoModel">
        
        <h1>
            <xsl:if test="./general/@type='interface'">
                <img src="images/xeointerface_icon.gif" width="16" height="16"></img>
            </xsl:if>    
            <xsl:if test="./general/@type != 'interface'">
                <img src="images/xeouml_icon.gif" width="16" height="16"></img>
            </xsl:if>
            <xsl:value-of select="./general/@name"/>
        </h1>
        
        <!-- General Definitions of the Model -->
        <div id="generalPanel">
            <xsl:apply-templates select="general"></xsl:apply-templates>
        </div>    
        
        <!-- EVENTS -->
        <xsl:apply-templates select="events"></xsl:apply-templates>
        
        <!-- OPL -->
        <xsl:apply-templates select="opl"></xsl:apply-templates>
        
        <!-- Methods -->
        <xsl:apply-templates select="methods"></xsl:apply-templates>
        
        <!-- Attributes -->
        
        <div id="attributesPanel"></div>
        <div id="attributesDiv">
            <h2><xsl:value-of select="count(./attributes/*)"></xsl:value-of>
                Attribute(s)</h2>
            <table class="relations">
                <tr>
                    <th>Name</th><th>Type</th><th>Label</th><th>Description</th>
                </tr> 
                <xsl:apply-templates select="./attributes/attributeText" />
                <xsl:apply-templates select="./attributes/attributeNumber" />
                <xsl:apply-templates select="./attributes/attributeLongText" />
                <xsl:apply-templates select="./attributes/attributeCurrency" />
                <xsl:apply-templates select="./attributes/attributeSequence" />
                <xsl:apply-templates select="./attributes/attributeBoolean" />
                <xsl:apply-templates select="./attributes/attributeDuration" />
                <xsl:apply-templates select="./attributes/attributeDate" />
                <xsl:apply-templates select="./attributes/attributeDateTime" />
                <xsl:apply-templates select="./attributes/attributeBinaryData" />
            </table>
            
            <xsl:if test="count(attributes/attributeObject) > 0">
                <h2><img src="images/attributeObject.gif" width="16" height="16"></img> Attribute Object</h2>
                <table class="relations">
                    <tr>
                        <th>Name</th><th>Target</th><th>Label</th><th>Orphan</th><th>Description</th>
                    </tr>
                    <xsl:apply-templates select="attributes/attributeObject"/>
                </table>
            </xsl:if>
            <xsl:if test="count(attributes/attributeObjectCollection) > 0">
                <h2><img src="images/attributeObjectCollection.gif" width="16" height="16"></img> Attribute ObjectCollection</h2>
                <table class="relations">
                    <tr>
                        <th>Name</th><th>Target</th><th>Label</th><th>Description</th>
                    </tr>        
                    <xsl:apply-templates select="attributes/attributeObjectCollection"></xsl:apply-templates>
                </table>
            </xsl:if>
            
        </div>
        
    </xsl:template>
    
    <!-- Events -->
    <xsl:template match="events">
        <xsl:if test="count(./event) > 0">
            <div id="eventsPanel"></div>
            <div id="eventsDiv">
                <h2><xsl:value-of select="count(./event)">         
                </xsl:value-of> Event(s)</h2>
                <table class="relations">
                    <tr><th>Event</th><th>Action</th></tr>
                    <xsl:for-each select="./event">
                        <tr>
                            <td><img src="images/events.gif" width="16" height="16"></img><xsl:value-of select="./@name"/></td>
                            <td>
                                <pre class="sh_java">
                                    <xsl:value-of select="./body/text()"/>
                                </pre>
                            </td>
                        </tr>   
                        <xsl:if test="count(./documentation) > 0">
                            <tr>
                                <td colspan="2" class="small">
                                    <xsl:value-of select="./documentation/inline[1]/description"/>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </div>
        </xsl:if>
    </xsl:template>
    <!-- General Definition -->
    <xsl:template match="general">
        <div id="generalDiv">
            Definitions
            <table class="relations">
                <tr>
                    <th>Property</th>
                    <th>Values</th>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><xsl:value-of select="./@name"></xsl:value-of></td>
                </tr>
                <tr>
                    <td>Type</td>
                    <td><xsl:value-of select="./@type"></xsl:value-of></td>
                </tr>
                <tr>
                    <td>Card ID</td>
                    <td><xsl:value-of select="./cardID/text()"></xsl:value-of></td>
                </tr>
                <xsl:if test="string-length(./label/text())> 0">
                    <tr>
                        <td>Label</td>
                        <td><xsl:value-of select="./label/text()"></xsl:value-of></td>
                    </tr>
                </xsl:if>
                <xsl:if test="string-length(./@version)> 0">
                    <tr>
                        <td>Version</td>
                        <td><xsl:value-of select="./@version"></xsl:value-of></td>
                    </tr>
                </xsl:if>
                <xsl:if test="string-length(./@extends)> 0">
                    <tr>
                        <td>Extends</td>
                        <td><xsl:value-of select="./@extends"></xsl:value-of></td>
                    </tr>
                </xsl:if>
                <xsl:if test="string-length(./@mastertable)> 0">
                    <tr>
                        <td>Master Table</td>
                        <td><xsl:value-of select="./@mastertable"></xsl:value-of></td>
                    </tr>
                </xsl:if>
                <tr>
                    <td>Orphan</td>
                    <xsl:if test="string-length(./@orphan) = 0">
                        <td><img src="images/ok.gif" width="16" height="16"></img></td>
                    </xsl:if>
                    <xsl:if test="string-length(./@orphan) > 0">
                        <xsl:if test="./@orphan='true'">
                            <td><img src="images/ok.gif" width="16" height="16"></img></td>
                        </xsl:if>
                        <xsl:if test="./@orphan='false'">
                            <td><img src="images/no.gif" width="16" height="16"></img></td>
                        </xsl:if>
                    </xsl:if>
                </tr>    
                <tr>
                    <td>Multiparent</td>
                    <xsl:if test="string-length(./@multiparent) = 0">
                        <td><img src="images/no.gif" width="16" height="16"></img></td>
                    </xsl:if>
                    <xsl:if test="string-length(./@multiparent) > 0">
                        <xsl:if test="./@multiparent = 'true'">
                            <td><img src="images/ok.gif" width="16" height="16"></img></td>
                        </xsl:if>
                        <xsl:if test="./@multiparent = 'false'">
                            <td><img src="images/no.gif" width="16" height="16"></img></td>
                        </xsl:if>
                    </xsl:if>
                </tr>
                <xsl:if test="string-length(./@extendsJavaClass)> 0">
                    <tr>
                        <td>Extends Java Class</td>
                        <td><xsl:value-of select="./@extendsJavaClass"></xsl:value-of></td>
                    </tr>
                </xsl:if>
            </table>
            
            <xsl:if test="count(./database/object) > 0">
                Database Constraints
                <table class="relations">
                    <tr>
                        <th>Constraint</th>
                        <th>Name of Constraint</th>
                        <th>Label</th>
                        <th>Attributes affected</th>
                    </tr>
                    <xsl:for-each select="./database/object">
                        <tr>
                            <td><xsl:value-of select="./@type"></xsl:value-of></td>
                            <td><xsl:value-of select="./@id"></xsl:value-of></td>
                            <td><xsl:value-of select="./label/text()"></xsl:value-of></td>
                            <td><xsl:value-of select="./expression/text()"></xsl:value-of></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </xsl:if>
            
            <xsl:if test="count(./databaseManager/attribute::node()) > 0">
                Database Manager
                <table class="relations">
                    <tr>
                        <th>dataManagerClass</th>
                        <th>xeoCompatible</th>
                        <th>manageViews</th>
                        <th>manageTables</th>
                    </tr>
                    <tr>
                        <td><xsl:value-of select="./databaseManager/@dataManagerClass"></xsl:value-of></td>
                        <td><xsl:value-of select="./databaseManager/@xeoCompatible"></xsl:value-of></td>
                        <td><xsl:value-of select="./databaseManager/@manageViews"></xsl:value-of></td>
                        <td><xsl:value-of select="./databaseManager/@manageTables"></xsl:value-of></td>
                    </tr>
                </table>
            </xsl:if>
            
            <xsl:if test="count(./castTo/interface)> 0">
                Cast to: <xsl:value-of select="./castTo/@class"></xsl:value-of>
                <ul>
                    <xsl:for-each select="./castTo/interface">
                        <li><xsl:value-of select="./text()"></xsl:value-of></li>
                    </xsl:for-each>
                </ul>
            </xsl:if>
            
            <!-- Text Index of General -->
            <xsl:if test="count(./textIndex/attribute::node()) > 0">
                <fieldset>
                    <legend>Text Index</legend>
                    <table class="relations">
                        <tr>
                            <th>Active</th>
                            <th>Append Childs</th>
                            <th>Depth</th>
                        </tr>
                        <tr>
                            <td>
                                <xsl:if test="./textIndex/@active='true'">
                                    <img src="images/ok.gif" width="16" height="16"></img>
                                </xsl:if>
                                <xsl:if test="./textIndex/@active='false'">
                                    <img src="images/no.gif" width="16" height="16"></img>
                                </xsl:if>
                            </td>
                            <td>
                                <xsl:if test="./textIndex/@appendChilds = 'true' or count(./textIndex/@appendChilds) = 0">
                                    <img src="images/ok.gif" width="16" height="16"></img>
                                </xsl:if>
                                <xsl:if test="./textIndex/@appendChilds = 'false'">
                                    <img src="images/no.gif" width="16" height="16"></img>
                                </xsl:if>
                            </td>
                            <td>
                                <xsl:if test="count(./textIndex/@deep) = 0">
                                    1
                                </xsl:if>
                                <xsl:if test="count(./textIndex/@deep)  > 0">
                                    <xsl:value-of select="./textIndex/@deep"></xsl:value-of>
                                </xsl:if>
                            </td>
                        </tr>
                    </table>
                    <xsl:if test="count(./textIndex/process/*) > 0">
                        Attributes Processed by Text Index
                        <table class="relations">
                            <tr>
                                <th>Attribute</th>
                                <th>Only Card ID</th>
                            </tr>
                            <xsl:for-each select="./textIndex/process/attribute">
                                <tr>
                                    <td><xsl:value-of select="text()"></xsl:value-of></td>
                                    <td>
                                        <xsl:choose>
                                            <xsl:when test="count(./@onlyCardID) = 0 or ./@onlyCardID = 'false'">
                                                <img src="images/no.gif" width="16" height="16"/>
                                            </xsl:when>
                                            <xsl:when test="./@onlyCardID = 'true'">
                                                <img src="images/ok.gif" width="16" height="16"/>
                                            </xsl:when>
                                        </xsl:choose>
                                    </td>
                                </tr>
                            </xsl:for-each>
                        </table>
                        
                    </xsl:if>
                </fieldset>
            </xsl:if>
        </div>
    </xsl:template>
    <!-- Methods -->
    <xsl:template match="methods">
        <xsl:if test="count(./method) > 0">
            <div id="methodsPanel"></div>
            <div id="methodsDiv">
                <h2><xsl:value-of select="count(./method)"></xsl:value-of> Method(s)</h2>
                <table class="relations"> 
                    <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                    <xsl:for-each select="./method">
                        <tr>
                            <td>
                                <xsl:value-of select="./@name"/>
                            </td>
                            <td><xsl:value-of select="./label"/></td>
                            <td><xsl:value-of select="./body/@language"/></td>
                            <td>
                                <pre class="sh_java">
                                    <xsl:value-of select="string(./body)"/>
                                </pre>
                            </td>
                            <td>
                                <xsl:if test="count(./hiddenWhen/text()) > 0">
                                    <pre class="sh_java">
                                        <xsl:value-of select="./hiddenWhen/text()"/>
                                    </pre>
                                </xsl:if>
                                <xsl:if test="./hiddenWhen/text() = ''">
                                    --
                                </xsl:if>
                            </td>
                            <td>
                                <pre class="sh_javascript">
                                    <xsl:value-of select="string(./javascriptToRun)"/>
                                </pre>
                                <!-- Javascript -->
                            </td>
                            <td>
                                <xsl:value-of select="./icon/text()"/>
                                <!-- Icon -->
                            </td>
                            <td>
                                <xsl:value-of select="./assinature/text()"/>
                                <!-- Assinature -->
                            </td>
                        </tr>
                        <xsl:if test="count(./documentation) > 0">
                            <tr>
                                <td colspan="5" class="small">
                                    <xsl:value-of select="./documentation/inline[1]/description"/>
                                </td>
                            </tr>
                        </xsl:if>
                    </xsl:for-each>
                </table>
            </div>
        </xsl:if>
        
    </xsl:template>
    
    <!-- Object Policy Labeling -->
    <xsl:template match="opl">
        <xsl:if test="count(./*) > 0">
        <div id="oplPanel"></div>
        <div id="oplDiv">    
        <xsl:if test="(count(./classKeys/class) > 0)  or  (count(./attributeKeys/*) > 0)">
            <h2>OPL (Object Policy Labelling)
                <xsl:if test="./@active='true' or count(./@active) = 0">
                    - Active
                </xsl:if>  
                <xsl:if test="./@active='false'">
                    - Not Active
                </xsl:if>   
            </h2>    
        </xsl:if>
        <xsl:if test="count(./classKeys/class) > 0 ">
            <fieldset>
                <legend>Class Keys</legend>
            </fieldset>
            <table class="relations">
                <tr>
                    <th>Class</th>
                    <th>Active</th>
                </tr>
            </table>
            <xsl:for-each select="./classKeys/class">
                <tr>
                    <td><xsl:value-of select="./@name"/></td>
                    <td><xsl:value-of select="./@active"/></td>
                </tr>
            </xsl:for-each>
        </xsl:if>
        <xsl:if test="count(./attributeKeys/*) > 0 ">
            
            
            <fieldset>
                <legend>Attribute Keys</legend>
                Read Permissions
                <ul>
                    <xsl:for-each select="./attributeKeys/read/attribute">
                        <li><xsl:value-of select="./text()"></xsl:value-of></li>
                    </xsl:for-each>    
                </ul> 
                Write Permissions
                <ul>
                    <xsl:for-each select="./attributeKeys/write/attribute">
                        <li><xsl:value-of select="./text()"></xsl:value-of></li>
                    </xsl:for-each>
                </ul>
                Delete Permissions        
                <ul>
                    <xsl:for-each select="./attributeKeys/delete/attribute">
                        <li><xsl:value-of select="./text()"></xsl:value-of></li>
                    </xsl:for-each>
                </ul>
                Full Control Permissions
                <ul>
                    <xsl:for-each select="./attributeKeys/fullcontrol/attribute">
                        <li><xsl:value-of select="./text()"></xsl:value-of></li>
                    </xsl:for-each>
                </ul>
            </fieldset>
        </xsl:if>
        </div>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="attributes/attributeObject">
        <xsl:for-each select=".">
            <xsl:variable name="objName">
                <xsl:value-of select="substring-after(./type/text(),'object.')"/>
            </xsl:variable>
            <tr>
                <td><img src="images/attributeObject.gif" width="16" height="16"></img>
                    <xsl:value-of select="./@name"/>
                    <xsl:text> </xsl:text>
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)</td>
                <td>
                    <xsl:value-of select="./type/text()"/>
                </td>
                <td><xsl:value-of select="./label"/></td>
                <td></td>
                <td><xsl:value-of select="./description"/></td>
            </tr>
            <tr>
                <td colspan="5">
                    <div id="{./@name}" class="hidden">
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                            <xsl:apply-templates select="./renderAsLov"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualOperation"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualAdd"></xsl:apply-templates>
                            <xsl:apply-templates select="./showLookup"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualAdd"></xsl:apply-templates>
                            <xsl:apply-templates select="./orphanRelation"></xsl:apply-templates>
                            
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                        <xsl:if test="count(./objectFilter/*) > 0">
                            <xsl:apply-templates select="./objectFilter"></xsl:apply-templates>
                        </xsl:if>
                        
                        <xsl:if test="count(./objects/*) > 0">
                            <xsl:apply-templates select="./objects"></xsl:apply-templates>
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    
    <xsl:template match="attributes/attributeObjectCollection">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <img src="images/attributeObjectCollection.gif" width="16" height="16"></img>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    <xsl:value-of select="substring-after(./type/text(),'object.')"/>
                </td>
                <td><xsl:value-of select="./label"/></td>
                <td><xsl:value-of select="./description"/></td>
            </tr>
            <tr>
                <td colspan="5">
                    <div id="{./@name}" class="hidden">
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                            <xsl:apply-templates select="./renderAsLov"></xsl:apply-templates>
                            <xsl:apply-templates select="./minOccurs"></xsl:apply-templates>
                            <xsl:apply-templates select="./maxOccurs"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualOperation"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualAdd"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualCreate"></xsl:apply-templates>
                            <xsl:apply-templates select="./showLookup"></xsl:apply-templates>
                            <xsl:apply-templates select="./manualAdd"></xsl:apply-templates>
                            <xsl:apply-templates select="./orphanRelation"></xsl:apply-templates>
                            
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                        <xsl:if test="count(./objectFilter/*) > 0">
                            <xsl:apply-templates select="./objectFilter"></xsl:apply-templates>
                        </xsl:if>
                        
                        <xsl:if test="count(./objects/*) > 0">
                            <xsl:apply-templates select="./objects"></xsl:apply-templates>
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    
    
    <!-- 
     ******************************************************************   
        Attributes Definition
    ****************************************************************** -->
    
    <xsl:template match="attributeText">
        <xsl:for-each select=".">
                <tr>
                    <td>
                        <xsl:value-of select="./@name"/> 
                        (<a href="javascript:togglecomments('{./@name}')">details</a>)
                    </td>
                    <td>
                       AttributeText 
                    </td>
                    <td>
                        <xsl:value-of select="./label/text()"/>
                    </td>
                    <td>
                        <xsl:value-of select="./description/text()"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <div class="hidden">
                            <xsl:attribute name="id" >
                           <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Value</th>
                                </tr>
                                <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                                <xsl:apply-templates select="./len"></xsl:apply-templates>
                                <xsl:apply-templates select="./editor"></xsl:apply-templates>
                                <xsl:apply-templates select="./behavior"></xsl:apply-templates>
                                
                            </table>
                            <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                            </xsl:if>
                            
                            
                            <xsl:if test="count(./events/event) > 0">
                                <img src="images/events.gif" width="16" height="16"></img>Events
                                <table class="relations">
                                    <tr>
                                        <th>Event</th>
                                        <th>Body</th>
                                    </tr>
                                    <xsl:for-each select="./events/event">
                                        <xsl:apply-templates select="."></xsl:apply-templates>
                                    </xsl:for-each>
                                </table>
                            </xsl:if>
                            
                            <xsl:if test="count(./methods/method) > 0">
                                <img src="images/methods.gif" width="16" height="16"></img>Methods
                                <table class="relations">
                                    <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                    <xsl:for-each select="./methods/method">
                                        <xsl:apply-templates select="."></xsl:apply-templates>
                                    </xsl:for-each>
                                </table>
                            </xsl:if>
                        
                            <xsl:if test="count(./textIndex) > 0">
                                <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                            </xsl:if>
                            
                            
                            <xsl:if test="count(./database/*) > 0 ">
                                <xsl:apply-templates select="./database"></xsl:apply-templates>    
                            </xsl:if>
                            
                            <xsl:if test="count(./lov) > 0">
                                <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                            </xsl:if>
                            
                        </div>
                    </td>
                </tr>
            </xsl:for-each>
    </xsl:template>
    
    
    <xsl:template match="attributeNumber">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeNumber 
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./len"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                            <xsl:apply-templates select="./min"></xsl:apply-templates>
                            <xsl:apply-templates select="./max"></xsl:apply-templates>
                            <xsl:apply-templates select="./grouping"></xsl:apply-templates>
                            <xsl:apply-templates select="./decimals"></xsl:apply-templates>
                            <xsl:apply-templates select="./minDecimals"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Language</th>
                                <th>Content</th>
                            </tr>
                            <xsl:apply-templates select="./required"></xsl:apply-templates>
                            <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                            <xsl:apply-templates select="./formula"></xsl:apply-templates>
                            <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                            <xsl:apply-templates select="./valid"></xsl:apply-templates>
                            <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                            <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                            <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                        </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="attributeLongText">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeLongText 
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./len"></xsl:apply-templates>
                            <xsl:apply-templates select="./editor"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Language</th>
                                <th>Content</th>
                            </tr>
                            <xsl:apply-templates select="./required"></xsl:apply-templates>
                            <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                            <xsl:apply-templates select="./formula"></xsl:apply-templates>
                            <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                            <xsl:apply-templates select="./valid"></xsl:apply-templates>
                            <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                            <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                            <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                        </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="attributeCurrency">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeCurrency
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                            <xsl:apply-templates select="./grouping"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="attributeSequence">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeSequence
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./type"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                            <xsl:apply-templates select="./len"></xsl:apply-templates>
                            <xsl:apply-templates select="./min"></xsl:apply-templates>
                            <xsl:apply-templates select="./max"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="attributeBoolean">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeBoolean 
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./renderAsCheck"></xsl:apply-templates>
                            
                            
                        </table>
                        <xsl:if test="count(./*/@language) > 0">
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Language</th>
                                <th>Content</th>
                            </tr>
                            <xsl:apply-templates select="./required"></xsl:apply-templates>
                            <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                            <xsl:apply-templates select="./formula"></xsl:apply-templates>
                            <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                            <xsl:apply-templates select="./valid"></xsl:apply-templates>
                            <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                            <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                            <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                        </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="attributeDuration">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeDuration
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                            <xsl:apply-templates select="./clock"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
        
    </xsl:template>
    <xsl:template match="attributeDate">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeDate
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
        
    </xsl:template>
    <xsl:template match="attributeDateTime">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeDateTime
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="attributeBinaryData">
        <xsl:for-each select=".">
            <tr>
                <td>
                    <xsl:value-of select="./@name"/> 
                    (<a href="javascript:togglecomments('{./@name}')">details</a>)
                </td>
                <td>
                    AttributeBinaryData
                </td>
                <td>
                    <xsl:value-of select="./label/text()"/>
                </td>
                <td>
                    <xsl:value-of select="./description/text()"/>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <div
                        class="hidden">
                        <xsl:attribute name="id" >
                            <xsl:value-of select="./@name"/>
                        </xsl:attribute>
                        <table class="relations">
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                            </tr>
                            <xsl:apply-templates select="./tooltip"></xsl:apply-templates>
                            <xsl:apply-templates select="./setParent"></xsl:apply-templates>
                        </table>
                        
                        <xsl:if test="count(./*/@language) > 0">
                            <table class="relations">
                                <tr>
                                    <th>Property</th>
                                    <th>Language</th>
                                    <th>Content</th>
                                </tr>
                                <xsl:apply-templates select="./required"></xsl:apply-templates>
                                <xsl:apply-templates select="./recommend"></xsl:apply-templates>
                                <xsl:apply-templates select="./formula"></xsl:apply-templates>
                                <xsl:apply-templates select="./defaultValue"></xsl:apply-templates>
                                <xsl:apply-templates select="./valid"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./onChangeSubmit"></xsl:apply-templates>
                                <xsl:apply-templates select="./hiddenWhen"></xsl:apply-templates>
                                <xsl:apply-templates select="./disableWhen"></xsl:apply-templates>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./events/event) > 0">
                            <img src="images/events.gif" width="16" height="16"></img>Events
                            <table class="relations">
                                <tr>
                                    <th>Event</th>
                                    <th>Body</th>
                                </tr>
                                <xsl:for-each select="./events/event">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./methods/method) > 0">
                            <img src="images/methods.gif" width="16" height="16"></img>Methods
                            <table class="relations">
                                <tr><th>Method</th><th>Label</th><th>Language</th><th>Body</th><th>Hidden When</th><th>Javascript</th><th>Icon</th><th>Assinature</th></tr>
                                <xsl:for-each select="./methods/method">
                                    <xsl:apply-templates select="."></xsl:apply-templates>
                                </xsl:for-each>
                            </table>
                        </xsl:if>
                        
                        <xsl:if test="count(./textIndex) > 0">
                            <xsl:apply-templates select="./textIndex"></xsl:apply-templates>        
                        </xsl:if>
                        
                        
                        <xsl:if test="count(./database/*) > 0 ">
                            <xsl:apply-templates select="./database"></xsl:apply-templates>    
                        </xsl:if>
                        
                        <xsl:if test="count(./lov) > 0">
                            <xsl:apply-templates select="./lov"></xsl:apply-templates>                                
                        </xsl:if>
                        
                    </div>
                </td>
            </tr>
        </xsl:for-each>
    </xsl:template>
    
    <!-- Properties of the attributes: Simple Attribute Types -->
    <xsl:template match="tooltip">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>tooltip</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="len">
        <xsl:if test="string-length(text()) > 0">
        <tr>
            <td>len</td>
            <td><xsl:value-of select="text()"></xsl:value-of></td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="editor">
        <xsl:if test="string-length(text()) > 0">
        <tr>
            <td>editor</td>
            <td><xsl:value-of select="text()"></xsl:value-of></td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="setParent">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>setParent</td>
                <td>
                    <xsl:if test="text() = 'true'">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="min">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>min</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="max">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>max</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="grouping">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>grouping</td>
                <td>
                    <xsl:if test="text() = 'true'">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="decimals">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>decimals</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="minDecimals">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>minDecimals</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="clock">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>clock</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="renderAsCheck">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>renderAsCheck</td>
                <td>
                    <xsl:if test="text() = 'true'">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="renderAsLov">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>renderAsLov</td>
                <td>
                    <xsl:if test="text() = 'true'">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>    
    <xsl:template match="minOccurs">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>minOccurs</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="maxOccurs">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>maxOccurs</td>
                <td><xsl:value-of select="text()"></xsl:value-of></td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="manualOperation">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>manualOperation</td>
                <td>
                    <xsl:if test="text() = 'true' or string-length(text()) = 0">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="manualAdd">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>manualAdd</td>
                <td>
                    <xsl:if test="text() = 'true' or string-length(text()) = 0">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="manualCreate">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>manualCreate</td>
                <td>
                    <xsl:if test="text() = 'true' or string-length(text()) = 0">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="showLookup">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>showLookup</td>
                <td>
                    <xsl:if test="text() = 'true'">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="orphanRelation">
        <xsl:if test="string-length(text()) > 0">
            <tr>
                <td>orphanRelation</td>
                <td>
                    <xsl:if test="text() = 'true'">
                        <img src="images/ok.gif" width="16" height="16"></img>
                    </xsl:if>
                    <xsl:if test="text() = 'false'">
                        <img src="images/no.gif" width="16" height="16"></img>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="behavior">
        <xsl:if test="string-length(script/text()) > 0">
            <tr>
                <td>behavior</td>
                <td>
                    <xsl:value-of select="script/text()"></xsl:value-of>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    
    <!-- Properties of attributes that have a language associated -->
    <xsl:template match="required">
        <xsl:if test="count(./@language) > 0">
        <tr>
            <td>required</td>
            <td><xsl:value-of select="./@language"></xsl:value-of></td>
            <td>
                <xsl:if test="./@language = 'JAVA'">
                    <pre class="sh_java">
                        <xsl:value-of select="text()"></xsl:value-of>
                    </pre>
                </xsl:if>
                <xsl:if test="./@language != 'JAVA'">
                        <xsl:value-of select="text()"></xsl:value-of>
                </xsl:if>
            </td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="recommend">
        <xsl:if test="count(./@language) > 0">
        <tr>
            <td>recommed</td>
            <td><xsl:value-of select="./@language"></xsl:value-of></td>
            <td><xsl:if test="./@language = 'JAVA'">
                <pre class="sh_java">
                    <xsl:value-of select="text()"></xsl:value-of>
                </pre>
            </xsl:if>
                <xsl:if test="./@language != 'JAVA'">
                    <xsl:value-of select="text()"></xsl:value-of>
                </xsl:if></td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="formula">
        <xsl:if test="count(./@language) > 0">
        <tr>
            <td>formula</td>
            <td><xsl:value-of select="./@language"></xsl:value-of></td>
            <td>
            <xsl:if test="./@language = 'JAVA'">
                <pre class="sh_java">
                    <xsl:value-of select="text()"></xsl:value-of>
                </pre>
            </xsl:if>
            <xsl:if test="./@language != 'JAVA'">
                <xsl:value-of select="text()"></xsl:value-of>
            </xsl:if>
            </td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="defaultValue">
        <xsl:if test="count(./@language) > 0">
        <tr>
            <td>defaultValue</td>
            <td><xsl:value-of select="./@language"></xsl:value-of></td>
            <td>
                <xsl:if test="./@language = 'JAVA'">
                    <pre class="sh_java">
                        <xsl:value-of select="text()"></xsl:value-of>
                    </pre>
                </xsl:if>
                <xsl:if test="./@language != 'JAVA'">
                    <xsl:value-of select="text()"></xsl:value-of>
                </xsl:if>
            </td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="valid">
        <xsl:if test="count(./@language) > 0">
        <tr>
            <td>valid</td>
            <td><xsl:value-of select="./@language"></xsl:value-of></td>
            <td>
                <xsl:if test="./@language = 'JAVA'">
                    <pre class="sh_java">
                        <xsl:value-of select="text()"></xsl:value-of>
                    </pre>
                </xsl:if>
                <xsl:if test="./@language != 'JAVA'">
                    <xsl:value-of select="text()"></xsl:value-of>
                </xsl:if>
            </td>
        </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="disableWhen">
        <xsl:if test="count(./@language) > 0">
            <tr>
                <td>disableWhen</td>
                <td><xsl:value-of select="./@language"></xsl:value-of></td>
                <td>
                    <xsl:if test="./@language = 'JAVA'">
                        <pre class="sh_java">
                            <xsl:value-of select="text()"></xsl:value-of>
                        </pre>
                    </xsl:if>
                    <xsl:if test="./@language != 'JAVA'">
                        <xsl:value-of select="text()"></xsl:value-of>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="hiddenWhen">
        <xsl:if test="count(./@language) > 0">
            <tr>
                <td>hiddenWhen</td>
                <td><xsl:value-of select="./@language"></xsl:value-of></td>
                <td>
                    <xsl:if test="./@language = 'JAVA'">
                        <pre class="sh_java">
                            <xsl:value-of select="text()"></xsl:value-of>
                        </pre>
                    </xsl:if>
                    <xsl:if test="./@language != 'JAVA'">
                        <xsl:value-of select="text()"></xsl:value-of>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="onChangeSubmit">
        <xsl:if test="count(./@language) > 0">
            <tr>
                <td>onChangeSubmit</td>
                <td><xsl:value-of select="./@language"></xsl:value-of></td>
                <td>
                    <xsl:if test="./@language = 'JAVA'">
                        <pre class="sh_java">
                            <xsl:value-of select="text()"></xsl:value-of>
                        </pre>
                    </xsl:if>
                    <xsl:if test="./@language != 'JAVA'">
                        <xsl:value-of select="text()"></xsl:value-of>
                    </xsl:if>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    
    <!-- Attributes that require a special type of render -->
    <xsl:template match="database">
        <xsl:if test="string-length(./fieldname/text()) > 0">
        Database Restrictions
        <table class="relations">
            <tr>
                <th>Fieldname</th>
                <th>Unique</th>
                <th>IndexFull</th>
                <th>Indexed</th>
                <th>Tabled</th>
                <th>Constraint</th>
                <th>Binding</th>
                <th>Keys</th>
            </tr>
            <tr>
                <td><xsl:value-of select="./fieldname/text()"></xsl:value-of></td>
                <td>
                    <xsl:choose>
                        <xsl:when test="./unique/text() = 'false'"><img src="images/no.gif" width="16" height="16"></img></xsl:when>
                        <xsl:when test="./unique/text() = 'true' "><img src="images/ok.gif" width="16" height="16"></img></xsl:when>
                    </xsl:choose>
                </td>
                <td>
                    <xsl:choose>
                        <xsl:when test="./indexfull/text() = 'false'"><img src="images/no.gif" width="16" height="16"></img></xsl:when>
                        <xsl:when test="./indexfull/text() = 'true' "><img src="images/ok.gif" width="16" height="16"></img></xsl:when>
                    </xsl:choose>
                </td>
                <td>
                    <xsl:choose>
                        <xsl:when test="./indexed/text() = 'false'"><img src="images/no.gif" width="16" height="16"></img></xsl:when>
                        <xsl:when test="./indexed/text() = 'true' "><img src="images/ok.gif" width="16" height="16"></img></xsl:when>
                    </xsl:choose>
                </td>
                <td>
                    <xsl:choose>
                        <xsl:when test="./tabled/text() = 'false'"><img src="images/no.gif" width="16" height="16"></img></xsl:when>
                        <xsl:when test="./tabled/text() = 'true' "><img src="images/ok.gif" width="16" height="16"></img></xsl:when>
                    </xsl:choose>
                </td>
                <td>
                    <xsl:choose>
                        <xsl:when test="./constraint/text() = 'false'"><img src="images/no.gif" width="16" height="16"></img></xsl:when>
                        <xsl:when test="./constraint/text() = 'true' "><img src="images/ok.gif" width="16" height="16"></img></xsl:when>
                    </xsl:choose>
                </td>
                <td>
                    <xsl:choose>
                        <xsl:when test="./binding/text() = 'false'"><img src="images/no.gif" width="16" height="16"></img></xsl:when>
                        <xsl:when test="./binding/text() = 'true' "><img src="images/ok.gif" width="16" height="16"></img></xsl:when>
                    </xsl:choose>
                </td>
                <td>
                    <xsl:if test="count(./relation/key)> 0 ">
                        <xsl:for-each select="./relation/*">
                            <xsl:value-of select="./text()">
                            </xsl:value-of>
                            <xsl:text>, </xsl:text>
                        </xsl:for-each>                        
                    </xsl:if>
                </td>
            </tr>
        </table>
            
        </xsl:if>
    </xsl:template>
    <xsl:template match="event">
        <tr>
            <td><xsl:value-of select="./@name"/></td>
            <td>
                <pre class="sh_java">
                    <xsl:value-of select="./body/text()"/>
                </pre>
            </td>
        </tr>   
        <xsl:if test="count(./documentation) > 0">
            <tr>
                <td colspan="2" class="small">
                    <xsl:value-of select="./documentation/inline[1]/description"/>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="method">
        <tr>
            <td>
                <xsl:value-of select="./@name"/>
            </td>
            <td><xsl:value-of select="./label"/></td>
            <td><xsl:value-of select="./body/@language"/></td>
            <td>
                <pre class="sh_java">
                    <xsl:value-of select="string(./body)"/>
                </pre>
            </td>
            <td>
                <xsl:if test="string-length(./hiddenWhen/text()) > 0">
                    <pre class="sh_java">
                        <xsl:value-of select="./hiddenWhen/text()"/>
                    </pre>
                </xsl:if>
                <xsl:if test="./hiddenWhen/text() = ''">
                    --
                </xsl:if>
            </td>
            <td>
                <pre class="sh_javascript">
                    <xsl:value-of select="string(./javascriptToRun)"/>
                </pre>
                <!-- Javascript -->
            </td>
            <td>
                <xsl:value-of select="./icon/text()"/>
                <!-- Icon -->
            </td>
            <td>
                <xsl:value-of select="./assinature/text()"/>
                <!-- Assinature -->
            </td>
        </tr>
        <xsl:if test="count(./documentation) > 0">
            <tr>
                <td colspan="5" class="small">
                    <xsl:value-of select="./documentation/inline[1]/description"/>
                </td>
            </tr>
        </xsl:if>
    </xsl:template>
    <xsl:template match="textIndex">
        <xsl:if test="count(./@active) > 0">
            Text Index
            <table class="relations">
                <tr>
                    <th>Active</th>
                    <th>Append Childs</th>
                    <th>Depth</th>
                    <th>OnlyCardID</th>
                </tr>
                <tr>
                    <td>
                        <xsl:if test="./@active = 'true'">
                            <img src="images/ok.gif" width="16" height="16"></img>
                        </xsl:if>
                        <xsl:if test="./@active = 'false'">
                            <img src="images/no.gif" width="16" height="16"></img>
                        </xsl:if>
                    </td>
                    <td>
                        <xsl:if test="./@appendChilds = 'true' or count(./@appendChilds) = 0">
                            <img src="images/ok.gif" width="16" height="16"></img>
                        </xsl:if>
                        <xsl:if test="./@appendChilds = 'false'">
                            <img src="images/no.gif" width="16" height="16"></img>
                        </xsl:if>
                    </td>
                    <td>
                        <xsl:if test="count(./@deep) = 0">
                            1
                        </xsl:if>
                        <xsl:if test="count(./@deep)  > 0">
                            <xsl:value-of select="./@deep"></xsl:value-of>
                        </xsl:if>
                    </td>
                    <td>
                        <xsl:if test="./@onlyCardID = 'false' or count(./@onlyCardID) = 0">
                            <img src="images/no.gif" width="16" height="16"></img>
                        </xsl:if>
                        <xsl:if test="./@onlyCardID = 'true'">
                            <img src="images/ok.gif" width="16" height="16"></img>
                        </xsl:if>
                    </td>
                </tr>
            </table>
        </xsl:if>
    </xsl:template>
    <xsl:template match="objectFilter">
        <xsl:if test="count(./filter) > 0">
            Filters
            <table class="relations">
                <tr>
                    <th>XEOQL</th>
                    <th>Condition</th>
                    <th>Language</th>
                </tr>
                <xsl:for-each select="./filter">
                    <tr>
                        <td><xsl:value-of select="./xeoql/text()"></xsl:value-of></td>
                        <td><xsl:value-of select="./condition/text()"></xsl:value-of></td>
                        <td><xsl:value-of select="./condition/@language"></xsl:value-of></td>
                    </tr>
                </xsl:for-each>
            </table>
        </xsl:if>
    </xsl:template>
    <xsl:template match="transformer">
        <xsl:if test="count(./*) > 0">
            Transformer
            From : <xsl:value-of select="./from/text()"></xsl:value-of>
            Class : <xsl:value-of select="./class/text()"></xsl:value-of>
        </xsl:if>
    </xsl:template>
    <xsl:template match="objects">
        Type of instance objects allowed in this relation
        <ul>
            <xsl:for-each select="./object">
                <li><xsl:value-of select="text()"></xsl:value-of></li>    
            </xsl:for-each>
            
        </ul>
    </xsl:template>
    
    <xsl:template match="lov">
        <xsl:choose>
            <xsl:when test="count(./items/*) > 0">
                Lov <br></br>
                name: <b><xsl:value-of select="./@name"></xsl:value-of></b>
                retainValues: <xsl:value-of select="./@retainValues"></xsl:value-of>
                <table class="relations">
                    <tr>
                        <th>Value</th>
                        <th>Label</th>
                    </tr>
                    <xsl:for-each select="./items/item">
                        <tr>
                            <td><xsl:value-of select="./value/text()"></xsl:value-of></td>
                            <td><xsl:value-of select="./label/text()"></xsl:value-of></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </xsl:when>
            <xsl:when test="count(./items/*) = 0">
                <xsl:variable name="name">
                    <xsl:value-of select="./@name"></xsl:value-of>
                </xsl:variable>
                Using Lov <xsl:value-of select="./@name"/>  from
            </xsl:when>
        </xsl:choose>
        
            
        
        
        
    </xsl:template>
   <!--  ************* END OF ATTRIBUTE DEFINITION ******************** -->
    <xsl:template match="*"></xsl:template>
</xsl:stylesheet>
