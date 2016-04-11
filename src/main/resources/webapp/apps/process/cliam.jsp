<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">查看流程信息</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="delegate()" class="button-main button-icon-move">转派</a>
	<a onclick="f('cliam.action?taskId=${param.taskId}')" class="button-main button-icon-move">接受</a>
</t:fragment>
<t:fragment>
	<script>
	function delegate(){
		UserTree.open({title:'选择转派用户',width:800,height:400,type:'u',scope:'o',callback:selectPerson});
	}
	
	function selectPerson(users){
		f('delegateTask.action?taskId=${param.taskId}&userId='+users[0].username);
	}
	</script>
</t:fragment>
<t:tpl menuId="${param.menuId }" param="support=userTree">
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
			<td><s:date name="startTime"/></td><td>${assignee }${name }</td><td>${state }</td><td><s:date name="dueDate"/></td><td><s:date name="claimTime"/></td>
			<td><s:date name="endTime"/><s:if test="localVariables!=null && !localVariables.isEmpty()">${localVariables }</s:if></td>
			</tr>
		</s:iterator>
		
		</table>
</t:tpl>
