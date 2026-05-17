package Obstacle;

public class Obstacle {
    public int x, y, width, height;
    boolean active = false;
    
    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean collidesWith(int playerX, int playerY, int playerSize) {
        return playerX < x + width &&
               playerX + playerSize > x &&
               playerY < y + height &&
               playerY + playerSize > y;
    }


}
