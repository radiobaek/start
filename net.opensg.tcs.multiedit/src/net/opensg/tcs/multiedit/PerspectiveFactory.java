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
		
		// Selection service -> ContView stack
//		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(new ISelectionListener() {
//			@Override
//			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//				TcsCommon.ConsoleOut(String.format("**SelectionService"));
//				// NaviView Selection  
//				if (part instanceof NaviView) {
//					TreeItemInfo selectionInfo = NaviView.SelectedTreeItemInfo(selection);
//					if (selectionInfo != null) {
//						if (selectionInfo.Item != null) {
//							// ContactGroup
//							if (selectionInfo.Item instanceof TcsContactGroup) {
//								IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//								try {
//									ContEditorInput input = new ContEditorInput();
//									input.ContactGroup = (TcsContactGroup)selectionInfo.Item;
//									page.openEditor(input, ContEditor.ID);
//								} catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
//						}
//					}
//				}
//			}
//		});
	}
}