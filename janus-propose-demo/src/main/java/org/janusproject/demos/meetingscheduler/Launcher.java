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

import org.arakhne.vmutil.locale.Locale;
import org.janusproject.demos.meetingscheduler.agent.InitiatorAgent;
import org.janusproject.demos.meetingscheduler.agent.ParticipantAgent;
import org.janusproject.kernel.Kernel;
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
		
		Kernel k = Kernels.get( false );
		
		InitiatorAgent initiator = new InitiatorAgent();
		ParticipantAgent participant = new ParticipantAgent();
		
		k.submitLightAgent(initiator, Locale.getString(Launcher.class, "SENDER")); //$NON-NLS-1$
		k.submitLightAgent(participant, Locale.getString(Launcher.class, "RECEIVER")); //$NON-NLS-1$
		
		k.launchDifferedExecutionAgents();	
		
		Kernels.killAll();
	}	
}
