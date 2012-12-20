package org.janusproject.demos.meetingscheduler.ontology;

import java.util.Date;
import java.util.List;

import com.miginfocom.util.dates.DateRange;
import com.miginfocom.util.dates.ImmutableDateRange;

public class Meeting {
	private List<ImmutableDateRange> dates;
	private String description;
	
	public Meeting(List<ImmutableDateRange> dates, String description) {
		super();
		this.dates = dates;
		this.description = description;
	}

}
