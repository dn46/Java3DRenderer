import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class DemoViewer {
    
    public static void main(String[] args) {

        ArrayList<Triangle> triangles = new ArrayList<>();

        triangles.add(new Triangle(new Vertex(100, 100, 100), new Vertex(-100, -100, 100), new Vertex(-100, 100, -100), Color.WHITE));
        triangles.add(new Triangle(new Vertex(100, 100, 100), new Vertex(-100, -100, 100), new Vertex(100, -100, -100), Color.RED));
        triangles.add(new Triangle(new Vertex(-100, 100, -100), new Vertex(100, -100, -100), new Vertex(100, 100, 100), Color.GREEN));
        triangles.add(new Triangle(new Vertex(-100, 100, -100), new Vertex(100, -100, -100), new Vertex(-100, -100, 100), Color.BLUE));
        
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // slider to control horizontal rotation
        JLabel headingLabel = new JLabel("Horizontal rotation");
        pane.add(headingLabel, BorderLayout.SOUTH);
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // slider to control vertical rotation
        JLabel pitchLabel = new JLabel("Vertical rotation");
        pane.add(pitchLabel, BorderLayout.EAST);
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);

        // slider for the shape size
        JLabel scaleLabel = new JLabel("Scale");
        pane.add(scaleLabel, BorderLayout.WEST);
        JSlider scaleSlider = new JSlider(SwingConstants.VERTICAL, 1, 200, 100);
        pane.add(scaleSlider, BorderLayout.WEST);

        // choosing the object
        JLabel shapeLabel = new JLabel("Shape");
        pane.add(shapeLabel, BorderLayout.NORTH);
        String[] shapes = {"Pyramid", "Cube"};
        JComboBox<String> shapeComboBox = new JComboBox<>(shapes);
        pane.add(shapeComboBox, BorderLayout.NORTH);

        // this is just to store the last mouse position
        int[] lastMousePos = new int[2];

        // panel to display render results
        JPanel renderPanel = new JPanel() {

            public void paintComponent(Graphics g) {

                double scale = scaleSlider.getValue();

                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // the rotation matrix 
                double heading = Math.toRadians(headingSlider.getValue());

                // horizontal slider
                Matrix3 headingTransform = new Matrix3(new double[] {
                    Math.cos(heading), 0, -Math.sin(heading),
                    0, 1, 0,
                    Math.sin(heading), 0, Math.cos(heading) 
                });

                double pitch = Math.toRadians(pitchSlider.getValue());
                
                // vertical slider
                Matrix3 pitchTransform = new Matrix3(new double[] {
                    1, 0, 0,
                    0, Math.cos(pitch), Math.sin(pitch),
                    0, -Math.sin(pitch), Math.cos(pitch)
                });

                Matrix3 transform = headingTransform.multiply(pitchTransform); // multiplying one matrix with the other

                g2.translate(getWidth() / 2, getHeight() / 2);
                g2.setColor(Color.WHITE);

                String shape = (String) shapeComboBox.getSelectedItem();
                ArrayList<Triangle> triangles;
                switch (shape) {
                    case "Pyramid":
                        triangles = Pyramid.create(scale);
                        break;
                    case "Cube":
                        triangles = Cube.create(scale);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid shape: " + shape);
                }

                for (Triangle triangle : triangles) {
                    Vertex v1 = transform.transform(triangle.getV1());
                    Vertex v2 = transform.transform(triangle.getV2());
                    Vertex v3 = transform.transform(triangle.getV3());
                    Path2D path = new Path2D.Double();
                    path.moveTo(v1.getX(), v1.getY());
                    path.lineTo(v2.getX(), v2.getY());
                    path.lineTo(v3.getX(), v3.getY());
                    path.closePath();
                    g2.draw(path);
                }

                

            }

        };

        // add a MouseMotionListener to the renderPanel
        renderPanel.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent e) {
                // calculate the change in mouse position
                int dx = e.getX() - lastMousePos[0];
                int dy = e.getY() - lastMousePos[1];
        
                // update the sliders based on the change in mouse position
                headingSlider.setValue(headingSlider.getValue() + dx);
                pitchSlider.setValue(pitchSlider.getValue() + dy);
        
                // store the current mouse position
                lastMousePos[0] = e.getX();
                lastMousePos[1] = e.getY();
            }
        });

        // changing the position of the shape by dragging with the mouse
        renderPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent e) {
                // store the current mouse position when the mouse is pressed
                lastMousePos[0] = e.getX();
                lastMousePos[1] = e.getY();
            }
        });

        // changing the shape with the mouse wheel
        renderPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent e) {
                // get the rotation of the wheel
                int rotation = e.getWheelRotation();

                // to increase the rate of change on the shape
                int factor = 10;
        
                // update the scaleSlider based on the rotation
                scaleSlider.setValue(scaleSlider.getValue() - rotation * factor);
            }
        });

        // these two listeners force redraw when we drag the sliders
        headingSlider.addChangeListener(e -> renderPanel.repaint());
        pitchSlider.addChangeListener(e -> renderPanel.repaint());
        scaleSlider.addChangeListener(e -> renderPanel.repaint());
        shapeComboBox.addActionListener(e -> renderPanel.repaint());

        pane.add(renderPanel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
