/*
 * Sample usage: java Accumulator 1000
 */

public class Accumulator {
    private double total;
    private int N;

    // Initializes an accumulator.
    public Accumulator(){
    }

    // Adds the specified data value to the accumulator.
    public void addDataValue(double val){
        N++;
        total += val;
    }

    // Returns the mean of the data values.
    public double mean(){
        return total/N;
    }

    // Returns a string representation of this accumulator.
    public String toString(){
        return "Mean ("+N+" values): "+String.format("%7.5f",mean());
    }

    public static void main(String[] args){
        int T = Integer.parseInt(args[0]);
        Accumulator a = new Accumulator();
        for (int t = 0; t < T; t++){
            a.addDataValue(StdRandom.uniform());
        }

        StdOut.println(a);
    }
}
