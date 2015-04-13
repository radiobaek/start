package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.main.model.TcsContact;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class ContEditorLabelProvider extends LabelProvider implements ITableLabelProvider {
	public String getColumnText(Object obj, int index) {
		String result = "";
		switch (index) {
		case 0:
			if (obj instanceof TcsContact) {
				result = ((TcsContact)obj).Name;
			}
			break;
		case 1:
			if (obj instanceof TcsContact) {
				result = ((TcsContact)obj).Email;
			}
			break;
		case 2:
			if (obj instanceof TcsContact) {
				result = ((TcsContact)obj).PhoneMobile;
			}
			break;
		case 3:
			if (obj instanceof TcsContact) {
				result = ((TcsContact)obj).Address;
			}
			break;
		}
		return result; 
	}

	public Image getColumnImage(Object obj, int index) {
		return getImage(obj);
	}

	public Image getImage(Object obj) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}
}

