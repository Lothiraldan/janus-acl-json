package org.janusproject.demos.meetingscheduler.gui;

import javax.swing.JFrame;

import org.janusproject.kernel.agent.Kernels;

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MeetingSchedulor ms = new MeetingSchedulor(Kernels.get());
		ms.start();
	}

}
