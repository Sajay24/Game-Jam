import java.awt.*;
import java.util.Random;

public class Asteroid {
    int x = 500;
    int y = 250;
    Random random = new Random();

    public void update()
    {
        x -= 6;

        if(x < -45)
        {
            x = 500;
            y = 50 + random.nextInt(350);
        }
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.GRAY);
        g.fillOval(x, y, 45, 45);
    }

    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 45, 45);
    }

    public void reset()
    {
        x = 500;
        y = 250;
    }
}
