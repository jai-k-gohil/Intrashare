/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import main.Client;
import view.controls.CustomDialog;

/**
 *
 * @author Jai
 */
public class LoginFormController {
    private LoginForm loginForm;
    public LoginFormController(LoginForm loginForm) {

        this.loginForm = loginForm;
        //event handling
        loginForm.btn_login.setOnAction((ActionEvent ae) -> {
            if (areFieldsFilled()) {
                if (userLogin()) {
                    loginForm.message.setText("Successfully LoggedIn");
                    List<File> files = UserInterface.client.getFileList();
                    UserInterface.clientPage.updateFiles(files);
                    UserInterface.setScene(Scenes.ClientPage);
                //Scene clientScene = new Scene(new ClientPage(files));
                    //((Stage)loginForm.getScene().getWindow()).setScene(clientScene);
                }
            } else {
                new CustomDialog("Caution", "Invalid entries or format!", "Please fill all the fields and check if they are appropiate.");
            }
        });

    }
    
    public boolean userLogin(){
        try{
            UserInterface.client = new Client();
            loginForm.message.setText("Sign in button pressed!");
            if(UserInterface.client.initiateLogin(loginForm.tf_username.getText(), loginForm.pf_password.getText())){
                return true;
            }else{
                stop();
            }
        }catch(Exception e){
            new CustomDialog("Caution", "Unable to connect to host..", "Please check if host is active in the network!");
            stop();
        }
        return false;
    }
    
    public void stop(){
        loginForm.message.setText("Login Unsuccessful");
        loginForm.tf_username.setText("");
        loginForm.pf_password.setText("");
    }
    
    public boolean areFieldsFilled(){
        return !(loginForm.tf_username.getText().length() < 1 || loginForm.pf_password.getText().length() < 8);
    }
    
}
