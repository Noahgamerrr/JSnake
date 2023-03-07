package at.htlVillach.noahArsic.snake;

import java.awt.*;

public class Food extends GameObject{
    public Food(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, SnakePart.getPartSize(), SnakePart.getPartSize());
    }
}
