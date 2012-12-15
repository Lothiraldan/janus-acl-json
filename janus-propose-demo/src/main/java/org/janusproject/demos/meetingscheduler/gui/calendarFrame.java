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

		String[] columnNames = { "Horaires", "Lundi", "Mardi", "Mercredi",
				"Jeudi", "Vendredi", "Samedi", "Dimanche" };
		Object[][] data = {
				{ "7-8", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "8-9", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "9-10", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "10-11", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "11-12", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "12-13", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "13-14", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "14-15", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "15-16", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "16-17", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "17-18", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "18-19", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" },
				{ "19-20", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,
						Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, "" }, };

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
			initiateMeetingFrame initmeetingFrame = new initiateMeetingFrame(this.calendar);
			initmeetingFrame.setVisible(true);
		}		
	}
	
}
