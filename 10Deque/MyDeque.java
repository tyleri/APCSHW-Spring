public class MyDeque<T> {

    Object[] arr;
    int head;
    int tail;
    int size;

    public MyDeque() {
        arr = new Object[100];
        head = tail = arr.length / 2;
        tail--;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        head--;
        arr[head] = item;
        size++;
    }

    public void addLast(T item) {
        tail++;
        arr[tail] = item;
        size++;
    }

}
