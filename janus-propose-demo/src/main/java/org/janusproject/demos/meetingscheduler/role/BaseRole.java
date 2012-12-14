package org.janusproject.demos.meetingscheduler.role;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;
import org.janusproject.kernel.crio.core.Role;

public abstract class BaseRole extends Role {
	Calendar calendar;

	public BaseRole() {
		super();
		this.calendar = new Calendar();
	}
}
