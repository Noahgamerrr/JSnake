package at.htlVillach.noahArsic.snake;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private final Game game;
    private final Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mouseOver(mx, my, 407, 255, 150, 75)) {
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
        g.drawString("Snake", 400, 150);
        g.drawRect(407, 255, 150, 75);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Play", 450, 300);
    }
}
