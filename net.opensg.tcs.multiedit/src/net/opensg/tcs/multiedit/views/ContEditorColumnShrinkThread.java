package net.opensg.tcs.multiedit.views;

import org.eclipse.swt.widgets.TableColumn;

public class ContEditorColumnShrinkThread extends Thread {
	private int width = 0;
	private TableColumn column;

	public ContEditorColumnShrinkThread(int width, TableColumn column) {
		super();
		this.width = width;
		this.column = column;
	}

	@Override
	public void run() {
		column.getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				column.setData("restoredWidth", new Integer(width));
			}
		});

		for( int i = width; i >= 0; i-- ) {
			final int index = i;
			column.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					column.setWidth(index);
				}

			});
		}
	}
};
