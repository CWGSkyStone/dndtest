package dev.skystone;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {

    @FXML
    void onClickCloseButton(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onClickD100Button(ActionEvent event) {
        App.trySetRoot("d100Scene");
    }

    @FXML
    void onClickD20Button(ActionEvent event) {
        App.trySetRoot("d20Scene");
        App.setTitle("D20");
    }

}
