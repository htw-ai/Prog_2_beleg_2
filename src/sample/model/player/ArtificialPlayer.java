package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;

import java.util.List;

/**
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

    protected abstract Color chooseColor(List<Color> colors) throws GameOverException;
}
