import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DijkstraTest {

    @Test
    public void smallPathTest1() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 3, 1);
        g.addEdge(4, 1, 1);
        g.addEdge(1, 4, 1);
        List<Integer> path = new ArrayList<Integer>();
        path.add(1);
        path.add(4);
        assert (Dijkstra.getShortestPath(g, 1, 4).equals(path));
    }
    
    @Test
    public void smallPathTest2() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 1, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 3, 1);
        g.addEdge(4, 1, 100);
        g.addEdge(1, 4, 100);
        List<Integer> path = new ArrayList<Integer>();
        path.add(1);
        path.add(2);
        path.add(3);
        path.add(4);
        assert (Dijkstra.getShortestPath(g, 1, 4).equals(path));
    }
    
    @Test
    public void smallPathTest3() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 1, 1);
        List<Integer> path = new ArrayList<Integer>();
        path.add(1);
        path.add(2);
        path.add(3);
        path.add(4);
        assert (Dijkstra.getShortestPath(g, 1, 4).equals(path));
    }
    
    @Test
    public void smallPathTest4() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 1, 1);
        List<Integer> path = new ArrayList<Integer>();
        assert (Dijkstra.getShortestPath(g, 0, 4).equals(path));
    }
}
