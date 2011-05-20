package com.rfid.common.constants;

public interface EnumConstant {

	//定义对象类型的编号
	int USER_POTYPE_NO = 100;
	int DEVICE_POTYPE_NO = 200;
	int STATUS_POTYPE_NO = 210;
	int AREA_POTYPE_NO = 300;
	int READER_POTYPE_NO = 610;
	int MONITOR_POTYPE_NO = 600;
	
	String ADMIN_TYPE_NO = "50001";
	String USER_TYPE_NO = "50002";
	String LEADER_TYPE_NO = "50005";
	
	public enum RoleType{
		AdminType{
			@Override
			public Long getInfo() {
				return Long.parseLong(ADMIN_TYPE_NO);
			}
		}
		,UserType{
			@Override
			public Long getInfo() {
				return Long.parseLong(USER_TYPE_NO);
			}
		}
		,LeaderType{
			@Override
			public Long getInfo() {
				return Long.parseLong(LEADER_TYPE_NO);
			}
		};
		public abstract Long getInfo();
	}
	
	public enum PoType{
		UserType{
			String getInfo(){
				return String.valueOf(USER_POTYPE_NO);
			}
		}
		,DeviceType{
			String getInfo(){
				return String.valueOf(DEVICE_POTYPE_NO);
			}
		}
		,StatusType{
			@Override
			public String getInfo() {
				return String.valueOf(STATUS_POTYPE_NO);
			}
		},ReaderType{
			@Override
			public String getInfo() {
				return String.valueOf(READER_POTYPE_NO);
			}
		},AreaType{
			@Override
			public String getInfo() {
				return String.valueOf(AREA_POTYPE_NO);
			}
		}, MonitorType{
			@Override
			public String getInfo() {
				return String.valueOf(MONITOR_POTYPE_NO);
			}
		};
		abstract String getInfo();
	}
}
