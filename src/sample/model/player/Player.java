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

    /**
     * returns the chosen color
     *
     * @return
     */
    public abstract void makeMove(Color color) throws GameOverException, ForbiddenColorException;

    protected List<Color> getAvailableColors(){
        List<Color> colors = new ArrayList<>();
        spielmaker.getColors().forEach(c -> colors.add(c));

        //remove the currently active color
        colors.remove(colorsHold.get(0).getColor());
        //remove the currently active color of the opponent player
        //colors.remove(spielmaker.getInactivePlayer().getColorsHold().get(0).getColor());

        return colors;
    }

    public static void SetSpielmaker(Spielmaker spielmaker) {
        Player.spielmaker = spielmaker;
    }

    public int getPoints() {
        return colorsHold.size();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void initColorsHold(ColorField colorField){
        colorsHold = new ArrayList<ColorField>(){{
                add(colorField);
            }};
    }

    public List<ColorField> getColorsHold() {
        return colorsHold;
    }

    public void addColors(List<ColorField> colorsHold) {
        this.colorsHold.addAll(colorsHold);
    }
}
