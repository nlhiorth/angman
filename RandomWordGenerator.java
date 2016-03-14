public class RandomWordGenerator {

    static String[] words = { "computer", "programmer",
            "software", "debugger", "compiler", "developer", "algorithm",
            "array", "method", "variable" };

    public static String get() {
        return words[(int) (Math.random()*words.length)];
    }
}
