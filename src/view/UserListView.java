/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import view.controls.CustomHBoxCell;
import view.controls.CustomListView;

/**
 *
 * @author Jai
 */
public class UserListView extends VBox{
    public CustomListView list;
    public ImageView user_icon;
    
    public UserListView(){
        super();
        setComponents();
    }
    
    private void setComponents(){
        list = new CustomListView();
        this.prefHeight(500);
        this.prefWidth(500);
        this.getStyleClass().add("user-list-view");
        this.getChildren().add(list);
    }
    
    
    
    public void reload(List<Socket> clients){
        List<CustomHBoxCell> cells = new ArrayList<>();
        for (Socket client : clients) {
            cells.add(new CustomHBoxCell("User"+client.getInetAddress().toString(),"Port:"+client.getPort()));
        }
        list.reload(cells);
    }
    
    public void clear(){
        list.clear();
    }
}
