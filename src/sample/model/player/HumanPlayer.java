package sample.model.player;

import sample.exceptions.ForbiddenColorException;
import sample.exceptions.GameOverException;
import sample.model.Color;

import java.util.List;

/**
 * Created by root on 27.11.14.
 *
 * An human player representing a user and doing his moves my using the UI
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        this.name = name;
    }

    /**
     * set the new color.
     *
     * @param color                     the new color a player picked
     * @throws GameOverException        if no more color is eligible the game is over and this Exception will be thrown
     * @throws ForbiddenColorException  if the player chose a color he's not capable to choose this Exception will be thrown
     */
    @Override
    public void makeMove(Color color) throws GameOverException, ForbiddenColorException {
        List<Color> colorsAvailable = getAvailableColors();
        if (colorsAvailable.contains(color))
            spielmaker.chooseColor(this, color);
        else
            throw new ForbiddenColorException();
    }

}
