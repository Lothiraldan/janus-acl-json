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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;

public class calendarFrame extends JFrame implements ActionListener{

	/**
 * 
 */
	private static final long serialVersionUID = -6091451186812076790L;
	private Calendar calendar;
	private String name;

	public calendarFrame(Calendar calendar, String name) {
		this.calendar = calendar;
		this.name = name;

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,
				getDefaultCloseOperation()));
		this.setSize(500, 300);
		this.setLocation(200, 200);

		// JTable calendarTable = new JTable(data, columnNames);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel descLabel = new JLabel("Schedule of " + this.name);

		myTableModel model = new myTableModel(this.calendar);
		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		
		JButton newmeetingButton = new JButton("New Meeting");
		newmeetingButton.setActionCommand("NEWMEETING");
	 	newmeetingButton.addActionListener(this);
	 	
		panel.add(descLabel, BorderLayout.NORTH);
		panel.add(newmeetingButton,BorderLayout.SOUTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		this.add(panel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "NEWMEETING") {
			initiateMeetingFrame initmeetingFrame = new initiateMeetingFrame(this.calendar, name);
			initmeetingFrame.setVisible(true);
		}		
	}
	
}
