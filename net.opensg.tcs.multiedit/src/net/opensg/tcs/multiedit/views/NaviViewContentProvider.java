package net.opensg.tcs.multiedit.views;

import java.util.List;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class NaviViewContentProvider implements ITreeContentProvider {

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}
		else if (inputElement instanceof List) {
			return ((List)inputElement).toArray();
		}
		return new Object[0];
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof TreeItemInfo) {
			return ((TreeItemInfo)parentElement).Children.toArray();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		if (element instanceof TreeItemInfo) {
			return ((TreeItemInfo)element).Parent;
		}
		return null;
	}

	public boolean hasChildren(Object element) {
		if (element instanceof TreeItemInfo) {
			if (((TreeItemInfo)element).Children == null) return false;
			return (((TreeItemInfo)element).Children.size() > 0);
		}
		return false;
	}

}
