package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Spielmaker;
import sample.exceptions.ForbiddenColorException;
import sample.exceptions.GameOverException;
import sample.model.Color;
import sample.model.player.ArtificialPlayer;
import sample.model.player.Player;


/**
 * Created by dudzik on 26.11.14.
 */
public class FieldController {
    public Label scoreP2;
    public Label scoreP1;
    public Rectangle playerTwoBackground;
    public Rectangle playerOneBackground;
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
                (ov, old_val, new_val) -> goForIt((Color) new_val));

        refreshPlayingField();
        refreshPlayerScore();
    }

    private void goForIt(Color newColor){
        try {
            System.out.println("Color changed to " + newColor.name());

            spielmaker.getActivePlayer().makeMove(newColor);

            if (spielmaker.getInactivePlayer() instanceof ArtificialPlayer)
                (spielmaker.getInactivePlayer()).makeMove(null);
            else
                spielmaker.switchActivePlayer();

            refreshPlayingField();
            refreshPlayerScore();

        } catch (ForbiddenColorException e) {

            //MessageBox.showDialog("Message Here...");
        } catch (GameOverException e) {
            e.printStackTrace();
        }
    }

    private void refreshPlayerScore(){
        scoreP1.setText(String.valueOf(spielmaker.getPlayer1().getPoints()));
        scoreP2.setText(String.valueOf(spielmaker.getPlayer2().getPoints()));
        boolean flag = spielmaker.getActivePlayer() == spielmaker.getPlayer1();
        playerOneBackground.setVisible(flag);
        playerTwoBackground.setVisible(!flag);
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
