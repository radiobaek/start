package net.opensg.tcs.multiedit.views;

import java.util.List;

import net.opensg.tcs.commons.libs.core.GeneralParam;
import net.opensg.tcs.commons.libs.core.TreeItemInfo;
import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;

import org.eclipse.jface.viewers.LabelProvider;

public class NaviViewLabelProvider extends LabelProvider {
	public String getText(Object element) {
		if (element instanceof TreeItemInfo) {
			Object item = ((TreeItemInfo)element).Item;
			if (item instanceof TcsContactGroup) {
				return String.format("[ContactGroup] %s", ((TcsContactGroup)item).Name);
			}
			else if (item instanceof TcsContact) {
				return String.format("[Contact] %s", ((TcsContact)item).Name);
			}
			else if (item instanceof List) {
				return String.format("[%s] Items", ((TreeItemInfo)element).ItemName);
			}
			else if (item instanceof GeneralParam) {
				return String.format("[GeneralParam] %s", ((GeneralParam)item).ParamValue.toString());
			}
			else if (item instanceof String) {
				return (String)item;
			}
		}
		return "*** Invalid item: " + element.getClass();
	}
}
