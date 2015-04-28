package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.main.application.Activator;
import net.opensg.tcs.main.view.SamplePopupDialog;
import net.opensg.tcs.multiedit.views.ContEditor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class SamplePopupAction extends Action implements ISelectionListener, IWorkbenchAction {

	public static String ID = "SamplePopupAction";
	private final IWorkbenchWindow window;

	public SamplePopupAction(IWorkbenchWindow window) {
		this.window = window;
		this.setId(ID);
		this.setText("Sample Popup");
        setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/alt_window_16.gif"));
        window.getSelectionService().addSelectionListener(this);
	}

	@Override
	public void run() {
		SamplePopupDialog dialog = new SamplePopupDialog(this.window.getShell());
		dialog.open();
		super.run();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection incoming) {
		if (incoming.isEmpty()) {
			setEnabled(false);
			return;
		}
		setEnabled(true);
	}

	@Override
	public void dispose() {
        window.getSelectionService().removeSelectionListener(this);
	}

}


