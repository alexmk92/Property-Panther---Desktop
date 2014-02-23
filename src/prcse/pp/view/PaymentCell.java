package prcse.pp.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class PaymentCell extends ListCell<String> {
    HBox hbox = new HBox();
    HBox desc = new HBox();
    Label label = new Label();
    Pane pane = new Pane();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/profile_default.png"));
    ImageView img = new ImageView();
    String lastItem;


    public PaymentCell() {
        super();

        Text total = new Text("350");
        Label paid = new Label("£" + total.getText());
        paid.setStyle("-fx-text-fill: #a6e22e !important; -fx-font-family: 'Open Sans Light'; -fx-font-size: 15px");
        paid.setLayoutX(-200);
        Label datePaid = new Label(" 3 days ago");
        datePaid.setStyle("-fx-text-fill: #fff !important; -fx-font-family: 'Open Sans Light'; -fx-font-size: 15px");
        Label amount = new Label("paid ");
        amount.setStyle("-fx-text-fill: #fff !important; -fx-font-family: 'Open Sans Light'; -fx-font-size: 15px");

        Glow g = new Glow(0.1);
        label.setEffect(g);
        label.setStyle("-fx-text-fill: #71cee5; -fx-translate-x: 10; -fx-translate-y: 3");
        img.setStyle("-fx-translate-y: 7; -fx-translate-x: -2");
        img.setImage(profile_default);
        desc.getChildren().addAll(amount, paid, datePaid);
        desc.setStyle("-fx-translate-x: -110; -fx-translate-y: 25");
        hbox.getChildren().addAll(img, label, pane, desc);
        HBox.setHgrow(pane, Priority.ALWAYS);
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
