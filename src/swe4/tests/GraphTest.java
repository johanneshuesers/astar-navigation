package swe4.tests;


import org.junit.jupiter.api.Test;
import swe4.exceptions.InvalidVertexIdException;
import swe4.gis.Graph;
import swe4.gis.TimeCalculator;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
  @Test
  public void testAddVertex() {
    Graph g = new Graph();
    assertTrue(g.getVertices().isEmpty());
    g.addVertex(34, 35);
    assertFalse(g.getVertices().isEmpty());
    g.addVertex(52.349393, 68.487654);
    g.addVertex(-2, -5);
    assertEquals(3, g.getVertices().size());
  }

  @Test
  public void testAddEdge() {
    Graph g = new Graph();
    long v1 = g.addVertex(345.346, 234.245);
    long v2 = g.addVertex(2456.2456, 2456.246);
    long v3 = g.addVertex(247.245, 2456.4);
    try {
      g.addEdge("v1v2", v1, v2, 34, (short) 1);
      g.addEdge("v2v3", v2, v3, 4, (short) 1);
      assertEquals(2, g.getEdges().size());
      g.addEdge("v2v3", v2, v3, 45, (short) 1);
      assertFalse(g.getEdges().size() == 3);
    } catch (InvalidVertexIdException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testFindShortestPath() {
    Graph austria = new Graph();
    long bregenz = austria.addVertex(47.500000, 9.760000);
    long dornbirn = austria.addVertex(47.410000, 9.740000);
    long feldkirch = austria.addVertex(47.250000, 9.590000);
    long salzburg = austria.addVertex(47.810000, 13.050000);
    long innsbruck = austria.addVertex(47.270000, 11.400000);
    long villach = austria.addVertex(46.620000, 13.880000);
    long wels = austria.addVertex(48.160000, 14.020000);
    long linz = austria.addVertex(48.320000, 14.300000);
    long steyr = austria.addVertex(48.050000, 14.420000);
    long klagenfurt = austria.addVertex(46.620000, 14.300000);
    long leoben = austria.addVertex(47.380000, 15.010000);
    long graz = austria.addVertex(47.070000, 15.440000);
    long krems = austria.addVertex(48.410000, 15.610000);
    long stPoelten = austria.addVertex(48.210000, 15.630000);
    long wien = austria.addVertex(48.210000, 16.400000);
    long wienerNeustadt = austria.addVertex(47.810000, 16.230000);
    long eisenstadt = austria.addVertex(47.840000, 16.530000);

    try {
      austria.addEdge("bgzdbn", bregenz, dornbirn, 10, (short) 1);
      austria.addEdge("dbnfkh", dornbirn, feldkirch, 25, (short) 1);
      austria.addEdge("fkhinb", feldkirch, innsbruck, 158, (short) 1);
      austria.addEdge("bgzszg", bregenz, salzburg, 330, (short) 1);
      austria.addEdge("inbszg", innsbruck, salzburg, 166, (short) 1);
      austria.addEdge("inbvil", innsbruck, villach, 289, (short) 1);
      austria.addEdge("szgvil", salzburg, villach, 194, (short) 1);
      austria.addEdge("szgwel", salzburg, wels, 107, (short) 1);
      austria.addEdge("szgsty", salzburg, steyr, 130, (short) 1);
      austria.addEdge("szglbn", salzburg, leoben, 227, (short) 1);
      austria.addEdge("wellnz", wels, linz, 28, (short) 1);
      austria.addEdge("welsty", wels, steyr, 41, (short) 1);
      austria.addEdge("welkla", wels, klagenfurt, 225, (short) 1);
      austria.addEdge("vilkla", villach, klagenfurt, 40, (short) 1);
      austria.addEdge("lnzsty", linz, steyr, 37, (short) 1);
      austria.addEdge("styvil", steyr, villach, 257, (short) 1);
      austria.addEdge("stykla", steyr, klagenfurt, 220, (short) 1);
      austria.addEdge("stykrm", steyr, krems, 121, (short) 1);
      austria.addEdge("styspn", steyr, stPoelten, 108, (short) 1);
      austria.addEdge("stylbn", steyr, leoben, 152, (short) 1);
      austria.addEdge("wellbn", wels, leoben, 156, (short) 1);
      austria.addEdge("lnzkrm", linz, krems, 139, (short) 1);
      austria.addEdge("lnzspn", linz, stPoelten, 125, (short) 1);
      austria.addEdge("lbngrz", leoben, graz, 61, (short) 1);
      austria.addEdge("lbnwns", leoben, wienerNeustadt, 108, (short) 1);
      austria.addEdge("grzwns", graz, wienerNeustadt, 144, (short) 1);
      austria.addEdge("wnsest", wienerNeustadt, eisenstadt, 32, (short) 1);
      austria.addEdge("wnswie", wienerNeustadt, wien, 63, (short) 1);
      austria.addEdge("wieest", wien, eisenstadt, 61, (short) 1);
      austria.addEdge("spnwns", stPoelten, wienerNeustadt, 94, (short) 1);
      austria.addEdge("krmwie", krems, wien, 77, (short) 1);
      austria.addEdge("krmspn", krems, stPoelten, 27, (short) 1);
      austria.addEdge("spnwie", stPoelten, wien, 65, (short) 1);
    } catch (InvalidVertexIdException e) {
      e.printStackTrace();
    }

    assertEquals(4017, austria.pathLength(austria.getEdges()));

    assertEquals(5, austria.findShortestPath(dornbirn, wien).size());
    assertEquals(643, austria.pathLength(austria.findShortestPath(dornbirn, wien)));

    assertEquals(3, austria.findShortestPath(krems, graz).size());
    assertEquals(265, austria.pathLength(austria.findShortestPath(krems, graz)));

    assertEquals(5, austria.findShortestPath(innsbruck, eisenstadt).size());
    assertEquals(530, austria.pathLength(austria.findShortestPath(innsbruck, eisenstadt)));

    assertEquals(0, austria.findShortestPath(linz, linz).size());
  }

  @Test
  public void testFindMinimalPath() {
    Graph upperAustria = new Graph();
    long braunau = upperAustria.addVertex(48.250000, 13.040000);
    long ried = upperAustria.addVertex(48.210000, 13.490000);
    long voecklabruck = upperAustria.addVertex(48.010000, 13.660000);
    long gmunden = upperAustria.addVertex(47.920000, 13.800000);
    long badIschl = upperAustria.addVertex(47.710000, 13.620000);
    long traun = upperAustria.addVertex(48.230000, 14.240000);
    long ansfelden = upperAustria.addVertex(48.210000, 14.260000);
    long linz = upperAustria.addVertex(48.320000, 14.300000);
    long steyr = upperAustria.addVertex(48.050000, 14.420000);
    long wels = upperAustria.addVertex(48.160000, 14.020000);

    try {
      upperAustria.addEdge("brnrie", braunau, ried, 40, (short) 3);
      upperAustria.addEdge("brnvoe", braunau, voecklabruck, 70, (short) 2);
      upperAustria.addEdge("brnbil", braunau, badIschl, 94, (short) 3);
      upperAustria.addEdge("rievoe", ried, voecklabruck, 33, (short) 3);
      upperAustria.addEdge("voegmd", voecklabruck, gmunden, 17, (short) 3);
      upperAustria.addEdge("riewel", ried, wels, 48, (short) 1);
      upperAustria.addEdge("voewel", voecklabruck, wels, 36, (short) 1);
      upperAustria.addEdge("gmdwel", gmunden, wels, 45, (short) 1);
      upperAustria.addEdge("gmdsty", gmunden, steyr, 71, (short) 1);
      upperAustria.addEdge("welsty", wels, steyr, 41, (short) 3);
      upperAustria.addEdge("weltra", wels, traun, 18, (short) 1);
      upperAustria.addEdge("trasty", traun, steyr, 28, (short) 3);
      upperAustria.addEdge("tralnz", traun, linz, 13, (short) 1);
      upperAustria.addEdge("traans", traun, ansfelden, 6, (short) 4);
      upperAustria.addEdge("lnzans", linz, ansfelden, 14, (short) 1);
      upperAustria.addEdge("anssty", ansfelden, steyr, 27, (short) 3);
      upperAustria.addEdge("bilgmd", badIschl, gmunden, 33, (short) 3);

    } catch (InvalidVertexIdException e) {
      e.printStackTrace();
    }

    TimeCalculator tc = new TimeCalculator();

    assertEquals(634, upperAustria.pathLength(upperAustria.getEdges()));

    assertEquals(3, upperAustria.findMinimalPath(ansfelden, voecklabruck, tc).size());
    assertEquals(0.54, upperAustria.pathCosts(upperAustria.findMinimalPath(ansfelden, voecklabruck, tc), tc), 0.01);

    assertEquals(2, upperAustria.findMinimalPath(steyr, voecklabruck, tc).size());
    assertEquals(0.79, upperAustria.pathCosts(upperAustria.findMinimalPath(steyr, voecklabruck, tc), tc), 0.01);

    assertEquals(0, upperAustria.findShortestPath(braunau, braunau).size());
  }
}