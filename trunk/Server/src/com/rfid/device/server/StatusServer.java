package com.rfid.device.server;

import java.util.List;

import com.rfid.device.vo.StatusVo;

/**
 * 对状态设置的增，查
 * @author Administrator
 *
 */
public interface StatusServer {

	/**
	 * 获取所有状态
	 * @return
	 */
	public List<StatusVo> getAllStatus();
	
	/**
	 * 增加状态
	 * @param vo
	 * @return
	 */
	public boolean addStatus(StatusVo vo);
	
}
