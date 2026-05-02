package dev.skystone;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class D20Controller {

    @FXML private CheckBox advantageBox;
    @FXML private CheckBox disadvantageBox;
    @FXML private CheckBox inspirationBox;

    @FXML private TextField critSuccessField;
    @FXML private TextField critFailField;
    @FXML private TextField rollCountField;

    @FXML private TextArea resultArea;

    private final CalcRolls calcRolls = new CalcRolls();
    private final CalcCrits calcCrits = new CalcCrits();

    @FXML
    public void initialize() {
        critSuccessField.setText("20");
        critFailField.setText("1");
        rollCountField.setText("1");
    }

    @FXML
    private void onRollClicked() {

        int rollCount;

        try {
            rollCount = Integer.parseInt(rollCountField.getText());
            if (rollCount <= 0) rollCount = 1;
            if (rollCount > 100) rollCount = 100; // prevent spam
        } catch (NumberFormatException e) {
            rollCount = 1;
        }

        StringBuilder results = new StringBuilder();

        for (int i = 0; i < rollCount; i++) {

            int roll = calcRolls.rollD20(
                    advantageBox.isSelected(),
                    disadvantageBox.isSelected(),
                    inspirationBox.isSelected()
            );

            String evaluated = calcCrits.evaluateCrit(
                    roll,
                    critSuccessField.getText(),
                    critFailField.getText()
            );

            results.append("Roll's count: ")
                   .append(i + 1)
                   .append(evaluated)
                   .append("\n");
        }

        resultArea.setText(results.toString());
    }
}