package dev.skystone;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class D20Controller {

    @FXML private ToggleButton advantageToggle;
    @FXML private ToggleButton disadvantageToggle;
    @FXML private ToggleButton inspirationToggle;

    @FXML private RadioButton meleeRadio;
    @FXML private RadioButton rangedRadio;

    @FXML private TextField critSuccessField;
    @FXML private TextField critFailField;
    @FXML private TextField rollCountField;

    @FXML private TextArea resultArea;

    @FXML private VBox inspirationBox;
    @FXML private Label inspirationLabel;

    @FXML
    private ComboBox<Weapon> weaponBox;

    private final CalcRolls calcRolls = new CalcRolls();
    private final CalcCrits calcCrits = new CalcCrits();

    private int pendingRoll;
    private int currentIndex;
    private int totalRolls;
    private StringBuilder results;
    private boolean inspirationUsed;

    private String getAttackType() {
        return meleeRadio.isSelected() ? "MELEE" : "RANGED";
    }

    @FXML
    public void initialize() {
        ToggleGroup group = new ToggleGroup();

        meleeRadio.setToggleGroup(group);
        rangedRadio.setToggleGroup(group);

        critSuccessField.setText("20");
        critFailField.setText("1");
        rollCountField.setText("1");

        weaponBox.getItems().addAll(
                new Weapon("Sword", 1, 6, 0),
                new Weapon("Sword", 1, 8, 0),
                new Weapon("Sword", 1, 10, 0),
                new Weapon("Sword", 1, 12, 0),
                new Weapon("Sword", 1, 20, 0),
                new Weapon("Sword", 2, 6, 0)
        );

        weaponBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onRollClicked() {

        try {
            totalRolls = Integer.parseInt(rollCountField.getText());
            if (totalRolls <= 0) totalRolls = 1;
            if (totalRolls > 100) totalRolls = 100;
        } catch (Exception e) {
            totalRolls = 1;
        }

        results = new StringBuilder();
        currentIndex = 0;
        inspirationUsed = false;
        inspirationToggle.setDisable(false);

        processNextRoll();
    }

    private void processNextRoll() {

        if (currentIndex >= totalRolls) {
            resultArea.setText(results.toString());
            return;
        }

        int critSuccess = Integer.parseInt(critSuccessField.getText());
        int critFail = Integer.parseInt(critFailField.getText());

        int roll = calcRolls.rollD20(
                advantageToggle.isSelected(),
                disadvantageToggle.isSelected(),
                false
        );

        boolean needsPrompt = (roll <= critFail) || (roll < critSuccess) ;

        if (needsPrompt && inspirationToggle.isSelected() && !inspirationUsed) {

            pendingRoll = roll;

            inspirationLabel.setText(
                    "Roll " + (currentIndex + 1) +
                    ": " + roll + " → Use Inspiration?"
            );

            inspirationBox.setVisible(true);
            inspirationBox.setManaged(true);
            return;
        }

        appendResult(roll);
        currentIndex++;
        processNextRoll();
    }

    @FXML
    private void onUseInspiration() {

        inspirationUsed = true;
        inspirationToggle.setDisable(true);

        int reroll = calcRolls.rollD20(
                advantageToggle.isSelected(),
                disadvantageToggle.isSelected(),
                false
        );

        int finalRoll = Math.max(pendingRoll, reroll);

        results.append("Roll ")
               .append(currentIndex + 1)
               .append(": Used Inspiration → ")
               .append(calcCrits.evaluateCrit(
                       finalRoll,
                       critSuccessField.getText(),
                       critFailField.getText(),
                       getAttackType()
               ))
               .append("\n");

        hidePrompt();
        currentIndex++;
        processNextRoll();
    }

    @FXML
    private void onKeepRoll() {
        appendResult(pendingRoll);
        hidePrompt();
        currentIndex++;
        processNextRoll();
    }

    private void appendResult(int roll) {

        results.append("Roll's count: ")
               .append(currentIndex + 1)
               .append(calcCrits.evaluateCrit(
                       roll,
                       critSuccessField.getText(),
                       critFailField.getText(),
                       getAttackType()
               ))
               .append("\n");
    }

    private void hidePrompt() {
        inspirationBox.setVisible(false);
        inspirationBox.setManaged(false);
    }
}