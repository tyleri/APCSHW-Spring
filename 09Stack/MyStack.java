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
}
