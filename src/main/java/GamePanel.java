import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener
{
    Rocket rocket = new Rocket();
    Asteroid asteroid = new Asteroid();

    Timer timer = new Timer(20, this);

    int score = 0;
    boolean gameOver = false;

    public GamePanel()
    {
        setBackground(Color.BLACK);
        setFocusable(true);

        addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                handleInput();
            }
        });

        timer.start();
    }

    void handleInput()
    {
        if (gameOver)
        {
            rocket.reset();
            asteroid.reset();
            score = 0;
            gameOver = false;
        } else {
            rocket.jump();
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if (!gameOver)
        {
            rocket.update();
            asteroid.update();

            if(asteroid.getBounds().intersects(rocket.getBounds()) || rocket.y < 0 || rocket.y > 465)
            {
                gameOver = true;
            }

            if(asteroid.x == 60)
            {
                score++;
            }
        }
        repaint();
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        for (int i = 0; i < 30; i++)
        {
            g.fillOval((i * 73) % 500, (i * 47) % 450, 2, 2);
        }

        rocket.draw(g);
        asteroid.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("MOON TAP", 20, 25);
        g.drawString("Score: " + score, 20, 50);

        if(gameOver)
        {
            g.setColor(Color.RED);
            g.drawString("GAME OVER!", 200, 220);

            g.setColor(Color.WHITE);
            g.drawString("Click or SPACE to restart", 160, 250);
        } else {
            g.drawString("Click or SPACE to fly!", 170, 460);
        }
    }
}
