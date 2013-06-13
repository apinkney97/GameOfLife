/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.List;
import java.util.Random;

/**
 *
 * @author azp
 */
public class GoLGrid {

    private int[][] grid;
    private int xsize;
    private int ysize;
    private int x_offset;
    private int y_offset;
    private List<Integer> births;
    private List<Integer> survives;

    public GoLGrid() {
        this(100, 100);
    }

    public GoLGrid(int x, int y) {
        this.xsize = x;
        this.ysize = y;
        grid = new int[xsize][ysize];
        x_offset = 0;
        y_offset = 0;
    }

    public void setRules(List<Integer> births, List<Integer> survives) {
        this.births = births;
        this.survives = survives;
    }

    public int getXOffset() {
        return x_offset;
    }

    public int getYOffset() {
        return y_offset;
    }

    public int[][] getGrid() {
        return grid;
    }

    private int getCellValue(int x, int y) {
        if (x < 0 || x >= xsize || y < 0 || y >= ysize) {
            return 0;
        }
        return grid[x][y] > 0 ? 1 : 0;
    }

    private int getNeighbourCount(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                count += getCellValue(i, j);
            }
        }

        if (getCellValue(x, y) > 0) {
            count--;
        }

        return count;
    }

    public void setGrid(int[][] grid) {
        for (int i = 0; i < xsize && i < grid.length; i++) {
            for (int j = 0; j < ysize && j < grid[i].length; j++) {
                this.grid[i][j] = grid[i][j];
            }
        }
    }

    public boolean step(boolean allowresizing) {

        boolean changed = false;

        if (allowresizing) {
            // Check if array needs resizing. Principally, whether there are any
            // cells outside the perimeter which would be born
        }

        int[][] newgrid = new int[xsize][ysize];

        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                int nc = getNeighbourCount(i, j);
                if (grid[i][j] == 0 && births.contains(nc)) {
                    newgrid[i][j] = 1;
                    changed = true;
                } else if (grid[i][j] > 0 && survives.contains(nc)) {
                    newgrid[i][j] = 1;
                } else {
                    changed = true;
                }
            }
        }

        grid = newgrid;

        return changed;
    }
    
    
    public void randomise() {
        Random r = new Random(System.currentTimeMillis());
        int[][] newgrid = new int[xsize][ysize];
        for(int i=0; i<xsize; i++) {
            for(int j=0; j<ysize; j++) {
                newgrid[i][j] = r.nextInt(2);
            }
        }
        grid = newgrid;
    }

    public void printAsText() {
        for (int i = 0; i < xsize; i++) {
            for (int j = 0; j < ysize; j++) {
                if (grid[i][j] != 0) {
                    System.out.print('#');
                } else {
                    System.out.print('-');
                }
            }
            System.out.println("");
        }
        System.out.println();
    }
}
