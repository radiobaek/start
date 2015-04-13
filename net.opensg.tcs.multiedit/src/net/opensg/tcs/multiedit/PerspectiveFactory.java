package net.opensg.tcs.multiedit;

import net.opensg.tcs.commons.libs.core.TcsCommon;
import net.opensg.tcs.commons.libs.core.TreeItemInfo;
import net.opensg.tcs.main.model.TcsContactGroup;
import net.opensg.tcs.multiedit.views.ContEditor;
import net.opensg.tcs.multiedit.views.ContEditorInput;
import net.opensg.tcs.multiedit.views.ContView;
import net.opensg.tcs.multiedit.views.NaviView;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class PerspectiveFactory implements IPerspectiveFactory {

	public static String ID = "net.opensg.tcs.multiedit.PerspectiveFactory";
	private int ContViewInstanceCount = 0;
	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addView(net.opensg.tcs.multiedit.views.NaviView.ID, IPageLayout.LEFT, 0.25f, editorArea);

		//layout.addView(net.opensg.tcs.multiedit.views.ContView.ID, IPageLayout.RIGHT, 0.35f, net.opensg.tcs.multiedit.views.NaviView.ID);

		layout.setEditorAreaVisible(true);
		layout.setFixed(true);
		
		
		// ��ũ��ġ Selection service -> ContView stack �߰��ϱ�
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				TcsCommon.ConsoleOut(String.format("**SelectionService"));
				// NaviView�� Selection�� �޴´�  
				if (part instanceof NaviView) {
					TreeItemInfo selectionInfo = NaviView.SelectedTreeItemInfo(selection);
					if (selectionInfo != null) {
						if (selectionInfo.Item != null) {
							// ContactGroup ������ ���
							if (selectionInfo.Item instanceof TcsContactGroup) {
								IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
								try {
									ContEditorInput input = new ContEditorInput();
									input.ContactGroup = (TcsContactGroup)selectionInfo.Item;
									page.openEditor(input, ContEditor.ID);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
		});
	}
}