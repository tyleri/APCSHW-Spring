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
    
    private int maxLen() {
        int curr, max = 0;

        for (int i = 0; i <= heap[0]; i++) {
            curr = Integer.toString(heap[i]).length();
            if (curr > max) {
                max = curr;
            }
        }

        return max;
    }

    public int remove() {
        heap[0]--; // decrement size
        int root = heap[1];

        int index = 1, maxIndex, minIndex;
        while (heap[index] != 0 && index <= heap[0] && index*2+1 < heap.length)  {
            if (isMaxHeap) {
                if (heap[index*2] > heap[index*2+1]) {
                    maxIndex = index*2;
                } else {
                    maxIndex = index*2+1;
                }
                heap[index] = heap[maxIndex];
                index = maxIndex;
            } else {
                if (heap[index*2] < heap[index*2+1]) {
                    minIndex = index*2;
                } else {
                    minIndex = index*2+1;
                }
                heap[index] = heap[minIndex];
                index = minIndex;
            }
        }

        for (int i = index/2; i <= heap[0]+1; i++) {
            heap[i] = heap[i+1];
        }

        return root;
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
        System.out.println(mh.remove());
        System.out.println(mh);
        mh.add(100);
        System.out.println(mh);
        mh.add(29);
        System.out.println(mh);
        mh.add(50);
        System.out.println(mh);
        mh.add(30);
        System.out.println(mh);
        mh.add(80);
        System.out.println(mh);
        mh.add(21);
        System.out.println(mh);
        System.out.println(mh.remove());
        System.out.println(mh);

    }
}
