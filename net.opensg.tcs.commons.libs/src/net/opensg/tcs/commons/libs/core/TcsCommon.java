package net.opensg.tcs.commons.libs.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TcsCommon {

	public static String APP_NAME = "TCS Begins";

	public static String LIB_VERSION = "1.0";
	
	public static final int UI_WIDGET_ROW_HEIGHT = 30;
	
	
	public static String DateDisplay(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
	}
	public static String DateDisplay() {
		return DateDisplay(new Date());
	}
	public static void ConsoleOut(String x) {
		System.out.print("[" + TcsCommon.DateDisplay() + "] ");
		System.out.println(x);
	}
	
}
