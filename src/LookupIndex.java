/*
 * Sample usage: java LookupIndex aminoI.csv ","
 * Sample usage: java LookupIndex movie.txt "/"
 */

public class LookupIndex {
    public static void main(String[] args){
        In in = new In(args[0]);  // index datatbase
        String sp = args[1];      // separator

        RedBlackBST<String, LinkedQueue<String>> st = new RedBlackBST<>();
        RedBlackBST<String, LinkedQueue<String>> ts = new RedBlackBST<>();

        while (in.hasNextLine()){
            String[] a = in.readLine().split(sp);
            String key = a[0];
            for (int i = 1; i < a.length; i++){
                String val = a[i];
                if (!st.contains(key)) st.put(key, new LinkedQueue<>());
                if (!ts.contains(val)) ts.put(val, new LinkedQueue<>());
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }

        while (!StdIn.isEmpty()){
            String query = StdIn.readLine();
            if (st.contains(query))
                for (String s: st.get(query))
                    StdOut.println(" " + s);

            if (ts.contains(query))
                for (String s: ts.get(query))
                    StdOut.println(" " + s);
        }
    }
}
