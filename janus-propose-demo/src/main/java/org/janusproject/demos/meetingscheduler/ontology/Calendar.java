package org.janusproject.demos.meetingscheduler.ontology;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
	public List<List<String>> busy = new ArrayList<List<String>>();
	
	public Calendar() {
		super();
		for(int i = 0; i < 6; i++) {
			List<String> row = new ArrayList<String>();
			for(int j = 0; j < 13; j++) {
				row.add(null);
			}
			busy.add(row);
		}
	}

	public void setBusy(int day, int hour, String reason) {
		busy.get(day).set(hour, reason);
	}
	
	
}
