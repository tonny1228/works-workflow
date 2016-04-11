<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">待办列表</t:fragment>
<t:fragment name="_ctlbnt">
</t:fragment>
<t:tpl menuId="__process_assigned">
		<table class="table-list">
			<tr class="table-list-head">
				<td>标题</td>
				<td>开始用户</td>
				<td>开始时间</td>
				<td>当前任务</td>
			</tr>
		<s:iterator value="#request.list">
			<tr class="table-list-data">
				<td><s:a href="startTask.action?taskId=%{id}&menuId=__process_assigned">${processVariables['proc_user'].name}发起的${processDefinition.name}</s:a></td>
				<td width="80">${processVariables['proc_user'].name}</td>
				<td width="120"><s:date name="startTime" format="yyyy-MM-dd HH:mm"/></td>
				<td width="80">${name}</td>
			</tr>
		</s:iterator>
			<tr><td colspan="9"><t:page bindData="#request.list" /></td></tr>
		</table>
</t:tpl>

