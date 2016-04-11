<%@page import="org.llama.library.EnterpriseApplication"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">部署列表</t:fragment>
<t:fragment name="_ctlbnt">
	<t:fragment name="_ctlbnt"><a onclick="$('#save').submit()" class="button-main button-icon-save">部署</a></t:fragment>
</t:fragment>
<t:fragment>
<script type="text/javascript">
<!--
var r;
function mindResource(item){
	r = item;
}
function addNewResource(item){
	if(!r&& item){
		$("#bnt").before('<tr><td class="form-feild">资源</td><td><input type="file" name="resources" onfocus="mindResource(this.value)" onchange="addNewResource(this.value)"/></td></tr>'); 
	}
}

var v;
function mindVariable(item){
	v = item;
}
function addNewVariable(item){
	if(!v&& item){
		$("#resourceTr").before('<tr><td class="form-feild">初始化变量</td><td>变量名:<input type="text" name="variableName" onfocus="mindVariable(this.value)" onchange="addNewVariable(this.value)" style="width:220px;"/> 类型：<select name="variableType" id="deploy_variableType"><option value="string">string</option><option value="int">int</option><option value="long">long</option><option value="double">double</option><option value="date">date</option></select><br/> <br/>值：<input type="text" name="variableValue" style="width:373px;"/></td></tr>'); 
	}
}
//-->
</script>
</t:fragment>
<t:tpl menuId="__deployment">
		<s:form action="deploy" method="post" enctype="multipart/form-data">
		<%request.setAttribute("types", EnterpriseApplication.getComponent("setting").get("workflow.type").toString().split(",")); %>
		<table class="table-form">
   			<tr>
   				<td class="form-feild">流程名</td>
   				<td><s:textfield name="name"/> </td>
   			</tr>
   			<tr>
   				<td class="form-feild">分类</td>
   				<td><s:select name="category" list="#request.types" headerKey="" headerValue=""/></td>
   			</tr>
   			<tr>
   				<td class="form-feild">租户</td>
   				<td><s:textfield name="tenantId"/> </td>
   			</tr>
   			<tr>
   				<td class="form-feild">流程文件</td>
   				<td><s:file name="file"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild">实体类</td>
   				<td><s:textfield name="prop['entityClass']"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild">表单编号器</td>
   				<td><s:textfield name="prop['entityIdGenerator']"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild">默认查看表单</td>
   				<td><s:textfield name="prop['viewForm']"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild">启动表单初始化类</td>
   				<td><s:textfield name="prop['startFormHandeler']"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild">初始化变量</td>
   				<td>变量名：<s:textfield name="variableName"  onfocus="mindVariable(this.value)" onchange="addNewVariable(this.value)" cssStyle="width:220px;"/>
   				类型：<s:select name="variableType" list="{'string','int','long','double','date'}"/>
   				<br/> <br/>
   				值：<s:textfield name="variableValue" cssStyle="width:373px;"/></td>
   			</tr>
   			<tr id="resourceTr">
   				<td class="form-feild">资源</td>
   				<td><s:file name="resources" onfocus="mindResource(this.value)" onchange="addNewResource(this.value)"/></td>
   			</tr>

			<tr id="bnt"><td></td><td><s:submit value="部署"/></td></tr>
		</table> 
		
	</s:form>
</t:tpl>

