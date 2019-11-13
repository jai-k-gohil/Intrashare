/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import java.io.File;
import java.util.List;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.CustomSettings;
import view.UserInterface;

/**
 *
 * @author Jai
 */
public class FileListViewController {
    private FileListView fileListView;
    
    
    
    
    public FileListViewController(FileListView fileListView ){
        this.fileListView = fileListView;
        //managiing events on download button
        fileListView.btn_download.setOnAction((ActionEvent event) -> {
            List<String> selectedItems = fileListView.getSelectedFiles();
            if(selectedItems.size() < 1) new CustomDialog("Caution", "No File is selected", "Please select the file to proceed!");
            else{
                List<File> selectedFiles = fileListView.containsFile(UserInterface.client.getClientFiles(),selectedItems);
                if(selectedFiles == null && selectedFiles.size() <= 0) return;
                
                
                Task downloadData = new Task<Void>(){
                    @Override
                    protected Void call(){
                        UserInterface.client.downloadFiles(selectedFiles);
                        new CustomDialog("Notification", "Files are downloaded", "Files downloaded successfully");
                        return null;
                    }
                     
                };
                UserInterface.taskManager.submitTask(downloadData);
            }
        });
        
        
        //managing events on upload button
        fileListView.btn_uploadFile.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Files To Upload");
            fileChooser.setInitialDirectory(CustomSettings.getDestinationFolder());
            List<File> files = fileChooser.showOpenMultipleDialog(fileListView.getScene().getWindow());
            if(files == null) return;
            files.stream().forEach((file) -> {
                System.out.println("Sending "+file);
            });
            if(files == null) return;
            
            Task uploadData = new Task<Void>(){
                    @Override
                    protected Void call(){
                        UserInterface.client.uploadFiles(files);
                        new CustomDialog("Notification", "Files are uploaded", "Files uploaded successfully ");
                        return null;
                    }
            };
            
            UserInterface.taskManager.submitTask(uploadData);
        });
        
        
        
        fileListView.btn_uploadFolder.setOnAction((ActionEvent event) -> {
            final DirectoryChooser directoryChooser = new DirectoryChooser();
            final File selectedDirectory = directoryChooser.showDialog(fileListView.getScene().getWindow());
            if (selectedDirectory != null) {
                System.out.println("Selected Directory"+selectedDirectory.getAbsolutePath());
            }
        });
        
        
        fileListView.btn_refresh.setOnAction((ActionEvent event) -> {
            Task getFileList = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    UserInterface.client.reloadFileList();
                    return null;
                }
            };
            
        });
        
        
        fileListView.btn_remove.setOnAction((ActionEvent event) -> {
            List<String> selectedItems = fileListView.getSelectedFiles();
            if(selectedItems.size() < 1) new CustomDialog("Caution", "No File is selected", "Please select the file to proceed!");
            else{
                List<File> selectedFiles = fileListView.containsFile(UserInterface.client.getClientFiles(),selectedItems);
                if (selectedFiles == null && selectedFiles.size() < 0){
                    System.out.println("got 0 files");
                    return;
                }
                    
                Task removeFiles = new Task<Void>(){
                    @Override
                    protected Void call() throws Exception {
                        UserInterface.client.removeFiles(selectedFiles);
                        System.out.println("Selected Files"+selectedFiles);
                        return null;
                    }
                
                };
                UserInterface.taskManager.submitTask(removeFiles);
            }
            updateFileView();            
        });
        
        fileListView.btn_close.setOnAction((ActionEvent event) -> {
            UserInterface.client.stop();
            ((Stage)fileListView.getScene().getWindow()).close();
        });
    }
    
    public void updateFileView(){
        List<File> files = UserInterface.client.getClientFiles();
        fileListView.reload(files);
    }
}
