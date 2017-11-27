package com.hp.gef.sqlmxvqp.commandhandler;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

import com.hp.gef.sqlmxvqp.dialogbox.PasswordDialog;

public class EnterCredentialsHandler extends AbstractHandler {

  @Execute
  public void execute(Shell shell) {
    PasswordDialog dialog = new PasswordDialog(shell);

    // get the new values from the dialog
    if (dialog.open() == Window.OK) {

      String SysDetails = dialog.getSystemDetails();
      String user = dialog.getUser();
      String pw = dialog.getPassword();

      System.out.println(SysDetails);
      System.out.println(user);
      System.out.println(pw);
      try {
        Class.forName("com.tandem.t4jdbc.SQLMXDriver");

        // STEP 3: Open a connection
        // System.out.println("Connecting to database...");

        DriverManager.getConnection("jdbc:t4sqlmx://" + SysDetails, user, pw);
        MessageDialog.openInformation(shell, "Success", "Connected to NonStop");
      } catch (SQLException e) {
        MessageDialog.openError(shell, "Error",
            "Failed to connect to NonStop\n" + e.getMessage());
      } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // TODO Auto-generated method stub
    
    execute(HandlerUtil.getActiveShell(event));

    return null;
  }

}