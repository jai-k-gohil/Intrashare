/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.controls.CustomCloseButton;
import view.controls.FileListView;

/**
 *r
 * @author Jai
 */
public class ClientView extends SplitPane{
    
    public FileListView fileView;
    public static Pane downloads_pane,settings_pane,master_pane;
    public Button btn_user,btn_downloads,btn_settings,btn_logout;
    public static CustomCloseButton btn_close;
    private List<File> files;
    
    public ClientView(){
        initializeComponents();
    }
    
    public ClientView(List<File> files){
        if (files != null) this.files = files;
        initializeComponents();
    }
    
    public final void initializeComponents(){
        this.getStyleClass().add("client-view");
        if(files != null)
            this.fileView = new FileListView(files);
        else
            this.fileView = new FileListView();
        
        this.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        fileView.setPadding(new Insets(10));
        
        downloads_pane = new Pane();
        downloads_pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        downloads_pane.getChildren().add(new Label("Download Sction"));
        settings_pane = new Pane();
        settings_pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        settings_pane.getChildren().add(new Label("Settings"));
        master_pane = new StackPane();
        master_pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        

        
        
        VBox sideBar = new VBox();
        sideBar.setAlignment(Pos.CENTER);
        sideBar.getStyleClass().add("detail-pane");
        sideBar.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn_user = new Button("User");
        btn_logout = new Button("Logout");
        btn_settings = new Button("Settings");
        btn_downloads = new Button("Downloads");
        btn_close = new CustomCloseButton();

        btn_close.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                UserInterface.client.stop();
                ((Stage)btn_close.getScene().getWindow()).close();
            }
        });

        btn_downloads.setMaxWidth(Double.MAX_VALUE);
        btn_user.setMaxWidth(Double.MAX_VALUE);
        btn_settings.setMaxWidth(Double.MAX_VALUE);
        btn_logout.setMaxWidth(Double.MAX_VALUE);
        
        
        
        sideBar.getChildren().addAll(btn_user,btn_downloads,btn_settings,btn_logout);
        
        StackPane.setAlignment(btn_close, Pos.TOP_RIGHT);
        master_pane.getChildren().add(fileView);
        this.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        this.setDividerPositions(0.3d);
        this.getItems().addAll(sideBar,master_pane);
        
        //setting panes
        //set the divider position
        new ClientViewController(this);
    }
    
    
    public void updateFiles(List<File> files){
        this.fileView.reload(files);
    }
    
    public static void setMasterNode(Pane pane){
        master_pane.getChildren().clear();
        master_pane.getChildren().addAll(pane,btn_close);
    }
}

