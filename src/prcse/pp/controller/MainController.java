package prcse.pp.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;

public class MainController implements Initializable {

    @FXML // fx:id="Draggable"
    private BorderPane draggable;
    private Button closeBtn;
    private Button maximBtn;
    private Button minimBtn;

    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        assert draggable != null : "fx:id=\"draggable\" was not injected: check your FXML file 'MainMenu.fxml";

        // Set the draggable component
        draggable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        draggable.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                draggable.getScene().getWindow().setX(event.getScreenX() - xOffset);
                draggable.getScene().getWindow().setY(event.getScreenY() - yOffset);
            }
        });


    }
}
