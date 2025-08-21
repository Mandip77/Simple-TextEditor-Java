package edu.bhcc.mandip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    private URL find(String... candidates) {
        for (String c : candidates) {
            URL u = App.class.getResource(c);
            if (u != null) return u;
        }
        return null;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Try both common locations for each resource
        URL fxml = find("/edu/bhcc/mandip/startpage.fxml", "/startpage.fxml");
        if (fxml == null) {
            throw new NullPointerException(
                    "Could not find startpage.fxml. Tried: /edu/bhcc/mandip/startpage.fxml and /startpage.fxml.\n" +
                            "Make sure the file is under src/main/resources in one of those locations.");
        }

        FXMLLoader loader = new FXMLLoader(fxml);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // CSS: also try both locations
        URL lightCss = find("/edu/bhcc/mandip/light.css", "/light.css");
        if (lightCss == null) {
            throw new NullPointerException(
                    "Missing light.css (looked in /edu/bhcc/mandip/ and /).");
        }
        scene.getStylesheets().add(lightCss.toExternalForm());

        Controller ctrl = loader.getController(); // your class is named 'controller'
        ctrl.init(primaryStage, scene);

        primaryStage.setTitle("Simple Text Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
