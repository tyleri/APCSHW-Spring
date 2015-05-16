public class RunningMedian {

    private MyHeap left, right;

    public RunningMedian() {
        left = new MyHeap();
        right = new MyHeap(false);
    }

    public void add(int value) {
        if (value < getMedian()) {
            left.add(value);
            while (left.size() - right.size() > 1) {
                right.add(left.remove());
            }
        } else {
            right.add(value);
            while (right.size() - left.size() > 1) {
                left.add(right.remove());
            }
        }

    }

    public double getMedian() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else if (left.size() > right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }

    public static void main(String[] args) {
        RunningMedian rm = new RunningMedian();
        if (args.length == 0) {
            rm.add(3);
            System.out.println(rm.getMedian());
            rm.add(5);
            System.out.println(rm.getMedian());
            rm.add(9);
            System.out.println(rm.getMedian());
        } else {
            for (int i = 0; i < args.length; i++) {
                rm.add(Integer.parseInt(args[i]));
                System.out.println("Median: " + rm.getMedian());
            }
        }
    }
}
