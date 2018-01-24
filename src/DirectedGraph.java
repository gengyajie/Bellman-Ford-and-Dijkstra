import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DirectedGraph {
    private ArrayList<Vertex> V;
    private ArrayList<DirectedEdge> E;
    private HashMap<Vertex, ArrayList<DirectedEdge>> adj;

    public DirectedGraph() {
        this.V = new ArrayList<Vertex>();
        this.E = new ArrayList<DirectedEdge>();
        this.adj = new HashMap<Vertex, ArrayList<DirectedEdge>>();
    }

    public ArrayList<Vertex> getV() {
        return V;
    }

    public ArrayList<DirectedEdge> getE() {
        return E;
    }

    public HashMap<Vertex, ArrayList<DirectedEdge>> getAdj() {
        return adj;
    }

    public void addV(Vertex v) {
        this.V.add(v);
        this.adj.put(v, new ArrayList<DirectedEdge>());
    }

    public void addE(DirectedEdge e) {
        this.E.add(e);
        this.adj.get(e.getSource()).add(e);
    }

    public void removeE(int index) {
        this.adj.get(E.get(index).getSource()).remove(this.E.get(index));
        this.E.remove(index);
    }

    public static DirectedGraph RandomGraph(int vsize, int esize, boolean negEdge) {
        DirectedGraph G = new DirectedGraph();
        Random rand = new Random();
        if (vsize > 1000) {
            System.out.println("Trivial input |V| (|V| > 1000)");
            System.exit(1);
        }
        else if (esize > vsize * (vsize - 1)) {
            System.out.println("Trivial inputs |V| and |E| (|E|>|V|(|V|-1))");
            System.exit(1);
        }
        else {
            for (int i = 0; i < vsize; i++) {
                G.addV(new Vertex(Integer.toString(i)));
            }
            ArrayList<DirectedEdge> edges = new ArrayList<DirectedEdge>();
            for (int i = 0; i < vsize; i++) {
                for (int j = 0; j < vsize; j++) {
                    if (i != j) {
                        DirectedEdge e = new DirectedEdge(G.getV().get(i), G.getV().get(j));
                        if (negEdge) e.setWeight(rand.nextInt(4299267) - 2149633);
                        else e.setWeight(rand.nextInt(2149634));
                        edges.add(e);
                    }
                }
            }
            int numRm = vsize*(vsize-1) - esize;
            if (esize <= numRm) {
                for (int i = 0; i < esize; i++) {
                    G.addE(edges.remove(rand.nextInt(edges.size())));
                }
            }
            else {
                for (int i = 0; i < numRm; i++) {
                    edges.remove(rand.nextInt(edges.size()));
                }
                for (DirectedEdge e : edges) G.addE(e);
            }
        }
        return G;
    }

    public static int add (int a, int b) {
        if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else return a+b;
    }
}
