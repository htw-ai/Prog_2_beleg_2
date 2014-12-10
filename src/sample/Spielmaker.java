package sample;

import sample.model.ColorField;
import sample.model.HumanPlayer;
import sample.model.Player;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private Player player1 = new HumanPlayer();
    private Player player2;
    private int colorsCount;

    public Spielmaker(ColorField[][] fields, Player enemy, int colorCount) {
        this.fields = fields;
        player2 = enemy;
        this.colorsCount = colorsCount;
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
