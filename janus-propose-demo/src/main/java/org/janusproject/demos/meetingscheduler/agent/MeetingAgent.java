package org.janusproject.demos.meetingscheduler.agent;

import org.janusproject.demos.meetingscheduler.organization.ProposeOrganization;
import org.janusproject.demos.meetingscheduler.role.MeetingRole;
import org.janusproject.kernel.agent.Agent;
import org.janusproject.kernel.status.Status;

public class MeetingAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3106607589577058099L;
	
	public Status live() {
		Status s = super.live();
		if (s.isSuccess()) {
			requestRole(MeetingRole.class, getOrCreateGroup(ProposeOrganization.class));
		}
		return s;
	}

}