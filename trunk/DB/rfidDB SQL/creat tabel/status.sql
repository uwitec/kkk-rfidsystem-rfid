use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('status')
            and   type = 'U')
   drop table status
go

/*==============================================================*/
/* Table: status                                                */
/*==============================================================*/
create table status (
   statusId                   bigint identity(1,1)               not null,
   statusNum             bigint               not null unique,
   scription            varchar(250)           null,
   statusLevel                int                  not null,
   constraint PK_STATUS primary key (statusId)
)
go
