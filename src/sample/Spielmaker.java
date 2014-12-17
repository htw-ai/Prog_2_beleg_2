package sample;

import sample.model.Color;
import sample.model.ColorKey;
import sample.model.HumanPlayer;
import sample.model.Player;

import java.util.*;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private Color[][] fields;

    private List<ColorKey> activeColors;
    //private Map<ColorKey, Color> activeColors;
    private Random rnd = new Random();
    private Player player1 = new HumanPlayer();
    private Player player2;
    private List<Color> colors;

    public Spielmaker(int fieldCount, Player enemy, int colorCount) {
        player2 = enemy;
        player1.setActive(true);
        initColors(colorCount);
        this.fields = generateFieldColors(fieldCount, colorCount);
        // put the first field into activeColors
        // ToDo: put equal neighbors also into the map!
        activeColors = new ArrayList<ColorKey>(){{
            add(new ColorKey(0, 0));
        }};
    }

    private void initColors(int colorCount){
        colors = new ArrayList<>(colorCount);
        for ( Color color : Color.values())
        {
            if(colorCount-- <= 0)
                break;
            colors.add(color);
        }
    }

    private Color[][] generateFieldColors(int fieldCount, int colorCount) {
        Color[][] field = new Color[fieldCount][fieldCount];
        for (int x = 0; x < fieldCount; x++) {
            for (int y = 0; y < fieldCount; y++) {
                field[x][y] = colors.get(rnd.nextInt(colorCount));
            }
        }
        return field;
    }

    public int chooseColor(Color newColor){
        List<ColorKey>  newFields = new ArrayList<>();

        for (int i = 0; i < activeColors.size(); i++) {
            ColorKey key = activeColors.get(i);
            fields[key.getX()][key.getY()] = newColor;
//            compareNeighbors(key.getX(), key.getY(), newColor)
            newFields.addAll(compareNeighbors(key.getX(), key.getY(), newColor));
        }

        /*
        for(Iterator<ColorKey> it = activeColors.iterator(); it.hasNext(); ) {
            ColorKey key = it.next();
            fields[key.getX()][key.getY()] = newColor;
            newFields.addAll(compareNeighbors(key.getX(), key.getY(), newColor));
        }*/

        System.out.println(newFields.size() + " new field(s) with color " + newColor.name());
        for (ColorKey key : newFields){
            //activeColors.put(key, newColor);
            fields[key.getX()][key.getY()] = newColor;
        }

        return newFields.size();
    }

  /*  private List<ColorKey> recListAppender(List<ColorKey> fields2){



        for(Iterator<ColorKey> it = fields2.iterator(); it.hasNext(); ) {
            ColorKey key = it.next();
            fields[key.getX()][key.getY()] = newColor;
            newFields.addAll(compareNeighbors(key.getX(), key.getY(), newColor));
        }
    }*/

    private List<ColorKey> compareNeighbors(final int x, final int y, final Color c){
        ColorKey checkedKey;
        List<ColorKey> newKeys = new ArrayList<>();
        if (x != 0) { // not top
            checkedKey = addOnEqualColors(x - 1, y, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (y != 0) { // not left
            checkedKey = addOnEqualColors(x, y - 1, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (x != fields.length -1) { // not bottom
            checkedKey = addOnEqualColors(x + 1, y, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (y != fields[x].length -1) { // not right
            checkedKey = addOnEqualColors(x, y + 1, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        return newKeys;
    }

    private ColorKey addOnEqualColors(int posX, int posY, Color c){
        if (fields[posX][posY].name().equals(c.name())) {
            ColorKey key = new ColorKey(posX, posY);
            if (!activeColors.contains(key)){
                activeColors.add(key);
                return key;
            }
        }
        return null;
    }

    public void switchActivePlayer(){
        player1.setActive(!player1.isActive());
        player2.setActive(player1.isActive());
        //ToDo: call an event which is handled in FieldController for updating the UI
    }

    public Player getActivePlayer(){
        return player1.isActive() ? player1 : player2;
    }

    public Color[][] getFields() {
        return fields;
    }

    public List<Color> getColors() {
        return colors;
    }
}
