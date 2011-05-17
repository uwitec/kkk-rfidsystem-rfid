package com.rfid.server.device;

import com.rfid.device.dao.NodesDao;
import com.rfid.device.dao.StatusDao;
import com.rfid.device.po.Nodes;
import com.rfid.device.server.NodesServer;
import com.rfid.test.common.SpringBeanUtils;

import junit.framework.TestCase;

public class NodesServerTest extends TestCase {

	public void testAddNode(){
		NodesServer server = (NodesServer)SpringBeanUtils.getBean("nodesServer");
		boolean b = server.addNodes(Long.valueOf("2002011042403393164"),6);
		assertTrue(b);
	}
}
