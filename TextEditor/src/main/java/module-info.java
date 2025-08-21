module edu.bhcc.mandip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens edu.bhcc.mandip to javafx.fxml; // FXML needs reflective access
    exports edu.bhcc.mandip;
}
