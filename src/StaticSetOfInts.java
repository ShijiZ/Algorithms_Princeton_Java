import java.util.Arrays;

public class StaticSetOfInts {
    private int [] a;

    // Initializes a set of integers specified by the integer array.
    public StaticSetOfInts(int [] keys){
        a = new int[keys.length];
        for (int i = 0; i < keys.length; i++){
            a[i] = keys[i];// defensive copy
        }
        Arrays.sort(a);
    }

    // Is the key in this set of integers?
    public boolean contains(int key){
        return rank(key) != -1;
    }

    // Returns either the index of the search key in the sorted array
    // (if the key is in the set) or -1 (if the key is not in the set).
    private int rank(int key){
        // Binary search
        int lo = 0;
        int hi = a.length-1;
        while (lo <= hi)
        { // Key is in a[lo..hi] or not present.
            int mid = lo+(hi-lo)/2;
            if (key < a[mid]) hi = mid-1;
            else if (key > a[mid]) lo = mid+1;
            else return mid;
        }
        return -1;
    }
}
