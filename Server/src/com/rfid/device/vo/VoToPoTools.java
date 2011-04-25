package com.rfid.device.vo;

import com.rfid.device.po.Status;

public class VoToPoTools {

	public static Status toStatus(StatusVo vo){
		if(vo==null)
			return null;
		Status s = new Status();
		if(vo.getId()!=null)
			s.setId(vo.getId());
		if(vo.getLevel()!=null)
			s.setLevel(vo.getLevel());
		if(vo.getScription()!=null)
			s.setScription(vo.getScription());
		if(vo.getStatusId()!=null)
			s.setStatusId(vo.getStatusId());
		return s;
	}
}
