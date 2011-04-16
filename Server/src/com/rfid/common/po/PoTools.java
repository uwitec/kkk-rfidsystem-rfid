package com.rfid.common.po;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PoTools {

	public static Long getPoId(){
		Long poId = null;
		Date date = new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String dateStr=dateFormat.format(date);
		String[] dates = dateStr.split("-");
		String year = dates[0];
		String month = dates[1];
		String day = dates[2];
		String hours = String.valueOf((24-Integer.valueOf(dates[3])));
		String min = dates[4];
		String second = dates[5];
		double d = Math.random()*100;
		String dd = Double.toString(d);
		String num = dd.substring(0, 2);
		String l = "100" + year + month + day + hours + min + second + num;
		Long lon = Long.parseLong( l );
		return lon;
	}
}
