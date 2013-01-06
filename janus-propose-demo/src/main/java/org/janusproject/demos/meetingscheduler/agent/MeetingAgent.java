package org.janusproject.demos.meetingscheduler.agent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.janusproject.acl.ACLAgent;
import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.demos.meetingscheduler.ontology.MeetingManager;
import org.janusproject.demos.meetingscheduler.ontology.MeetingResponse;
import org.janusproject.demos.meetingscheduler.role.MeetingChannel;
import org.janusproject.demos.meetingscheduler.role.MeetingListener;
import org.janusproject.demos.meetingscheduler.util.SerializationUtil;
import org.janusproject.kernel.address.Address;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.channels.Channel;
import org.janusproject.kernel.channels.ChannelInteractable;
import org.janusproject.kernel.message.Message;
import org.janusproject.kernel.message.StringMessage;
import org.janusproject.kernel.status.Status;
import org.janusproject.kernel.status.StatusFactory;

public class MeetingAgent extends ACLAgent implements ChannelInteractable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3106607589577058099L;
	private List<MeetingListener> listeners = new ArrayList<MeetingListener>();
	private MeetingManager meetingManager;

	public MeetingAgent() {
		super();
		this.meetingManager = new MeetingManager();
	}

	public Status live() {
		Status s = super.live();
		for (Message m : getMailbox()) {
			StringMessage msg = (StringMessage) m;
			Meeting meeting = (Meeting) SerializationUtil.decode(msg
					.getContent());
			meetingManager.saveInitiatorAddress(meeting.getId(),
					msg.getSender());
			for (MeetingListener listener : listeners) {
				listener.incomingMeetingProposal(meeting);
			}
		}
		return s;
	}

	private class MeetingChannelImplementation implements MeetingChannel {

		@Override
		public Address getChannelOwner() {
			// TODO Auto-generated method stub
			return null;
		}

		public synchronized void addMeetingListener(MeetingListener listener) {
			MeetingAgent.this.listeners.add(listener);
		}

		public synchronized void removeMeetingListener(MeetingListener listener) {
			MeetingAgent.this.listeners.remove(listener);
		}

		public synchronized void release() {
			MeetingAgent.this.listeners.clear();
		}

		@Override
		public void createMeeting(Meeting meeting,
				List<AgentAddress> participants) {
			String encoded_meeting = SerializationUtil.encode(meeting);
			Message message = new StringMessage(encoded_meeting);
			// Map<AgentAddress, MeetingResponse> meet = new
			// HashMap<AgentAddress, MeetingResponse>();
			// MeetingAgent.this.meetings.put(meeting.getId(), meet);
			MeetingAgent.this.sendMessage(message, participants);
		}

		@Override
		public void responseMeeting(MeetingResponse meetingResponse) {
			Address address = MeetingAgent.this.meetingManager
					.getInitiatorAddress(meetingResponse.getId());
			MeetingAgent.this
					.sendMessage(
							new StringMessage(SerializationUtil
									.encode(meetingResponse)),
							(AgentAddress) address);
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
}
