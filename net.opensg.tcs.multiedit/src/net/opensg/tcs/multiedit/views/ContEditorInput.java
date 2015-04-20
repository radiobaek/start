package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class ContEditorInput implements IEditorInput {

	private TcsContactGroup currentGroup = null;
	public TcsContactGroup getCurrentGroup() {
		return this.currentGroup;
	}
	public void setCurrentGroup(TcsContactGroup group) {
		this.currentGroup = group;
	}
	
	private TcsContact currentContact = null;
	public TcsContact getCurrentContact() {
		return currentContact;
	}
	public void setCurrentContact(TcsContact contact) {
		this.currentContact = contact;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return null;
	}

}
