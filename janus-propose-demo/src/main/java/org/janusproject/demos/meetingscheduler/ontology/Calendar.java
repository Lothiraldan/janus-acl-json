package org.janusproject.demos.meetingscheduler.ontology;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
	public List<List<Object>> busy = new ArrayList<List<Object>>();
	
	public Calendar() {
		super();
		for(int i = 0; i < 13; i++) {
			List<Object> row = new ArrayList<Object>();
			for(int j = 0; j < 7; j++) {
				row.add(Boolean.FALSE);
			}
			busy.add(row);
		}
	}

	public void setBusy(int day, int hour, Object reason) {
		busy.get(day).set(hour, reason);
	}
	
	
}
