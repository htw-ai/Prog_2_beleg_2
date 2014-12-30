package sample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 27.11.14.
 */
public abstract class Player {

    private int moves;
    private int points;
    private boolean isActive;
    private List<ColorField> colorsHold;

    /**
     * returns the chosen color
     *
     * @return
     */
    public abstract String makeMove();

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getPoints() {
        return colorsHold.size();
    }

//    public void addPoints(int p){
//        points += p;
//    }
//
//    public void setPoints(int points) {
//        this.points = points;
//    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void initColorsHold(ColorField colorField){
        colorsHold = new ArrayList<ColorField>(){{
                add(colorField);
            }};
    }

    public List<ColorField> getColorsHold() {
        return colorsHold;
    }

    public void addColors(List<ColorField> colorsHold) {
        this.colorsHold.addAll(colorsHold);
    }
}
