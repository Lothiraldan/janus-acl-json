package org.janusproject.demos.meetingscheduler.ontology;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.miginfocom.util.dates.ImmutableDateRange;

public class MeetingResponse implements Serializable {

	private static final long serialVersionUID = 2190746471098238663L;
	private UUID id;
	private Map<ImmutableDateRange, Integer> slots = new HashMap<ImmutableDateRange, Integer>();
	private String who;

	public MeetingResponse(Meeting meeting, String who) {
		super();
		this.id = meeting.getId();
		this.who = who;
	}

	public void addResponseDate(ImmutableDateRange date, Integer value) {
		if (value != 0) {
			slots.put(date, value);
		}
	}

	public UUID getId() {
		return this.id;
	}

	public Map<ImmutableDateRange, Integer> getSlots() {
		return slots;
	}

	public String getWho() {
		return who;
	}

}
