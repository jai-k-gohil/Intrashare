/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.controls.CustomCloseButton;

/**
 *
 * @author Jai
 */
public class LoginView extends AnchorPane{
        
        public GridPane login_form,signup_form;
        public StackPane loginContainer,signupContainer;
        public SplitPane basePane;        
        
        
        public LoginView() {
            //making splitpane
            this.getStyleClass().add("login-view");
            this.getStyleClass().add("rounded-border-view");
            basePane = new SplitPane();
            basePane.setPrefSize(UserInterface.stageWidth,UserInterface.stageHeight);
            
            //initializing forms
            this.login_form = new LoginForm();
            this.signup_form = new SignupForm();
          
            //making containers and adding it to container
            this.loginContainer = new StackPane();
            this.signupContainer = new StackPane();
            this.loginContainer.setAlignment(Pos.CENTER);
            this.signupContainer.setAlignment(Pos.CENTER);
            this.loginContainer.maxHeight(Double.MAX_VALUE);
            this.signupContainer.maxHeight(Double.MAX_VALUE);
            //HBox.setHgrow(loginContainer, Priority.ALWAYS);
            //HBox.setHgrow(signupContainer, Priority.ALWAYS);
            
            
            //close button
            CustomCloseButton btn_close = new CustomCloseButton();
            StackPane.setAlignment(btn_close, Pos.TOP_RIGHT);
            btn_close.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    ((Stage)btn_close.getScene().getWindow()).close();
                }
            });
            
            btn_close.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    ((Stage)btn_close.getScene().getWindow()).close();
                }
            });
            
            
            //for adding effects
            loginContainer.getStyleClass().add("login-container");
            signupContainer.getStyleClass().add("signup-container");
            
            

//adding the blur panes to the container
            this.loginContainer.getChildren().add(getLoginBlurPane());
            this.signupContainer.getChildren().addAll(getSignupBlurPane(),btn_close);
            
            
            basePane.getItems().addAll(this.loginContainer,this.signupContainer);
            AnchorPane.setLeftAnchor(basePane, 0d);
            AnchorPane.setRightAnchor(basePane, 0d);
            this.getChildren().add(basePane);
            
            //new LoginViewController(this);
        }//end of constructor
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        private Pane getLoginBlurPane(){
            StackPane loginPane = new StackPane();
            loginPane.setAlignment(Pos.CENTER);
            loginPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            //glassPane.setPrefSize(basePane.getWidth()*0.5, basePane.getHeight());
            loginPane.getStyleClass().add("blur-login-cover");
            
            
            ImageView login_icon = new ImageView(LoginView.class.getResource("resources/images/userlogin.png").toExternalForm());
            StackPane.setAlignment(login_icon, Pos.CENTER);
            Text login_text = new Text("Login to connect");
            login_text.setFill(Color.WHITE);
            StackPane.setAlignment(login_text, Pos.CENTER);
            //login.setMinSize(50, 25);
            
            loginPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    loginContainer.getChildren().remove(loginPane);
                    loginContainer.getChildren().add(login_form);
                }
            });
            
            VBox details = new VBox();
            details.setAlignment(Pos.CENTER);
            details.getChildren().addAll(login_icon,login_text);
            loginPane.getChildren().add(details);
            return loginPane;
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        private Pane getSignupBlurPane(){
            StackPane glassPane = new StackPane();
            glassPane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            //glassPane.setPrefSize(this.getWidth()*0.5, this.getHeight());
            glassPane.getStyleClass().add("blur-signup-cover");
            
            ImageView signup_icon = new ImageView(LoginView.class.getResource("resources/images/usersignup.png").toExternalForm());
            StackPane.setAlignment(signup_icon, Pos.CENTER);
            
            Text signup_text = new Text("Not already a user");
            signup_text.setFill(Color.WHITE);
            StackPane.setAlignment(signup_text, Pos.CENTER);
            
            glassPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    signupContainer.getChildren().remove(glassPane);
                    signupContainer.getChildren().add(signup_form);
                }
            });
            
            VBox details = new VBox();
            details.setAlignment(Pos.CENTER);
            details.getChildren().addAll(signup_icon,signup_text);
            glassPane.getChildren().add(details);
            return glassPane;
        }
       
}
