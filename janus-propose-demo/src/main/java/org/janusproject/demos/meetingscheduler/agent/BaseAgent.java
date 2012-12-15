package org.janusproject.demos.meetingscheduler.agent;

import org.janusproject.acl.ACLAgent;
import org.janusproject.demos.meetingscheduler.ontology.Calendar;

public class BaseAgent extends ACLAgent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8082340632710800159L;
	
	public Calendar calendar;

	public BaseAgent() {
		super();
		this.calendar = new Calendar();
	}

}
