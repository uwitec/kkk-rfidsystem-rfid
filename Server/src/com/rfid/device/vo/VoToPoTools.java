package com.rfid.device.vo;

import java.sql.Timestamp;
import java.util.List;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;
import com.rfid.device.po.Area;
import com.rfid.device.po.AreaMonitor;
import com.rfid.device.po.Device;
import com.rfid.device.po.DeviceDetail;
import com.rfid.device.po.Monitor;
import com.rfid.device.po.Reader;
import com.rfid.device.po.Status;

public class VoToPoTools {

	public static Status toStatus(StatusVo vo){
		if(vo==null)
			return null;
		Status s = new Status();
		if(vo.getId()!=null && vo.getId()!=0)
			s.setId(vo.getId());
		if(vo.getLevel()!=null)
			s.setLevel(vo.getLevel());
		if(vo.getScription()!=null)
			s.setScription(vo.getScription());
		if(vo.getStatusId()!=null && vo.getStatusId()!=0)
			s.setStatusId(vo.getStatusId());
		return s;
	}

	public static DeviceDetail toDeviceDetail(DeviceDetailVo vo) {
		DeviceDetail dd = new DeviceDetail();
		if(vo.getBuyer()!=null)
			dd.setBuyer(vo.getBuyer());
		if(vo.getDeviceName()!=null)
			dd.setDeviceName(vo.getDeviceName());
		if(vo.getDeviceNum()!=null)
			dd.setDeviceNum(vo.getDeviceNum());
		if(vo.getDeviceVo()!=null)
			dd.setDevice(VoToPoTools.toDevice(vo.getDeviceVo()));
		else{
			dd.setDevice(VoToPoTools.toDevice(new DeviceVo()));
		}
		if(vo.getId()!=null && vo.getId()!=0)
			dd.setId(vo.getId());
		if(vo.getManufactory()!=null)
			dd.setManufactory(vo.getManufactory());
		if(vo.getPrice()!=null)
			dd.setPrice(vo.getPrice());
		if(vo.getPurchaseDate()!=null)
			dd.setPurchaseDate(new Timestamp(vo.getPurchaseDate().getTime()));
		return dd;
	}

	public static Device toDevice(DeviceVo vo) {
		Device d = new Device();
		if(vo==null){
			d.setMonitorEnable(1);
			d.setDeviceId(PoTools.getPoId(PoType.DeviceType));
			return d;
		}
		if(vo.getId()!=null && vo.getId()!=0)
			d.setId(vo.getId());
		if(vo.getMonitorEnable()!=null){
			if(vo.getMonitorEnable()==0)
				d.setMonitorEnable(0);
			else
				d.setMonitorEnable(1);
		}else
			d.setMonitorEnable(1);
		if(vo.getDeviceId()!=null && vo.getDeviceId()!=0)
			d.setDeviceId(vo.getDeviceId());
		else
			d.setDeviceId(PoTools.getPoId(PoType.DeviceType));
		return d;
	}

	public static Reader toReader(ReaderVo vo) {
		Reader r = new Reader();
		if(vo.getId()!=null && vo.getId()!=0)
			r.setId(vo.getId());
		if(vo.getReaderid()!=null && vo.getReaderid()!=0)
			r.setReaderid(vo.getReaderid());
		if(vo.getReaderIp()!=null)
			r.setReaderIp(vo.getReaderIp());
		return r;
	}

	public static Area toArea(AreaVo vo) {
		Area a = new Area();
		if(vo.getAreaAddress()!=null)
			a.setAreaAddress(vo.getAreaAddress());
		if(vo.getAreaId()!=null && vo.getAreaId()!=0)
			a.setAreaId(vo.getAreaId());
		if(vo.getId()!=null && vo.getId()!=0)
			a.setId(vo.getId());
		if(vo.getReader()!=null)
			a.setReader(toReader(vo.getReader()));
		if(vo.getScription()!=null)
			a.setScription(vo.getScription());
		return a;
	}

	public static Monitor toMonitor(MonitorVo vo) {
		// TODO Auto-generated method stub
		Monitor m = new Monitor();
		if(vo.getId()!=null && vo.getId()>0)
			m.setId(vo.getId());
		if(vo.getMonitorId()!=null && vo.getMonitorId()>0)
			m.setMonitorId(vo.getMonitorId());
		else
			m.setMonitorId(PoTools.getPoId(PoType.MonitorType));
		if(vo.getMonitorIp()!=null)
			m.setMonitorIp(vo.getMonitorIp());
		if(vo.getMonitorPort()!=null)
			m.setMonitorPort(vo.getMonitorPort());
		return m;
	}
}
