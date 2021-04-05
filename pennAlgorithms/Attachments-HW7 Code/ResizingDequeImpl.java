import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingDequeImpl<E> implements ResizingDeque<E> {
    
    E[] array; 
    int front = 0;
    int end = 0;
    int deqSize = 0;
    
    public ResizingDequeImpl() {
        array = (E[]) new Object[2];
    }
    
    @Override
    public int size() {
        return deqSize;
    }
    
    @Override
    public E[] getArray() {
        return array;
    }

    @Override
    public void addFirst(E e) {
        if (array.length == size()) { 
            E[] newArray = (E[]) new Object[array.length * 2];
            for (int i = front; i < array.length; i++) {
                newArray[i - front] = array[i];
            }
            // Leave space at index 0 to add element after copying
            for (int j = 0; j < end; j++) {
                newArray[array.length - front + j] = array[j];
            } 
            front = 0;
            end = array.length;
            array = newArray;
            addFirst(e);
        } else if (size() == 0) {
            end++;
            array[0] = e;
            deqSize++;
        } else if (front > 0) {
            array[front - 1] = e;
            front--;
            deqSize++;
        } else {
            front = array.length - 1;
            array[front] = e;
            deqSize++;
        }
    }

    @Override
    public void addLast(E e) {
        if (array.length == size()) { 
            E[] newArray = (E[]) new Object[array.length * 2];
            for (int i = front; i < array.length; i++) {
                newArray[i - front] = array[i];
            }
            // Leave space at index 0 to add element after copying
            for (int j = 0; j < end; j++) {
                newArray[array.length - front + j] = array[j];
            } 
            front = 0;
            end = array.length;
            array = newArray;
            addLast(e);
        } else if (end == array.length) { 
            array[0] = e;
            end = 1;
            deqSize++;
        } else {
            array[end] = e;
            end++;
            deqSize++;
        }
    }

    @Override
    public E pollFirst() { 
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        // Retrieve and remove element
        E first = array[front];
        if (first == null) {
            System.out.println(front + " " + end + " " + size());
        }
        if (front != array.length - 1) {
            array[front] = null;
            front++;
        } else {
            array[front] = null;
            front = 0;
        }
        deqSize--;
        if (size() == 0) {
            front = 0;
            end = 0;
        }
        int s = size();
        if (s < (array.length / 4) && array.length > 2) {
            E[] newArray = (E[]) new Object[array.length / 2];
            // Either not dealing with a wrap around (spanning middle of the array),
            // or dealing with wrap around (pick back up at start):
            int upperbound = Math.min(array.length, end);
            for (int i = front; i < upperbound; i++) {
                newArray[i - front] = array[i];
            }
            if (end < front) {
                for (int j = 0; j < end; j++) {
                    newArray[upperbound + j] = array[j];
                } 
            }
            front = 0;
            end = s;
            array = newArray;
        }
        
        return first;
    }

    @Override
    public E pollLast() { 
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        E last = array[end - 1];
        array[end - 1] = null;
        if (end == 1) {
            end = array.length;
        } else {
            end--;
        }
        deqSize--;
        if (size() == 0) {
            front = 0;
            end = 0;
        }
        int s = size();
        if (s < (array.length / 4) && array.length > 2){
            E[] newArray = (E[]) new Object[array.length / 2];
            int upperbound = Math.min(array.length, end);
            for (int i = front; i < upperbound; i++) {
                newArray[i - front] = array[i];
            }
            if (end < front) {
                for (int j = 0; j < end; j++) {
                    newArray[upperbound + j] = array[j];
                } 
            }
            front = 0;
            end = s;
            array = newArray;
        }
        return last;
    }

    @Override
    public E peekFirst() { 
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return array[front];
    }

    @Override
    public E peekLast() { 
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return array[end - 1];
    }
    
    @Override
    public Iterator<E> iterator() { 
        
        class DequeIterator implements Iterator<E> { 
            E[] array; 
            int front = 0;
            int size = 0;
            
            public DequeIterator(E[] array, int front, int size) {
                this.array = array.clone();
                this.front = front;
                this.size = size;
            }
            
            @Override
            public boolean hasNext() {
                return (this.size != 0);
            }
            
            @Override
            public E next() {
                if (hasNext()) {
                    if (front == array.length - 1) {
                        E current = array[front];
                        array[front] = null;
                        front = 0;
                        size--;
                        return current;
                    } else {
                        E current = array[front];
                        array[front] = null;
                        front++;
                        size--;
                        return current;
                    }
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
        return new DequeIterator(array, front, deqSize);
    }

}
