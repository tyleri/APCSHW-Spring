public class HeapSort {

    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        heapify();

        int end = arr.length-1;
    }

    private void heapify() {
        int index = arr.length / 2 - 1; // first element that needs to be pushed down
        for (int i = index; i >= 0; i--) { // push down each element
            pushDown(i);
        }
    }

    private void pushDown(int index) {
        int biggestChild;
        for (int j = index; j <= arr.length/2-1; j++) { // keep pushing element down to the right place

            // if the element only has one child or left child > right child
            if (j*2+2 == arr.length || arr[j*2+1] > arr[j*2+2]) {
                biggestChild = j*2+1;
            } else { // if right child > left child
                biggestChild = j*2+2;
            }

            // if the biggest of the children is bigger than the element, swap
            if (arr[j] < arr[biggestChild]) {
                int temp = arr[j];
                arr[j] = arr[biggestChild];
                arr[biggestChild] = temp;
            }
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
