package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.main.model.TcsContact;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

public class ContEditorCellLabelProvider extends CellLabelProvider {

	@Override
	public void update(ViewerCell cell) {
		int index = cell.getColumnIndex();
		String cellText = "";
		switch (index) {
		case 0:
			if (cell.getElement() instanceof TcsContact) {
				cellText = ((TcsContact)cell.getElement()).Name;
			}
			break;
		case 1:
			if (cell.getElement() instanceof TcsContact) {
				cellText = ((TcsContact)cell.getElement()).Email;
			}
			break;
		case 2:
			if (cell.getElement() instanceof TcsContact) {
				cellText = ((TcsContact)cell.getElement()).PhoneMobile;
			}
			break;
		case 3:
			if (cell.getElement() instanceof TcsContact) {
				cellText = ((TcsContact)cell.getElement()).Address;
			}
			break;
		}
		cell.setText(cellText);
		
	}
}