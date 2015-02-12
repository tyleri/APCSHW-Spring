public class Recursion {

    public String name() {
        return "Ishikawa,Tyler";
    }

    public int fact(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        else if (n < 2)
            return 1;
        return n * fact(n-1);
    }
    
    public int fib(int n) {
        if (n < 0)
            throw new IllegalArgumentException();
        else {
            return fibHelper(0, 1, n);
        }
    }

    private int fibHelper(int num1, int num2, int steps) {
        if (steps == 0)
            return num1;
        else
            return fibHelper(num2, num1+num2, steps-1);
    }

    public double sqrt(double n) {
        return sqrtHelper(n, 1);
    }

    private double sqrtHelper(double n, double guess) {
        if ( Math.sqrt(n) == guess )
            return guess;
            
        return sqrtHelper(n, (n / guess + guess) / 2);
    }

    public static void main(String[] args) {
        // tests
        Recursion r = new Recursion();
        System.out.println(r.name());
        System.out.println(r.fact(10));
        System.out.println(r.fib(20));
        System.out.println(r.sqrt(10.8));
    }
}
