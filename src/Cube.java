import java.awt.Color;
import java.util.ArrayList;

public class Cube {

    public static ArrayList<Triangle> create(double scale) {

        ArrayList<Triangle> triangles = new ArrayList<>();
    
        // Front face
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(1, -1, 1), new Vertex(1, 1, 1), Color.RED));
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(-1, 1, 1), new Vertex(1, 1, 1), Color.RED));
    
        // Back face
        triangles.add(new Triangle(new Vertex(-1, -1, -1), new Vertex(1, -1, -1), new Vertex(1, 1, -1), Color.GREEN));
        triangles.add(new Triangle(new Vertex(-1, -1, -1), new Vertex(-1, 1, -1), new Vertex(1, 1, -1), Color.GREEN));
    
        // Left face
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(-1, -1, -1), new Vertex(-1, 1, -1), Color.BLUE));
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(-1, 1, 1), new Vertex(-1, 1, -1), Color.BLUE));
    
        // Right face
        triangles.add(new Triangle(new Vertex(1, -1, 1), new Vertex(1, -1, -1), new Vertex(1, 1, -1), Color.YELLOW));
        triangles.add(new Triangle(new Vertex(1, -1, 1), new Vertex(1, 1, 1), new Vertex(1, 1, -1), Color.YELLOW));
    
        // Top face
        triangles.add(new Triangle(new Vertex(-1, 1, 1), new Vertex(1, 1, 1), new Vertex(1, 1, -1), Color.MAGENTA));
        triangles.add(new Triangle(new Vertex(-1, 1, 1), new Vertex(-1, 1, -1), new Vertex(1, 1, -1), Color.MAGENTA));
    
        // Bottom face
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(1, -1, 1), new Vertex(1, -1, -1), Color.CYAN));
        triangles.add(new Triangle(new Vertex(-1, -1, 1), new Vertex(-1, -1, -1), new Vertex(1, -1, -1), Color.CYAN));
    
        // Scale the vertices of the triangles
        for (Triangle triangle : triangles) {
            triangle.setV1(triangle.getV1().scale(scale));
            triangle.setV2(triangle.getV2().scale(scale));
            triangle.setV3(triangle.getV3().scale(scale));
        }
    
        return triangles;

    }
    
}
