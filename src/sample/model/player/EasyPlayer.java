package sample.model.player;

import sample.exceptions.GameOverException;
import sample.model.Color;

import java.util.List;
import java.util.Random;

/**
 * Created by root on 27.11.14.
 */
public class EasyPlayer extends ArtificialPlayer {
    Random rnd = new Random();

    public EasyPlayer() {
        this.name = "easy player";
    }

//    @Override
//    public void makeMove()throws GameOverException{
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        super.makeMove();
//    }

    /**
     * Tests if the chosen color would change at least one field.
     * If not removes this color from the possible colors list
     *
     * We also could be at the end of the game, where no field would change at all.
     * This case should not just exit the loop, but call the spielmaker to end the game.
     * Therefore an GameOverException will be thrown!
     *
     * @return chosen Color
     */
    @Override
    protected Color chooseColor(List<Color> colors) throws GameOverException {
        int colorIndex = getAnotherColorIndex(colors);
        while (spielmaker.testColor(this, colors.get(colorIndex)).size() < 0){
            if (colors.size() > 0)
                throw new GameOverException();
            colors.remove(colors.get(colorIndex));
            colorIndex = getAnotherColorIndex(colors);
        }
        return colors.get(colorIndex);
    }

    protected int getAnotherColorIndex(List<Color> colors) {
        return rnd.nextInt(colors.size());
    }
}
