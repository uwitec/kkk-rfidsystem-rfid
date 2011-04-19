use rfiddb
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('device_detail') and o.name = 'FK_DEVICE_DETAIL')
alter table device_detail
   drop constraint FK_DEVICE_DETAIL
go

if exists (select 1
            from  sysobjects
           where  id = object_id('device_detail')
            and   type = 'U')
   drop table device_detail
go

/*==============================================================*/
/* Table: device_detail                                         */
/*==============================================================*/
create table device_detail (
   id                   bigint identity(1,1)               not null,
   deviceId             bigint               null,
   device_Name          varchar(64)          null,
   price                money                null,
   manufactory          varchar(64)          null,
   purchaseDate         datetime             null,
   buyer                varchar(64)          null,
   device_Num           bigint               null,
   constraint PK_DEVICE_DETAIL primary key (id)
)
go

alter table device_detail
   add constraint FK_DEVICE_DETAIL foreign key (deviceId)
      references device (deviceId)
go
