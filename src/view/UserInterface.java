/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Client;
import main.Server;
import main.ThreadPool;

/**
 *
 * @author jaikishorgohil
 */
public class UserInterface extends Application {
    
    
    public static ThreadPool taskManager = null;
    public static Font museoFont;
    public static final int stageWidth = 840;
    public static final int stageHeight = 540;
    public static Client client;
    public static Server server;
    public static ClientView clientPage;
    public static Scene introView,loginView,clientView,dashboard,serverView;
    private static Stage window;
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.initStyle(StageStyle.TRANSPARENT);
        window = stage;
        

        //all initailizations 
        System.out.println("Entered start method");
        System.out.println("Getting the font");
        museoFont = Font.loadFont(UserInterface.class.getResource("resources/museo_slab.otf").toExternalForm(), 10);
        
        taskManager = new ThreadPool("UITaskManager", 1);
        introView = new Scene(new IntroView(),stageWidth,stageHeight); 
        loginView = new Scene(new LoginView(),stageWidth,stageHeight);
        clientPage = new ClientView();
        clientView = new Scene(clientPage,stageWidth,stageHeight);
        dashboard = new Scene(new Dashboard(),stageWidth,stageHeight);
        serverView = new Scene(new ServerView(),stageWidth,stageHeight);
        
        introView.getStylesheets().add(UserInterface.class.getResource("resources/Application.css").toExternalForm());
        loginView.getStylesheets().add(UserInterface.class.getResource("resources/Application.css").toExternalForm());
        clientView.getStylesheets().add(UserInterface.class.getResource("resources/Application.css").toExternalForm());
        dashboard.getStylesheets().add(UserInterface.class.getResource("resources/Application.css").toExternalForm());
        serverView.getStylesheets().add(UserInterface.class.getResource("resources/Application.css").toExternalForm());
        
        introView.setFill(Color.TRANSPARENT);
        loginView.setFill(Color.TRANSPARENT);
        clientView.setFill(Color.TRANSPARENT);
        dashboard.setFill(Color.TRANSPARENT);
        serverView.setFill(Color.TRANSPARENT);
        
        stage.setScene(introView);
        stage.show();        
    }
    
    @Override
    public void stop(){
        System.out.println("Stop called");
        taskManager.shutdown();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    public static void setScene(int scene){
        switch(scene){
            case Scenes.IntroView:  window.setScene(introView);break;
            case Scenes.LoginView:  window.setScene(loginView);break;
            case Scenes.ClientPage: window.setScene(clientView);break;
            case Scenes.Dashboard : window.setScene(dashboard);break;
            case Scenes.ServerPage: window.setScene(serverView);break;
        }
    }
    
}
