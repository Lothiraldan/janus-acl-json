package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;

public class initiateMeetingFrame extends JFrame implements ActionListener {
	private MeetingSchedulor ms;
	private JList participantList;
	private String initiator_name;
	private Calendar initiator_invertcalendar;
	private JTextField description_field;
	
	private static final long serialVersionUID = 234360639496126275L;
	
	public initiateMeetingFrame(Calendar calendar, String name){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, getDefaultCloseOperation()));
		this.setSize(500,300);
		this.setLocation(300,400);
		this.initiator_name=name;
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		description_field = new JTextField("Description");
		
		
		
		participantList = new JList(MeetingSchedulor.getInstance().getAllAgentExcept(name).toArray());
		JScrollPane scrollPane = new JScrollPane(participantList);
		
		JButton sendProposalButton = new JButton("Send meeting proposal");
		sendProposalButton.setActionCommand("SENDMEETING");
		sendProposalButton.addActionListener(this);
		
		initiator_invertcalendar = calendar;
		myTableModel model = new invertTableModel(initiator_invertcalendar);
		JTable table = new JTable(model);
		
		box.add(description_field);
		box.add(table);
		
		panel.add(scrollPane,BorderLayout.EAST);
		panel.add(box,BorderLayout.CENTER);
		panel.add(sendProposalButton,BorderLayout.SOUTH);
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd == "SENDMEETING") {
			int selections[] =participantList.getSelectedIndices();
			Object selectionValues[] = participantList.getSelectedValues();
			List<String> partList = new ArrayList<String>();
			if(selections!=null){
				for (int i = 0, n = selections.length; i < n; i++) {
					partList.add((String)selectionValues[i]);
				}
				MeetingSchedulor.getInstance().createMeeting(initiator_name, partList, initiator_invertcalendar,description_field.getText());
				this.dispose();
				
			}
			
		}
		
	}
	

}
