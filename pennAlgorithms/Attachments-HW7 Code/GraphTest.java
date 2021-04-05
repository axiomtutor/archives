import java.util.HashSet;

import org.junit.Test;

public class GraphTest {

    @Test
    public void smallTest() {
        Graph g = new Graph(5);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 3, 2);
        g.addEdge(3, 4, 3);
        g.addEdge(4, 1, 4);
        assert (g.hasEdge(1, 2));
        assert (!(g.hasEdge(2, 1)));
        assert (g.getWeight(3, 4) == 3);
        assert (g.getSize() == 5);
        HashSet<Integer> hs1 = new HashSet<Integer>();
        hs1.add(2);
        assert (g.outNeighbors(1).equals(hs1));
        HashSet<Integer> hs2 = new HashSet<Integer>();
        hs2.add(1);
        assert (g.outNeighbors(4).equals(hs2));
    }

}
