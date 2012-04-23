Ext.namespace('com.zz91.sms.smslog');

var SMSLOG=new function(){
	this.SMSLOG_GRID="smsloggrid";
}
com.zz91.sms.smslog.Field=[
	{name:"id",mapping:"id"},
	{name:"content",mapping:"content"},
	{name:"receiver",mapping:"receiver"},
	{name:"sendStatus",mapping:"sendStatus"},
	{name:"gmtSend",mapping:"gmtSend"},
	{name:"gatewayCode",mapping:"gatewayCode"},
	{name:"priority",mapping:"priority"}
];

com.zz91.sms.smslog.Grid = Ext.extend(Ext.grid.GridPanel,{
	constructor:function(config){
		config = config||{};
		Ext.apply(this,config);
		
		var _store = new Ext.data.JsonStore({
				root:"records",
				totalProperty:'totals',
				remoteSort:true,
				fields:com.zz91.sms.smslog.Field,
				url:Context.ROOT +  "/smslog/querySms.htm",
				autoLoad:true
		});
		
		var grid = this;
		var _sm=new Ext.grid.CheckboxSelectionModel({});
		
		var _cm=new Ext.grid.ColumnModel( 
				[ _sm,{
			header : "短信内容",
			sortable : false,
			dataIndex : "content"
		},{
			header :"接收者电话",
			sortable : false,
			dataIndex:"receiver"
		},{
			header:"发送状态",
			sortable:false,
			dataIndex:"sendStatus"
		},{
			header : "发送时间",
			sortable : false,
			dataIndex : "gmtSend",
			renderer : function(value, metadata, record, rowIndex,colIndex, store) {
				if(value!=null){
					return Ext.util.Format.date(new Date(value.time), 'Y-m-d h:m:s');
				}
				else{
					return "";
				}
			}
		},{
			header : "网关",
			sortable : false,
			dataIndex : "gatewayCode"
		},{
			header:"优先级",
			sortable:false,
			dataIndex:"priority"
		}
		]);
		
		var c={
			loadMask:MESSAGE.loadmask,
			iconCls:"icon-grid",
			store:_store,
			sm:_sm,
			cm:_cm,
			tbar:this.mytoolbar,
			bbar: com.zz91.utils.pageNav(_store)
		};
		
		com.zz91.sms.smslog.Grid.superclass.constructor.call(this,c);
	},
	mytoolbar:[
	{
		text : '重发',
		iconCls : 'edit16',
		handler : function(btn){		
			var row = Ext.getCmp(SMSLOG.SMSLOG_GRID).getSelectionModel().getSelections();
			if (row.length > 0) {
				Ext.MessageBox.confirm(Context.MSG_TITLE, '是否要发送选中的信息?', function(_btn){
					if (_btn != "yes")
						return;
					for (var i = 0, len = row.length; i < len; i++) {
						Ext.Ajax.request({
							url:Context.ROOT +  "/smslog/resendSms.htm",
							params:{"id":row[i].get("id")},
							success:function(response,opt){
								var obj = Ext.decode(response.responseText);
								if(obj.success){
									com.zz91.utils.Msg("","发送成功");
									Ext.getCmp(SMSLOG.SMSLOG_GRID).getStore().reload();
								}else{
									Ext.MessageBox.show({
										title:MESSAGE.title,
										msg : MESSAGE.saveFailure,
										buttons:Ext.MessageBox.OK,
										icon:Ext.MessageBox.ERROR
									});
								}
							},
							failure:function(response,opt){
								Ext.MessageBox.show({
									title:MESSAGE.title,
									msg : MESSAGE.submitFailure,
									buttons:Ext.MessageBox.OK,
									icon:Ext.MessageBox.ERROR
								});
							}
						});
					}
				});
			}
		}
	},{
		text : '删除',
		iconCls : 'delete16',
		handler : function(btn){		
		var row = Ext.getCmp(SMSLOG.SMSLOG_GRID).getSelectionModel().getSelections();		
			if (row.length > 0) {
				Ext.MessageBox.confirm(Context.MSG_TITLE, '是否要删除选中的 ' + row.length + '条记录?', function(_btn){
					if (_btn != "yes")
						return;
					for (var i = 0, len = row.length; i < len; i++) {
						Ext.Ajax.request({
							url:Context.ROOT +  "/smslog/deleteSms.htm",
							params:{"id":row[i].get("id")},
							success:function(response,opt){
								var obj = Ext.decode(response.responseText);
								if(obj.success){
									com.zz91.utils.Msg("","删除记录成功");
									Ext.getCmp(SMSLOG.SMSLOG_GRID).getStore().reload();
								}else{
									Ext.MessageBox.show({
										title:MESSAGE.title,
										msg : MESSAGE.saveFailure,
										buttons:Ext.MessageBox.OK,
										icon:Ext.MessageBox.ERROR
									});
								}
							},
							failure:function(response,opt){
								Ext.MessageBox.show({
									title:MESSAGE.title,
									msg : MESSAGE.submitFailure,
									buttons:Ext.MessageBox.OK,
									icon:Ext.MessageBox.ERROR
								});
							}
						});
					}
				});
			}
		}
	}]
});



