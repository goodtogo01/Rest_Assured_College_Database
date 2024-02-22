package com.College.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.google.gson.JsonObject;

 
 

public class Utilities {
	 @SuppressWarnings("unused")
	private Date getTime(long millis) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTimeInMillis(millis);
         return calendar.getTime();        
         }

	public static String reportName() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_mm_dd_HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		String formattedTime = dateTimeFormatter.format(localDateTime);
		String reportName = "Test_Report_On_" + formattedTime;
		return reportName;
	}
	 

}
