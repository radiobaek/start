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
import org.eclipse.jface.preference.RadioGroupFieldEditor;

public class GeneralOptionsPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public static String ID = "net.opensg.tcs.main.preference.GeneralPage";

	private BooleanFieldEditor editorAddToNewTab;
	private StringFieldEditor editorSamplePopupField1Name;
	private StringFieldEditor editorSamplePopupField2Name;
	private ComboFieldEditor editorSamplePopupType;
	private ColorFieldEditor editorSamplePopupBackColor;
	
	public GeneralOptionsPage() {
		super(GRID);
		setTitle("General Options");
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("General Options");
	}

	public GeneralOptionsPage(int style) {
		super(style);
	}

	public GeneralOptionsPage(String title, int style) {
		super(title, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		editorAddToNewTab = new BooleanFieldEditor(
				PreferenceConstants.KEY_AddEditorToNewTab, "Add To New Tab",
				getFieldEditorParent());
		addField(editorAddToNewTab);

		editorSamplePopupField1Name = new StringFieldEditor(
				PreferenceConstants.KEY_SamplePopupField1Name,
				"SamplePopup Field1 Caption", -1,
				StringFieldEditor.VALIDATE_ON_KEY_STROKE,
				getFieldEditorParent());
		addField(editorSamplePopupField1Name);

		editorSamplePopupField2Name = new StringFieldEditor(
				PreferenceConstants.KEY_SamplePopupField2Name,
				"SamplePopup Field2 Caption", -1,
				StringFieldEditor.VALIDATE_ON_KEY_STROKE,
				getFieldEditorParent());
		addField(editorSamplePopupField2Name);

		editorSamplePopupType = new ComboFieldEditor(
				PreferenceConstants.KEY_SamplePopupType, "SamplePopup Type",
				new String[][] { 
						{ "name_1", "value_1" },
						{ "name_2", "value_2" } }, 
				getFieldEditorParent());
		addField(editorSamplePopupType);

		editorSamplePopupBackColor = new ColorFieldEditor(
				PreferenceConstants.KEY_SamplePopupBackColor,
				"SamplePopup Background Color", getFieldEditorParent());
		addField(editorSamplePopupBackColor);
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
	}
}
