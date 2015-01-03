package sample.model.player;

import sample.Spielmaker;
import sample.exceptions.ForbiddenColorException;
import sample.exceptions.GameOverException;
import sample.model.Color;
import sample.model.ColorField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public abstract class Player {

    protected static Spielmaker spielmaker;

    private boolean isActive;
    protected List<ColorField> colorsHold;

    protected String name;

    /**
     * returns the chosen color
     *
     */
    public abstract void makeMove(Color color) throws GameOverException, ForbiddenColorException;

    /**
     * gets all colors a player can choose
     *
     * @return
     * @throws GameOverException
     */
    protected List<Color> getAvailableColors() throws GameOverException {
        List<Color> colors = new ArrayList<>();
        spielmaker.getColors().forEach(c -> colors.add(c));

        //remove the currently active colors of both players
        colors.remove(spielmaker.getActivePlayer().getColorsHold().get(0).getColor());
        colors.remove(spielmaker.getInactivePlayer().getColorsHold().get(0).getColor());

        testIfGameIsOver(colors);
        //colors = testIfGameIsOver(colors);

        return colors;
    }

    /**
     * Checks if there is at least one color to choose, if not a GameOverException will be thrown.
     * Also removes colors that wouldn't change the score
     *
     * @param colors
     * @return
     * @throws GameOverException
     */
    protected List<Color> testIfGameIsOver(List<Color> colors) throws GameOverException {
        boolean flag = false;
        for (int i = 0; i < colors.size(); i++) {
            Color c = colors.get(i);

            if (spielmaker.testColor(this, c).size() > 0)
                flag = true;
            else {
                colors.remove(c);
                i--;
            }
        }

        if (!flag || colors.size() <= 0)
            throw new GameOverException();

        return colors;
    }

    /**
     * sets the Spielmaker attribute for all players
     * @param spielmaker
     */
    public static void SetSpielmaker(Spielmaker spielmaker) {
        Player.spielmaker = spielmaker;
    }

    public int getPoints() {
        return colorsHold.size();
    }

    /**
     *
     * @return the current state of a player. if true he's next
     */
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * initializes the color fields and sets the fist item
     *
     * @param colorField
     */
    public void initColorsHold(ColorField colorField){
        colorsHold = new ArrayList<ColorField>(){{
                add(colorField);
            }};
    }

    /**
     *
     * @return all color fields a player holds
     */
    public List<ColorField> getColorsHold() {
        return colorsHold;
    }

    public void addColors(List<ColorField> colorsHold) {
        this.colorsHold.addAll(colorsHold);
    }

    public String getName() {
        return name;
    }
}
