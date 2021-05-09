package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {

    private static Shape[] arr = new Shape[] {
            new Square(10, 10, 50),
            new Rectangle(50, 20, 50, 80),
            new Parallelogram(10, 10, 40, 20, 45f),
            new Circle(20, 20, 40),
            new Ellipse(40 , 40, 20, 10),
    };

    HashMap<String, Shape> map = new HashMap<>();

    @FXML
    private MenuButton shapes;

    @FXML
    private MenuItem squareItem;

    @FXML
    private MenuItem rectItem;

    @FXML
    private MenuItem parallelogramItem;

    @FXML
    private MenuItem lineItem;

    @FXML
    private MenuItem circleItem;

    @FXML
    private MenuItem ellipseItem;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private Canvas canvas;

    private Point startPoint;

    @FXML
    void initialize() {
        assert shapes != null : "fx:id=\"shapes\" was not injected: check your FXML file 'sample.fxml'.";
        assert squareItem != null : "fx:id=\"squareItem\" was not injected: check your FXML file 'sample.fxml'.";
        assert rectItem != null : "fx:id=\"rectItem\" was not injected: check your FXML file 'sample.fxml'.";
        assert parallelogramItem != null : "fx:id=\"parallelogramItem\" was not injected: check your FXML file 'sample.fxml'.";
        assert lineItem != null : "fx:id=\"lineItem\" was not injected: check your FXML file 'sample.fxml'.";
        assert circleItem != null : "fx:id=\"circleItem\" was not injected: check your FXML file 'sample.fxml'.";
        assert ellipseItem != null : "fx:id=\"ellipseItem\" was not injected: check your FXML file 'sample.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'sample.fxml'.";

        map.put("Square", new Square());
        map.put("Rectangle", new Rectangle());
        map.put("Parallelogram", new Parallelogram());
        map.put("Circle", new Circle());
        map.put("Ellipse", new Ellipse());
        map.put("Line", new Line());

        drawStaticShapes();
    }


    @FXML
    void onSelectionItem(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        shapes.setText(item.getText());
    }

    @FXML
    void onClear() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    @FXML
    void onMousePressed(MouseEvent event) {
        startPoint = new Point(event.getX(), event.getY());
    }

    void drawStaticShapes() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        gc.setLineWidth(4.0);
        gc.setStroke(Color.BLACK);

        for(int i = 0; i < arr.length; i++) {
            arr[i].draw(gc);
        }
    }

    @FXML
    void onMouseReleased(MouseEvent event) {
        Point endPoint = new Point(event.getX(), event.getY());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(colorPicker.getValue());
        gc.setLineWidth(4.0);
        gc.setStroke(colorPicker.getValue());
        Shape shape = map.get(shapes.getText());
        shape.settings(startPoint, endPoint);
        shape.draw(canvas.getGraphicsContext2D());
    }

}
