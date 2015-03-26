import java.util.*;

public class MyDeque<T> {

    Object[] arr;
    int head;
    int tail;
    int size;

    public MyDeque() {
        arr = new Object[5];
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

    private void resize() {
        int newIndex = arr.length / 2, oldIndex = head;
        Object[] newArr = new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[newIndex] = arr[oldIndex];
            oldIndex++;
            newIndex++;
            if (oldIndex >= arr.length)
                oldIndex = 0;
        }
        arr = newArr;
    }

    public T removeFirst() {
        if (arr[head] == null)
            throw new NoSuchElementException();
        T item = (T)arr[head];
        arr[head] = null;
        head++;
        if (head >= arr.length)
            head = 0;
        size--;
        return item;
    }

    public T removeLast() {
        if (arr[tail] == null)
            throw new NoSuchElementException();
        T item = (T)arr[tail];
        arr[tail] = null;
        tail--;
        if (tail < 0)
            tail = arr.length-1;
        size--;
        return item;
    }

    public String toString() {
        String s = "[";
        for (int i = head; i != tail; i++) {
            if (i >= arr.length)
                i = 0;
            s += arr[i] + " ";
        }
        s += arr[tail] + "]";
        return s;
    }

    public static void main(String[] args) {
        MyDeque<Integer> d = new MyDeque<Integer>();
        d.addFirst(5);
        d.addLast(3);
        d.addLast(2);
        d.addLast(8);
        d.addLast(10);
        d.addLast(100);
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println( d.removeLast() );
        System.out.println( d.removeLast() );
        System.out.println( d.removeLast() );
        System.out.println( d.removeLast() );
        System.out.println(d.size());
    }

}
