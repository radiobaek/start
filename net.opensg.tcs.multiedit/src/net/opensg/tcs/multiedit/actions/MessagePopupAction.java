package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.main.application.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class MessagePopupAction extends Action implements ISelectionListener, IWorkbenchAction {
	public static String ID = "net.opensg.tcs.multiedit.MessagePopupAction";
	private final IWorkbenchWindow window;
	public MessagePopupAction(IWorkbenchWindow window, String text) {
		this.window = window;
		setId(ID);
		//setActionDefinitionId(ID);
		setText(text);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/alt_window_16.gif"));
		window.getSelectionService().addSelectionListener(this);
	}

	public void run() {
		MessageDialog.openInformation(window.getShell(), "Open", "Open Message Dialog!");
	}

	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	}
}
