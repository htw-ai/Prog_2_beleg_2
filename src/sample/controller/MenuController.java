package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.model.HumanPlayer;
import sample.model.Player;
import sample.model.SmartPlayer;

public class MenuController {

    public Slider fieldsSlider;
    public Slider colorsSlider;
    public ToggleGroup enemy;

    @FXML
    public void startGame(ActionEvent actionEvent) throws Exception{
        int fieldsCount = (int) Math.round(fieldsSlider.getValue());
        int colorsCount = (int) Math.round(colorsSlider.getValue());
        Player player = null;

        String radioBtn = ((RadioButton) enemy.getSelectedToggle()).getId();
        if (radioBtn.equals("playerRadBtn")){
            player = new HumanPlayer();
            System.out.println("multiplayer mode enabled");
        } else if (radioBtn.equals("kiRadBtn")) {
            player = new SmartPlayer();
            System.out.println("ki mode enabled");
        }

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/field.fxml"));
        stage.setScene(new Scene((SplitPane) loader.load()));
        FieldController controller = loader.getController();
        controller.initData(fieldsCount, player, colorsCount);

        stage.show();

        System.out.println("" + fieldsCount + " fields");
        System.out.println("" + colorsCount + " different colors");
    }
}