package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import sample.model.player.EasyPlayer;
import sample.model.player.HardPlayer;
import sample.model.player.HumanPlayer;
import sample.model.player.Player;

/**
 * Created by root on 26.11.14.
 *
 * controls the game
 */
public class MenuController {

    public Slider fieldsSlider;
    public Slider colorsSlider;
    public ToggleGroup opponent;

    @FXML
    public void startGame(ActionEvent actionEvent) throws Exception{
        int fieldsCount = (int) Math.round(fieldsSlider.getValue());
        int colorsCount = (int) Math.round(colorsSlider.getValue());
        Player player = null;

        String radioBtn = ((RadioButton) opponent.getSelectedToggle()).getId();
        if (radioBtn.equals("playerRadBtn")){
            player = new HumanPlayer("player 2");
            System.out.println("multiplayer mode enabled");
        } else if (radioBtn.equals("easyRadBtn")) {
            player = new EasyPlayer();
            System.out.println("easy mode enabled");
        } else if (radioBtn.equals("hardRadBtn")) {
            player = new HardPlayer();
            System.out.println("hard mode enabled");
        }

        Node node = (Node) actionEvent.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/field.fxml"));
        stage.setScene(new Scene(loader.load()));
        FieldController controller = loader.getController();
        controller.init(fieldsCount, player, colorsCount);

        stage.show();

        System.out.println("" + fieldsCount + " fields");
        System.out.println("" + colorsCount + " different colors");
    }
}
