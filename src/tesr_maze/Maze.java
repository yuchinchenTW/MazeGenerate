/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesr_maze;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author jason_yu
 */
public class Maze {

    private int x;
    private int y;
    private char maze[][];
    private int cursor_x;
    private int cursor_y;
    private Random ran;
    private int recur_count;
    private int O_count;
    private char block = 0X2588;
    private char road = 0X25A1;
    private char blank = 0X2591;
    private String out = "";
    private int whenitstuck = 0;
    private boolean temp = false;
    private int old_O = 0;
    private int count=0;

    public Maze(int dimension) {
        this(dimension, dimension);
        this.x = dimension;
        this.y = dimension;
        ran = new Random();
        whenitstuck = (dimension * dimension) / 2;

    }

    private Maze(int dim_x, int dim_y) {
        maze = new char[dim_x][dim_y];
        for (int i = 0; i < dim_x; i++) {
            for (int t = 0; t < dim_y; t++) {
                maze[i][t] = blank;
            }

        }
        for (int i = 0; i < dim_y; i++) {
            maze[0][i] = block;
            maze[dim_x - 1][i] = block;
        }

        for (int i = 0; i < dim_x; i++) {
            maze[i][0] = block;
            maze[i][dim_y - 1] = block;

        }

        maze[0][1] = road;
        maze[1][1] = road;
        cursor_x = 1;
        cursor_y = 1;
        recur_count = 0;

    }

