<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">流程详细信息</t:fragment>
<t:fragment>
<script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.form.js"></script>
<script type="text/javascript">
<!--
function saveActor(){
	$('#actor').ajaxForm( function() {showTips("<s:text name='tip_save_success'/>",5000);});
	$('#actor').ajaxSubmit();
}
//-->
</script>
</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="delSelected('.chk','#delete')" class="button-main button-icon-del">删除流程</a>
</t:fragment>
<t:tpl menuId="__process" param="support=form">
	<div class="title-sub">流程图</div>
	<div style="text-align: center;border: 2px solid #ccc;padding: 10px;margin: 5px 0px 5px 0px;"><img src="viewImage.action?processDefinitionId=${param.processDefinitionId }" style="width: 90%"/></div>
	
	<div class="title-sub">流程设置</div>
	<table class="table-list">
		<tr class="table-list-head">
			<td width="100">节点名称</td>
			<td width="100">均衡策略</td>
			<td width="150">时限</td>
			<td>执行人</td>
			<td width="80">设置</td>
		</tr>
		<s:iterator value="processDefinition.activities" status="sta" var="a">
		<s:if test="%{type=='userTask'}">
		<tr>
			<td>${name }</td>
			<td>${balancerPolicy}</td>
			<td>${dueTime }${dueType }</td>
			<td><s:iterator value="%{#request.configs[id].actors}">
				${departmentName } ${positionName } ${roleName } ${name }
				</s:iterator>
			</td>
			<td><a href="actorSetting.action?processDefinitionId=${processDefinitionId }&activitiId=${id }">设置</a></td>
		</tr>
		</s:if>
		</s:iterator>
	</table>
</t:tpl>
