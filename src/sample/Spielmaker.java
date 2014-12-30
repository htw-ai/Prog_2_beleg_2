package sample;

import sample.model.*;

import java.util.*;

/**
 * Created by root on 27.11.14.
 */
public class Spielmaker {

    private Random rnd = new Random();

    private ColorField[][] fields;
    private Player player1 = new HumanPlayer();
    private Player player2;
    private List<Color> colors;

    public Spielmaker(int fieldCount, Player enemy, int colorCount) {
        initColors(colorCount);
        this.fields = generateFieldColors(fieldCount, colorCount);
        player2 = enemy;
        player1.setActive(true);
        // put the first field into activeColors
        player1.initColorsHold(fields[0][0]);
        player2.initColorsHold(fields[fieldCount -1][fieldCount -1 ]);
//        activeColors = new ArrayList<ColorKey>(){{
//            add(fields[0][0].getKey());
//        }};
        chooseColor(fields[0][0].getColor(), false);
        switchActivePlayer();
        chooseColor(fields[fieldCount -1][fieldCount -1 ].getColor(), false);
        switchActivePlayer();
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
                field[x][y].setKey(new ColorKey(x, y));
            }
        }
        field[0][0].setActive(true);
        return field;
    }

    /**
     * returns the points a player receives when choosing a certain color.
     * Can also update the fields on the edge of a players field range.
     *
     * @param newColor  the chosen color
     * @param isTest    if true, updates the UI
     * @return points a player receives for this certain color
     */
    public List<ColorField> chooseColor(Color newColor, boolean isTest){
        List<ColorField> newFields = new ArrayList<>();

        for (int i = 0; i < getActivePlayer().getColorsHold().size(); i++) {
            ColorKey key = getActivePlayer().getColorsHold().get(i).getKey();
            List<ColorField> neighbors = getNeighborsWithColor(key.getX(), key.getY(), newColor);
            newFields.addAll(neighbors);
            if (!isTest) {
                neighbors.stream().forEach(k -> {
                    k.setActive(true);
                    getActivePlayer().getColorsHold().add(k);
                    // activeColors.add(k.getKey());
                });
                fields[key.getX()][key.getY()].setColor(newColor);
            }

            //newFieldsCounter += neighbors.size();
        }

        System.out.println(newFields.size() + " consumable field(s) with color " + newColor.name());
        return newFields;
    }

    public void setFieldsActive(List<ColorField> fields){
        fields.stream().forEach(k -> {
            k.setActive(true);
            getActivePlayer().getColorsHold().add(k);
            //activeColors.add(k.getKey());
        });
    }

    private List<ColorField> getNeighborsWithColor(final int x, final int y, final Color color){
        ColorField checkedKey;
        List<ColorField> newKeys = new ArrayList<>();
        if (x != 0) { // top
            checkedKey = checkColorField(x - 1, y, color);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (y != 0) { // left
            checkedKey = checkColorField(x, y - 1, color);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (x != fields.length -1) { // bottom
            checkedKey = checkColorField(x + 1, y, color);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        if (y != fields[x].length -1) { // right
            checkedKey = checkColorField(x, y + 1, color);
            if (checkedKey != null)
                newKeys.add(checkedKey);
        }
        return newKeys;
    }

    private ColorField checkColorField(int posX, int posY, Color color){
        ColorField cf = fields[posX][posY];
        if (cf.getColor().name().equals(color.name()) && !cf.isActive()) {
            cf.setKey(new ColorKey(posX, posY));
            return cf;
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

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
