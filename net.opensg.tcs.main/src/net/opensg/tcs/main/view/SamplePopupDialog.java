package net.opensg.tcs.main.view;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;

public class SamplePopupDialog extends Dialog {

	private Text txtField1;
	private Text txtField2;
	private String valueField1 = "";
	private String valueField2 = "";

	public SamplePopupDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 5;
		layout.marginLeft = 10;
		container.setLayout(layout);

		Label lblField1 = new Label(container, SWT.NONE);
		lblField1.setText("Field1:");

		txtField1 = new Text(container, SWT.BORDER);
		txtField1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		txtField1.setText(valueField1);

		Label lblField2 = new Label(container, SWT.NONE);
		GridData gd_lblField2 = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblField2.horizontalIndent = 1;
		lblField2.setLayoutData(gd_lblField2);
		lblField2.setText("Field2:");

		txtField2 = new Text(container, SWT.BORDER);
		txtField2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		txtField2.setText(valueField2);
		
		Group grpSomethingTogether = new Group(container, SWT.NONE);
		grpSomethingTogether.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		grpSomethingTogether.setText("Something together");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		return container;
	}

}
