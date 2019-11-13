/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author jaikishorgohil
 */
public interface Commands {
    int CLOSE_CONNECTION = 0;
    int GET_FILE_LIST = 1;
    int UPLOAD_FILES= 2;
    int DOWNLOAD_FILES = 3;
    int REMOVE_FILES = 4;
    int USER_LOGIN = 5;
    int USER_REGISTER = 9;
    int INVALID_CREDENTIALS = 6;
    int USER_ALREADY_EXISTS = 7;
    int LOGIN_SUCCESSFUL = 8;
}
