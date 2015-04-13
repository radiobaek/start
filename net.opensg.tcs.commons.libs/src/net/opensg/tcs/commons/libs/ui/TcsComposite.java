package net.opensg.tcs.commons.libs.ui;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public class TcsComposite extends Composite {

	Color backColor = null;
	
	public TcsComposite(Composite parent, int style) {
		super(parent, style);
		
		RGB parentRgb = this.getBackground().getRGB();
		RGB childRgb = new RGB(parentRgb.red - 5, parentRgb.green - 5, parentRgb.blue - 5);
		Color childColor = new Color(Display.getCurrent(), childRgb);
		this.setBackground(childColor);
		childColor.dispose();
	}

	
}
