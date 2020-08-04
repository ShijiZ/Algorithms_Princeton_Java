/*
 * Sample usage: java WhiteList tinyW.txt < tinyT.txt
 */

public class WhiteList {

    public static void main(String[] args){
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        StaticSetOfInts set = new StaticSetOfInts(whitelist);

        while (!StdIn.isEmpty())
        { // Read key, print if not in whitelist.
            int key = StdIn.readInt();
            if (!set.contains(key))
                StdOut.println(key);}
    }
}
