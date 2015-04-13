package net.opensg.tcs.main.model;

import java.util.ArrayList;
import java.util.List;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;

public class TcsContactGroup {
	public String Name;
	public String Description;
	public String Memo;
	
	public ArrayList<TcsContact> ContactList;
	
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
