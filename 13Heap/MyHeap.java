import java.util.Arrays;

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
        return Arrays.toString(heap);
    }

    public int remove() {
        return 0;
    }

    public void add(int n) {
        if (!hasRoot) {
            heap[1] = n;
            hasRoot = true;
        } else if (isMaxHeap) {
            int index = heap[0] + 1; // next index in heap to fill in
            while ( index != 1 && n > heap[index/2]) {
                heap[index] = heap[index/2];
                index /= 2;
            }
            heap[index] = n;
        } else {
            int index = heap[0] + 1; // next index in heap to fill in
            while (index != 1 && n < heap[index/2]) {
                heap[index] = heap[index/2];
                index /= 2;
            }
            heap[index] = n;
        }

        heap[0]++;
    }

    public int peek() {
        return heap[1];
    }

    public static void main(String[] args) {
        MyHeap mh = new MyHeap();
        mh.add(5);
        System.out.println(mh);
        mh.add(19);
        System.out.println(mh);
        mh.add(23);
        System.out.println(mh);
        mh.add(1);
        System.out.println(mh);
    }
}
