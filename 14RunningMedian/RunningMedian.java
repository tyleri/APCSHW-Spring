public class RunningMedian {

    private MyHeap left, right;

    public RunningMedian() {
        left = new MyHeap();
        right = new MyHeap(false);
    }

    public void add(int value) {
        if (left.size() + right.size() == 0) {
            left.add(value);
        } else if (left.size() + right.size() == 1) {
            if (value < left.peek()) {
                left.add(value);
                value = left.remove();
            }
            right.add(value);
        } else {
            int median = getMedian();
            if (value > median) {
                right.add(value);
            } else {
                left.add(value);
            }
        }
    }

    public double getMedian() {
        if (left.size() + right.size() == 0) {
            return 0;
        } else if (left.size() + right.size() == 1) {
            return left.peek();
        } else if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2;
        } else if (left.size() > right.size()) {
            return left.peek();
        } else {
            return right.peek();
        }
    }

    public static void main(String args) {
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
                rm.add(args[i]);
                System.out.println("Median: " + rm.getMedian());
            }
        }
    }
}
