package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;
import sample.model.ColorField;

import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public class HardPlayer extends ArtificialPlayer {

    public HardPlayer() {
        this.name = "hard player";
    }

    @Override
    protected Color chooseColor(List<Color> colors) throws GameOverException {
        int bestColorToChoose = 0, maxPoints = 0;
        boolean hasAnyColorToChoose = false;

        for (int i = 0; i < colors.size(); i++) {
            List<ColorField> colorFields = spielmaker.testColor(this, colors.get(i));
            if(colorFields.size() > maxPoints)
            {
                hasAnyColorToChoose = true;
                maxPoints = colorFields.size();
                bestColorToChoose = i;
            }
        }
        if (!hasAnyColorToChoose)
            throw new GameOverException();

        return colors.get(bestColorToChoose);
    }
}
