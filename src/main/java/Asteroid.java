import java.awt.*;
import java.util.Random;

public class Asteroid {

    int x = 500;
    int y = 250;

    boolean scored = false;

    Random random = new Random();

    public void update() {

        x -= 6;

        if (x < -45) {
            x = 500;
            y = 50 + random.nextInt(350);
            scored = false;
        }
    }

    public void draw(Graphics g) {

        // Asteroid
        g.setColor(Color.GRAY);
        g.fillOval(x, y, 45, 45);

        // Asteroid details
        g.setColor(Color.DARK_GRAY);
        g.fillOval(x + 10, y + 8, 10, 10);
        g.fillOval(x + 27, y + 25, 8, 8);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 45, 45);
    }

    public void reset() {
        x = 500;
        y = 250;
        scored = false;
    }
}



