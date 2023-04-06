module com.ales.fittrack {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.ales.fittrack to javafx.fxml;
    exports com.ales.fittrack;
    exports com.ales.fittrack.controllers;
    opens com.ales.fittrack.controllers to javafx.fxml;
}