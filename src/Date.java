/*
 * Sample usage: java Date 12 31 1999
 */

public class Date implements Comparable<Date>{
    private final int month;
    private final int day;
    private final int year;

    // Initializes a new date from the month, day, and year.
    public Date(int m, int d, int y){
        month = m;
        day = d;
        year = y;
    }

    // Initializes new date specified as a string in form MM/DD/YYYY.
    public Date(String date) {
        String[] fields = date.split("/");
        if (fields.length != 3) {
            throw new IllegalArgumentException("Invalid date");
        }
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);
    }

    // Return the month.
    public int month(){
        return month;
    }

    // Return the day.
    public int day(){
        return day;
    }

    // Return the year.
    public int year(){
        return year;
    }

    @Override
    public int compareTo(Date that) {
        if (this.year > that.year) return 1;
        if (this.year < that.year) return -1;
        if (this.month > that.month) return 1;
        if (this.month < that.month) return -1;
        if (this.day > that.day) return 1;
        if (this.day < that.day) return -1;
        return 0;
    }

    // Returns a string representation of this date.
    public String toString(){
        return month+"/"+day+"/"+year;
    }

    // Compares this date to the specified date.
    public boolean equals(Object other){
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Date that = (Date) other;
        return (this.month == that.month)&&(this.day == that.day)&&(this.year == that.year);
    }


    // Test client code
    public static void main(String[] args){
        int m = Integer.parseInt(args[0]);
        int d = Integer.parseInt(args[1]);
        int y = Integer.parseInt(args[2]);

        Date date = new Date(m, d, y);

        StdOut.println(date);
    }
}