    public Integer findpath() throws IOException, InterruptedException {

        recur_count++;
        if (recur_count >= 7000) {
            
            int ran_inx= x-4-ran.nextInt(5);
            int ran_iny= ran.nextInt(y-3);
            cursor_x=ran_inx;
            cursor_y=ran_iny;
          //  System.out.println(cursor_x);
          //  System.out.println(cursor_y);
            
            return null;
        }

        if (maze[cursor_x + 1][cursor_y] != blank
                && maze[cursor_x - 1][cursor_y] != blank
                && maze[cursor_x][cursor_y + 1] != blank
                && maze[cursor_x][cursor_y - 1] != blank) {
            setpath();
            return findpath();
        }
        

        int num = ran.nextInt(33339);
        int out = num % 4;
        if (cursor_x > x ) {
            return findpath();
        }
        if (cursor_y > y ) {
            return findpath();
        }
        if (out == 0) {
            if (cursor_y >= y) {
                return findpath();
            } else if (cursor_x >= x) {
                return findpath();
            }
            if (maze[cursor_x + 1][cursor_y] == block) {
                return findpath();
            } else if (maze[cursor_x + 1][cursor_y] == road) {
                return findpath();
            } else if (maze[cursor_x + 1][cursor_y] == blank) {
                num = ran.nextInt(33339);
                out = num % 2;

                if (cursor_x >= 1
                        && cursor_y >= 1
                        && cursor_x <= x - 1
                        && cursor_y <= y - 1) {
                    if (this.check_O(cursor_x, cursor_y) >= 2) {
                        maze[cursor_x + 1][cursor_y] = block;
                        return findpath();

                    }

                }

                if (out == 0) {
                    maze[cursor_x + 1][cursor_y] = road;
                    cursor_x++;

                    return findpath();
                } else {
                    maze[cursor_x + 1][cursor_y] = block;
                    return findpath();
                }
            }

        } else if (out == 1) {
            if (cursor_y >= y) {
                return findpath();
            } else if (cursor_x >= x) {
                return findpath();
            } else if (cursor_x <= 0) {
                return findpath();
            }
            if (maze[cursor_x - 1][cursor_y] == block) {
                return findpath();
            } else if (maze[cursor_x - 1][cursor_y] == road) {
                return findpath();
            } else if (maze[cursor_x - 1][cursor_y] == blank) {

                num = ran.nextInt(33339);
                out = num % 2;
                if (cursor_x >= 1
                        && cursor_y >= 1
                        && cursor_x <= x - 1
                        && cursor_y <= y - 1) {
                    if (this.check_O(cursor_x, cursor_y) >= 2) {
                        maze[cursor_x - 1][cursor_y] = block;
                        return findpath();

                    }

                }

                if (out == 0 || this.O_count < 7) {
                    maze[cursor_x - 1][cursor_y] = road;
                    cursor_x--;

                    return findpath();
                } else {
                    maze[cursor_x - 1][cursor_y] = block;
                    return findpath();
                }

            }

        } else if (out == 2) {
            if (cursor_y >= y) {
                return findpath();
            } else if (cursor_x >= x) {
                return findpath();
            } else if (cursor_y <= 0) {
                return findpath();
            }
            if (maze[cursor_x][cursor_y - 1] == block) {
                return findpath();
            } else if (maze[cursor_x][cursor_y - 1] == road) {
                return findpath();
            } else if (maze[cursor_x][cursor_y - 1] == blank) {

                num = ran.nextInt(33339);
                out = num % 2;
                if (cursor_x >= 1
                        && cursor_y >= 1
                        && cursor_x <= x - 1
                        && cursor_y <= y - 1) {
                    if (this.check_O(cursor_x, cursor_y) >= 2) {
                        //System.out.println(this.check_O(cursor_x, cursor_y));
                        maze[cursor_x][cursor_y - 1] = block;
                        return findpath();

                    }

                }

                if (out == 0 || this.O_count < 7) {
                    maze[cursor_x][cursor_y - 1] = road;
                    cursor_y--;

                    return findpath();
                } else {
                    maze[cursor_x][cursor_y - 1] = block;
                    return findpath();
                }

            }

        } else if (out == 3) {
            if (cursor_y >= y) {
                return findpath();
            } else if (cursor_x >= x) {
                return findpath();
            }
            if (maze[cursor_x][cursor_y + 1] == block) {
                return findpath();
            } else if (maze[cursor_x][cursor_y + 1] == road) {
                return findpath();
            } else if (maze[cursor_x][cursor_y + 1] == blank) {

                num = ran.nextInt(33339);
                out = num % 2;

                if (cursor_x >= 1
                        && cursor_y >= 1
                        && cursor_x <= x - 1
                        && cursor_y <= y - 1) {
                    if (this.check_O(cursor_x, cursor_y) >= 2) {
                        maze[cursor_x][cursor_y + 1] = block;
                        return findpath();

                    }

                }

                if (out == 0 || this.O_count < 7) {
                    maze[cursor_x][cursor_y + 1] = road;
                    cursor_y++;

                    return findpath();
                } else {
                    maze[cursor_x][cursor_y + 1] = block;
                    return findpath();
                }

            }

        }
        if(this.count<10){
            temp = false;
        }

        if (this.count_O() > this.whenitstuck && temp == false) {
            for (int i = 0; i < this.x - 1; i++) {
                for (int t = 0; t < this.y - 1; t++) {
                       
                    if (maze[i][t] == this.blank && maze[i + 1][t] == this.road) {
                        // System.out.println(1);
                        maze[i][t] = this.road;
                        this.cursor_x = i;
                        this.cursor_y = t;
                        this.old_O = this.count_O();
                        temp = true;
                        this.count++;
                        return findpath();
                    } else if (maze[i][t] == this.blank && maze[i][t + 1] == this.road) {
                        maze[i][t] = this.road;
                        this.cursor_x = i;
                        this.cursor_y = t;
                        temp = true;
                        this.count++;
                        this.old_O = this.count_O();
                        return findpath();

                    } else if (maze[i][t] == this.blank && maze[i - 1][t] == this.road) {
                        maze[i][t] = this.road;
                        this.cursor_x = i;
                        this.cursor_y = t;
                        temp = true;
                        this.count++;
                        this.old_O = this.count_O();
                        return findpath();

                    } else if (maze[i][t] == this.blank && maze[i][t - 1] == this.road) {
                        maze[i][t] = this.road;
                        this.cursor_x = i;
                        this.cursor_y = t;
                        temp = true;
                        this.count++;
                        this.old_O = this.count_O();
                        return findpath();

                    }
                }
            }

        }

        return findpath();

    }

    public void print_maze() {
        System.out.println(cursor_x + " " + cursor_y);
        out="";
        for (int i = 0; i < x; i++) {
            for (int t = 0; t < y; t++) {
                if (t != y - 1) {
                    out += maze[t][i];
                    //System.out.print(maze[t][i] + "");
                } else if (t == y - 1) {
                    out += maze[t][i] + "\n";
                    // System.out.println(maze[t][i]);
                }
            }

        }
    }

    public String out() {
        return this.out;
    }

