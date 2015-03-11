public class MyLinkedList {

    private LNode start;

    public MyLinkedList() {
        start = new LNode();
    }

    public boolean isEmpty() {
        return start.getNext() == null;
    }

    public Object get(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();

        LNode curr = start;

        for (int i = 0; i <= index; i++) {
            curr = curr.getNext();
            if (curr == null)
                throw new IndexOutOfBoundsException();
        }
        return curr.getData();
    }

    public void set(int index, Object value) {
        if (index < 0)
            throw new IndexOutOfBoundsException();

        LNode curr = start;

        for (int i = 0; i <= index; i++) {
            curr = curr.getNext();
            if (curr == null)
                throw new IndexOutOfBoundsException();
        }
        curr.setData(value);
    }

    public void add(Object value) {
        LNode curr = start;

        while (curr.getNext() != null)
            curr = curr.getNext();
        curr.setNext( new LNode(value) );
    }

    public void remove(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException();

        LNode curr = start;

        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
            if (curr.getNext() == null)
                throw new IndexOutOfBoundsException();
        }
        curr.setNext( curr.getNext().getNext() );
    }

    public int size() {
        LNode curr = start.getNext();
        int count = 0;

        while ( curr != null ) {
            count++;
            curr = curr.getNext();
        }
        return count;
    }

    public String toString() {
        LNode curr = start.getNext();
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
        list.add(42);
        list.add("pi");
        list.add(3.14);
        list.add('c');

        System.out.println(list);

        System.out.println("Element at index 1? " + list.get(1));
        System.out.println("Element at index 3? " + list.get(3));
        
        System.out.println("Removing the element at index 2...");
        list.remove(2);
        System.out.println(list);

        System.out.println("Size of the list? " + list.size());

        System.out.println("Setting the element at index 2 to 'z'");
        list.set(2, 'z');
        System.out.println(list);

        System.out.println("Where is 'z'? " + list.indexOf('z'));
        System.out.println("Where is 42? " + list.indexOf(42));
        System.out.println("Where is \"pi\"? " + list.indexOf("pi"));
        System.out.println("Where is 3.14? " + list.indexOf(3.14));

    }
}
