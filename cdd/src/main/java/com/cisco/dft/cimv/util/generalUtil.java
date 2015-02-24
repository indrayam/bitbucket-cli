package main.java.com.cisco.dft.cimv.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class generalUtil {
	
	private generalUtil(){
		
	}
	
	public static boolean validateLoginSession(HttpSession session){
		if(session.getAttribute("userInfo") == null){
			return true;
		}
		return false;
	}
	
	// 0 to 4.9 is red, 5 to 6.9 is yellow and 7 onwards green
	public static String doQualityIndexToStatus(float qualityIndex){
		String status = "green";
		if(qualityIndex < 5 && qualityIndex >= 0){
			status = "red";
		}else if(qualityIndex >= 5 && qualityIndex < 7){
			status = "yellow";
		}
		return status;
	}
	
	// string : 2345.234232424 converted to string : 2345.23
	public static String roundFloatStringTwoDecimalUnit(String num){
		double numTemp = Double.parseDouble(num);
		return String.valueOf(Math.round(numTemp*100)/100.0);
	}
	
	// (DD/MM/YYYY HH:MM:SS) this changed to (DD/MM HH:MM). eg: (18/04/2014 12:44:34) to (18/04 12:44) 
	public static String trimYearAndSecondsFromTimestamp(String timestamp){
		
		String strTimestamp = timestamp;
		String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}";
		if (timestamp.matches(datePattern)) {
			// HH:MM:SS
			String time = timestamp.split(" ")[1];
		
			// DD/MM/YYYY
			String date = timestamp.split(" ")[0];
		
			// DD/MM HH:MM
			strTimestamp = date.split("/")[0] + "/" + date.split("/")[1] + " " + time.split(":")[0] + ":" + time.split(":")[1];
		}
		return strTimestamp;
	}
	
	// (YYYY-MM-DD HH:MM:SS) this changed to (DD/MM HH:MM). eg: (2014-09-25 08:03:10) to (25/09 12:44) 
	public static String timestampReformation(String timestamp) {

		String strTimestamp = timestamp;
		String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
		if (timestamp.matches(datePattern)) {
			// HH:MM:SS
			String time = timestamp.split(" ")[1];

			// DD/MM/YYYY
			String date = timestamp.split(" ")[0];
			
			// DD/MM HH:MM
			Map<String, String> hmDateMap = new HashMap<String, String>();
			hmDateMap.put("01", "Jan");
			hmDateMap.put("02", "Feb");
			hmDateMap.put("03", "Mar");
			hmDateMap.put("04", "Apr");
			hmDateMap.put("05", "May");
			hmDateMap.put("06", "Jun");
			hmDateMap.put("07", "Jul");
			hmDateMap.put("08", "Aug");
			hmDateMap.put("09", "Sep");
			hmDateMap.put("10", "Oct");
			hmDateMap.put("11", "Nov");
			hmDateMap.put("12", "Dec");
			
			String month= hmDateMap.get(date.split("-")[1]);
			
			strTimestamp = month + " " + date.split("-")[2] + " "
					+ time.split(":")[0] + ":" + time.split(":")[1];
		}
		return strTimestamp;
	}
}
