package org.janusproject.demos.meetingscheduler.ontology;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.miginfocom.util.dates.DateRange;
import com.miginfocom.util.dates.ImmutableDateRange;

public class Meeting implements Serializable {
	
	private static final long serialVersionUID = 3733265177969466470L;
	private List<ImmutableDateRange> dates;
	private String description;
	private UUID id;
	
	public Meeting(List<ImmutableDateRange> dates, String description) {
		super();
		this.id = UUID.randomUUID();
		this.dates = dates;
		this.description = description;
	}

	public UUID getId() {
		return id;
	}

}
