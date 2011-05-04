package com.rfid.device.server.impl;

import java.util.ArrayList;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.dao.ReaderDao;
import com.rfid.device.po.Reader;
import com.rfid.device.server.ReaderServer;
import com.rfid.device.vo.ReaderVo;
import com.rfid.device.vo.VoToPoTools;

public class ReaderServerImpl implements ReaderServer {

	ReaderDao readerDao;
	
	public ReaderDao getReaderDao() {
		return readerDao;
	}

	public void setReaderDao(ReaderDao readerDao) {
		this.readerDao = readerDao;
	}

	public void addReader(ReaderVo vo) {
		Reader r = VoToPoTools.toReader(vo);
		r.setReaderid(PoTools.getPoId(PoType.ReaderType));
		readerDao.save(r);
	}

	public List<ReaderVo> getAllReader() {
		List<Reader> list = readerDao.findAll();
		List<ReaderVo> listVo = new ArrayList<ReaderVo>();
		if(list==null || list.size()<=0)
			return listVo;
		for(Reader r : list){
			ReaderVo v = r.toReaderVo();
			listVo.add(v);
		}
		return listVo;
	}

	public ReaderVo getReaderById(Long readerId) {
		List<Reader> list = readerDao.findByReaderid(readerId);
		ReaderVo vo = new ReaderVo();
		if(list==null || list.size()<=0)
			return vo;
		vo = list.get(0).toReaderVo();
		return vo;
	}

}
