package net.opensg.tcs.multiedit.views;

import org.eclipse.swt.widgets.TableColumn;

public class ContEditorColumnExpandThread extends Thread {
	private int width = 0;
	private TableColumn column;

	public ContEditorColumnExpandThread(int width, TableColumn column) {
		super();
		this.width = width;
		this.column = column;
	}

	@Override
	public void run() {
		for( int i = 0; i <= width; i++ ) {
			final int index = i;
			column.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					column.setWidth(index);
				}

			});
		}
	}
}

