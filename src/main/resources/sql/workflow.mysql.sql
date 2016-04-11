CREATE TABLE if not exists wf_cfg_activity (
  id varchar(50) NOT NULL,
  process_definition_id varchar(50) DEFAULT NULL,
  activiti_id varchar(50) DEFAULT NULL,
  due_time int(50) DEFAULT NULL,
  due_type int(50) DEFAULT NULL,
  balancer_policy varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE if not exists  wf_cfg_actor (
  id varchar(50) NOT NULL,
  config_id varchar(50) DEFAULT NULL,
  department_id varchar(50) DEFAULT NULL,
  department_name varchar(50) DEFAULT NULL,
  position_id varchar(50) DEFAULT NULL,
  position_name varchar(50) DEFAULT NULL,
  role_id varchar(50) DEFAULT NULL,
  role_name varchar(50) DEFAULT NULL,
  user_loginname varchar(500) DEFAULT NULL,
  user_name varchar(500) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  if not exists wf_cfg_holiday (
  id varchar(50) NOT NULL,
  holiday_date date NOT NULL,
  name varchar(50) DEFAULT NULL,
  create_date datetime DEFAULT NULL,
  type int(11) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
