package org.janusproject.demos.meetingscheduler.agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.janusproject.demos.meetingscheduler.role.MeetingChannel;
import org.janusproject.kernel.address.Address;
import org.janusproject.kernel.agent.Agent;
import org.janusproject.kernel.channels.Channel;
import org.janusproject.kernel.channels.ChannelInteractable;
import org.janusproject.kernel.status.Status;

public class MeetingAgent extends Agent implements ChannelInteractable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3106607589577058099L;

	/**
	 * Reference to the UIChannel to limit the number of instances to one.
	 */
	private MeetingChannel meetingChannel = null;

	private class MeetingChannelImplementation implements MeetingChannel {

		private final List<ChangeListener> listeners = new ArrayList<ChangeListener>();

		@Override
		public Address getChannelOwner() {
			// TODO Auto-generated method stub
			return null;
		}

		public synchronized void addChangeListener(ChangeListener listener) {
			this.listeners.add(listener);
		}

		public synchronized void removeChangeListener(ChangeListener listener) {
			this.listeners.remove(listener);
		}

		public synchronized void fireChange() {
			ChangeEvent e = new ChangeEvent(this);
			for (ChangeListener listener : this.listeners) {
				listener.stateChanged(e);
			}
		}

	    public synchronized void release() {
	      this.listeners.clear();
	    }

	}

	@Override
	public Set<? extends Class<? extends Channel>> getSupportedChannels() {
		return Collections.singleton(MeetingChannel.class);
	}

	@Override
	public synchronized <C extends Channel> C getChannel(Class<C> channelClass,
			Object... params) {
		// Check if the given channel type is supported by the agent.
		if (MeetingChannel.class.isAssignableFrom(channelClass)) {

			// Check if a channel was already created
			if (this.meetingChannel == null) {
				// Create the instance of the channel.
				this.meetingChannel = new MeetingChannelImplementation();
			}

			// Reply the channel instance.
			return channelClass.cast(this.meetingChannel);

		}

		// The given channel type is not supported
		throw new IllegalArgumentException("channelClass");
	}
	
	public Status end() {
	    // Be sure that any UI channel is released
	    if (this.meetingChannel!=null) {
	      this.meetingChannel.release();
	      this.meetingChannel = null;
	    }
	    return null;
	  }

}
