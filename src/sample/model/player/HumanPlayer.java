package sample.model.player;

import sample.model.Color;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public class HumanPlayer extends Player {

    /**
     * do nothing
     * @return
     */
    @Override
    public Color makeMove() {
        return null;
    }


    /**
     * is never been used
     *
     * @param colors
     * @return
     */
    @Override
    protected int getAnotherColorIndex(List<Color> colors) {
        throw new NotImplementedException();
        //return 0;
    }
}
