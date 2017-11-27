package com.hp.gef.sqlmxvqp.commandhandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.hp.gef.sqlmxvqp.sqlmxeditor.SqlMxQueryEditor;

public class GenerateExplain extends AbstractHandler {

  static String QueryText;
  static int ViewId = 1;

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // TODO Auto-generated method stub
    try {
      IEditorPart part;

      part = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
          .getActivePage().getActiveEditor();
      if (part instanceof SqlMxQueryEditor) {
        SqlMxQueryEditor editor = (SqlMxQueryEditor) part;
        ITextSelection sel = (ITextSelection) editor.getSelectionProvider()
            .getSelection();
        if (!sel.isEmpty() && sel.getText() != null
            && !sel.getText().equals("")) {
          QueryText = sel.getText();

          IViewPart partView = PlatformUI.getWorkbench()
              .getActiveWorkbenchWindow().getActivePage()
              .findView("com.hp.gef.sqlmxvqp.views.SampleView");

          if (PlatformUI.getWorkbench().getActiveWorkbenchWindow()
              .getActivePage().isPartVisible(partView)) {
            IWorkbenchPage page = PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage();
            // page.hideView(page.findView("com.hp.gef.sqlmxvqp.views.SampleView"));
            page.showView(
                "com.hp.gef.sqlmxvqp.views.SampleView",
                "com.hp.gef.sqlmxvqp.views.SampleView"
                    + Integer.toString(ViewId++), page.VIEW_ACTIVATE);
            // page.activate(partView);
          } else {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getActivePage()
                .showView("com.hp.gef.sqlmxvqp.views.SampleView");
          }
        }
      }

    } catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
  }

  public static String getQueryText() {
    return QueryText;
  }

}
