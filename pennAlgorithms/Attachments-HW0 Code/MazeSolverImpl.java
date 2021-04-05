public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */

    //Method to copy a 2D Array for later manipulation
    static int[][] copy(int[][] maze) {
        int[][] copy = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                copy[i][j] = maze[i][j]; 
            }
        }
        return copy;
    }
    
    //Exception Helpers:
    static boolean nullMaze(int[][] maze) { 
        //Check if maze is null
        return maze == null;
    }
    
    static boolean badDimMaze(int[][] maze) { 
        //Check if maze has bad dimensions (short-circuit if empty)
        if (maze.length == 0 || maze[0].length == 0 || maze.length * maze[0].length <= 1) {
            return true;
        }
        return false;
    }
    
    static boolean goalSourceOutBounds(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        //Check if source or goal are out of bounds.
        if (sourceCoord.getX() >= maze[0].length || sourceCoord.getX() < 0 || 
            sourceCoord.getY() >= maze.length || sourceCoord.getY() < 0) {  
            return true; 
        } else if (goalCoord.getX() >= maze[0].length || goalCoord.getX() < 0 || 
                 goalCoord.getY() >= maze.length || goalCoord.getY() < 0) { 
            return true;
        }
        return false;
    }
    
    static boolean goalSourceOnBadTile(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        //Check if source or goal are on blocked tile.
        if (maze[sourceCoord.getY()][sourceCoord.getX()] == 1) { 
            return true; 
        }
        if (maze[goalCoord.getY()][goalCoord.getX()] == 1) {  
            return true; 
        }
        return false;
    }

    //Main Helper:
    static int[][] solveHelper(int[][] maze, Coordinate here, Coordinate goalCoord) {
        //If you are on 1) blocked coordinate, 2) already visited coordinate, or 3) out of bounds
        if (0 > here.getY() || here.getY() >= maze.length || 
            0 > here.getX() || here.getX() >= maze[0].length || 
            maze[here.getY()][here.getX()] == 1 || maze[here.getY()][here.getX()] == 2) {
            return null;
        }

        //In maze, set current coordinate to 2 (it's been visited) 
        maze[here.getY()][here.getX()] = 2;

        //If you start (or end up) on the goal
        if (here.equals(goalCoord)) {
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[0].length; j++) {
                    if (maze[i][j] == 2) {
                        maze[i][j] = 1;
                    } else if (maze[i][j] == 1) {
                        maze[i][j] = 0;
                    }
                }
            }
            return maze;
        }

        //Construct directional coordinates
        Coordinate downCoord = new Coordinate(here.getX(), here.getY() + 1);
        Coordinate upCoord = new Coordinate(here.getX(), here.getY() - 1);
        Coordinate leftCoord = new Coordinate(here.getX() - 1, here.getY());
        Coordinate rightCoord = new Coordinate(here.getX() + 1, here.getY());
 
        //See the result of the recursive call of the updated maze and the updated coordinate.
        //If this is anything other than null, then it's the return value.
        //Go Down
        int[][] rec = solveHelper(copy(maze), downCoord, goalCoord); 
        if (rec != null) { 
            return rec;
        } 
        //Go Up
        rec = solveHelper(copy(maze), upCoord, goalCoord);
        if (rec != null) { 
            return rec;
        }
        //Go Left
        rec = solveHelper(copy(maze), leftCoord, goalCoord);
        if (rec != null) { 
            return rec;
        }
        //Go Right
        rec = solveHelper(copy(maze), rightCoord, goalCoord);
        if (rec != null) { 
            return rec;
        }
        return null;
    }

    //Main Method:
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        if (nullMaze(maze) || badDimMaze(maze) || 
            goalSourceOutBounds(maze, sourceCoord, goalCoord) || 
            goalSourceOnBadTile(maze, sourceCoord, goalCoord)) {
            throw new IllegalArgumentException();
        }
        // Construct copy of the maze that stores information about the maze AND information about 
        // which cells have been visited.
        int[][] cMaze = copy(maze);
        // Pass this new array into a call to a new function 
        // that will make use of the new kind of array.
        return solveHelper(cMaze, sourceCoord, goalCoord);
    }
}
