import java.util.Random;
import java.util.Arrays;

public class QuickSelect {

    public static int partition(int[] ary, int si, int ei, int pivotIndex) {

        int[] d = new int[ary.length];
        System.arraycopy(ary, 0, d, 0, si);
        System.arraycopy(ary, ei, d, ei, ary.length - ei);

        int pivot = ary[pivotIndex], currIndex = si;

		for (int i = ei - si; i > 0; i--) {
			if (ary[currIndex] < pivot) {
                d[si] = ary[currIndex];
                si++;
            } else if (ary[currIndex] >= pivot) {
                ei--;
                d[ei] = ary[currIndex];
            }
            currIndex++;
		}
        d[si] = pivot;

        System.arraycopy(d, 0, ary, 0, d.length);
		return si;
    }

	public static int quickselect(int[] ary, int n) {
		
		int pivotIndex = 0, si = 0, ei = ary.length;
		Random r = new Random();

		do {
			System.out.println(si + " " + ei);
			if (ei > si)
				pivotIndex = partition(ary, si, ei, r.nextInt(ei - si) + si);
			if (pivotIndex > n)
				ei = pivotIndex - 1;
			else if (pivotIndex < n)
				si = pivotIndex + 1;
		} while (pivotIndex != n);

		return ary[n];
	}

    public static void main(String[] args) {
        
        int[] test = new int[5];
        Random r = new Random();
        for (int i = 0; i < test.length; i++)
            test[i] = r.nextInt(21) - 10; // random ints from -10 to 10

        for (int i = 0; i < test.length; i++)
            System.out.print(test[i] + " ");
        System.out.println();

        //System.out.println( partition(test, 0, test.length, 0) ); // partitions the whole array

		try {
			System.out.println( quickselect(test, 1) );
			//Arrays.sort(test);
			for (int i = 0; i < test.length; i++)
				System.out.print(test[i] + " ");
			System.out.println();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}

    }

}
