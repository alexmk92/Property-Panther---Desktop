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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import prcse.pp.model.Tenant;

/**
 * Created with IntelliJ IDEA.
 * Tenant: Alex
 * Date: 13/02/14
 * Time: 12:48
 * To change this template use File | Settings | File Templates.
 */
public class UserCell extends ListCell<String> {
    HBox hbox = new HBox();
    VBox desc = new VBox();
    Label label = new Label();
    Label address = new Label();
    Pane pane = new Pane();
    Image profile_default = new Image(this.getClass().getResourceAsStream("../view/img/user_default.png"));
    ImageView img = new ImageView();
    String lastItem;
    Button viewUser = new Button("View User");
    Button editUser = new Button("Edit User");


    /**
     * Constructor providing the styles and elements which the view
     * contains
     */
    public UserCell() {
        super();

        address.getStyleClass().add("address_text");

        viewUser.getStyleClass().addAll("btn_actions", "blue", "center-vert", "pull_left", "btn_narrow");
        editUser.getStyleClass().addAll("btn_actions", "blue", "center-vert", "pull_left", "margin_right", "btn_narrow");

        viewUser.setPrefWidth(50);

        Glow g = new Glow(0.1);
        label.setEffect(g);
        label.setStyle("-fx-text-fill: #66d8f0; -fx-translate-y: 10; -fx-font-size: 20px");
        img.setStyle("-fx-translate-y: 10; -fx-translate-x: 15");
        img.setImage(profile_default);
        img.setFitWidth(50);
        img.setFitHeight(50);

        desc.setStyle("-fx-translate-x: 35px;");
        desc.getChildren().addAll(label, address);
        hbox.getChildren().addAll(img, desc, pane, editUser, viewUser);

        HBox.setHgrow(pane, Priority.ALWAYS);
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

            // Split the item into 2 seperate strings and load them into new labels
            // these will then have their styles assigned to them to render the view
            String[] newString;
            String userName;
            String userAddress;

            newString = item.split("\n");

            userName = newString[0];
            userAddress = newString[1];

            // Check whether the string is populated
            label.setText(userName!=null ? userName : "<null>");
            address.setText(userAddress!=null ? userAddress : "<null>");

            // Render the view
            setGraphic(hbox);
        }
    }
}
