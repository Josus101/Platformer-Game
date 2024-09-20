
import javax.swing.*;


public class Game {

    static GameManager gameManager;
    public static void main(String[] args) {
        // Create a new frame (window)
        JFrame frame = new JFrame();
        
        // Set the title
        frame.setTitle("Platformer");

        // Set the size of the frame
        frame.setSize(1600, 1200);
        
        // Add the custom panel to the frame
        gameManager = new GameManager(frame);

        // Exit the application when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        // Make the frame visible
        frame.setVisible(true);
    }
}
