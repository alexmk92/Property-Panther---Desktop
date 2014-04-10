package prcse.pp.view;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import prcse.pp.controller.MessagesController;
import prcse.pp.model.Message;
import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.Payment;
import prcse.pp.model.Tenant;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class MessageCell extends ListCell<String> {
    HBox hbox = new HBox();
    HBox c = new HBox();
    Pane b = new HBox();
    Pane pane = new Pane();
    Label msg = new Label();
    Label date = new Label();
    Label type = new Label();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/mail.png"));
    ImageView img = new ImageView();
    Message  thisMessage;
    int thisIndex;

    String lastItem;


    public MessageCell(int index, MessagesController controller, ArrayList<Message> list) {
        super();

        thisIndex = index;

        if(thisIndex < list.size()) {
            try{
                thisMessage = list.get(index);

                // Format the message style
                msg.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-font-family: 'Open Sans Light'; -fx-label-padding: 5px; -fx-translate-x: 30; -fx-translate-y: 4 !important");

                date.setStyle("-fx-text-fill: #fff !important; -fx-font-family: Helvetica; -fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: center-right; -fx-translate-x: -40");


                // Set the type label
                switch(thisMessage.getType()){
                    case "INBOX":
                        type.getStyleClass().addAll("encapsulated-small", "green");
                        type.setText("Inbox");
                    break;
                    case "MAINTENANCE":
                        type.getStyleClass().addAll("encapsulated-small", "orange");
                        type.setText("Maint");
                    break;
                    case "ALERT":
                        type.getStyleClass().addAll("encapsulated-small", "pink");
                        type.setText("Alert");
                    break;
                    default:
                        type.getStyleClass().addAll("encapsulated-small", "green");
                        type.setText("Inbox");
                    break;
                }

                date.setText(thisMessage.getDateAsString(thisMessage.getDate()));


                // Set the panel alignmentss
                b.setStyle("-fx-translate-y: 8.5; -fx-translate-x: 20");
                c.setStyle("-fx-translate-y: 8.5;");

                // Set img styles
                img.setStyle("-fx-translate-y: 15; -fx-translate-x: 4");
                img.setImage(profile_default);
                img.setFitWidth(18);
                img.setFitHeight(12);

                pane.setTranslateY(20);
                b.getChildren().add(type);
                c.getChildren().add(date);
                hbox.getChildren().addAll(img, b, msg, pane, c);
                HBox.setHgrow(pane, Priority.ALWAYS);
            } catch (Exception e) {
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
            msg.setText(item!=null ? item : "<null>");
            setGraphic(hbox);
        }
    }
}
