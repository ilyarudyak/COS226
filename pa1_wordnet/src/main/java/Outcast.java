/**
 * Created by ilyarudyak on 11/8/15.
 */
public class Outcast {

    private WordNet wordNet;

    // constructor takes a WordNet object
    public Outcast(WordNet wordNet) {
        this.wordNet = wordNet;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int max = Integer.MIN_VALUE;
        String outcast = null;
        for (String noun: nouns) {
            int distance = distance(noun, nouns);
            if (distance > max) {
                max = distance;
                outcast = noun;
            }
        }
        return outcast;
    }

    // helper functions
    private int distance(String nounA, String[] nouns) {
        int distance = 0;
        for (String noun: nouns) {
            distance += wordNet.distance(nounA, noun);
        }
        return distance;
    }

    // see test client below
    public static void main(String[] args) {

    }
}










