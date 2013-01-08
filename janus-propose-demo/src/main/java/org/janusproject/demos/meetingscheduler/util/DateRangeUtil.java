package org.janusproject.demos.meetingscheduler.util;

import com.miginfocom.util.dates.ImmutableDateRange;

public class DateRangeUtil {
	public static String dateToHumanFriendly(ImmutableDateRange date){
		return "From "+date.getStart().getTime()+" to "+date.getEnd().getTime();
	}
}
