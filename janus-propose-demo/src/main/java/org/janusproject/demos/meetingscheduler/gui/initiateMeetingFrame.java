package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
import javax.swing.JTextField;
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
import com.miginfocom.util.dates.ImmutableDateRange;

public class initiateMeetingFrame extends JFrame implements ActionListener,
		DateChangeListener {

	private KernelWatcher kw;

	private JList<String> participantList;
	private JList<ImmutableDateRange> hoursList;
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

		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		description_field = new JTextField("Description");

		participantList = new JList<String>(this.kw.getAllAgentExcept(name)
				.toArray(new String[0]));
		JScrollPane scrollPaneParticipants = new JScrollPane(participantList);

		hoursList = new JList<ImmutableDateRange>();
		JScrollPane scrollPaneHours = new JScrollPane(hoursList);

		JButton sendProposalButton = new JButton("Send meeting proposal");
		sendProposalButton.setActionCommand("SENDMEETING");
		sendProposalButton.addActionListener(this);

		box.add(scrollPaneHours);
		box.add(description_field);

		panel.add(createDatePickerPanel(), BorderLayout.NORTH);
		panel.add(scrollPaneParticipants, BorderLayout.EAST);
		panel.add(box, BorderLayout.CENTER);
		panel.add(sendProposalButton, BorderLayout.SOUTH);
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

		panel.setOpaque(false);

		return panel;
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
		}
	}

	@Override
	public void dateRangeChanged(DateChangeEvent e) {
		if (e.getType() == DateChangeEvent.PRESSED) {
			ImmutableDateRange range = e.getNewRange();

			DefaultListModel<ImmutableDateRange> data = new DefaultListModel<ImmutableDateRange>();

			ActivityList existingActivites = depository.getActivities();

			@SuppressWarnings("unchecked")
			Iterator<ImmutableDateRange> x = range.iterator(
					DateRange.RANGE_TYPE_HOUR, 2);
			while (x.hasNext()) {
				ImmutableDateRange date = x.next();
				if (!existingActivites.hasOverlapping(date.getDateRangeForReading())) {
					data.addElement(date);
				}
			}
			hoursList.removeAll();
			hoursList.setModel(data);
		}
	}

}
