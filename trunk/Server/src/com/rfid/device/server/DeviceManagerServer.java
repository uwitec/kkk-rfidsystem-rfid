package com.rfid.device.server;

/**
 * 管理设备，与用户关系，与区域关系
 * @author Administrator
 * 
 */
public interface DeviceManagerServer {

	/**
	 * 将设备设置监控区域，有该设备由该区域监控
	 * @param deviceId 设备编号
	 * @param areaId 区域编号
	 * @throws Exception 
	 */
	void assignDeviceToArea(Long deviceId,Long areaId) throws Exception;
	
	
	/**
	 * 更改设备状态
	 * @param deviceId 设备编号
	 * @param statusId 状态编号
	 * @throws Exception 
	 */
	void modifyDeviceState(Long deviceId,Long statusId) throws Exception;
}
