package at.htlVillach.noahArsic.snake;

import java.awt.*;

public class HUD {
    private int FPS;
    private int score = 0;

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawString("FPS: " +FPS, 10, 20);
        g.drawString("Score: " +score, 10, 35);
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
