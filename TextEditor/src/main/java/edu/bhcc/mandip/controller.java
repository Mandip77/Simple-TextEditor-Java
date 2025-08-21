package edu.bhcc.mandip;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class controller { // keep your name if you must, but prefer 'Controller'
    @FXML private MenuItem open;
    @FXML private MenuItem save;
    @FXML private MenuItem saveAs;
    @FXML private MenuItem close;
    @FXML private CheckMenuItem darkMode;
    @FXML private TextArea txtarea;

    private Stage stage;
    private Scene scene;
    private File currentFile = null;
    private boolean dirty = false;

    public void init(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;

        // Track edits
        txtarea.textProperty().addListener((obs, oldV, newV) -> {
            dirty = true;
            updateTitle();
        });
        updateTitle();
    }

    // ===== File actions =====

    @FXML
    void openfile(ActionEvent event) {
        if (!maybeContinue()) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File fileToLoad = fileChooser.showOpenDialog(stage);
        if (fileToLoad != null) {
            String content = readFile(fileToLoad);
            if (content != null) {
                txtarea.setText(content);
                currentFile = fileToLoad;
                dirty = false;
                updateTitle();
            }
        }
    }

    @FXML
    void savefile(ActionEvent event) {
        if (currentFile == null) {
            saveAsFile();
        } else {
            writeFile(currentFile, txtarea.getText());
            dirty = false;
            updateTitle();
        }
    }

    @FXML
    void saveAsFile(ActionEvent event) { saveAsFile(); }

    private void saveAsFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            writeFile(file, txtarea.getText());
            currentFile = file;
            dirty = false;
            updateTitle();
        }
    }

    @FXML
    void exitfile(ActionEvent event) {
        if (maybeContinue()) {
            Platform.exit();
        } else {
            event.consume();
        }
    }

    // ===== Theme toggle =====
    @FXML
    void toggleDarkMode(ActionEvent e) {
        scene.getStylesheets().clear();
        String css = darkMode.isSelected() ? "dark.css" : "light.css";
        scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
    }

    // ===== Helpers =====
    private boolean maybeContinue() {
        if (!dirty || txtarea.getText().isEmpty()) return true;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "You have unsaved changes. Save before continuing?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.setHeaderText(null);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.YES) {
                savefile(null);
                return !dirty; // true if save succeeded
            } else if (result.get() == ButtonType.NO) {
                return true;
            }
        }
        return false; // CANCEL or closed
    }

    private void updateTitle() {
        if (stage == null) return;
        String name = (currentFile == null) ? "Untitled" : currentFile.getName();
        stage.setTitle((dirty ? "*" : "") + name + " - Simple Text Editor");
    }

    private String readFile(File fileToLoad) {
        try {
            // Preserve newlines and UTF-8
            return Files.readString(fileToLoad.toPath(), StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(SEVERE, null, ex);
            showError("Failed to open file:\n" + ex.getMessage());
            return null;
        }
    }

    private void writeFile(File file, String content) {
        try {
            Files.write(file.toPath(), content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(SEVERE, null, ex);
            showError("Failed to save file:\n" + ex.getMessage());
        }
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
