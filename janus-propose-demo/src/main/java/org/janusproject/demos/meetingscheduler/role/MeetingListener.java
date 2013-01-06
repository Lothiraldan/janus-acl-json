package org.janusproject.demos.meetingscheduler.role;

import java.util.Map;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.demos.meetingscheduler.ontology.MeetingTimeSlot;
import org.janusproject.kernel.address.Address;

import com.miginfocom.util.dates.ImmutableDateRange;

public interface MeetingListener {
	public void incomingMeetingProposal(Meeting meeting);

	public void chooseMeetingTimeSlot(Map<ImmutableDateRange, MeetingTimeSlot> slots);
}
