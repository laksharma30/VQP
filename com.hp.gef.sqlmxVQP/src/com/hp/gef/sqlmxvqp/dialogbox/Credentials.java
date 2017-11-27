package com.hp.gef.sqlmxvqp.dialogbox;

public class Credentials {

  String SysDetails;
  String Usrname;
  String Pwd;
  boolean loginSuccessful = false;

  static Credentials credentials = new Credentials();
  
  private Credentials() {

  }
  
  public static Credentials getInstance() {
    return credentials;
  }

  public void setSysDetails(String SysDetails) {
    this.SysDetails = SysDetails;
  }

  public void setLoginSuccessful(boolean value) {
    this.loginSuccessful = value;
  }
  
  public boolean loginSuccessful() {
    return this.loginSuccessful;
  }
  
  public void setUsrname(String usr) {
    this.Usrname = usr;
  }

  public void setPassWord(String pwd) {
    this.Pwd = pwd;
  }

  public String getSysDetails() {
    return SysDetails;
  }

  public String getUsrname() {
    return Usrname;
  }

  public String getPwd() {
    return Pwd;
  }
}
