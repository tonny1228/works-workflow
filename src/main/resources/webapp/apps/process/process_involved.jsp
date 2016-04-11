<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">我参与的流程</t:fragment>
<t:fragment name="_ctlbnt">
</t:fragment>
<t:tpl menuId="__process_involved">
		<table class="table-list">
			<tr class="table-list-head">
				<td>标题</td>
				<td>状态</td>
				<td>开始用户</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>最后任务时间</td>
				<td>当前任务</td>
				<td>结束任务</td>
			</tr>
		<s:iterator value="#request.list">
			<tr class="table-list-data">
				<td><s:a href="viewInstanceHistory.action?processInstanceId=%{id}&menuId=__process_involved">${variables['proc_user'].name}发起的${processDefinitionName}</s:a></td>
				<td width="80">${state}</td>
				<td width="80">${startUserId}</td>
				<td width="120"><s:date name="startTime" format="yyyy-MM-dd HH:mm"/></td>
				<td width="120"><s:date name="endTime" format="yyyy-MM-dd HH:mm"/></td>
				<td width="80"><s:date name="currentActivityTime" format="yyyy-MM-dd HH:mm"/></td>
				<td width="80">${currentActivityName}</td>
				<td width="80">${endActivityName}</td>
			</tr>
		</s:iterator>
			<tr><td colspan="9"><t:page bindData="#request.list" /></td></tr>
		</table>
</t:tpl>

