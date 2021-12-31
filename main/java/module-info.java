module corn_snake {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.databind;

    opens corn_snake to javafx.graphics, javafx.fxml;
    opens corn_snake.front_end.controllers to javafx.graphics, javafx.fxml;
    exports corn_snake;
    exports corn_snake.facade;
    exports corn_snake.back_end;
}