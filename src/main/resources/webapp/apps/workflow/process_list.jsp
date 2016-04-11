<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">流程列表</t:fragment>
<t:fragment name="_ctlbnt">
</t:fragment>
<t:tpl menuId="__process">
		<table class="table-list">
			<tr class="table-list-head">
				<td width="40">选择</td>
				<td>标识</td>
				<td width="30">版本</td>
				<td>分类</td>
				<td>资源名称</td>
				<td>租户</td>
				<td>状态</td>
				<td>启动用户</td>
				<td>启动组</td>
				<td width="150">操作</td>
			</tr>
		<s:iterator value="#request.list">
			<tr class="table-list-data">
				<td width="40"><s:checkbox name="id" fieldValue="%{id}" cssClass="chk"/></td>
				<td><s:a href="viewProcess.action?processDefinitionId=%{id}">${key }</s:a></td>
				<td>${version }</td>
				<td width="80">${category}</td>
				<td width="80">${resourceName}</td>
				<td width="80">${tenantId}</td>
				<td width="80">${state}</td>
				<td width="80">${candidateStarterUserIds}</td>
				<td width="80">${candidateStarterGroupIds}</td>
				<td>
					<a href="activateProcessDefinition.action?processDefinitionId=${id }">启用</a>
					<a href="suspendProcessDefinition.action?processDefinitionId=${id }">停用</a>
					<a href="viewImage.action?processDefinitionId=${id }" target="_blank">流程图</a>
					<a href="../process/start.action?processDefinitionId=${id }" target="_blank">启动</a>
				</td>
			</tr>
		</s:iterator>
		</table>
</t:tpl>

