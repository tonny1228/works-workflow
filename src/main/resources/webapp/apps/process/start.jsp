<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">流程管理</t:fragment>
<t:fragment name="_title">流程发起</t:fragment>
<t:fragment name="_ctlbnt">
</t:fragment>
<t:tpl menuId="__process">
	<s:form action="save">
		<s:property value="#request.html" escape="false"/>
					
		
		<s:if test="#request.form!=null">
		<table class="table-form">
		<s:iterator value="#request.form.formElements">
				<s:if test="element.dataType=='enum'">
				<tr>
				<td class="form-feild">${name }</td><td>
					<s:select name="%{id}" list="element.options.split('\\\n')"></s:select>
				</td>
				</tr>
				</s:if>
				<s:elseif test="element.dataType=='date'">
				<tr>
				<td class="form-feild">${name }</td><td>
					<s:textfield name="%{id}" id="ele_%{id}"/>
   					<script>
   					addOnLoad(function() {
   					    $("#ele_${id}").datetimepicker({dateFormat: 'yy-mm-dd',timeFormat:'hh:mm:ss',
   							buttonImage: '/admin/images/icon/calendar.gif',
   							buttonImageOnly: true,
   							showSecond: true,
   							changeMonth:true,
   							changeYear:true,
   							showOn: 'both',
   							clearText:'清除',
   							closeText:'关闭',
   						    prevText:'上月',
   						    nextText:'下月',
	   						hourText:'时',
	   						timeText:'时间',
	   						minuteText:'分',
	   						currentText:'当前',
	   						secondText:'秒',
   						    monthNames:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
   						    dayNames:['日','一','二','三','四','五','六'],
   						    dayNamesMin:['日','一','二','三','四','五','六']
   							});
   					});
   					</script>
				</td>
				</tr>
				</s:elseif>
				<s:elseif test="element!=null">
				<tr>
				<td class="form-feild">${name }</td><td>
					<input name="${id}"/>
				</td>
				</tr>
				</s:elseif>
		</s:iterator>
		<tr><td></td><td></td></tr>
		</table>
		</s:if>
		<s:iterator value="#request.form.formElements">
			<s:if test="element==null">
			<input name="${name}" type="hidden" value="${id}"/>
			</s:if>
		</s:iterator>
		<s:hidden name="processDefinitionId" value="%{#parameters.processDefinitionId[0]}"/>
		<div><s:submit name="proc_action" value="保存"/> <s:submit name="proc_action" value="下一步"/></div>
	</s:form>
</t:tpl>
