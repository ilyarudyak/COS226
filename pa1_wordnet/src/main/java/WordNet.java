import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilyarudyak on 11/7/15.
 */
public class WordNet {

    // symbol table with key=noun and value=synset_id
    private Map<String, List<Integer>> nounsMap;

    // we can get a synset (as a string) by id like that: synsetsList.get(id);
    private List<String> synsetsList;

    private Digraph wordNetDigraph;
    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsetsFileName, String hypernymsFileName) {

        if (synsetsFileName == null || hypernymsFileName == null) {
            throw new NullPointerException();
        }

        nounsMap = new HashMap<>();
        synsetsList = new ArrayList<>();
        int V = readSynsets(synsetsFileName);

        wordNetDigraph = new Digraph(V);
        readHypernyms(hypernymsFileName);
        if (hasCycle(wordNetDigraph) || hasMultipleRoots(wordNetDigraph)) {
            throw new IllegalArgumentException();
        }

        sap = new SAP(wordNetDigraph);
    }

    // helper functions
    private boolean hasCycle(Digraph G) {
        DirectedCycle dc = new DirectedCycle(G);
        return dc.hasCycle();
    }
    private boolean hasMultipleRoots(Digraph G) {
        int multipleRootsCount = 0;
        for (int i = 0; i < G.V(); i++) {
            if (G.outdegree(i) == 0) {
                multipleRootsCount++;
            }
        }
        return multipleRootsCount > 1;
    }
    private void readHypernyms(String hypernymsFileName) {

        In in = new In(hypernymsFileName);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] splitLine = line.split(",");
            int v = Integer.parseInt(splitLine[0].trim());
            for (int i = 1; i < splitLine.length; i++) {
                wordNetDigraph.addEdge(v, Integer.parseInt(splitLine[i].trim()));
            }
        }
    }
    private int readSynsets(String synsetsFileName) {

        In in = new In(synsetsFileName);
        int V = 0;
        while (in.hasNextLine()) {
            V++;
            String line = in.readLine();
            String[] splitLine = line.split(",");
            int synsetId = Integer.parseInt(splitLine[0].trim());

            // add synset as a string to synsetsList
            synsetsList.add(splitLine[1].trim());

            // split synset and add each word to nounsMap
            // as a key with synset id as a pair
            String[] words = splitLine[1].split(" ");
            for (String word: words) {
                if (nounsMap.containsKey(word)) {
                    List<Integer> synsetsIdList = nounsMap.get(word);
                    synsetsIdList.add(synsetId);
                    nounsMap.put(word, synsetsIdList);
                } else {
                    List<Integer> synsetsIdList = new ArrayList<>();
                    synsetsIdList.add(synsetId);
                    nounsMap.put(word, synsetsIdList);
                }

            }
        }
        return V;
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nounsMap.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {

        if (word == null) {
            throw new NullPointerException();
        }

        return nounsMap.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {

        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }

        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }

        List<Integer> nounASynsetIdList = nounsMap.get(nounA);
        List<Integer> nounBSynsetIdList = nounsMap.get(nounB);
        return sap.length(nounASynsetIdList, nounBSynsetIdList);
    }

    // a synset (second field of synsetsList.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

        if (nounA == null || nounB == null) {
            throw new NullPointerException();
        }

        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        List<Integer> nounASynsetIdList = nounsMap.get(nounA);
        List<Integer> nounBSynsetIdList = nounsMap.get(nounB);
        int ancestorId = sap.ancestor(nounASynsetIdList, nounBSynsetIdList);
        return synsetsList.get(ancestorId);
    }

    // do unit testing of this class
    public static void main(String[] args) {

        String synsetsFileName = "src/main/resources/synsets15.txt";
        String hypernymsFileName = "src/main/resources/hypernyms3InvalidTwoRoots.txt";
        WordNet wn = new WordNet(synsetsFileName, hypernymsFileName);

//        StdOut.print(wn.wordNetDigraph);
//        StdOut.println(wn.distance("h", "m"));
//        StdOut.println(wn.sap("h", "m"));
//        StdOut.println(wn.isNoun("x"));

    }
}
















