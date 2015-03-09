import java.util.Random;
import java.util.Arrays;

public class Quick {

    public static int partition(int[] ary, int si, int ei, int pivotIndex) {
        int[] d = new int[ary.length];
        System.arraycopy(ary, 0, d, 0, si);
        System.arraycopy(ary, ei, d, ei, ary.length - ei);

        int pivot = ary[pivotIndex], currIndex = si;

        for (int i = ei - si; i > 0; i--) {
            if (currIndex != pivotIndex && ary[currIndex] < pivot) {
                d[si] = ary[currIndex];
                si++;
            } else if (currIndex != pivotIndex && ary[currIndex] >= pivot) {
                ei--;
                d[ei] = ary[currIndex];
            } currIndex++;
        }
        d[si] = pivot;

        System.arraycopy(d, 0, ary, 0, d.length);
        return si;
    }

    public static int quickselect(int[] ary, int n) {
        n--;
        int pivotIndex, si = 0, ei = ary.length;
        Random r = new Random();

        do {
            pivotIndex = partition(ary, si, ei, (int)(Math.random() * (ei-si-1) + si) );
            if (pivotIndex > n)
                ei = pivotIndex;
            else if (pivotIndex < n)
                si = pivotIndex + 1;
        } while (pivotIndex != n);

        return ary[n];
    }

    public static void quicksort(int[] ary) {
        quicksort(ary, 0, ary.length);
    }

    private static void quicksort(int[] ary, int si, int ei) {
        if (ei - si > 1) {
            int pivotIndex = partition(ary, si, ei, (int)(Math.random() * (ei-si-1) + si) );
            quicksort(ary, si, pivotIndex);
            quicksort(ary, pivotIndex+1, ei);
        }
    } public static void main(String[] args) {
        
        int[] test = new int[100];
        Random r = new Random();
        for (int i = 0; i < test.length; i++)
            test[i] = r.nextInt(2000001) - 1000000;

        System.out.println("Random array of ints:");
        System.out.println(Arrays.toString(test));
        System.out.println();

        quicksort(test);
        System.out.println("After sorting:");
        System.out.println(Arrays.toString(test));

        /*
        try {
            int index = r.nextInt(test.length);
            System.out.println( "The " + index + "(th/st/nd) value when the array is sorted: " +
                    quickselect(test, index) );

            System.out.println();
            Arrays.sort(test);
            System.out.println("Sorted Array (for verification):");
            for (int i = 0; i < test.length; i++)
                System.out.print(test[i] + " ");
            System.out.println();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        */

    }

}
