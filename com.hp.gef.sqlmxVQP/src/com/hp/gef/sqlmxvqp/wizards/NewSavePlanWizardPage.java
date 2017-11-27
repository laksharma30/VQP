package com.hp.gef.sqlmxvqp.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

public class NewSavePlanWizardPage extends WizardNewFileCreationPage {

  public NewSavePlanWizardPage(IStructuredSelection selection) {
    super("NewSavePlanWizardPage", selection);
    setTitle("Save VQP");
    setDescription("Creates a new VQP file");
    setFileExtension("ser");
  }

}
