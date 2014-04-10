package prcse.pp.view;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.scene.text.Text;
import prcse.pp.controller.ScreensFramework;
import prcse.pp.controller.UserInfoController;
import prcse.pp.model.Note;
import prcse.pp.model.Tenant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
    Label msg = new Label();
    Label date = new Label();
    Button cross = new Button();
    Tenant thisTenant;
    int thisIndex;

    String lastItem;


    public NoteCell(int index, UserInfoController controller) {
        super();

        try{
            thisTenant = controller.getTenant();
            Note currNote = thisTenant.getNoteAt(index);
            date = new Label(currNote.getDateAsString(currNote.getDate()));

            thisIndex = index;

            date.setStyle("-fx-text-fill: #fff !important; -fx-font-family: Helvetica; -fx-font-weight: bold; -fx-font-size: 12px; -fx-background-color: #f9246b; -fx-border-color: #FE246C; -fx-padding: 8 8 6 8; -fx-border-radius: 6; -fx-background-radius: 6");
            b.setStyle("-fx-padding: 10px 5px 10px 5px");
            msg.setStyle("-fx-text-fill: #fafafa; -fx-font-size: 14px; -fx-font-family: 'Open Sans Light'; -fx-label-padding: 17px");
            cross.getStyleClass().add("button_close");
            cross.setTranslateY(20);
            msg.setAlignment(Pos.CENTER_LEFT);

            pane.setTranslateY(20);
            b.getChildren().add(date);
            c.getChildren().add(cross);
            hbox.getChildren().addAll(b, msg, pane, cross);
            HBox.setHgrow(pane, Priority.ALWAYS);

            // Deletes the note
            cross.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ScreensFramework.logGeneral.writeToFile("I am removing from index " + String.valueOf(thisIndex));
                    if(thisTenant.removeNoteAt(thisIndex, true)){
                        controller.populateListView();
                    } else {
                        controller.populateListView();
                    }
                }
            });

        } catch (Exception e) {
            ScreensFramework.logError.writeToFile(e.getMessage());
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
