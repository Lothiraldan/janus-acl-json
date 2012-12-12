package org.janusproject.demos.meetingscheduler.osgi;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.arakhne.vmutil.locale.Locale;
import org.janusproject.demos.acl.request.Launcher;
import org.janusproject.demos.acl.request.agent.ACLProtocolReceiver;
import org.janusproject.demos.acl.request.agent.ACLProtocolSender;
import org.janusproject.demos.meetingscheduler.agent.InitiatorAgent;
import org.janusproject.demos.meetingscheduler.agent.ParticipantAgent;
import org.janusproject.kernel.Kernel;
import org.janusproject.kernel.agent.KernelAgentFactory;
import org.janusproject.kernel.agent.Kernels;
import org.janusproject.kernel.logger.LoggerUtil;
import org.janusproject.kernel.mmf.JanusApplication;
import org.janusproject.kernel.mmf.KernelAuthority;
import org.janusproject.kernel.mmf.KernelService;
import org.janusproject.kernel.status.Status;
import org.janusproject.kernel.status.StatusFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;


/**
 * 
 * @author $Author: ngaud$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class MeetingSchedulerActivator  implements BundleActivator, JanusApplication {

	private Logger logger;
	
	private static final int PARTICIPANT_COUNT = 10;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		LoggerUtil.setGlobalLevel(Level.INFO);
		this.logger = Logger.getLogger(this.getClass().getCanonicalName());
		this.logger.info(Locale.getString(MeetingSchedulerActivator.class, "ACTIVATING_ACLBASE")); //$NON-NLS-1$
		context.registerService(JanusApplication.class.getName(), this, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		//
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status start(KernelService kernel) {
		this.logger.log(Level.INFO, Locale.getString(MeetingSchedulerActivator.class, "ACLCNP_START")); //$NON-NLS-1$		
		
		LoggerUtil.setGlobalLevel(Level.ALL);
		LoggerUtil.setShortLogMessageEnable(true);
		
		Kernel k = Kernels.get( false );
		
		InitiatorAgent initiatorAgent = new InitiatorAgent();
		ParticipantAgent[] participants = new ParticipantAgent[PARTICIPANT_COUNT];
		for (int i = 0; i < PARTICIPANT_COUNT; ++i) {
			participants[i] = new ParticipantAgent();
		}
		
		kernel.launchLightAgent(initiatorAgent, Locale.getString(Launcher.class, "INITIATOR")); //$NON-NLS-1$
		for (int i = 0; i < PARTICIPANT_COUNT; ++i) {
			kernel.launchLightAgent(participants[i], Locale.getString(Launcher.class, "PARTICIPANT_NAME", i)); //$NON-NLS-1$
		}
//		
//		k.submitLightAgent(sender, Locale.getString(Launcher.class, "SENDER")); //$NON-NLS-1$
//		k.submitLightAgent(receiver, Locale.getString(Launcher.class, "RECEIVER")); //$NON-NLS-1$
//		
		k.launchDifferedExecutionAgents();	
		
		Kernels.killAll();
		
		
		return StatusFactory.ok(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KernelAgentFactory getKernelAgentFactory() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public KernelAuthority getKernelAuthority() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAutoStartJanusModules() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Status stop(KernelService kernel) {
		return StatusFactory.ok(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStopOsgiFramework() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isKeepKernelAlive() {
		return false;
	}

	/** {@inheritDoc}
	 */
	@Override
	public String getName() {
		return Locale.getString(MeetingSchedulerActivator.class, "APPLICATION_NAME"); //$NON-NLS-1$
	}

	/** {@inheritDoc}
	 */
	@Override
	public String getDescription() {
		return Locale.getString(MeetingSchedulerActivator.class, "APPLICATION_DESCRIPTION"); //$NON-NLS-1$
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRunning() {
		return true;
	}

}
