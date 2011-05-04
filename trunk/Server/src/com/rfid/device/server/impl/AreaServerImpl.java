package com.rfid.device.server.impl;

import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.AreaDao;
import com.rfid.device.dao.ReaderDao;
import com.rfid.device.po.Area;
import com.rfid.device.po.Reader;
import com.rfid.device.server.AreaServer;
import com.rfid.device.vo.AreaVo;
import com.rfid.device.vo.VoToPoTools;

public class AreaServerImpl implements AreaServer {

	AreaDao areaDao;
	ReaderDao readerDao;
	
	public ReaderDao getReaderDao() {
		return readerDao;
	}

	public void setReaderDao(ReaderDao readerDao) {
		this.readerDao = readerDao;
	}

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.areaDao = areaDao;
	}

	public void addArea(AreaVo vo) throws Exception {
		if(vo==null)
			return;
		boolean is = areaDao.hasAreaByReaderId(vo.getReader().getReaderid());
		if(is)
			throw new Exception("该读写器已经配有区域");
		Area a = VoToPoTools.toArea(vo);
		a.setAreaId(PoTools.getPoId(PoType.AreaType));
		areaDao.save(a);
	}

	public void modifyAreaReader(Long areaId, Long readerId) throws Exception {
		List rList = readerDao.findByReaderid(readerId);
		if(rList==null || rList.size()<=0)
			throw new Exception("不存在该读写器");
		List aList = areaDao.findByAreaId(areaId);
		if(aList == null || aList.size() <= 0)
			throw new Exception("不存在该区域");
		Area a = (Area)aList.get(0);
		Reader r = (Reader)rList.get(0);
		a.setReader(r);
		areaDao.update(a);
		
	}

}
