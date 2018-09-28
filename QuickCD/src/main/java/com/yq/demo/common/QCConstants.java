package com.yq.demo.common;

import java.util.Locale;

public class QCConstants {
    //******************************************************************************************************************
    // CLASS
    //******************************************************************************************************************

    static public final Locale DEFAULT_LOCALE = Locale.US;
    static public final Locale[] SUPPORTED_LOCALES = { Locale.US, Locale.SIMPLIFIED_CHINESE};
    static public final String DEPLOY_BUNDLE_NAME = "QuickDeploy";
    //------------------------------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------------------------------
    final static public String ALL_USER_LIST                              = "userList";
    final static public String USER_ID                                    = "userID";
    final static public String CURRENT_USER                               = "currentUser";
    final static public String EDIT_USER                                  = "editUser";
    final static public String LOGIN_USER                                 = "loginUser";

    final static public String ALL_PROJECT_LIST                           = "projectList";
    final static public String CURRENT_PROJECT                            = "currentProj";
    final static public String ALL_STEP_OF_PROJECT                        = "stepList";

    final static public String ALL_AGENT_LIST                             = "agentList";
    final static public String ALL_AGENTPOOL_LIST                         = "agentpoolList";
    final static public String AGENTPOOL                                  = "agentpool";
    final static public String AGENT_EDIT_MODE                            = "agentEditMode";

    final static public String ALL_BUILD_LIST                             = "buildList";
    final static public String CURRENT_BUILD                              = "currentBuild";
    final static public String ALL_BUILD_OF_PROJECT                       = "buildListOfProj";
    final static public String CURRENT_STEP_LOG                           = "currentStepLog";

    final static public String ALL_RESULT_OF_BUILD                        = "resultList";
    final static public String CURRENT_RESULT                             = "currentResult";

    final static public String ERROR_MSG                                  = "error";

    final static public String ALL_ENV_LIST                               = "envList";
    final static public String CURRENT_ENV                                = "currentEnv";

    final static public String DATEFORMAT                                 = "yyyy-MM-dd HH:mm:ss z.";

    final static public int SERVER_PORT                                   = 6667;

    final static public String BUILD_ID_SEQ                               = "BUILD_ID_SEQ";
    final static public String BASE_URL                                   = "BASE_URL";

    final static public String PLUGIN_DIR                                  = "pluginDir";
    final static public String PROJECT_STEPLOG_DIR                         = "PROJECT_STEPLOG_DIR";
    final static public String VAR_BUILD_DIR                                  = "varBuildDir";
}
