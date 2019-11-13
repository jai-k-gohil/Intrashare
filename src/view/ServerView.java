/*0
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.Socket;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Server;
import view.controls.CustomCloseButton;

/**
 *
 * @author Jai
 */
public class ServerView extends StackPane{
    private Button btn_close;
    private static UserListView userList;
    
    public ServerView(){
        initializeComponents();
    }
    
    
    
    
    private void initializeComponents() {
        System.out.println("initial");
        //for this stack pane
        this.getStyleClass().add("server-view");
        this.setAlignment(Pos.CENTER);
        userList = new UserListView();
        userList.getStyleClass().add("user-list");
        //the close button
        CustomCloseButton btn_close = new CustomCloseButton();
        StackPane.setAlignment(btn_close, Pos.TOP_RIGHT);
        btn_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserInterface.server.closeServer();
                ((Stage) btn_close.getScene().getWindow()).close();
            }
        });
        
        
        //the title text
        Text users = new Text("Users");
        if(UserInterface.museoFont != null)
            users.setFont(UserInterface.museoFont);
        users.getStyleClass().add("text-title");
        
        Button btn_refresh = new Button("Refresh");
        Button btn_stop = new Button("Stop");
        
        btn_stop.setStyle("-fx-background-color:#ff3333;-fx-font-size: 16px;");
        btn_refresh.setStyle("-fx-background-color:#009933;-fx-font-size: 16px;");
        
        HBox options = new HBox();
        options.getChildren().addAll(btn_refresh,btn_stop);
        
        StackPane.setAlignment(options, Pos.CENTER_RIGHT);
        
        btn_refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                reload(Server.getClients());
            }
        });
        
        btn_stop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserInterface.server.closeServer();
                UserInterface.setScene(Scenes.Dashboard);
            }
        });
        
        //making the top pane
        StackPane userPane = new StackPane();
        userPane.setMaxWidth(Double.MAX_VALUE);
        userPane.getChildren().addAll(users,options);
        
        
        
        //making the whole pane by adding the  top pane and user list view beneath it
        VBox basePane = new VBox();
        basePane.setPrefSize(UserInterface.stageWidth-10, UserInterface.stageHeight-10);
        basePane.setPadding(new Insets(20));
        basePane.getStyleClass().add("server-base-pane");
        basePane.getChildren().addAll(userPane,userList);
        this.getChildren().addAll(basePane,btn_close);
    }
    
    public static void reload(List<Socket> clients){
        userList.reload(clients);
    }
}
