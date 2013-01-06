package org.janusproject.demos.meetingscheduler.role;

import java.util.List;

import javax.swing.event.ChangeListener;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.demos.meetingscheduler.ontology.MeetingResponse;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.channels.Channel;

public interface MeetingChannel extends Channel {

	public void addMeetingListener(MeetingListener listener);

	public void removeMeetingListener(MeetingListener listener);

	public void release();

	public void createMeeting(Meeting meeting, List<AgentAddress> participants);

	void responseMeeting(MeetingResponse meetingResponse);
}