    public Integer setpath() {

        for (int i = 0; i < x; i++) {
            for (int t = 0; t < y; t++) {
                if ((i >= 1 && t >= 1 && i < x - 1 && t < y - 1) && maze[i][t] == road) {
                    if (maze[i + 1][t] == blank
                            && maze[i - 1][t] != blank
                            && maze[i][t + 1] != blank
                            && maze[i][t - 1] != blank) {
                        maze[i + 1][t] = road;
                        cursor_x = i + 1;
                        cursor_y = t;
                        return null;
                    } else if (maze[i + 1][t] != blank
                            && maze[i - 1][t] == blank
                            && maze[i][t + 1] != blank
                            && maze[i][t - 1] != blank) {
                        maze[i - 1][t] = road;
                        cursor_x = i - 1;
                        cursor_y = t;
                        return null;
                    } else if (maze[i + 1][t] != blank
                            && maze[i - 1][t] != blank
                            && maze[i][t + 1] == blank
                            && maze[i][t - 1] != blank) {
                        maze[i][t + 1] = road;
                        cursor_x = i;
                        cursor_y = t + 1;
                        return null;
                    } else if (maze[i + 1][t] != blank
                            && maze[i - 1][t] != blank
                            && maze[i][t + 1] != blank
                            && maze[i][t - 1] == blank) {
                        maze[i][t - 1] = road;
                        cursor_x = i;
                        cursor_y = t - 1;
                        return null;
                    }
                } else if (i == x - 1 && t == y - 1) {
                    for (int a = 1; a < x; a++) {
                        for (int b = 1; b < y; b++) {
                            if (maze[a][b] == block && check_O(a, b) >= 1 && check_X(a, b) > 2) {
                                //System.out.println("1");
                                maze[a][b] = road;
                                cursor_x = a;
                                cursor_y = b;
                                return null;
                            }

                        }
                    }
                }
            }
        }

        return null;

    }

    public int check_X(int in_x, int in_y) {
        int result = 0;

        if (in_x > 0 && in_y > 0 && in_x < x - 1 && in_y < y - 1) {
            if (maze[in_x + 1][in_y] == block) {
                result++;
            }
            if (maze[in_x][in_y + 1] == block) {
                result++;
            }
            if (maze[in_x - 1][in_y] == block) {
                result++;
            }
            if (maze[in_x][in_y - 1] == block) {
                result++;
            }

        }
        // System.out.println(result);
        return result;
    }

    public int check_O(int in_x, int in_y) {
        int result = 0;

        if (in_x > 0 && in_y > 0 && in_x < x - 1 && in_y < y - 1) {
            if (maze[in_x + 1][in_y] == road) {
                result++;
            }
            if (maze[in_x][in_y + 1] == road) {
                result++;
            }
            if (maze[in_x - 1][in_y] == road) {
                result++;
            }
            if (maze[in_x][in_y - 1] == road) {
                result++;
            }

        }
        // System.out.println(result);
        return result;
    }

    public int count_O() {
        int result = 0;
        for (int i = 0; i < x; i++) {
            for (int t = 0; t < y; t++) {
                if (maze[t][i] == road) {
                    result++;
                }
            }

        }

        return result;
    }

    public void reset_maze() {
        for (int i = 0; i < x; i++) {
            for (int t = 0; t < y; t++) {
                maze[i][t] = blank;
            }

        }
        for (int i = 0; i < y; i++) {
            maze[0][i] = block;
            maze[x - 1][i] = block;
        }

        for (int i = 0; i < x; i++) {
            maze[i][0] = block;
            maze[i][y - 1] = block;

        }

        maze[0][1] = road;
        maze[1][1] = road;
        cursor_x = 1;
        cursor_y = 1;
        recur_count = 0;
        temp = false;
        this.old_O=0;
    }

    public void maze_eraseblank() {
        for (int i = 0; i < x; i++) {
            for (int t = 0; t < y; t++) {
                if (maze[i][t] == blank) {
                    //  System.out.println("1");
                    maze[i][t] = block;
                }

            }

        }
    }

    public void resetRecur_count() {
        this.recur_count = 0;
    }

    public void setCursor(int x, int y) {
        if (x < this.x && y < this.y) {
            this.cursor_x = x;
            this.cursor_y = y;
        }
    }

}
