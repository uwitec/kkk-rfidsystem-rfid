use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('users')
            and   type = 'U')
   drop table users
go

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users (
   userId                   bigint identity(1,1) not null primary key,
   userNum               bigint               not null unique,
   login_Name           varchar(64)          not null unique,
   login_password       varchar(64)          not null
)
go
