package com.hp.gef.sqlmxvqp.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import com.hp.gef.sqlmxvqp.views.SampleView;

public class SavePlanWizard extends Wizard implements INewWizard {

  private IStructuredSelection selection;
  private NewSavePlanWizardPage newFileWizardPage;
  private IWorkbench workbench;

  public SavePlanWizard() {
    setWindowTitle("New VQP File");
    // TODO Auto-generated constructor stub
  }

  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    // TODO Auto-generated method stub
    this.selection = selection;
    this.workbench = workbench;

  }

  @Override
  public void addPages() {

    newFileWizardPage = new NewSavePlanWizardPage(selection);
    addPage(newFileWizardPage);
  }

  @Override
  public boolean performFinish() {

    IFile file = newFileWizardPage.createNewFile();

    try {
      // @SuppressWarnings("unused")
      // SampleView view = (SampleView)
      SampleView view = (SampleView) PlatformUI.getWorkbench()
          .getActiveWorkbenchWindow().getActivePage()
          .showView("com.hp.gef.sqlmxvqp.views.SampleView");
      // view.setFile(file);
      view.setFile(file);
      return true;
    } catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
    // TODO Auto-generated method stub

  }

}
