<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">选择执行人</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="$('#delegateTask').submit()" class="button-main button-icon-add">分派</a>
</t:fragment>
<t:tpl menuId="__process_assigned">
	<s:form action="delegateTask">
	<s:hidden name="taskId" value="%{#request.task.id}"/>
	<s:hidden name="processDefinitionKey" value="%{#request.processDefinitionKey}"/>
	<s:hidden name="processDefinitionId" value="%{#request.processDefinitionId}"/>
	<s:hidden name="return" value="%{#parameters.return[0]}"/>
	<s:iterator value="#request.users" >
		<div class="title-sub">${key.roleName }</div>
		<table class="table-list">
			<tr class="table-list-head">
				<td width="40">选择</td>
				<td width="100">用户登录名</td>
				<td width="100">姓名</td>
				<td width="100">部门</td>
				<td width="100">岗位</td>
				<td>角色</td>
			</tr>
		<s:iterator value="value">
			<tr class="table-list-data">
				<td><input type="radio" name="userId" value="${username}" class="chk"/></td>
				<td>${username }</td>
				<td>${name }</td>
				<td>${position.department.name }</td>
				<td>${position.name }</td>
				<td><s:iterator value="roles">${name } </s:iterator></td>
			</tr>
		</s:iterator>
		</table>
	</s:iterator>
	</s:form>
</t:tpl>