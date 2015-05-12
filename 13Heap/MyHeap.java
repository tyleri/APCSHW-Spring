public class MyHeap {

    private int[] heap;
    private boolean hasRoot;
    private boolean isMaxHeap;

    public MyHeap() {
        heap = new int[16];
        hasRoot = false;
        isMaxHeap = true;
    }

    public MyHeap(boolean isMax) {
        this();
        isMaxHeap = isMax;
    }

    public String toString() {
        return "";
    }

    public int remove() {
        return 0;
    }

    public void add(int n) {
    }

    public int peek() {
        return heap[1];
    }
}
