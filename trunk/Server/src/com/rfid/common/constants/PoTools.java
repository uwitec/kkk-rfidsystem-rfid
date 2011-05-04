package com.rfid.common.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.rfid.common.constants.EnumConstant.PoType;

public class PoTools {

	public static Long getPoId(PoType type){
		//获取对应对象类型的编号
		String typeNo = type.getInfo();
		//获取日期时间的编号数
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String dateStr = dateFormat.format(date);
		//获取后2位随机数
		double random = Math.random()*100;
		String randomStr = Double.toString(random);
		String randomNum = randomStr.substring(0, 2);
		String id = typeNo + dateStr + randomNum;
		Long l;
		try{
			l = Long.parseLong(id);
		}catch(Exception e){
			l = getPoId(type);
		}
		return l;
	}
}
