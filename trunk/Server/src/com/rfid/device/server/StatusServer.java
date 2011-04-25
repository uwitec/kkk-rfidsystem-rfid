package com.rfid.device.server;

import java.util.List;

import com.rfid.device.vo.StatusVo;

public interface StatusServer {

	public List<StatusVo> getAllStatus();
	
	public boolean addStatus(StatusVo vo);
	
}
