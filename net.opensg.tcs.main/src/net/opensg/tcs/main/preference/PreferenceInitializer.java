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
		store.setDefault(PreferenceConstants.KEY_AddEditorToNewTab, true);
		store.setDefault(PreferenceConstants.KEY_SamplePopupField1Name, "Field1:");
		store.setDefault(PreferenceConstants.KEY_SamplePopupField2Name, "Field2:");
		
		store.setDefault(PreferenceConstants.KEY_TableViewerBindingType, PreferenceConstants.VAL_TableViewerBindingType_Normal);
		
		store.setDefault(PreferenceConstants.KEY_TableViewerOption_DoubleClickEditing, false);
		store.setDefault(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor, false);
		store.setDefault(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow, false);
	}
}
