import java.util.Arrays;
import java.util.Random;

public class Compare {

    public static void main(String[] args) {

        int size;
        try {
            size = Integer.parseInt( args[0] );
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage:\n$ java Compare size");
            return;
        }

        int[] arr1 = new int[size], arr2 = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++)
            arr1[i] = r.nextInt(2000001) - 1000000;
        System.arraycopy(arr1, 0, arr2, 0, size);

        System.out.println("Sorting with Arrays.sort:");
        long m = System.currentTimeMillis();
        Arrays.sort(arr1);
        System.out.println(System.currentTimeMillis() - m + " ms");

        System.out.println();
        System.out.println("Sorting with mergesort");
        m = System.currentTimeMillis();
        Sorts.mergesort(arr2);
        System.out.println(System.currentTimeMillis() - m + " ms");
    }
}
