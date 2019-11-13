/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
 
/**
 *
 * @author jaikishorgohil
 */
public class Server extends Thread{
    //All declarations
	private ServerSocket serverSocket = null;
	private Socket clientSocket  = null;
	private int port = 4444;
	private volatile boolean stopServer = false;
        private static List<Socket> clients = null; 
        private static ThreadPool connectionHandler = null;
	


        //Overloaded Constructors
	public Server(){
            initializeComponents();
        }

	public Server(int port){
            this.port  = port;
            initializeComponents();
            start();
	}
        
        private void initializeComponents(){
            clients = new ArrayList<>();
            connectionHandler = new ThreadPool("ConnectionHandler", 3);
            
            setEnvironment();
            try { DatabaseManager.createDatabaseManager(); } catch (Exception e) { System.out.println("Some error has occured while create database!!"+e.getMessage());closeServer();}
        }

	//All instance methods
        @Override
	public void run(){

	//Trying to create a server socket
		try
		{
                    logger("Creating server !");
                    serverSocket = new ServerSocket(port);
		}catch(IOException ie){
		}catch(Exception e){
		}

	//Now wait for clients to connect
                logger("Starting server");
		while(!stopServer){
			try
			{
                            clientSocket = serverSocket.accept();
                            clients.add(clientSocket);
                            System.out.println("got a client");
                            handleClient(new ServerConnectionObject(clientSocket));
			}catch(IOException ie){
                            logger(ie.getMessage());
			}catch(Exception e){
                            logger(e.getMessage());
			}
		}
	}

	public void closeServer(){
            logger("Closing the main server!");
            stopServer = true;
                try {
                    if(serverSocket != null)
                        serverSocket.close();
                    if(clientSocket != null)
                        clientSocket.close();
                    for (Socket client : clients) {
                        client.close();
                    }
                    DatabaseManager.close();
                    if(connectionHandler != null)
                    connectionHandler.shutdownAndAwaitTermination();
                } catch (IOException e) {
                } catch(Exception e) {
                }
		//and other shutting down procedure for stopping the server

	}
        
         
        

        
        public final void setEnvironment() {
            logger("Setting environment!");
            //check if there is cutom file destination specified by user or default location and set the destinationfolder accordingly
            File destinationFolder = CustomSettings.getDestinationFolder();//get the File path from CustomSettings
            logger(destinationFolder.toString());
            if(!destinationFolder.isAbsolute()){
                if(destinationFolder.getName().equals(".")){
                    destinationFolder = new File(destinationFolder.getAbsoluteFile().getParent());
                    destinationFolder = new File(destinationFolder, "appdata");
                    FileManager.createDirectory(destinationFolder);
                    CustomSettings.setDestinationFolder(destinationFolder);
                    logger(destinationFolder.toString());
                }
                else{
                    destinationFolder = destinationFolder.getAbsoluteFile();
                    CustomSettings.setDestinationFolder(destinationFolder);
                    logger(destinationFolder.toString());
                }
            }
            System.out.println("Server folder"+destinationFolder);
            FileManager.createFile(new File(destinationFolder,DatabaseManager.DB_NAME+".db"));
        
        
    }//end of set environment
        
        
    public static void handleClient(Runnable task){
        connectionHandler.addTask(task);
    }
        
    public static void deleteRecord(Socket client){
        clients.remove(client);
    }
    
    public static List<Socket> getClients(){
        return clients;
    }
        
        
    private static void logger(String message){
        System.out.println("Logger: "+message);
    }
}
