use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('reader')
            and   type = 'U')
   drop table reader
go

/*==============================================================*/
/* Table: reader                                                */
/*==============================================================*/
create table reader (
   readerId                   bigint identity(1,1)               not null,
   readerNum             bigint               not null unique,
   reader_IP            varchar(64)          not null,
   constraint PK_READER primary key (readerId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '读写器表
   与区域一一对应',
   'user', @CurrentUser, 'table', 'reader'
go
