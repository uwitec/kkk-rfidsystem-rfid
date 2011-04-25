package com.rfid.common.constants;

public interface EnumConstant {

	//定义对象类型的编号
	int USER_POTYPE_NO = 100;
	int DEVICE_POTYPE_NO = 200;
	int STATUS_POTYPE_NO = 210;
	
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
		};
		abstract String getInfo();
	}
}
