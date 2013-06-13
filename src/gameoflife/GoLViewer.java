/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class GoLViewer extends JFrame{
    
    private int x;
    private int y;
    private int[][] grid;
    private int it;
    
    public GoLViewer(int x, int y) {
        this.x = x;
        this.y = y;
        this.grid = new int[x][y]; //make sure it's not null...
        this.it = 0;
        
        this.setVisible(true);
        this.setSize(x,y);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
    }
    
    public void refresh(int[][] grid) {
        it++;
        this.grid = grid;
        this.x = grid.length;
        this.y = grid[0].length;
        this.setTitle("Iteration " + it);
        this.repaint();
    }
    
    public JPanel panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            BufferedImage bi = (BufferedImage) (panel.createImage(x, y));
            for(int i=0; i<x; i++) {
                for(int j=0; j<y; j++) {
                    bi.setRGB(i, j, getColour(grid[i][j]).getRGB());
                }
            }
            g2d.drawImage(bi, null, 0, 0);
        }
    };
    
    private Color getColour(int i) {
        switch(i) {
            case 0:
                return Color.WHITE;
            default:
                return Color.BLACK;
        }
    }
    
}
