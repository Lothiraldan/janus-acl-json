package org.janusproject.demos.meetingscheduler.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.janusproject.kernel.Kernel;

public class MeetingSchedulor {

	private Kernel kernel;
	private mainFrame mainFrame;

	public MeetingSchedulor(Kernel kernel) {
		this.kernel = kernel;
		this.mainFrame = new mainFrame();
		this.mainFrame.setEventListener(this);
	}
	
	public void start() {
		this.mainFrame.setVisible(true);
	}

	
	public List<String> getAllAgents(){}
	public void addAgent(String name){}
	public void showAgentFrame(String){}
	

}
