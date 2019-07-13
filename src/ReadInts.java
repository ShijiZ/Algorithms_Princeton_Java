/*
 * Sample usage: java-alg4 ReadInts < numbers.txt
 */

public class ReadInts {
    public static void main(String[] args){
        LinkedQueue<Integer> queue;
        queue = new LinkedQueue<Integer>();
        while (!StdIn.isEmpty())
            queue.enqueue(StdIn.readInt());

        for (int i: queue)
            StdOut.println(i);
    }
}
