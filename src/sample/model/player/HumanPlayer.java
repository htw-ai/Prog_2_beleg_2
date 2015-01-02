package sample.model.player;

import sample.exceptions.ForbiddenColorException;
import sample.exceptions.GameOverException;
import sample.model.Color;
import sample.model.ColorField;

import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        this.name = name;
    }

    /**
     * do nothing.
     *
     * @return
     */
    @Override
    public void makeMove(Color color) throws GameOverException, ForbiddenColorException {
        List<Color> colorsAvailable = getAvailableColors();
        if (colorsAvailable.contains(color)) {
            List<ColorField> colorFields = spielmaker.chooseColor(this, color);
            if (colorFields.size() == 0) {
                colorsAvailable.remove(color);
                testIfGameIsOver(colorsAvailable);
            }
        }
        else {
            throw new ForbiddenColorException();
        }
    }

    private void testIfGameIsOver(List<Color> colors) throws GameOverException {
        boolean flag = false;
        for (Color c : colors){
            List<ColorField> colorFields = spielmaker.testColor(this, c);
            if (colorFields.size() > 0){
                flag = true;
                break;
            }
        }
        if (!flag)
            throw new GameOverException();
    }


}
