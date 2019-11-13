/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jaikishorgohil
 */
public class FileManager {
    //create file
	public static boolean createFile(File path){
		try{
                    return path.createNewFile();
		}catch(IOException ie){ 
		}
            return false;
	}//end of createFile
        
        public static boolean createDirectory(File directory){
            try {
               return directory.mkdir();
            } catch (Exception e) {
            }
            return false;
        }
        
        public static boolean deleteFile(File path){
            try {
                return path.delete();
            } catch (Exception e) {
            }
            return false;
        }//end of deleteFile

	//write object data into file
	public static boolean serializeObject(File file,Object obj){
		
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(IOException ie){ return false;   }
		}
		

		try(ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file))){
			objectStream.writeObject(obj);
                        return true;
            } catch (IOException e) {
                return false;
            }

	}//end of serializeObject

	public static Object deserializeObject(File file){
		try(ObjectInputStream inputStream= new ObjectInputStream(new FileInputStream(file))){
			return inputStream.readObject();
		}catch(IOException ie){
			return null;
		}catch(ClassNotFoundException cnfe){
                    return null;
                }catch(Exception e){
                    return null;
                }
	}//end of deserializeObject


	//get list of file from subfolders
	//this method returns files filtered according to regex
	public static List<File> getFilesFrom(File dir){
		return Arrays.asList(dir.listFiles());
	}//end of local
        
	//replace a file
}
