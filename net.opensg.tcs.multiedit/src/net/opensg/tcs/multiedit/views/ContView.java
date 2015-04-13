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

		tableViewer.setContentProvider(new ContViewContentProvider());
		tableViewer.setLabelProvider(new ContViewLabelProvider());

	}

	@Override
	public void setFocus() {
	}
	
	public void BindData(Object data) {
		tableViewer.setInput(data);
	}
	
	
	public static ContView GetCurrentContView() {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IViewPart contViewPart = page.findView(ContView.ID);
		return (ContView)contViewPart;
	}

}
