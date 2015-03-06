import java.util.Random;

public class QuickSelect {

    public static void partition(int[] ary, int si, int ei) {

        int[] d = new int[ary.length];
        System.arraycopy(ary, 0, d, 0, si);
        System.arraycopy(ary, ei+1, d, ei+1, ary.length - ei - 1);

        int pivot = ary[si], currIndex = si + 1;

        while (si < ei) {
            if (ary[currIndex] < pivot) {
                d[si] = ary[currIndex];
                si++;
            } else {
                d[ei] = ary[currIndex];
                ei--;
            }
            currIndex++;
        }
        d[si] = pivot;

        System.arraycopy(d, 0, ary, 0, d.length);
    }

    public static void main(String[] args) {
        
        int[] test = new int[20];
        Random r = new Random();
        for (int i = 0; i < test.length; i++)
            test[i] = r.nextInt(21) - 10; // random ints from -10 to 10

        for (int i = 0; i < test.length; i++)
            System.out.print(test[i] + " ");
        System.out.println();

        partition(test, 0, test.length-1); // partitions the whole array

        for (int i = 0; i < test.length; i++)
            System.out.print(test[i] + " ");
        System.out.println();

    }

}
