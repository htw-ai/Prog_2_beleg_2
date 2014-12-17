package sample;

import sample.model.*;

import java.util.*;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private ColorField[][] fields;

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
        chooseColor(fields[0][0].getColor());
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

    private ColorField[][] generateFieldColors(int fieldCount, int colorCount) {
        ColorField[][] field = new ColorField[fieldCount][fieldCount];
        for (int x = 0; x < fieldCount; x++) {
            for (int y = 0; y < fieldCount; y++) {
                field[x][y] = new ColorField(colors.get(rnd.nextInt(colorCount)));
            }
        }
        field[0][0].setActive(true);
        return field;
    }

    public int chooseColor(Color newColor){
        List<ColorKey> newFields = new ArrayList<>();

        for (int i = 0; i < activeColors.size(); i++) {
            ColorKey key = activeColors.get(i);
            fields[key.getX()][key.getY()].setColor(newColor);
//            compareNeighbors(key.getX(), key.getY(), newColor)
            newFields.addAll(compareNeighbors(key.getX(), key.getY(), newColor));
        }

        System.out.println(newFields.size() + " new field(s) with color " + newColor.name());
//        for (ColorKey key : newFields){
//            //activeColors.put(key, newColor);
//            //ToDo: just in the first round:
//            fields[key.getX()][key.getY()].setColor(newColor);
//        }

        return newFields.size();
    }

    private List<ColorKey> compareNeighbors(final int x, final int y, final Color c){
        ColorKey checkedKey;
        List<ColorKey> newKeys = new ArrayList<>();
        if (x != 0) { // top
            checkedKey = addOnEqualColors(x - 1, y, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (y != 0) { // left
            checkedKey = addOnEqualColors(x, y - 1, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (x != fields.length -1) { // bottom
            checkedKey = addOnEqualColors(x + 1, y, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (y != fields[x].length -1) { // right
            checkedKey = addOnEqualColors(x, y + 1, c);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        return newKeys;
    }

    private ColorKey addOnEqualColors(int posX, int posY, Color c){
        ColorField cf = fields[posX][posY];
        if (cf.getColor().name().equals(c.name())) {
            //ColorKey key = new ColorKey(posX, posY);
            if (!cf.isActive()){
                //activeColors.add(key);
                cf.setActive(true);
                ColorKey key = new ColorKey(posX, posY);
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

    public ColorField[][] getFields() {
        return fields;
    }

    public List<Color> getColors() {
        return colors;
    }
}
