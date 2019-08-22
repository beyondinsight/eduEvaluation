/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2019/8/20 20:55:44                           */
/*==============================================================*/
use education2;

drop table if exists tbl_collection_major;

drop table if exists tbl_collection_material;

drop table if exists tbl_collection_school;

drop table if exists tbl_collection_tasks;

drop table if exists tbl_evaluation;

drop table if exists tbl_evaluation_sub_task;

drop table if exists tbl_evaluation_tasks;

drop table if exists tbl_expert_group;

drop table if exists tbl_expert_group_person;

drop table if exists tbl_form_basic;

drop table if exists tbl_form_capitalprogress;

drop table if exists tbl_form_performance;

drop table if exists tbl_major;

drop table if exists tbl_major_group;

drop table if exists tbl_major_group_item;

drop table if exists tbl_metrics_detail;

drop table if exists tbl_metrics_system;

drop table if exists tbl_permissions;

drop table if exists tbl_role_permission;

drop table if exists tbl_roles;

drop table if exists tbl_school;

drop table if exists tbl_support_material;

drop table if exists tbl_user_role;

drop table if exists tbl_users;

/*==============================================================*/
/* Table: tbl_collection_major                                  */
/*==============================================================*/
create table tbl_collection_major
(
   id                   bigint not null,
   collection_school_id bigint,
   major_id             bigint,
   description          varchar(200),
   create_time          datetime,
   update_time          datetime,
   process_status       char(1),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_collection_material                               */
/*==============================================================*/
create table tbl_collection_material
(
   id                   bigint not null,
   form_performance_id  bigint,
   metrics_id           int,
   material_id          int,
   doc                  varchar(300),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_collection_school                                 */
/*==============================================================*/
create table tbl_collection_school
(
   id                   bigint not null,
   collection_task_id   bigint,
   school_id            bigint,
   process_status       char(1),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_collection_tasks                                  */
/*==============================================================*/
create table tbl_collection_tasks
(
   id                   bigint not null,
   task_name            varchar(200),
   task_year            int,
   description          varchar(500),
   use_metrics_system   int,
   create_time          datetime,
   update_time          datetime,
   status               char(1),
   start_time           datetime,
   end_time             datetime,
   memo                 varchar(200),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_evaluation                                        */
/*==============================================================*/
create table tbl_evaluation
(
   id                   bigint not null,
   eval_task_id         bigint,
   school_id            bigint,
   major_id             bigint,
   expert_id            bigint,
   metrics_id           int,
   expert_name          varchar(100),
   expert_score         double,
   expert_suggestion    varchar(1000),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_evaluation_sub_task                               */
/*==============================================================*/
create table tbl_evaluation_sub_task
(
   id                   bigint not null,
   eval_task_id         bigint,
   major_group          bigint,
   expert_group         bigint,
   description          varchar(200),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_evaluation_tasks                                  */
/*==============================================================*/
create table tbl_evaluation_tasks
(
   id                   bigint not null,
   task_name            varchar(200),
   description          varchar(500),
   use_metrics_system   int,
   create_time          datetime,
   update_time          datetime,
   status               char(1),
   start_time           datetime,
   end_time             datetime,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_expert_group                                      */
/*==============================================================*/
create table tbl_expert_group
(
   id                   bigint not null,
   group_name           varchar(100),
   create_time          datetime,
   update_time          datetime,
   status               char(1),
   eval_task_id         bigint,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_expert_group_person                               */
/*==============================================================*/
create table tbl_expert_group_person
(
   id                   bigint not null,
   group_id             bigint,
   expert_id            bigint,
   description          varchar(100),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_form_basic                                        */
/*==============================================================*/
create table tbl_form_basic
(
   id                   bigint not null,
   collection_major_id  bigint,
   major_task_doc       varchar(200),
   self_eval_doc        varchar(200),
   create_time          datetime,
   update_time          datetime,
   op_user              bigint,
   process_status       char(1),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_form_capitalprogress                              */
/*==============================================================*/
create table tbl_form_capitalprogress
(
   id                   bigint not null,
   collection_major_id  bigint,
   create_time          datetime,
   update_time          datetime,
   central_disbursement_total double,
   central_paid_total   double,
   region_disbursement_amount double,
   region_paid_amount   double,
   region_paid_hardware_amount double,
   region_paid_internal_amount double,
   central_disbursement_amount double,
   central_paid_amount  double,
   central_paid_hardware_amount double,
   central_paid_internal_amount double,
   school_funding_total double,
   school_funding_hardware double,
   school_funding_internal double,
   process_status       char(1),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_form_performance                                  */
/*==============================================================*/
create table tbl_form_performance
(
   id                   bigint not null,
   collection_major_id  bigint,
   metrics_id           int,
   unit                 varchar(20),
   create_time          datetime,
   update_time          datetime,
   op_user              bigint,
   current_value        double,
   target_value         double,
   actual_value         double,
   score                double,
   self_evaluate        double,
   self_introduction    varchar(1000),
   process_status       char(1),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_major                                             */
/*==============================================================*/
create table tbl_major
(
   id                   bigint not null auto_increment,
   major_name           varchar(100),
   major_code           varchar(50),
   major_class          int,
   main_lecture         varchar(100),
   memo                 varchar(200),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_major_group                                       */
/*==============================================================*/
create table tbl_major_group
(
   id                   bigint not null,
   group_name           varchar(100),
   create_time          datetime,
   update_time          datetime,
   status               char(1),
   eval_task_id         bigint,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_major_group_item                                  */
/*==============================================================*/
create table tbl_major_group_item
(
   id                   bigint not null auto_increment,
   group_id             bigint,
   major_id             bigint,
   description          varchar(100),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_metrics_detail                                    */
/*==============================================================*/
create table tbl_metrics_detail
(
   id                   int not null,
   m_system_id          int,
   metric_name          varchar(200),
   pid                  int,
   morder               int,
   level                int,
   description          varchar(500),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_metrics_system                                    */
/*==============================================================*/
create table tbl_metrics_system
(
   id                   int not null auto_increment,
   system_name          varchar(200),
   used_year            varchar(4),
   description          varchar(500),
   create_time          datetime,
   update_time          datetime,
   status               char(1),
   level                int,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_permissions                                       */
/*==============================================================*/
create table tbl_permissions
(
   id                   bigint not null auto_increment,
   permission_name      varchar(50),
   description          varchar(100),
   rid                  bigint,
   status               char(1),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_role_permission                                   */
/*==============================================================*/
create table tbl_role_permission
(
   id                   bigint not null auto_increment,
   role_id              bigint,
   permission_id        bigint,
   memo                 varchar(200),
   status               int,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_roles                                             */
/*==============================================================*/
create table tbl_roles
(
   id                   bigint not null auto_increment,
   role_name            varchar(50),
   role_code            varchar(20),
   description          varchar(100),
   role_def             varchar(100),
   pid                  bigint,
   status               char(1),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_school                                            */
/*==============================================================*/
create table tbl_school
(
   id                   bigint not null auto_increment,
   school_name          varchar(200),
   school_code          varchar(50),
   build_year           varchar(4),
   type                 char(1),
   properties           varchar(50),
   level                int,
   create_time          datetime,
   update_time          datetime,
   memo                 varchar(200),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_support_material                                  */
/*==============================================================*/
create table tbl_support_material
(
   id                   int not null,
   metrics_id           int,
   material_name        varchar(200),
   type                 char(1),
   description          varchar(500),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_user_role                                         */
/*==============================================================*/
create table tbl_user_role
(
   id                   bigint not null auto_increment,
   user_id              bigint,
   role_id              bigint,
   memo                 varchar(200),
   status               char(1),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: tbl_users                                             */
/*==============================================================*/
create table tbl_users
(
   id                   bigint not null auto_increment,
   user_name            varchar(50),
   chinese_name         varchar(50),
   password             varchar(50),
   institution          varchar(100),
   major                varchar(100),
   status               char(1),
   create_time          datetime,
   update_time          datetime,
   last_operation       datetime,
   position             varchar(50),
   QQ                   varchar(20),
   fix_phone            varchar(20),
   mobile_phone         varchar(20),
   email                varchar(50),
   memo                 varchar(200),
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table tbl_collection_major add constraint FK_Reference_22 foreign key (collection_school_id)
      references tbl_collection_school (id) on delete restrict on update restrict;

alter table tbl_collection_major add constraint FK_Reference_23 foreign key (major_id)
      references tbl_major (id) on delete restrict on update restrict;

alter table tbl_collection_material add constraint FK_Reference_26 foreign key (form_performance_id)
      references tbl_form_performance (id) on delete restrict on update restrict;

alter table tbl_collection_material add constraint FK_Reference_27 foreign key (material_id)
      references tbl_support_material (id) on delete restrict on update restrict;

alter table tbl_collection_school add constraint FK_Reference_17 foreign key (collection_task_id)
      references tbl_collection_tasks (id) on delete restrict on update restrict;

alter table tbl_collection_school add constraint FK_Reference_18 foreign key (school_id)
      references tbl_school (id) on delete restrict on update restrict;

alter table tbl_collection_tasks add constraint FK_Reference_16 foreign key (use_metrics_system)
      references tbl_metrics_system (id) on delete restrict on update restrict;

alter table tbl_evaluation add constraint FK_Reference_29 foreign key (metrics_id)
      references tbl_metrics_detail (id) on delete restrict on update restrict;

alter table tbl_evaluation add constraint FK_Reference_31 foreign key (expert_id)
      references tbl_users (id) on delete restrict on update restrict;

alter table tbl_evaluation add constraint FK_Reference_32 foreign key (school_id)
      references tbl_school (id) on delete restrict on update restrict;

alter table tbl_evaluation add constraint FK_Reference_33 foreign key (major_id)
      references tbl_major (id) on delete restrict on update restrict;

alter table tbl_evaluation add constraint FK_Reference_35 foreign key (eval_task_id)
      references tbl_evaluation_tasks (id) on delete restrict on update restrict;

alter table tbl_evaluation_sub_task add constraint FK_Reference_15 foreign key (eval_task_id)
      references tbl_evaluation_tasks (id) on delete restrict on update restrict;

alter table tbl_evaluation_tasks add constraint FK_Reference_7 foreign key (use_metrics_system)
      references tbl_metrics_system (id) on delete restrict on update restrict;

alter table tbl_expert_group add constraint FK_Reference_11 foreign key (eval_task_id)
      references tbl_evaluation_tasks (id) on delete restrict on update restrict;

alter table tbl_expert_group_person add constraint FK_Reference_12 foreign key (group_id)
      references tbl_expert_group (id) on delete restrict on update restrict;

alter table tbl_expert_group_person add constraint FK_Reference_14 foreign key (expert_id)
      references tbl_users (id) on delete restrict on update restrict;

alter table tbl_form_basic add constraint FK_Reference_19 foreign key (collection_major_id)
      references tbl_collection_major (id) on delete restrict on update restrict;

alter table tbl_form_basic add constraint FK_Reference_20 foreign key (op_user)
      references tbl_users (id) on delete restrict on update restrict;

alter table tbl_form_capitalprogress add constraint FK_Reference_28 foreign key (collection_major_id)
      references tbl_collection_major (id) on delete restrict on update restrict;

alter table tbl_form_performance add constraint FK_Reference_24 foreign key (collection_major_id)
      references tbl_collection_major (id) on delete restrict on update restrict;

alter table tbl_form_performance add constraint FK_Reference_25 foreign key (metrics_id)
      references tbl_metrics_detail (id) on delete restrict on update restrict;

alter table tbl_major_group add constraint FK_Reference_8 foreign key (eval_task_id)
      references tbl_evaluation_tasks (id) on delete restrict on update restrict;

alter table tbl_major_group_item add constraint FK_Reference_10 foreign key (major_id)
      references tbl_major (id) on delete restrict on update restrict;

alter table tbl_major_group_item add constraint FK_Reference_9 foreign key (group_id)
      references tbl_major_group (id) on delete restrict on update restrict;

alter table tbl_metrics_detail add constraint FK_Reference_5 foreign key (m_system_id)
      references tbl_metrics_system (id) on delete restrict on update restrict;

alter table tbl_role_permission add constraint FK_Reference_3 foreign key (role_id)
      references tbl_roles (id) on delete restrict on update restrict;

alter table tbl_role_permission add constraint FK_Reference_4 foreign key (permission_id)
      references tbl_permissions (id) on delete restrict on update restrict;

alter table tbl_support_material add constraint FK_Reference_6 foreign key (metrics_id)
      references tbl_metrics_detail (id) on delete restrict on update restrict;

alter table tbl_user_role add constraint FK_Reference_1 foreign key (user_id)
      references tbl_users (id) on delete restrict on update restrict;

alter table tbl_user_role add constraint FK_Reference_2 foreign key (role_id)
      references tbl_roles (id) on delete restrict on update restrict;

