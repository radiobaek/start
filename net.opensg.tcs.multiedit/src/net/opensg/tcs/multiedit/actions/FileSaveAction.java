package net.opensg.tcs.multiedit.actions;

import java.beans.PropertyChangeListener;

import net.opensg.tcs.multiedit.Activator;
import net.opensg.tcs.multiedit.io.ContRepository;
import net.opensg.tcs.multiedit.util.GeneralUtil;

import org.eclipse.core.runtime.Preferences.IPropertyChangeListener;
import org.eclipse.core.runtime.Preferences.PropertyChangeEvent;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class FileSaveAction extends Action implements IWorkbenchAction, PropertyChangeListener {

	protected final IWorkbenchWindow window;
	public static String ID = "net.opensg.tcs.multiedit.FileSaveAction";

	public FileSaveAction(IWorkbenchWindow window, String text, String imagePath) {
		this.window = window;
		setId(ID);
		setText(text);
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, imagePath));
		setEnabled(false);
		ContRepository.getInstance().addPropertyChangeListener("dirty", this);
	}

	@Override
	public void run() {
		try {
			String filePath = ContRepository.getInstance().getModelFilePath();
			if (filePath == "") {
				FileDialog fileDialog = new FileDialog(
						GeneralUtil.getCurrentShell(), SWT.SAVE);
				fileDialog
						.setFilterNames(new String[] { "Data Files (*.bin)" });
				fileDialog.setFilterExtensions(new String[] { "*.bin" });
				fileDialog.setText("Select Data File to save");
				// fileDialog.setFilterPath(folder.getRawLocation().toString());
				filePath = fileDialog.open();
			}
			if (filePath != "") {
				ContRepository.getInstance().writeData(filePath);
				setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.run();
	}

	@Override
	public void dispose() {
		ContRepository.getInstance().removePropertyChangeListener("dirty", this);
	}

	@Override
	public void propertyChange(java.beans.PropertyChangeEvent event) {
		switch (event.getPropertyName()) {
		case "dirty":
			boolean newValue = false;
			try {
				newValue = (boolean)event.getNewValue();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			setEnabled(newValue);
			break;
		}
		
	}

}
