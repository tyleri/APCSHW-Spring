public class DoubleEndedQueue {

    Object[] arr;
    int head;
    int tail;
    int size;

    public DoubleEndedQueue() {
        arr = new Object[100];
        head = tail = arr.length / 2;
    }

    public int size() {
        return size;
    }

}
