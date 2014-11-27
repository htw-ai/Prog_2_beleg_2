package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Menu {

    public Slider fieldsSlider;
    public Slider colorsSlider;
    public ToggleGroup enemy;

    @FXML
    public void startGame(ActionEvent actionEvent) {
        double fieldsCount = fieldsSlider.getValue();
        double colorsCount = colorsSlider.getValue();
        String radioBtn = ((RadioButton) enemy.getSelectedToggle()).getId();
        if (radioBtn.equals("playerRadBtn")){
            System.out.println("chose multiplayer mode");
        } else if (radioBtn.equals("kiRadBtn")) {
            System.out.println("chose ki mode");
        }

        System.out.println("chose " + fieldsCount + " fields");
        System.out.println("chose " + colorsCount + " different colors");

    }
}
