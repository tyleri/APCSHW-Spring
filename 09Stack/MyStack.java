import java.util.EmptyStackException;

public class MyStack<T> {

    MyLinkedList<T> mll;

    public MyStack() {
        mll = new MyLinkedList<T>();
    }
    
    public boolean empty() {
        return mll.isEmpty();
    }

    public T push(T item) {
        mll.add(0, item);
        return item;
    }

    public T pop() {
        if (empty())
            throw new EmptyStackException();
        return mll.remove();
    }

    public T peek() {
        if (empty())
            throw new EmptyStackException();

        return mll.get(0);
    }

    public int search(Object o) {
        return mll.indexOf((T)o);
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
