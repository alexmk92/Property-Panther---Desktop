package prcse.pp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import prcse.pp.misc.Validate;
import prcse.pp.model.Tenant;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author PRCSE
 */
public class AddUserController implements Initializable, ControlledScreen {

    /******************************************************
     *       FXML VARIABLES - RELATIVE TO DASHBOARD.XML
     ******************************************************/
    @FXML // fx:id="btnUserSearch"
    private Button btnUserSearch;
    @FXML // fx:id="btn_create_user"
    private Button btn_create_user;
    @FXML // fx:id="img_create_user"
    private ImageView img_create_user;
    @FXML // fx:id="img_view_users"
    private ImageView img_view_users;
    @FXML // fx:id="btn_view_user"
    private Button btn_view_user;
    @FXML // fx:id="Draggable"
    private BorderPane draggable;
    @FXML // fx:id="closeBtn"
    private Button closeBtn;
    @FXML // fx:id="maximBtn"
    private Button maximBtn;
    @FXML // fx:id="minimBtn"
    private Button minimBtn;
    @FXML // fx:id="nav1"
    private AnchorPane nav1;
    @FXML // fx:id="nav2"
    private AnchorPane nav2;
    @FXML // fx:id="nav3"
    private AnchorPane nav3;
    @FXML // fx:id="nav4"
    private AnchorPane nav4;
    @FXML // fx:id="nav5"
    private AnchorPane nav5;
    @FXML // fx:id="accent1"
    private Rectangle accent1;
    @FXML // fx:id="accent2"
    private Rectangle accent2;
    @FXML // fx:id="accent3"
    private Rectangle accent3;
    @FXML // fx:id="accent4"
    private Rectangle accent4;
    @FXML // fx:id="accent5"
    private Rectangle accent5;
    @FXML //fx:id="nav_icon1"
    private Pane nav_icon1;
    @FXML //fx:id="nav_bg1"
    private Button nav_bg1;
    @FXML //fx:id="nav_icon2"
    private Pane nav_icon2;
    @FXML //fx:id="nav_bg2"
    private Button nav_bg2;
    @FXML //fx:id="nav_icon3"
    private Pane nav_icon3;
    @FXML //fx:id="nav_bg3"
    private Button nav_bg3;
    @FXML //fx:id="nav_icon4"
    private Pane nav_icon4;
    @FXML //fx:id="nav_bg4"
    private Button nav_bg4;
    @FXML //fx:id="nav_icon5"
    private Pane nav_icon5;
    @FXML //fx:id="nav_bg5"
    private Button nav_bg5;
    @FXML // fx:id="title"
    private Label title;
    @FXML // fx:id="spinner_green"
    private ImageView spinner_green;
    @FXML // fx:id="txtUser_Username"
    private TextField txtUsers_Username;
    @FXML // fx:id="searchBar"
    private Pane searchBar;
    @FXML // fx:id="searchButtons"
    private Pane searchButtons;
    @FXML // fx:id="searchWrap"
    private Pane searchWrap;
    @FXML // fx:id="widget_top_left"
    private Pane widget_top_left;
    @FXML // fx:id="widget_middle"
    private Pane widget_middle;
    @FXML // fx:id="widget_bottom_left"
    private Pane widget_bottom_left;
    @FXML // fx:id="widget_top_right"
    private Pane widget_top_right;
    @FXML // fx:id="widget_bottom_right"
    private Pane widget_bottom_right;
    @FXML // fx:id="body"
    private AnchorPane body;
    @FXML // fx:id="txtEmail"
    private TextField txtEmail;
    @FXML // fx:id="selectTitle"
    private ChoiceBox selectTitle;
    @FXML // fx:id="txtForename"
    private TextField txtForename;
    @FXML // fx:id="txtSurname"
    private TextField txtSurname;
    @FXML // fx:id="txtAddr1"
    private TextField txtAddr1;
    @FXML // fx:id="txtAddr2"
    private TextField txtAddr2;
    @FXML // fx:id="txtCounty"
    private TextField txtCounty;
    @FXML // fx:id="txtPostcode"
    private TextField txtPostcode;
    @FXML // fx:id="txtPhone"
    private TextField txtPhone;
    @FXML // fx:id="txtSearchEmail"
    private TextField txtSearchEmail;
    @FXML // fx:id="txtSearchProp"
    private TextField txtSearchProp;
    @FXML // fx:id="txtSearchName"
    private TextField txtSearchName;
    @FXML // fx:id="btnSearch"
    private Button btnSearch;
    @FXML // fx:id="btnClearAll"
    private Button btnClearAll;
    @FXML // fx:id="btnCreate"
    private Button btnCreate;

    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;
    ScreensController myController;

