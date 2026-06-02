package hust.soict.vp.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {
    @FXML
    private Pane drawingPane;
    @FXML
    private ToggleButton btnPen;
    @FXML
    private ToggleButton btnEraser;

    private Color color = Color.BLACK;
    private double radius = 4.0;

    @FXML
    private void initialize() {
        btnPen.setSelected(true);
    }

    @FXML
    private void clear() {
        drawingPane.getChildren().clear();
    }

    @FXML
    private void selectPen() {
        color = Color.BLACK;
        radius = 4.0;
        btnPen.setSelected(true);
        btnEraser.setSelected(false);
    }

    @FXML
    private void selectEraser() {
        color = Color.WHITE;
        radius = 10.0;
        btnPen.setSelected(false);
        btnEraser.setSelected(true);
    }

    @FXML
    private void draw(MouseEvent event) {
        Circle circle = new Circle(event.getX(), event.getY(), radius, color);
        drawingPane.getChildren().add(circle);
    }
}
