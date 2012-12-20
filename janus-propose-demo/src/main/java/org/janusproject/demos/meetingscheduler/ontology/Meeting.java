package org.janusproject.demos.meetingscheduler.ontology;

import java.util.Date;

public class Meeting {
	private Calendar calendar;
	private String description;

	public Meeting(Calendar calendar, String description) {
		super();
		this.calendar = calendar;
		this.description = description;
	}
}
