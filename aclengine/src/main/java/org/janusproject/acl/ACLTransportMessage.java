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
package org.janusproject.acl;

import org.janusproject.kernel.message.ObjectMessage;

/**
 * This class extends ObjectMessage and is used to
 * transport the encoded ACLMessage (payload) via
 * the Message Transport Service
 * 
 * @author $Author: madeline$
 * @author $Author: kleroy$
 * @author $Author: ptalagrand$
 * @author $Author: ngaud$
 * @version $FullVersion$
 * @mavengroupid $Groupid$
 * @mavenartifactid $ArtifactId$
 */
public class ACLTransportMessage extends ObjectMessage
{
	private static final long serialVersionUID = -296154029511090541L;

	/**
	 * Creates a new ACLTransportMessage containing
	 * the ACLMessage encoded (payload) in byte array
	 * got as parameter
	 * 
	 * @param payload the ACLMessage encoded in byte array
	 */
	public ACLTransportMessage(byte[] payload) {
		super(payload);
	}
	
	/**
	 * Getter of the ACLTransportMessage content
	 * 
	 * @return the content of the ACLTransportMessage
	 * normally the encoded ACLMessage (payload)
	 */
	public byte[] getPayload() {
		return getContent(byte[].class);
	}
}
