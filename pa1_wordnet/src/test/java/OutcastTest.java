import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by ilyarudyak on 11/8/15.
 */
public class OutcastTest {

    private WordNet wn;
    private Outcast outcast;

    @Before
    public void setUp() {
        String synsetsFileName = "src/main/resources/synsets.txt";
        String hypernymsFileName = "src/main/resources/hypernyms.txt";
        wn = new WordNet(synsetsFileName, hypernymsFileName);
        outcast = new Outcast(wn);
    }

    @Test
    public void testOutcast() throws Exception {
        In in = new In("src/main/resources/outcast11.txt");
        String[] nouns = in.readAllStrings();
        StdOut.println(outcast.outcast(nouns));
    }
}


















