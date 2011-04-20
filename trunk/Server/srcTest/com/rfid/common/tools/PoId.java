package com.rfid.common.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import javax.swing.text.DateFormatter;

import junit.framework.TestCase;

import com.rfid.common.constants.PoTools;
import com.rfid.common.constants.EnumConstant.PoType;

public class PoId extends TestCase{

	/**
	 * @param args
	 */
	public void testGetPoId() {
		Long id = PoTools.getPoId(PoType.UserType);
		System.out.print(id);
	}

}
