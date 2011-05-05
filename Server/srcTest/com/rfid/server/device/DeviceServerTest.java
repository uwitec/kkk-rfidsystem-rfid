package com.rfid.server.device;

import java.util.Date;
import java.util.List;

import com.rfid.device.dao.DeviceDao;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;
import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class DeviceServerTest extends MyLazyTestCase {
	
	public void testGetAllDevice(){
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		List list = server.getAllDevice();
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
	
	public void testGetDeviceByDeviceId(){
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		DeviceVo vo = server.getDeviceByDeviceId(Long.parseLong("2002011042403393164"));
		System.out.print(vo.getId());
		assertNotNull(vo);
	}
	
	public void testGetDeviceDetailByDeviceName(){
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		DeviceDetailVo vo = server.getDeviceDetailByDeviceName("电脑服务器");
		System.out.print(vo.getManufactory());
		assertNotNull(vo);
	}
	
	public void testAddDevice() throws Exception{
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		DeviceVo vo = new DeviceVo();
		vo.setMonitorEnable(0);
		vo.setDeviceId(Long.parseLong("2002011050505022072"));
		assertNotNull(server.addDevice(vo));
	}
	
	public void testAddDeviceDetail() throws Exception{
		DeviceServer server = (DeviceServer)SpringBeanUtils.getBean("deviceServer");
		DeviceDetailVo vo = new DeviceDetailVo();
		vo.setBuyer("李老师");
		vo.setDeviceName("分频器");
		vo.setDeviceNum(null);
		vo.setManufactory("长沙制造厂");
		vo.setPrice(70000.0);
		vo.setPurchaseDate(new Date());
		assertNotNull(server.addDeviceDetail(vo));
	}
}
