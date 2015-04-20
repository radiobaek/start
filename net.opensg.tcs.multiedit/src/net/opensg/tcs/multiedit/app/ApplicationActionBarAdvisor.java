package net.opensg.tcs.multiedit.app;

import net.opensg.tcs.main.action.ClearTableViewerAction;
import net.opensg.tcs.main.action.PreferenceDialogAction;
import net.opensg.tcs.main.action.SamplePopupAction;
import net.opensg.tcs.main.application.Activator;
import net.opensg.tcs.main.preference.PreferenceConstants;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public static IActionBarConfigurer CurrentActionConfig;

	public SamplePopupAction samplePopupAction;
	public PreferenceDialogAction prefDialogAction;

	public MenuManager mainMenu_File;

	public MenuManager mainMenu_TableViewer;
	public ClearTableViewerAction mainMenuAction_ClearTableViewer;
	
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
		CurrentActionConfig = configurer;
    }

	protected void makeActions(IWorkbenchWindow window) {
		samplePopupAction = new SamplePopupAction();
		register(samplePopupAction);
		prefDialogAction = new PreferenceDialogAction();
		register(prefDialogAction);
		
		mainMenuAction_ClearTableViewer = new ClearTableViewerAction();
		register(mainMenuAction_ClearTableViewer);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		mainMenu_File = new MenuManager("File", "File");
		mainMenu_File.add(samplePopupAction);
		mainMenu_File.add(prefDialogAction);
		menuBar.add(mainMenu_File);
		
		mainMenu_TableViewer = new MenuManager("TableViewer", "TableViewer");
		mainMenu_TableViewer.add(mainMenuAction_ClearTableViewer);
		menuBar.add(mainMenu_TableViewer);
		
	}

	
	
	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
		super.fillStatusLine(statusLine);

		// TODO - Preference Changed StatusBar
		Activator.getDefault().getPreferenceStore()
				.addPropertyChangeListener(new IPropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent event) {
						IPreferenceStore pref = Activator.getDefault().getPreferenceStore();
						boolean vAddToNewTab = pref.getBoolean(PreferenceConstants.PREF_AddToNewTab);
						String v1 = (vAddToNewTab) ? "True" : "False";
						String v2 = pref.getString(PreferenceConstants.PREF_SamplePopupField1Name);
						String v3 = pref.getString(PreferenceConstants.PREF_SamplePopupField2Name);
						String displayText = String.format("Preference : v1=%s, v2=%s, v3=%s", v1, v2, v3);
						CurrentActionConfig.getStatusLineManager().setMessage(displayText);
					}
				});
	}
}
