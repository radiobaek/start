package net.opensg.tcs.multiedit.actions;

import net.opensg.tcs.main.model.TcsContact;
import net.opensg.tcs.main.model.TcsContactGroup;
import net.opensg.tcs.multiedit.Activator;
import net.opensg.tcs.multiedit.views.ContEditor;
import net.opensg.tcs.multiedit.views.ContEditorInput;

import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class GlobalCommand {

	/**
	 * ContEditor 실행
	 * @param group : Group
	 */
	public static ContEditor RunActivateContEditor(TcsContactGroup group) {
		return RunActivateContEditor(group, null);
	}

	/**
	 * ContEditor 실행 (Contact 지정)
	 * @param group : Group
	 * @param contact : Contact
	 */
	public static ContEditor RunActivateContEditor(TcsContactGroup group, TcsContact contact) {
		// 해당 그룹의 Editor가 이미 EditorReference에 있으면 Activate시키고 없으면 생성한다
		if (group == null) return null;
		ContEditor contEditor = null;
		IEditorReference existingEditorRef = null;
		IEditorReference[] editorRefList = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for (IEditorReference ref : editorRefList) {
			try {
				if (((ContEditorInput)ref.getEditorInput()).getCurrentGroup().Name == group.Name) {
					existingEditorRef = ref;
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
		if (existingEditorRef == null) {
			// 신규 Editor 생성
			IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				ContEditorInput input = new ContEditorInput();
				input.setCurrentGroup(group);
				input.setCurrentContact(contact);
				contEditor = (ContEditor)page.openEditor(input, ContEditor.ID);
				contEditor.BindData(input.getCurrentGroup());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			// 기존 Editor 변경
			IWorkbenchPage page = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
			contEditor = (ContEditor)existingEditorRef.getPart(true);
			page.bringToTop(contEditor);
		}
		if (contact != null) {
			contEditor.setFocusToContact(contact);
		}
		return contEditor;
	}
}
