/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import view.UserInterface;

/**
 *
 * @author Jai
 */
public class CustomCloseButton extends Button{
    public CustomCloseButton(){
        super();
        ImageView close_icon = new ImageView(UserInterface.class.getResource("resources/images/error.png").toExternalForm());
        this.setGraphic(close_icon);
        this.setStyle("-fx-border-radius: 100 100 100 100;-fx-background-radius: 100 100 100 100;");
    }
}
