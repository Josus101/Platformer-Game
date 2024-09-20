import javax.swing.*;


public class GameManager {

    public static float gravity = 2400f;

    Scene scene = new Scene();

    // delta time
    int fps = 60;
    long lastTime = System.nanoTime();
    double deltaTime;
    long timeNow;




    public GameManager(JFrame gameWindow) {
        scene.setSize(gameWindow.getWidth(), gameWindow.getHeight());
        gameWindow.add(scene);
        gameLoop();
    }

    
    void gameLoop(){
        new Thread(() -> {
            while (true) {
                timeNow = System.nanoTime();
                deltaTime = (timeNow - lastTime) / 1_000_000_000.0;
                lastTime = timeNow;
                scene.update(deltaTime);
    
                try {
                    Thread.sleep((long)(1000/fps));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}