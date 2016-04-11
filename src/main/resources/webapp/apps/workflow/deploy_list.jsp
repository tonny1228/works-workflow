<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">部署列表</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="selAll('.chk',this)" class="button-main button-icon-selall">全选</a>
	<a onclick="f('addDeploy.action')" class="button-main button-icon-add">部署</a>
	<a onclick="delSelected('.chk','#deleteDeployment')" class="button-main button-icon-del">删除</a>
</t:fragment>
<t:tpl menuId="__deployment">
		<s:form name="delForm" action="deleteDeployment">
		<input type="hidden" name="cascade" value="true"/>
		<table class="table-list">
			<tr class="table-list-head">
				<td width="40">选择</td>
				<td width="100">名称</td>
				<td width="80">分类</td>
				<td width="100">用户</td>
				<td>属性</td>
				<td width="120">部署时间</td>
			</tr>
		<s:iterator value="#request.list">
			<tr class="table-list-data">
				<td><s:checkbox name="deploymentId" fieldValue="%{id}" cssClass="chk"/></td>
				<td>${name }</td>
				<td>${category }</td>
				<td>${tenantId}</td>
				<td>${properties}</td>
				<td><s:date name="deploymentTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</s:iterator>
		</table>
		</s:form>
</t:tpl>

