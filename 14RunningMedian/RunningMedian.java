public class RunningMedian {

    private MyHeap left, right;

    public RunningMedian() {
        left = new MyHeap();
        right = new MyHeap(false);
    }

    public void add(int value) {

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

}
