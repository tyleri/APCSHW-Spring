import java.util.Arrays;
import java.util.Random;

public class Sorts {

    public static int[] merge(int[] arr1, int[] arr2) {
        
        if (arr1.length > 1)
            arr1 = merge(
                    Arrays.copyOfRange(arr1, 0, arr1.length/2),
                    Arrays.copyOfRange(arr1, arr1.length/2, arr1.length) );
        if (arr2.length > 1)
            arr2 = merge(
                    Arrays.copyOfRange(arr2, 0, arr2.length/2),
                    Arrays.copyOfRange(arr2, arr2.length/2, arr2.length) );

        int[] newArr = new int[ arr1.length + arr2.length ];

        int n1 = 0, n2 = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (n1 == arr1.length)
                newArr[i] = arr2[n2++];
            else if (n2 == arr2.length)
                newArr[i] = arr1[n1++];
            else
                newArr[i] = (arr1[n1] < arr2[n2] ?
                        arr1[n1++] : arr2[n2++]);
        }

        return newArr;
    }

    // not part of the assignment
    public static Comparable[] merge(Comparable[] arr1, Comparable[] arr2) {

        if (arr1.length > 1)
            arr1 = merge(
                    Arrays.copyOfRange(arr1, 0, arr1.length/2),
                    Arrays.copyOfRange(arr1, arr1.length/2, arr1.length) );
        if (arr2.length > 1)
            arr2 = merge(
                    Arrays.copyOfRange(arr2, 0, arr2.length/2),
                    Arrays.copyOfRange(arr2, arr2.length/2, arr2.length) );

        Comparable[] newArr = new Comparable[ arr1.length + arr2.length ];

        int n1 = 0, n2 = 0;
        for (int i = 0; i < newArr.length; i++) {
            if (n1 == arr1.length)
                newArr[i] = arr2[n2++];
            else if (n2 == arr2.length)
                newArr[i] = arr1[n1++];
            else
                newArr[i] = ( arr1[n1].compareTo(arr2[n2]) < 0 ?
                        arr1[n1++] : arr2[n2++]);
        }

        return newArr;
    }
    
    public static void mergesort(int[] arr) {
        int[] sortedArr = merge(
                Arrays.copyOfRange(arr, 0, arr.length/2),
                Arrays.copyOfRange(arr, arr.length/2, arr.length) );
        for (int i = 0; i < arr.length; i++)
            arr[i] = sortedArr[i];
    }

    // not part of the assignment
    public static void mergesort(Comparable[] arr) {
        Comparable[] sortedArr = merge(
                Arrays.copyOfRange(arr, 0, arr.length/2),
                Arrays.copyOfRange(arr, arr.length/2, arr.length) );
        for (int i = 0; i < arr.length; i++)
            arr[i] = sortedArr[i];
    }

    public static void main(String[] args) {

        // testing int merge
        System.out.println("Testing int merge:");
        int[] arr1 = new int[] { 1, 6, 7, 8, 10, 16 };
        int[] arr2 = new int[] { 5, 6, 6, 7, 17, 18, 20 };

        int[] mergedArr1 = merge(arr1, arr2);
        for (int i = 0; i < mergedArr1.length; i++) {
            System.out.print(mergedArr1[i] + " ");
        }
        System.out.println();
        System.out.println();

        // testing Comparable merge
        System.out.println("Testing Comparable merge:");
        String[] arr3 = new String[] {"a", "c", "d", "d", "g", "n", "z"};
        String[] arr4 = new String[] {"b", "e", "g", "h", "i", "o", "y"};

        Comparable[] mergedArr2 = merge(arr3, arr4);
        for (int i = 0; i < mergedArr2.length; i++) {
            System.out.print(mergedArr2[i] + " ");
        }
        System.out.println();
        System.out.println();

        // testing mergesort
        System.out.println("Testing mergesort:");
        int[] arr5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }; // sorted
        int[] arr6 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }; // reverse sorted

        Random r = new Random();
        int[] arr7 = new int[40]; // randomized tiny range of values
        for (int i = 0; i < 40; i++)
            arr7[i] = r.nextInt(3) + 1;
        int[] arr8 = new int[40]; // randomized wide range of values
        for (int i = 0; i < 40; i++)
            arr8[i] = r.nextInt(2000001) - 1000000;

        System.out.print("Before sort: ");
        for (int i = 0; i < arr5.length; i++)
            System.out.print(arr5[i] + " ");
        System.out.println();
        System.out.print("After sort: ");
        mergesort(arr5);
        for (int i = 0; i < arr5.length; i++)
            System.out.print(arr5[i] + " ");
        System.out.println();

        System.out.print("Before sort: ");
        for (int i = 0; i < arr6.length; i++)
            System.out.print(arr6[i] + " ");
        System.out.println();
        System.out.print("After sort: ");
        mergesort(arr6);
        for (int i = 0; i < arr6.length; i++)
            System.out.print(arr6[i] + " ");
        System.out.println();

        System.out.print("Before sort: ");
        for (int i = 0; i < arr7.length; i++)
            System.out.print(arr7[i] + " ");
        System.out.println();
        System.out.print("After sort: ");
        mergesort(arr7);
        for (int i = 0; i < arr7.length; i++)
            System.out.print(arr7[i] + " ");
        System.out.println();

        System.out.print("Before sort: ");
        for (int i = 0; i < arr8.length; i++)
            System.out.print(arr8[i] + " ");
        System.out.println();
        System.out.print("After sort: ");
        mergesort(arr8);
        for (int i = 0; i < arr8.length; i++)
            System.out.print(arr8[i] + " ");
        System.out.println();
        System.out.println();

        // testing Comparable mergesort
        System.out.println("Testing Comparable mergesort:");
        String test = "I will grade and post results Saturday morning. Submit before then if you want a chance to re-do for full credit. No late submissions will be graded. I will re-grade and post results Monday morning.";
        String[] arr9 = test.split(" ");
        System.out.println("Before sort: " + test);
        mergesort(arr9);
        System.out.print("After sort: ");
        for (int i = 0; i < arr9.length; i++)
            System.out.print(arr9[i] + " ");
        System.out.println();

    }
}
