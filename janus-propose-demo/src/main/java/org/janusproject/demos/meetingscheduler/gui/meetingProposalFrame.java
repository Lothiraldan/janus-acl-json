package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.kernel.address.Address;

public class meetingProposalFrame extends JFrame implements ActionListener {

	
	private static final long serialVersionUID = -8269547358309700827L;
	private JTable propList;

	public meetingProposalFrame(String who, Meeting meeting) {
		setTitle(who + " new meeting proposal from " + meeting.getInitiator());
		String[][] propositions = { { "Monday 7-8", "" },
				{ "Tuesday 14-15", "" }, { "Wednesday 17-18", "" },
				{ "Wednesday 18-19", "" }, { "Friday 10-11" } };
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,
				getDefaultCloseOperation()));
		this.setSize(500, 300);
		this.setLocation(300, 400);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
	
		String columnNames[] = { "Time Slot", "Rank" };
		propList = new JTable(propositions, columnNames);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("SUBMIT");
		submitButton.addActionListener(this);

		JLabel descLabel = new JLabel(
				"Rate your prefered proposition (1 is the best)");

		JScrollPane scrollPane = new JScrollPane(propList);
		panel.add(descLabel, BorderLayout.NORTH);
		panel.add(submitButton, BorderLayout.SOUTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		this.add(panel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd == "SUBMIT") {

		}

	}
}
