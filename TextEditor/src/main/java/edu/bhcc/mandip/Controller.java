package edu.bhcc.mandip;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Controller {
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

        // Try to reflect current theme in the checkbox (optional)
        boolean isDark = scene.getStylesheets().stream().anyMatch(s -> s.contains("dark.css"));
        if (darkMode != null) darkMode.setSelected(isDark);

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
        applyTheme(darkMode != null && darkMode.isSelected());
    }

    private void applyTheme(boolean useDark) {
        // Remove only previous theme sheets, not all styles
        scene.getStylesheets().removeIf(s ->
                s.endsWith("/dark.css") || s.endsWith("/light.css") ||
                        s.contains("dark.css") || s.contains("light.css"));

        final String cssName = useDark ? "dark.css" : "light.css";
        URL url = findTheme(cssName);

        System.out.println("[Theme] Requested: " + cssName + " -> " + url);

        if (url == null) {
            showError("Stylesheet not found: " + cssName +
                    "\nLooked for: /edu/bhcc/mandip/" + cssName + " and /" + cssName +
                    "\nEnsure the CSS is under src/main/resources.");
            return; // Don't crash if missing
        }

        scene.getStylesheets().add(url.toExternalForm());
    }

    private URL findTheme(String cssName) {
        // Try package path first, then classpath root
        String[] candidates = {
                "/edu/bhcc/mandip/" + cssName,
                "/" + cssName
        };
        for (String c : candidates) {
            URL u = App.class.getResource(c);
            System.out.println("[Theme] Tried " + c + " -> " + u);
            if (u != null) return u;
        }
        return null;
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
