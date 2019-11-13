/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Jai
 */
public class SignupForm extends GridPane{
    
    public Text sceneTitle;
    public Label lbl_username, lbl_password,lbl_confirm_password;
    public TextField tf_username;
    public PasswordField pf_password,pf_confirm_password;
    public Button btn_login;
    public Text message;

    public SignupForm() {
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("signup-form");
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        //adding components
        sceneTitle = new Text("Welcome");
        sceneTitle.setId("welcome-text");
        this.getChildren().add(sceneTitle);

        lbl_username = new Label("Username :");
        this.add(lbl_username, 0, 1);

        tf_username = new TextField();
        this.add(tf_username, 1, 1);

        lbl_password = new Label("Password:");
        this.add(lbl_password, 0, 2);

        pf_password = new PasswordField();
        this.add(pf_password, 1, 2);
        
        lbl_confirm_password = new Label("Confirm Password");
        this.add(lbl_confirm_password, 0, 3);
        
        pf_confirm_password = new PasswordField();
        this.add(pf_confirm_password, 1, 3);

        btn_login = new Button("Sign up");
        HBox pane = new HBox(10);
        pane.setAlignment(Pos.BOTTOM_RIGHT);
        pane.getChildren().add(btn_login);
        this.add(pane, 0, 6);

        //btn.setOnAction(e -> System.out.println("It works!"));
        message = new Text();
        message.setId("actiontarget");
        this.add(message, 1, 8);

        new SignupFormController(this);
    }
}
