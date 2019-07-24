/*
 * Sample usage: java-alg4 StopWatch 1000
 * Sample usage: java-alg4 StopWatch 2000
 * Sample usage: java-alg4 StopWatch 4000
 * Sample usage: java-alg4 StopWatch 8000
 */

public class StopWatch {
    private final long start;

    public StopWatch(){
        start = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long now = System.currentTimeMillis();
        return (now-start)/1000.0;
    }

    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        int[] a = new int[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(-1000000, 1000000);
        StopWatch timer = new StopWatch();
        int cnt = ThreeSum.count(a);
        double time = timer.elapsedTime();
        StdOut.println(cnt + " triples " + time);
    }
}
