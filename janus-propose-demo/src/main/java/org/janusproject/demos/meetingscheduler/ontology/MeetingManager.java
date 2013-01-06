package org.janusproject.demos.meetingscheduler.ontology;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.janusproject.kernel.address.Address;
import org.janusproject.kernel.address.AgentAddress;

import com.miginfocom.util.dates.ImmutableDateRange;

public class MeetingManager {

	Map<UUID, Address> initiatorsAddresses;
	Map<UUID, List<AgentAddress>> waitingResponses;
	Map<UUID, Map<ImmutableDateRange, MeetingTimeSlot>> meetingSlots;

	public MeetingManager() {
		super();
		this.initiatorsAddresses = new HashMap<UUID, Address>();
		this.waitingResponses = new HashMap<UUID, List<AgentAddress>>();
		this.meetingSlots = new HashMap<UUID, Map<ImmutableDateRange, MeetingTimeSlot>>();
	}

	public void saveInitiatorAddress(UUID meeting_uuid, Address address) {
		this.initiatorsAddresses.put(meeting_uuid, address);
	}

	public Address getInitiatorAddress(UUID meeting_uuid) {
		return this.initiatorsAddresses.get(meeting_uuid);
	}

	public void processNewMeeting(Meeting meeting,
			List<AgentAddress> participants) {
		this.waitingResponses.put(meeting.getId(), participants);
		Map<ImmutableDateRange, MeetingTimeSlot> slots = new HashMap<ImmutableDateRange, MeetingTimeSlot>();
		for (ImmutableDateRange timeSlot : meeting.getDates()) {
			slots.put(timeSlot, new MeetingTimeSlot(participants.size()));
		}
		this.meetingSlots.put(meeting.getId(), slots);
	}

	public void process_response(MeetingResponse meetingResponse,
			AgentAddress address) {
		this.waitingResponses.get(meetingResponse.getId()).remove(address);
		Map<ImmutableDateRange, MeetingTimeSlot> slots = this.meetingSlots
				.get(meetingResponse.getId());
		for (Entry<ImmutableDateRange, Integer> entry : meetingResponse
				.getSlots().entrySet()) {
			slots.get(entry.getKey()).processResponse(meetingResponse.getWho(),
					entry.getValue());
		}
	}
	
	public Boolean hasAllResponses(UUID id) {
		return this.waitingResponses.get(id).size() == 0;
	}

	public Map<ImmutableDateRange, MeetingTimeSlot> getSlots(UUID id) {
		return this.meetingSlots.get(id);
	}
}
