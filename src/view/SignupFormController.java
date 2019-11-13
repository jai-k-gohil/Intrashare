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
public class SignupFormController {

    private SignupForm signupForm;

    public SignupFormController(SignupForm signupForm) {
        this.signupForm = signupForm;
        //event handling
        signupForm.btn_login.setOnAction((ActionEvent ae) -> {
            if (signupForm.pf_password.getText().equals(signupForm.pf_confirm_password.getText())) {
                if (userLogin()) {
                    signupForm.message.setText("Successfully LoggedIn");
                    List<File> files = UserInterface.client.getFileList();
                    UserInterface.setScene(Scenes.ClientPage);
                }
            } else {
                new CustomDialog("Validation Error", "Passwords entered are not similar. ", "Please refill the details");
            }
        });

    }
    
    public boolean userLogin(){
        try{
            UserInterface.client = new Client();
            signupForm.message.setText("Sign in button pressed!");
            if(UserInterface.client.initiateSignUp(signupForm.tf_username.getText(), signupForm.pf_password.getText())){
                return true;
            }
        }catch(Exception e){
            new CustomDialog("Caution", "Unable to connect to host..", "Please check if host is active in the network!");
        }
        stop();
        return false;
    }
    
    public void stop(){
        signupForm.message.setText("Login unsuccessful");
        signupForm.tf_username.setText("");
        signupForm.pf_password.setText("");
    }
}
