package Entitiy;

import java.awt.Graphics2D;
import java.awt.Image;
import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    Image currentImage;
    
    // Variabel Animasi
    int spriteCounter = 0;
    int spriteNum = 1;

    // Constructor disesuaikan dengan GamePanel kamu (5 parameter)
    public Player(GamePanel gp, KeyHandler keyH, Image playerImg, int x, int y) {
        super(x, y, 4); // speed 4
        this.gp = gp;
        this.keyH = keyH;
        this.currentImage = playerImg; 
    }

    public void update() {
        int nextX = x;
        int nextY = y;
        boolean moving = false;

        // Cek Input dan tentukan gambar (Bisa dikembangkan per arah jika ada asetnya)
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            moving = true;
            if (keyH.upPressed) nextY -= speed;
            if (keyH.downPressed) nextY += speed;
            if (keyH.leftPressed) nextX -= speed;
            if (keyH.rightPressed) nextX += speed;
        }

        // LOGIKA ANIMASI: Berganti antara spriteNum 1 dan 2 saat bergerak
        if (moving) {
            spriteCounter++;
            if (spriteCounter > 12) { // Kecepatan ganti kaki
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1; // Kembali ke posisi diam jika berhenti
        }

        // Collision Check
        if (!gp.collidesWithWall(nextX, y)) {
            x = nextX;
        }
        if (!gp.collidesWithWall(x, nextY)) {
            y = nextY;
        }
    }

    public void draw(Graphics2D g2) {
        int drawY = y;

        // EFEK VISUAL JALAN:
        // Jika sedang melangkah (spriteNum 2), gambar naik sedikit agar terlihat seperti melangkah
        // Jika kamu sudah punya 2 gambar berbeda, kamu bisa mengganti gambarnya di sini.
        if (spriteNum == 2) {
            drawY -= 4; 
        }

        if (currentImage != null) {
            g2.drawImage(currentImage, x, drawY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }
}