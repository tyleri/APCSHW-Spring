public class HeapSort {

    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void sort() {
        heapify();
    }

    private void heapify() {
        int index = arr.length / 2 - 1; // first element that needs to be pushed down

        int biggestChild;
        for (int i = index; i >= 0; i--) { // push down each element

            for (int j = i; j <= index; j++) { // keep pushing it down to the right place
                
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
    }

    public String toString() {
        String s = "[";
        for (int i : arr) {
            s += i + " ";
        }
        return s.substring(0, s.length()-1) + "]";
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort( new int[] {1, 5, 3, 19, 4, 12} );
        System.out.println(hs);
        hs.sort();
        System.out.println(hs);
    }

}
