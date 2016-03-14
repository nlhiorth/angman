

public class Scores implements Comparable<Scores> {
    private String name;
    private int misses;

    public Scores(String name, int misses) {
        this.name = name;
        this.misses = misses;
    }

    public int getMisses() {
        return misses;
    }

    public String getName() {
        return name;
    }

    /*
    Used for Collections.sort(), sorts from least to most misses.
     */

    @Override public int compareTo(Scores s) {
        if (s.misses == this.misses) {
            return 0;
        } else if (s.misses > this.misses) {
            return -1;
        } else {
            return 1;
        }
    }
}
