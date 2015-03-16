public class LNode<T> {

    private T data;
    private LNode next;

    public LNode() { }

    public LNode(T t) {
        setData(t);
    }

    public T getData() {
        return data;
    }

    public LNode<T> getNext() {
        return next;
    }

    public void setData(T t) {
        data = t;
    }

    public void setNext(LNode<T> ln) {
        next = ln;
    }

}
