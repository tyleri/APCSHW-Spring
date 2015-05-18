public class HeapSort {

    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        
    }

    private void heapify() {
        
    }

    public String toString() {
        String s = "[";
        for (int i : arr) {
            s += i + " ";
        }
        return s.substring(0, arr.length-1) + "]";
    }

}
