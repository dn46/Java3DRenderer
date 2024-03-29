import java.awt.Color;

public class Triangle {
    
    private Vertex v1;
    private Vertex v2;
    private Vertex v3;
    private Color color;

    public Triangle(Vertex v1, Vertex v2, Vertex v3, Color color) {

        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.color = color;

    }


    public Vertex getV1() {
        return this.v1;
    }

    public void setV1(Vertex v1) {
        this.v1 = v1;
    }

    public Vertex getV2() {
        return this.v2;
    }

    public void setV2(Vertex v2) {
        this.v2 = v2;
    }

    public Vertex getV3() {
        return this.v3;
    }

    public void setV3(Vertex v3) {
        this.v3 = v3;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
