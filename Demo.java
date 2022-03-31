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

        for(Grammar.Production p : gram.getProductions())
        {
            String result = gram.isChomskyProd(p)? " is" : " is not";
            System.out.println(p + result +" in Chomsky Form");
        }

        String result = gram.isChomskyForm()? " is" : " is not";
        System.out.println("Grammar" + result + " in Chomsky Form");


        gram.printAlphabet();

        System.out.println(gram);

        //creazione grammatica esempio libro
        char[] terminals = {'a', 'b'};
        char[] nonterminals = {'A', 'B', 'S'};
        char axiom = 'S';
        Grammar.Production[] prods = new Grammar.Production[6];
        prods[0].setTesta('S');
        prods[0].setCorpo("ASA");

        prods[1].setTesta('S');
        prods[1].setCorpo("aB");

        prods

        Grammar gram2 = new Grammar();
    }


}
