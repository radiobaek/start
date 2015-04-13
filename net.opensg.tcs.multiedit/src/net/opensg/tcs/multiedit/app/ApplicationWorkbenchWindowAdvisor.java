package net.opensg.tcs.multiedit.app;

import net.opensg.tcs.commons.libs.core.TcsCommon;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowMenuBar(true);
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        
        String title = TcsCommon.APP_NAME + " - " + TcsCommon.LIB_VERSION + " - " + "Single View Application";
        configurer.setTitle(title); //$NON-NLS-1$
    }
}
