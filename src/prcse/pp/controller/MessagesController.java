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
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;
import prcse.pp.model.*;
import prcse.pp.view.MessageCell;
import prcse.pp.view.PaymentCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Alex
 * Date: 09/04/14
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class MessagesController implements Initializable, ControlledScreen {
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
    @FXML // fx:id="nav6"
    private AnchorPane nav6;
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
    @FXML // fx:id="accent6"
    private Rectangle accent6;
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
    @FXML //fx:id="nav_icon6"
    private Pane nav_icon6;
    @FXML //fx:id="nav_bg6"
    private Button nav_bg6;
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
    @FXML // fx:id="body"
    private AnchorPane body;
    @FXML // fx:id="widget_bottom_right"
    private Pane widget_bottom_right;
    @FXML // fx:id="widget_top_right"
    private Pane widget_top_right;
    @FXML // fx:id="lstMessages"
    private ListView lstMessages;
    @FXML // fx:id="lblNew"
    private Label lblNew;
    @FXML // fx:id="txtMessage"
    private TextArea txtMessage;
    @FXML // fx:id="txtRecipient"
    private TextField txtRecipient;
    @FXML // fx:id="btnSend"
    private Button btnSend;
    @FXML // fx:id="btnCloseDialog"
    private Button btnCloseDialog;
    @FXML // fx:id="chatOverlay"
    private Pane chatOverlay;
    @FXML // fx:id="btnSearchFilter"
    private Button btnSearchFilter;
    @FXML // fx:id="txtSearchEmail"
    private TextField txtSearchEmail;
    @FXML // fx:id="txtSearchSender"
    private TextField txtSearchSender;
    @FXML // fx:id="selectType"
    private ChoiceBox selectType;
    @FXML // fx:id="btnNew"
    private Button btnNew;
    @FXML // fx:id="btnSent"
    private Button btnSent;
    @FXML // fx:id="btnViewInbox"
    private Button btnViewInbox;
    @FXML // fx:id="lblMessageCount"
    private Label lblMessageCount;
    @FXML // fx:id="messageWrap"
    private Pane messageWrap;
    @FXML // fx:id="lblToInbox"
    private Label lblToInbox;


    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;

    // Reference to user list
    private UserList u = ScreensFramework.tenants;

    private boolean usersHidden = true;
    ScreensController myController;

    // Reference to this controller for the cell factory
    private MessagesController controller = this;
    private Admin sessionUser;

    // Toggles if we are currently using the Inbox or Sentbox (True = inbox, False = sentbox)
    private Boolean usingInbox = true;

    // Checks if we have loaded the page
    private Boolean pageLoaded = false;

    // Index to bind custom cell controls to the listview cell
    private int index = 0;
    private int unread = 0;

    // Allows us to load the next 25 results on clicking "load more"
    private int amount = 20;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        // Add options to combo box
        ObservableList<String> selectItems = FXCollections.observableArrayList();
        selectItems.addAll("All", "Alert", "Inbox", "Maintenance");
        selectType.setItems(selectItems);
        lblToInbox.setVisible(false);

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
        body.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(pageLoaded == false){
                    animateIn();
                    populateMessageList();
                    resetStyles();
                }
                pageLoaded = true;
            }
        });

        // Hide the overlay and message window
        hideMessages();

        /******************************************************
         *                NAVIGATION CONTROLS
         ******************************************************/
        nav_icon1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg1.getStyleClass().add("dark_hover");
            }
        });
        nav_icon1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg1.getStyleClass().remove("dark_hover");
            }
        });
        nav2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent2.setStyle("visibility: visible !important");
            }
        });
        nav2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(usersHidden == true)
                    accent2.setStyle("visibility: hidden !important");
            }
        });
        nav_icon2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent2.setStyle("visibility: visible !important");
                nav_bg2.getStyleClass().add("light_hover");
            }
        });
        nav_icon2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg2.getStyleClass().remove("light_hover");
            }
        });
        nav3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: visible !important");
            }
        });
        nav3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: hidden !important");
            }
        });
        nav_icon3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: visible !important");
                nav_bg3.getStyleClass().add("dark_hover");
            }
        });
        nav_icon3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg3.getStyleClass().remove("dark_hover");
            }
        });
        nav4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent4.setStyle("visibility: visible !important");
            }
        });
        nav4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent4.setStyle("visibility: hidden !important");
            }
        });
        nav_icon4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent4.setStyle("visibility: visible !important");
                nav_bg4.getStyleClass().add("light_hover");
            }
        });
        nav_icon4.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg4.getStyleClass().remove("light_hover");
            }
        });
        nav5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: visible !important");
            }
        });
        nav5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: hidden !important");
            }
        });
        nav_icon5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: visible !important");
                nav_bg5.getStyleClass().add("dark_hover");
            }
        });
        nav_icon5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg5.getStyleClass().remove("dark_hover");
            }
        });
        nav6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent6.setStyle("visibility: visible !important");
            }
        });
        nav6.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent6.setStyle("visibility: hidden");
            }
        });
        nav_icon6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent6.setStyle("visibility: visible !important");
                nav_bg6.getStyleClass().add("light_hover");
            }
        });
        nav_icon6.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg6.getStyleClass().remove("light_hover");
            }
        });

        /******************************************************
         *                 USER SLIDEOUT PANEL
         ******************************************************/
        btn_create_user.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image add_user_black = new Image(getClass().getResourceAsStream("../view/img/add_user_black.png"));
                img_create_user.setImage(add_user_black);
            }
        });
        btn_create_user.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image add_user_white = new Image(getClass().getResourceAsStream("../view/img/add_user_white.png"));
                img_create_user.setImage(add_user_white);
            }
        });
        img_create_user.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn_create_user.getStyleClass().add("searching");
                Image add_user_black = new Image(getClass().getResourceAsStream("../view/img/add_user_black.png"));
                img_create_user.setImage(add_user_black);
            }
        });
        img_create_user.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn_create_user.getStyleClass().remove("searching");
                Image add_user_white = new Image(getClass().getResourceAsStream("../view/img/add_user_white.png"));
                img_create_user.setImage(add_user_white);
            }
        });

        btn_view_user.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image view_user_black = new Image(getClass().getResourceAsStream("../view/img/all_users_black.png"));
                img_view_users.setImage(view_user_black);
            }
        });
        btn_view_user.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image view_user_white = new Image(getClass().getResourceAsStream("../view/img/all_users_white.png"));
                img_view_users.setImage(view_user_white);
            }
        });
        img_view_users.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn_view_user.getStyleClass().add("searching");
                Image add_user_black = new Image(getClass().getResourceAsStream("../view/img/all_users_black.png"));
                img_view_users.setImage(add_user_black);
            }
        });
        img_view_users.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn_view_user.getStyleClass().remove("searching");
                Image add_user_white = new Image(getClass().getResourceAsStream("../view/img/all_users_white.png"));
                img_view_users.setImage(add_user_white);
            }
        });

        /******************************************************
         *                  UTILITY CONTROLS
         ******************************************************/
        closeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });

        body.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(usersHidden == false)
                    hideUsers();
            }
        });
        nav_bg2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(usersHidden == false)
                    hideUsers();
                else
                    showUsers();
            }
        });



        /******************************************************
         *              MODEL MANIPULATION METHODS
         ******************************************************/
        // Reset the textbox to "Enter a users name..." if the box is empty on focus out.
        txtUsers_Username.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                if (newPropertyValue) {
                    System.out.println("Textfield on focus");
                } else {
                    System.out.println("Textfield out focus");
                    String username = txtUsers_Username.getText();
                    if(username.trim().isEmpty())
                    {
                        txtUsers_Username.setText("");
                    }
                }
            }
        });

        btnCloseDialog.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hideMessages();
            }
        });
        btnNew.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showNewMessage();
                txtRecipient.requestFocus();
            }
        });
        btnSent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                usingInbox = false;
                sessionUser.buildSent(amount);
                populateMessageList();
            }
        });
        btnViewInbox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                usingInbox = true;
                populateMessageList();
            }
        });
        /**
         * Performs a User search when the Enter key is pressed
         */
        txtUsers_Username.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    goToUsers(new ActionEvent());
                }
            }
        });

        txtUsers_Username.setOnKeyTyped(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent keyEvent) {

            }
        });
        txtMessage.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int chars = txtMessage.getText().length();
                String message = txtMessage.getText();

                if (chars < 251) {
                    String amount = String.valueOf(chars);
                    lblMessageCount.setText(amount + "/250 Characters");
                    lblMessageCount.setStyle("-fx-text-fill: #ffffff");
                } else {
                    // Updat the message to the old state
                    String fullMsg = message.substring(0, 250);
                    System.out.println(fullMsg.length());
                    txtMessage.setText(fullMsg);

                    // Set the position to the end of the string
                    txtMessage.positionCaret(250);
                    lblMessageCount.setText("250/250 Characters");
                    lblMessageCount.setStyle("-fx-text-fill: #f92772");
                }
            }
        });
        txtRecipient.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                // Global variables
                String save = "";

                // Set that we have an inbox recipient
                if(!lblToInbox.isVisible()){
                    lblToInbox.setVisible(true);
                }


                if(!save.isEmpty()) {
                    txtRecipient.setText(save);
                }

                String value  = txtRecipient.getText();
                int    length = value.length();
                String result;

                // prototype the auto complete here then build method
                result = autoComplete(value, length, "EMAIL");

                if(result.isEmpty() || result.length() < 1) {
                    result = autoComplete(value, length, "NAME");
                }

                save  = value.substring(0, length);
                txtRecipient.setText(value + result);
                txtRecipient.positionCaret(save.length());
            }
        });
        btnSend.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Attempting send...");
                if(sendToUser(txtRecipient.getText())) {
                    System.out.println("Sent");
                }
            }
        });
        selectType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object o2) {

                // Get the selected index
                int selected = selectType.getSelectionModel().getSelectedIndex();

                // Get the sessionUser
                sessionUser = ScreensFramework.loggedIn;

                switch(selected) {
                    case 0:
                        sessionUser.buildInbox(amount, "ALL");
                        populateMessageList();
                        break;
                    case 1:
                        sessionUser.buildInbox(amount, "ALERT");
                        populateMessageList();
                        break;
                    case 2:
                        sessionUser.buildInbox(amount, "INBOX");
                        populateMessageList();
                        break;
                    case 3:
                        sessionUser.buildInbox(amount, "MAINTENANCE");
                        populateMessageList();
                    break;
                }
            }
        });
    }


    /******************************************************
     *                  CONTROL METHODS
     *****************************************************/
    /**
     * Auto completes the text field by searching through an array of
     * available values
     * @param value - the current text in the textbox
     * @param length- the length of the value string
     * @param type  - the type we are searching on : EMAIL, NAME
     */
    public String autoComplete(String value, int length, String type) {

        // Initialise to the current value
        String append    = "";
        String searchBy  = "";

        try {
            // Loop through each user in the array
            for(int i = 0; i < u.size(); i++) {

                // Get the tenants details
                Tenant t = u.getUserAt(i);

                // Find what we are searching by
                switch(type) {
                    case "EMAIL":
                        searchBy = t.getEmail();
                        break;
                    case "NAME":
                        searchBy = t.getName();
                        break;
                }

                // Store variables as comparison
                String compareA = searchBy.toLowerCase();
                String compareB = value.toLowerCase();

                // Is the value string contained in the searchBy string - compare as lowercase
                for(int j = 0; j < length; j++){
                    if(compareA.substring(0, length).contains(compareB)) {
                        append = compareA.substring(length+1, compareA.length());
                        return append;
                    }
                }
            }
        } catch(Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }

        return append;
    }


    /**
     * sends the mail to the user
     * @param email - the email address we wish to send the mail to
     * @param
     */
    public Boolean sendToUser(String email) {
        Boolean sent = false;

        // Check we have the correct user
        for(int i = 0; i < u.size(); i++) {
            Tenant t = u.getUserAt(i);
            if(t.getEmail().equals(email)){
                // Build the query object
                String query = "INSERT INTO messages VALUES('', " + t.getUserId() + ", " + sessionUser.getId() + ", 'INBOX', '" + txtMessage.getText() + "', '', '', '')";

                // Send the message
                ScreensFramework.db.query(query);
                sent = true;
            }
        }

        return sent;
    }


    /**
     * Populates the payment ListView
     */
    public void populateMessageList() {

        // Get the sessionUser
        sessionUser = ScreensFramework.loggedIn;

        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;

        // Check which mail box view we are using
        ArrayList<Message> thisBox;
        if(usingInbox == true) {
            thisBox = sessionUser.getInbox();
            unread = sessionUser.totalRead();
        } else {
            thisBox = sessionUser.getSentBox();
        }

        // Refresh the lists contents to null
        lstMessages.setItems(null);

        // Observable list containing items to add
        ObservableList payments = populateObservable(thisBox);

        // Set the items returned by populateObservable(T);
        lstMessages.setItems(payments);
        lstMessages.setFixedCellSize(50);

        // Use a cell factory for custom styling
        lstMessages.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                MessageCell pCell = new MessageCell(index, controller, thisBox);
                pCell.getStyleClass().add("lineBottom");
                index++;
                return pCell;
            }
        });

        // Reset the flag
        this.usingInbox = true;
    }

    /**
     * Populates an observable list, this needs to be seperated into
     * its own method to allow for the list to be refreshed from the
     * cellfactory
     * @return an observable array of objects to populate the list
     */
    public ObservableList populateObservable(ArrayList<Message> messagesArray) {

        ObservableList messages = FXCollections.observableArrayList();

        // Loop through the session holders Message array and create a listview item
        for(int i = 0; i < messagesArray.size(); i++) {

            String thisMessage;
            String appendSender = "";
            Message m = messagesArray.get(i);

            switch(m.getType()) {
                case "ALERT":
                    appendSender = "System: ";
                    break;
                case "INBOX":
                    appendSender = m.getSender() + ": ";
                    break;
                case "MAINTENANCE":
                    appendSender = "System: ";
                    break;
            }


            if(m.getMessage().length() > 32) {
                // Only get the first 70 character
                thisMessage = messagesArray.get(i).getMessage().substring(0, 32) + "...";
            } else {
                thisMessage = messagesArray.get(i).getMessage();
            }

            // Add to observable
            messages.add(appendSender + thisMessage);
        }

        return messages;
    }

    /******************************************************
     *                ANIMATION CONTROLS
     ******************************************************/
    public void animateIn()
    {
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(title.layoutYProperty(), 20);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);
        final KeyValue kv1 = new KeyValue(widget_top_right.opacityProperty(), 1);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(widget_bottom_right.opacityProperty(), 1);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);

        // Animate the position in
        final KeyValue kv3 = new KeyValue(widget_top_right.translateXProperty(), -316);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(400), kv3);
        final KeyValue kv4 = new KeyValue(widget_bottom_right.translateYProperty(), -480);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4);
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
        final KeyValue kv2 = new KeyValue(widget_bottom_right.opacityProperty(), 0.3);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);

        // Animate the position in
        final KeyValue kv3 = new KeyValue(widget_top_right.translateXProperty(), 0);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(400), kv3);
        final KeyValue kv4 = new KeyValue(widget_bottom_right.translateYProperty(), 0);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4);
        load_scene.play();
    }

    /**
     * Animates the new message panel in
     */
    public void showNewMessage() {
        chatOverlay.setVisible(true);
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(chatOverlay.opacityProperty(), 1);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);
        final KeyValue kv1 = new KeyValue(messageWrap.opacityProperty(), 1);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(750), kv1);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1);
        load_scene.play();
    }

    /**
     * Animates the new message panel in
     */
    public void hideMessages() {
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(chatOverlay.opacityProperty(), 0);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(750), kv0);
        final KeyValue kv1 = new KeyValue(messageWrap.opacityProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(250), kv1);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1);
        load_scene.play();

        chatOverlay.setVisible(false);
    }

    /**
     * Reveals the user panel and toggles the hidden flag to false
     */
    public void showUsers()
    {
        final Timeline slideOut = new Timeline();
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 339);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(250), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 339);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(250), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 67);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(350), kv3);
        slideOut.getKeyFrames().addAll(kf1, kf2, kf3);
        slideOut.play();

        nav_bg2.getStyleClass().add("light_hover");
        accent2.setStyle("visibility: visible !important");
        txtUsers_Username.requestFocus();
        usersHidden = false;

    }

    /**
     * Slides the user panel back inward and toggles the hidden flag to true
     */
    public void hideUsers()
    {
        final Timeline slideBack = new Timeline();
        slideBack.setCycleCount(1);
        slideBack.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(350), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(250), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 0);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(250), kv3);
        slideBack.getKeyFrames().addAll(kf1, kf2, kf3);
        slideBack.play();

        nav_bg2.getStyleClass().remove("light_hover");
        accent2.setStyle("visibility: hidden !important");
        txtUsers_Username.setText("");
        spinner_green.setVisible(false);
        btnUserSearch.getStyleClass().remove("searching");
        usersHidden = true;
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
                        case "Settings":
                            nav_bg6.getStyleClass().addAll("active");
                            nav_icon6.getStyleClass().add("active");
                            accent6.getStyleClass().addAll("active", "show");
                            break;
                        case "Add Tenant":
                            nav_bg2.getStyleClass().addAll("active");
                            nav_icon2.getStyleClass().add("active");
                            accent2.getStyleClass().addAll("active", "show");
                            break;
                        case "View Tenant":
                            nav_bg2.getStyleClass().addAll("active");
                            nav_icon2.getStyleClass().add("active");
                            accent2.getStyleClass().addAll("active", "show");
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
        nav_icon5.getStyleClass().remove("active");
        nav_bg5.getStyleClass().remove("active");
        accent5.getStyleClass().remove("show");
    }

    /**
     * Reset the navigation styles to make this current window the active one, if we don't call this method
     * then the next time we load this window form the HashMap, the wrong active state shall be applied
     */
    private void resetStyles()
    {
        // Active state for this window
        nav_icon5.getStyleClass().add("active");
        nav_bg5.getStyleClass().add("active");
        accent5.getStyleClass().addAll("active", "show");

        // Default styles for every other nav element
        nav_icon4.getStyleClass().remove("active");
        accent4.getStyleClass().removeAll("active", "show");
        nav_bg4.getStyleClass().remove("active");
        nav_icon2.getStyleClass().remove("active");
        accent2.getStyleClass().removeAll("active", "show");
        nav_bg2.getStyleClass().remove("active");
        nav_icon3.getStyleClass().remove("active");
        accent3.getStyleClass().removeAll("active", "show");
        nav_bg3.getStyleClass().remove("active");
        nav_icon5.getStyleClass().remove("active");
        accent5.getStyleClass().removeAll("active", "show");
        nav_bg5.getStyleClass().remove("active");
        nav_icon6.getStyleClass().remove("active");
        accent6.getStyleClass().removeAll("active", "show");
        nav_bg6.getStyleClass().remove("active");

    }

    /******************************************************
     *              LOAD NEW SCREEN METHODS
     ******************************************************/
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    /**
     * Performs a search on the user objects and sets the correct
     * results in the AllUsers view.
     * If only one result is found on the search, the user is taken to
     * the UserInfo view where they can view details on that user
     * @param event
     */
    @FXML
    private void goToUsers(ActionEvent event)
    {
        // Local variables
        Searcher s = ScreensFramework.searchObj;
        String[] name = txtUsers_Username.getText().split(" ");
        String forename = null;
        String surname  = null;

        // Check that the textbox has been set
        if(txtUsers_Username.getText().length() > 0 && txtUsers_Username.getText() != null)
        {
            // Build a forename and surname string to search for the user
            try
            {
                forename = name[0];
                System.out.println(forename);

                surname  = name[1];
                System.out.println(surname);

            } catch (Exception e)
            {
                System.out.println("Error:" + e.getMessage());
            }

            // Populate a new UserList item collection of results
            UserList results = ScreensFramework.tenants.getTenant(forename, surname);


            // Is there more than one user
            if(results.size() > 1)
            {
                hideUsers();
                nextForm(ScreensFramework.screen2ID);
            }
            // Is there only one user with that name?
            else if(results.size() == 1) {
                Tenant t = results.getUserAt(0);
                s.setTenant(t);
                hideUsers();
                nextForm(ScreensFramework.screen8ID);
            } else {
                hideUsers();
                nextForm(ScreensFramework.screen2ID);
            }
        } else {
            s.setSearchedUsers(ScreensFramework.tenants);
            hideUsers();
            nextForm(ScreensFramework.screen2ID);
        }
    }

    @FXML
    private void goToDashboard(ActionEvent event){
        hideUsers();
        nextForm(ScreensFramework.screen1ID);
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
        nextForm(ScreensFramework.screen2ID);
    }
}
