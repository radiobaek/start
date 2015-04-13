package net.opensg.tcs.main.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TableEditingSupport extends EditingSupport {

	private ComboBoxViewerCellEditor cellEditor = null;

	public TableEditingSupport(TableViewerColumn tableColumn) {
		super(tableColumn.getViewer());
		cellEditor = new ComboBoxViewerCellEditor((Composite) getViewer()
				.getControl(), SWT.READ_ONLY);
		cellEditor.setLabelProvider(new LabelProvider());
		cellEditor.setContentProvider(new ArrayContentProvider());
		String[] comboValues = new String[] { "A", "B", "C" };
		cellEditor.setInput(comboValues);
	}

	@Override
	protected boolean canEdit(Object arg0) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object arg0) {
		return cellEditor;
	}

	@Override
	protected Object getValue(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	protected Object getValue(Object element) {
//		if (element instanceof TestCase) {
//			TestCase data = (TestCase) element;
//			return data.ID;
//		}
//		return null;
//	}
//
//	@Override
//	protected void setValue(Object element, Object value) {
//		if (element instanceof TestCase && value instanceof String) {
//			TestCase data = (TestCase) element;
//			String newValue = (String) value;
//			if (!data.ID.equals(newValue)) {
//				data.ID = newValue;
//			}
//		}
//	}

}
