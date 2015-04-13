package net.opensg.tcs.main.selection;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

public class NaviViewSelectionListener implements ISelectionListener {

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
//		if (part instanceof NaviView) {
//			TreeItemInfo selectionInfo = NaviView.SelectedTreeItemInfo(selection);
//			ContView contView = ContView.GetCurrentContView(ContView.this.getSite().getPage());
//			if (selectionInfo != null) {
//				if (selectionInfo.Item != null) {
//					// ContactGroup 선택한 경우
//					if ((contView != null) && (selectionInfo.Item instanceof TcsContactGroup)) { 
//						contView.tableViewer.setInput(((TcsContactGroup)selectionInfo.Item).ContactList);
//					}
//					// Contact 선택한 경우
//					else if (selectionInfo.Item instanceof TcsContact) {
//						contView.tableViewer.setInput(((TcsContactGroup)selectionInfo.Parent).ContactList);
//						contView.tableViewer.setSelection(new StructuredSelection(selectionInfo.Item));
//					}
//				}
//			}
//		}
	}

}
