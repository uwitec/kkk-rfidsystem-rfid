package com.rfid.device.server;

import java.util.List;

import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;

public interface DeviceServer {

	
	/**
	 * 获取所有的设备信息
	 * @return
	 */
	List<DeviceVo> getAllDevice();
	
	/**
	 * 精确查找，通过设备编号查找设备
	 * @param deviceId 设备编号
	 * @return
	 */
	DeviceVo getDeviceByDeviceId(Long deviceId);
	
	
	/**
	 * 精确查找，通过设备名称查找设备
	 * @param deviceName 设备名称
	 * @return
	 */
	DeviceDetailVo getDeviceDetailByDeviceName(String deviceName);
	
	/**
	 * 增加设备
	 * @param vo 封装增添设备的信息
	 * @return 是否增添成功
	 */
	boolean addDevice(DeviceVo vo);
	
	/**
	 * 删除设备
	 * @param vo 需要删除的设备的查找信息
	 * 根据此输入的对象，查找需要删除的设备
	 * @return
	 */
	boolean deleteDevice(DeviceVo vo);
	
	/**
	 * 更新设备
	 * @param vo 需要跟新的设备的查找信息
	 * 根据此输入的对象，查找需要更新的设备
	 * @return
	 */
	boolean updateDevice(DeviceVo vo);
}
