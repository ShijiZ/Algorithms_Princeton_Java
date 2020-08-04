/*
 * Sample usage: java Counter 6 10000
 */

public class Counter {
    private final String name;
    private int count = 0;

    // Initializes a new counter starting at 0, with the given id.
    public Counter(String id){
        name = id;
    }

    // Increments the counter by 1.
    public void increment(){
        count++;
    }

    // Returns the current value of this counter.
    public int tally(){
        return count;
    }

    // Returns a string representation of this counter.
    public String toString(){
        return count+" "+name;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        // create n counters
        Counter[] hits = new Counter[n];
        for (int i = 0; i < n; i++) {
            hits[i] = new Counter("counter" + i);
        }

        // increment trials counters at random
        for (int t = 0; t < trials; t++) {
            hits[StdRandom.uniform(n)].increment();
        }

        // print results
        for (int i = 0; i < n; i++) {
            StdOut.println(hits[i]);
        }
    }
}
