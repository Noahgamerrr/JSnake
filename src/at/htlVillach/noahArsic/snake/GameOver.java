package at.htlVillach.noahArsic.snake;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameOver extends MouseAdapter {
    private final HUD hud;
    private final Game game;
    private final Handler handler;

    public GameOver(HUD hud, Game game, Handler handler) {
        this.hud = hud;
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mouseOver(mx, my, 407, 255, 150, 75)) {
            game.getHud().setScore(0);
            GameObject.setMoveX(15);
            GameObject.setMoveY(0);
            Game.setGameState(Game.STATE.Game);
            game.removeMouseListener(this);
            Random r = new Random();
            game.addKeyListener(new KeyInput());
            for (int i = 1; i <= 2; i++) {
                handler.addObject(new SnakePart(90 + i * 15, 90, ID.SnakePart, handler, game.getHud(), game));
            }
            handler.addObject(new SnakePart(135, 90, ID.LeadingSnakePart, handler, game.getHud(), game));
            handler.addObject(new Food((r.nextInt((Game.getGameWidth() / SnakePart.getPartSize()) - 2) + 1) * SnakePart.getPartSize(),
                    (r.nextInt((Game.getGameHeight() / SnakePart.getPartSize()) - 4) + 1) * SnakePart.getPartSize(), ID.Food));
        }
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        return (mx > x && mx < mx + width && my > y && my < y + height);
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 60));
        g.drawString("Game Over", 310, 150);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Score: " + hud.getScore(), 420, 190);
        g.drawRect(407, 255, 150, 75);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Play", 450, 300);
    }
}
