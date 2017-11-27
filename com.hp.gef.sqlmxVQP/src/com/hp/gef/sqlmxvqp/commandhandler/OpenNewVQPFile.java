package com.hp.gef.sqlmxvqp.commandhandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

import com.hp.gef.sqlmxvqp.dialogbox.PasswordDialog;
import com.hp.gef.sqlmxvqp.model.SerializableModel;
import com.hp.gef.sqlmxvqp.sqlmxeditor.SqlMxQueryEditor;
import com.hp.gef.sqlmxvqp.views.SampleView;

public class OpenNewVQPFile extends AbstractHandler {
  @Execute
  public void execute(Shell shell) {
    IResource resource = null;
    try {
      String fileLoc = null;
      ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
          shell, new WorkbenchLabelProvider(),
          new WorkbenchContentProvider());
      dialog.setTitle("title");
      dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
      dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));
      if (dialog.open() == IDialogConstants.OK_ID) {
        resource = (IResource) dialog.getFirstResult();
        fileLoc = resource.getFullPath().toOSString(); 
        fileLoc = Platform.getLocation() + fileLoc;
      }

      FileInputStream fileIn = new FileInputStream(fileLoc);
      
      ObjectInputStream in = new ObjectInputStream(fileIn);
      SerializableModel model = (SerializableModel) in.readObject();
      in.close();
      fileIn.close();
      SampleView part = (SampleView)PlatformUI.getWorkbench()
          .getActiveWorkbenchWindow().getActivePage()
          .findView("com.hp.gef.sqlmxvqp.views.SampleView");
      part = (SampleView) PlatformUI.getWorkbench()
      .getActiveWorkbenchWindow().getActivePage()
      .showView("com.hp.gef.sqlmxvqp.views.SampleView");

      part.setPlanLocationFlag(false);
      part.setSerializedModel(model);
      part.setFile((IFile)resource);
      part.setInitialInput();
      
    } catch (IOException i) {
      i.printStackTrace();
      return;
    } catch (ClassNotFoundException c) {
      System.out.println("Employee class not found");
      c.printStackTrace();
      return;
    } catch (PartInitException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // TODO Auto-generated method stub
    
    execute(HandlerUtil.getActiveShell(event));

    return null;
  }

 
  

}