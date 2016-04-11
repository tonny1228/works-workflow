<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">工作日设置</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="selAll('.chk',this)" class="button-main button-icon-selall">全选</a>
	<a onclick="add()" class="button-main button-icon-add">添加</a>
	<a onclick="edit()" class="button-main button-icon-edit">修改</a>
	<a onclick="delSelected('.chk','#delete')" class="button-main button-icon-del">删除</a>
	<a onclick="f('?year=${year-1}')" class="button-main button-icon-left">上一年</a>
	<a onclick="f('?year=${year+1}')" class="button-main button-icon-right">下一年</a>
</t:fragment>
<t:fragment>
<script type="text/javascript">
<!--
var dialog;
addOnLoad(function(){
	dialog = new Dialog({title:'假日',divId:'form',width:500,height:400,buttons:{'sdf':function(){}}});
});
function add(){
	dialog = new Dialog({title:'添加假日',divId:'form',width:500,height:250,buttons:{'保存':function(){
		$('#save').submit();
	}}});
	$('#save_holiday_id').val('');
	$('#save_holiday_name').val('');
	$('#save_holiday_date').val('');
	$('#save_holiday_type').val('0');

	dialog.show();
}


function edit(){
	var chbox = getSelOne('chk');
	if(!chbox){
		return;
	}
	var x = $(chbox);
	dialog = new Dialog({title:'修改假日',divId:'form',width:500,height:250,buttons:{'保存':function(){
		$('#save').submit();
	}}});
	$('#save_holiday_id').val(x.val());
	$('#save_holiday_name').val(x.parent().parent().children().eq(2).text());
	$('#save_holiday_date').val(x.parent().parent().children().eq(1).text().replace('年','-').replace('月','-').replace('日',''));
	$('#save_holiday_type').val(x.parent().parent().children().eq(3).text()=='节假日'?'0':'1');
	dialog.show();
}

function savePattern(){
	$.ajax({
	    url: 'updateWorkingDayPattern.action',
	    type: 'post',
	    data:'workingDayPattern='+$('#pattern').val(),
	    dataType: "text",
	    error: function(e,err){
	    	showTips('保存失败',5000);
	    },
	    success: function(){
	    	showTips("保存成功",5000);
	    }
	});
}
//-->
</script>
</t:fragment>
<t:tpl menuId="__worktime">
	<div class="title-sub">默认工作时间</div>
	<div class="form-search ui-corner-all"><s:textfield id="pattern" value="%{#request.setting.stringValue}" cssStyle="width:300px;" class="button-clear input-text"/> <a class="js-button" onclick="savePattern()" style="float:left;">保存</a></div>
		<s:form action="delete">
		<s:set name="types" value="{'节假日','工作日'}"/>
		<t:dataTable widths="120||50|100" headerNames="日期|名称|类型|设置时间" fields="%{utils.formatDate(date,'yyyy年MM月dd日')}|%{name}|%{#types[type]}|%{utils.formatDate(createDate,'yyyy年MM月dd日')}" bundle="#request.list" multiSelect="true" checkboxName="id" checkboxValue="%{id }"/>
		</s:form>
		<div id="form">
			<s:form action="save">
				<s:hidden name="holiday.id"/>
				<table class="table-form">
		   			<tr>
		   				<td class="form-feild">名称</td>
		   				<td><s:textfield name="holiday.name"/> </td>
		   			</tr>
		   			<tr>
		   				<td class="form-feild">日期</td>
		   				<td><t:datetime name="holiday.date"  cssStyle="width:150px;"/></td>
		   			</tr>
		   			<tr>
		   				<td class="form-feild">类型</td>
		   				<td><s:select name="holiday.type" list="#{0:'节假日',1:'工作日' }" value="0"/> </td>
		   			</tr>
				</table> 
			</s:form>
		</div>
</t:tpl>

