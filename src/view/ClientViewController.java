/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Jai
 */
public class ClientViewController {
    private ClientView clientView;
    
    public ClientViewController(ClientView clientView){
        this.clientView = clientView;
        clientView.btn_user.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clientView.setMasterNode(clientView.fileView);
            }
        });
        
        clientView.btn_downloads.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clientView.setMasterNode(clientView.downloads_pane);
            }
        });
        
        clientView.btn_settings.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                clientView.setMasterNode(clientView.settings_pane);
            }
        });
        
        //code for logout
        clientView.btn_logout.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                UserInterface.client.stop();
                UserInterface.setScene(Scenes.Dashboard);
            }
        });
    }
    
    
}
