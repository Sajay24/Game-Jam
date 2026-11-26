import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel implements ActionListener {

    Rocket rocket = new Rocket();
    Asteroid asteroid = new Asteroid();

    Timer timer = new Timer(20, this);

    int score = 0;
    boolean gameOver = false;

    public GamePanel() {

        setPreferredSize(new Dimension(500, 500));
        setBackground(Color.BLACK);
        setFocusable(true);

        // Mouse controls
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                handleInput();
            }
        });

        // Keyboard controls
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    handleInput();
                }
            }
        });

        timer.start();
    }

    // Player input
    void handleInput() {

        if (gameOver) {

            rocket.reset();
            asteroid.reset();

            score = 0;
            gameOver = false;

        } else {

            rocket.jump();
        }
    }

    // Game loop
    @Override
    public void actionPerformed(ActionEvent e) {

        if (!gameOver) {

            rocket.update();
            asteroid.update();

            // Collision detection
            if (asteroid.getBounds().intersects(rocket.getBounds())
                    || rocket.y < 0
                    || rocket.y > 465) {

                gameOver = true;
            }

            // Score system
            if (!asteroid.scored && asteroid.x + 45 < rocket.x) {

                score++;
                asteroid.scored = true;
            }
        }

        repaint();
    }

    // Drawing
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Stars
        g.setColor(Color.WHITE);

        for (int i = 0; i < 30; i++) {

            int starX = (i * 73) % getWidth();
            int starY = (i * 47) % getHeight();

            g.fillOval(starX, starY, 3, 3);
        }

        // Draw player and obstacle
        rocket.draw(g);
        asteroid.draw(g);

        // Title and score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString("MOON TAP 🚀", 20, 30);
        g.drawString("Score: " + score, 20, 60);

        // Game over screen
        if (gameOver) {

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 26));
            g.drawString("GAME OVER!", 155, 220);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Click or SPACE to restart", 130, 250);

        } else {

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString("Click or SPACE to fly!", 140, 460);
        }
    }
}


