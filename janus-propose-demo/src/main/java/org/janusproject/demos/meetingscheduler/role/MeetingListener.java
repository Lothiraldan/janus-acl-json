package org.janusproject.demos.meetingscheduler.role;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.kernel.address.Address;

public interface MeetingListener {
	public void incomingMeetingProposal(Meeting meeting);
}
