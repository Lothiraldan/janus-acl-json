package org.janusproject.demos.meetingscheduler.gui;

import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class calendarFrame extends JFrame{
	public calendarFrame(){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, getDefaultCloseOperation()));
		this.setSize(400,500);
		this.setLocation(100,100);
		
		String[] columnNames = {"Horaires","Lundi",
                "Mardi",
                "Mercredi",
                "Jeudi",
                "Vendredi",
                "Samedi",
                "Dimanche"};
		Object[][] data = {
			    {"7-8", "","", "", "","","",""},
			    {"8-9", "","", "", "","","",""},
			    {"9-10", "","", "", "","","",""},
			    {"10-11", "","", "", "","","",""},
			    {"11-12", "","", "", "","","",""},
			    {"12-13", "","", "", "","","",""},
			    {"13-14", "","", "", "","","",""},
			    {"14-15", "","", "", "","","",""},
			    {"15-16", "","", "", "","","",""},
			    {"16-17", "","", "", "","","",""},
			    {"17-18", "","", "", "","","",""},
			    {"18-19", "","", "", "","","",""},
			    {"19-20", "","", "", "","","",""},
			};
	}
}
