package sample.model;

/**
 * Created by root on 10.12.14.
 *
 *
 */
public enum Color {
    Red(javafx.scene.paint.Color.RED),
    Blue(javafx.scene.paint.Color.BLUE),
    Green(javafx.scene.paint.Color.GREEN),
    Yellow(javafx.scene.paint.Color.YELLOW),
    Violet(javafx.scene.paint.Color.VIOLET),
    Pink(javafx.scene.paint.Color.PINK);

    javafx.scene.paint.Color color;

    private Color(javafx.scene.paint.Color color){
        this.color = color;
    }

    public javafx.scene.paint.Color getFill() {
        return color;
    }
}
