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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Jai
 */
public class IntroView extends HBox{
    public IntroView(){
        this.getStyleClass().add("rounded-border-view");
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(new IntroPanel());
    }
    
    class IntroPanel extends VBox{
        public IntroPanel(){
            this.setAlignment(Pos.CENTER_RIGHT);
            Text project_name = new Text("Intrashare");
            if(UserInterface.museoFont != null)
                project_name.setFont(UserInterface.museoFont);
            project_name.setId("title-text");
            
            Button next = new Button("Proceed");
            next.setMinSize(100, 50);
            next.setId("intro-proceed-button");
            this.getChildren().addAll(project_name,next);
            
            
            next.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    UserInterface.setScene(Scenes.Dashboard);
                }
            });
        }
    }
}
