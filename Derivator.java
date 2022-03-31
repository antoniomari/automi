import sun.awt.image.ImageWatched;

import java.util.LinkedList;
import java.util.Random;

public class Derivator
{
    private Grammar gram;
    private StringBuilder strBuffer;
    private int numOfNt;
    private LinkedList<Integer> ntIndexes;
    private Random random;

    Derivator(Grammar gram)
    {
        this.gram = gram;
        this.strBuffer = new StringBuilder( (Character) gram.getAxiom());
        this.numOfNt = 1;
        this.ntIndexes = new LinkedList<Integer>();
        ntIndexes.add(0);

    }

    Derivator()
    {
        random = new Random();
    }

    public void assignGrammar(Grammar gram)
    {
        this.gram = gram;
        this.strBuffer = new StringBuilder( (Character) gram.getAxiom());
        this.numOfNt = 1;
        this.ntIndexes = new LinkedList<Integer>();
        ntIndexes.add(0);
    }

    public void randomMove()
    {
        int index = ntIndexes.get(random.nextInt(ntIndexes.size()));
        char head = strBuffer.charAt(index);

        LinkedList<Grammar.Production> availableProds = Grammar.Production.select(gram.getProductions(), head);
        Grammar.Production choice = availableProds.get(random.nextInt(availableProds.size()));

        //
        strBuffer.deleteCharAt(index);
        strBuffer.insert(index, choice.getBody());
        System.out.println();
    }


}
