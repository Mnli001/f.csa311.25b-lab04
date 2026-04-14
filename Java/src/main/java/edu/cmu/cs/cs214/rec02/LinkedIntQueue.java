import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedIntQueue implements IntQueue {
    private LinkedList<Integer> list;
    public LinkedIntQueue() {
        list = new LinkedList<Integer>();
    }
    @Override
    public void enqueue(int x) {
        list.addLast(x);
    }
    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        return list.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        return list.getFirst();
    }
}
