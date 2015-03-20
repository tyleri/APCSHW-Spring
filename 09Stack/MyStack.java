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

}
