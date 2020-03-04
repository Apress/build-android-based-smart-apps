/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class JEOPS {
    public static void main(String args[]) {
        for(int i=0; i <100; i++) {
            Fibonacci f = new Fibonacci(i);
            FibonacciBase kb = new FibonacciBase(new PriorityConflictSet());
            kb.tell(f);
            kb.run();
            System.out.println(f.getN() + "th number of the fibonacci series = " + f.getValue());
        }
    }
}
