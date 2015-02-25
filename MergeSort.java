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

    public static void main(String[] args) {
        MergeSort m = new MergeSort();
        int[] arr1 = new int[] { 1, 6, 7, 8, 10, 16 };
        int[] arr2 = new int[] { 5, 6, 6, 7, 17, 18, 20 };

        int[] mergedArr = m.merge(arr1, arr2);
        for (int i = 0; i < mergedArr.length; i++) {
            System.out.print(mergedArr[i] + " ");
        }
        System.out.println();
    }
}
