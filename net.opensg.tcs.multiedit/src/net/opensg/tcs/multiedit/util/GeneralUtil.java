package net.opensg.tcs.multiedit.util;

import net.opensg.tcs.main.application.Activator;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Shell;

public class GeneralUtil {


	// Window, Shell
	public static Shell getCurrentShell() {
		return Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getShell();
	}
	
	// Preferences
	
	public static String getPreferenceString(String key) {
		IPreferenceStore pref = Activator.getDefault().getPreferenceStore();
		if (!pref.contains(key)) return "";
		return pref.getString(key);
	}
	
	public static void setPreferenceString(String key, String value) {
		IPreferenceStore pref = Activator.getDefault().getPreferenceStore();
		if (!pref.contains(key)) return;
		String oldValue = pref.getString(key);
		pref.setValue(key, value);
		pref.firePropertyChangeEvent(key, oldValue, value);
	}

	public static boolean getPreferenceBool(String key) {
		IPreferenceStore pref = Activator.getDefault().getPreferenceStore();
		if (!pref.contains(key)) return false;
		return pref.getBoolean(key);
	}
	
	public static void setPreferenceBool(String key, boolean value) {
		IPreferenceStore pref = Activator.getDefault().getPreferenceStore();
		if (!pref.contains(key)) return;
		boolean oldValue = pref.getBoolean(key);
		pref.setValue(key, value);
		pref.firePropertyChangeEvent(key, oldValue, value);
	}

}
