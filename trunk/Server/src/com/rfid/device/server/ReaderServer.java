package com.rfid.device.server;

import java.util.List;

import com.rfid.device.vo.ReaderVo;

/**
 * @author Administrator
 *
 */
public interface ReaderServer {

	/**
	 * 新增一个读写器
	 * (一个读写器只对应一个区域)
	 * @param vo 读写器的值对象
	 */
	void addReader(ReaderVo vo);
	
	/**
	 * 查询所有读写器
	 * @return
	 */
	List<ReaderVo> getAllReader();
	
	/**
	 * 通过读写器编号查询
	 * @param readerId 读写器编号
	 * @return
	 */
	ReaderVo getReaderById(Long readerId);
}
