package org.janusproject.demos.meetingscheduler.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.demos.meetingscheduler.ontology.MeetingResponse;
import org.janusproject.demos.meetingscheduler.util.SerializationUtil;
import org.janusproject.kernel.address.Address;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.channels.Channel;
import org.janusproject.kernel.channels.ChannelInteractable;
import org.janusproject.kernel.crio.core.Role;
import org.janusproject.kernel.message.Message;
import org.janusproject.kernel.message.StringMessage;
import org.janusproject.kernel.status.Status;

public class MeetingRole extends Role implements ChannelInteractable {

	private MeetingChannel meetingChannel = null;
	private Map<UUID, Map<AgentAddress, MeetingResponse>> meetings = new HashMap<UUID, Map<AgentAddress, MeetingResponse>>();

	@Override
	public Status live() {
		// TODO Auto-generated method stub
		return null;
	}

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

		@Override
		public void createMeeting(Meeting meeting,
				List<AgentAddress> participants) {
			String encoded_meeting = SerializationUtil.encode(meeting);
			Message message = new StringMessage(encoded_meeting);
			Map<AgentAddress, MeetingResponse> meet = new HashMap<AgentAddress, MeetingResponse>();
			MeetingRole.this.meetings.put(meeting.getId(), meet);

			for (AgentAddress agentAddress : participants) {
				meet.put(agentAddress, null);
				MeetingRole.this.sendMessage(MeetingRole.class, agentAddress,
						message);
			}
		}

	}

	public <C extends Channel> C getChannel(Class<C> channelClass,
			Object... params) {
		// Check if the given channel type is supported by the role.
		if (MeetingChannel.class.isAssignableFrom(channelClass)) {

			// Create the instance of the channel.
			MeetingChannel channelInstance = new MeetingChannelImplementation();

			// Reply the channel instance.
			return channelClass.cast(channelInstance);

		}

		// The given channel type is not supported
		throw new IllegalArgumentException("channelClass");
	}

	/**
	 * This function replies the types of the channels that are supported by
	 * this role.
	 */
	public Set<? extends Class<? extends Channel>> getSupportedChannels() {
		return Collections.singleton(MeetingChannel.class);
	}

	@Override
	public UUID getUUID() {
		// TODO Auto-generated method stub
		return null;
	}

}
