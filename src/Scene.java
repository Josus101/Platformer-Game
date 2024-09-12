import java.awt.*;
import javax.swing.*;

import components.InputHandler;
import components.Vector2D;


public class Scene extends JPanel {

    Player player;
    InputHandler inputHandler;
    Object[] objects = {
            new Object(new Vector2D(800 / 2, 600 - 50), new Vector2D(800, 50)),
            new Object(new Vector2D(600, 150), new Vector2D(100, 10)),
            new Object(new Vector2D(300, 200), new Vector2D(100, 10)),
            new Object(new Vector2D(450, 300), new Vector2D(100, 10)),
            new Object(new Vector2D(600, 450), new Vector2D(100, 10)),
            new Object(new Vector2D(200, 230), new Vector2D(100, 10))
        };

    public Scene(int width, int height) {
        player = new Player(new Vector2D(width/2, height/2));
        inputHandler = new InputHandler();

        for (Object o: objects) {
            o.setLayer("collidable");
        }

        this.setFocusable(true);
        this.addKeyListener(inputHandler);
        this.setSize(width, height);
        this.setBackground(Color.GRAY);
        
    }

    public Scene() {
        this(800, 600);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Clear the previous render

        // Render background
        // g.setColor(Color.WHITE);
        // g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // render player
        player.render(g);

        for (Object o : objects) {
            o.render(g);
        }
    }

    void update(double deltaTime) {
        player.update(inputHandler, deltaTime);
        // collision with enviroment
        for (Object o : objects) {
            if (player.getHitBox().isColliding(o.getHitBox()) && o.getLayer() == "collidable") {
                // System.out.println("Colliding");

                // find the side the player is on. 
                double aboveSideDist = Math.abs(player.getHitBox().above() - o.getHitBox().below());
                double belowSideDist = Math.abs(player.getHitBox().below() - o.getHitBox().above());
                double leftSideDist = Math.abs(player.getHitBox().left() - o.getHitBox().rigtht());
                double rigthSideDist = Math.abs(player.getHitBox().rigtht() - o.getHitBox().left());

                // find the smallest distance
                double minDist = Math.min(Math.min(leftSideDist, rigthSideDist), Math.min(aboveSideDist, belowSideDist));
                // determine which side has the smallest distance
                if (minDist == belowSideDist) {                                         // player is above
                    player.move(new Vector2D(0, -(minDist + .1)));
                    player.setVelocity(new Vector2D(player.getVel().getX(), -2));
                    player.setGrounded(); // set the isgrounded value to true
                    
                } else if (minDist == aboveSideDist) {                                  // player is below
                    player.move(new Vector2D(0, (minDist + .1)));
                    player.setVelocity(new Vector2D(player.getVel().getX(), -2));

                } else if (minDist == leftSideDist) {                                   // player is right
                    player.move(new Vector2D(minDist, 0));

                } else {                                                                // player is left
                    player.move(new Vector2D(-minDist, 0));
                }
            }
        }
        repaint();
    }
    
}
