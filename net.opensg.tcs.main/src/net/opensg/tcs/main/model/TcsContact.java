package net.opensg.tcs.main.model;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;

public class TcsContact {
	public String Name;
	public String PhoneMobile;
	public String PhoneOffice;
	public String Email;
	public String Address;
	public String Depart;
	
	public String toString() {
		if (this.Name == null) return "";
		return this.Name;
	}
}
