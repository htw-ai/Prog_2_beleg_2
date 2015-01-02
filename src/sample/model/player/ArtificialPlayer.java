package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;

import java.util.List;

/**
 * Abstract class extended by all unhuman players.
 *
 * Created by Christoph on 30.12.2014.
 */
public abstract class ArtificialPlayer extends Player {

    @Override
    public void makeMove(Color color) throws GameOverException {
        makeMove();
    }

    public void makeMove() throws GameOverException {
        Color c = chooseColor(getAvailableColors());

        spielmaker.chooseColor(this, c);
    }

    /**
     * choosing a certain color by algorithm
     *
     * @param colors colors
     * @return
     * @throws GameOverException
     */
    protected abstract Color chooseColor(List<Color> colors) throws GameOverException;
}
