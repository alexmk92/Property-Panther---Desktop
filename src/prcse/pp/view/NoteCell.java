package prcse.pp.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class NoteCell extends ListCell<String> {
    HBox hbox = new HBox();
    HBox c = new HBox();
    Pane b = new HBox();
    Pane pane = new Pane();
    Label label = new Label();

    String lastItem;


    public NoteCell(String forename, String surname) {
        super();

        Label date = new Label("24 Mar 2014");
        date.setStyle("-fx-text-fill: #fff !important; -fx-font-family: Helvetica; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-color: #f9246b; -fx-border-color: #FE246C; -fx-padding: 8 8 6 8; -fx-border-radius: 6; -fx-background-radius: 6");
        b.setStyle("-fx-padding: 10px 5px 10px 5px");
        label.setStyle("-fx-text-fill: #fafafa; -fx-font-size: 14px; -fx-font-family: 'Open Sans Light'; -fx-label-padding: 10px");
        Button cross = new Button();
        cross.getStyleClass().add("button_close");
        cross.setTranslateY(20);
        label.setAlignment(Pos.CENTER_LEFT);

        pane.setTranslateY(20);
        b.getChildren().add(date);
        c.getChildren().add(cross);
        hbox.getChildren().addAll(b, label, pane, cross);
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
