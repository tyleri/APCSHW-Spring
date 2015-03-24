public class DoubleEndedQueue<T> {

    Object[] arr;
    int head;
    int tail;
    int size;

    public DoubleEndedQueue() {
        arr = new Object[100];
        head = tail = arr.length / 2;
        tail--;
    }

    public int size() {
        return size;
    }

    public boolean add(T item) {
        tail++;
        arr[tail] = item;
        size++;
        return true;
    }

    public void addFirst(T item) {
        head--;
        arr[head] = item;
        size++;
    }

    public void addLast(T item) {
        add(item);
    }

}
