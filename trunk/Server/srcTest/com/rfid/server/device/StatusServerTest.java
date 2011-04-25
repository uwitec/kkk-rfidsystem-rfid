package com.rfid.server.device;

import java.util.List;

import junit.framework.TestCase;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.StatusDao;
import com.rfid.device.po.Status;
import com.rfid.device.server.StatusServer;
import com.rfid.device.vo.StatusVo;
import com.rfid.test.common.SpringBeanUtils;

public class StatusServerTest extends TestCase{
	
	public void testAddStatus(){
		StatusServer server = (StatusServer)SpringBeanUtils.getBean("statusServer");
		StatusVo vo = new StatusVo();
		vo.setLevel(6);
		vo.setScription("已借出");
		server.addStatus(vo);
	}
	
	public void testGetAllStatus(){
		StatusServer server = (StatusServer)SpringBeanUtils.getBean("statusServer");
		List list = server.getAllStatus();
		assertNotNull(list);
		assertTrue(list.size()>0);
	}

}
