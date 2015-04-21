package net.opensg.tcs.multiedit.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

import net.opensg.tcs.commons.libs.core.TreeItemInfo;
import net.opensg.tcs.main.action.ClearTableViewerAction;
import net.opensg.tcs.main.action.PreferenceDialogAction;
import net.opensg.tcs.main.action.SamplePopupAction;
import net.opensg.tcs.main.application.Activator;
import net.opensg.tcs.main.preference.PreferenceConstants;
import net.opensg.tcs.multiedit.io.ContRepository;
import net.opensg.tcs.multiedit.util.GeneralUtil;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private static String filePath = "C:\\test.bin";
	
	public static IActionBarConfigurer CurrentActionConfig;

	public SamplePopupAction samplePopupAction;
	public PreferenceDialogAction prefDialogAction;

	public MenuManager mainMenu_General;

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
		// MenuBar - General
		mainMenu_General = new MenuManager("General", "General");
		
		Action File_Open = new Action("Read from File") {
			@Override
			public void run() {
				try {
					FileInputStream fis = new FileInputStream(filePath);
					ObjectInputStream ois = new ObjectInputStream(fis);
					ContRepository.modelData = (List<TreeItemInfo>)ois.readObject();
					ois.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				super.run();
			}
		};
		mainMenu_General.add(File_Open);
		
		Action File_Save = new Action("Write to File") {
			@Override
			public void run() {
				FileOutputStream fos;
				try {
					fos = new FileOutputStream(filePath);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(ContRepository.modelData);
					oos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				super.run();
			}
		};
		mainMenu_General.add(File_Save);
		
		mainMenu_General.add(new Separator());
		mainMenu_General.add(samplePopupAction);
		mainMenu_General.add(prefDialogAction);
		menuBar.add(mainMenu_General);

		// MenuBar - TableViewer Options
		mainMenu_TableViewer = new MenuManager("TableViewer", "TableViewer");
		mainMenu_TableViewer.add(mainMenuAction_ClearTableViewer);
		mainMenu_TableViewer.add(new Separator());

		Action TableViewer_BindingOption_Normal = new Action("BindingOption : Normal") {
			@Override
			public void run() {
				GeneralUtil.setPreferenceString(PreferenceConstants.KEY_TableViewerBindingType, PreferenceConstants.VAL_TableViewerBindingType_Normal);
				super.run();
			}
		};
		mainMenu_TableViewer.add(TableViewer_BindingOption_Normal);

		Action tableViewer_BindingOption_OwnerDraw = new Action("BindingOption : OwnerDraw") {
			@Override
			public void run() {
				GeneralUtil.setPreferenceString(PreferenceConstants.KEY_TableViewerBindingType, PreferenceConstants.VAL_TableViewerBindingType_OwnerDraw);
				super.run();
			}
		};
		mainMenu_TableViewer.add(tableViewer_BindingOption_OwnerDraw);
		
		Action TableViewer_BindingOption_ToolTip = new Action("BindingOption : ToolTip Column") {
			@Override
			public void run() {
				GeneralUtil.setPreferenceString(PreferenceConstants.KEY_TableViewerBindingType, PreferenceConstants.VAL_TableViewerBindingType_ToolTipColumn);
				super.run();
			}
		};
		mainMenu_TableViewer.add(TableViewer_BindingOption_ToolTip);

		Action TableViewer_BindingOption_ShowHideColumn = new Action("BindingOption : ShowHide Column") {
			@Override
			public void run() {
				GeneralUtil.setPreferenceString(PreferenceConstants.KEY_TableViewerBindingType, PreferenceConstants.VAL_TableViewerBindingType_ShowHideColumn);
				super.run();
			}
		};
		mainMenu_TableViewer.add(TableViewer_BindingOption_ShowHideColumn);

		mainMenu_TableViewer.add(new Separator());
		
		Action TableViewer_BindingOption_ComboCellEditor = new Action("BindingOption : Combo CellEditor", SWT.CHECK) {
			@Override
			public void runWithEvent(Event event) {
				boolean checked_ComboCellEditor = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor);
				GeneralUtil.setPreferenceBool(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor, !checked_ComboCellEditor);
				super.runWithEvent(event);
			}
		};
		boolean checked_ComboCellEditor = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor);
		TableViewer_BindingOption_ComboCellEditor.setChecked(checked_ComboCellEditor);
		mainMenu_TableViewer.add(TableViewer_BindingOption_ComboCellEditor);
		
		Action TableViewer_BindingOption_CellEditorPerRow = new Action("BindingOption : CellEditor Per Row", SWT.CHECK) {
			@Override
			public void runWithEvent(Event event) {
				boolean checked_CellEditorPerRow = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow);
				GeneralUtil.setPreferenceBool(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow, !checked_CellEditorPerRow);
				super.runWithEvent(event);
			}
		};
		boolean checked_CellEditorPerRow = GeneralUtil.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow);
		TableViewer_BindingOption_CellEditorPerRow.setChecked(checked_CellEditorPerRow);
		mainMenu_TableViewer.add(TableViewer_BindingOption_CellEditorPerRow);
		
		menuBar.add(mainMenu_TableViewer);
	}

	@Override
	protected void fillStatusLine(IStatusLineManager statusLine) {
		super.fillStatusLine(statusLine);

		// Preference 변경사항 Listener -> Status Text 변경
		Activator.getDefault().getPreferenceStore()
				.addPropertyChangeListener(new IPropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent event) {
						List<String> props = Arrays
								.asList(new String[] {
										PreferenceConstants.KEY_AddEditorToNewTab,
										PreferenceConstants.KEY_SamplePopupField1Name,
										PreferenceConstants.KEY_SamplePopupField2Name, });
						if (props.contains(event.getProperty())) {
							IPreferenceStore pref = Activator.getDefault()
									.getPreferenceStore();
							boolean vAddToNewTab = pref
									.getBoolean(PreferenceConstants.KEY_AddEditorToNewTab);
							String v1 = (vAddToNewTab) ? "True" : "False";
							String v2 = pref
									.getString(PreferenceConstants.KEY_SamplePopupField1Name);
							String v3 = pref
									.getString(PreferenceConstants.KEY_SamplePopupField2Name);
							String displayText = String.format(
									"Preference : v1=%s, v2=%s, v3=%s", v1, v2,
									v3);
							CurrentActionConfig.getStatusLineManager()
									.setMessage(displayText);
						}
					}
				});
	}
}
