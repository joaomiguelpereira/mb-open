package com.medibooking.admin.client;


/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 */
public class GwtTestAdminApp {

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "com.medibooking.admin.AdminAppJUnit";
  }

  

}