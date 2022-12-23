package edu.bhcc.mandip;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

import java.io.*;

public class controller {

    @FXML
    private Menu Exit;

    @FXML
    private MenuItem close;

    @FXML
    private Menu file;

    @FXML
    private MenuItem open;

    @FXML
    private MenuItem save;

    @FXML
    private TextArea txtarea;

    @FXML
    void exitfile(ActionEvent event) {
        String checker;
        checker = txtarea.getText();
        if (checker.isEmpty()) {
            Platform.exit();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit without saving the file?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            java.util.Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.YES) {
                Platform.exit();
            } else {
                event.consume();
            }
        }
    }

    @FXML
    void openfile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File fileToLoad = fileChooser.showOpenDialog(null);

        if (fileToLoad != null) {
            txtarea.setText(readFile(fileToLoad));
        }
    }

    private String readFile(File fileToLoad) {
        StringBuilder stringBuffer = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(fileToLoad));

            String text;
            while ((text = bufferedReader.readLine()) != null) {
                stringBuffer.append(text);
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(SEVERE, null, ex);
        } finally {
            try {
                assert bufferedReader != null;
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(SEVERE, null, ex);
            }
        }

        return stringBuffer.toString();
    }

    @FXML
    void savefile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            SaveFile(txtarea.getText(), file);
        }
    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(SEVERE, null, ex);
        }
    }

}
