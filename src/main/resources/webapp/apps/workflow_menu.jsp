<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/tpl" prefix="t"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/apps-core" prefix="core"%>
<t:menu parentId="workflow.manage">
	mainMenu['__workflow']={id:"__workflow",name:"流程管理"};
	subMenu['__workflow']=[{id:"__deployment",name:"部署管理",link:"/workflow/deployList.action"}
		,{id:"__process",name:"流程管理",link:"/workflow/processList.action"}
		,{id:"__worktime",name:"工作时间设置",link:"/workflow/workingday/list.action"}
		,{id:"__process_all",name:"所有流程",link:"/process/allList.action"}
		];
</t:menu>
<t:menu parentId="workflow.use">
	mainMenu['__workflowdata']={id:"__workflowdata",name:"流程数据"};
	subMenu['__workflowdata']=[{id:"__process_started",name:"我发起的",link:"/process/startedList.action"}
		,{id:"__process_assigned",name:"我的待办",link:"/process/assignedList.action"}
		,{id:"__process_queue",name:"待接收",link:"/process/queueList.action"}
		,{id:"__process_involved",name:"我参与的",link:"/process/involvedList.action"}
		];
</t:menu>