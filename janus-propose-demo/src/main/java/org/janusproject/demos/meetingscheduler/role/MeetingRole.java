package org.janusproject.demos.meetingscheduler.role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import org.janusproject.kernel.status.StatusFactory;

public class MeetingRole extends Role implements ChannelInteractable {

	private UUID id;
	private MeetingChannel meetingChannel = null;
	private Map<UUID, Map<AgentAddress, MeetingResponse>> meetings = new HashMap<UUID, Map<AgentAddress, MeetingResponse>>();
	private List<MeetingListener> listeners = new ArrayList<MeetingListener>();

	public MeetingRole() {
		super();
		this.id = UUID.randomUUID();
		this.meetingChannel = getChannel(MeetingChannel.class);
	}

	@Override
	public Status live() {
		for (Message m : getMailbox()) {
			StringMessage msg = (StringMessage) m;
			Meeting meeting = (Meeting) SerializationUtil.decode(msg.getContent());
			for(MeetingListener listener : listeners) {
				listener.incomingMeetingProposal(meeting);
			}
		}
		return StatusFactory.ok("Ok");
	}

	private class MeetingChannelImplementation implements MeetingChannel {

		@Override
		public Address getChannelOwner() {
			// TODO Auto-generated method stub
			return null;
		}

		public synchronized void addMeetingListener(MeetingListener listener) {
			MeetingRole.this.listeners.add(listener);
		}

		public synchronized void removeMeetingListener(MeetingListener listener) {
			MeetingRole.this.listeners.remove(listener);
		}

		public synchronized void release() {
			MeetingRole.this.listeners.clear();
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
		return this.id;
	}
}
