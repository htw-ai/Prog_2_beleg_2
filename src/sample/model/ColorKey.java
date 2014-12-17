package sample.model;

/**
 * Created by root on 17.12.14.
 */
public class ColorKey {
    private int x;
    private int y;

    public ColorKey(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean compare(int x, int y){
        return x == this.x && y == this.y;
    }
}
