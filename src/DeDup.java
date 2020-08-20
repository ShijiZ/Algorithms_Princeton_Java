/*
 * Sample usage: java DeDup < tinyTale.txt
 */

public class DeDup {
    public static void main(String[] args){
        SET<String> set;
        set = new SET<>();

        while (!StdIn.isEmpty()){
            String key = StdIn.readString();
            if (!set.contains(key)){
                set.add(key);
                StdOut.println(key);
            }
        }
    }
}