    // Reference our validation object
    Validate validator = ScreensFramework.validateThis;

    // Editing is initialised to false, if its enabled we hide all labels
    // and show textboxes
    private Boolean editing = false;

    // Array to hold errors
    private ArrayList errors = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        // Set opacity of widgets
        widget_middle.setOpacity(0.3);
        widget_top_left.setOpacity(0.3);
        widget_top_right.setOpacity(0.3);
        widget_bottom_left.setOpacity(0.3);
        widget_bottom_right.setOpacity(0.3);

        // Set titles for the title box
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.addAll("Mr", "Mrs", "Miss", "Ms", "Dr", "Master", "Sir", "Prof");

        selectTitle.setItems(titles);

        // Animate the scene in
        body.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                animateIn();
                resetStyles();
            }
        });

        // Set the display graphic for title
        Effect glow = new Glow(0.3);
        title.setEffect(glow);

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

        nav1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent1.setStyle("visibility: visible");
            }
        });
        nav1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent1.setStyle("visibility: hidden");
            }
        });
        nav4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent4.setStyle("visibility: visible");
            }
        });
        nav4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent4.setStyle("visibility: hidden");
            }
        });
        nav3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: visible");
            }
        });
        nav3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: hidden");
            }
        });
        nav5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: visible");
            }
        });
        nav5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: hidden");
            }
        });

        /******************************************************
         *              MODEL MANIPULATION METHODS
         ******************************************************/
        // Reset the textbox to "Enter a users name..." if the box is empty on focus out.
        txtUsers_Username.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                resetText(txtUsers_Username, newPropertyValue);
            }
        });
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                resetText(txtEmail, newPropertyValue);
            }
        });


        btnUserSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnUserSearch.getStyleClass().add("searching");
                spinner_green.setVisible(true);
            }
        });


        // Utility controls
        closeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });

        searchWrap.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg2.getStyleClass().add("light_hover");
                accent2.setStyle("visibility: visible");
            }
        });
        searchWrap.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg2.getStyleClass().remove("light_hover");
                accent2.setStyle("visibility: hidden");
                hideUsers();
            }
        });

        txtUsers_Username.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                if(!validator.validateName(txtUsers_Username.getText())){
                    txtUsers_Username.getStyleClass().add("invalid");
                } else {
                    txtUsers_Username.getStyleClass().remove("invalid");
                }
            }
        });
        txtForename.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {

                String error = "Please enter a forename: i.e. Alex";
                errors.remove(error);

                txtForename.getStyleClass().remove("invalid");
                if(validator.validateName(txtForename.getText())){
                    errors.remove(error);
                    txtForename.getStyleClass().remove("invalid");
                } else {
                    errors.add(error);
                    txtForename.getStyleClass().add("invalid");
                }
            }
        });
        txtSurname.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {

                String error = "Please enter a surname: i.e. Sims";
                errors.remove(error);

                txtSurname.getStyleClass().remove("invalid");
                if(validator.validateName(txtSurname.getText())){
                    errors.remove(error);
                    txtSurname.getStyleClass().remove("invalid");
                } else {
                    errors.add(error);
                    txtSurname.getStyleClass().add("invalid");
                }
            }
        });
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {

                String error = "Please enter a email: i.e. alexmk92@live.co.uk";
                errors.remove(error);

                txtEmail.getStyleClass().remove("invalid");
                if (validator.validateEmail(txtEmail.getText()) == true) {
                    errors.remove(error);
                    txtEmail.getStyleClass().removeAll("invalid");
                } else {
                    errors.add(error);
                    txtEmail.getStyleClass().add("invalid");
                }
            }
        });
        txtPhone.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                txtPhone.getStyleClass().remove("invalid");

                String error = "Please enter a phone number: i.e. 07969114443";
                errors.remove(error);

                if(validator.validateNumber(txtPhone.getText()) == true) {
                    errors.remove(error);
                    txtPhone.getStyleClass().removeAll("invalid");
                } else {
                    errors.add(error);
                    txtPhone.getStyleClass().add("invalid");
                }
            }
        });
        txtAddr1.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                txtAddr1.getStyleClass().remove("invalid");

                String error = "Please enter an address: i.e. 1 Cedar Way";
                errors.remove(error);

                if(validator.validateAddress(txtAddr1.getText()) == true) {
                    errors.remove(error);
                    txtAddr1.getStyleClass().removeAll("invalid");
                } else {
                    errors.add(error);
                    txtAddr1.getStyleClass().add("invalid");
                }
            }
        });
        txtCounty.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                txtCounty.getStyleClass().remove("invalid");

                String error = "Please enter a county: i.e. Plymouth";
                errors.remove(error);

                if(validator.validateName(txtCounty.getText()) == true) {
                    errors.remove(error);
                    txtCounty.getStyleClass().removeAll("invalid");
                } else {
                    errors.add(error);
                    txtCounty.getStyleClass().add("invalid");
                }
            }
        });
        txtPostcode.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean aBoolean2) {
                txtPostcode.getStyleClass().remove("invalid");

                String error = "Please enter a postcode: i.e. PL49JJ";
                errors.remove(error);

                if(validator.validatePostcode(txtPostcode.getText().toUpperCase()) == true) {
                    errors.remove(error);
                    txtPostcode.getStyleClass().removeAll("invalid");
                } else {
                    errors.add(error);
                    txtPostcode.getStyleClass().add("invalid");
                }

                // Upper the postcode
                txtPostcode.setText(txtPostcode.getText().toUpperCase());
            }
        });
        btnClearAll.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearFields();
            }
        });
        btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(errors.size() == 0 || errors.isEmpty()) {

                    // Can lock GUI up if query takes a while to execute, therefore run on new thread
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String title = selectTitle.getSelectionModel().getSelectedItem().toString();

                                // build the query and execute it using the db object
                                String query = "INSERT INTO users VALUES('', '" + txtEmail.getText() + "', '', '', '" + txtAddr1.getText() + "', '" + txtAddr2.getText() +
                                        "', '" + txtPostcode.getText() + "', '" + txtCounty.getText() + "', '" + title +
                                        "', '" + txtForename.getText() + "', '" + txtSurname.getText() + "', '" + txtPhone.getText() + "', 'USER', '', '', '')";
                                ScreensFramework.db.query(query);
                            } catch (Exception e) {
                                ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
                            }
                        }
                    }).start();

                    // The title of the user
                    String title = selectTitle.getSelectionModel().getSelectedItem().toString();

                    // Add the tenant to the user list
                    Tenant t = new Tenant(0, title, txtForename.getText(), txtSurname.getText(), txtEmail.getText(), txtPhone.getText(), txtAddr1.getText(),
                            txtAddr2.getText(), txtPostcode.getText(), txtCounty.getText(), null, null);

                    // Add this new tenant to the global list
                    ScreensFramework.tenants.addUser(t);

                    // Set the searched users to the updated home list
                    ScreensFramework.searchObj.setSearchedUsers(ScreensFramework.tenants);
                }
                clearFields();
                showSuccess();
            }
        });
    }

    // Clears all fields
    private void clearFields() {
        txtForename.setText("");
        txtSurname.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
        txtCounty.setText("");
        txtAddr2.setText("");
        txtAddr1.setText("");
        selectTitle.setValue(0);
        txtPostcode.setText("");
    }

    // Shows the success window
    private void showSuccess() {

    }

    /******************************************************
     *                ANIMATION CONTROLS
     ******************************************************/
    public void showUsers()
    {
        final Timeline slideOut = new Timeline();
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 339);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(300), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 339);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(300), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 67);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(700), kv3);

        final KeyValue kv4 = new KeyValue(widget_top_right.opacityProperty(), 0.3);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_top_left.opacityProperty(), 0.3);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);
        final KeyValue kv6 = new KeyValue(widget_bottom_left.opacityProperty(), 0.3);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(500), kv6);
        final KeyValue kv7 = new KeyValue(widget_middle.opacityProperty(), 0.3);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(500), kv7);
        final KeyValue kv8 = new KeyValue(widget_bottom_right.opacityProperty(), 0.3);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);

        slideOut.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8);
        slideOut.play();

        //txtUsers_Username.requestFocus();
    }

    public void hideUsers()
    {
        final Timeline slideBack = new Timeline();
        slideBack.setCycleCount(1);
        slideBack.setAutoReverse(false);

        // Animate slider back in
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(300), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(300), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 0);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(700), kv3);

        // Fade widgets back in
        final KeyValue kv4 = new KeyValue(widget_top_right.opacityProperty(), 1);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_top_left.opacityProperty(), 1);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);
        final KeyValue kv6 = new KeyValue(widget_bottom_left.opacityProperty(), 1);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(500), kv6);
        final KeyValue kv7 = new KeyValue(widget_middle.opacityProperty(), 1);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(500), kv7);
        final KeyValue kv8 = new KeyValue(widget_bottom_right.opacityProperty(), 1);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);

        slideBack.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8);
        slideBack.play();

        txtUsers_Username.setText("");
        spinner_green.setVisible(false);
        btnUserSearch.getStyleClass().remove("searching");
    }

    public void slideTitleIn()
    {
        final Timeline slideDown = new Timeline();
        slideDown.setCycleCount(1);
        slideDown.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(title.translateYProperty(), 120);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        slideDown.getKeyFrames().add(kf1);
        slideDown.play();
    }

    public void resetText(TextField txt, Boolean newPropertyValue)
    {
        if (newPropertyValue) {
            System.out.println("Textfield on focus");
        } else {
            System.out.println("Textfield out focus");
            String username = txt.getText();
            if(username.trim().isEmpty())
            {
                txt.setText("");
            }
        }
    }

    public void animateIn()
    {
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(title.layoutYProperty(), 20);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);
        final KeyValue kv1 = new KeyValue(widget_top_right.opacityProperty(), 1);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(widget_top_left.opacityProperty(), 1);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        final KeyValue kv3 = new KeyValue(widget_bottom_left.opacityProperty(), 1);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(500), kv3);
        final KeyValue kv4 = new KeyValue(widget_middle.opacityProperty(), 1);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_bottom_right.opacityProperty(), 1);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);

        // Animate the position in
        final KeyValue kv6 = new KeyValue(widget_top_right.translateXProperty(), -316);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(400), kv6);
        final KeyValue kv7 = new KeyValue(widget_top_left.translateXProperty(), 880);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(500), kv7);
        final KeyValue kv8 = new KeyValue(widget_bottom_left.translateXProperty(), 880);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);
        final KeyValue kv9 = new KeyValue(widget_middle.translateXProperty(), -940);
        final KeyFrame kf9 = new KeyFrame(Duration.millis(250), kv9);
        final KeyValue kv10 = new KeyValue(widget_bottom_right.translateYProperty(), -480);
        final KeyFrame kf10 = new KeyFrame(Duration.millis(500), kv10);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10);
        load_scene.play();
    }

    public void animateOut()
    {
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(title.layoutYProperty(), -100);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);
        final KeyValue kv1 = new KeyValue(widget_top_right.opacityProperty(), 0.3);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(widget_top_left.opacityProperty(), 0.3);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        final KeyValue kv3 = new KeyValue(widget_bottom_left.opacityProperty(), 0.3);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(500), kv3);
        final KeyValue kv4 = new KeyValue(widget_middle.opacityProperty(), 0.3);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_bottom_right.opacityProperty(), 0.3);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);

        // Animate the position in
        final KeyValue kv6 = new KeyValue(widget_top_right.translateXProperty(), 0);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(400), kv6);
        final KeyValue kv7 = new KeyValue(widget_top_left.translateXProperty(), 0);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(500), kv7);
        final KeyValue kv8 = new KeyValue(widget_bottom_left.translateXProperty(), 0);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);
        final KeyValue kv9 = new KeyValue(widget_middle.translateXProperty(), 0);
        final KeyFrame kf9 = new KeyFrame(Duration.millis(200), kv9);
        final KeyValue kv10 = new KeyValue(widget_bottom_right.translateYProperty(), 0);
        final KeyFrame kf10 = new KeyFrame(Duration.millis(500), kv10);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10);
        load_scene.play();
    }

    /**
     * Animates the scene out on a new Thread to allow the animation to play through without being
     * interrupted by the main thread, styles are applied to show the new active button
     */
    private void nextForm(final String ID)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    clearStyles();
                    switch(ID) {
                        case "Dashboard":
                            nav_bg1.getStyleClass().addAll("active");
                            nav_icon1.getStyleClass().add("active");
                            accent1.getStyleClass().addAll("active", "show");
                            break;
                        case "Tenant":
                            nav_bg2.getStyleClass().addAll("active");
                            nav_icon2.getStyleClass().add("active");
                            accent2.getStyleClass().addAll("active", "show");
                            break;
                        case "Properties":
                            nav_bg3.getStyleClass().addAll("active");
                            nav_icon3.getStyleClass().add("active");
                            accent3.getStyleClass().addAll("active", "show");
                            break;
                        case "Payments":
                            nav_bg4.getStyleClass().addAll("active");
                            nav_icon4.getStyleClass().add("active");
                            accent4.getStyleClass().addAll("active", "show");
                            break;
                        case "Messages":
                            nav_bg5.getStyleClass().addAll("active");
                            nav_icon5.getStyleClass().add("active");
                            accent5.getStyleClass().addAll("active", "show");
                            break;
                    }

                    // Animate the scene
                    animateOut();
                    Thread.sleep(300);
                } catch(Exception e )
                {
                    System.out.println("There was an error handling the animation...");
                }
                // Go to our view.
                myController.setScreen(ID);
            }
        }).start();
    }

    /**
     * Clears the styles on the current button
     */
    private void clearStyles()
    {
        // Active state for this window
        nav_icon2.getStyleClass().remove("active");
        nav_bg2.getStyleClass().remove("active");
        accent2.getStyleClass().remove("show");
    }

    /**
     * Reset the navigation styles to make this current window the active one, if we don't call this method
     * then the next time we load this window form the HashMap, the wrong active state shall be applied
     */
    private void resetStyles()
    {
        // Active state for this window
        nav_icon2.getStyleClass().add("active");
        nav_bg2.getStyleClass().add("active");
        accent2.getStyleClass().addAll("active", "show");

        // Default styles for every other nav element
        nav_icon1.getStyleClass().remove("active");
        accent1.getStyleClass().removeAll("active", "show");
        nav_bg1.getStyleClass().remove("active");
        nav_icon4.getStyleClass().remove("active");
        accent4.getStyleClass().removeAll("active", "show");
        nav_bg4.getStyleClass().remove("active");
        nav_icon3.getStyleClass().remove("active");
        accent3.getStyleClass().removeAll("active", "show");
        nav_bg3.getStyleClass().remove("active");
        nav_icon5.getStyleClass().remove("active");
        accent5.getStyleClass().removeAll("active", "show");
        nav_bg5.getStyleClass().remove("active");
    }

    // Set the parent of the new screen
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    // Navigation Control
    @FXML
    private void goToDashboard(ActionEvent event){
        // If the user panel is open then hide it
        //hideUsers();
        nextForm(ScreensFramework.screen1ID);
    }
    @FXML
    private void goToUsers(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen2ID);
    }
    @FXML
    private void goToProperties(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen3ID);
    }
    @FXML
    private void goToPayments(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen4ID);
    }
    @FXML
    private void goToMessages(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen5ID);
    }
    @FXML
    private void goToSettings(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen6ID);
    }
    @FXML
    private void goToAddUser(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen7ID);
    }
    @FXML
    private void goToAllUsers(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen8ID);
    }
}



