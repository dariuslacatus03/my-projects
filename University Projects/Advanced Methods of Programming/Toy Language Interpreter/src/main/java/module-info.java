module gui {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports view.gui;
    opens view.gui to javafx.fxml;

    exports controller;
    opens controller to javafx.fxml;

    exports controller.exceptions;
    opens controller.exceptions to javafx.fxml;

    exports model.expressions;
    opens model.expressions to javafx.fxml;

    exports model.programState;
    opens model.programState to javafx.fxml;

    exports model.statements;
    opens model.statements to javafx.fxml;

    exports model.types;
    opens model.types to javafx.fxml;

    exports model.adts;
    opens model.adts to javafx.fxml;

    exports model.values;
    opens model.values to javafx.fxml;

    exports repository;
    opens repository to javafx.fxml;

}