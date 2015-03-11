public class LNode {

    private Object data;
    private LNode next;

    public LNode() { }

    public LNode(Object o) {
        data = o;
    }

    public Object getData() {
        return data;
    }

    public LNode getNext() {
        return next;
    }

    public void setData(Object o) {
        data = o;
    }

    public void setNext(LNode ln) {
        next = ln;
    }

}
