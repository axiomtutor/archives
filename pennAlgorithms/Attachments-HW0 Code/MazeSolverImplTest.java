import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MazeSolverImplTest {

    private int[][] myEx1;
    private int[][] myEx2;
    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;

    @Before
    public void setupTestMazes() {
        myEx1 = new int[][] {
            {0, 0, 0, 0},
            {0, 1, 0, 0}
        };

        myEx2 = new int[][] {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };

        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };
        
        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

    }
    
    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }

    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);
    }

    /*
      Note: the above tests are the ones included in the writeup and NOT exhaustive. The autograder
      uses other test cases not listed above. Please thoroughly read all stub files, including
      CoordinateTest.java!

      For help with creating test cases, please see this link:
      https://www.seas.upenn.edu/~cis121/current/testing_guide.html
     */

    //Testing change in direction
    @Test
    public void smallSimplePathWithBranch() {
        int[][] solutionPath = {
                {1,1,1,0},
                {0,0,0,0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(myEx1,
                new Coordinate(0, 0), new Coordinate(2, 0)));
    }
    
    //Testing null maze
    @Test(expected = IllegalArgumentException.class)
    public void nullMaze() {
        MazeSolverImpl.solveMaze(null, null, null);
    }
    
    //Testing maze with dimension less than 1
    @Test(expected = IllegalArgumentException.class)
    public void badDimMaze() {
        int[][] ex = new int[0][0]; 
        MazeSolverImpl.solveMaze(ex, null, null);
    }
    
    //Testing source and goal out of bounds
    @Test(expected = IllegalArgumentException.class)
    public void outOfBoundsPoints() { 
        int[][] ex = new int[0][0]; 
        MazeSolverImpl.solveMaze(ex, new Coordinate(0, 100), new Coordinate(0, -50));
    }
    
    //Testing start on tile
    @Test(expected = IllegalArgumentException.class)
    public void onATile() {
        MazeSolverImpl.solveMaze(myEx1, new Coordinate(1, 1), new Coordinate(0, 0));
    }
    
    //Testing Multiple Paths
    @Test
    public void biggerPathTwoOptions() {
        int[][] solutionPath = {
                {1, 0, 0},
                {1, 0, 0},
                {1, 1, 1}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(myEx2,
                new Coordinate(0, 0), new Coordinate(2, 2)));
    }

}
