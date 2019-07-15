package com.com.yummigr.util;


/**
 * @author osvaldoairon
 */
public class Constants {




    public static final  String[] HEADER = {
            "Date",
            "Action",
            "User"
    };

    /**
     * criar uma variavel de ambiente para setar a url base da umbrella.
     *
     *
     */

    public static final String API_URL_BASE = "http://localhost:8000/";

    /**
     * default;
     */
    public static final String CONTENT_TYPE = "application/json";


    public static final String[] CONFIGURATION_ARCHIVE_CSV_ACTION_NAMES =
            {

                    "action_create_user_log",
                    "action_desative_user_log",
                    "action_create_contact_log",
                    "action_update_contact_log",
                    "action_delete_contact_log",
                    "action_connect_profile_umbrella_log",
                    "action_create_messenger_connector_log"
            };

    public static final String[] CONFIGURATION_ARCHIVE_FORMATS =
            {
                    ".png",
                    ".jpeg",
                    ".csv",
                    ".txt",
                    ".pdf"
            };

    public static final String[] CONFIGURATIONCSV =
            {

                    "Date",",","Action",",","User"

            };







}
