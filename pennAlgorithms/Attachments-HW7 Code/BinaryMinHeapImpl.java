import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @param <V>   {@inheritDoc}
 * @param <Key> {@inheritDoc}
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {
    
    //Stores entry objects which contains the key
    ArrayList<Entry<Key, V>> heap = new ArrayList<Entry<Key, V>>();
    //Values are the characters. Values map to indices in the heap.
    //Index in heap will give the key, which are frequencies. 
    HashMap<V, Integer> map = new HashMap<V, Integer>();
    
    int left(int i) {
        return 2 * i + 1;
    }
    
    int right(int i) {
        return 2 * i + 2;
    }
    
    int parent(int i) {
        return (i - 1) / (2);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        return map.containsKey(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        if ((key == null) || (map.containsKey(value))) {
            throw new IllegalArgumentException();
        }
        Entry<Key, V> e = new Entry<Key, V>(key, value);
        map.put(value, heap.size());
        heap.add(e);
        decreaseKey(value, key);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseKey(V value, Key newKey) {
        if (!(map.containsKey(value))) {
            throw new NoSuchElementException();
        }
        int index = map.get(value);
        if ((newKey == null) || (newKey.compareTo(heap.get(index).key) > 0)) {
            throw new IllegalArgumentException();
        }
        Entry<Key, V> e = new Entry<Key, V>(newKey, value);
        heap.set(index, e);
        //Check determines if parent is greater than current index:
        boolean check = 
                heap.get(parent(index)).key.compareTo(heap.get(index).key) > 0;
        while ((index > 0) && (check)) {
            //Swap:
            //Value getting moved to parent
            Entry<Key, V> temp = heap.get(index);
            map.put(temp.value, parent(index));
            //Parent moved down to child
            map.put(heap.get(parent(index)).value, index);
            heap.set(index, heap.get(parent(index)));
            heap.set(parent(index), temp);
            index = parent(index);
            check = heap.get(parent(index)).key.compareTo(heap.get(index).key) > 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> peek() {
        if (heap.size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(0);
    }

    void minHeapify(int i) {
        int smallest;
        int l = left(i);
        int r = right(i);
        if (l < heap.size() && 
            heap.get(l).key.compareTo(heap.get(i).key) < 0) {
            smallest = l;
        } else {
            smallest = i;
        }
        if (r < heap.size() && 
            heap.get(r).key.compareTo(heap.get(smallest).key) < 0) {
            smallest = r;
        }
        if (smallest != i) {
            //Swap A[i] with A[smallest]:
            Entry<Key, V> temp = heap.get(i);
            map.put(temp.value, smallest);
            map.put(heap.get(smallest).value, i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);
            minHeapify(smallest);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> extractMin() {
        if (heap.size() == 0) {
            throw new NoSuchElementException();
        }
        Entry<Key, V> min = heap.get(0);
        map.remove(min.value); 
        map.put(heap.get(heap.size() - 1).value, 0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        minHeapify(0);
        return min;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        //Map has all values as keys, so return key set:
        return map.keySet();
    }
}