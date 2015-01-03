package sample;

import sample.model.*;
import sample.model.player.HumanPlayer;
import sample.model.player.Player;

import java.util.*;

/**
 * Created by root on 27.11.14.
 *
 * controls the game
 */
public class Spielmaker {

    private Random rnd = new Random();

    private ColorField[][] fields;
    private Player player1 = new HumanPlayer("player 1");
    private Player player2;
    private List<Color> colors;

    public Spielmaker(int fieldCount, Player enemy, int colorCount) {
        initColors(colorCount);
        player2 = enemy;
        initPlayingField(fieldCount, colorCount);

        Player.SetSpielmaker(this);
    }

    /**
     * initializes the list of colors to choose
     *
     * @param colorCount
     */
    private void initColors(int colorCount){
        colors = new ArrayList<>(colorCount);
        for ( Color color : Color.values())
        {
            if(colorCount-- <= 0)
                break;
            colors.add(color);
        }
    }

    /**
     * build the playing field with random colors and set the start point for every player
     *
     * @param fieldCount    the size of the playing field
     * @param colorCount    number of colors
     * @return              the playing field as two-dimensional array
     */
    private void initPlayingField(int fieldCount, int colorCount) {
        fields = new ColorField[fieldCount][fieldCount];
        for (int x = 0; x < fieldCount; x++) {
            for (int y = 0; y < fieldCount; y++) {
                fields[x][y] = new ColorField(colors.get(rnd.nextInt(colorCount)));
                fields[x][y].setKey(new ColorKey(x, y));
            }
        }

        player1.setActive(true);

        ColorField firstField = fields[0][0];
        ColorField lastField = fields[fieldCount -1][fieldCount -1 ];

        player1.initColorsHold(firstField);
        player2.initColorsHold(lastField);
        firstField.setActive(true);
        lastField.setActive(true);
        chooseColor(player1, firstField.getColor());
        chooseColor(player2, lastField.getColor());
    }

    /**
     * returns the fields a player could receive when choosing a certain color.
     *
     * @param player      the player who should get the fields
     * @param newColor    the color of the fields which should be added
     * @return fields a player receives for his chosen color
     */
    public List<ColorField> testColor(Player player, Color newColor){
        return chooseColor(player, newColor, true);
    }

    /**
     * adding all fields at the edge of a players fields with a certain color
     *
     * @param player    the player who should get the fields
     * @param newColor  the color of the fields which should be added
     * @return fields a player receives for his chosen color
     */
    public List<ColorField> chooseColor(Player player, Color newColor){
        return chooseColor(player, newColor, false);
    }

    /**
     * returns the fields a player receives when choosing a certain color.
     * Can also update the fields on the edge of a players field range.
     *
     * @param player    the player who should get the fields
     * @param newColor  the color of the fields which should be added
     * @param isTest    if true, nothing happens, otherwise the player really receives the fields of chosen color
     * @return fields a player receives for his chosen color
     */
    private List<ColorField> chooseColor(Player player, Color newColor, boolean isTest){
        List<ColorField> newFields = new ArrayList<>();

        for (int i = 0; i < player.getColorsHold().size(); i++) {
            ColorKey key = player.getColorsHold().get(i).getKey();
            List<ColorField> neighbors = getNeighborsWithColor(key.getX(), key.getY(), newColor);
            newFields.addAll(neighbors);

            if (!isTest) {
                neighbors.stream().forEach(k -> {
                    k.setActive(true);
                    if (!player.getColorsHold().contains(k))
                        player.getColorsHold().add(k);
                });
                fields[key.getX()][key.getY()].setColor(newColor);
            }
        }

        System.out.println(newFields.size() + " consumable field(s) with color " + newColor.name());
        return newFields;
    }

    /**
     * returns all neighbors of a field with a certain color
     *
     * @param x        position on x direction
     * @param y        position on y direction
     * @param color    color you're looking for
     * @return         all neighbors with the chosen color
     */
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

    /**
     * Checks if a field is consumable. Therefore it must have the given color and must not be active
     *
     * @param posX     position on x direction
     * @param posY     position on y direction
     * @param color    color you're looking for
     * @return the checked ColorField if it's consumable, otherwise null
     */
    private ColorField checkColorField(int posX, int posY, Color color){
        ColorField cf = fields[posX][posY];
        if (cf.getColor().name().equals(color.name()) && !cf.isActive()) {
            cf.setKey(new ColorKey(posX, posY));
            return cf;
        }
        return null;
    }

    /**
     * switches the activity property of both players
     */
    public void switchActivePlayer(){
        player1.setActive(!player1.isActive());
        player2.setActive(player1.isActive());
    }

    /**
     * @return the currently active player
     */
    public Player getActivePlayer(){
        return player1.isActive() ? player1 : player2;
    }

    /**
     *
     * @return the currently inactive player
     */
    public Player getInactivePlayer(){
        return !player1.isActive() ? player1 : player2;
    }

    /**
     *
     * @return the playing field
     */
    public ColorField[][] getFields() {
        return fields;
    }

    /**
     *
     * @return list of colors
     */
    public List<Color> getColors() {
        return colors;
    }

    /**
     *
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     *
     * @return player 2
     */
    public Player getPlayer2() {
        return player2;
    }
}
