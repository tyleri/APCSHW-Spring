public class MergeSort {

    public int[] merge(int[] arr1, int[] arr2) {
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

    public Comparable[] merge(Comparable[] arr1, Comparable[] arr2) {
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

    public static void main(String[] args) {
        MergeSort m = new MergeSort();

        // testing ints
        int[] arr1 = new int[] { 1, 6, 7, 8, 10, 16 };
        int[] arr2 = new int[] { 5, 6, 6, 7, 17, 18, 20 };

        int[] mergedArr1 = m.merge(arr1, arr2);
        for (int i = 0; i < mergedArr1.length; i++) {
            System.out.print(mergedArr1[i] + " ");
        }
        System.out.println();
        System.out.println();

        // testing Comparables
        String[] arr3 = new String[] {"a", "c", "d", "d", "g", "n", "z"};
        String[] arr4 = new String[] {"b", "e", "g", "h", "i", "o", "y"};

        Comparable[] mergedArr2 = m.merge(arr3, arr4);
        for (int i = 0; i < mergedArr2.length; i++) {
            System.out.print(mergedArr2[i] + " ");
        }
        System.out.println();
    }
}
