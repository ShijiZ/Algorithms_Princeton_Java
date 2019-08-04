/*
 * Sample usage: java-alg4 SparseVector
 */

public class SparseVector {
    private ST<Integer, Double> st;

    public SparseVector(){
        st = new ST<Integer, Double>();
    }

    public int size(){
        return st.size();
    }

    public void put(int i, double x){
        st.put(i, x);
    }

    public double get(int i){
        if (!st.contains(i)) return 0.0;
        else return st.get(i);
    }

    public double dot(double[] that){
        double sum = 0.0;
        for (int i: st.keys())
            sum += that[i]*this.get(i);
        return sum;
    }

    public static void main(String[] args) {
        SparseVector a = new SparseVector();
        a.put(3, 0.50);
        a.put(9, 0.75);
        a.put(6, 0.11);
        a.put(6, 0.00);
        double[] b = {0, 0, 0, 0.6, 0.9, 0, 0, 0, 0, 0, 0};
        StdOut.println("a = " + a);
        StdOut.println("b = " + b);
        StdOut.println("a dot b = " + a.dot(b));
    }
}
