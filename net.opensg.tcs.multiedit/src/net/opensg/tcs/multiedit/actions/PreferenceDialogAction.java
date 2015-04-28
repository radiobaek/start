package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.main.application.Activator;
import net.opensg.tcs.main.preference.GeneralOptionsPage;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class PreferenceDialogAction extends Action implements IWorkbenchAction {

	public static String ID = "PreferenceDialogAction";
	private Shell shell;

	public PreferenceDialogAction() {
		this.shell = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
		this.setId(ID);
		this.setText("Preference Dialog");
        this.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/alt_window_16.gif"));
	}

	@Override
	public void run() {
		PreferenceDialog pref = PreferencesUtil.createPreferenceDialogOn(shell, GeneralOptionsPage.ID, null, null);
		if (pref != null)
			pref.open();

		super.run();
	}

	@Override
	public void dispose() {
	}

}