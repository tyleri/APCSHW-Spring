import java.util.*;

public class MyDeque<T> {

    Object[] arr;
    int[] priority;
    int head;
    int tail;
    int size;

    public MyDeque() {
        arr = new Object[15];
        priority = new int[15];
        head = tail = 0;
    }

    public int size() {
        return size;
    }

    public void add(T item, int p) {
        if (head == tail+1 || (head == 0 && tail == arr.length-1) )
            resize(true);
        if (arr[tail] != null)
            tail++;
        if (tail >= arr.length)
            tail = 0;
        arr[tail] = item;
        priority[tail] = p;
        size++;
    }

    public void addFirst(T item) {
        if (head == tail+1 || (head == 0 && tail == arr.length-1) )
            resize(false);
        if (arr[head] != null)
            head--;
        if (head < 0)
            head = arr.length - 1;
        arr[head] = item;
        size++;
    }

    public void addLast(T item) {
        if (head == tail+1 || (head == 0 && tail == arr.length-1) )
            resize(false);
        if (arr[tail] != null)
            tail++;
        if (tail >= arr.length)
            tail = 0;
        arr[tail] = item;
        size++;
    }

    private void resize(boolean usePriority) {
        // resize arr
        Object[] newArr = new Object[arr.length * 2];
        if (tail > head)
            System.arraycopy(arr, 0, newArr, 0, arr.length);
        else {
            System.arraycopy(arr, head, newArr, 0, arr.length - head);
            System.arraycopy(arr, 0, newArr, arr.length - head, head);
        }
        arr = newArr;

        // resize priority
        if (usePriority) {
            int[] newPriority = new int[priority.length * 2];
            if (tail > head)
                System.arraycopy(priority, 0, newPriority, 0, priority.length);
            else {
                System.arraycopy(priority, head, newPriority, 0, priority.length - head);
                System.arraycopy(priority, 0, newPriority, priority.length - head, head);
            }
            priority = newPriority;
        }

        head = 0;
        tail = arr.length / 2;
    }

    @SuppressWarnings("unchecked")
    public T removeSmallest() {
        if (arr[head] == null)
            throw new NoSuchElementException();

        int min = priority[head], minIndex = head, currIndex = head;

        while (currIndex != tail) {
            currIndex++;

            if (currIndex >= priority.length)
                currIndex = 0;

            if (priority[currIndex] < min) {
                min = priority[currIndex];
                minIndex = currIndex;
            }
        }
        T item = (T)arr[minIndex];
        arr[minIndex] = arr[head];
        priority[minIndex] = priority[head];
        arr[head] = null;
        if (head != tail)
            head = ++head % arr.length;
        size--;

        return item;
    }

    @SuppressWarnings("unchecked")
    public T removeFirst() {
        if (arr[head] == null)
            throw new NoSuchElementException();
        T item = (T)arr[head];
        arr[head] = null;
        if (head != tail)
            head++;
        if (head >= arr.length)
            head = 0;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (arr[tail] == null)
            throw new NoSuchElementException();
        T item = (T)arr[tail];
        arr[tail] = null;
        if (head != tail)
            tail--;
        if (tail < 0)
            tail = arr.length-1;
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T getFirst() {
        if (arr[head] == null)
            throw new NoSuchElementException();
        return (T) arr[head];
    }

    @SuppressWarnings("unchecked")
    public T getLast() {
        if (arr[tail] == null)
            throw new NoSuchElementException();
        return (T) arr[tail];
    }

    public String toString() {
        /*
        String s = "[";
        for (int i = head; i != tail; i++) {
            if (i >= arr.length)
                i = 0;
            s += arr[i] + " ";
        }
        s += arr[tail] + "]";
        return s;
        */
        return Arrays.toString(arr);
    }

    public String toString(boolean usePriority) {
        return toString() + ( usePriority ? "\n" + Arrays.toString(priority) : "" );
    }

    public static void main(String[] args) {
        MyDeque<Integer> d = new MyDeque<Integer>();

        d.add(5, 10);
        d.add(59, 2);
        d.add(10, 0);
        d.add(10, 5);
        d.add(15, 1);
        d.add(20, 11);
        d.add(30, 4);
        d.add(40, 5);
        d.add(50, 8);
        d.add(60, 9);
        d.add(70, 32);
        d.add(80, 30);
        d.add(90, 13);
        d.add(100, 7);
        d.add(110, 6);
        d.add(120, 3);
        d.add(130, 22);
        d.add(140, 15);
        d.add(150, 99);
        System.out.println(d.toString(true));
        System.out.println();

        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        System.out.println(d.removeSmallest());
        d.add(100, 2);
        System.out.println(d.toString(true));
        System.out.println(d.removeSmallest());
        System.out.println(d.toString(true));

        // testing queue and stack methods
        /*
        d.addFirst(5);
        System.out.println(d);
        d.addFirst(3);
        System.out.println(d);
        d.addFirst(2);
        System.out.println(d);
        d.addLast(8);
        System.out.println(d);
        d.addLast(10);
        System.out.println(d);
        d.addLast(100);
        System.out.println(d);
        d.addLast(101);
        System.out.println(d);
        d.addLast(102);
        System.out.println(d);
        d.addLast(103);
        System.out.println(d);
        d.addLast(104);
        System.out.println(d);
        d.addLast(105);
        System.out.println(d);
        d.addLast(105);
        System.out.println(d);
        d.addLast(105);
        System.out.println(d);
        d.addLast(105);
        System.out.println(d);
        d.addLast(105);
        System.out.println(d);
        d.addLast(105);
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        System.out.println(d);
        System.out.println( d.removeLast() );
        d.addLast(100);
        System.out.println(d);
        d.removeFirst();
        System.out.println(d);
        d.addLast(1);
        System.out.println(d);
        d.removeFirst();
        System.out.println(d);
        d.addLast(5);
        System.out.println(d);
        d.addLast(3);
        System.out.println(d);
        d.removeFirst();
        System.out.println(d);
        d.removeFirst();
        System.out.println(d.size());
        */
    }

}
