package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.janusproject.demos.meetingscheduler.ontology.MeetingTimeSlot;

import com.miginfocom.util.dates.ImmutableDateRange;

public class chooseMeetingtimeSlotFrame extends JFrame implements
		ActionListener {

	private static final long serialVersionUID = -4757588276034608043L;
	private Map<ImmutableDateRange, MeetingTimeSlot> slots;
	private Vector<Vector<Object>> data;
	private JTable slotsTable;

	public chooseMeetingtimeSlotFrame(String who,
			Map<ImmutableDateRange, MeetingTimeSlot> slots) {
		this.slots = slots;
		setTitle(who + " choose meeting slot");

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
		columnNames.add("Participants");

		data = new Vector<Vector<Object>>();
	
		for (Entry<ImmutableDateRange, MeetingTimeSlot> entry : this.slots
				.entrySet()) {
			Vector<Object> row = new Vector<Object>();
			row.add(entry.getKey());
			MeetingTimeSlot value = entry.getValue();
			row.add(value.getValue());
			if (value.hasAllParticipants()) {
				row.add("All participants");
			} else {
				row.add(value.getParticipants().toString());
			}
			data.add(row);
		}

		DefaultTableModel model = new DefaultTableModel(data, columnNames);

		slotsTable = new JTable(model);
		slotsTable.setAutoCreateRowSorter(true);

		JButton submitButton = new JButton("Submit");
		submitButton.setActionCommand("SUBMIT");
		submitButton.addActionListener(this);

		JScrollPane scrollPane = new JScrollPane(slotsTable);
		panel.add(submitButton, BorderLayout.SOUTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		this.add(panel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}