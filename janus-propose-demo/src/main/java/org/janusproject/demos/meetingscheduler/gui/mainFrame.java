package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JWindow;

public class mainFrame extends JFrame{

	private static final long serialVersionUID = 3830079646720453065L;

	public mainFrame(){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, getDefaultCloseOperation()));
		this.setSize(400,500);
		this.setLocation(100,100);
		
		JLabel welcomeLabel = new JLabel("Welcome to Janus MeetingScheduler demo");
		JLabel instructionLabel = new JLabel("Add participants to start");
		
		String[] columnNames = {"Participant name"};

		Object[][] data = {
		{"Kathy"},
		{"John"},
		{"Sue"},
		{"Jane"},
		{"Joe"}
		};
		
		final JTable table = new JTable(data, columnNames);
	
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    System.out.println(table);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(table);

		
		//this.pack();
		this.add(welcomeLabel);
		this.add(instructionLabel);
		this.add(scrollPane);
	}

}
