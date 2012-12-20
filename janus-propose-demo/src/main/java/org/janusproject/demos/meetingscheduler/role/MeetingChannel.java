package org.janusproject.demos.meetingscheduler.role;

import javax.swing.event.ChangeListener;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.kernel.channels.Channel;

public interface MeetingChannel extends Channel {

	/**
	 * Add listener on state changes.
	 */
	public void addChangeListener(ChangeListener listener);

	/**
	 * Remove listener on state changes.
	 */
	public void removeChangeListener(ChangeListener listener);

	public void release();

	public void createMeeting(Meeting meeting);
}
