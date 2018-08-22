package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.lang.Object;


public class Controller implements Initializable {

    @FXML
    private TextField txtMap;

    @FXML
    private TextField txtNaam;

    @FXML
    private Label lblError;

    @FXML
    private Label lblSucces;

    public void buttonHandler(ActionEvent event) {

        String _map = txtMap.getText();             //folder

        String _naam = txtNaam.getText();           //general name of what the files are gonna be named


        File myFolder = new File(_map);
        File[] file_array = myFolder.listFiles();


//        System.out.println(_map);
//        System.out.println(myFolder);


        if (myFolder.isDirectory()) {
            for (int i = 0; i < file_array.length; i++) {
                Arrays.sort( file_array, new Comparator()
                {
                    public int compare(Object o1, Object o2) {

                        if (((File)o1).lastModified() < ((File)o2).lastModified()) {
                            return -1;
                        } else if (((File)o1).lastModified() > ((File)o2).lastModified()) {
                            return +1;
                        } else {
                            return 0;
                        }
                    }

                });
                if (file_array[i].isFile()) {
                    File myFile = new File(_map + "\\" + file_array[i].getName());
                    String fileExtension = getFileExtension(myFile);
                    System.out.println(file_array[i]);

                    int number = i + 1;

                    myFile.renameTo(new File(_map + "\\" + _naam + " - " + number + fileExtension));
                }
            }
            System.out.println("-----------------------------------------");

        } else {
            lblError.setText("Geen path!!");

        }


        file_array = null;

    }

    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
