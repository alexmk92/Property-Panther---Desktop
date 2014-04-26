package prcse.pp.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import prcse.pp.controller.*;
import prcse.pp.model.Tenant;
import prcse.pp.model.UserList;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class UserCellSmall extends ListCell<String> {

    Searcher s = ScreensFramework.searchObj;
    ScreensController myController;
    HBox hbox = new HBox();
    VBox desc = new VBox();
    Label label = new Label();
    Label address = new Label();
    Pane pane = new Pane();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/user_default.png"));
    ImageView img = new ImageView();
    String lastItem;


    /**
     * Constructor providing the styles and elements which the view
     * contains
     */
    public UserCellSmall(int index, PropertyController givenController, Tenant t) {
        super();

        if(index >= 0) {
            try {
                address.getStyleClass().add("address_text");

                Glow g = new Glow(0.1);
                label.setEffect(g);
                label.setStyle("-fx-text-fill: #66d8f0; -fx-translate-y: 10; -fx-font-size: 20px");
                img.setStyle("-fx-translate-y: 10; -fx-translate-x: 15");
                img.setImage(profile_default);
                img.setFitWidth(50);
                img.setFitHeight(50);

                desc.setStyle("-fx-translate-x: 35px;");
                desc.getChildren().addAll(label, address);
                hbox.getChildren().addAll(img, desc, pane);

                HBox.setHgrow(pane, Priority.ALWAYS);
            } catch (NullPointerException e) {
                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
            } catch(Exception e) {
                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
            }
        }
    }


    /**
     * Render the view specified by the constructor
     * @param item the String we pass to render the cell
     * @param empty
     */
    @Override
    protected void updateItem(String item, boolean empty) {

        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;

            // Check whether the string is populated
            label.setText(item!=null ? item : "<null>");

            // Render the view
            setGraphic(hbox);
        }
    }


}
