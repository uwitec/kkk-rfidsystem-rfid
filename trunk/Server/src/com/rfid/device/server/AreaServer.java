package com.rfid.device.server;

import java.util.List;

import com.rfid.device.vo.AreaVo;

public interface AreaServer {

	/**
	 * 新增一个新的区域，需要配置该区域的读写器
	 * @param vo
	 * @throws Exception 
	 */
	void addArea(AreaVo vo) throws Exception;
	
	
	/**
	 * 更改该区域使用的读写器
	 * @param areaId 区域编号
	 * @param readerId 读写器编号
	 * @throws Exception 错误信息
	 */
	void assignAreaReader(Long areaId,Long readerId) throws Exception;


	/**
	 * 获取所有区域
	 * @return
	 */
	List<AreaVo> findAllArea();
}
