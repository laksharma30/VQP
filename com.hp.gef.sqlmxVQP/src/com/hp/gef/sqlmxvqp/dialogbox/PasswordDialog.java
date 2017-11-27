package com.hp.gef.sqlmxvqp.dialogbox;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class PasswordDialog extends Dialog {
  private Text txtHost;
  private Text txtUser;
  private Text txtPassword;
  private String port = "18650";
  private String host;
  private String serverDataSource = "TDM_Default_DataSource";
  private String user = "";
  private String password = "";
  static Credentials credentials = Credentials.getInstance();
  private Text txtServerDataSource;
  private Text textPort;

  public PasswordDialog(Shell parentShell) {
    super(parentShell);
  }

  @Override
  protected Control createDialogArea(Composite parent) {
    Composite container = (Composite) super.createDialogArea(parent);
    container.setLayout(new FormLayout());

    Label lblDetails = new Label(container, SWT.NONE);
    FormData fd_lblDetails = new FormData();
    fd_lblDetails.top = new FormAttachment(0, 8);
    fd_lblDetails.left = new FormAttachment(0, 15);
    lblDetails.setLayoutData(fd_lblDetails);
    lblDetails.setText("Host:Port");
    txtHost = new Text(container, SWT.BORDER);
    FormData fd_txtHost = new FormData();
    fd_txtHost.top = new FormAttachment(0, 5);
    fd_txtHost.left = new FormAttachment(0, 115);
    txtHost.setLayoutData(fd_txtHost);

    Label lblServerDatasource = new Label(container, SWT.NONE);
    FormData fd_lblServerDatasource = new FormData();
    fd_lblServerDatasource.top = new FormAttachment(0, 34);
    fd_lblServerDatasource.left = new FormAttachment(0, 15);
    lblServerDatasource.setLayoutData(fd_lblServerDatasource);
    lblServerDatasource.setText("Server DataSource");

    txtServerDataSource = new Text(container, SWT.BORDER);
    FormData fd_txtServerDataSource = new FormData();
    fd_txtServerDataSource.right = new FormAttachment(0, 434);
    fd_txtServerDataSource.top = new FormAttachment(0, 31);
    fd_txtServerDataSource.left = new FormAttachment(0, 115);
    txtServerDataSource.setLayoutData(fd_txtServerDataSource);
    txtServerDataSource.setText(serverDataSource);

    Label lblUser = new Label(container, SWT.NONE);
    FormData fd_lblUser = new FormData();
    fd_lblUser.top = new FormAttachment(0, 60);
    fd_lblUser.left = new FormAttachment(0, 15);
    lblUser.setLayoutData(fd_lblUser);
    lblUser.setText("User");

    txtUser = new Text(container, SWT.BORDER);
    FormData fd_txtUser = new FormData();
    fd_txtUser.right = new FormAttachment(0, 434);
    fd_txtUser.top = new FormAttachment(0, 57);
    fd_txtUser.left = new FormAttachment(0, 115);
    txtUser.setLayoutData(fd_txtUser);
    txtUser.setText(user);

    Label lblPassword = new Label(container, SWT.NONE);
    FormData fd_lblPassword = new FormData();
    fd_lblPassword.top = new FormAttachment(0, 86);
    fd_lblPassword.left = new FormAttachment(0, 16);
    lblPassword.setLayoutData(fd_lblPassword);
    lblPassword.setText("Password");

    txtPassword = new Text(container, SWT.SINGLE | SWT.BORDER | SWT.PASSWORD);
    FormData fd_txtPassword = new FormData();
    fd_txtPassword.right = new FormAttachment(0, 434);
    fd_txtPassword.top = new FormAttachment(0, 83);
    fd_txtPassword.left = new FormAttachment(0, 115);
    txtPassword.setLayoutData(fd_txtPassword);
    txtPassword.setText(password);

    textPort = new Text(container, SWT.BORDER);
    fd_txtHost.right = new FormAttachment(textPort, -6);
    FormData fd_textPort = new FormData();
    fd_textPort.left = new FormAttachment(txtServerDataSource, -64);
    fd_textPort.top = new FormAttachment(0, 5);
    fd_textPort.right = new FormAttachment(100, -10);
    textPort.setLayoutData(fd_textPort);
    textPort.setText(port);
    return container;
  }

  // override method to use "Login" as label for the OK button
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.OK_ID, "Login", true);
    createButton(parent, IDialogConstants.CANCEL_ID,
        IDialogConstants.CANCEL_LABEL, false);
  }

  @Override
  protected void configureShell(Shell newShell) {
    super.configureShell(newShell);
    newShell.setText("Connect to NonStop");
  }

  @Override
  protected Point getInitialSize() {
    return new Point(450, 300);
  }

  @Override
  protected void okPressed() {
    host = txtHost.getText();
    user = txtUser.getText();
    serverDataSource = txtServerDataSource.getText();
    password = txtPassword.getText();
    credentials.setSysDetails(getSystemDetails());
    credentials.setUsrname(user);
    credentials.setPassWord(password);
    super.okPressed();
  }

  public String getUser() {
    System.out.println("User: " + user);
    credentials.setUsrname(user);
    return user;
  }

  public void setUser(String user) {
    this.user = user;

  }

  public String getSystemDetails() {
    String systemDetails = new String(host + ":" + port + "/serverDataSource="
        + serverDataSource);
    credentials.setSysDetails(systemDetails);
    return systemDetails;
  }

  public String getPassword() {
    System.out.println("Password: " + password);
    credentials.setPassWord(password);
    return password;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getPort() {
    return port;
  }

  public void setServerDataSource(String dataSource) {
    this.serverDataSource = dataSource;
  }

  public String getServerDataSource() {
    return serverDataSource;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public static Credentials getCredentials() {
    return credentials;
  }
}
