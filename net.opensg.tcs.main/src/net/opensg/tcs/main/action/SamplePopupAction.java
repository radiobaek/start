package net.opensg.tcs.main.action;

import net.opensg.tcs.main.view.SamplePopupDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class SamplePopupAction extends Action {

	public static String ID = "SamplePopupAction";
	private Shell shell;

	public SamplePopupAction() {
		shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		this.setId(ID);
		this.setText("Sample Popup");
	}

	@Override
	public void run() {
		// MessageDialog.openInformation(null, "Information",
		// "SamplePopupAction");

		SamplePopupDialog dialog = new SamplePopupDialog(shell);
		dialog.open();

		super.run();
	}

}

// public class SamplePopupAction extends Action implements ISelectionListener,
// IWorkbenchAction {
//
// private IWorkbenchWindow window;
// public static String ID = "SamplePopupAction";
//
// public SamplePopupAction(IWorkbenchWindow window) {
// this.window = window;
// setId(ID);
// }
//
// @Override
// public void run() {
// MessageDialog.openInformation(null, "Information", "SamplePopupAction");
// super.run();
// }
//
// @Override
// public void selectionChanged(IWorkbenchPart part, ISelection selection) {
// }
//
// @Override
// public void dispose() {
// }
// }

