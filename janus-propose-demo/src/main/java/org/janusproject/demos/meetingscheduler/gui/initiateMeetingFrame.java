package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.janusproject.demos.meetingscheduler.ontology.Meeting;
import org.janusproject.demos.meetingscheduler.util.KernelWatcher;

import com.miginfocom.calendar.DatePicker;
import com.miginfocom.calendar.ThemeDatePicker;
import com.miginfocom.calendar.activity.ActivityDepository;
import com.miginfocom.calendar.activity.ActivityList;
import com.miginfocom.theme.Themes;
import com.miginfocom.util.dates.DateChangeEvent;
import com.miginfocom.util.dates.DateChangeListener;
import com.miginfocom.util.dates.DateRange;
import com.miginfocom.util.dates.DateRangeI;
import com.miginfocom.util.dates.ImmutableDateRange;

public class initiateMeetingFrame extends JFrame implements ActionListener,
		DateChangeListener {

	private KernelWatcher kw;

	private JList<String> participantList;
	private JList<ImmutableDateRange> hoursList;
	
	private JList<String> suggestedHoursList;
	private JList<String> selectedHoursList;
	private DefaultListModel<String> suggestedHoursListModel = new DefaultListModel<String>();;
	private DefaultListModel<String> selectedHoursListModel = new DefaultListModel<String>();;
	
	private String initiator_name;
	private JTextField description_field;

	private static final String DP_THEME_CTX1 = "datePicker1";
	private DatePicker datePicker;

	private ActivityDepository depository;

	private static final long serialVersionUID = 234360639496126275L;

	public initiateMeetingFrame(String name, KernelWatcher kw) {

		// Init themes

		try {
			Themes.loadTheme("src/main/resources/themes/DatePicker1.tme",
					DP_THEME_CTX1, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.initiator_name = name;
		this.kw = kw;
		this.depository = ActivityDepository.getInstance(name);

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,
				getDefaultCloseOperation()));
		this.setSize(800, 300);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel listbox = new JPanel();
		listbox.setLayout(new BoxLayout(listbox, BoxLayout.LINE_AXIS));
		
		suggestedHoursList = new JList<String>(suggestedHoursListModel);
		selectedHoursList= new JList<String>(selectedHoursListModel);
		
		JPanel list_left = new JPanel();
		list_left.setLayout(new BoxLayout(list_left, BoxLayout.Y_AXIS));
		list_left.add(new JLabel("Suggested timeslots"));
		list_left.add(suggestedHoursList);
		
		JPanel list_right = new JPanel();
		list_right.setLayout(new BoxLayout(list_right, BoxLayout.Y_AXIS));
		list_right.add(new JLabel("Selected timeslots"));
		list_right.add(selectedHoursList);
		
		
		listbox.add(list_left);
		
		JButton toRight = new JButton("<<");
		listbox.add(toRight);
		toRight.setActionCommand("ADDLEFT");
		toRight.addActionListener(this);
		
		JButton toLeft = new JButton(">>");
		listbox.add(toLeft);
		toLeft.setActionCommand("ADDRIGHT");
		toLeft.addActionListener(this);
		
		listbox.add(list_right);
				
		description_field = new JTextField("Description");

		participantList = new JList(this.kw.getAllAgentExcept(name).toArray(new String[0]));
		JScrollPane scrollPaneParticipants = new JScrollPane(participantList);

		hoursList = new JList<ImmutableDateRange>();
		JScrollPane scrollPaneHours = new JScrollPane(hoursList);
		
		JPanel bottombox = new JPanel();
		bottombox.setLayout(new BoxLayout(bottombox, BoxLayout.Y_AXIS));
		
		JButton sendProposalButton = new JButton("Send meeting proposal");
		sendProposalButton.setActionCommand("SENDMEETING");
		sendProposalButton.addActionListener(this);

		bottombox.add(description_field);
		bottombox.add(sendProposalButton);

		panel.add(createDatePickerPanel(), BorderLayout.NORTH);
		panel.add(scrollPaneParticipants, BorderLayout.EAST);
		panel.add(listbox,BorderLayout.CENTER);
		panel.add(bottombox, BorderLayout.SOUTH);
		this.add(panel);
	}

	private JComponent createDatePickerPanel() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 5));
		panel.setBorder(new CompoundBorder(new TitledBorder(
				"Date Pickers that only differs by the Theme set"),
				new EmptyBorder(10, 10, 10, 10)));

		datePicker = new ThemeDatePicker(DP_THEME_CTX1);
		datePicker.setEditable(true);
		datePicker.getDateAreaContainer().getDateArea()
				.addDateChangeListener(this, false);

		panel.add(new JLabel("Day Select:"));
		panel.add(datePicker);
		panel.add(new JLabel("Number of hours of the meeting"));
		JSpinner hoursSpinner = new JSpinner();
		panel.add(hoursSpinner);
		JButton getRangeButton = new JButton("Get ranges");
		panel.add(getRangeButton);
		
		getRangeButton.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	refreshSlotList();	
                System.out.println("You clicked the button");
            }
        });

		panel.setOpaque(false);

		return panel;
	}
	
	public List<ImmutableDateRange> generateDateRange(DateRangeI s, int nbHours){
		ImmutableDateRange start = new ImmutableDateRange(s);
		//for(ImmutableDateRange i = start; i.getStartTime().getTime())
		return null;
		
	}
	
	public void refreshSlotList(){
		Date today = new Date();
		System.out.println(today.toString()+ " "+today.getDate());
		int range = 2;
		long hour = 3600 * 1000; 
		List<String> slots = new ArrayList();
		int max = today.getDate()+1;
		Date d = today;
		while(d.getDate()<max){
			Date addrange = new Date ();
			addrange.setTime(d.getTime());
			addrange.setTime(addrange.getTime()+range*hour);
			slots.add(""+d.getDate()+" "+d.getHours()+":"+d.getMinutes()+ " to "+addrange.getDate()+" "+addrange.getHours()+":"+addrange.getMinutes());
			d.setTime(d.getTime()+range*hour);
		}
		suggestedHoursList.removeAll();
		
		for (String s : slots) {
		   suggestedHoursListModel.addElement( s);
	   }
		
	}
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		if (cmd == "SENDMEETING") {
			List<String> participants = participantList.getSelectedValuesList();
			List<ImmutableDateRange> hours = hoursList.getSelectedValuesList();
			String description = description_field.getText();
			Meeting meeting = new Meeting(this.initiator_name, hours,
					description);
			this.kw.getChannel(this.initiator_name).createMeeting(meeting,
					this.kw.getAgentByNames(participants));
			this.dispose();
		}else if(cmd=="ADDRIGHT"){ // >>
			selectedHoursListModel.addElement(suggestedHoursList.getSelectedValue());
			suggestedHoursListModel.removeElement(suggestedHoursList.getSelectedValue());
			suggestedHoursList.setSelectedIndex(0);
		}else if(cmd=="ADDLEFT"){ // <<
			suggestedHoursListModel.addElement(selectedHoursList.getSelectedValue());
			selectedHoursListModel.removeElement(selectedHoursList.getSelectedValue());
			selectedHoursList.setSelectedIndex(0);
		}
	}

	@Override
	public void dateRangeChanged(DateChangeEvent e) {
		ImmutableDateRange range = null;

		if (e.getType() == DateChangeEvent.PRESSED) {
			range = e.getNewRange();
		} else if (e.getType() == DateChangeEvent.SELECTED) {
			range = e.getNewRange();
		}

		if (range == null) {
			return;
		}

		DefaultListModel<ImmutableDateRange> data = new DefaultListModel<ImmutableDateRange>();

		ActivityList existingActivites = depository.getActivities();

		@SuppressWarnings("unchecked")
		Iterator<ImmutableDateRange> x = range.iterator(
				DateRange.RANGE_TYPE_HOUR, 2);
		while (x.hasNext()) {
			ImmutableDateRange date = x.next();
			if (!existingActivites
					.hasOverlapping(date.getDateRangeForReading())) {
				data.addElement(date);
			}
		}
		hoursList.removeAll();
		hoursList.setModel(data);
	}

}
