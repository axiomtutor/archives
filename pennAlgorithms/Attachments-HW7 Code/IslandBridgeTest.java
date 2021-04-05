import org.junit.Test;

public class IslandBridgeTest {

    @Test
    public void smallTrueTest() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 1, 1);
        assert (IslandBridge.allNavigable(g, 1));
    }
    
    @Test
    public void smallFalseTest() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        assert (!(IslandBridge.allNavigable(g, 1)));
    }
    
    @Test
    public void twoSCCTrueTest() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        assert (IslandBridge.allNavigable(g, 1));
    }
    
    @Test
    public void twoSCCFalseTest() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(1, 2, 1);
        assert (!(IslandBridge.allNavigable(g, 1)));
    }
    
    @Test
    public void twoSCCAsBeforeButNowTrueTest() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 0, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 2, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 1, 1);
        assert (IslandBridge.allNavigable(g, 1));
    }
    
    @Test
    public void smallFalseTest2() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        assert (!(IslandBridge.allNavigable(g, 1)));
    }
    
    @Test
    public void oneVertexTest() {
        Graph g = new Graph(1);
        assert (IslandBridge.allNavigable(g, 0));
    }
    
    @Test
    public void mutationTest() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 4, 1);
        assert (!(IslandBridge.allNavigable(g, 1)));
        g.addEdge(4, 1, 1);
        assert (IslandBridge.allNavigable(g, 1));
    }

}
