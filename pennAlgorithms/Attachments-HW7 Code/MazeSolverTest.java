import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MazeSolverTest {

    int[][] smallMaze = new int[][]{
        {0, 0, 0, 1},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {0, 0, 0, 0}
    };
    
    int[][] twoPathsMaze = new int[][]{
        {0, 0, 0, 1},
        {0, 1, 0, 1},
        {0, 1, 0, 1},
        {0, 0, 0, 1}
    };
    
    int[][] bigMaze = new int[][]{
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
    
    @Test
    public void smallMazeTest() {
        Coordinate src = new Coordinate(0, 3);
        Coordinate tgt = new Coordinate(0, 0);
        List<Coordinate> lc = MazeSolver.getShortestPath(smallMaze, src, tgt);
        List<Coordinate> truth = new ArrayList<Coordinate>();
        truth.add(src);
        truth.add(new Coordinate(1, 3));
        truth.add(new Coordinate(2, 3));
        truth.add(new Coordinate(2, 2));
        truth.add(new Coordinate(2, 1));
        truth.add(new Coordinate(2, 0));
        truth.add(new Coordinate(1, 0));
        truth.add(new Coordinate(0, 0));
        assert (lc.equals(truth));
    }
    
    @Test
    public void twoPathsMazeTest() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(1, 0);
        List<Coordinate> lc = MazeSolver.getShortestPath(twoPathsMaze, src, tgt);
        List<Coordinate> truth = new ArrayList<Coordinate>();
        truth.add(src);
        truth.add(tgt);
        assert (lc.equals(truth));
        // Trial 2:
        src = new Coordinate(1, 0);
        tgt = new Coordinate(0, 0);
        lc = MazeSolver.getShortestPath(twoPathsMaze, src, tgt);
        truth.clear();
        truth.add(src);
        truth.add(tgt);
        assert (lc.equals(truth));
        // Trial 3:
        src = new Coordinate(0, 0);
        tgt = new Coordinate(0, 1);
        lc = MazeSolver.getShortestPath(twoPathsMaze, src, tgt);
        truth.clear();
        truth.add(src);
        truth.add(tgt);
        assert (lc.equals(truth));
        // Trial 4:
        src = new Coordinate(0, 1);
        tgt = new Coordinate(0, 0);
        lc = MazeSolver.getShortestPath(twoPathsMaze, src, tgt);
        truth.clear();
        truth.add(src);
        truth.add(tgt);
        assert (lc.equals(truth));
    }
    
    @Test
    public void testReturnsBigMazeSolutionPath() {
        Coordinate src = new Coordinate(0, 0);
        Coordinate tgt = new Coordinate(4, 0);
        List<Coordinate> truth = new ArrayList<Coordinate>();
        truth.add(src);
        truth.add(new Coordinate(0, 1));
        truth.add(new Coordinate(0, 2));
        truth.add(new Coordinate(1, 2));
        truth.add(new Coordinate(2, 2));
        truth.add(new Coordinate(2, 3));
        truth.add(new Coordinate(2, 4));
        truth.add(new Coordinate(2, 5));
        truth.add(new Coordinate(3, 5));
        truth.add(new Coordinate(3, 6));
        truth.add(new Coordinate(4, 6));
        truth.add(new Coordinate(4, 7));
        truth.add(new Coordinate(5, 7));
        truth.add(new Coordinate(6, 7));
        truth.add(new Coordinate(7, 7));
        truth.add(new Coordinate(8, 7));
        truth.add(new Coordinate(9, 7));
        truth.add(new Coordinate(9, 6));
        truth.add(new Coordinate(9, 5));
        truth.add(new Coordinate(9, 4));
        truth.add(new Coordinate(9, 3));
        truth.add(new Coordinate(9, 2));
        truth.add(new Coordinate(9, 1));
        truth.add(new Coordinate(9, 0));
        truth.add(new Coordinate(8, 0));
        truth.add(new Coordinate(7, 0));
        truth.add(new Coordinate(6, 0));
        truth.add(new Coordinate(5, 0));
        truth.add(tgt);
        List<Coordinate> lc = MazeSolver.getShortestPath(bigMaze, src, tgt);
        assert (lc.equals(truth));
    }

}
