public class MyQueue<T> {

    MyLinkedList<T> mll;

    public MyQueue() {
        mll = new MyLinkedList<T>();
    }

    public boolean enqueue(T e) {
        mll.add(e);
        return true;
    }

    public T dequeue() {
        return mll.remove();
    }

    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<Integer>();
        System.out.println( q.enqueue(1) );
        System.out.println( q.enqueue(2) );
        System.out.println( q.dequeue() );
        System.out.println( q.enqueue(3) );
        System.out.println( q.dequeue() );
        System.out.println( q.dequeue() );
    }

}
