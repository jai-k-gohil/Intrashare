/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jai
 */
public class Client {
   
    //all initailizations
    private Socket client;
    private final int port = 4444;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private static List<File> files;
    
    //constructors
    public Client() throws Exception{
            this.client = new Socket(InetAddress.getLocalHost(), 4444);
            
            this.inputStream = new ObjectInputStream(
                                    new BufferedInputStream(
                                        client.getInputStream()));
            
            this.outputStream = new ObjectOutputStream(
                                    new BufferedOutputStream(
                                        client.getOutputStream()));
            this.outputStream.flush();
            
            files = new ArrayList<>();
            System.out.println("Got the components");
    }
    
     
    

    //for userlogin
    public boolean initiateLogin(String name,String password){
        System.out.println("Got components right");
        if(inputStream == null || outputStream == null){ System.out.println("initiatelogin in client class "); return false;}
        if(ClientServices.initiateUserLogin(outputStream,inputStream,name, password)){
            System.out.println("Loginn Successful!!1");
            return true;
        }else{
            System.out.println("user login failed");
            stop();
            return false;
        }
        //if user login is not successful then stop the client and reinitiate the client
    }
    
    
    //for user signup
    public boolean initiateSignUp(String name,String password){
        if(inputStream == null || outputStream == null){ System.out.println("initiatelogin in client class "); return false;}
        if(Commands.LOGIN_SUCCESSFUL == ClientServices.initiateUserRegistration(outputStream, inputStream, name, password)){
            System.out.println("Login successfull!!!!!");
            return true;
        }else{
            //if user signup is not successful then stop the client and reinitiate the client
            System.out.println("Login unsuccessful!");
            stop();
        }
        return false;
    }

    
    public List<File> getFileList(){
        ClientServices.sendQuery(outputStream, Commands.GET_FILE_LIST);
        List<File> recievedfiles = ClientServices.recieveFileList(inputStream);
        return recievedfiles;
    }
    
    public synchronized void reloadFileList(){
        ClientServices.sendQuery(outputStream, Commands.GET_FILE_LIST);
        files = ClientServices.recieveFileList(inputStream);
    }
    
    public synchronized void uploadFiles(List<File> files){
        if(files == null) {System.out.println("This");return;}
        System.out.println("Sending query ...");
        ClientServices.sendQuery(outputStream, Commands.UPLOAD_FILES);
        System.out.println("Sending files by upload files method  client!");
        ClientServices.sendFiles(outputStream, files);
        reloadFileList();
    }
    
    public synchronized void downloadFiles(List<File> files){
        if(files == null) return;
        ClientServices.sendQuery(outputStream, Commands.DOWNLOAD_FILES);
        ClientServices.sendFilesList(outputStream, files);
        ClientServices.receiveFiles(inputStream, CustomSettings.getDownloadFolder());
        reloadFileList();
    }
    
    public void removeFiles(List<File> files){
        if(files == null) return;
        ClientServices.sendQuery(outputStream, Commands.REMOVE_FILES);
        ClientServices.sendFilesList(outputStream, files);
        reloadFileList();
    }
    
    public void terminateConnection(){
        if(!client.isClosed())
            ClientServices.sendQuery(outputStream, Commands.CLOSE_CONNECTION);
    }
    
    
    public void stop(){
        try { 
            terminateConnection();
            if(inputStream != null)
                inputStream.close();
            if(outputStream!= null)
                outputStream.close();
            if(!client.isClosed())
                client.close();
        } catch (Exception e) {}
    }//end of stop
    
    private boolean areResourcesAvailable(){
        return !(client.isClosed() || outputStream == null || inputStream == null);
    }
    
    public ObjectInputStream getInputStream(){
        return this.inputStream;
    }
    
    public ObjectOutputStream getOutputStream(){
        return this.outputStream;
    }
    
    public List<File> getClientFiles(){
        return files;
    }
}
