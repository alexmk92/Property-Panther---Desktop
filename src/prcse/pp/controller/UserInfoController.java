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
import prcse.pp.model.Tenant;
import prcse.pp.view.NoteCell;
import prcse.pp.model.Note;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;


/**
 * FXML Controller class
 *
 * @author PRCSE
 */
public class UserInfoController implements Initializable, ControlledScreen {

    /******************************************************
     *     FXML VARIABLES - RELATIVE TO UserDetails.XML
     ******************************************************/
    @FXML // fx:id="btn_create_user"
    private Button btn_create_user;
    @FXML // fx:id="btn_view_users"
    private Button btn_view_user;
    @FXML // fx:id="btnSearchUsers"
    private Button btnSearchUsers;
    @FXML // fx:id="btnMakeNote"
    private Button btnMakeNote;
    @FXML // fx:id="btnUserSearch"
    private Button btnUserSearch;
    @FXML // fx:id="btn_create_user"
    private Button btnCreateUser;
    @FXML // fx:id="img_create_user"
    private ImageView img_create_user;
    @FXML // fx:id="img_view_users"
    private ImageView img_view_users;
    @FXML // fx:id="btn_view_user"
    private Button btnViewUsers;
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
    @FXML // fx:id="txtName"
    private TextField txtName;
    @FXML // fx:id="txtEmail"
    private TextField txtEmail;
    @FXML // fx:id="txtProperty"
    private TextField txtProperty;
    @FXML // fx:id="lstNotes"
    private ListView lstNotes;
    @FXML // fx:id="widget_top_left"
    private Pane widget_top_left;
    @FXML // fx:id="widget_middle_left"
    private Pane widget_middle_left;
    @FXML // fx:id="widget_middle_right"
    private Pane widget_middle_right;
    @FXML // fx:id="widget_bottom_left"
    private Pane widget_bottom_left;
    @FXML // fx:id="widget_top_right"
    private Pane widget_top_right;
    @FXML // fx:id="widget_bottom_right"
    private Pane widget_bottom_right;
    @FXML // fx:id="body"
    private AnchorPane body;
    @FXML // fx:id="lblUsername"
    private Label lblUsername;
    @FXML // fx:id="lblUserAddress"
    private Label lblUserAddress;
    @FXML // fx:id="lblEmail"
    private Label lblEmail;
    @FXML // fx:id="lblPhone"
    private Label lblPhone;
    @FXML // fx:id="lblAddr1"
    private Label lblAddr1;
    @FXML // fx:id="lblLocation"
    private Label lblLocation;
    @FXML // fx:id="lblPostcode"
    private Label lblPostcode;
    @FXML // fx:id="lblCharCount"
    private Label lblCharCount;
    @FXML // fx:id="lblNewNote"
    private Label lblNewNote;
    @FXML // fx:id="btnAdd" - adds the note
    private Button btnAdd;
    @FXML // fx:id="btnClearNote"
    private Button btnClearNote;
    @FXML // fx:id="txtNote"
    private TextArea txtNote;
    @FXML // fx:id="noteWrap"
    private Pane noteWrap;
    @FXML // fx:id="btnBack"
    private Button btnBack;
    @FXML // fx:id="errorWrap"
    private Pane errorWrap;
    @FXML // fx:id="lblError"
    private Label lblError;
    @FXML // fx:id="btnClearNotes"
    private Button btnClearNotes;
    @FXML // fx:id="btnHideFull
    private Button btnHideFull;
    @FXML // fx:id="fullNoteWrap"
    private Pane fullNoteWrap;
    @FXML // fx:id="lblNoteBody"
    private Label lblNoteBody;
    @FXML // fx:id="lblNoteTitle"
    private Label lblNoteTitle;
    @FXML // fx:id="btnAddAnother"
    private Button btnAddAnother;
    @FXML // fx:id="btnDeleteThis"
    private Button btnDeleteThis;


    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;
    private int    index   = 0;
    private Boolean objectsSet = false;   // Allows the tenant to only be set once per screen session
    protected Tenant thisTenant;
    ScreensController myController;

    // Reference to controller for the Cell-Factory
    protected UserInfoController controller = this;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        // Hide the add note wrapper - only show when clickNote is enabled
        noteWrap.setVisible(false);
        fullNoteWrap.setVisible(false);

        // Set opacity of widgets
        widget_middle_left.setOpacity(0.3);
        widget_middle_right.setOpacity(0.3);
        widget_top_left.setOpacity(0.3);
        widget_top_right.setOpacity(0.3);
        widget_bottom_left.setOpacity(0.3);
        widget_bottom_right.setOpacity(0.3);

