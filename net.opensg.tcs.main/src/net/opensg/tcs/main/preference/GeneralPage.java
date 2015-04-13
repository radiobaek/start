package net.opensg.tcs.main.preference;

import net.opensg.tcs.main.application.Activator;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;

public class GeneralPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public static String ID = "net.opensg.tcs.main.preference.GeneralPage";

	private BooleanFieldEditor editorAddToNewTab;
	private StringFieldEditor editorSamplePopupField1Name;
	private StringFieldEditor editorSamplePopupField2Name;
	private ComboFieldEditor editorSamplePopupType;
	private ColorFieldEditor editorSamplePopupBackColor;

	public GeneralPage() {
		super(GRID);
		setTitle("TCS General");
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("General Preferences for TCS");
	}

	public GeneralPage(int style) {
		super(style);
	}

	public GeneralPage(String title, int style) {
		super(title, style);
		// TODO Auto-generated constructor stub
	}

	// public GeneralPage(String title, ImageDescriptor image, int style) {
	// super(title, image, style);
	// // TODO Auto-generated constructor stub
	// }

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		editorAddToNewTab = new BooleanFieldEditor(
				PreferenceConstants.PREF_AddToNewTab, "Add To New Tab",
				getFieldEditorParent());
		addField(editorAddToNewTab);

		editorSamplePopupField1Name = new StringFieldEditor(
				PreferenceConstants.PREF_SamplePopupField1Name,
				"SamplePopup Field1 Caption", -1,
				StringFieldEditor.VALIDATE_ON_KEY_STROKE,
				getFieldEditorParent());
		addField(editorSamplePopupField1Name);

		editorSamplePopupField2Name = new StringFieldEditor(
				PreferenceConstants.PREF_SamplePopupField2Name,
				"SamplePopup Field2 Caption", -1,
				StringFieldEditor.VALIDATE_ON_KEY_STROKE,
				getFieldEditorParent());
		addField(editorSamplePopupField2Name);

		editorSamplePopupType = new ComboFieldEditor(
				PreferenceConstants.PREF_SamplePopupType, "SamplePopup Type",
				new String[][] { { "name_1", "value_1" },
						{ "name_2", "value_2" } }, getFieldEditorParent());
		addField(editorSamplePopupType);

		editorSamplePopupBackColor = new ColorFieldEditor(
				PreferenceConstants.PREF_SamplePopupBackColor,
				"SamplePopup Background Color", getFieldEditorParent());
		addField(editorSamplePopupBackColor);
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
	}
}
