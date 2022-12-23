module edu.bhcc.mandip {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens edu.bhcc.mandip to javafx.fxml;
    exports edu.bhcc.mandip;
}
