package org.janusproject.demos.meetingscheduler.role;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.arakhne.vmutil.locale.Locale;
import org.janusproject.acl.Performative;
import org.janusproject.acl.protocol.ProtocolResult;
import org.janusproject.acl.protocol.ProtocolState;
import org.janusproject.acl.protocol.propose.FipaProposeProtocol;
import org.janusproject.acl.protocol.request.FipaRequestProtocol;
import org.janusproject.demos.meetingscheduler.util.SerializationUtil;
import org.janusproject.kernel.crio.core.Role;
import org.janusproject.kernel.status.Status;
import org.janusproject.kernel.status.StatusFactory;

public class Initiator extends Role{
	private FipaProposeProtocol proposeProtocol;
	private ProtocolResult answer;
	private ProtocolResult result;
	
	private State state;
	
	@Override
	public Status live() {
		this.state = Request();
		return StatusFactory.ok(this);
	}
	
	@Override
	public Status activate(Object... parameters) {
		
		for (Object parameter : parameters) {
			if (parameter instanceof FipaRequestProtocol) {
				this.proposeProtocol = (FipaProposeProtocol) parameter;
				this.state = State.SENDING_REQUEST;
				return StatusFactory.ok(this);
			}
		}
		
		return StatusFactory.cancel(this);
	}
	
	private State Request() {
		
		if (this.proposeProtocol.hasFailed() ){
			leaveMe();
		}
		
		switch (this.state) 
		{
			case SENDING_REQUEST: 
			{	
				//requestProtocol.request( "evil" );
				//this.proposeProtocol.propose(SerializationUtil.encode(Meeting)); //$NON-NLS-1$
				
				//requestProtocol.cancel("@++");
				//broadcastMessage(Participant.class, this.proposeProtocol.propose(SerializationUtil.encode(Meeting)));

				
				log(Locale.getString("REQUESTSENT")); //$NON-NLS-1$
				
				return State.WAITING_ANSWER;
			}
			case WAITING_ANSWER:
			{
				this.answer = this.proposeProtocol.getAnswer();
				
				if( this.answer != null ) {
					log(Locale.getString("ANSWERRECEIVED")); //$NON-NLS-1$
					
					if (this.answer.getPerformative().compareTo(Performative.REFUSE) == 0)
						return State.REFUSED;
					
					return State.WAITING_RESULT;
				}
				return this.state;
			}
			case WAITING_RESULT:
			{
				this.result = this.proposeProtocol.getResult();
				
				if (this.result != null ) {
					log(Locale.getString("RESULTRECEIVED")); //$NON-NLS-1$
					return State.DONE;
				}
				return this.state;

			}
			case DONE:
			{
				this.answer = null;
				this.result = null;
				leaveMe();
				return this.state;
			}
		default:
			return this.state;
		}
	}	
	
	private void log(String str){
		Logger.getAnonymousLogger().log(Level.INFO, "[" + getPlayer() + "] : " + str); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	private enum State implements ProtocolState {
		SENDING_REQUEST,
		WAITING_ANSWER,
		WAITING_RESULT,
		DONE,
		CANCELING,
		CANCELED,
		REFUSED;
	}
}
