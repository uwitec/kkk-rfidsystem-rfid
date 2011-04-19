use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('area_monitor')
            and   type = 'U')
   drop table area_monitor
go

/*==============================================================*/
/* Table: area_monitor                                          */
/*==============================================================*/
create table area_monitor (
   id                   bigint identity(1,1)               not null,
   areaId               bigint               not null,
   monitorId            bigint               not null,
   constraint PK_AREA_MONITOR primary key (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '一个监控服务器 ，可以监控 多个区域',
   'user', @CurrentUser, 'table', 'area_monitor'
go

alter table area_monitor
   add constraint FK_AREA_MONITOR foreign key (areaId)
      references Area (areaId)
go

alter table area_monitor
   add constraint FK_MONITOR_AREA foreign key (monitorId)
      references monitor (monitorId)
go
