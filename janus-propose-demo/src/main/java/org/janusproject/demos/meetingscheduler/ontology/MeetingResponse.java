package org.janusproject.demos.meetingscheduler.ontology;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.miginfocom.util.dates.ImmutableDateRange;

public class MeetingResponse implements Serializable {
	private UUID id;
	private Map<ImmutableDateRange, Integer> choices = new HashMap<ImmutableDateRange, Integer>();

	public MeetingResponse(Meeting meeting) {
		super();
		this.id = meeting.getId();
	}
	
	public void addResponseDate(ImmutableDateRange date, int value) {
		if (value != 0) {
			choices.put(date, value);
		}
	}

	public UUID getId() {
		return this.id;
	}	
	
}
