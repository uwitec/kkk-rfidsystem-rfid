use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('monitor')
            and   type = 'U')
   drop table monitor
go

/*==============================================================*/
/* Table: monitor                                               */
/*==============================================================*/
create table monitor (
   monitorId                   bigint identity(1,1)               not null,
   monitorNum            bigint               not null unique, 
   monitor_IP           varchar(64)          not null,
   monitor_Port         varchar(64)          not null,
   constraint PK_MONITOR primary key (monitorId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '¼à¿Ø·þÎñÆ÷',
   'user', @CurrentUser, 'table', 'monitor'
go
