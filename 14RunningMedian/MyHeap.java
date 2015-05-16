import java.util.Arrays;

public class MyHeap {

    private int[] heap;
    private boolean isMaxHeap;

    public MyHeap() {
        heap = new int[16];
        isMaxHeap = true;
    }

    public MyHeap(boolean isMax) {
        this();
        isMaxHeap = isMax;
    }

    public String toString() {
        if (heap[0] == 0) {
            return "";
        }
        String s = "";
        int len = maxLen();
        int maxLevel = (int)(Math.log(heap[0]) / Math.log(2));
        s += getStartSpaces(maxLevel, len);

        int index = 1, row = 0;
        for (int i = 0; i <= maxLevel; i++) {
            for (int j = 0; j < Math.pow(2, i); j++) {
                s += String.format("%" + len + "d", heap[index]).replace(" ", "0");
                s += getBetSpaces(maxLevel-row, len);
                index++;
                if (index > heap[0]) {
                    break;
                }
            }
            s += "\n";
            row++;
            s += getStartSpaces(maxLevel-row, len);
        }

        return s;
    }

    private String getStartSpaces(int levelFromBot, int len) {
        char[] arr = new char[(int)((Math.pow(2, levelFromBot)-1))*len];
        Arrays.fill(arr, ' ');
        return new String(arr);
    }

    private String getBetSpaces(int levelFromBot, int len) {
        char[] arr = new char[((int)((Math.pow(2, levelFromBot)-1))*2+1)*len];
        Arrays.fill(arr, ' ');
        return new String(arr);
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
        while (index <= heap[0] && index*2+1 < heap.length) {
            if (isMaxHeap) {
                if (heap[0] == index*2 || heap[index*2] > heap[index*2+1]) {
                    maxIndex = index*2;
                } else {
                    maxIndex = index*2+1;
                }
                heap[index] = heap[maxIndex];
                index = maxIndex;
            } else {
                if (heap[0] == index*2 || heap[index*2] < heap[index*2+1]) {
                    minIndex = index*2;
                } else {
                    minIndex = index*2+1;
                }
                heap[index] = heap[minIndex];
                index = minIndex;
            }
        }

        for (int i = index/2; i <= heap[0]; i++) {
            heap[i] = heap[i+1];
        }

        return root;
    }

    public void add(int n) {
        if (heap[0] == 0) {
            heap[1] = n;
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
        if (heap[0] == heap.length-1) {
           resize();
        }
    }

    private void resize() {
        System.out.println(heap.length);
        int[] arr = new int[heap.length*2];
        System.arraycopy(heap, 0, arr, 0, heap.length);
        heap = arr;
        System.out.println(heap.length);
    }

    public int peek() {
        return heap[1];
    }

    public int size() {
        return heap[0];
    }

    public static void main(String[] args) {
        MyHeap mh = new MyHeap();
        if (args.length == 0) {
            mh.add(3);
            System.out.println(mh);
            mh.add(4);
            System.out.println(mh);
            mh.add(23);
            System.out.println(mh);
            mh.add(1);
            System.out.println(mh);
            System.out.println(mh.remove());
            System.out.println(mh);
        } else {
            for (int i = 0; i < args.length; i++) {
                mh.add(Integer.parseInt(args[i]));
                System.out.println(mh);
            }
        }
    }
}
