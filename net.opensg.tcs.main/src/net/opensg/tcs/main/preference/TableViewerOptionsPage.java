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

public class TableViewerOptionsPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public static String ID = "net.opensg.tcs.main.preference.TableViewerOptionsPage";

	private RadioGroupFieldEditor tableViewerBindingType;

	private BooleanFieldEditor tableViewerOption_DoubleClickEditing;
	private BooleanFieldEditor tableViewerOption_ComboCellEditor;
	private BooleanFieldEditor tableViewerOption_CellEditorPerRow;

	/**
	 * @wbp.parser.constructor
	 */
	public TableViewerOptionsPage() {
		super(GRID);
		setTitle("TableViewer Options");
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription("TableViewer Options");
	}

	public TableViewerOptionsPage(int style) {
		super(style);
	}

	public TableViewerOptionsPage(String title, int style) {
		super(title, style);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		// TableViewer Binding Type
		tableViewerBindingType = new RadioGroupFieldEditor(
			PreferenceConstants.KEY_TableViewerBindingType, 
			"TableViewer Binding Type", 
			1, 
			new String[][] {
					{"TableViewer Binding : Normal LabelProvider", PreferenceConstants.VAL_TableViewerBindingType_Normal}, 
					{"TableViewer Binding : OwnerDraw LabelProvider", PreferenceConstants.VAL_TableViewerBindingType_OwnerDraw}, 
					{"TableViewer Binding : ToolTip Column", PreferenceConstants.VAL_TableViewerBindingType_ToolTipColumn}, 
					{"TableViewer Binding : ShowHide Column", PreferenceConstants.VAL_TableViewerBindingType_ShowHideColumn}, 
			}, 
			getFieldEditorParent(), 
			false);
		addField(tableViewerBindingType);
		
		// TableViewer Options - Use DoubleClick Editing
		tableViewerOption_DoubleClickEditing = new BooleanFieldEditor(
			PreferenceConstants.KEY_TableViewerOption_DoubleClickEditing, 
			"Use TableViewer DoubleClick Editing", 
			BooleanFieldEditor.DEFAULT, 
			getFieldEditorParent()
		);
		addField(tableViewerOption_DoubleClickEditing);

		// TableViewer Options - Use TableViewer Combo CellEditor
		tableViewerOption_ComboCellEditor = new BooleanFieldEditor(
			PreferenceConstants.KEY_TableViewerOption_ComboCellEditor, 
			"Use TableViewer Combo CellEditor", 
			BooleanFieldEditor.DEFAULT, 
			getFieldEditorParent()
		);
		addField(tableViewerOption_ComboCellEditor);

		// TableViewer Options - Use TableViewer CellEditor Per Row
		tableViewerOption_CellEditorPerRow = new BooleanFieldEditor(
			PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow, 
			"Use TableViewer CellEditor Per Row", 
			BooleanFieldEditor.DEFAULT, 
			getFieldEditorParent()
		);
		addField(tableViewerOption_CellEditorPerRow);
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
	}
}
