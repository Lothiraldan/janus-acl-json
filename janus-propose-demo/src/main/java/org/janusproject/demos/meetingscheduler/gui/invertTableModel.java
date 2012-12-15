package org.janusproject.demos.meetingscheduler.gui;

import org.janusproject.demos.meetingscheduler.ontology.Calendar;

public class invertTableModel extends myTableModel{

	public invertTableModel(Calendar c) {
		super(c);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 935165394682823501L;
	
	@Override
	public Object getValueAt(int row, int column) {
		if (column == 0) {
			return String.format("%s-%s", row + 7, row + 8);
		}
		Object valueCell = this.c.get(row, column - 1) ;
		if( valueCell instanceof Boolean ){
			return !((Boolean) valueCell);
		}
		return this.c.get(row, column - 1);
	}

}
