package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.main.application.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

public class ClearTableViewerAction extends Action {

	protected String ID = "ClearTableViewerAction";
	private Shell shell;

	public ClearTableViewerAction() {
		this.shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
		this.setId(ID);
		this.setText("Clear TableViewer");
	}

	@Override
	public void run() {
		IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorReference[] editors = page.getEditorReferences();
		page.closeEditors(editors, false);
		super.run();
	}

}
