package com.hp.gef.sqlmxvqp.actionprovider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonActionProvider;

import com.hp.gef.sqlmxvqp.model.SerializableModel;


public class OpenFileProvider extends CommonActionProvider {

  public OpenFileProvider() {
    // TODO Auto-generated constructor stub
  }
  
  class OpenChildAction extends Action
  {
      private ISelectionProvider provider;
      private IFile data;

      public OpenChildAction(IWorkbenchPage workbenchPage, ISelectionProvider selectionProvider)
      {
          super("Open item");
          provider = selectionProvider;
      }
      

      @Override
      public void run()
      {
          
          if(data!=null)
          {
            if(data.getFileExtension()=="ser")
            {
              try
              {
                 FileInputStream fileIn = new FileInputStream(data.getName());
                 ObjectInputStream in = new ObjectInputStream(fileIn);
                SerializableModel model = (SerializableModel) in.readObject();
                 in.close();
                 fileIn.close();
//                 RetrievedPlanView view =(RetrievedPlanView) PlatformUI.getWorkbench()
//                     .getActiveWorkbenchWindow().getActivePage()
//                     .findView("com.hp.gef.sqlmxVQP.retrievePlanView");
//                 view.setSerializedModel(model);
              }catch(IOException i)
              {
                 i.printStackTrace();
                 return;
              }catch(ClassNotFoundException c)
              {
                 System.out.println("Employee class not found");
                 c.printStackTrace();
                 return;
              }
            
            }
            else
            {
              System.out.println("Open only ser files");
            }
             
            
          }
          super.run();
      }

      @Override
      public boolean isEnabled()
      {
          ISelection selection = provider.getSelection();
          if (!selection.isEmpty())
          {
              IStructuredSelection sSelection = (IStructuredSelection) selection;
              if (sSelection.size() == 1 && sSelection.getFirstElement() instanceof IFile) 
              {
                  data = (IFile)sSelection.getFirstElement();
                  return true;
              }
          }
          return false;
      }

      
      
  }

}
