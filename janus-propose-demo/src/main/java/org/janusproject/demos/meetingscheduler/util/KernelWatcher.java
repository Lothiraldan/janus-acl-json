package org.janusproject.demos.meetingscheduler.util;

import java.util.ArrayList;
import java.util.List;

import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.address.AgentAddress;
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

	public List<String> getAllAgents(){
		List<String> agents = new ArrayList<String>();
		for (SizedIterator<AgentAddress> iterator = k.getAgents(); iterator.hasNext();) {
			AgentAddress aagent = iterator.next();
			if(!aagent.equals(this.kernelAgentAddress)) {
				agents.add(aagent.getName());
			}
		}
		return agents;
	}
	
	public List<String> getAllAgentExcept(String name) {
		List<String> agents = new ArrayList<String>();
		for (SizedIterator<AgentAddress> iterator = k.getAgents(); iterator.hasNext();) {
			AgentAddress aagent = iterator.next();
			if(!aagent.equals(this.kernelAgentAddress) && !aagent.getName().equals(name)) {
				agents.add(aagent.getName());
			}
		}
		return agents;		
	}
}
