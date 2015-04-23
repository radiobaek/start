package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.main.model.TcsContact;

import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

public class ContEditorLabelProviderDrawing extends OwnerDrawLabelProvider  {

	@Override
	protected void measure(Event event, Object element) {
		if (!(element instanceof TcsContact)) return;
		TcsContact contact = (TcsContact)element;
		Point textExt = event.gc.textExtent(contact.Name);
		Rectangle textRect = new Rectangle(event.x, event.y, textExt.x , textExt.y + 10);
		event.setBounds(textRect);
	}


	@Override
	protected void paint(Event event, Object element) {
		if (!(element instanceof TcsContact)) return;
		TcsContact contact = (TcsContact)element;
		drawTableCell(contact, event);
	}


	// Draw Contact Cells
	public void drawTableCell(TcsContact contact, Event event) {
		switch (event.index) {
		case 0:
			drawTableCell_Name(contact, event);
			break;
		case 1:
			drawTableCell_Email(contact, event);
			break;
		case 2:
			drawTableCell_Phone(contact, event);
			break;

		default:
			break;
		}
	}
	
	public void drawTableCell_Name(TcsContact contact, Event event) {
		event.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_CYAN));
		Rectangle bounds = event.getBounds();
		//bounds.width += 100;
		event.gc.fillRectangle(bounds);
		event.gc.drawText( contact.Name, event.x, event.y);
	}
	public void drawTableCell_Email(TcsContact contact, Event event) {
		event.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_YELLOW));
		Rectangle bounds = event.getBounds();
		//bounds.width += 100;
		event.gc.fillRectangle(bounds);
		event.gc.drawText(contact.Email, event.x, event.y);
	}
	public void drawTableCell_Phone(TcsContact contact, Event event) {
		event.gc.setBackground(Display.getDefault().getSystemColor(SWT.COLOR_GRAY));
		Rectangle bounds = event.getBounds();
		//bounds.width += 100;
		event.gc.fillRectangle(bounds);
		event.gc.drawText(contact.PhoneMobile, event.x, event.y);
	}
	
//	public Rectangle getBounds() {
//		int width = 200;
//		int height = 30;
//		return new Rectangle(0, 0, width, height);
//	}
		
}
