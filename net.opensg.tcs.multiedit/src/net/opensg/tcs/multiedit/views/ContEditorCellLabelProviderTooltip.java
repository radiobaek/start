package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.main.model.TcsContact;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Point;

public class ContEditorCellLabelProviderTooltip extends CellLabelProvider {

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

	@Override
	public String getToolTipText(Object element) {
		return "Tooltip..";
	}

	@Override
	public Point getToolTipShift(Object object) {
		return new Point(5, 5);
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		return 500;
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		return 3000;
	}
}
