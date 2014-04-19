package prcse.pp.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import prcse.pp.controller.PaymentsController;
import prcse.pp.controller.PropertyController;
import prcse.pp.controller.ScreensFramework;
import prcse.pp.misc.Writer;
import prcse.pp.model.Property;

import java.io.PrintStream;


/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class PropertyCell extends ListCell<String> {
    HBox hbox = new HBox();
    HBox desc = new HBox();
    Label label = new Label();
    Pane pane = new Pane();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/property_large.png"));
    ImageView img = new ImageView();
    String lastItem;
    Button showRooms    = new Button("Show Rooms");
    Button editProperty = new Button("Edit Details");


    public PropertyCell(int index, PropertyController controller, Property p) {
        super();

        if(index >= 0){
            try {
                Property thisProperty = p;

                if(thisProperty.numRooms() > 0) {
                    String propDetails = Integer.valueOf(thisProperty.numRooms()) + " bedroom property";
                    label.setText(propDetails);
                } else {
                    label.setText("Property with no available rooms");
                }

                label.setStyle("-fx-font-size: 12px; -fx-text-fill: #ffffff; -fx-translate-y: 20");

                showRooms.getStyleClass().addAll("btn_actions", "blue", "center-vert", "pull_left", "btn_narrow");
                editProperty.getStyleClass().addAll("btn_actions", "blue", "center-vert", "pull_left", "margin_right", "btn_narrow");

                showRooms.setPrefWidth(50);

                Glow g = new Glow(0.1);
                label.setEffect(g);
                label.setStyle("-fx-text-fill: #66d8f0; -fx-translate-y: 10; -fx-font-size: 20px");
                img.setStyle("-fx-translate-y: 10; -fx-translate-x: 15");
                img.setImage(profile_default);
                img.setFitWidth(35);
                img.setFitHeight(35);

                desc.setStyle("-fx-translate-x: 35px;");
                desc.getChildren().addAll(label);
                hbox.getChildren().addAll(img, desc, pane, showRooms, editProperty);

                HBox.setHgrow(pane, Priority.ALWAYS);

                // Event handlers
                showRooms.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        try{
                            controller.showRooms(thisProperty, false);
                        } catch(Exception e){
                            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
                        }
                    }
                });
            } catch(NullPointerException e){
                ScreensFramework.logError.writeToFile(e.getMessage());
            }
        }
    }



    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            label.setText(item!=null ? item : "<null>");
            setGraphic(hbox);
        }
    }
}
