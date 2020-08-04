public class VisualAccumulator {
    private double total;
    private int N;

    // Initializes an accumulator.
    public VisualAccumulator(int trials, double max){
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }

    // Adds the specified data value to the accumulator.
    public void addDataValue(double val){
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total/N);
    }

    // Returns the mean of the data values.
    public double mean(){
        return total/N;
    }

    // Returns a string representation of this accumulator.
    public String toString(){
        return "Mean ("+N+" values): "+String.format("%7.5f",mean());
    }
}
