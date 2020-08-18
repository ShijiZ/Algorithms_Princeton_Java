/*
 * Sample usage: java FrequencyCounter 1 < tinyTale.txt
 * Sample usage: java FrequencyCounter 8 < tale.txt
 */

public class FrequencyCounter {
    public static void main(String[] args){
        int minlen = Integer.parseInt(args[0]);  // key-length cutoff

        // st could be: SequentialSearchST, BinarySearchST, BST,
        // RedBlackBST, SeparateChainingST, LinearProbingHashST
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<>();
        while(!StdIn.isEmpty()){
            // Build symbol table and count frequencies.
            String word = StdIn.readString();
            if (word.length() < minlen) continue;  // Ignore short keys.
            if (!st.contains(word)) st.put(word, 1);
            else st.put(word, st.get(word)+1);
        }

        // Find a key with the highest frequency count.
        String max = "";
        st.put(max, 0);
        for (String word: st.keys())
            if (st.get(word)>st.get(max))
                max = word;
        StdOut.println(max + " " + st.get(max));
    }
}
