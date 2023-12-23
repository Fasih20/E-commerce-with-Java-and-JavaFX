module com.example.projectuitest {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;
    //requires com.almasb.fxgl.all;
    requires transitive java.desktop;

    opens com.example.nova_marketproject to javafx.fxml;
    exports com.example.nova_marketproject;
}