package net.opensg.tcs.multiedit.views;

import java.util.Arrays;

import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.preference.PreferenceConstants;
import net.opensg.tcs.multiedit.util.GeneralUtil;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class ContEditorCellEditingSupport extends EditingSupport {

	private TableViewer viewer = null;
	private TableViewerColumn column = null;
	private boolean enabled = true;
	
	public ContEditorCellEditingSupport(ColumnViewer viewer, TableViewerColumn viewerColumn) {
		super(viewer);
		this.viewer = (TableViewer)viewer;
		this.column = viewerColumn;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		String[] inputOdd = new String[] {
	    		"00001@opensg.net", 
	    		"00003@opensg.net", 
	    		"00005@opensg.net", 
		};
		String[] inputEven = new String[] {
	    		"00002@opensg.net", 
	    		"00004@opensg.net", 
	    		"00006@opensg.net", 
		};
		String[] inputNine = new String[] {
	    		"00001@opensg.net", 
	    		"00002@opensg.net", 
	    		"00003@opensg.net", 
	    		"00004@opensg.net", 
	    		"00005@opensg.net", 
	    		"00006@opensg.net", 
	    		"00007@opensg.net", 
	    		"00008@opensg.net", 
	    		"00009@opensg.net", 
		};
		String text = this.column.getColumn().getText();
		boolean option_ComboCellEditor = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor);
		boolean option_CellEditorPerRow = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow);
		if (option_ComboCellEditor) {
			switch (text) {
			case "E-Mail":
				ComboBoxViewerCellEditor cellEditor = new ComboBoxViewerCellEditor((Composite)viewer.getTable(), SWT.READ_ONLY);
				cellEditor.setContentProvider(new ArrayContentProvider());
				if (option_CellEditorPerRow) {
					String value = ((TcsContact)element).Email;
					switch (value) {
					case "00001@opensg.net":
					case "00003@opensg.net":
					case "00005@opensg.net":
				        cellEditor.setInput(inputOdd);
						break;
					case "00002@opensg.net":
					case "00004@opensg.net":
					case "00006@opensg.net":
				        cellEditor.setInput(inputEven);
						break;
					}
				} else {
			        cellEditor.setInput(inputNine);
				}
		        return cellEditor;
			default:
				break;
			}
		}
		return new TextCellEditor((Composite) getViewer().getControl());
	}

	@Override
	protected boolean canEdit(Object element) {
		return this.enabled;
	}

	@Override
	protected Object getValue(Object element) {
		String text = this.column.getColumn().getText();
		switch (text) {
		case "Name":
			return ((TcsContact) element).Name;
		case "E-Mail":
			return ((TcsContact) element).Email;
		case "Phone":
			return ((TcsContact) element).PhoneMobile;
		default:
			break;
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		String text = this.column.getColumn().getText();
		switch (text) {
		case "Name":
			((TcsContact) element).Name = value.toString();
			break;
		case "E-Mail":
			((TcsContact) element).Email = value.toString();
			break;
		case "Phone":
			((TcsContact) element).PhoneMobile = value.toString();
			break;
		default:
			break;
		}
		getViewer().update(element, null);
	}
	
}

