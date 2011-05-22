package com.rfid.server.device;

import java.util.Date;
import java.util.List;

import com.rfid.device.server.DeviceServer;
import com.rfid.device.vo.DeviceDetailVo;
import com.rfid.device.vo.DeviceVo;
import com.rfid.test.common.MyLazyTestCase;
import com.rfid.test.common.SpringBeanUtils;

public class DeviceServerTest extends MyLazyTestCase {

	public void testGetAllDevice() {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		List list = server.getAllDevice();
		assertNotNull(list);
		assertTrue(list.size() > 0);
	}

	public void testGetAllDeviceMonitor() {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		List list = server.getAllDeviceMonitor();
		assertNotNull(list);
		System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testGetAllDeviceDetail() throws Exception {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		List list = server.getAllDeviceDetailMonitor();
		assertNotNull(list);
		System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testGetDeviceByDeviceId() {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		DeviceVo vo = server.getDeviceByDeviceId(Long
				.parseLong("2002011042403393164"));
		System.out.print(vo.getId());
		assertNotNull(vo);
	}

	public void testGetDeviceDetailByDeviceId() {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		DeviceDetailVo vo = server.getDeviceDetailByDeviceId(Long
				.parseLong("2002011042403393163"));
		assertNotNull(vo);
		System.out.print(vo.getId());
	}

	public void testGetDeviceDetailByDeviceName() {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		DeviceDetailVo vo = server.getDeviceDetailByDeviceName("电脑服务器");
		System.out.print(vo.getManufactory());
		assertNotNull(vo);
	}

//	public void testAddDevice() throws Exception {
//		DeviceServer server = (DeviceServer) SpringBeanUtils
//				.getBean("deviceServer");
//		DeviceVo vo = new DeviceVo();
//		vo.setMonitorEnable(0);
//		vo.setDeviceId(Long.parseLong("2002011050505022072"));
//		assertNotNull(server.addDevice(vo));
//	}

	public void testAddDeviceDetail() throws Exception {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		DeviceDetailVo vo = new DeviceDetailVo();
		vo.setBuyer("李老师");
		vo.setDeviceName("分频器");
		vo.setDeviceNum(null);
		vo.setManufactory("长沙制造厂");
		vo.setPrice(70000.0);
		vo.setPurchaseDate(new Date());
		assertNotNull(server.addDeviceDetail(vo));
	}

	public void testGetDeviceVoListByAreaId() throws Exception {
		DeviceServer server = (DeviceServer) SpringBeanUtils
				.getBean("deviceServer");
		List list = server.getDeviceListByAreaId(Long
				.valueOf("3002011050402592162"));
		assertNotNull(list);
		System.out.println(list.size());
		assertTrue(list.size() > 0);
	}

	public void testUpdateDeviceDetail() throws Exception {
		DeviceServer server = (DeviceServer) SpringBeanUtils
		.getBean("deviceServer");
		DeviceDetailVo vo = new DeviceDetailVo();
		DeviceVo d = new DeviceVo();
		d.setDeviceId(Long.parseLong("2002011042403393164"));
		vo.setDeviceVo(d);
		vo.setBuyer("周丽霞");
		boolean list = server.updateDeviceDetail(vo);
		assertTrue(list);
	}
	
//	public void testDeleteDeviceByDeviceId() throws  Exception{
//		DeviceServer server = (DeviceServer) SpringBeanUtils
//		.getBean("deviceServer");
//		boolean list = server.deleteDeviceByDeviceId(
//				Long.parseLong("2002011042403393164"));
//		assertTrue(list);
//	}
	
	public void testDeleteDeviceDetailByDeviceId() throws Exception {
		DeviceServer server = (DeviceServer) SpringBeanUtils
		.getBean("deviceServer");
//		DeviceDetailVo vo = new DeviceDetailVo();
//		DeviceVo d = new DeviceVo();
//		d.setDeviceId(Long.parseLong("2002011042403393164"));
//		vo.setDeviceVo(d);
//		vo.setBuyer("周丽霞");
		boolean list = server.deleteDeviceDetailByDeviceId(
				Long.parseLong("2002011052007092251"));
		assertTrue(list);
	}
}
