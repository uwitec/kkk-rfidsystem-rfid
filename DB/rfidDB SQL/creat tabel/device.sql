use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('device')
            and   type = 'U')
   drop table device
go

/*==============================================================*/
/* Table: device                                                */
/*==============================================================*/
create table device (
   deviceId                   bigint identity(1,1)               not null,
   deviceNum             bigint               null unique,
   monitor_Enable       int                  null default 1,
   constraint PK_DEVICE primary key (deviceId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '�Ƿ���м�أ�Ĭ��Ϊ1---���У�0---�����',
   'user', @CurrentUser, 'table', 'device', 'column', 'monitor_Enable'
go
