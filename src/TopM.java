/*
 * Sample usage: java-alg4 TopM 5 < tinyBatch.txt
 */

public class TopM {
    public static void main(String[] args){
        // Print the top M lines in the input stream.
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq = new MinPQ<Transaction>(M+1);
        while (StdIn.hasNextLine()){
            // Create an entry from the next line and put on the PQ.
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M)
                pq.delMin();   // Remove minimum if M+1 entries on the PQ.
        }  // Top M entries are on the PQ.

        LinkedStack<Transaction> stack = new LinkedStack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t : stack) StdOut.println(t);
    }
}
