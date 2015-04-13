package net.opensg.tcs.multiedit.views;

import java.util.List;

import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ContEditorContentProvider implements IStructuredContentProvider {
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		if (parent == null) return null;

		// TODO Type Conversion ���� ��� ã��
		List<TcsContactGroup> group = null;
		try {
			group = (List<TcsContactGroup>)parent;
		} catch (Exception ex) {
		}
		if (group != null) {
			return group.toArray();
		}

		List<TcsContact> contact = null;
		try {
			contact = (List<TcsContact>)parent;
		} catch (Exception ex) {
		}
		if (contact != null) {
			return contact.toArray();
		}
		
		return new Object[0];
	}
}
