package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class Menu {

    public Slider fieldsSlider;
    public Slider colorsSlider;
    public ToggleGroup enemy;

    @FXML
    public void startGame(ActionEvent actionEvent) throws Exception{
        double fieldsCount = fieldsSlider.getValue();
        double colorsCount = colorsSlider.getValue();
        String radioBtn = ((RadioButton) enemy.getSelectedToggle()).getId();
        if (radioBtn.equals("playerRadBtn")){
            System.out.println("chose multiplayer mode");
        } else if (radioBtn.equals("kiRadBtn")) {
            System.out.println("chose ki mode");
        }

        Node node=(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("field.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        System.out.println("chose " + fieldsCount + " fields");
        System.out.println("chose " + colorsCount + " different colors");

    }
}
