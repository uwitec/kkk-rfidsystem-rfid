package com.rfid.server.device;

import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.server.DeviceServer;
import com.rfid.device.server.ReaderServer;
import com.rfid.device.vo.ReaderVo;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class ReaderServerTest extends TestCase{

	public void testAddReader(){
		ReaderServer server = (ReaderServer)SpringBeanUtils.getBean("readerServer");
		ReaderVo vo = new ReaderVo();
		vo.setReaderid(PoTools.getPoId(PoType.ReaderType));
		vo.setReaderIp("192.168.1.6");
		server.addReader(vo);
	}
	
	public void testGetAllReader(){
		ReaderServer server = (ReaderServer)SpringBeanUtils.getBean("readerServer");
		List list = server.getAllReader();
		assertNotNull(list);
		assertTrue(list.size()>0);
	}
}
