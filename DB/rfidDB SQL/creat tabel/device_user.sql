use rfiddb
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('device_user') and o.name = 'FK_DEVICE_USER')
alter table device_user
   drop constraint FK_DEVICE_USER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('device_user') and o.name = 'FK_USER_DEVICE')
alter table device_user
   drop constraint FK_USER_DEVICE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('device_user')
            and   type = 'U')
   drop table device_user
go

/*==============================================================*/
/* Table: device_user                                           */
/*==============================================================*/
create table device_user (
   id                   bigint identity(1,1)               not null,
   userId               bigint               not null,
   deviceId             bigint               not null,
   constraint PK_DEVICE_USER primary key (id)
)
go

alter table device_user
   add constraint FK_DEVICE_USER foreign key (deviceId)
      references device (deviceId)
go

alter table device_user
   add constraint FK_USER_DEVICE foreign key (userId)
      references users (userid)
go
