package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;
import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;
import net.opensg.tcs.multiedit.app.ApplicationActionBarAdvisor;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class ContView extends ViewPart {

	public static String ID = "net.opensg.tcs.multiedit.views.ContView";
	
	Display display = null;

	TableViewer tableViewer = null;
	private Table table;

	public ContView() {
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		this.display = parent.getDisplay();
		parent.setLayout(new GridLayout(1, false));

		// (1) Create Table
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		// (2) Create Columns
		TableViewerColumn viewerColName = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblColName = viewerColName.getColumn();
		tblColName.setWidth(150);
		tblColName.setText("Name");
		
		TableViewerColumn viewerColEmail = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblColEmail = viewerColEmail.getColumn();
		tblColEmail.setWidth(150);
		tblColEmail.setText("E-Mail");

		TableViewerColumn viewerColPhone = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblColPhone = viewerColPhone.getColumn();
		tblColPhone.setWidth(150);
		tblColPhone.setText("Phone");

		// *** 중요 : ContentProvider/LabelProvidor 설정은 컬럼설정등이 모두 된 후에 한다
		tableViewer.setContentProvider(new ContViewContentProvider());
		tableViewer.setLabelProvider(new ContViewLabelProvider());

		// 초기 바인딩
		
		
//		// 워크벤치 Selection service
//		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(new ISelectionListener() {
//			@Override
//			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//				System.out.println(String.format("**SelectionService"));
//				// NaviView의 Selection을 받는다  
//				if (part instanceof NaviView) {
//					TreeItemInfo selectionInfo = NaviView.SelectedTreeItemInfo(selection);
//					ContView contView = ContView.GetCurrentContView(ContView.this.getSite().getPage());
//					if (selectionInfo != null) {
//						if (selectionInfo.Item != null) {
//							// ContactGroup 선택한 경우
//							if ((contView != null) && (selectionInfo.Item instanceof TcsContactGroup)) { 
//								contView.tableViewer.setInput(((TcsContactGroup)selectionInfo.Item).ContactList);
//								// StatusBar 출력
//								ApplicationActionBarAdvisor.CurrentActionConfig.getStatusLineManager().setMessage("ContactGroup selected"); 								
//							}
//							// Contact 선택한 경우
//							else if (selectionInfo.Item instanceof TcsContact) {
//								contView.tableViewer.setInput(((TcsContactGroup)selectionInfo.Parent).ContactList);
//								contView.tableViewer.setSelection(new StructuredSelection(selectionInfo.Item));
//								// StatusBar 출력
//								ApplicationActionBarAdvisor.CurrentActionConfig.getStatusLineManager().setMessage("Contact selected"); 								
//							}
//						}
//					}
//				}
//			}
//		});
		

	}

	@Override
	public void setFocus() {
	}
	
	// TableViewer 바인딩 (외부 이벤트)
	public void BindData(Object data) {
		tableViewer.setInput(data);
	}
	
	
	public static ContView GetCurrentContView() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart contViewPart = page.findView(ContView.ID);
		return (ContView)contViewPart;
	}

}
