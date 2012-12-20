/* 
 * $Id$
 * 
 * Janus platform is an open-source multiagent platform.
 * More details on <http://www.janus-project.org>
 * Copyright (C) 2012 Janus Core Developers
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.janusproject.demos.meetingscheduler;

import java.util.logging.Level;

import org.janusproject.demos.meetingscheduler.agent.MeetingAgent;
import org.janusproject.demos.meetingscheduler.gui.AgentCalendarUI;
import org.janusproject.demos.meetingscheduler.gui.CopyOfAgentCalendarUI;
import org.janusproject.demos.meetingscheduler.role.MeetingChannel;
import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.agent.ChannelManager;
import org.janusproject.kernel.agent.Kernels;
import org.janusproject.kernel.logger.LoggerUtil;

/**
 * DEMO : Propose Protocol
 * 
 * @author $Author: madeline$
 * @author $Author: kleroy$
 * @author $Author: ptalagrand$
 * @author $Author: ngaud$
 * @version $FullVersion$
 * @mavengroupid $Groupid$
 * @mavenartifactid $ArtifactId$
 */
public class Launcher {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		LoggerUtil.setGlobalLevel(Level.ALL);
		LoggerUtil.setShortLogMessageEnable(true);
		
		Kernel k = Kernels.get(false);
		
		String[] names = {"Boris FELD"};//, "Nicolas GAUD", "Nicolas GRENIE"};
		
		for (int i = 0; i < names.length; i++) {
			launch(k, names[i]);
		}
		
		k.launchDifferedExecutionAgents();	
		
		Kernels.killAll();
	}
	
	public static void launch(Kernel k, String name) {
		MeetingAgent agent = new MeetingAgent();
		k.submitLightAgent(agent, name);
		
		// Channel
		AgentAddress aa = agent.getAddress();
	    ChannelManager channelManager = k.getChannelManager();
	    MeetingChannel channel = channelManager.getChannel(aa, MeetingChannel.class);
	    
	    // UI
	    CopyOfAgentCalendarUI ui = new CopyOfAgentCalendarUI(name, channel);
	    ui.setVisible(true);
	}
}
