/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 *
 * @author vesp
 */
public class CustomHBoxCell extends HBox{
    private Label oneLabel;
    private String[] labels;
    public CustomHBoxCell(String ... labels){
        super();
        this.labels = labels;
        for (String label : labels) {
            oneLabel = new Label(label);
            oneLabel.setMinWidth(100);
            oneLabel.setStyle("-fx-label-padding: 0 5 0 5;-fx-border-color: #ffffff;");
            HBox.setHgrow(oneLabel, Priority.ALWAYS);
            this.getChildren().add(oneLabel);
        }
    }
    
    public String[] getData(){
        return this.labels;
    }
    
    public String getTextFromColumn(int idx){
        return this.labels[idx];
    }
}
