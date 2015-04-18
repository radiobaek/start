package net.opensg.tcs.multiedit.views;

import java.util.List;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;
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

		List<TcsContactGroup> groupList = null;
		try {
			groupList = (List<TcsContactGroup>)parent;
		} catch (Exception ex) {
		}
		if (groupList != null) {
			return groupList.toArray();
		}

		TcsContactGroup group = null;
		try {
			group = (TcsContactGroup)parent;
		} catch (Exception ex) {
		}
		if (group != null) {
			return group.ContactList.toArray();
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
