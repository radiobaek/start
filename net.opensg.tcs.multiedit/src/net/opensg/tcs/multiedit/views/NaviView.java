package net.opensg.tcs.multiedit.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NaviView extends ViewPart {

	public static String ID = "net.opensg.tcs.multiedit.views.NaviView";
	
	private TreeViewer treeViewer;
	private Text txtFilePath;

	public NaviView() {
	}

	public void createPartControl(Composite parent) {
		GridLayout gl_parent = new GridLayout(1, false);
		gl_parent.verticalSpacing = 0;
		parent.setLayout(gl_parent);
		
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.verticalSpacing = 0;
		gl_composite.marginWidth = 0;
		gl_composite.marginHeight = 0;
		composite.setLayout(gl_composite);
		GridData gd_composite = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_composite.heightHint = 30;
		composite.setLayoutData(gd_composite);
		
		CLabel lblFilePath = new CLabel(composite, SWT.NONE);
		lblFilePath.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, true, 1, 1));
		lblFilePath.setText("File Path:");
		
		txtFilePath = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		txtFilePath.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtFilePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Tree tree = new Tree(parent, SWT.BORDER);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		treeViewer = new TreeViewer(tree);

		treeViewer.setContentProvider(new NaviViewContentProvider());
		treeViewer.setLabelProvider(new NaviViewLabelProvider());

		ContRepository.getInstance().setModelData(TreeViewerBindingData());
		treeViewer.setInput(ContRepository.getInstance().getModelData());

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

		// Data Observer 지정
		ContRepository.getInstance().addObserver(new Observer() {
			@Override
			public void update(Observable arg0, Object arg1) {
				String changeReason = (String)arg1;
				switch (changeReason) {
				case ContRepository.dataChangeReason_ModelData:
					NaviView.this.bindData_TreeViewer();
					break;
				case ContRepository.dataChangeReason_FilePath:
					NaviView.this.bindData_FilePath();
					break;
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
	
	
	
	public void bindData_TreeViewer() {
		System.out.println("NaviView bindData");
		this.treeViewer.setInput(ContRepository.getInstance().getModelData());
	}
	public void bindData_FilePath() {
		this.txtFilePath.setText(ContRepository.getInstance().getModelFilePath());
	}
	
}
