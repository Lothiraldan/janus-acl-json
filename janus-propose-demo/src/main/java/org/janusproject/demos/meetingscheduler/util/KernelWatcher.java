package org.janusproject.demos.meetingscheduler.util;

import java.util.ArrayList;
import java.util.List;

import org.janusproject.demos.meetingscheduler.organization.ProposeOrganization;
import org.janusproject.demos.meetingscheduler.role.MeetingChannel;
import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.address.AgentAddress;
import org.janusproject.kernel.agent.ChannelManager;
import org.janusproject.kernel.crio.core.AddressUtil;
import org.janusproject.kernel.crio.core.GroupAddress;
import org.janusproject.kernel.util.sizediterator.SizedIterator;

public class KernelWatcher {

	Kernel k;
	private AgentAddress kernelAgentAddress;

	public KernelWatcher(Kernel k) {
		super();
		this.k = k;
		k.launchDifferedExecutionAgents();
		this.kernelAgentAddress = this.k.getAgents().next();
	}

	public List<AgentAddress> getAgentByNames(List<String> agentsNames) {
		List<AgentAddress> agentsAddresses = new ArrayList<AgentAddress>();
		for (SizedIterator<AgentAddress> iterator = k.getAgents(); iterator
				.hasNext();) {
			AgentAddress aagent = iterator.next();
			if (agentsNames.contains(aagent.getName())) {
				agentsAddresses.add(aagent);
			}
		}
		return agentsAddresses;
	}

	public List<String> getAllAgents() {
		List<String> agents = new ArrayList<String>();
		for (SizedIterator<AgentAddress> iterator = k.getAgents(); iterator
				.hasNext();) {
			AgentAddress aagent = iterator.next();
			if (!aagent.equals(this.kernelAgentAddress)) {
				agents.add(aagent.getName());
			}
		}
		return agents;
	}

	public List<String> getAllAgentExcept(String name) {
		List<String> agents = new ArrayList<String>();
		for (SizedIterator<AgentAddress> iterator = k.getAgents(); iterator
				.hasNext();) {
			AgentAddress aagent = iterator.next();
			if (!aagent.equals(this.kernelAgentAddress)
					&& !aagent.getName().equals(name)) {
				agents.add(aagent.getName());
			}
		}
		return agents;
	}

	public MeetingChannel getChannel(String agentName) {
		AgentAddress aaddress = null;
		for (SizedIterator<AgentAddress> iterator = k.getAgents(); iterator
				.hasNext();) {
			AgentAddress aagent = iterator.next();
			if (aagent.getName().equals(agentName)) {
				aaddress = aagent;
				break;
			}
		}

		if (aaddress == null) {
			return null;
		}

		ChannelManager channelManager = k.getChannelManager();
		return channelManager.getChannel(aaddress, MeetingChannel.class);
	}
}
