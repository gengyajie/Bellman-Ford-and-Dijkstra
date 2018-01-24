import java.util.ArrayList;

public class DirectedEdge {
    private Vertex source;
    private Vertex destination;
    private int weight;

    public DirectedEdge() {
        this.source = new Vertex();
        this.destination = new Vertex();
        this.weight = Integer.MAX_VALUE;
    }

    public DirectedEdge(Vertex s, Vertex d) {
        this.source = s;
        this.destination = d;
        this.weight = Integer.MAX_VALUE;
    }

    public DirectedEdge(Vertex source, Vertex destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public void setSource (Vertex s) {
        this.source = s;
    }

    public void setDestination (Vertex d) {
        this.destination = d;
    }

    public void setWeight (int w) {
        this.weight = w;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public boolean isExisted(ArrayList<DirectedEdge> edges) {
        for (DirectedEdge e : edges) {
            if (source == e.getSource() && destination == e.getDestination()) return true;
        }
        return false;
    }
}
