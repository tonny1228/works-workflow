<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">流程列表</t:fragment>
<t:fragment name="_ctlbnt">
</t:fragment>
<t:tpl menuId="__process">
	<div style="position: relative;border: 0px solid #ff0000;">
	<img style="position:relative;border: 0px solid #0000ff;" src="../workflow/viewImage.action?processDefinitionId=${param.processDefinitionId }&activityId=${activity.id}"/>
	</div>
</t:tpl>
