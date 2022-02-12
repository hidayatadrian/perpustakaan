module controller.perpustakaan {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;

    opens controller.perpustakaan to javafx.fxml;
    exports controller.perpustakaan;
}