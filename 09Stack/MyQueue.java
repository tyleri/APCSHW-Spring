public class MyQueue<T> {

    MyLinkedList<T> mll;

    public boolean enqueue(T e) {
        mll.add(e);
        return true;
    }

    public T dequeue() {
        return mll.remove();
    }

}
