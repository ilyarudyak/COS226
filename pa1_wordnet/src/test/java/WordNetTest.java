import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by ilyarudyak on 11/8/15.
 */
public class WordNetTest {

    private WordNet wn;

    @Before
    public void setUp() {
        String synsetsFileName = "src/main/resources/synsets15.txt";
        String hypernymsFileName = "src/main/resources/hypernyms15Tree.txt";
        wn = new WordNet(synsetsFileName, hypernymsFileName);
    }

    @Test
    public void testIsNoun() throws Exception {

    }

    @Test
    public void testDistance() throws Exception {

        // vertices are not connected with each other
        assertEquals(2, wn.distance("h", "i"));
        assertEquals(2, wn.distance("j", "k"));
        assertEquals(4, wn.distance("i", "j"));
        assertEquals(4, wn.distance("i", "c"));
        assertEquals(7, wn.distance("h", "o"));

        // vertices are connected with each other
        assertEquals(1, wn.distance("h", "d"));
        assertEquals(3, wn.distance("h", "a"));
        assertEquals(3, wn.distance("o", "k"));
    }

    @Test
    public void testSap() throws Exception {

    }
}












