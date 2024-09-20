package components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

    int left = 0;
    int right = 0;

    int up = 0;
    int down = 0;

    boolean jump = false;

    boolean dash = false;
    
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());

        // left
        if (e.getKeyCode() == 65) left = -1;
        if (e.getKeyCode() == 37) left = -1;

        // right
        if (e.getKeyCode() == 68) right = 1;
        if (e.getKeyCode() == 39) right = 1;


        // jump
        if (e.getKeyCode() == 32) jump = true;
        if (e.getKeyCode() == 38) jump = true;

        // dash
        if (e.getKeyCode() == 16) dash = true; 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO: Implement keyTyped method
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println(e.getKeyCode());

        // left
        if (e.getKeyCode() == 65) left = 0;
        if (e.getKeyCode() == 37) left = 0;

        // right
        if (e.getKeyCode() == 68) right = 0;
        if (e.getKeyCode() == 39) right = 0;


        // jump
        if (e.getKeyCode() == 32) jump = false;
        if (e.getKeyCode() == 38) jump = false;

        if (e.getKeyCode() == 16) dash = false;
    }

    public int horizontal() {
        return left + right;
    }

    public int vertical() {
        return up + down;
    }

    public boolean jump() {
        return jump;
    }

    public boolean dash() {
        return dash;
    }
}
