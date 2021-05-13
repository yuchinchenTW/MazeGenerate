/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesr_maze;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 *
 * @author jason_yu
 */
public class Tesr_maze {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        Maze maze = new Maze(100);
        int set = 0;
        // maze.findpath();
        while (maze.count_O() <6000) {

            //maze.resetRecur_count();
            // maze.findpath();
            for (int i = 0; i < 6; i++) {
                maze.resetRecur_count();
                maze.findpath();
                
            }
            System.out.println(maze.count_O());
                maze.print_maze();
                System.out.println(maze.out());
            
            if (maze.count_O() < 1000) {
                maze = new Maze(100);
              //  set = 0;
                maze.reset_maze();
                maze.resetRecur_count();
                
            }
        }
        maze.maze_eraseblank();

        System.out.println(maze.count_O());
        maze.print_maze();
        System.out.println(maze.out());

    }

    public static int returnZero(int anyNumber) {
        if (anyNumber == 0) {
            return 0;
        } else {
            anyNumber--;
            return returnZero(anyNumber);
        }
    }

}
