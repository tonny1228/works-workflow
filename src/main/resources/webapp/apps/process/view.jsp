<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">查看流程信息</t:fragment>
<t:fragment name="_ctlbnt">
</t:fragment>
<t:tpl menuId="${param.menuId }">
		<div class="title-sub">流程数据</div>
		${process.state }
		<s:property value="#request.form" escape="false"/>
		<table class="table-view">
		<s:iterator value="#request.form.formElements">
			<s:if test="element!=null">
			<tr>
			<td class="form-feild">${name }</td>
			<td>
				<s:if test="element.dataType=='date'">
				<s:date name="#request.process.variables[id]" />
				</s:if>
				<s:else>
				${process.variables[id] }
				</s:else>
			</td>
			</tr>
			</s:if>
		</s:iterator>
		</table>
		<s:hidden name="processDefinitionId" value="%{#parameters.processDefinitionId[0]}"/>
		<div class="title-sub">流程步骤</div>
		<table class="table-view">
		<s:iterator value="#request.tasks">
			<tr>
			<td><s:date name="startTime"/></td><td>${assignee }${name }</td>
			<td>${state }<s:if test="endTime!=null">(<s:date name="endTime"/>)</s:if></td>
			<td>创建时间：<s:date name="startTime"/></td>
			<td><s:if test="dueDate!=null">超时时间：<s:date name="dueDate"/></s:if></td>
			<td><s:if test="claimTime!=null">接收时间：<s:date name="claimTime"/></s:if></td>
			<td><s:if test="localVariables!=null && !localVariables.isEmpty()">${localVariables }</s:if></td>
			</tr>
		</s:iterator>
		
		</table>
</t:tpl>
