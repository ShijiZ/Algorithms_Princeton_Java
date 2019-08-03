/*
 * Sample usage: java WhiteFilter list.txt < tinyTale.txt
 */

public class WhiteFilter {
    public static void main(String[] args){
        SET<String> set;
        set = new SET<String>();

        In in = new In(args[0]);
        while (!in.isEmpty())
            set.add(in.readString());
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (set.contains(word))
                StdOut.println(word);
        }
    }
}
