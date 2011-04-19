use rfiddb
go

if exists (select 1
            from  sysobjects
           where  id = object_id('device_status')
            and   type = 'U')
   drop table device_status
go

/*==============================================================*/
/* Table: device_status                                         */
/*==============================================================*/
create table device_status (
   id                   bigint identity(1,1)               not null,
   current_AreaId       bigint               not null,
   current_StatusId     bigint               not null,
   monitor_Time         datetime             not null,
   previousId           bigint               not null,
   isEnable             int                  not null default 1,
   deviceId             bigint               not null,
   constraint PK_DEVICE_STATUS primary key (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备状态表，记录该设备应该在那个位置。由设备进入管理开始设定。',
   'user', @CurrentUser, 'table', 'device_status'
go


--device_stauts
alter table device_status
   add constraint FK_DEVICE_STATUS foreign key (deviceId)
      references device (deviceId)
go

alter table device_status
   add constraint FK_STATUS_DEVICE foreign key (current_StatusId)
      references [status] (statusId)
go

alter table device_status
   add constraint FK_DEVICE_STATUS_AREA foreign key (current_AreaId)
      references Area (areaId)
go