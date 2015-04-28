package net.opensg.tcs.multiedit.app;

import java.util.Arrays;
import java.util.List;

import net.opensg.tcs.main.application.Activator;
import net.opensg.tcs.main.preference.PreferenceConstants;
import net.opensg.tcs.multiedit.actions.ClearTableViewerAction;
import net.opensg.tcs.multiedit.actions.FileOpenAction;
import net.opensg.tcs.multiedit.actions.FileSaveAction;
import net.opensg.tcs.multiedit.actions.PreferenceDialogAction;
import net.opensg.tcs.multiedit.actions.SamplePopupAction;
import net.opensg.tcs.multiedit.util.GeneralUtil;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.ozsoft.secs4j.gui.TestTool;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public static IActionBarConfigurer CurrentActionConfig;

	public MenuManager mainMenu_General;
	public MenuManager mainMenu_TableViewer;
	public MenuManager mainMenu_Secs;

	public SamplePopupAction samplePopupAction;
	public PreferenceDialogAction prefDialogAction;
	public ClearTableViewerAction mainMenuAction_ClearTableViewer;

	private Action fileOpenAction;
	private Action fileSaveAction;
	private Action TableViewer_BindingOption_Normal;	
	private Action tableViewer_BindingOption_OwnerDraw;	
	private Action TableViewer_BindingOption_ToolTip;	
	private Action TableViewer_BindingOption_ShowHideColumn;	
	private Action TableViewer_BindingOption_ComboCellEditor;	
	private Action TableViewer_BindingOption_CellEditorPerRow;	
	private Action Secs_Server;	
	
	private ControlContribution comboCI;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
		CurrentActionConfig = configurer;
	}

	protected void makeActions(IWorkbenchWindow window) {
		samplePopupAction = new SamplePopupAction(window);
		register(samplePopupAction);
		prefDialogAction = new PreferenceDialogAction();
		register(prefDialogAction);
		mainMenuAction_ClearTableViewer = new ClearTableViewerAction();
		register(mainMenuAction_ClearTableViewer);

		fileOpenAction = new FileOpenAction(window, "Read from File", "icons/open.gif");
		register(fileOpenAction);
		fileSaveAction = new FileSaveAction(window, "Save to File", "icons/save.gif");
		register(fileSaveAction);

		comboCI = new ControlContribution("control2") {
			protected Control createControl(Composite parent) {
				Combo c = new Combo(parent, SWT.READ_ONLY);
				c.add("one");
				c.add("two");
				c.add("three");
				return c;
			}
		};
		
		TableViewer_BindingOption_Normal = new Action("BindingOption : Normal") {
			@Override
			public void run() {
				GeneralUtil.setPreferenceString(
						PreferenceConstants.KEY_TableViewerBindingType,
						PreferenceConstants.VAL_TableViewerBindingType_Normal);
				super.run();
			}
		};
		tableViewer_BindingOption_OwnerDraw = new Action("BindingOption : OwnerDraw") {
			@Override
			public void run() {
				GeneralUtil
						.setPreferenceString(
								PreferenceConstants.KEY_TableViewerBindingType,
								PreferenceConstants.VAL_TableViewerBindingType_OwnerDraw);
				super.run();
			}
		};
		TableViewer_BindingOption_ToolTip = new Action("BindingOption : ToolTip Column") {
			@Override
			public void run() {
				GeneralUtil
						.setPreferenceString(
								PreferenceConstants.KEY_TableViewerBindingType,
								PreferenceConstants.VAL_TableViewerBindingType_ToolTipColumn);
				super.run();
			}
		};
		TableViewer_BindingOption_ShowHideColumn = new Action("BindingOption : ShowHide Column") {
			@Override
			public void run() {
				GeneralUtil
						.setPreferenceString(
								PreferenceConstants.KEY_TableViewerBindingType,
								PreferenceConstants.VAL_TableViewerBindingType_ShowHideColumn);
				super.run();
			}
		};
		TableViewer_BindingOption_ComboCellEditor = new Action("BindingOption : Combo CellEditor", IAction.AS_CHECK_BOX) {
			@Override
			public void runWithEvent(Event event) {
				boolean checked_ComboCellEditor = GeneralUtil
						.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor);
				GeneralUtil
						.setPreferenceBool(
								PreferenceConstants.KEY_TableViewerOption_ComboCellEditor,
								!checked_ComboCellEditor);
				super.runWithEvent(event);
			}
		};
		TableViewer_BindingOption_CellEditorPerRow = new Action("BindingOption : CellEditor Per Row", IAction.AS_CHECK_BOX) {
			@Override
			public void runWithEvent(Event event) {
				boolean checked_CellEditorPerRow = GeneralUtil
						.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow);
				GeneralUtil
						.setPreferenceBool(
								PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow,
								!checked_CellEditorPerRow);
				super.runWithEvent(event);
			}
		};
		Secs_Server = new Action("Start Server") {
			@Override
			public void run() {
				TestTool tool = new TestTool();
				super.run();
			}
		};
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		// MenuBar - General
		mainMenu_General = new MenuManager("General", "General");

		mainMenu_General.add(fileOpenAction);
		mainMenu_General.add(fileSaveAction);

		mainMenu_General.add(new Separator());
		mainMenu_General.add(samplePopupAction);
		mainMenu_General.add(prefDialogAction);
		menuBar.add(mainMenu_General);

		// MenuBar - TableViewer Options
		mainMenu_TableViewer = new MenuManager("TableViewer", "TableViewer");
		mainMenu_TableViewer.add(mainMenuAction_ClearTableViewer);
		mainMenu_TableViewer.add(new Separator());

		mainMenu_TableViewer.add(TableViewer_BindingOption_Normal);
		mainMenu_TableViewer.add(tableViewer_BindingOption_OwnerDraw);
		mainMenu_TableViewer.add(TableViewer_BindingOption_ToolTip);
		mainMenu_TableViewer.add(TableViewer_BindingOption_ShowHideColumn);
		mainMenu_TableViewer.add(new Separator());

		boolean checked_ComboCellEditor = GeneralUtil
				.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_ComboCellEditor);
		TableViewer_BindingOption_ComboCellEditor
				.setChecked(checked_ComboCellEditor);
		mainMenu_TableViewer.add(TableViewer_BindingOption_ComboCellEditor);
		boolean checked_CellEditorPerRow = GeneralUtil
				.getPreferenceBool(PreferenceConstants.KEY_TableViewerOption_CellEditorPerRow);
		TableViewer_BindingOption_CellEditorPerRow
				.setChecked(checked_CellEditorPerRow);
		mainMenu_TableViewer.add(TableViewer_BindingOption_CellEditorPerRow);

		menuBar.add(mainMenu_TableViewer);

		// MenuBar - Secs
		mainMenu_Secs = new MenuManager("Secs", "Secs");
		mainMenu_Secs.add(Secs_Server);

		menuBar.add(mainMenu_Secs);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		IToolBarManager toolbar = new ToolBarManager(coolBar.getStyle() | SWT.RIGHT);
		coolBar.add(toolbar);
		ActionContributionItem aci;

		aci = new ActionContributionItem(fileOpenAction);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);                        
        toolbar.add(aci);
		aci = new ActionContributionItem(fileSaveAction);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);                        
        toolbar.add(aci);
        toolbar.add(new Separator());
		aci = new ActionContributionItem(samplePopupAction);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);                        
        toolbar.add(aci);
		aci = new ActionContributionItem(prefDialogAction);
		aci.setMode(ActionContributionItem.MODE_FORCE_TEXT);                        
        toolbar.add(aci);
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
