package org.janusproject.demos.meetingscheduler.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.TableView.TableCell;

public class calendarFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6091451186812076790L;

	public calendarFrame(){
		Container contentPane = this.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane, getDefaultCloseOperation()));
		this.setSize(500,600);
		this.setLocation(200,200);
		
		String[] columnNames = {"Horaires","Lundi",
                "Mardi",
                "Mercredi",
                "Jeudi",
                "Vendredi",
                "Samedi",
                "Dimanche"};
		Object[][] data = {
			    {"7-8", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"8-9", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"9-10", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"10-11", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"11-12", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"12-13", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"13-14", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"14-15", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"15-16", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"16-17", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"17-18", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"18-19", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			    {"19-20", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
			};
		
		JTable calendarTable = new JTable(data, columnNames);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel descLabel = new JLabel("Schedule off [AGENT_NAME]");
		
		TableModel model = new ColorTableModel();
	    JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(descLabel,BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		this.add(panel);
	}
	
	class ColorTableModel extends AbstractTableModel {

//		  Object rowData[][] = { { "1", Boolean.TRUE }, { "2", Boolean.TRUE }, { "3", Boolean.FALSE },
//		      { "4", Boolean.TRUE }, { "5", Boolean.FALSE }, };
//		  
		  Object rowData[][] = {
				    {"7-8", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,"kiki"},
				    {"8-9", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"9-10", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"10-11", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"11-12", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"12-13", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"13-14", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"14-15", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"15-16", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"16-17", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"17-18", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"18-19", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				    {"19-20", Boolean.FALSE,Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,Boolean.FALSE,Boolean.FALSE,""},
				};

		  String[] columnNames = {"Horaires","Lundi",
	                "Mardi",
	                "Mercredi",
	                "Jeudi",
	                "Vendredi",
	                "Samedi",
	                "Dimanche"};

		  public int getColumnCount() {
		    return columnNames.length;
		  }

		  public String getColumnName(int column) {
		    return columnNames[column];
		  }

		  public int getRowCount() {
		    return rowData.length;
		  }

		  public Object getValueAt(int row, int column) {
		    return rowData[row][column];
		  }

		  public Class getColumnClass(int column) {
		    return (getValueAt(0, column).getClass());
		  }

		  public void setValueAt(Object value, int row, int column) {
		    rowData[row][column] = value;
		  }

		  public boolean isCellEditable(int row, int column) {
		    return (column != 0);
		  }
		}
}
