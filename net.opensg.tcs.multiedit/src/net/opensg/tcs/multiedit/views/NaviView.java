package net.opensg.tcs.multiedit.views;

import java.util.ArrayList;
import java.util.List;

import net.opensg.tcs.commons.libs.core.TcsCommon;
import net.opensg.tcs.commons.libs.core.TreeItemInfo;
import net.opensg.tcs.main.action.ClearTableViewerAction;
import net.opensg.tcs.main.application.Activator;
import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;
import net.opensg.tcs.main.model.sample.TcsAddressDataModel;
import net.opensg.tcs.main.preference.PreferenceConstants;
import net.opensg.tcs.multiedit.command.GlobalCommand;
import net.opensg.tcs.multiedit.io.ContRepository;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
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

		ContRepository.modelData = TreeViewerBindingData();
		treeViewer.setInput(ContRepository.modelData);

		// Selection service 지정
		getSite().setSelectionProvider(treeViewer);
		
		// Listener 지정 - TreeViewer Selection
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
						// 현재 선택된 Item 타입에 따른 처리
						Object domainItem = domainInfo.Item;
						TcsContactGroup currentGroup = null;
						TcsContact currentContact = null;
						if (domainItem instanceof TcsContactGroup) {
							// 선택된 Item이 Group인 경우
							currentGroup = (TcsContactGroup)domainItem;
							GlobalCommand.RunActivateContEditor(currentGroup);
						} 
						else if (domainItem instanceof TcsContact) {
							// 선택된 Item이 Contact인 경우
							currentContact = (TcsContact)domainItem;
							currentGroup = (TcsContactGroup)domainInfo.Parent;
							GlobalCommand.RunActivateContEditor(currentGroup, currentContact);
						}
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
		
		// Listener 지정 - Preference Change (TableViewerBindingType)
		Activator.getDefault().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty() == PreferenceConstants.KEY_TableViewerBindingType) {
					(new ClearTableViewerAction()).run();
				}
			}
		});


	}

	public void setFocus() {
	}

	private List<TreeItemInfo> TreeViewerBindingData() {
		List<TreeItemInfo> resultList = new ArrayList<TreeItemInfo>();
		List<TcsContactGroup> SampleDataList = TcsAddressDataModel
				.BuildSampleContactGroupList();
		for (TcsContactGroup item : SampleDataList) {
			TreeItemInfo itemInfo = new TreeItemInfo(item.Name, item, null, item.ChildTreeBindingItemList());
			resultList.add(itemInfo);
		}
		return resultList;
	}
}
