package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

public class GameController {
    
    int BLOCK_SIZE = 64; 
    int BLOCKS_WIDTH = 9; 
    int BLOCKS_HEIGHT = 11;

    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    private Player player;

    public GameController(int panelWidth, int panelHeight) {
    	player = new Player(left_x + (BLOCKS_WIDTH * BLOCK_SIZE / 2) - (32 / 2), bottom_y - 52);
        
    }

    public void update() {
        player.update();
    }

    public void draw(Graphics2D g2) {
        // Dibujar área de juego
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x - 4, top_y - 4, (BLOCKS_WIDTH * BLOCK_SIZE) + 8, (BLOCKS_HEIGHT * BLOCK_SIZE) + 8);

        // Dibujar cuadrado siguiente pieza
        g2.setColor(Color.yellow);
        int x = right_x + 100;
        int y = top_y + 50;
        g2.drawRect(x, y, 200, 150);
        g2.setFont(new Font("Lucida Console", Font.ITALIC, 50));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontMetrics fm = g2.getFontMetrics();
        String text = "NEXT";
        int textWidth = fm.stringWidth(text);
        int textX = x + (200 - textWidth) / 2;
        int textY = y - 10;
        g2.setColor(Color.yellow);
        g2.drawString(text, textX, textY);

        // Dibujar al jugador
        player.draw(g2);
    }

    public void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                player.setMovingLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                player.setMovingRight(true);
                break;
            case KeyEvent.VK_UP:
                player.jump();
                break;
            case KeyEvent.VK_SPACE:
                player.jump();
                break;
        }
    }

    public void keyReleased(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                player.setMovingLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                player.setMovingRight(false);
                break;
        }
    }

    public void resize(int panelWidth, int panelHeight) {
       
        left_x = (panelWidth / 2) - (BLOCKS_WIDTH * BLOCK_SIZE / 2);
        right_x = left_x + (BLOCKS_WIDTH * BLOCK_SIZE); 

        
        top_y = (panelHeight / 2) - (BLOCKS_HEIGHT * BLOCK_SIZE / 2) + 20; 
        if (top_y < 0) {
            top_y = 0;
        }
        
        bottom_y = top_y + (BLOCKS_HEIGHT * BLOCK_SIZE);

        System.out.println("Resolución actual: " + panelWidth + "x" + panelHeight);
        
        BLOCK_SIZE = (panelWidth < 636) ? 32 : 64;
        BLOCKS_WIDTH = (panelWidth < 636) ? 12 : 9;
        BLOCKS_HEIGHT = (panelWidth < 636) ? 18 : 11;
        
        if (bottom_y > panelHeight) {
            bottom_y = panelHeight; 
            top_y = bottom_y - (BLOCKS_HEIGHT * BLOCK_SIZE); 
        }
        
        
        player.setPosition(left_x + (BLOCKS_WIDTH * BLOCK_SIZE / 2) - (player.getWidth() / 2), bottom_y - player.getHeight());
    }

}
