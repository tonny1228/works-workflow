<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">我发起的流程</t:fragment>
<t:fragment name="_ctlbnt">
	<s:if test="#request.processDefinitionId!=null">
		<a onclick="f('start.action?processDefinitionId=${processDefinitionId}')" class="button-main button-icon-add">发起</a>
	</s:if>
</t:fragment>
<t:tpl menuId="__process_started">
		<table class="table-list">
			<tr class="table-list-head">
				<td>标题</td>
				<td>状态</td>
				<td>开始用户</td>
				<td>开始时间</td>
				<td>结束时间</td>
				<td>当前任务</td>
				<td width="100">操作</td>
			</tr>
		<s:iterator value="#request.list">
			<tr class="table-list-data">
				<td><s:a href="viewInstanceHistory.action?processInstanceId=%{id}&menuId=__process_started">${variables['proc_user'].name}发起的${processDefinitionName}</s:a></td>
				<td width="80">${state}</td>
				<td width="80">${variables['proc_user'].name}</td>
				<td width="120"><s:date name="startTime" format="yyyy-MM-dd HH:mm"/></td>
				<td width="120"><s:date name="endTime" format="yyyy-MM-dd HH:mm"/></td>
				<td width="80">${currentActivityName}</td>
				<td>
					<s:if test="state!='结束'">
					<a href="deleteProcessInstance.action?processInstanceId=${id}&action=started">终结</a>
					</s:if>
					<s:if test="state=='进行中'">
					<a href="suspendProcessInstance.action?processInstanceId=${id}&action=started">休眠</a>
					</s:if>
					<s:if test="state=='休眠'">
					<a href="activateProcessInstance.action?processInstanceId=${id}&action=started">恢复</a>
					</s:if>
					<a href="viewImage.action?processInstanceId=${id}&processDefinitionId=${processDefinitionId}">流程图</a>
				</td>
			</tr>
		</s:iterator>
			<tr><td colspan="9"><t:page bindData="#request.list" /></td></tr>
		</table>
</t:tpl>

