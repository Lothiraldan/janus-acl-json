package org.janusproject.demos.meetingscheduler.util;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
	
	private static long step = 1000 * 60 * 60;
	
	public static List<Date> generateDateRange(Date from, Date to) {
		List<Date> dates = new ArrayList<Date>();
		for(long t = from.getTime(); t <= to.getTime(); t += step) {
			dates.add(new Date(t));
		}
		return dates;
	}
}
