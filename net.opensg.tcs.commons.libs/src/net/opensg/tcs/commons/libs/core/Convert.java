package net.opensg.tcs.commons.libs.core;

public class Convert {

	public static String ToString(Object obj) {
		String result = "";
		try {
			result = String.valueOf(obj);
		} catch (Exception ex) {
		}
		return result;
	}
	
	public static double ToDouble(Object obj) {
		double result = 0;
		try {
			result = Double.parseDouble((String)obj);
		} catch (Exception ex) {
		}
		return result;
	}
	
	
}
