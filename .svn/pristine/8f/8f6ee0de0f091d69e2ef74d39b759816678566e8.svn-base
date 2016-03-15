/*
 * Auther:李世龙
 * Date:2015-8-9
 * Desc:投诉受理信息表
 * */
drop table if exists complain;

/*==============================================================*/
/* Table: complain                                              */
/*==============================================================*/
create table complain
(
   comp_id              varchar(32) not null,
   comp_company         varchar(100),
   comp_name            varchar(20),
   comp_mobile          varchar(20),
   is_NM                bool,
   comp_time            datetime,
   comp_tile            varchar(200) not null,
   to_comp_name         varchar(20),
   to_comp_dept         varchar(100),
   comp_content         text,
   state                varchar(1),
   primary key (comp_id)
);
drop table if exists complain_rply;

/*==============================================================*/
/* Table: complain_rply                                         */
/*==============================================================*/
create table complain_rply
(
   reply_id             varchar(32) not null,
   comp_id              varchar(32) not null,
   replyer              varchar(20),
   reply_dept           varchar(100),
   reply_time           datetime,
   reply_content        varchar(300),
   primary key (reply_id)
);

alter table complain_rply add constraint FK_comp_reply foreign key (comp_id)
      references complain (comp_id) on delete restrict on update restrict;
