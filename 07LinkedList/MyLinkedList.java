import java.util.NoSuchElementException;

public class MyLinkedList<T> {

    private int size;
    private LNode<T> head;
    private LNode<T> tail;

    public String name() {
        return "Ishikawa,Tyler";
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T get(int index) {
        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException();

        LNode<T> curr = head;

        for (int i = 0; i < index; i++)
            curr = curr.getNext();

        return curr.getData();
    }

    public T set(int index, T value) {
        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException();

        LNode<T> curr = head;

        for (int i = 0; i < index; i++)
            curr = curr.getNext();

        T replaced = curr.getData();
        curr.setData(value);
        return replaced;
    }

    public boolean add(T value) {
        if (head == null) {
            head = new LNode<T>(value);
            tail = head;
        } else {
            tail.setNext( new LNode<T>(value) );
            tail = tail.getNext();
        }
        size++;
        return true;
    }

    public void add(int index, T value) {
        LNode n = new LNode<T>(value);

        if (index < 0 || size < index)
            throw new IndexOutOfBoundsException();
        else if (index == size)
            add(value);
        else if (index == 0) {
            n.setNext(head);
            head = n;
        } else {
            LNode curr = head;

            for (int i = 1; i < index; i++)
                curr = curr.getNext();

            n.setNext( curr.getNext() );
            curr.setNext(n);
        }
        size++;
    }

    public int indexOf(T value) {
        LNode curr = head;

        for (int i = 0; curr != null; i++) {
            if ( curr.getData().equals(value) )
                return i;
            curr = curr.getNext();
        }
        return -1;
    }

    public T remove() {
        if (head == null)
            throw new NoSuchElementException();
        T removed = head.getData();
        head = head.getNext();
        size--;
        return removed;
    }

    public T remove(int index) {
        T removed;

        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException();
        else if (index == 0) {
            return remove();
        } else {
            LNode<T> curr = head;

            for (int i = 1; i < index; i++)
                curr = curr.getNext();
            removed = curr.getNext().getData();

            if (curr.getNext() == tail) {
                curr.setNext(null);
                tail = curr;
            } else {
                curr.setNext( curr.getNext().getNext() );
            }
        }
        size--;
        return removed;
    }

    public int size() {
        return size;
    }

    public String toString() {
        LNode curr = head;
        String s = "[ ";

        while ( curr != null ) {
            s += curr.getData() + " ";
            curr = curr.getNext();
        }
        s += "]";

        return s;
    }

    public static void main(String[] args) {
        MyLinkedList<int> list = new MyLinkedList<int>();

        System.out.println("Is the list empty? " + list.isEmpty());

        System.out.println("Adding elements...");
        System.out.println(list.add(42));
        list.add(10);
        list.add(30);
        list.add(1000);
        list.add(2015);

        System.out.println(list);

        System.out.println("Element at index 1? " + list.get(1));
        System.out.println("Element at index 3? " + list.get(3));
        
        System.out.println("Removing the element at index 3...");
        System.out.println(list.remove(3));
        System.out.println(list);
        System.out.println("Removing the first element...");
        System.out.println(list.remove());
        System.out.println(list);

        System.out.println("Size of the list? " + list.size());

        System.out.println("Setting the element at index 2 to 9000");
        System.out.println(list.set(2, 9000));
        System.out.println(list);

        System.out.println("Where is 'z'? " + list.indexOf('z'));
        System.out.println("Where is 42? " + list.indexOf(42));
        System.out.println("Where is 9000? " + list.indexOf("pi"));
        System.out.println("Where is 3.14? " + list.indexOf(3.14));

    }
}
