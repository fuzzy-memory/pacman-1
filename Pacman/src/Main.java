import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT's event classes and listener interfaces
import javax.swing.*;    // Using Swing's components and containers
/**
 * Custom Graphics Example: Using key/button to move a object left or right.
 * The moving object (sprite) is defined in its own class, with its own
 * operations and can paint itself.
 */
/**
 * The class Sprite models a moving game object, with its own operations
 *  and can paint itself.
 */
class Sprite {
    // Variables (package access)
    int x, y, width, height; // Use an rectangle for illustration
    Color color = Color.RED; // Color of the object

    // Constructor
    public Sprite(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Paint itself given the Graphics context
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height); // Fill a rectangle
    }
}
