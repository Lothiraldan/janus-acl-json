package org.janusproject.demos.meetingscheduler.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.janusproject.kernel.Kernel;

public class MeetingSchedulor implements ActionListener {

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
