
INSERT INTO a_system_resource(ID,NAME,package_name,API,URL,DESCRIPTION,TYPE,PARENT_ID,UPDATE_TIME,ADMIN) VALUES ('workflow.manage', '流程管理', 'ProcessService', '', '', '', '1', null, now(), 'system');
INSERT INTO a_system_resource(ID,NAME,package_name,API,URL,DESCRIPTION,TYPE,PARENT_ID,UPDATE_TIME,ADMIN) VALUES ('workflow.add', '流程部署', 'ProcessService', 'create', '', '', '3', 'workflow.manage', now(), 'system');
INSERT INTO a_system_resource(ID,NAME,package_name,API,URL,DESCRIPTION,TYPE,PARENT_ID,UPDATE_TIME,ADMIN) VALUES ('workflow.delete', '删除部署', 'ProcessService', 'delete', '', '', '3', 'workflow.manage', now(), 'system');
INSERT INTO a_system_resource(ID,NAME,package_name,API,URL,DESCRIPTION,TYPE,PARENT_ID,UPDATE_TIME,ADMIN) VALUES ('workflow.update', '更新状态', 'ProcessService', 'update', '', '', '3', 'workflow.manage', now(), 'system');


INSERT INTO a_system_resource(ID,NAME,package_name,API,URL,DESCRIPTION,TYPE,PARENT_ID,UPDATE_TIME,ADMIN) VALUES ('workflow.use', '流程', 'ProcessService', '', '', '', '1', null, now(), 'system');


INSERT INTO a_privilege(ID,NAME,UPDATE_TIME,ADMIN) VALUES ('pri.workflow.manage', '流程管理', now(), null);
INSERT INTO a_privilege(ID,NAME,UPDATE_TIME,ADMIN) VALUES ('pri.workflow.data', '流程使用', now(), null);

INSERT INTO a_privilege_resource(RESOURCE_ID,PRIVILEGE_ID) VALUES ('workflow.manage', 'pri.workflow.manage');
INSERT INTO a_privilege_resource(RESOURCE_ID,PRIVILEGE_ID) VALUES ('workflow.add', 'pri.workflow.manage');
INSERT INTO a_privilege_resource(RESOURCE_ID,PRIVILEGE_ID) VALUES ('workflow.delete', 'pri.workflow.manage');
INSERT INTO a_privilege_resource(RESOURCE_ID,PRIVILEGE_ID) VALUES ('workflow.update', 'pri.workflow.manage');
INSERT INTO a_privilege_resource(RESOURCE_ID,PRIVILEGE_ID) VALUES ('workflow.use', 'pri.workflow.data');