import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra {
    private HashMap<Vertex, Integer> dist;
    private HashMap<Vertex, Vertex> pred;
    private ArrayList<Vertex> settled;
    private ArrayList<Vertex> unsettled;

    public Dijkstra() {
        this.dist = new HashMap<Vertex, Integer>();
        this.pred = new HashMap<Vertex, Vertex>();
        this.settled = new ArrayList<Vertex>();
        this.unsettled = new ArrayList<Vertex>();
    }

    public void Initialize(DirectedGraph G, Vertex s) {
        for (Vertex v : G.getV()) {
            this.dist.put(v, Integer.MAX_VALUE);
            this.unsettled.add(v);
        }
        this.dist.put(s, 0);
    }

    public Vertex ExtractMin() {
        Vertex min = unsettled.get(0);
        for (int i = 1; i < unsettled.size(); i++) {
            if (dist.get(unsettled.get(i)) < dist.get(min)) min = unsettled.get(i);
        }
        return min;
    }

    public void Relax (DirectedEdge e) {
        if (dist.get(e.getDestination()) > DirectedGraph.add(dist.get(e.getSource()), e.getWeight())) {
            this.dist.put(e.getDestination(), DirectedGraph.add(dist.get(e.getSource()), e.getWeight()));
            this.pred.put(e.getDestination(), e.getSource());
        }
    }

    public boolean Execute(DirectedGraph G, Vertex s) {
        for (DirectedEdge e : G.getE()) {
            if (e.getWeight() < 0) return false;
        }
        this.Initialize(G, s);
        Vertex u;
        while (!unsettled.isEmpty()) {
            u = this.ExtractMin();
            this.settled.add(u);
            this.unsettled.remove(u);
            for (DirectedEdge e : G.getAdj().get(u)) {
                if (!settled.contains(e.getDestination())) this.Relax(e);
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
