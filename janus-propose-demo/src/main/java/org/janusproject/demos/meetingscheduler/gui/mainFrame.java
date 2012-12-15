package org.janusproject.demos.meetingscheduler.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class mainFrame extends JFrame implements ActionListener,
		ListSelectionListener {

	private static final long serialVersionUID = 3830079646720453065L;
	private ActionListener listener;
	private MeetingSchedulor ms;

	private JList agentNameList;

	public mainFrame() {
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,
				getDefaultCloseOperation()));
		this.setSize(400, 500);
		this.setLocation(100, 100);

		JLabel welcomeLabel = new JLabel(
				"Welcome to Janus MeetingScheduler demo");
		JLabel instructionLabel = new JLabel("Add participants to start");
		JButton addParticipantButton = new JButton("Add Participant");
		addParticipantButton.setActionCommand("ADDPARTICIPANT");
		addParticipantButton.addActionListener(this);

		agentNameList = new JList();
		agentNameList.setSize(400, 200);
		agentNameList.addListSelectionListener(this);

		agentNameList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.println(agentNameList);
			}
		});

		JScrollPane scrollPane = new JScrollPane(agentNameList);

		// this.pack();
		this.add(welcomeLabel);
		this.add(instructionLabel);
		this.add(addParticipantButton);
		this.add(scrollPane);
	}

	public void setSchedulor(MeetingSchedulor ms) {
		this.ms = ms;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd == "ADDPARTICIPANT") {
			this.ms.addAgent(addParticipantPrompt());
			updateList();

		} else if (cmd == "CANCEL") {
			System.out.println("jjiji");
		}
	}

	public String addParticipantPrompt() {
		JFrame frame = new JFrame("InputDialog Example #1");
		String name = JOptionPane.showInputDialog(frame, "What's your name?");
		return name;
	}

	public void updateList() {
		agentNameList.removeAll();
		DefaultListModel dlm = new DefaultListModel();
		for (String elem : this.ms.getAllAgents()) {
			dlm.addElement(elem);
		}
		agentNameList.setModel(dlm);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList list = (JList) e.getSource();
		int selections[] = list.getSelectedIndices();
		Object selectionValues[] = list.getSelectedValues();
		for (int i = 0, n = selections.length; i < n; i++) {
			this.ms.showAgentFrame((String)selectionValues[i]);
		}
	}

}
