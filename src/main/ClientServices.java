/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author jaikishorgohil
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ClientServices {
    
    private static final int BUFFER_SIZE = 4092;
    
    public static void sendFiles(ObjectOutputStream outputStream, List<File> files) {
        if(outputStream == null){ System.out.println("socket is closed"); return;    }
        try{
            //writing list of files
            outputStream.writeObject(files);
            outputStream.flush();
            
            //sending the files
            int recievedBytes;
            byte[] buffer = new byte[BUFFER_SIZE];
            for (File file : files) {
                System.out.println("Entered file");
                //making stream for sending contents in file
                try(FileInputStream fileInput = new FileInputStream(file) ){
                    //write files to outputStream of socket
                    System.out.println("opened file inputStream");

                    while ((recievedBytes = fileInput.read(buffer)) > 0  && fileInput.available() > 0) {
                        outputStream.write(buffer,0, recievedBytes);
                        outputStream.flush();
                    }
                    System.out.println("Done writing file:"+file);
                }catch(FileNotFoundException fnfe){
                    //continue for next one dont wait
                    System.out.println(fnfe);
                }catch(IOException ie){
                    System.out.println("Error send files"+ie.getMessage());
                }catch(Exception e){
                }
            }//end of for loop
      } catch (Exception e){
             System.out.println("Error"+e.getMessage());
        }//end of try of ObjectOutputStream
    }//end
        
        
        public static void receiveFiles(ObjectInputStream inputStream,File path){
        if(inputStream == null || path == null) return;
        try{
            // System.out.println("recieveing files");
            // int numberOfFiles  = inputStream.readInt();//get number of files
            // System.out.println("Recieveing side files size"+numberOfFiles);
            
            List<File> files = (List<File>)inputStream.readObject();
            if(files == null){ System.out.println("Null returned by list of files"); return;}
            System.out.println("After getting files liat"+files.size());
            int receivedBytes;
            byte[] buffer = new byte[BUFFER_SIZE];
            //first check that file already exists or not
            for (File file : files) {
               System.out.println("Entered in for loop"); 
                try {
                    // String fileName = (String)inputStream.readUTF();
                    // System.out.println("Making files"+fileName);           
                    File newFile = new File(path,file.getName()).getCanonicalFile();//read file names and add it to list of files
                    System.out.println(newFile);
                    FileOutputStream outputStream = new FileOutputStream(newFile);
                    System.out.println("recieveing files!!");
                    //read file 
                   while ((receivedBytes = inputStream.read(buffer)) > 0) {
                       try{
                       		outputStream.write(buffer,0,receivedBytes);
                       		outputStream.flush();
                       	}catch(EOFException eofe){
                       		System.out.println("EOF recieve files"+eofe);
           					break;
                       	}
                   }
                    System.out.println("Writing file"+file);   
                }catch (FileNotFoundException fnfe) { 
                    System.out.println(fnfe);continue;
                }catch (IOException ie) {
                    System.out.println(ie);
                }catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("Next file please");
            }
        // }catch(IOException e){
        //     System.out.println("In Revceiving files method IOException"+e);
        //     e.printStackTrace();
        }catch(Exception e) {
            System.out.println("error recieveing files"+e.getMessage());
        }
    }
        
        
    public static void sendFilesList(ObjectOutputStream outputStream, List<File> files) {
        System.out.println("sending file list");
        if(outputStream == null) return;
        try{
            outputStream.writeObject(files);
            outputStream.flush();
        }catch(IOException ie){
        }catch(Exception e){
            System.out.println("Error sending file list "+e.getMessage());
        }
    }
    

    //remove Files specified in the list
    public static void removeFiles(List<File> files){
        files.forEach((file) -> {
            FileManager.deleteFile(file);
        });
    
    }    
    
    public static List<File> recieveFileList(ObjectInputStream inputStream) {
        if(inputStream == null) return null;
        System.out.println("Recieveing file list");
        try{
            return (List<File>)inputStream.readObject();
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    
    public static boolean initiateUserLogin(ObjectOutputStream outputStream,ObjectInputStream inputStream,String name,String password){
        //if(inputStream != null || outputStream != null){ System.out.println("Did not get resources");return false;}
        try
        {
            outputStream.writeInt(Commands.USER_LOGIN);
            outputStream.flush();
            outputStream.writeUTF(name);
            outputStream.flush();
            outputStream.writeUTF(password);
            outputStream.flush();
            if(Commands.LOGIN_SUCCESSFUL == inputStream.readInt())  return true;
        }catch(IOException ie){}
       return false;
    }
    
    public static int initiateUserRegistration(ObjectOutputStream outputStream,ObjectInputStream inputStream,String name,String password){
        //if(outputStream == null || inputStSream == null) return -1;
        try
        {
            outputStream.writeInt(Commands.USER_REGISTER);
            outputStream.flush();
            outputStream.writeUTF(name);
            outputStream.flush();
            outputStream.writeUTF(password);
            outputStream.flush();
            System.out.println("Sending detils!");
            return inputStream.readInt();
        }catch(IOException ie){}
       return -1;
    }
    
    public static int readQuery(ObjectInputStream inputStream){
        if(inputStream == null) return -1;
        try {
            return inputStream.readInt();
        } catch (Exception e) {
        }
        return -1;
    }
    
    public static void sendQuery(ObjectOutputStream outputStream,int query){
        if(outputStream == null) return;
        try{
            outputStream.writeInt(query);
            outputStream.flush();
            System.out.println("after writing qurey");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
} 
