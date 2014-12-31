package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;

import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public class HumanPlayer extends Player {

    /**
     * do nothing.
     *
     * @return
     */
    @Override
    public void makeMove(Color color) throws GameOverException {
        List<Color> colorsAvailable = getAvailableColors();
        if (colorsAvailable.contains(color))
            spielmaker.chooseColor(this, color);
        else {
            throw new GameOverException();
        }
    }

}
