module dev.skystone {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.fasterxml.jackson.databind;

    opens dev.skystone to javafx.fxml, com.fasterxml.jackson.databind;
    exports dev.skystone;
}
