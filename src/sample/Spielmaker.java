package sample;

import sample.model.Color;
import sample.model.HumanPlayer;
import sample.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private Color[][] fields;
    private Random rnd = new Random();
    private Player player1 = new HumanPlayer();
    private Player player2;
    private List<Color> colors;

    public Spielmaker(int fieldCount, Player enemy, int colorCount) {
        player2 = enemy;
        player1.setActive(true);
        initColors(colorCount);
        this.fields = generateFieldColors(colorCount, fieldCount);
    }

    private Color[][] generateFieldColors(int colorCount, int fieldCount) {
        Color[][] field = new Color[fieldCount][fieldCount];
        for (int i = 0; i < fieldCount; i++) {
            for (int j = 0; j < fieldCount; j++) {
                field[i][j] = colors.get(rnd.nextInt(colorCount));
            }
        }
        return field;
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

    public Color[][] getFields() {
        return fields;
    }

    public void chooseColor(Color newColor){
        Color activeColor = fields[0][0];
        for (int x = 0; x < fields.length; x++){
            for (int y = 0; y < fields[x].length; y++){
                if(fields[x][y] == activeColor)
                    fields[x][y] = newColor;
            }
        }
    }

    public void switchActivePlayer(){

    }

    public Player getActivePlayer(){
        return player1.isActive() ? player1 : player2;
    }

    public List<Color> getColors() {
        return colors;
    }
}
