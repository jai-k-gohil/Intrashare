/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;

/**
 *
 * @author jaikishorgohil
 */
public class CustomSettings {
    private static File destinationFolder = new File("C:\\Users\\Jai\\Desktop\\intrashare\\intrasharevf\\data");
    private static File downloadFolder = new File("C:\\Users\\Jai\\Desktop\\timepass");
    private static boolean compression;
    public static String userDirectoryName = "appdata";
    
    
    public static void setDestinationFolder(File path){
        destinationFolder = path;
    }
    
    public static File getDestinationFolder(){
        return destinationFolder;
    }
    
    public static void setDownloadFolder(File path){
        downloadFolder = path;
    }
    
    public static File getDownloadFolder(){
        return downloadFolder;
    }
    
    public static void setCompression(boolean flag){
        compression = flag;
    }
    
    public static boolean isCompressionEnabled(){
        return compression;
    }
}
