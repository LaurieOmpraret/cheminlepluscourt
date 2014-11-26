package test;

import junit.framework.TestCase;
import main.Graph;
import main.Vertex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



/**
 * Created by laurieompraret on 26/11/2014.
 */
public class GraphTest {
    private Vertex lille = new Vertex("Lille");
    private Vertex paris = new Vertex("Paris");
    private Vertex reims = new Vertex("Reims");
    private Vertex nancy = new Vertex("Nancy");
    private Vertex lyon = new Vertex("Lyon");
    private Vertex marseille = new Vertex("Marseille");
    private Vertex lemans = new Vertex("Le Mans");
    private Vertex nantes = new Vertex("Nantes");
    private Vertex bordeaux = new Vertex("Bordeaux");
    private Vertex toulouse = new Vertex("Toulouse");
    private Vertex clermont = new Vertex("Clermont Ferrant");
    private Vertex montpellier = new Vertex("Montpellier");


    @Before
    public void setup() {
        lille.connectTo(reims, 206);
        lille.connectTo(paris, 222);
        lille.connectTo(nancy, 418);

        reims.connectTo(paris, 144);
        reims.connectTo(nancy, 245);
        reims.connectTo(lyon, 489);

        paris.connectTo(lyon, 465);
        paris.connectTo(lemans, 208);
        paris.connectTo(clermont, 423);

        lyon.connectTo(clermont, 166);
        lyon.connectTo(marseille, 313);
        lyon.connectTo(montpellier, 304);

        lemans.connectTo(nantes, 189);
        lemans.connectTo(bordeaux, 443);

        nantes.connectTo(bordeaux, 347);

        bordeaux.connectTo(toulouse, 243);

        toulouse.connectTo(montpellier, 245);

        montpellier.connectTo(marseille, 169);
        montpellier.connectTo(toulouse, 245);

        marseille.connectTo(montpellier, 169);

        clermont.connectTo(lyon, 166);
        clermont.connectTo(montpellier, 333);
        clermont.connectTo(marseille, 474);
    }

    @Test
    public void getDistanceForTwoAdjacentVertices() {
        Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);

        Assert.assertEquals(graph.getDistance("Paris", "Lyon"), 465);
    }

    @Test
    public void getDistancebyOneVertexGiven(){

        int distance = 0;
        Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);
        distance = graph.getDistance("Lille", "Paris") + graph.getDistance("Paris", "Lyon");
        Assert.assertEquals(distance, graph.getDistanceByOneVertexGiven("Lille", "Paris", "Lyon"));
    }

   @Test
    public void getDistanceByOneVertex(){
       int distance = 0;
       Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);
       Assert.assertEquals(graph.getDistanceByOneVertex("Lille", "Lyon"), 687 );


   }

    @Test
    public void getBestVertex(){
        Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);
        Assert.assertEquals(graph.getBestVertexToGo("Lille", "Lyon"), "Paris");


    }

    @Test
    public void getBestDistanceBy2Vertex() {
        int distance = -1 ;
        Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);
        Assert.assertEquals(graph.getDistanceBy2Vertex("Lille", "Marseille" ), 1000);

    }

    @Test
    public void getBestDistanceByNVertex() {
        int distance = -1 ;
        Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);
        Assert.assertEquals(graph.getDistanceByNVertex("Lille", "Marseille", 3 ), 1124);

    }

    @Test
    public void villeInexistante() {
        int distance = -1 ;
        Graph graph = new Graph(lille, paris, lyon, toulouse, montpellier, nancy, marseille, clermont, nantes, lemans, reims, bordeaux);
        Assert.assertEquals(graph.getDistanceByNVertex("Lille", "Licorne", 3 ), -1);

    }
}
