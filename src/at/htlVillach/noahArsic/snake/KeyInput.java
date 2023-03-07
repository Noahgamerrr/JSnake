package at.htlVillach.noahArsic.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private final boolean[] pressedKeys = new boolean[4];

    public KeyInput() {
        for (int i = 0; i < 3; i++) {
            pressedKeys[i] = false;
        }
        pressedKeys[3] = true;
    }


    private void changeMovement() {
        if (pressedKeys[0]) {
            GameObject.setMoveY(SnakePart.getPartSize() * -1);
            GameObject.setMoveX(0);
        }
        else if (pressedKeys[1]) {
            GameObject.setMoveX(SnakePart.getPartSize() * -1);
            GameObject.setMoveY(0);
        }
        else if (pressedKeys[2]) {
            GameObject.setMoveY(SnakePart.getPartSize());
            GameObject.setMoveX(0);
        }
        else {
            GameObject.setMoveX(SnakePart.getPartSize());
            GameObject.setMoveY(0);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
        if (e.getKeyCode() == KeyEvent.VK_W && !pressedKeys[2]) {
            pressedKeys[0] = true;
            pressedKeys[1] = false;
            pressedKeys[3] = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A && !pressedKeys[3]) {
            pressedKeys[1] = true;
            pressedKeys[0] = false;
            pressedKeys[2] = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S && !pressedKeys[0]) {
            pressedKeys[2] = true;
            pressedKeys[1] = false;
            pressedKeys[3] = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_D && !pressedKeys[1]) {
            pressedKeys[3] = true;
            pressedKeys[0] = false;
            pressedKeys[2] = false;
        }
        changeMovement();
    }
}
