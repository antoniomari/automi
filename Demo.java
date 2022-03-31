public class Demo {
    public static void main(String[] args) {
        Grammar gram = new Grammar();

        char[] term = {'a', 'b'};
        gram.addTerminal(term);
        gram.addTerminal('a');

        gram.printAlphabet();
    }
}
