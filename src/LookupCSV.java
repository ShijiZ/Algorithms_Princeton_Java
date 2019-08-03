/*
 * Sample usage: java-alg4 LookupCSV ip.csv 1 0
 * Sample usage: java-alg4 LookupCSV amino.csv 0 3
 * Sample usage: java-alg4 LookupCSV DJIA.csv 0 3
 */

public class LookupCSV {
    public static void main(String[] args){
        In in = new In(args[0]);
        int keyField = Integer.parseInt(args[1]);
        int valField = Integer.parseInt(args[2]);

        ST<String, String> st = new ST<String, String>();

        while (in.hasNextLine()){
            String line = in.readLine();
            String[] tokens = line.split(",");
            String key = tokens[keyField];
            String val = tokens[valField];
            st.put(key, val);
        }

        while (!StdIn.isEmpty()){
            String query = StdIn.readString();
            if (st.contains(query))
                StdOut.println(st.get(query));
        }
    }
}
