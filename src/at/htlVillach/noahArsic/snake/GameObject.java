package at.htlVillach.noahArsic.snake;

import java.awt.*;

public abstract class GameObject {
    protected final int x, y;
    protected static int moveX = 15, moveY;
    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public Rectangle getBoundary() {
        return new Rectangle(x, y, SnakePart.getPartSize(), SnakePart.getPartSize());
    }

    public ID getId() {
        return id;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public static void setMoveX(int moveX) {
        GameObject.moveX = moveX;
    }

    public static void setMoveY(int moveY) {
        GameObject.moveY = moveY;
    }

}
