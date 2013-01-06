package org.janusproject.demos.meetingscheduler.ontology;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.janusproject.kernel.address.Address;

public class MeetingManager {
	
	Map<UUID, Address> initiatorsAddresses;

	public MeetingManager() {
		super();
		this.initiatorsAddresses = new HashMap<UUID, Address>();
	}

	public void saveInitiatorAddress(UUID meeting_uuid, Address address) {
		this.initiatorsAddresses.put(meeting_uuid, address);
	}
	
	public Address getInitiatorAddress(UUID meeting_uuid) {
		return this.initiatorsAddresses.get(meeting_uuid);
	}
}
