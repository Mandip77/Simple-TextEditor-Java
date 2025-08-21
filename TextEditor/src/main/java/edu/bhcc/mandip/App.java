package edu.bhcc.mandip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("startpage.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Default to light theme
        scene.getStylesheets().add(getClass().getResource("light.css").toExternalForm());

        // Let controller know about stage+scene so it can toggle theme & manage window title
        controller ctrl = loader.getController();
        ctrl.init(primaryStage, scene);

        primaryStage.setTitle("Simple Text Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
