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
        if (head == tail+1 || (head == 0 && tail == arr.length-1) )
            resize();
        head--;
        if (head < 0)
            head = arr.length - 1;
        arr[head] = item;
        size++;
    }

    public void addLast(T item) {
        if (head == tail+1 || (head == 0 && tail == arr.length-1) )
            resize();
        tail++;
        if (tail >= arr.length)
            tail = 0;
        arr[tail] = item;
        size++;
    }


}
