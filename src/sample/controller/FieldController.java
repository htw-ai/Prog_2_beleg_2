package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Spielmaker;
import sample.model.Color;
import sample.model.Player;


/**
 * Created by dudzik on 26.11.14.
 */
public class FieldController {
    private Spielmaker spielmaker;
    public ListView colorList;
    public GridPane playingField;

    public void init(int fieldSize, Player player, int colorsCount){
        this.spielmaker = new Spielmaker(fieldSize, player, colorsCount);
        initUI();
    }

    /**
     * instantiates the UI
     */
    private void initUI(){
        ObservableList<Color> colors = FXCollections.observableArrayList();
        colors.addAll(spielmaker.getColors());
        colorList.setItems(colors);

        colorList.getSelectionModel().selectedItemProperty().addListener(
                (ov, old_val, new_val) -> {
                    Color newColor = (Color) new_val;
                    System.out.println("Color changed to " + newColor.name());
                    spielmaker.chooseColor(newColor, false);
                    refreshPlayingField();
                    spielmaker.switchActivePlayer();
                });

        refreshPlayingField();
    }

    /**
     * sets the colors of the playing field
     */
    private void refreshPlayingField(){
        playingField.getChildren().clear();
        int tileSize = 30;
        for (int x = 0; x < spielmaker.getFields().length; x++) {
            for (int y = 0; y < spielmaker.getFields().length; y++) {
                Rectangle rectangel = new Rectangle(tileSize, tileSize);
                rectangel.setTranslateY(y * tileSize);
                rectangel.setTranslateX(x * tileSize);
                rectangel.setFill(spielmaker.getFields()[x][y].getColor().getFill());
                playingField.getChildren().add(rectangel);
            }
        }
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
