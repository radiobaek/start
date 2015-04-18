package net.opensg.tcs.main.preference;

import net.opensg.tcs.main.application.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.PlatformUI;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		//IPreferenceStore store = PlatformUI.getPreferenceStore();
		store.setDefault(PreferenceConstants.PREF_AddToNewTab, true);
		store.setDefault(PreferenceConstants.PREF_SamplePopupField1Name, "Field1:");
		store.setDefault(PreferenceConstants.PREF_SamplePopupField2Name, "Field2:");
	}
}
