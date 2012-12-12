package org.janusproject.demos.meetingscheduler.ontology;

import java.util.Date;

public class Meeting {
	private String description = "";
	private Date startingOn;
	private Date endingWith;

	public Meeting(String description, Date startingOn, Date endingWith) {
		super();
		this.description = description;
		this.startingOn = startingOn;
		this.endingWith = endingWith;
	}
}
