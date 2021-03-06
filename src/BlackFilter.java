/*
 * Sample usage: java BlackFilter list.txt < tinyTale.txt
 */

public class BlackFilter {
    public static void main(String[] args){
        SET<String> set;
        set = new SET<>();

        In in = new In(args[0]);
        while (!in.isEmpty())
            set.add(in.readString());
        while (!StdIn.isEmpty()){
            String word = StdIn.readString();
            if (!set.contains(word))
                StdOut.println(word);
        }
    }
}
