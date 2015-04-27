package net.opensg.tcs.multiedit.views;

import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.preference.PreferenceConstants;
import net.opensg.tcs.multiedit.Activator;
import net.opensg.tcs.multiedit.util.GeneralUtil;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.ToolTip;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class ContEditor extends EditorPart {

	public static final String ID = "net.opensg.tcs.multiedit.views.ContEditor"; //$NON-NLS-1$

	private Display display = null;
	private TableViewer tableViewer = null;
	private Table table = null;

	private TableViewerColumn viewerColName = null;
	private TableViewerColumn viewerColEmail = null;
	private TableViewerColumn viewerColPhone = null;
	private ContEditorCellEditingSupport columnEditingSupportName = null;
	private ContEditorCellEditingSupport columnEditingSupportEmail = null;
	private ContEditorCellEditingSupport columnEditingSupportPhone = null;

	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public static ContEditor getCurrentEditor() {
		IEditorPart editor = Activator.getDefault().getWorkbench()
				.getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		if (editor instanceof ContEditor) {
			return (ContEditor) editor;
		}
		return null;
	}

	public ContEditor() {
		super();
	}

	/**
	 * Create contents of the editor part.
	 * 
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {

		this.display = parent.getDisplay();
		parent.setLayout(new GridLayout(1, false));
		String TableViewerBindingType = GeneralUtil.getPreferenceString(PreferenceConstants.KEY_TableViewerBindingType);

		// (1) Create Table
		tableViewer = new TableViewer(parent, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// (2) Create Columns
		this.viewerColName = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblColName = viewerColName.getColumn();
		tblColName.setWidth(150);
		tblColName.setText("Name");

		this.viewerColEmail = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblColEmail = viewerColEmail.getColumn();
		tblColEmail.setWidth(150);
		tblColEmail.setText("E-Mail");

		this.viewerColPhone = new TableViewerColumn(tableViewer, SWT.NONE);
		TableColumn tblColPhone = viewerColPhone.getColumn();
		tblColPhone.setWidth(150);
		tblColPhone.setText("Phone");

		// Column Editing Support
		this.addColumnEditingSupport();
		// TableViewer Binding Common
		
		boolean useMouseListener = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_DoubleClickEditing);
		if (useMouseListener) {
			this.addMouseListener();
		}
		
		// TableViewer Binding Each Case
		switch (TableViewerBindingType) {
		case PreferenceConstants.VAL_TableViewerBindingType_Normal:
			// ContentProvider Setting
			tableViewer.setContentProvider(new ContEditorContentProvider());
			// LabelProvider Setting
			tableViewer.setLabelProvider(new ContEditorLabelProvider());
			// Column Editing Support
			this.addColumnEditingSupport();
			break;
		case PreferenceConstants.VAL_TableViewerBindingType_OwnerDraw:
			// ContentProvider Setting
			tableViewer.setContentProvider(new ContEditorContentProvider());
			// LabelProvider Setting
			tableViewer.setLabelProvider(new ContEditorLabelProviderDrawing());
			break;
		case PreferenceConstants.VAL_TableViewerBindingType_ToolTipColumn:
			// ContentProvider Setting
			tableViewer.setContentProvider(new ContEditorContentProvider());
			// LabelProvider Setting
			ColumnViewerToolTipSupport.enableFor(tableViewer,
					ToolTip.NO_RECREATE);
			viewerColName
					.setLabelProvider(new ContEditorCellLabelProviderTooltip());
			viewerColEmail
					.setLabelProvider(new ContEditorCellLabelProviderTooltip());
			viewerColPhone
					.setLabelProvider(new ContEditorCellLabelProviderTooltip());
			break;
		case PreferenceConstants.VAL_TableViewerBindingType_ShowHideColumn:
			// ContentProvider Setting
			tableViewer.setContentProvider(new ContEditorContentProvider());
			// LabelProvider Setting
			tableViewer.setLabelProvider(new ContEditorLabelProvider());
			this.addShowHideColumnMenu();
			break;
		default:
			break;
		}

	}

	public void BindData(Object data) {
		tableViewer.setInput(data);
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// Do the Save operation
	}

	@Override
	public void doSaveAs() {
		// Do the Save As operation
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		if (!(input instanceof ContEditorInput))
			throw new PartInitException(
					"ContEditor.init expects a ContEditorInput.  Actual input: "
							+ input);
		setSite(site);
		setInput(input);
		setPartName(this.getEditorPartName());
	}

	private String getEditorPartName() {
		return ((ContEditorInput) this.getEditorInput()).getCurrentGroup().Name;
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	/**
	 * Editor의 해당 Contact에 포커스 이동
	 * 
	 * @param contact
	 */
	public void setFocusToContact(TcsContact contact) {
		System.out.println("setFocusToContact " + contact.Name);
		TableItem item = this.getContactItem(contact.Name);
		if (item != null) {
			StructuredSelection selection = new StructuredSelection(
					item.getData());
			this.tableViewer.setSelection(selection);
		}
	}

	public TableItem getContactItem(String contactName) {
		for (TableItem item : this.table.getItems()) {
			if (item.getData() instanceof TcsContact) {
				TcsContact con = (TcsContact) item.getData();
				if (con.Name == contactName)
					return item;
			}
		}
		return null;
	}

	private void addShowHideColumnMenu() {
		final MenuManager mgr = new MenuManager();
		Action action;
		for (int i = 0; i < tableViewer.getTable().getColumnCount(); i++) {
			final TableColumn column = tableViewer.getTable().getColumn(i);
			action = new Action(tableViewer.getTable().getColumn(i).getText(), SWT.CHECK) {
				@Override
				public void runWithEvent(Event event) {
					if (!isChecked()) {
						ContEditorColumnShrinkThread thread = new ContEditorColumnShrinkThread(
								column.getWidth(), column);
						thread.run();
					} else {
						ContEditorColumnExpandThread thread = new ContEditorColumnExpandThread(
								((Integer) column.getData("restoredWidth"))
										.intValue(),
								column);
						thread.run();
					}
				}
			};
			action.setChecked(true);
			mgr.add(action);
		}
		tableViewer.getControl().setMenu(
				mgr.createContextMenu(tableViewer.getControl()));
	}

	private void addMouseListener() {
		table.addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				columnEditingSupportName.setEnabled(false);
				columnEditingSupportEmail.setEnabled(false);
				columnEditingSupportPhone.setEnabled(false);
			}
		});

		table.addListener(SWT.MouseDoubleClick, new Listener() {
			@Override
			public void handleEvent(Event event) {
				columnEditingSupportName.setEnabled(true);
				columnEditingSupportEmail.setEnabled(true);
				columnEditingSupportPhone.setEnabled(true);
				TableItem[] selection = table.getSelection();
				if (selection.length != 1) {
					return;
				}
				TableItem item = table.getSelection()[0];
				for (int i = 0; i < table.getColumnCount(); i++) {
					if (item.getBounds(i).contains(event.x, event.y)) {
						tableViewer.editElement(
								((StructuredSelection) tableViewer
										.getSelection()).getFirstElement(), i);
						columnEditingSupportName.setEnabled(false);
						columnEditingSupportEmail.setEnabled(false);
						columnEditingSupportPhone.setEnabled(false);
						break;
					}
				}
			}
		});
	}

	private void addColumnEditingSupport() {
		columnEditingSupportName = new ContEditorCellEditingSupport(tableViewer, viewerColName);
		columnEditingSupportEmail = new ContEditorCellEditingSupport(tableViewer, viewerColEmail);
		columnEditingSupportPhone = new ContEditorCellEditingSupport(tableViewer, viewerColPhone);
		// viewerColName.setEditingSupport(columnEditingSupportName);
		viewerColEmail.setEditingSupport(columnEditingSupportEmail);
		viewerColPhone.setEditingSupport(columnEditingSupportPhone);
	}

}

