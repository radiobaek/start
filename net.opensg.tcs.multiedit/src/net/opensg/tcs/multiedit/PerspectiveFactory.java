package net.opensg.tcs.multiedit;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactory implements IPerspectiveFactory {

	public static String ID = "net.opensg.tcs.multiedit.PerspectiveFactory";
	private int ContViewInstanceCount = 0;
	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addView(net.opensg.tcs.multiedit.views.NaviView.ID, IPageLayout.LEFT, 0.25f, editorArea);

		layout.setEditorAreaVisible(true);
		layout.setFixed(true);
	}
}