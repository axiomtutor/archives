import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class HashMapTest {
    
    static class MockHashObject {
        private final int hashCode;

        public MockHashObject(int hashCode) { 
            this.hashCode = hashCode; 
        }

        @Override
        public int hashCode() { 
            return hashCode;
        }
    }

    @Test
    public void simpleTest() {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        hm.put("A", 1);
        assertTrue(hm.containsKey("A"));
        assertEquals((int) hm.get("A"), 1);
        assertEquals(hm.size(), 1);
        hm.remove("A");
        assertEquals(hm.size(), 0);
        assertTrue(!(hm.containsKey(null)));
        hm.put(null, 2);
        assertEquals(hm.size(), 1);
        assertTrue(hm.containsValue(2));
        assertTrue(!(hm.containsValue(3)));
        assertEquals(hm.remove("Z"), null);
        assert (hm.get(null) == 2);
    }
    
    @Test
    public void resizeTest() {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < 20; i++) {
            hm.put(i, i);
        }
        assertEquals(hm.size(), 20);
        hm.put(1, -1);
        assertEquals(hm.size(), 20);
        assert (hm.get(1) == -1);
        hm.clear();
        hm.put(1, null);
        hm.put(2, null);
        hm.put(3, null);
        hm.put(4, null);
        hm.put(5, null);
        hm.put(6, null);
        hm.put(7, null);
        hm.put(8, null);
        hm.put(9, null);
        hm.put(10, null);
        hm.put(11, null);
        hm.put(12, null);
        hm.put(13, null);
        hm.put(14, null);
        hm.put(15, null);
        hm.put(16, null);
        hm.put(17, null);
        for (int i = 1; i < 18; i++) {
            assertEquals(hm.get(i), null);
        }
        assertEquals((int) hm.size(), 17);
        hm.clear();
        hm.put(null, 1);
        hm.put(null, 2);
        hm.put(null, 3);
        hm.put(null, 4);
        hm.put(null, 5);
        hm.put(null, 6);
        hm.put(null, 7);
        hm.put(null, 8);
        hm.put(null, 9);
        hm.put(null, 10);
        hm.put(null, 11);
        hm.put(null, 12);
        hm.put(null, 13);
        hm.put(null, 14);
        hm.put(null, 15);
        hm.put(null, 16);
        hm.put(null, 17);
        assertEquals(hm.size(), 1);
    }
    
    @Test
    public void collisionTest() {
        HashMap<MockHashObject, Integer> hm = 
                new HashMap<MockHashObject, Integer>();
        MockHashObject mho1 = new MockHashObject(1);
        MockHashObject mho2 = new MockHashObject(1);
        MockHashObject mho3 = new MockHashObject(1);
        MockHashObject mho4 = new MockHashObject(1);
        MockHashObject mho5 = new MockHashObject(1);
        hm.put(mho1, 10);
        hm.put(mho2, 20);
        hm.put(mho3, 21);
        hm.put(mho4, 22);
        hm.put(mho5, 23);
        assertEquals((int) hm.size(), 5);
        assertEquals((int) hm.get(mho1), 10);
        assertEquals((int) hm.get(mho2), 20);
        assertTrue(hm.containsKey(mho5));
        assertEquals((int) hm.remove(mho5), 23);
        
    }
    
    @Test
    public void bigTest() {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (int i = 0; i < 100; i++) {
            hm.put(i, i);
        }
        for (int i = 25; i < 75; i++) {
            hm.put(i, i * i);
        }
        hm.remove(0);
        hm.remove(1);
        hm.remove(2);
        assertEquals(hm.size(), 97);
        assert (hm.get(99) == 99);
        assert (hm.get(25) == 25 * 25);
        hm.clear();
        assertEquals(hm.size(), 0);
        assertEquals(hm.get(0), null);
        assertEquals(hm.get(99), null);
    }
    
    @Test
    public void iteratorTest() {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int[] targets = new int[100];
        for (int i = 0; i < 100; i++) {
            hm.put(i, i);
            targets[i] = i;
        }
        Iterator<Map.Entry<Integer, Integer>> i = hm.entryIterator();
        int c = 0;
        while (i.hasNext()) {
            targets[i.next().getValue()] = 0;
            c++;
        }
        for (int j = 0; j < 100; j++) {
            assertEquals(targets[j], 0);
        }
    }
    
    @Test 
    public void entryTest() {
        HashMap.Entry<Integer, Integer> e1 = new HashMap.Entry<Integer, Integer>(1, 1, null);
        assertNotEquals(e1, null);
        assertNotEquals(e1.hashCode(), 0);
        HashMap.Entry<Integer, Integer> e2 = new HashMap.Entry<Integer, Integer>(1, 2, null);
        assertNotEquals(e1, e2);
    }
}
