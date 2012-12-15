package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;

public class initiateMeetingFrame extends JFrame implements ActionListener {
	private MeetingSchedulor ms;
	
	private static final long serialVersionUID = 234360639496126275L;
	
	public initiateMeetingFrame(Calendar calendar, String name){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, getDefaultCloseOperation()));
		this.setSize(500,300);
		this.setLocation(300,400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		String[] data = {"one", "two", "three", "four"};
		JList participantList = new JList(MeetingSchedulor.getInstance().getAllAgents().toArray());
		JScrollPane scrollPane = new JScrollPane(participantList);
		
		JButton sendProposalButton = new JButton("Send meeting proposal");
		sendProposalButton.setActionCommand("SENDMEETING");
		sendProposalButton.addActionListener(this);
		
		myTableModel model = new myTableModel(this.calendar);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		panel.add(scrollPane,BorderLayout.EAST);
		
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
