package com.rfid.device.server.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.StatusDao;
import com.rfid.device.po.Status;
import com.rfid.device.server.StatusServer;
import com.rfid.device.vo.StatusVo;
import com.rfid.device.vo.VoToPoTools;

public class StatusServerImpl implements StatusServer{

	StatusDao statusDao;

	public StatusDao getStatusDao() {
		return statusDao;
	}

	public void setStatusDao(StatusDao statusDao) {
		this.statusDao = statusDao;
	}

	public boolean addStatus(StatusVo vo) {
		try{
			Status s = VoToPoTools.toStatus(vo);
			s.setStatusId(PoTools.getPoId(PoType.StatusType));
			statusDao.save(s);
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StatusVo> getAllStatus() {
		// TODO Auto-generated method stub
		List list = statusDao.findAll();
		if(list==null || list.size()==0)
			return null;
		Iterator it = list.iterator();
		List<StatusVo> vos = new ArrayList<StatusVo>();
		while(it.hasNext()){
			Status s = (Status)it.next();
			StatusVo vo = s.toStatusVo();
			vos.add(vo);
		}
		return vos;
	}

}
