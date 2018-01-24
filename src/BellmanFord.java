import java.util.ArrayList;
import java.util.HashMap;

public class BellmanFord {
    private HashMap<Vertex, Integer> dist;
    private HashMap<Vertex, Vertex> pred;

    public BellmanFord() {
        this.dist = new HashMap<Vertex, Integer>();
        this.pred = new HashMap<Vertex, Vertex>();
    }

    public void Initialize(DirectedGraph G, Vertex s) {
        for (Vertex v : G.getV()) {
            this.dist.put(v, Integer.MAX_VALUE);
        }
        this.dist.put(s, 0);
    }

    public void Relax (DirectedEdge e) {
        if (dist.get(e.getDestination()) > DirectedGraph.add(dist.get(e.getSource()), e.getWeight())) {
            this.dist.put(e.getDestination(), DirectedGraph.add(dist.get(e.getSource()), e.getWeight()));
            this.pred.put(e.getDestination(), e.getSource());
        }
    }

    public boolean Execute(DirectedGraph G, Vertex s) {
        this.Initialize(G, s);
        for (int i = 0; i < G.getV().size() - 1; i++) {
            for (DirectedEdge e : G.getE()) {
                this.Relax(e);
            }
        }
        for (DirectedEdge e : G.getE()) {
            if (dist.get(e.getDestination()) > DirectedGraph.add(dist.get(e.getSource()), e.getWeight())) {
                return false;
            }
        }
        return true;
    }

    public void PrintShortestPath (Vertex s, Vertex d) {
        ArrayList<Vertex> path = new ArrayList<Vertex>();
        boolean valid = true;
        int weight = dist.get(d);
        if (weight == Integer.MAX_VALUE) valid = false;
        if (valid) {
            Vertex tmp = d;
            while (tmp != s) {
                path.add(tmp);
                tmp = pred.get(tmp);
            }
            path.add(s);
            System.out.print("Shortest Path from " + s.getId() + " to " + d.getId() + ": ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i).getId() + " ");
            }
            System.out.println("(Path weight: " + Integer.toString(weight) + ")");
        }
        else {
            System.out.println("No path from " + s.getId() + " to " + d.getId());
        }
    }
}
