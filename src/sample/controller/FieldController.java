package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sample.Spielmaker;
import sample.model.Color;
import sample.model.ColorField;
import sample.model.Player;

/**
 * Created by dudzik on 26.11.14.
 */
public class FieldController {
    private Spielmaker spielmaker;
    public ListView colorList;

    public void initData(int fieldSize, Player player, int colorsCount){
        this.spielmaker = new Spielmaker(new ColorField[fieldSize][fieldSize], player, colorsCount);
        ObservableList<Color> colors = FXCollections.observableArrayList();
        colors.addAll(spielmaker.getColors());
        colorList.setItems(colors);
    }

    public void newGame(ActionEvent actionEvent) throws Exception{
        Node node=(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../view/menu.fxml"));/* Exception */
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
