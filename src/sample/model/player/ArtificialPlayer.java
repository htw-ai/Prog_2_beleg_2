package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christoph on 30.12.2014.
 */
public abstract class ArtificialPlayer extends Player {

    @Override
    public Color makeMove(Color opponentsColor) throws GameOverException {
        List<Color> colors = new ArrayList<>();
        spielmaker.getColors().forEach(c -> colors.add(c));

        //remove the currently active color
        colors.remove(colorsHold.get(0).getColor());
        colors.remove(opponentsColor);

        return chooseColor(colors);
    }

    protected abstract Color chooseColor(List<Color> colors) throws GameOverException;
}
