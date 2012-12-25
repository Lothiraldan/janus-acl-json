package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;

import com.miginfocom.util.dates.ImmutableDateRange;

public class meetingProposalFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -8269547358309700827L;
	private JTable propList;

	public meetingProposalFrame(String who, Meeting meeting) {
		setTitle(who + " new meeting proposal from " + meeting.getInitiator());

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,
				getDefaultCloseOperation()));
		this.setSize(500, 300);
		this.setLocation(300, 400);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Time Slot");
		columnNames.add("Rank");
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		for (ImmutableDateRange date: meeting.getDates()) {
			Vector<Object> row = new Vector<Object>();
			row.add(date);
			row.add(new JSpinner(new SpinnerNumberModel(0, 0, 10, 1)));
			data.add(row);
		}
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		
		propList = new JTable(model);

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
