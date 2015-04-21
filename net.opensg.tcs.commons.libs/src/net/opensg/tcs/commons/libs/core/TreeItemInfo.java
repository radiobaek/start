package net.opensg.tcs.commons.libs.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreeItemInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String ItemName = "";
    public Object Item = null;
    public Object Parent = null;
    public List<TreeItemInfo> Children = null;
    
    public TreeItemInfo(String itemName, Object item, Object parent) {
        this.ItemName = itemName;
        this.Item = item;
        this.Parent = parent;
    }

    public TreeItemInfo(String itemName, Object item, Object parent, List<TreeItemInfo> children) {
        this.ItemName = itemName;
        this.Item = item;
        this.Parent = parent;
        this.Children = children;
    }

    public String toString() {
    	if (this.Item == null) return "";
    	return Item.toString();
    }
   
    public static List<TreeItemInfo> ListItems(List<?> items) {
        List<TreeItemInfo> resultList = new ArrayList<TreeItemInfo>();
        for (Object item : items) {
            resultList.add(new TreeItemInfo("", item, items));
        }
        return resultList;
    }
}
