package org.janusproject.demos.meetingscheduler.gui;

import org.janusproject.kernel.agent.Kernels;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingSchedulor.setKernel(Kernels.get());
		MeetingSchedulor.getInstance().start();
		meetingProposalFrame m = new meetingProposalFrame();
		m.setVisible(true);
	}

}
