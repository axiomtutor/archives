import java.util.Iterator;

import org.junit.Test;

public class ResizingDequeImplTest {

    @Test
    public void addFirstOnSizeTest() {
        ResizingDeque<String> rdi = new ResizingDequeImpl<String>(); 
        rdi.addFirst("A");
        assert (rdi.peekFirst().equals("A"));
        assert (rdi.size() == 1);
        rdi.addFirst("B");
        assert (rdi.size() == 2);
        rdi.addFirst("C");
        assert (rdi.size() == 3);
    }
    
    @Test
    public void addLastOnSizeTest() {
        ResizingDeque<Integer> rdi = new ResizingDequeImpl<Integer>(); 
        rdi.addLast(1);
        rdi.addLast(2);
        rdi.addLast(4);
        rdi.addLast(3);
        rdi.addLast(9);
        assert (rdi.size() == 5);
        Object[] a = rdi.getArray();
        assert (a.length == 8);
    }
    
    @Test
    public void pollFirstTest() {
        ResizingDeque<Integer> rdi = new ResizingDequeImpl<Integer>(); 
        rdi.addLast(1);
        rdi.addLast(2);
        rdi.addLast(4);
        rdi.addLast(3);
        rdi.addLast(9);
        assert (rdi.pollFirst() == 1);
        assert (rdi.pollFirst() == 2);
        assert (rdi.pollFirst() == 4);
        assert (rdi.pollFirst() == 3);
        assert (rdi.pollFirst() == 9);
        assert (rdi.size() == 0);
        Object[] a = rdi.getArray();
        assert (a.length == 2);
        
    }
    
    @Test
    public void pollLastTest() {
        ResizingDeque<Integer> rdi = new ResizingDequeImpl<Integer>(); 
        rdi.addLast(1);
        rdi.addLast(2);
        rdi.addLast(4);
        rdi.addLast(3);
        rdi.addLast(9);
        assert (rdi.pollLast() == 9);
        assert (rdi.pollLast() == 3);
        assert (rdi.pollLast() == 4);
        assert (rdi.pollLast() == 2);
        assert (rdi.pollLast() == 1);
        assert (rdi.size() == 0);
        Object[] a = rdi.getArray();
        assert (a.length == 2);
    }
    
    @Test
    public void iteratorTest() {
        ResizingDeque<Integer> rdi = new ResizingDequeImpl<Integer>(); 
        rdi.addFirst(1);
        rdi.addFirst(2);
        rdi.addFirst(3);
        rdi.addFirst(4);
        rdi.addFirst(5);
        Iterator<Integer> i1 = rdi.iterator();
        int counter1 = 0;
        while (i1.hasNext()) {
            assert (i1.next() == 5 - counter1);
            counter1++;
        }
        // Wrap around case:
        rdi.addLast(6);
        rdi.pollFirst();
        Iterator<Integer> i2 = rdi.iterator();
        assert (i2.next() == 4);
        assert (i2.next() == 3);
        assert (i2.next() == 2);
        assert (i2.next() == 1);
        assert (i2.next() == 6);
    }
    
    @Test
    public void peekNullTest() {
        ResizingDeque<Graph> rdi = new ResizingDequeImpl<Graph>(); 
        rdi.addLast(null);
        assert (rdi.peekFirst() == null);
    }
    
    @Test
    public void nullIteratorTest() {
        ResizingDeque<Integer> rdi = new ResizingDequeImpl<Integer>(); 
        rdi.addFirst(null);
        rdi.addFirst(null);
        Iterator<Integer> i1 = rdi.iterator();
        int counter1 = 0;
        while (i1.hasNext()) {
            assert (i1.next() == null);
            counter1++;
        }
    }

}
