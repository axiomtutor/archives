import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WidestPathTest {

    @Test
    public void smallPathTest1() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(2, 3, 3);
        g.addEdge(3, 2, 3);
        g.addEdge(3, 4, 4);
        g.addEdge(4, 3, 4);
        g.addEdge(4, 1, 2);
        g.addEdge(1, 4, 2);
        List<Integer> path = new ArrayList<Integer>();
        path.add(1);
        path.add(4);
        path.add(3);
        path.add(2);
        assert (WidestPath.getWidestPath(g, 1, 2).equals(path));
    }
    
    @Test
    public void smallPathTest2() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 2);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 3, 1);
        g.addEdge(4, 1, 1);
        g.addEdge(1, 4, 1);
        List<Integer> path = new ArrayList<Integer>();
        path.add(1);
        path.add(2);
        assert (WidestPath.getWidestPath(g, 1, 2).equals(path));
    }
    
    @Test
    public void smallPathTest3() {
        Graph g = new Graph(7);
        g.addEdge(0, 1, 50);
        g.addEdge(1, 0, 50);
        g.addEdge(0, 2, 50);
        g.addEdge(2, 0, 50);
        g.addEdge(0, 3, 2);
        g.addEdge(3, 0, 2);
        g.addEdge(0, 4, 2);
        g.addEdge(4, 0, 2);
        g.addEdge(0, 5, 2);
        g.addEdge(5, 0, 2);
        g.addEdge(1, 5, 50);
        g.addEdge(5, 1, 50);
        g.addEdge(6, 1, 5);
        g.addEdge(1, 6, 5);
        g.addEdge(6, 2, 2);
        g.addEdge(2, 6, 2);
        g.addEdge(6, 3, 50);
        g.addEdge(3, 6, 50);
        g.addEdge(6, 4, 2);
        g.addEdge(4, 6, 2);
        g.addEdge(6, 5, 50);
        g.addEdge(5, 6, 50);
        List<Integer> path = new ArrayList<Integer>();
        path.add(0);
        path.add(1);
        path.add(5);
        path.add(6);
        assert (WidestPath.getWidestPath(g, 0, 6).equals(path));
    }

} 
