package org.janusproject.demos.meetingscheduler.role;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.arakhne.vmutil.locale.Locale;
import org.janusproject.acl.protocol.ProtocolResult;
import org.janusproject.acl.protocol.propose.FipaProposeProtocol;
import org.janusproject.kernel.crio.core.Role;
import org.janusproject.kernel.status.Status;
import org.janusproject.kernel.status.StatusFactory;

public class Participant extends Role{
	private State state;
	private FipaProposeProtocol proposeProtocol;
	private ProtocolResult propose;
	
	@Override
	public Status live() {
		this.state = HandleRequest();
		return StatusFactory.ok(this);
	}
	
	@Override
	public Status activate(Object... parameters) {
		
		for (Object parameter : parameters) {
			if (parameter instanceof FipaProposeProtocol) {
				this.proposeProtocol = (FipaProposeProtocol) parameter;
				this.state = State.WAITING_REQUEST;
				return StatusFactory.ok(this);
			}
		}
		
		return StatusFactory.cancel(this);
	}
	
	private State HandleRequest() {
		
		if (this.proposeProtocol.hasFailed() ){
			leaveMe();
		}
		
		switch (this.state) 
		{
			case WAITING_REQUEST: 
			{
				this.propose = this.proposeProtocol.getPropose();
				
				if( this.propose != null ){
					log(Locale.getString("REQUESTRECEIVED")); //$NON-NLS-1$
					return State.SENDING_ANSWER;

				// requestProtocol.notUnderstood("?");
				// return State.DONE;
				}
				
				return State.WAITING_REQUEST;
			}
			case SENDING_ANSWER:
			{
				if( this.propose.getContent().toString().equalsIgnoreCase(Locale.getString("REQUESTCONTENT"))) { //$NON-NLS-1$
					this.proposeProtocol.accept(Locale.getString("ACCEPT")); //$NON-NLS-1$
					log(Locale.getString("ACCEPTEMENTSENT")); //$NON-NLS-1$
					return State.SENDING_RESULT;
				}
				this.proposeProtocol.reject(Locale.getString("REJECT")); //$NON-NLS-1$
				log(Locale.getString("REJECTIONSENT")); //$NON-NLS-1$
				return State.DONE;
			}
			case SENDING_RESULT:
			{
				this.proposeProtocol.informDone(Locale.getString("INFORMDONE")); //$NON-NLS-1$
				log(Locale.getString("RESULTSENT")); //$NON-NLS-1$
				return State.DONE;
			}
			case DONE:
			{
				log(Locale.getString("FINALIZATION")); //$NON-NLS-1$
				this.propose = null;
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
	
	private enum State{
		WAITING_REQUEST,
		SENDING_ANSWER,
		SENDING_RESULT,
		DONE;
	}
}
