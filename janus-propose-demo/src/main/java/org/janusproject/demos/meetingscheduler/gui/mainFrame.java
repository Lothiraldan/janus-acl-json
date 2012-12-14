package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;

public class mainFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 3830079646720453065L;
	private ActionListener listener;

	public mainFrame(){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, getDefaultCloseOperation()));
		this.setSize(400,500);
		this.setLocation(100,100);
		
		JLabel welcomeLabel = new JLabel("Welcome to Janus MeetingScheduler demo");
		JLabel instructionLabel = new JLabel("Add participants to start");
		JButton addParticipantButton =  new JButton("Add Participant");
		addParticipantButton.setActionCommand("ADDPARTICIPANT");
		addParticipantButton.addActionListener(this);
		
		String[] columnNames = {"Participant name"};

		Object[][] data = {
		{"Kathy"},
		{"John"},
		{"Sue"},
		{"Jane"},
		{"Joe"}
		};
		
		final JTable table = new JTable(data, columnNames);
		table.setSize(400,200);
	
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    System.out.println(table);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);

		
		//this.pack();
		this.add(welcomeLabel);
		this.add(instructionLabel);
		this.add(addParticipantButton);
		this.add(scrollPane);
	}
	
	public void setSchedulor(MeetingSchedulor ms){
		
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		System.out.println("ahaha");
		if (cmd == "ADDPARTICIPANT") {
		     System.out.println(addParticipantPrompt());
		     
		} else if (cmd == "CANCEL") {
		     System.out.println("jjiji");
		}
	}
	
	public String addParticipantPrompt(){
		JFrame frame = new JFrame("InputDialog Example #1");
		String name = JOptionPane.showInputDialog(frame, "What's your name?");
		return name;
	}
}
