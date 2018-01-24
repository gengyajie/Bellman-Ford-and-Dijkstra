public class Vertex {
    private String id;

    public Vertex() {
        this.id = null;
    }

    public Vertex (String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void pushID(String id ){
        this.id = id;
    }
}
