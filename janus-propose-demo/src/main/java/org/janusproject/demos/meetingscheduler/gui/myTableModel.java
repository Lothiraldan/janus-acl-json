package org.janusproject.demos.meetingscheduler.gui;

import javax.swing.table.AbstractTableModel;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;

class myTableModel extends AbstractTableModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4210233142405691105L;

	String[] columnNames = { "Horaires", "Lundi", "Mardi", "Mercredi",
			"Jeudi", "Vendredi", "Samedi", "Dimanche" };

	private Calendar c;

	public myTableModel(Calendar c) {
		this.c = c;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public int getRowCount() {
		return this.c.row_size();
	}

	public Object getValueAt(int row, int column) {
		if (column == 0) {
			return String.format("%s-%s", row + 7, row + 8);
		}
		return this.c.get(row, column - 1);
	}

	public Class getColumnClass(int column) {
		return (getValueAt(0, column).getClass());
	}

	public void setValueAt(Object value, int row, int column) {
		this.c.set(row, column - 1, value);
	}

	public boolean isCellEditable(int row, int column) {
		return (column != 0);
	}
}
