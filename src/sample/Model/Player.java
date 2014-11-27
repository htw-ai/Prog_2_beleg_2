package sample.Model;

/**
 * Created by root on 27.11.14.
 */
public abstract class Player {

    private int moves;
    private int points;
    private boolean isActive;

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
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
