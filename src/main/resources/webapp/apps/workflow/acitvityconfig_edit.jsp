<%@page import="org.llama.library.EnterpriseApplication"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">任务设置</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="$('#actor').submit()" class="button-main button-icon-save">保存</a>
</t:fragment>
<t:fragment>
<script type="text/javascript">
<!--
var validate_rules={
	"config.dueTime":{digits:true}
}

function check(v){
	$('#actor_config_dueType').rules('remove');
	if(v && v>0){
		$('#actor_config_dueType').rules('add',{required:true});
	}else{
		$('#actor_config_dueType').rules('add',{required:false});
	}
}
//-->
</script>
<style>
<!--
.table-form input[type='text'] {
	width: 120px;
}
-->
</style>
</t:fragment>
<t:tpl menuId="__deployment" param="support=form">
	<s:form action="actor" namespace="/workflow">
		<s:if test="#request.config">
		<s:hidden name="config.id" value="%{#request.config.id }"/>
		<s:hidden name="config.processDefinitionId" value="%{#request.config.processDefinitionId}"/>
		<s:hidden name="config.activitiId" value="%{#request.config.activitiId}"/>
		</s:if>
		<s:else>
		<s:hidden name="config.processDefinitionId" value="%{#parameters.processDefinitionId[0]}"/>
		<s:hidden name="config.activitiId" value="%{#parameters.activitiId}"/>
		</s:else>
		<table class="table-form">
			<tr>
   				<td class="form-feild">分派模式</td>
   				<td><s:select name="config.balancerPolicy" list="#{'assign':'用户指定','free':'自由模式','robin':'轮循模式'}" value="%{#request.config.balancerPolicy }"/></td>
   			</tr>
			<tr>
   				<td class="form-feild">超时期限</td>
   				<td><s:textfield name="config.dueTime" value="%{#request.config.dueTime }" cssStyle="width:40px;" onchange="check(this.value)"/>
					<s:select name="config.dueType" list="#{'':'无',1:'天',2:'工作日',3:'工时'}" value="%{#request.config.dueType }"/></td>
   			</tr>
   			<s:set var="index" value="-1"/>
   			<s:if test="#request.config.actors">
   			<s:iterator value="#request.config.actors" status="sta">
			<tr>
   				<td class="form-feild">执行人</td>
   				<td><s:hidden name="actors[%{#sta.index}].id" value="%{id}"/> 
   				部门：<s:textfield name="actors[%{#sta.index}].departmentId" value="%{departmentId}"/> 岗位：<s:textfield name="actors[%{#sta.index}].positionId" value="%{positionId}"/><br/>  
   				角色：<s:textfield name="actors[%{#sta.index}].roleId" value="%{roleId}"/> 用户：<s:textfield name="actors[%{#sta.index}].username" value="%{username}"/> </td>
   			</tr>
   			<s:set var="index" value="%{#sta.index}"/>
   			</s:iterator>
   			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[%{#index+1}].departmentId"/> 岗位：<s:textfield name="actors[%{#index+1}].positionId"/><br/>  角色：<s:textfield name="actors[%{#index+1}].roleId"/> 用户：<s:textfield name="actors[%{#index+1}].username"/> </td>
   			</tr>
   			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[%{#index+2}].departmentId"/> 岗位：<s:textfield name="actors[%{#index+2}].positionId"/><br/>  角色：<s:textfield name="actors[%{#index+2}].roleId"/> 用户：<s:textfield name="actors[%{#index+2}].username"/> </td>
   			</tr>
   			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[%{#index+3}].departmentId"/> 岗位：<s:textfield name="actors[%{#index+3}].positionId"/><br/>  角色：<s:textfield name="actors[%{#index+3}].roleId"/> 用户：<s:textfield name="actors[%{#index+3}].username"/> </td>
   			</tr>
   			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[%{#index+4}].departmentId"/> 岗位：<s:textfield name="actors[%{#index+4}].positionId"/><br/>  角色：<s:textfield name="actors[%{#index+4}].roleId"/> 用户：<s:textfield name="actors[%{#index+4}].username"/> </td>
   			</tr>
   			</s:if>
   			<s:else>
			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[0].departmentId"/> 岗位：<s:textfield name="actors[0].positionId"/><br/>  角色：<s:textfield name="actors[0].roleId"/> 用户：<s:textfield name="actors[0].username"/> </td>
   			</tr>
			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[1].departmentId"/> 岗位：<s:textfield name="actors[1].positionId"/><br/>  角色：<s:textfield name="actors[1].roleId"/> 用户：<s:textfield name="actors[1].username"/> </td>
   			</tr>
			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[2].departmentId"/> 岗位：<s:textfield name="actors[2].positionId"/><br/>  角色：<s:textfield name="actors[2].roleId"/> 用户：<s:textfield name="actors[2].username"/> </td>
   			</tr>
			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[3].departmentId"/> 岗位：<s:textfield name="actors[3].positionId"/><br/>  角色：<s:textfield name="actors[3].roleId"/> 用户：<s:textfield name="actors[3].username"/> </td>
   			</tr>
			<tr>
   				<td class="form-feild">执行人</td>
   				<td>部门：<s:textfield name="actors[4].departmentId"/> 岗位：<s:textfield name="actors[4].positionId"/><br/>  角色：<s:textfield name="actors[4].roleId"/> 用户：<s:textfield name="actors[4].username"/> </td>
   			</tr>
   			</s:else>
		</table>
	</s:form>
</t:tpl>

