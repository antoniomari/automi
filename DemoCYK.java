public class DemoCYK
{
    public static void main(String[] args) throws Exception {
        //creazione grammatica esempio slide
        char[] terminals = {'a', 'b'};
        char[] nonterminals = {'A', 'B', 'C', 'S'};
        char axiom = 'S';
        Grammar.Production[] prods = new Grammar.Production[8];
        prods[0] = new Grammar.Production('S', "AB");
        prods[1] = new Grammar.Production('S', "BC");
        prods[2] = new Grammar.Production('A', "BA");
        prods[3] = new Grammar.Production('A', "a");
        prods[4] = new Grammar.Production('B', "CC");
        prods[5] = new Grammar.Production('B', "b");
        prods[6] = new Grammar.Production('C', "AB");
        prods[7] = new Grammar.Production('C', "a");

        Grammar gram2 = new Grammar(terminals, nonterminals, axiom, prods);

        for(Grammar.Production p : gram2.getProductions())
        {
            String result = gram2.isChomskyProd(p)? " is" : " is not";
            System.out.println(p + result +" in Chomsky Form");
        }

        System.out.println(gram2);


        // Derivator der = new Derivator(gram2);

        // String result = der.randomDerivation();

        String testString = "baaba";

        boolean resultCYK = gram2.CYK(testString, true);

        System.out.println("Result: " + resultCYK);
    }
}
