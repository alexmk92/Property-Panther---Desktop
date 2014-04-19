package prcse.pp.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import prcse.pp.controller.PropertyController;
import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.Property;
import prcse.pp.model.Room;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class RoomCell extends ListCell<String> {
    HBox hbox = new HBox();
    HBox c = new HBox();
    Pane b = new HBox();
    Pane pane = new Pane();
    Label msg = new Label();
    Label date = new Label();
    Label details = new Label();
    Label status = new Label();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/arrow_right.png"));
    ImageView img = new ImageView();
    Room thisRoom;
    int thisIndex;

    String lastItem;


    public RoomCell(int index, PropertyController controller, Property p) {
        super();

        if(index < p.numRooms()) {
            try{
                thisIndex = index;

                thisRoom = p.getRoomAt(index);


                String detailsText = thisRoom.getDetails();

                if(detailsText.length() > 30) {
                    detailsText = detailsText.substring(0, 30) + "...";
                }

                status.setText(thisRoom.getStatusAsString(thisRoom.getStatus()));
                details.setText(detailsText);

                // Format the message style
                msg.setStyle("-fx-text-fill: #fff; -fx-font-size: 16px; -fx-font-family: 'Open Sans Light'; -fx-label-padding: 5px; -fx-translate-x: 18; -fx-translate-y: 4 !important");
                details.setStyle("-fx-text-fill: #fff !important; -fx-font-family: Helvetica; -fx-font-size: 14px; -fx-alignment: center-right;");

                if(status.getText().toUpperCase().equals("OCCUPIED")) {
                    status.getStyleClass().addAll("encapsulated-small-wide", "orange");
                } else {
                    status.getStyleClass().addAll("encapsulated-small-wide", "green");
                }



                // Set the panel alignmentss
                b.setStyle("-fx-translate-y: 6.5; -fx-translate-x: 35");
                c.setStyle("-fx-translate-y: 8.5; -fx-translate-x: 45");

                // Set img styles
                img.setStyle("-fx-translate-y: 12.5; -fx-translate-x: 4");
                img.setImage(profile_default);
                img.setFitWidth(16);
                img.setFitHeight(16);

                pane.setTranslateY(20);
                b.getChildren().add(status);
                c.getChildren().add(details);
                hbox.getChildren().addAll(img, msg, b, c);
                HBox.setHgrow(pane, Priority.ALWAYS);



            } catch (Exception e) {
                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
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
            msg.setText(item!=null ? item : "<null>");
            setGraphic(hbox);
        }
    }
}
