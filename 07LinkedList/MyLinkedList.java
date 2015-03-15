import java.util.NoSuchElementException;

public class MyLinkedList {

    private int size;
    private LNode head;
    private LNode tail;

    public MyLinkedList() { }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object get(int index) {
        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException();

        LNode curr = head;

        for (int i = 0; i < index; i++)
            curr = curr.getNext();

        return curr.getData();
    }

    public Object set(int index, Object value) {
        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException();

        LNode curr = head;

        for (int i = 0; i < index; i++)
            curr = curr.getNext();

        Object replaced = curr.getData();
        curr.setData(value);
        return replaced;
    }

    public boolean add(Object value) {
        if (head == null) {
            head = new LNode(value);
            tail = head;
        } else {
            tail.setNext( new LNode(value) );
            tail = tail.getNext();
        }
        size++;
        return true;
    }

    public void add(int index, Object value) {
        LNode n = new LNode(value);

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

    public int indexOf(Object value) {
        LNode curr = head;

        for (int i = 0; curr != null; i++) {
            if ( curr.getData().equals(value) )
                return i;
            curr = curr.getNext();
        }
        return -1;
    }

    public Object remove() {
        if (head == null)
            throw new NoSuchElementException();
        Object removed = head.getData();
        head = head.getNext();
        size--;
        return removed;
    }

    public Object remove(int index) {
        Object removed;

        if (index < 0 || size <= index)
            throw new IndexOutOfBoundsException();
        else if (index == 0) {
            return remove();
        } else {
            LNode curr = head;

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
        MyLinkedList list = new MyLinkedList();

        System.out.println("Is the list empty? " + list.isEmpty());

        System.out.println("Adding elements...");
        System.out.println(list.add(42));
        list.add("pi");
        list.add(3.14);
        list.add('c');
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

        System.out.println("Setting the element at index 2 to 'z'");
        System.out.println(list.set(2, 'z'));
        System.out.println(list);

        System.out.println("Where is 'z'? " + list.indexOf('z'));
        System.out.println("Where is 42? " + list.indexOf(42));
        System.out.println("Where is \"pi\"? " + list.indexOf("pi"));
        System.out.println("Where is 3.14? " + list.indexOf(3.14));

    }
}
