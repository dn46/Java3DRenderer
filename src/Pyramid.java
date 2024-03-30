import java.awt.Color;
import java.util.ArrayList;

public class Pyramid {
    
    public static ArrayList<Triangle> create(double scale) {

        ArrayList<Triangle> triangles = new ArrayList<>();
    
        // Base
        triangles.add(new Triangle(new Vertex(-1, -1, -1), new Vertex(1, -1, -1), new Vertex(1, -1, 1), Color.RED));
        triangles.add(new Triangle(new Vertex(-1, -1, -1), new Vertex(-1, -1, 1), new Vertex(1, -1, 1), Color.RED));
    
        // Sides
        triangles.add(new Triangle(new Vertex(-1, -1, -1), new Vertex(1, -1, -1), new Vertex(0, 1, 0), Color.GREEN));
        triangles.add(new Triangle(new Vertex(1, -1, -1), new Vertex(1, -1, 1), new Vertex(0, 1, 0), Color.BLUE));
        triangles.add(new Triangle(new Vertex(1, -1, 1), new Vertex(-1, -1, 1), new Vertex(0, 1, 0), Color.YELLOW));
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(-1, -1, -1), new Vertex(0, 1, 0), Color.MAGENTA));
    
        // Scale the vertices of the triangles
        for (Triangle triangle : triangles) {
            triangle.setV1(triangle.getV1().scale(scale));
            triangle.setV2(triangle.getV2().scale(scale));
            triangle.setV3(triangle.getV3().scale(scale));
        }
    
        return triangles;

    }

}
