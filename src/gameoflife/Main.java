/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author azp
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        int x = 1024;
        int y = 768;
        
        GoLGrid glg = new GoLGrid(x, y);
        int[][] grid = {{0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 0, 0}, {0, 0, 1, 0, 0, 0}};
        glg.setGrid(grid);
        glg.randomise();
        List<Integer> births = new LinkedList<Integer>();
        List<Integer> survives = new LinkedList<Integer>();
        births.add(3);
        //births.add(6);
        survives.add(2);
        survives.add(3);
        //survives.add(4);

        glg.setRules(births, survives);
        GoLViewer glv = new GoLViewer(x, y);

        while(true) {
            glv.refresh(glg.getGrid());
            //System.out.println("foo");
            //foo.printAsText();
            //s.next();
            boolean changed = glg.step(false);
            if(!changed)
                glg.randomise();
            /*try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
    }
}
