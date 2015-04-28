package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.multiedit.Activator;
import net.opensg.tcs.multiedit.io.ContRepository;
import net.opensg.tcs.multiedit.util.GeneralUtil;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class FileOpenAction extends Action implements IWorkbenchAction {

	protected final IWorkbenchWindow window;
	public static String ID = "net.opensg.tcs.multiedit.FileOpenAction";
	
	public FileOpenAction(IWorkbenchWindow window, String text, String imagePath) {
		this.window = window;
		setId(ID);
		setText(text);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, imagePath));
	}
	
	@Override
	public void run() {
		try {
			FileDialog fileDialog = new FileDialog(
					GeneralUtil.getCurrentShell(), SWT.OPEN);
			fileDialog.setText("Select Data File");
			// fileDialog.setFilterPath("C:/");
			String[] filterExt = { "*.bin", "*.*" };
			fileDialog.setFilterExtensions(filterExt);
			String filePath = fileDialog.open();
			if (filePath != "") {
				ContRepository.getInstance().readData(filePath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}

	@Override
	public void dispose() {
	}
}
