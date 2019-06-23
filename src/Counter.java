public class Counter {
    private final String name;
    private int count;

    // Initializes a new counter starting at 0, with the given id.
    public Counter(String id){
        name = id;
    }

    // Increments the counter by 1.
    public void increment(){
        count++;
    }

    // Returns the current value of this counter.
    public int tally(){
        return count;
    }

    // Returns a string representation of this counter.
    public String toString(){
        return count+" "+name;
    }
}
