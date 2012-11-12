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
package org.janusproject.acl.encoding;

import org.janusproject.acl.ACLMessage;
import org.janusproject.acl.ACLMessageContent;

/**
 * This interface describes the EncodingService which encodes the ACLMessageContent
 * of an ACLMessage
 * 
 * @author $Author: madeline$
 * @author $Author: kleroy$
 * @author $Author: ptalagrand$
 * @author $Author: ngaud$
 * @version $FullVersion$
 * @mavengroupid $Groupid$
 * @mavenartifactid $ArtifactId$
 */
interface ACLMessageContentEncodingService
{
    /**
     * This method encodes the ACLMessageContent of an ACLMessage in the matching type
     * 
     * @param aMsg the ACLMessage containing the ACLMessageContent
     * @return the ACLMessageContent encoded
     */
    public byte[] encode(ACLMessage aMsg);
    
    /**
     * This method decodes the ACLMessageContent of an ACLMessage encoded in the matching type
     * 
     * @param byteMsg the ACLMessage encoded in byte array (payload)
     * @param parameters
     * @return the ACLMessageContent decoded
     */
    public ACLMessageContent decode(byte[] byteMsg, Object... parameters);
}
