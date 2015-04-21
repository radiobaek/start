package net.opensg.tcs.main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;

public class TcsContactGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String Name;
	public String Description;
	public String Memo;
	
	public ArrayList<TcsContact> ContactList;
	
	public String toString() {
		if (this.Name == null) return "";
		return this.Name;
	}

	public List<TreeItemInfo> ChildTreeBindingItemList() {
		List<TreeItemInfo> childList = new ArrayList<TreeItemInfo>();
		if (this.ContactList != null) {
			for (TcsContact con : this.ContactList) {
				childList.add(new TreeItemInfo(con.Name, con, this));
			}
		}
		return childList;
	}
	
}
