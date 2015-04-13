package net.opensg.tcs.multiedit.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.basic.BasicTreeUI.TreeIncrementAction;

import net.opensg.tcs.commons.libs.core.TcsCommon;
import net.opensg.tcs.commons.libs.core.TreeItemInfo;
import net.opensg.tcs.main.model.TcsContactGroup;
import net.opensg.tcs.main.model.sample.TcsAddressDataModel;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

public class NaviView extends ViewPart {

	public static String ID = "net.opensg.tcs.multiedit.views.NaviView";
	private TreeViewer treeViewer;

	public NaviView() {
	}

	public void createPartControl(Composite parent) {
		Tree tree = new Tree(parent, SWT.BORDER);
		treeViewer = new TreeViewer(tree);

		treeViewer.setContentProvider(new NaviViewContentProvider());
		treeViewer.setLabelProvider(new NaviViewLabelProvider());

		Object[] SampleData = TreeViewerBindingData();
		treeViewer.setInput(SampleData);

		// Selection service
		getSite().setSelectionProvider(treeViewer);
		
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection().isEmpty()) {
					return;
				}
				if (event.getSelection() instanceof IStructuredSelection) {
					Object domain = ((IStructuredSelection)event.getSelection()).getFirstElement();
					if (domain instanceof TreeItemInfo) {
						TreeItemInfo domainInfo = (TreeItemInfo)domain;
						TcsCommon.ConsoleOut(String.format("SelectionChanged - %s", domainInfo.ItemName));
					}
				}
			}
		});

		treeViewer.addTreeListener(new ITreeViewerListener() {
			@Override
			public void treeExpanded(TreeExpansionEvent event) {
			}

			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
			}
		});
	}

	public void setFocus() {
	}

	private Object[] TreeViewerBindingData() {
		List<TreeItemInfo> resultList = new ArrayList<TreeItemInfo>();
		List<TcsContactGroup> SampleDataList = TcsAddressDataModel
				.BuildSampleContactGroupList();
		for (TcsContactGroup item : SampleDataList) {
			TreeItemInfo itemInfo = new TreeItemInfo(item.Name, item, null, item.ChildTreeBindingItemList());
			resultList.add(itemInfo);
		}
		return resultList.toArray();
	}
}
