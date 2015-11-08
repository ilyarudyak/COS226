import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilyarudyak on 11/8/15.
 */
public class SAPTest {

    private Digraph G1;
    private SAP sap1;

    @Before
    public void setUp() throws Exception {

        G1 = new Digraph(new In("src/main/resources/digraph1.txt"));
        sap1 = new SAP(G1);
    }

    @Test
    public void testLength() throws Exception {
        StdOut.println(sap1.ancestor(3, 8));
    }

    @Test
    public void testAncestor() throws Exception {

    }
}