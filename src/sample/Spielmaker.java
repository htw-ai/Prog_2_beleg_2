package sample;

import sample.model.Color;
import sample.model.ColorField;
import sample.model.HumanPlayer;
import sample.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private Random rnd = new Random();
    private Player player1 = new HumanPlayer();
    private Player player2;
    private List<Color> colors;

    public Spielmaker(ColorField[][] fields, Player enemy, int colorCount) {
        this.fields = fields;
        player2 = enemy;
        player1.setActive(true);
        initColors(colorCount);
    }

    private void initColors(int colorCount){
        colors = new ArrayList<Color>(colorCount);
        for ( Color color : Color.values())
        {
            if(colorCount-- <= 0)
                break;
            colors.add(color);
        }
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
