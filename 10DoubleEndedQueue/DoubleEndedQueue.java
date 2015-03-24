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

    public boolean add(T element) {
        tail++;
        arr[tail] = element;
        return true;
    }

}
