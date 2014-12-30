package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;
import sample.model.ColorField;

import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public class HardPlayer extends ArtificialPlayer {

    @Override
    protected Color chooseColor(List<Color> colors) throws GameOverException {
        int bestColorToChoose = 0, maxPoints = 0;

        for (int i = 0; i < colors.size(); i++) {
            List<ColorField> colorFields = spielmaker.testColor(colors.get(i));
            if(colorFields.size() > maxPoints)
                bestColorToChoose = i;
        }
        return colors.get(bestColorToChoose);
    }
}
