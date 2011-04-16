package com.rfid.common.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.swing.text.DateFormatter;

public class PoId {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Long poId = null;
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String dateStr = dateFormat.format(date);
		
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
		System.out.print(l);
	}

}
