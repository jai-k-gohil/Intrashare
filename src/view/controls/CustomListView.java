/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 *
 * @author Jai
 */
public class CustomListView extends ListView{
    public CustomListView(){
        super();
        this.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        this.setMinWidth(500);
        this.getStyleClass().add("list-view");
    }
    
    public CustomListView(List<CustomHBoxCell> cells){
        this.getItems().addAll(cells);
        this.setMinWidth(500);
        this.getStyleClass().add("list-view");
    }
    
    public void add(CustomHBoxCell cell){
        this.getItems().add(cell);
    }
    
    public void remove(CustomHBoxCell cell){
        this.getItems().remove(cell);
    }
    
    public void clear(){
        this.getItems().clear();
    }
    
    public void reload(List<CustomHBoxCell> cells){
        this.getItems().clear();
        this.getItems().addAll(cells);
    }
    
    public CustomHBoxCell getSelection(){
        return (CustomHBoxCell)this.getSelectionModel().getSelectedItem();
    }
    
    public List<CustomHBoxCell> getSelections(){
        ObservableList<CustomHBoxCell> cells = this.getSelectionModel().getSelectedItems();
        //this.getSelectionModel().clearSelection();
        return cells;
    }
}