        // Animate the scene in
        body.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(objectsSet == false) {
                    thisTenant = getTenant();
                    renderView();
                }
                index = 0;
                animateIn();
                resetStyles();
                objectsSet = true;
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
        nav6.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent6.setStyle("visibility: visible");
            }
        });
        nav6.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent6.setStyle("visibility: hidden");
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
        txtName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                resetText(txtName, newPropertyValue);
            }
        });
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                resetText(txtEmail, newPropertyValue);
            }
        });
        txtProperty.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) {
                resetText(txtProperty, newPropertyValue);
            }
        });


        btnUserSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // search code here
            }
        });

        btnSearchUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // search code here
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

        btnViewUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScreensFramework.searchObj.setSearchedUsers(ScreensFramework.tenants);
                nextForm(ScreensFramework.screen2ID);
            }
        });

        btnCreateUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextForm(ScreensFramework.screen7ID);
            }
        });

        btn_create_user.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextForm(ScreensFramework.screen7ID);
            }
        });

        btn_view_user.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nextForm(ScreensFramework.screen2ID);
            }
        });

        // Open the add note dialog
        btnMakeNote.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                lstNotes.setVisible(false);
                noteWrap.setVisible(true);
                fullNoteWrap.setVisible(false);
                txtNote.requestFocus();
            }
        });
        btnAddAnother.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                fullNoteWrap.setVisible(false);
                noteWrap.setVisible(true);
                txtNote.requestFocus();
            }
        });
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                noteWrap.setVisible(false);
                lstNotes.setVisible(true);
                txtNote.setText("");
                lblCharCount.setText("0/150 Characters");
                //refreshList(lstNotes);
            }
        });
        btnClearNote.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtNote.setText("");
                lblCharCount.setText("0/150 Characters");
            }
        });
        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Add the note to this tenant
                Note n = new Note(txtNote.getText(), "", 0);
                if(thisTenant.addNote(n, true)){
                    txtNote.setText("");
                    noteWrap.setVisible(false);
                    lstNotes.setVisible(true);
                    populateListView();
                } else {
                    ScreensFramework.logGeneral.writeToFile("The note was not inserted as we failed to connect to the database");
                    slideUpError("Error: Could not connect to the database.");
                    populateListView();
                }
            }
        });
        btnClearNotes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thisTenant.removeAllNotes();
                lstNotes.setItems(null);
            }
        });
        txtNote.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    // Build the note object to add
                    Note n = new Note(txtNote.getText(), "", 0);
                    if (thisTenant.addNote(n, true)) {
                        noteWrap.setVisible(false);
                        lstNotes.setVisible(true);
                        populateListView();
                        slideUpError("Success: Your note was added.");
                        txtNote.setText("");
                        txtNote.positionCaret(0);
                    } else {
                        ScreensFramework.logGeneral.writeToFile("The note was not inserted as we failed to connect to the database");
                        slideUpError("Error: Could not connect to the database.");
                    }
                }
            }
        });
        txtNote.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int chars = txtNote.getText().length();
                String message = txtNote.getText();

                if (chars < 151) {
                    String amount = String.valueOf(chars);
                    lblCharCount.setText(amount + "/150 Characters");
                    lblCharCount.setStyle("-fx-text-fill: #ffffff");
                } else {
                    // Updat the message to the old state
                    String fullMsg = message.substring(0, 150);
                    System.out.println(fullMsg.length());
                    txtNote.setText(fullMsg);

                    // Set the position to the end of the string
                    txtNote.positionCaret(150);
                    lblCharCount.setText("150/150 Characters");
                    lblCharCount.setStyle("-fx-text-fill: #f92772");
                }
            }
        });
        btnDeleteThis.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thisTenant.removeNoteAt(index, true);
                populateListView();
                slideUpError("Success: Note was deleted.");
                fullNoteWrap.setVisible(false);
                lstNotes.setVisible(true);
            }
        });
        lstNotes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Note n = thisTenant.getNoteAt(lstNotes.getSelectionModel().getSelectedIndex());
                // Sets the index to this index
                index = lstNotes.getSelectionModel().getSelectedIndex();
                lblNoteBody.setText(n.getMessage());
                lblNoteTitle.setText(n.getDate());
                lstNotes.setVisible(false);
                fullNoteWrap.setVisible(true);
            }
        });
        btnHideFull.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                populateListView();
                fullNoteWrap.setVisible(false);
                lstNotes.setVisible(true);
            }
        });

    }

    /******************************************************
     *                      METHOD
     ******************************************************/
    public Tenant getTenant()
    {
        return ScreensFramework.searchObj.getTenant();
    }

    /**
     * Renders all objects and sets their data appropriate to the Tenant object
     * that has been passed.
     */
    public void renderView()
    {
        // Sets the title to the tenants name
        lblUsername.setText(thisTenant.getName());

        // Populate all widgets
        if(thisTenant.getAddr_line_2() == "NULL" || thisTenant.getAddr_line_2() == null) {
            lblUserAddress.setText(thisTenant.getAddr_line_1());
            lblAddr1.setText(thisTenant.getAddr_line_1() + "\n" + thisTenant.getCity() + "\n" + thisTenant.getPostcode());
        } else {
            lblUserAddress.setText(thisTenant.getAddr_line_1() + " " + thisTenant.getAddr_line_2());
            lblAddr1.setText(thisTenant.getAddr_line_1() + ", " + thisTenant.getAddr_line_2() + "\n" + thisTenant.getCity() + "\n" + thisTenant.getPostcode());
        }
        lblEmail.setText(thisTenant.getEmail());
        lblPhone.setText("+" + thisTenant.getPhone());
        lblNewNote.setText("Adding new note for " + thisTenant.getName());

        populateListView();

    }

    /**
     * Populates the lsitview
     */

    public void populateListView() {

        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;

        // Refresh the lists contents to null
        lstNotes.setItems(null);

        // Observable list containing items to add
        ObservableList notes = populateObservable(thisTenant.getNotes());

        // Set the items returned by populateObservable(T);
        lstNotes.setItems(notes);
        lstNotes.setFixedCellSize(50);

        // Use a cell factory for custom styling
        lstNotes.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                NoteCell nCell = new NoteCell(index, controller);
                index++;
                return nCell;
            }
        });
    }

    /**
     * Populates an observable list, this needs to be seperated into
    * its own method to allow for the list to be refreshed from the
    * cellfactory
    * @return an observable array of objects to populate the list
    */
    public ObservableList populateObservable(ArrayList<Note> notesArray) {

        ObservableList notes = FXCollections.observableArrayList();

        // Loop through the users Notes array and create a listview item
        for(int i = 0; i < notesArray.size(); i++) {
            Note thisNote = notesArray.get(i);

            String thisMessage;

            if(thisNote.getMessage().length() > 60) {
                // Only get the first 70 character
                thisMessage = thisNote.getMessage().substring(0, 60) + "...";
            } else {
                thisMessage = thisNote.getMessage();
            }

            // Add to observable
            notes.add(thisMessage);
        }

        return notes;
    }

    /**
     * Returns the listview
     * @return
     */
    public ListView getList() {
        return this.lstNotes;
    }


    /******************************************************
     *                ANIMATION CONTROLS
     ******************************************************/
    public void showUsers()
    {
        final Timeline slideOut = new Timeline();
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);

        // Slide out the window
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 339);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(300), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 339);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(300), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 67);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(700), kv3);

        // Fade the widgets
        final KeyValue kv4 = new KeyValue(widget_top_right.opacityProperty(), 0.3);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_top_left.opacityProperty(), 0.3);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);
        final KeyValue kv6 = new KeyValue(widget_bottom_left.opacityProperty(), 0.3);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(500), kv6);
        final KeyValue kv7 = new KeyValue(widget_middle_left.opacityProperty(), 0.3);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(500), kv7);
        final KeyValue kv8 = new KeyValue(widget_middle_right.opacityProperty(), 0.3);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);
        final KeyValue kv9 = new KeyValue(widget_bottom_right.opacityProperty(), 0.3);
        final KeyFrame kf9 = new KeyFrame(Duration.millis(500), kv9);

        slideOut.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9);
        slideOut.play();
    }

    public void hideUsers()
    {
        final Timeline slideBack = new Timeline();
        slideBack.setCycleCount(1);
        slideBack.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 0);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(300), kv3);

        // Fade widgets back in
        final KeyValue kv4 = new KeyValue(widget_top_right.opacityProperty(), 1);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_top_left.opacityProperty(), 1);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);
        final KeyValue kv6 = new KeyValue(widget_bottom_left.opacityProperty(), 1);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(500), kv6);
        final KeyValue kv7 = new KeyValue(widget_middle_left.opacityProperty(), 1);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(500), kv7);
        final KeyValue kv8 = new KeyValue(widget_middle_right.opacityProperty(), 1);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);
        final KeyValue kv9 = new KeyValue(widget_bottom_right.opacityProperty(), 1);
        final KeyFrame kf9 = new KeyFrame(Duration.millis(500), kv9);

        slideBack.getKeyFrames().addAll(kf1, kf2, kf3);
        slideBack.play();

        txtUsers_Username.setText("");
        spinner_green.setVisible(false);
        btnUserSearch.getStyleClass().remove("searching");
    }

    public void slideUpError(String msg) {

        final Timeline slideUp = new Timeline();
        slideUp.setCycleCount(1);
        slideUp.setAutoReverse(true);

        final KeyValue kv0 = new KeyValue(errorWrap.translateYProperty(), -45);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);

        slideUp.getKeyFrames().addAll(kf0);
        slideUp.play();

        lblError.setText(msg);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    slideBackError();
                } catch(Exception e) {
                    ScreensFramework.logError.writeToFile("Error handling the slide back animation...");
                }
            }
        }).start();
    }

    public void slideBackError() {

        final Timeline slideBack = new Timeline();
        slideBack.setCycleCount(1);
        slideBack.setAutoReverse(true);

        final KeyValue kv0 = new KeyValue(errorWrap.translateYProperty(), 0);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);

        slideBack.getKeyFrames().addAll(kf0);
        slideBack.play();
    }

    public void resetText(TextField txt, Boolean newPropertyValue)
    {
        if (newPropertyValue) {
        } else {
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
        final KeyValue kv4 = new KeyValue(widget_middle_left.opacityProperty(), 1);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_middle_right.opacityProperty(), 1);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);
        final KeyValue kv6 = new KeyValue(widget_bottom_right.opacityProperty(), 1);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(500), kv6);

        // Animate the position in
        final KeyValue kv7 = new KeyValue(widget_top_right.translateXProperty(), -320);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(400), kv7);
        final KeyValue kv8 = new KeyValue(widget_top_left.translateXProperty(), 880);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);
        final KeyValue kv9 = new KeyValue(widget_bottom_left.translateYProperty(), -270);
        final KeyFrame kf9 = new KeyFrame(Duration.millis(500), kv9);
        final KeyValue kv10 = new KeyValue(widget_middle_left.translateXProperty(), 578);
        final KeyFrame kf10 = new KeyFrame(Duration.millis(250), kv10);
        final KeyValue kv11 = new KeyValue(widget_middle_right.translateXProperty(), -625);
        final KeyFrame kf11 = new KeyFrame(Duration.millis(250), kv11);
        final KeyValue kv12 = new KeyValue(widget_bottom_right.translateYProperty(), -443);
        final KeyFrame kf12 = new KeyFrame(Duration.millis(500), kv12);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10, kf11, kf12);
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
        final KeyValue kv4 = new KeyValue(widget_middle_left.opacityProperty(), 0.3);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(500), kv4);
        final KeyValue kv5 = new KeyValue(widget_middle_right.opacityProperty(), 0.3);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(500), kv5);
        final KeyValue kv6 = new KeyValue(widget_bottom_right.opacityProperty(), 0.3);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(500), kv6);

        // Animate the position in
        final KeyValue kv7 = new KeyValue(widget_top_right.translateXProperty(), 0);
        final KeyFrame kf7 = new KeyFrame(Duration.millis(400), kv7);
        final KeyValue kv8 = new KeyValue(widget_top_left.translateXProperty(), 0);
        final KeyFrame kf8 = new KeyFrame(Duration.millis(500), kv8);
        final KeyValue kv9 = new KeyValue(widget_bottom_left.translateYProperty(), 0);
        final KeyFrame kf9 = new KeyFrame(Duration.millis(500), kv9);
        final KeyValue kv10 = new KeyValue(widget_middle_left.translateXProperty(), 0);
        final KeyFrame kf10 = new KeyFrame(Duration.millis(250), kv10);
        final KeyValue kv11 = new KeyValue(widget_middle_right.translateXProperty(), 0);
        final KeyFrame kf11 = new KeyFrame(Duration.millis(200), kv11);
        final KeyValue kv12 = new KeyValue(widget_bottom_right.translateYProperty(), 0);
        final KeyFrame kf12 = new KeyFrame(Duration.millis(500), kv12);

        // Build the animation
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4, kf5, kf6, kf7, kf8, kf9, kf10, kf11, kf12);
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
                        case "Tenants":
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
                        case "View Tenant":
                            nav_bg2.getStyleClass().addAll("active");
                            nav_icon2.getStyleClass().add("active");
                            accent2.getStyleClass().addAll("active", "show");
                            break;
                        case "Add Tenant":
                            nav_bg2.getStyleClass().addAll("active");
                            nav_icon2.getStyleClass().add("active");
                            accent2.getStyleClass().addAll("active", "show");
                            break;
                    }

                    // Animate the scene
                    animateOut();
                    Thread.sleep(400);
                } catch(Exception e )
                {
                    ScreensFramework.logError.writeToFile("There was an error handling the animation...");
                }
                // Go to our view.
                noteWrap.setVisible(false);
                lstNotes.setVisible(true);
                myController.setScreen(ID);
            }
        }).start();

        // Reset the objectsSet flag, this will allow an object to be loaded when a user
        // requests this form from the HashMap again.
        objectsSet = false;
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
        nav_icon6.getStyleClass().remove("active");
        accent6.getStyleClass().removeAll("active", "show");
        nav_bg6.getStyleClass().remove("active");

    }

    // Set the parent of the new screen
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    // Navigation Control
    @FXML
    private void goToDashboard(ActionEvent event){
        // If the user panel is open then hide it
        hideUsers();
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

