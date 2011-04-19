use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('Area')
            and   type = 'U')
   drop table Area
go

/*==============================================================*/
/* Table: Area                                                  */
/*==============================================================*/
create table Area (
   areaid                   bigint identity(1,1)               not null,
   areaNum               bigint               not null unique,
   area_Address         varchar(200)         null,
   scription            varchar(500)         null,
   readerId             bigint               not null unique,
   constraint PK_AREA primary key (areaid)
)
go

alter table Area
   add constraint FK_AREA_READER foreign key (readerId)
      references reader (readerid)
go
