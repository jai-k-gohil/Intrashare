/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import main.Server;

/**
 *
 * @author Jai
 */
public class Dashboard extends HBox{
    public Dashboard(){
        this.setPrefSize(UserInterface.stageWidth, UserInterface.stageHeight-10);
        this.setPadding(new Insets(10));
        this.getStyleClass().add("dashboard");
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(getJoinPane(),getHostPane());
    }
    
    private VBox getJoinPane(){
        VBox joinPane = new VBox();
        joinPane.setAlignment(Pos.CENTER);
        //joinPane.getStyleClass().add("join-pane");
        joinPane.setMinWidth(400);
        joinPane.setMaxWidth(400);
        
        ImageView client_icon = new ImageView(Dashboard.class.getResource("resources/images/database-monitor.png").toExternalForm());
        
        Label joinTitle  = new Label("Join");
        if(UserInterface.museoFont != null)
            joinTitle.setFont(UserInterface.museoFont);
        joinTitle.getStyleClass().add("text-title");
        
        final String text = "Connecting to the local machine\n for uploading files,\n downloading files etc";
        Label join_description = new Label(text);
        join_description.setWrapText(true);
        join_description.getStyleClass().add("text-description");
        
        
        Button btn_connect = new Button("Connect");
        Button btn_direct_connect = new Button("Direct-Connect");
        
        btn_connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                UserInterface.setScene(Scenes.LoginView);
            }
        });
        
        VBox.setMargin(client_icon, new Insets(10, 10, 10, 10));
        VBox.setMargin(btn_connect, new Insets(10, 10, 10, 10));
        VBox.setMargin(btn_direct_connect, new Insets(10, 10, 10, 10));
        VBox.setMargin(joinTitle, new Insets(10, 10, 10, 10));
        
        joinPane.getChildren().addAll(client_icon,joinTitle,join_description ,btn_connect,btn_direct_connect);
        return joinPane;
    }
    
    private VBox getHostPane(){
        VBox hostPane = new VBox();
        hostPane.setAlignment(Pos.CENTER);
        //hostPane.getStyleClass().add("host-pane");
        hostPane.setMinWidth(400);
        hostPane.setMaxWidth(400);
        
        ImageView server_icon = new ImageView(Dashboard.class.getResource("resources/images/cloudserver.png").toExternalForm());
        
        Label joinTitle  = new Label("Host");
        if(UserInterface.museoFont != null)
            joinTitle.setFont(UserInterface.museoFont);
        joinTitle.getStyleClass().add("text-title");
        
        final String text = "Hosting for users to\nconnect for storing or\n retrieveing files";
        Label join_description = new Label(text);
        join_description.setWrapText(true);
        join_description.getStyleClass().add("text-description");
        
        
        Button btn_connect = new Button("Start");
        
        btn_connect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        UserInterface.server = new Server();
                        UserInterface.setScene(Scenes.ServerPage);
                        UserInterface.server.start();
                    }
                });
            }
        });
        
        VBox.setMargin(server_icon, new Insets(10, 10, 10, 10));
        VBox.setMargin(btn_connect, new Insets(10, 10, 10, 10));
        VBox.setMargin(joinTitle, new Insets(10, 10, 10, 10));
        
        hostPane.getChildren().addAll(server_icon,joinTitle,join_description ,btn_connect);
        return hostPane;
    }
    
}
