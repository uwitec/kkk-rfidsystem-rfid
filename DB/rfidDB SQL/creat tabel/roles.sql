use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('roles')
            and   type = 'U')
   drop table roles
go

/*==============================================================*/
/* Table: roles                                                 */
/*==============================================================*/
create table roles (
   roleId                   bigint identity(1,1)               not null,
   roleNum               bigint               not null unique,
   role_Name            varchar(64)          null,
   role_Note            varchar(64)           null,
   isEnable             int                  null default 1,
   constraint PK_ROLES primary key (roleId)
)
go
