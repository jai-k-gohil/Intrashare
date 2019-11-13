/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.util.List;

/**
 *
 * @author Jai
 */
public class ClientDemo {
    public static void main(String[] args) throws Exception{
        Client client = new Client();
       
        if(client.initiateLogin("milind", "milind9819")){
            System.out.println("Login successful");
            List<File> files = FileManager.getFilesFrom(new File("C:\\Users\\Jai\\Desktop\\intrashare\\main\\libraries"));
            System.out.println(files);
            ClientServices.sendQuery(client.getOutputStream(), Commands.UPLOAD_FILES);
            
            ClientServices.sendFiles(client.getOutputStream(), files);
        }
        else
            System.out.println("Login unsuccessful");
        
        client.stop();
    }
}
