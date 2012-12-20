package org.janusproject.demos.meetingscheduler.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import org.janusproject.demos.meetingscheduler.agent.BaseAgent;
import org.janusproject.demos.meetingscheduler.ontology.Calendar;
import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.crio.core.AddressUtil;
import org.janusproject.kernel.util.sizediterator.SizedIterator;

public class MeetingSchedulor {
	
	private static Kernel kernel;
	private static MeetingSchedulor instance;
	private mainFrame mainFrame;
	private Map<String, calendarFrame> mapping;
	private AgentAddress kernelAgentAddress;

	private MeetingSchedulor() {
		MeetingSchedulor.kernel.launchDifferedExecutionAgents();
		this.kernelAgentAddress = MeetingSchedulor.kernel.getAgents().next();
		this.mainFrame = new mainFrame();
		this.mapping = new TreeMap<String, calendarFrame>();
	}
	
	public static MeetingSchedulor getInstance() {
		if(instance == null) {
			instance = new MeetingSchedulor();
		}
		return instance;
	}
	
	public static void setKernel(Kernel k) {
		kernel = k;
	}
	
	public void start() {
		this.mainFrame.setVisible(true);
	}

	
	
	public void addAgent(String name){
		BaseAgent agent = new BaseAgent();
		this.kernel.submitLightAgent(agent, name);
		this.kernel.launchDifferedExecutionAgents();
		calendarFrame aframe = new calendarFrame(agent.calendar, name);
		aframe.setVisible(true);
		mapping.put(name, aframe);
	}
	
	public void showAgentFrame(String name){
		this.mapping.get(name).setVisible(true);
	}
	
	public void createMeeting(String initiator_name, List<String> participants, Calendar cal, String description){
		Meeting meeting = new Meeting(cal, description);
		Logger.getAnonymousLogger().info("Here ?");
//		Meeting meeting = new Meeting()
	}

}
