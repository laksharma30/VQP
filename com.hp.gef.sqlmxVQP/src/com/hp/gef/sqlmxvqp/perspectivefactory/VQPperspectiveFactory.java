package com.hp.gef.sqlmxvqp.perspectivefactory;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class VQPperspectiveFactory implements IPerspectiveFactory {

  @Override
  public void createInitialLayout(IPageLayout layout) {
    String editorArea = layout.getEditorArea();

    
    IFolderLayout left =
            layout.createFolder("left", IPageLayout.LEFT, (float) 0.26, editorArea);
    left.addView("org.eclipse.jdt.ui.PackageExplorer");
    layout.createPlaceholderFolder("centre", IPageLayout.TOP   , (float)0.7 , editorArea).addPlaceholder("com.hp.gef.sqlmxvqp.views.SampleView");
    layout.createFolder("right", IPageLayout.BOTTOM  , (float) 0.26 , editorArea).addView("  org.eclipse.ui.views.PropertySheet");
    layout.setEditorAreaVisible(false);
    
    //left.addView(IPageLayout.ID_OUTLINE);
   

  }

}
