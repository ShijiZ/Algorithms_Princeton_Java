/*
 * Sample usage: java-alg4 LookupIndex aminoI.csv ","
 * Sample usage: java-alg4 LookupIndex movie.txt "/"
 */

public class LookupIndex {
    public static void main(String[] args){
        In in = new In(args[0]);  // index datatbase
        String sp = args[1];      // separator

        ST<String, LinkedQueue<String>> st = new ST<String, LinkedQueue<String>>();
        ST<String, LinkedQueue<String>> ts = new ST<String, LinkedQueue<String>>();

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
