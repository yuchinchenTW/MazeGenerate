# MazeGenerate
maze_generate_randomly

how to use it:

 Maze maze = new Maze(200);// the consturctor gives x*x square maze

        maze.findpath();//first time find path limit recusion and node recusion is 10000 if(recursion>10000) it stops
        while (maze.count_O() < 26700) { //26700 this number is a counter which is the path on maze due to the maze size could be increase or decrease
            System.out.println("retry");
            maze.resetRecur_count(); reset recursion node counting
           
            
            for(int i=0;i<17;i++){
                maze.resetRecur_count();
                maze.findpath();
            }
            
        }
        maze.maze_eraseblank();

      
       maze.print_maze();
        System.out.println(maze.out());
        

      
