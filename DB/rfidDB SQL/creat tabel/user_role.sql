use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('user_role')
            and   type = 'U')
   drop table user_role
go

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role (
   id                   bigint identity(1,1)               not null,
   userId               bigint               not null,
   roleId               bigint               not null,
   constraint PK_USER_ROLE primary key (id)
)
go

alter table user_role
   add constraint FK_ROLE_USER foreign key (roleId)
      references roles (roleId)
go

alter table user_role
   add constraint FK_USERS_ROLE foreign key (userId)
      references users (userid)
go
