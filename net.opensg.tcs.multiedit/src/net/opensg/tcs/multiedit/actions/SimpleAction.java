package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.multiedit.Activator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * 사용하지 않음
 * @author opensg
 *
 */
public class SimpleAction extends Action implements ISelectionListener,
		IWorkbenchAction {

	protected final IWorkbenchWindow window;
	
	public SimpleAction(IWorkbenchWindow window, String id, String text, String imagePath) {
		this.window = window;
		setId(id);
		setText(text);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, imagePath));
		window.getSelectionService().addSelectionListener(this);
	}
	
	public SimpleAction(IWorkbenchWindow window, String id, String text, String imagePath, boolean useCheck) {
		super(text, (useCheck ? AS_PUSH_BUTTON : AS_CHECK_BOX));
		this.window = window;
		setId(id);
		setText(text);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, imagePath));
		window.getSelectionService().addSelectionListener(this);
	}
	
	@Override
	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
	}
}
