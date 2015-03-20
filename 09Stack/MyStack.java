import java.util.EmptyStackException;

public class MyStack<T> {

    LNode<T> head;
    
    public boolean empty() {
        return head == null;
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

    public static void main(String[] args) {
        MyStack<String> s = new MyStack<String>();

        System.out.println( "Push: " + s.push("hello") );
        System.out.println( "Push: " + s.push("world") );
        System.out.println( "Push: " + s.push("!") );
        System.out.println( "Peek: " + s.peek() );
        System.out.println( "Pop: " + s.pop() );
        System.out.println( "Peek: " + s.peek() );
        System.out.println( "Empty: " + s.empty() );
        System.out.println( "Pop: " + s.pop() );
        System.out.println( "Empty: " + s.empty() );
        System.out.println( "Pop: " + s.pop() );
        System.out.println( "Empty: " + s.empty() );
    }
}
