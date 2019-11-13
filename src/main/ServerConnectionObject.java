/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author jaikishorgohil
 */
public class ServerConnectionObject implements Runnable{
	private Socket clientSocket = null;
        private ObjectOutputStream outputStream = null;
        private ObjectInputStream inputStream = null;
	private boolean stopServer;
        private String path = null;
        private String username = null;
	private int query = -1;

	public ServerConnectionObject(Socket clientSocket){
            this.clientSocket = clientSocket;
            initializeComponents();
	}
        
        private void initializeComponents(){
            try
            {
                this.outputStream = new ObjectOutputStream(
                                            new BufferedOutputStream(
                                                    clientSocket.getOutputStream()));
                this.outputStream.flush();
                
                this.inputStream = new ObjectInputStream(
                                            new BufferedInputStream(
                                                    clientSocket.getInputStream()));
                
                System.out.println("got the resources");
            }catch(Exception e){ System.out.println(e.getMessage());}
        }
        
        @Override
	public final void run(){
                
            try {
                System.out.println("Entered start method"); 
                while (!stopServer && !clientSocket.isClosed()) {
                    //wait for any query
                    query = inputStream.readInt();
                    System.out.println("Got query !!!!");
                    //get what did client queried for and perform the intended function
                    if(query >= 0) processQuery(query);
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println(cnfe);
            } catch (IOException ie) {
                System.out.println(ie);
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                closeThisServer();
            }
    
		
	}


	private void closeThisServer(){
            //perform all shutdown activities
            stopServer = true;
            try {
                Server.deleteRecord(clientSocket);
                if(!clientSocket.isClosed())
                    processQuery(Commands.CLOSE_CONNECTION);
                if(!clientSocket.isClosed())
                    clientSocket.close();
                if(inputStream != null)
                    inputStream.close();
                if(outputStream != null)
                    outputStream.close();
            } catch (Exception e) {
                System.out.println("Closing this server !");
            }
	}

	private void processQuery(int query) throws IOException,ClassNotFoundException{
		switch(query){
			case Commands.CLOSE_CONNECTION :{ closeThisServer();
                        }break;
                        
                        case Commands.USER_LOGIN :  {
                            String name = processUserLogin();
                            System.out.println("processing user login");
                            if(name != null){
                                this.username = name;
                                setUserPath(username);
                            }else{
                                closeThisServer();
                            }
                            
                        }break;
                                                        
                        case Commands.USER_REGISTER :   {
                            String name = processUserRegistration();
                            if(name != null){
                                this.username = name;
                                setUserPath(username);
                            }else{
                                closeThisServer();
                            }
                        }break;  
                                                            
//                        case Commands.GET_FILE_LIST :   {
//                            Server.submitOutputTask(new Runnable(){
//                                @Override
//                                public void run(){
//                                    if(areResourcesAvailable()){
//                                        System.out.println("Sending files");
//                                        ClientServices.sendFilesList(outputStream,FileManager.getFilesFrom(new File(path)));
//                                    }
//                                }
//                            });break;   }
                                                        
//                        case Commands.UPLOAD_FILES :   {
//                            Server.submitInputTask(new Runnable(){
//                                @Override
//                                public void run(){
//                                    if(areResourcesAvailable()){
//                                        System.out.println("recieveing files");
//                                        ClientServices.receiveFiles(inputStream, new File(path).getAbsoluteFile());
//                                    }
//                                }
//                            });break;   }

                        
//                        case Commands.UPLOAD_FILES :   {
//                            Server.submitInputTask(new ServerUploadFile(inputStream,new File(path).getAbsoluteFile()));
//                            break;   }
                        
                        
                        
//                        case Commands.DOWNLOAD_FILES :   {
//                            Server.submitOutputTask(new Runnable(){
//                                @Override
//                                public void run(){
//                                    if(areResourcesAvailable()){
//                                        List<File> files = ClientServices.recieveFileList(inputStream);
//                                        ClientServices.sendFiles(outputStream,files);
//                                    }
//                                }
//                            });break;   }
                                                        
//                        case Commands.REMOVE_FILES :   {
//                            Server.submitInputTask(new Runnable(){
//                                @Override
//                                public void run(){
//                                    if(areResourcesAvailable()){
//                                        List<File> files = ClientServices.recieveFileList(inputStream);
//                                        ClientServices.removeFiles(files);
//                                    }
//                                }
//                            });break;   }
                        
                    case Commands.UPLOAD_FILES: {
                        ClientServices.receiveFiles(this.inputStream, new File(path));
                        break;
                    }

                    case Commands.GET_FILE_LIST: {
                        System.out.println("get file list");
                        if (areResourcesAvailable()) {
                            System.out.println("Sending files");
                            ClientServices.sendFilesList(outputStream, FileManager.getFilesFrom(new File(path)));
                        }
                        break;
                    }

                    case Commands.DOWNLOAD_FILES: {
                        if (areResourcesAvailable()) {
                            List<File> files = ClientServices.recieveFileList(inputStream);
                            ClientServices.sendFiles(outputStream, files);
                        }
                        break;
                    }
                        
                        case Commands.REMOVE_FILES: {
                            if (areResourcesAvailable()) {
                                List<File> files = ClientServices.recieveFileList(inputStream);
                                ClientServices.removeFiles(files);
                            }
                            break;
                        }
                        
                        default:    closeThisServer();
                                    break;
                                                            
			//and further add any functionality add here and this constants belong 
			
		}
	}
        
        private  String processUserLogin() {
            if(clientSocket.isClosed()) return null;
            System.out.println("connection"+clientSocket.toString());
                try{
                    //authenticate user and send reply accordingly
                    String name = inputStream.readUTF();
                    String password = inputStream.readUTF();
                    
                    if(DatabaseManager.processUserLogin(name,password)){ outputStream.writeInt(Commands.LOGIN_SUCCESSFUL);outputStream.flush();return name;}
                    else {outputStream.writeInt(Commands.INVALID_CREDENTIALS);
                    outputStream.flush();
                    }
                    return null;
                } catch(Exception e) {
                    return null;
                }
        }
        
        private String processUserRegistration(){
            if(clientSocket.isClosed()) return null;
            try {
                String name = inputStream.readUTF();
                String password = inputStream.readUTF();
                    
                if(DatabaseManager.recordExists(name, password)){
                    this.outputStream.writeInt(Commands.USER_ALREADY_EXISTS);
                    name = null;
                }
                else{   if(DatabaseManager.processUserRegistration(name, password))
                    this.outputStream.writeInt(Commands.LOGIN_SUCCESSFUL);
                }
                this.outputStream.flush();
                return name;
            } catch (Exception e) {
                return null;
            }
        }
        
        private void setUserPath(String username){
            try{
                    this.path = new File(CustomSettings.getDestinationFolder(),username).getCanonicalPath();
                    System.out.println(path);
                }catch(IOException ie){ System.out.println(ie);}
            
                //create directory if not exists
                FileManager.createDirectory(new File(path));
        }
        
        private boolean areResourcesAvailable(){
            return !(username == null || path == null );
        }
}
