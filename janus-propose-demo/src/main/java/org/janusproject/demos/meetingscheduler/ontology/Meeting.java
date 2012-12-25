package org.janusproject.demos.meetingscheduler.ontology;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.miginfocom.util.dates.DateRange;
import com.miginfocom.util.dates.ImmutableDateRange;

public class Meeting implements Serializable {
	
	private String initiator;
	private static final long serialVersionUID = 3733265177969466470L;
	private List<ImmutableDateRange> dates;
	private String description;
	private UUID id;
	
	public Meeting(String initiator, List<ImmutableDateRange> dates, String description) {
		super();
		this.initiator = initiator;
		this.id = UUID.randomUUID();
		this.dates = dates;
		this.description = description;
	}

	public List<ImmutableDateRange> getDates() {
		return dates;
	}

	public String getInitiator() {
		return initiator;
	}

	public UUID getId() {
		return id;
	}

}
