package at.htlVillach.noahArsic.snake;

import java.awt.*;
import java.util.Random;

public class SnakePart extends GameObject{
    private static final int PART_SIZE = 15;
    private long lastTime = System.currentTimeMillis();
    private int nextX = x, nextY = y;
    private final Handler handler;
    private final HUD hud;
    private final Game game;

    public SnakePart(int x, int y, ID id, Handler handler, HUD hud, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        if (System.currentTimeMillis() - lastTime > 100 && this.getId() == ID.LeadingSnakePart) {
            lastTime = System.currentTimeMillis();
            nextX += moveX;
            nextY += moveY;
            if (!checkForFood()) removeFirst();
            snakeBitesSelf();
            spawnNewPart();
            makeOlderPart();
            clamp();
        }
    }

    private void deleteAll() {
        for (int j = handler.object.size() - 1; j >= 0; j--) {
            handler.removeObject(handler.object.get(j));
        }
    }

    private void resetGame() {
        deleteAll();
        Game.setGameState(Game.STATE.GameOver);
        game.addMouseListener(game.getGameOver());
    }

    public void clamp() {
        if (x < 0 || x > Game.getGameWidth() - 30|| y < 0 || y > Game.getGameHeight() - 50) resetGame();
    }

    public void snakeBitesSelf() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.SnakePart) {
                if (getBoundary().intersects(tempObject.getBoundary())) resetGame();
            }
        }
    }

    public boolean checkForFood() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.Food) {
                if (getBoundary().intersects(tempObject.getBoundary())) {
                    Random r = new Random();
                    handler.removeObject(tempObject);
                    hud.setScore(hud.getScore() + 1);
                    handler.addObject(new Food((r.nextInt((Game.getGameWidth() / SnakePart.getPartSize()) - 2) + 1) * SnakePart.getPartSize(),
                            (r.nextInt((Game.getGameHeight() / SnakePart.getPartSize()) - 4) + 1) * SnakePart.getPartSize(), ID.Food));
                    return true;
                }
            }
        }
        return false;
    }

    public void makeOlderPart() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.LeadingSnakePart) {
                tempObject.setID(ID.SnakePart);
                return;
            }
        }
    }

    public void spawnNewPart() {
       for (int i = 0; i < handler.object.size(); i++) {
           GameObject tempObject = handler.object.get(i);
           if (tempObject.getId() == ID.LeadingSnakePart) {
               SnakePart tempSnake = (SnakePart) tempObject;
               handler.addObject(new SnakePart(tempSnake.nextX, tempSnake.nextY, ID.LeadingSnakePart, handler, hud, game));
               return;
           }
       }
    }

    public void removeFirst() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ID.SnakePart) {
                handler.removeObject(tempObject);
                return;
            }
        }
    }

    public void render(Graphics g) {
        if (this.getId() == ID.LeadingSnakePart) g.setColor(Color.yellow);
        else g.setColor(Color.green);
        g.fillRect(x, y, PART_SIZE, PART_SIZE);
    }

    public static int getPartSize() {
        return PART_SIZE;
    }
}
