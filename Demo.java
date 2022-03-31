public class Demo {
    public static void main(String[] args) {
        Grammar gram = new Grammar();

        //gram setup
        char[] term = {'a', 'b'};

        gram.addTerminal(term);
        gram.addTerminal('a');

        char[] nonterm = {'S'};
        gram.addNonterminal(nonterm);

        gram.setAxiom('S');

        gram.addProduction('S', new String[] {"aSa", "bSb", "aa", "bb"});


        gram.printAlphabet();

        System.out.println(gram);
    }


}
