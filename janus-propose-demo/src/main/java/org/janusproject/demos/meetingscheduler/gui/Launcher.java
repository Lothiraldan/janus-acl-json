package org.janusproject.demos.meetingscheduler.gui;

import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.agent.Kernels;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Kernel k = Kernels.get();
		
		// Create UI
		MeetingSchedulor.setKernel(k);
		MeetingSchedulor.getInstance().start();
		meetingProposalFrame m = new meetingProposalFrame();
		m.setVisible(true);
	}

}
