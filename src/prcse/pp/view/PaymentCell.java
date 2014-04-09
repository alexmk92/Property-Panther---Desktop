package prcse.pp.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import prcse.pp.controller.PaymentsController;
import prcse.pp.controller.ScreensFramework;
import prcse.pp.model.Payment;
import prcse.pp.model.Tenant;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class PaymentCell extends ListCell<String> {
    HBox hbox = new HBox();
    HBox c = new HBox();
    Pane b = new HBox();
    Pane pane = new Pane();
    Label msg = new Label();
    Label date = new Label();
    Label amount = new Label();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/user_default.png"));
    ImageView img = new ImageView();
    Tenant thisTenant;
    int thisIndex;

    String lastItem;


    public PaymentCell(int index, PaymentsController controller) {
        super();

        if(index < controller.getTenant().numOfPayments()) {
            try{
                thisIndex = index;

                thisTenant = controller.getTenant();
                Payment currPayment = thisTenant.getPaymentAt(index);
                date.setText(currPayment.getDateAsString());
                amount.setText(currPayment.getAmount());

                // Format the message style
                msg.setStyle("-fx-text-fill: #66d9ef; -fx-font-size: 16px; -fx-font-family: 'Open Sans Light'; -fx-label-padding: 5px; -fx-translate-x: 18; -fx-translate-y: 4 !important");
                amount.setStyle("-fx-text-fill: #96ca2e !important; -fx-font-family: Helvetica; -fx-font-size: 16px; -fx-font-weight: bold; -fx-alignment: center-right;");
                date.setStyle("-fx-text-fill: #fff !important; -fx-font-family: Helvetica; -fx-font-size: 16px; -fx-alignment: center-right;");


                // Set the panel alignmentss
                b.setStyle("-fx-translate-y: 8.5; -fx-translate-x: 120");
                c.setStyle("-fx-translate-y: 8.5;");

                // Set img styles
                img.setStyle("-fx-translate-y: 2.5; -fx-translate-x: 2.5");
                img.setImage(profile_default);
                img.setFitWidth(38);
                img.setFitHeight(38);

                pane.setTranslateY(20);
                b.getChildren().add(amount);
                c.getChildren().add(date);
                hbox.getChildren().addAll(img, msg, b, pane, c);
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
