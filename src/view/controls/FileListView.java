/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controls;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jai
 */
public class FileListView extends VBox{
    public Label lbl_title;
    public ImageView file_icon;
    public Button btn_uploadFile,btn_uploadFolder,btn_download,btn_remove,btn_refresh,btn_close;
    public HBox topContainer,midContainer;
    public CustomListView list;
    public List<File> files;
    
    public FileListView(){
        super();
        setComponents();
    }
    
    public FileListView(List<File> files){
        super();
        setComponents();
        this.files = files;
        for (File file : files) {
            list.add(new CustomHBoxCell(file.getName(),getStringSizeLengthFile(file.length()),new Date(file.lastModified()).toString(),getPermissionString(file)));
        }
    }
    
    
    private void setComponents(){
        lbl_title = new Label("My Files");
        this.setAlignment(Pos.CENTER_RIGHT);
        
        this.getStyleClass().add("filelist-view");
//        CustomHBoxCell cell = new CustomHBoxCell("File","FileSize","LastModified","Permissions:>>Read>>Write>>Execute");
//        CustomHBoxCell cell1 = new CustomHBoxCell("SecondFile","10mb","10/20/20","Permissions:>>Read>>Write>>Execute");
//        CustomHBoxCell cell2 = new CustomHBoxCell("ThirdFile","20Mb","3/4/2015","Permissions:>>Read>>Write>>Execute");
        list = new CustomListView();
//        list.add(cell);
//        list.add(cell1);
//        list.add(cell2);
        btn_uploadFile = new Button("Upload");
        btn_uploadFolder = new Button("Upload Folder");
        btn_download = new Button("Download");
        btn_remove  = new Button("Delete");
        btn_refresh = new Button("Refresh");
        btn_close = new Button("Close");
        
        btn_uploadFile.setId("btn-upload-file");
        btn_uploadFolder.setId("btn-upload-folder");
        btn_download.setId("btn-download");
        btn_remove.setId("btn-remove");
        btn_refresh.setId("btn-refresh");
        btn_close.setId("btn-close");
        
        topContainer = new HBox(lbl_title,btn_close);
        topContainer.setAlignment(Pos.CENTER_RIGHT);
        
        midContainer = new HBox(btn_refresh,btn_remove,btn_uploadFolder,btn_uploadFile,btn_download);
        midContainer.setAlignment(Pos.CENTER_RIGHT);
        this.getChildren().addAll(topContainer,midContainer,list);
        new FileListViewController(this);
    }
    
    
    
    public void reload(List<File> files){
        List<CustomHBoxCell> cells = new ArrayList<>();
        for (File file : files) {
            cells.add(new CustomHBoxCell(file.getName(),file.length()+"",new Date(file.lastModified()).toString(),getPermissionString(file)));
        }
        list.reload(cells);
    }
    
    public void clear(){
        list.clear();
    }
    
    public List<String> getSelectedFiles(){
        List<CustomHBoxCell> cells  = list.getSelections();
        List<String> fileNames = new ArrayList<>();
        for (CustomHBoxCell cell : cells) {
            fileNames.add(cell.getTextFromColumn(0));
        }
        System.out.println("files"+fileNames);
        return fileNames;
    }
    
    private String getPermissionString(File file){
        StringBuffer permissions = new StringBuffer("Permissions:");
        String permissionString;
            if(file.canRead()) permissions.append(">>Read");
            if(file.canWrite()) permissions.append(">>Write");
            if(file.canExecute()) permissions.append(">>Execute");
            
            if((permissionString=permissions.toString()).equals("Permissions:")) permissionString = "No Permissions";
            
            return permissionString;
    }
    
    public static String getStringSizeLengthFile(long size) {
        DecimalFormat df = new DecimalFormat("0.00");

        float sizeKb = 1024.0f;
        float sizeMb = sizeKb * sizeKb;
        float sizeGb = sizeMb * sizeKb;
        float sizeTerra = sizeGb * sizeKb;


        if(size < sizeMb)
            return df.format(size / sizeKb)+ " Kb";
        else if(size < sizeGb)
            return df.format(size / sizeMb) + " Mb";
        else if(size < sizeTerra)
            return df.format(size / sizeGb) + " Gb";

        return ""+size;
}
    
    
    public List<File> containsFile(List<File> files,List<String> fileNames){
        System.out.println("Files original:"+files);
        List<File> selectedFiles = new ArrayList<>();
        String fName;
        try{
            for (File file : files) {
                fName = file.getName();
                for (String fileName : fileNames){
                    if(fileName.equals(fName)) {
                        System.out.println("Comparing "+fileName+" with "+ file.getName());
                        selectedFiles.add(file);
                    }
                }
            }
        }catch(NullPointerException npe){
        }
        System.out.println("contains file"+selectedFiles);
        return selectedFiles;
    }
    
    
}
