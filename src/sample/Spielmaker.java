package sample;

import sample.Model.ColorField;
import sample.Model.HumanPlayer;
import sample.Model.Player;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private Player player1 = new HumanPlayer();
    private Player player2;

    public Spielmaker(ColorField[][] fields, Player enemy) {
        this.fields = fields;
        player2 = enemy;
    }

    private void init(){

        player1.setActive(true);
    }

    private ColorField[][] fields;

    public ColorField[][] getFields() {
        return fields;
    }

    public void setFields(ColorField[][] fields) {
        this.fields = fields;
    }

    public void switchActivePlayer(){

    }

    public Player getActivePlayer(){
        return player1.isActive() ? player1 : player2;
    }
}