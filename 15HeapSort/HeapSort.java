public class HeapSort {

    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        heapify();

        int end = arr.length-1;
        while (end > 0) {
            // swap first (biggest element) and last in heap
            int temp = arr[end];
            arr[end] = arr[0];
            arr[0] = temp;
            end--;

            pushDown(0, end);
        }
    }

    private void heapify() {
        int index = arr.length / 2 - 1; // first element that needs to be pushed down
        for (int i = index; i >= 0; i--) { // push down each element
            pushDown(i, arr.length-1);
        }
    }

    private void pushDown(int index, int end) {
        int biggestChild;
        while (index*2+1 <= end) { // keep pushing element down to the right place

            // if the element only has one child or left child > right child
            if (index*2+2 == end+1 || arr[index*2+1] > arr[index*2+2]) {
                biggestChild = index*2+1;
            } else { // if right child > left child
                biggestChild = index*2+2;
            }

            // if the biggest of the children is bigger than the element, swap
            if (arr[index] < arr[biggestChild]) {
                int temp = arr[index];
                arr[index] = arr[biggestChild];
                arr[biggestChild] = temp;
            }

            index = biggestChild;
        }
    }

    public String toString() {
        String s = "[";
        for (int i : arr) {
            s += i + " ";
        }
        return s.substring(0, s.length()-1) + "]";
    }

    public static void main(String[] args) {
        int[] array;
        if (args.length == 0) {
            array = new int[10];
            for (int i = 0; i < 10; i++) {
                array[i] = (int)(Math.random() * 100);
            }
        } else {
            array = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                array[i] = Integer.parseInt(args[i]);
            }
        }

        HeapSort hs = new HeapSort(array);
        System.out.println(hs);
        hs.sort();
        System.out.println(hs);
    }

}
