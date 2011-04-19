use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('user_detail')
            and   type = 'U')
   drop table user_detail
go

/*==============================================================*/
/* Table: user_detail                                           */
/*==============================================================*/
create table user_detail (
   id                   bigint identity(1,1)               not null,
   userId               bigint               not null unique,
   userName             varchar(64)          null,
   birthday             datetime             null,
   userAddress          varchar(250)           null,
   registerTime         datetime             null,
   Connection           varchar(64)             null,
   constraint PK_USER_DETAIL primary key (id)
)
go

alter table user_detail
   add constraint FK_USER_DETAIL foreign key (Id)
      references users (userid)
go
