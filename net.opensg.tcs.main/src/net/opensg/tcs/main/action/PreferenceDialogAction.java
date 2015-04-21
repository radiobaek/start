package net.opensg.tcs.main.action;

import net.opensg.tcs.main.preference.GeneralOptionsPage;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

public class PreferenceDialogAction extends Action {

	public static String ID = "PreferenceDialogAction";
	private Shell shell;

	public PreferenceDialogAction() {
		shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		this.setId(ID);
		this.setText("Preference Dialog");
	}

	@Override
	public void run() {
		PreferenceDialog pref = PreferencesUtil.createPreferenceDialogOn(shell, GeneralOptionsPage.ID, null, null);
		if (pref != null)
			pref.open();

		super.run();
	}

}