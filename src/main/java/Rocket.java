import java.awt.*;

public class Rocket
{
    int x = 60;
    int y = 250;
    int velocity = 0;

    public void jump()
    {
        velocity = -9;
    }

    public void update()
    {
        velocity += 1;
        y += velocity;
    }

    public void draw(Graphics g)
    {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, 35, 35);

        g.setColor(Color.CYAN);
        g.fillOval(x + 10, y + 8, 15, 15);

        g.setColor(Color.RED);
        g.fillOval(x + 10, y+30, 15, 10);
    }

    public void reset()
    {
        y = 250;
        velocity = 0;
    }

    public Rectangle getBounds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBounds'");
    }
}
