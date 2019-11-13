/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.ObjectInputStream;

/**
 *
 * @author Jai
 */
public class ServerUploadFile implements Runnable{
    private ObjectInputStream inputStream = null;
    private File path = null;
    public ServerUploadFile(ObjectInputStream inputStream,File path){
        this.inputStream = inputStream;
        this.path = path;
    }

    @Override
    public void run() {
        ClientServices.receiveFiles(this.inputStream, this.path);
    }
}
