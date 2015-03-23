import java.util.EmptyStackException;

public class MyStack<T> {

    MyLinkedList mll;

    public MyStack() {
        mll = new MyLinkedList<T>();
    }
    
    public boolean empty() {
        return mll.isEmpty();
    }

    public T push(T item) {
        LNode<T> ln = new LNode<T>(item);
        if (head != null)
            ln.setNext(head);

        head = ln;
        return item;
    }

    public T pop() {
        if (head == null)
            throw new EmptyStackException();

        T item = head.getData();
        head = head.getNext();
        return item;
    }

    public T peek() {
        if (head == null)
            throw new EmptyStackException();

        return head.getData();
    }

    public int search(Object o) {
        LNode<T> ln = head;
        int loc = 1;

        while (ln != null) {
            if ( ln.getData().equals(o) )
                return loc;
            loc++;
            ln = ln.getNext();
        }
        return -1;
    }

    public static void main(String[] args) {
        MyStack<String> s = new MyStack<String>();

        System.out.println( "Push: " + s.push("hello") );
        System.out.println( "Push: " + s.push("world") );
        System.out.println( "Push: " + s.push("!") );
        System.out.println( "Search \"hello\": " + s.search("hello") );
        System.out.println( "Search \"!\": " + s.search("!") );
        System.out.println( "Search \"hola\": " + s.search("hola") );
        System.out.println( "Peek: " + s.peek() );
        System.out.println( "Pop: " + s.pop() );
        System.out.println( "Search \"hello\": " + s.search("hello") );
        System.out.println( "Search \"world\": " + s.search("world") );
        System.out.println( "Peek: " + s.peek() );
        System.out.println( "Empty: " + s.empty() );
        System.out.println( "Pop: " + s.pop() );
        System.out.println( "Empty: " + s.empty() );
        System.out.println( "Pop: " + s.pop() );
        System.out.println( "Empty: " + s.empty() );
    }
}
