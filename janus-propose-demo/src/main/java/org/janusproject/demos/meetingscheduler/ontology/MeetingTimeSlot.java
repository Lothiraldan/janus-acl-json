package org.janusproject.demos.meetingscheduler.ontology;

import java.util.ArrayList;
import java.util.List;

public class MeetingTimeSlot {
	private Integer value;
	private List<String> participants;
	private Integer participantsSize;

	public MeetingTimeSlot(int size) {
		super();
		this.participantsSize = size;
		this.value = 0;
		this.participants = new ArrayList<String>();
	}

	public void processResponse(String participant, int value) {
		this.participants.add(participant);
		this.value += value;
	}

	public Integer getValue() {
		return value;
	}

	public Boolean hasAllParticipants() {
		return this.participants.size() == this.participantsSize;
	}

	public List<String> getParticipants() {
		return participants;
	}

}
