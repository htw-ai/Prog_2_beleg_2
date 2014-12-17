package sample.model;

/**
 * Created by root on 17.12.14.
 */
public class ColorField {
    private ColorKey key;
    private Color color;
    private boolean active;

    public ColorField(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ColorKey getKey() {
        return key;
    }

    public void setKey(ColorKey key) {
        this.key = key;
    }
}
