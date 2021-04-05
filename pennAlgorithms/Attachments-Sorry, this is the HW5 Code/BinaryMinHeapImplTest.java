import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import org.junit.Test;

public class BinaryMinHeapImplTest {

    BinaryMinHeapImpl<Integer, Character> bh = 
            new BinaryMinHeapImpl<Integer, Character>();
    ArrayList<BinaryMinHeap.Entry<Integer, Character>> h = 
            new ArrayList<BinaryMinHeap.Entry<Integer, Character>>(); 
    BinaryMinHeap.Entry<Integer, Character> e1 = 
            new BinaryMinHeap.Entry<Integer, Character>(10, 'A');
    BinaryMinHeap.Entry<Integer, Character> e2 = 
            new BinaryMinHeap.Entry<Integer, Character>(20, 'B');
    BinaryMinHeap.Entry<Integer, Character> e3 = 
            new BinaryMinHeap.Entry<Integer, Character>(30, 'C');
    HashMap<Character, Integer> hash = new HashMap<Character, Integer>();

    
    @Test
    public void testDecreaseKey() {
        h.add(e1);
        h.add(e2);
        h.add(e3);
        hash.put('A', 0);
        hash.put('B', 1);
        hash.put('C', 2);
        bh.heap = h;
        bh.map = hash;
        assert (bh.size() == 3);
        assert (bh.containsValue('A'));
        bh.decreaseKey('C', 5);
        assert (bh.containsValue('C'));
        assert (bh.size() == 3);
        assert (h.get(0).value == 'C');
        bh.decreaseKey('B', -1);
        assert (bh.containsValue('B'));
        assert (bh.size() == 3);
        assert (h.get(0).value == 'B');
    }
    
    @Test
    public void testAdd() {
        BinaryMinHeapImpl<Integer, Character> bin = 
                new BinaryMinHeapImpl<Integer, Character>();
        bin.add(100, 'D');
        bin.add(75, 'C');
        bin.add(500, 'B');
        bin.add(25, 'A');
        assert (bin.heap.get(0).value == 'A');
        assert (bin.heap.get(1).value == 'C');
        assert (bin.heap.get(2).value == 'B');
        assert (bin.heap.get(3).value == 'D');
        bin.add(1, 'F');
        assert (bin.heap.get(0).value == 'F');
    }

    @Test
    public void testExtractMin() {
        BinaryMinHeapImpl<Integer, Character> bin = 
                new BinaryMinHeapImpl<Integer, Character>();
        bin.add(100, 'D');
        bin.add(75, 'C');
        bin.add(500, 'B');
        bin.add(25, 'A');
        assert (bin.extractMin().value == 'A');
        assert (bin.peek().value == 'C');
        assert (bin.extractMin().value == 'C');
        assert (bin.peek().value == 'D');
        assert (bin.extractMin().value == 'D');
        assert (bin.peek().value == 'B');
        assert (bin.extractMin().value == 'B');
    }
    
    @Test
    public void testExtractMinMore() {
        BinaryMinHeapImpl<Integer, Character> bin = 
                new BinaryMinHeapImpl<Integer, Character>();
        bin.add(100, 'D');
        bin.add(75, 'C');
        bin.add(500, 'B');
        bin.add(25, 'A');
        bin.decreaseKey('D', 24);
        assert (bin.extractMin().value == 'D');
    }
    
    @Test
    public void testValues() {
        BinaryMinHeapImpl<Integer, Character> bin = 
                new BinaryMinHeapImpl<Integer, Character>();
        bin.add(100, 'D');
        bin.add(75, 'C');
        bin.add(500, 'B');
        bin.add(25, 'A');
        Set<Character> s = new HashSet<Character>();
        s.add('A');
        s.add('B');
        s.add('C');
        s.add('D');
        assert (bin.values().equals(s));
    }
}
