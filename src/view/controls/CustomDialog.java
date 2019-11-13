/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 *
 * @author Jai
 */
public class CustomDialog {
    public CustomDialog(String title,String headerText,String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
