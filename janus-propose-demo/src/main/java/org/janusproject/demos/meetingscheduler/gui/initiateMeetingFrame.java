package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;
import org.janusproject.demos.meetingscheduler.role.MeetingChannel;
import org.janusproject.demos.meetingscheduler.util.KernelWatcher;

import com.miginfocom.calendar.DatePicker;
import com.miginfocom.calendar.ThemeDatePicker;
import com.miginfocom.theme.Themes;
import com.miginfocom.util.dates.DateChangeEvent;
import com.miginfocom.util.dates.DateChangeListener;

public class initiateMeetingFrame extends JFrame implements ActionListener,
		DateChangeListener {

	private MeetingChannel channel;
	private KernelWatcher kw;

	private JList participantList;
	private String initiator_name;
	private JTextField description_field;

	private static final String DP_THEME_CTX1 = "datePicker1";
	private DatePicker datePicker;

	private static final long serialVersionUID = 234360639496126275L;

	public initiateMeetingFrame(String name, MeetingChannel channel,
			KernelWatcher kw) {

		// Init themes

		try {
			Themes.loadTheme("src/main/resources/themes/DatePicker1.tme", DP_THEME_CTX1, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.initiator_name = name;
		this.channel = channel;
		this.kw = kw;

		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,
				getDefaultCloseOperation()));
		this.setSize(500, 300);
		this.setLocation(300, 400);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
		description_field = new JTextField("Description");

		participantList = new JList(this.kw.getAllAgentExcept(name).toArray());
		JScrollPane scrollPane = new JScrollPane(participantList);

		JButton sendProposalButton = new JButton("Send meeting proposal");
		sendProposalButton.setActionCommand("SENDMEETING");
		sendProposalButton.addActionListener(this);

		box.add(description_field);

		panel.add(createDatePickerPanel(), BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.EAST);
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
			int selections[] = participantList.getSelectedIndices();
			Object selectionValues[] = participantList.getSelectedValues();
			List<String> partList = new ArrayList<String>();
			if (selections != null) {
				for (int i = 0, n = selections.length; i < n; i++) {
					partList.add((String) selectionValues[i]);
				}
				this.dispose();

			}

		}

	}

	@Override
	public void dateRangeChanged(DateChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

}
