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
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import javafx.util.Duration;
import prcse.pp.model.Tenant;
import prcse.pp.model.Room;
import prcse.pp.model.UserList;
import prcse.pp.view.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import prcse.pp.model.Property;


/**
 * FXML Controller class
 *
 * @author PRCSE
 */
public class PropertyController implements Initializable, ControlledScreen {

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
    @FXML // fx:id="widget_right"
    private Pane widget_right;
    @FXML // fx:id="widget_top_left"
    private Pane widget_top_left;
    @FXML // fx:id="widget_bottom_left"
    private Pane widget_bottom_left;
    @FXML // fx:id="txtName"
    private TextField txtName;
    @FXML // fx:id="txtEmail"
    private TextField txtEmail;
    @FXML // fx:id="txtProperty"
    private TextField txtProperty;
    @FXML // fx:id="lstProperties"
    private ListView lstProperties;
    @FXML // fx:id="body"
    private AnchorPane body;
    @FXML // fx:id="propertyDetails"
    private Pane propertyDetails;
    @FXML // fx:id="lblAddr1"
    private Label lblAddr1;
    @FXML // fx:id="lblNumRooms"
    private Label lblNumRooms;
    @FXML // fx:id="btnHideDetails"
    private Button btnHideDetails;
    @FXML // fx:id="lstRooms"
    private ListView lstRooms;
    @FXML // fx:id="roomDetailsWrap"
    private Pane roomDetailsWrap;
    @FXML // fx:id="overlay"
    private Pane overlay;
    @FXML // fx:id="btnUpdate"
    private Button btnUpdate;
    @FXML // fx:id="btnAddTenant"
    private Button btnAddTenant;
    @FXML // fx:id="btnBack"
    private Button btnBack;
    @FXML // fx:id="lblCurrStatus"
    private Label lblCurrStatus;
    @FXML // fx:id="lblRoomPrice"
    private Label lblRoomPrice;
    @FXML // fx:id="occupiedWrap"
    private Pane occupiedWrap;
    @FXML // fx:id="imgCurrTenant"
    private ImageView imgCurrTenant;
    @FXML // fx:id="lblTenantName"
    private Label lblTenantName;
    @FXML // fx:id="lblRoomDetails"
    private Label lblRoomDetails;
    @FXML // fx:id="editRoomWrap2
    private Pane editRoomWrap;
    @FXML // fx:id="txtRoomPrice"
    private TextField txtRoomPrice;
    @FXML // fx:id="txtRoomDetails"
    private TextArea txtRoomDetails;
    @FXML // fx:id="lblRoomCharCount"
    private Label lblRoomCharCount;
    @FXML // fx:id="btnCloseEditRoom"
    private Button btnCloseEditRoom;
    @FXML // fx:id="btnRoomSave"
    private Button btnRoomSave;
    @FXML // fx:id="tenantWrap"
    private Pane tenantWrap;
    @FXML // fx:id="searchName"
    private TextField searchName;
    @FXML // fx:id="lstTenants"
    private ListView lstTenants;
    @FXML // fx:id="btnCancel"
    private Button btnCancel;
    @FXML // fx:id="btnAssignTenant"
    private Button btnAssignTenant;
    @FXML // fx:id="editPropertyWrap"
    private Pane editPropertyWrap;
    @FXML // fx:id="txtChangeAddr1"
    private TextField txtChangeAddr1;
    @FXML // fx:id="txtChangeAddr2"
    private TextField txtChangeAddr2;
    @FXML // fx:id="txtChangePostcode"
    private TextField txtChangePostcode;
    @FXML // fx:id="txtChangeCity"
    private TextField txtChangeCity;
    @FXML // fx:id="txtChangeMapCode"
    private TextField txtChangeMapCode;
    @FXML // fx:id="btnUpdateProperty"
    private Button btnUpdateProperty;
    @FXML // fx:id="lblEditingProperty"
    private Label lblEditingProperty;
    @FXML // fx:id="btnHideEditProperty"
    private Button btnHideEditProperty;
    @FXML // fx:id="btnRemoveTenant"
    private Button btnRemoveTenant;

    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;
    ScreensController myController;

    // Allows us to bind controls to custom cell factory
    private int index = 0;

    // Reference to this contorller object
    private PropertyController thisController = this;

    // Null property object to be set by setter method
    private Property currProperty = null;
    private Room     currRoom     = null;

    // Determines if the screen has loaded or not
    private Boolean objectsLoaded = false;

    // List of searched users -- default to the tenant list on the homescreen (defaults on body getting focus)
    private UserList users;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        // Set the display graphic for title
        Effect glow = new Glow(0.3);
        title.setEffect(glow);
        propertyDetails.setVisible(false);
        overlay.setVisible(false);
        roomDetailsWrap.setVisible(false);
        editRoomWrap.setVisible(false);
        tenantWrap.setVisible(false);
        editPropertyWrap.setVisible(false);


        body.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                users = ScreensFramework.tenants;
                if(objectsLoaded == false) {
                    populatePropertyList();
                    setSearchResults(ScreensFramework.tenants);
                    objectsLoaded = true;
                }
            }
        });

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
        nav2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent2.setStyle("visibility: visible");
            }
        });
        nav2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent2.setStyle("visibility: hidden");
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
                btnUserSearch.getStyleClass().add("searching");
                spinner_green.setVisible(true);
            }
        });
        btnHideDetails.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                propertyDetails.setVisible(false);
                roomDetailsWrap.setVisible(false);
                editRoomWrap.setVisible(false);
                tenantWrap.setVisible(false);
                editPropertyWrap.setVisible(false);
                lstRooms.setVisible(true);
                hideOverlay();
                hideTenants();
            }
        });
        btnCloseEditRoom.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                editRoomWrap.setVisible(false);
                roomDetailsWrap.setVisible(true);
                tenantWrap.setVisible(false);
            }
        });
        btnCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hideTenants();
            }
        });
        btnAssignTenant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Tenant t = users.getUserAt(lstTenants.getSelectionModel().getSelectedIndex());
                Room   r = getCurrRoom();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Occupy the room with user t  - pass true as we want to insert a new payment object here
                        if(t != null) {
                            if(t.getRoom() == null) {
                                t.setRoom(r);
                                r.occupied(t, true);
                            } else {
                                t.unsetRoom();
                                t.setRoom(r);
                                r.occupied(t, true);
                            }
                            showRoomDetails(getCurrRoom());

                            showRooms(false);
                            hideTenants();
                        }
                    }
                }).start();
            }
        });
        btnRemoveTenant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Room   r = getCurrRoom();
                Tenant t = getCurrRoom().getTenant();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Occupy the room with user t  - pass true as we want to insert a new payment object here
                        if(t != null) {
                            t.unsetRoom();
                            r.vacant();
                            showRoomDetails(getCurrRoom());

                            refreshList(lstRooms);
                            showRooms(false);
                        }
                    }
                }).start();
            }
        });
        btnHideEditProperty.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                editPropertyWrap.setVisible(false);
                populateRoomList(getCurrProperty());
                lstRooms.setVisible(true);
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
        lstProperties.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    // Show the rooms of the property at the selected index
                    int index = lstProperties.getSelectionModel().getSelectedIndex();
                    Property currProperty = ScreensFramework.properties.getPropertyAt(index);
                    setCurrentProperty(currProperty);
                    showRooms(false);
                } catch (IndexOutOfBoundsException e) {
                    ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
                }
            }
        });
        lstRooms.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int index = lstRooms.getSelectionModel().getSelectedIndex();
                Property p = getCurrProperty();
                Room thisRoom = p.getRoomAt(index);
                setCurrentRoom(thisRoom);
                showRoomDetails(thisRoom);
            }
        });
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                roomDetailsWrap.setVisible(false);
                lstRooms.setVisible(true);
            }
        });
        btnUpdate.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                txtRoomPrice.setText(String.valueOf(currRoom.getPrice()));
                txtRoomDetails.setText(currRoom.getDetails());
                roomDetailsWrap.setVisible(false);
                editRoomWrap.setVisible(true);
            }
        });
        btnRoomSave.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!txtRoomDetails.getText().isEmpty() && ScreensFramework.validateThis.validateCurrency(txtRoomPrice.getText())){

                    // Update the DB
                    String update = "UPDATE rooms SET room_price = '" + txtRoomPrice.getText() + "', room_details = '" + txtRoomDetails.getText() + "' WHERE room_id =" + getCurrRoom().getRoomId();
                    ScreensFramework.db.update(update);

                    // Set the changed states in the object
                    currRoom.setPrice(txtRoomPrice.getText());
                    currRoom.setDetails(txtRoomDetails.getText());

                    // Reset the labels
                    lblRoomPrice.setText(txtRoomPrice.getText());
                    lblRoomDetails.setText(txtRoomDetails.getText());

                    // Refresh the list
                    populateRoomList(currProperty);

                    // Go back to detail screen
                    editRoomWrap.setVisible(false);
                    roomDetailsWrap.setVisible(true);
                }
            }
        });
        txtRoomDetails.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                int chars = txtRoomDetails.getText().length();
                String message = txtRoomDetails.getText();

                if (chars < 151) {
                    String amount = String.valueOf(chars);
                    lblRoomCharCount.setText(amount + "/150 Characters");
                    lblRoomCharCount.setStyle("-fx-text-fill: #ffffff");
                } else {
                    // Updat the message to the old state
                    String fullMsg = message.substring(0, 150);
                    txtRoomDetails.setText(fullMsg);

                    // Set the position to the end of the string
                    txtRoomDetails.positionCaret(150);
                    lblRoomCharCount.setText("150/150 Characters");
                    lblRoomCharCount.setStyle("-fx-text-fill: #f92772");
                }
            }
        });
        btnAddTenant.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                populateTenantList();
                showTenants();
            }
        });
        btnUpdateProperty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String update = "UPDATE properties SET addr_line_1 = '" + txtChangeAddr1.getText() + "', addr_line_2 = '" + txtChangeAddr2.getText()
                                + "', addr_postcode = '" + txtChangePostcode.getText() +  "', city_name = '" + txtChangeCity.getText()
                                + "', google_map_code= '" + txtChangeMapCode.getText() + "' WHERE property_id =" + getCurrProperty().getPropertyId();
                        ScreensFramework.db.update(update);
                    }
                }).start();
            }
        });
        searchName.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String   name    = searchName.getText();
                UserList results = new UserList();

                // Begin searching through the userlist
                if(name.length() > 0) {
                    for(int i = 0; i < ScreensFramework.tenants.size(); i++) {
                        Tenant t = ScreensFramework.tenants.getUserAt(i);
                        if(t.getName().substring(0, name.length()).toLowerCase().equals(name.toLowerCase())) {
                            results.addUser(t);
                        } else {
                            setSearchResults(ScreensFramework.tenants);
                        }
                        setSearchResults(results);
                        populateTenantList();
                    }
                } else {
                    setSearchResults(ScreensFramework.tenants);
                    populateTenantList();
                }
            }
        });
    }

    /**
     * Refreshes a given listview
     * @param list the listview we are refreshing
     */
    private void refreshList(ListView list) {
        ObservableList items = list.getItems();
        list.setItems(null);
        list.setItems(items);
    }

    /**
     * Populates the tenant ListView
     */
    private void populateTenantList() {

        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;

        // Refresh the lists contents to null
        lstTenants.setItems(null);

        // Observable list containing items to add
        ObservableList tenants = populateTenantsObservable(getSearchResults());

        // Set the items returned by populateObservable(T);
        lstTenants.setItems(tenants);

        // Use a cell factory for custom styling
        lstTenants.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                Tenant t = ScreensFramework.tenants.getUserAt(index);
                UserCellSmall uCell = new UserCellSmall(index, thisController, t);
                uCell.getStyleClass().add("lineBottom");
                index++;
                return uCell;
            }
        });
    }

    /**
     * Populates an observable list, this needs to be seperated into
     * its own method to allow for the list to be refreshed from the
     * cellfactory
     * @return an observable array of objects to populate the list
     */
    private ObservableList populateTenantsObservable(ArrayList<Tenant> tenantArray) {

        ObservableList tenants = FXCollections.observableArrayList();

        // Loop through the session holders Message array and create a listview item
        for(int i = 0; i < tenantArray.size(); i++) {

            Tenant t = tenantArray.get(i);

            // Add to observable
            tenants.add(t.getName());
        }
        return tenants;
    }

    /**
     * Returns a UserList containing the current search results
     */
    private ArrayList getSearchResults() {
        ArrayList tenants = new ArrayList();
        for(int i = 0; i < this.users.size(); i++) {
            tenants.add(users.getUserAt(i));
        }
        return tenants;
    }

    /**
     * Sets the searchResults
     * @param searchedTenants a UserList of tenants in the current list
     */
    private void setSearchResults(UserList searchedTenants) {
        this.users = searchedTenants;
    }

    /**
     * Populates the payment ListView
     */
    private void populatePropertyList() {

        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;

        // Refresh the lists contents to null
        lstProperties.setItems(null);

        // Observable list containing items to add
        ObservableList payments = populateObservable(ScreensFramework.properties.getAllProperties());

        // Set the items returned by populateObservable(T);
        lstProperties.setItems(payments);
        lstProperties.setFixedCellSize(65);

        // Use a cell factory for custom styling
        lstProperties.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                Property p = ScreensFramework.properties.getPropertyAt(index);
                PropertyCell pCell = new PropertyCell(index, thisController, p);
                pCell.getStyleClass().add("lineBottom");
                index++;
                return pCell;
            }
        });
    }

    /**
     * Populates an observable list, this needs to be seperated into
     * its own method to allow for the list to be refreshed from the
     * cellfactory
     * @return an observable array of objects to populate the list
     */
    private ObservableList populateObservable(ArrayList<Property> propertiesArray) {

        ObservableList messages = FXCollections.observableArrayList();

        // Loop through the session holders Message array and create a listview item
        for(int i = 0; i < propertiesArray.size(); i++) {

            Property p = propertiesArray.get(i);

            // Add to observable
            if(p.getAddressLine2() == null) {
                messages.add(p.getAddressLine1());
            } else {
                messages.add(p.getAddressLine1() + ", " + p.getCity());
            }
        }
        return messages;
    }

    /**
     * Show the rooms container and populate its details
     */
    public void showRooms(Boolean editing) throws NullPointerException {
        animateOverlay();
        propertyDetails.setVisible(true);

        Property p = getCurrProperty();

        // Set the properties name label
        if(p.getAddressLine2() == null) {
            lblAddr1.setText(p.getAddressLine1() + ", " + p.getCity());
        } else {
            lblAddr1.setText(p.getAddressLine1() + " " + p.getAddressLine2());
        }

        lblNumRooms.setText(String.valueOf(p.numRooms()) + " bedroom property");

        // If we are not editing show rooms
        if(editing == false) {
            populateRoomList(p);
        }
    }

    /**
     * Populates the room ListView
     */
    private void populateRoomList(Property p) {

        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;

        // Refresh the lists contents to null
        lstRooms.setItems(null);

        // Observable list containing items to add
        ObservableList rooms = populateRoomsObservable(p.getRooms());

        // Set the items returned by populateObservable(T);
        lstRooms.setItems(rooms);
        lstRooms.setFixedCellSize(45);

        // Use a cell factory for custom styling
        lstRooms.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                RoomCell pCell = new RoomCell(index, thisController, p);
                index++;
                return pCell;
            }
        });
    }

    /**
     * Populates an observable list, this needs to be seperated into
     * its own method to allow for the list to be refreshed from the
     * cellfactory
     * @return an observable array of objects to populate the list
     */
    private ObservableList populateRoomsObservable(ArrayList<Room> roomArray) {

        ObservableList rooms = FXCollections.observableArrayList();

        // Loop through the session holders Message array and create a listview item
        for(int i = 0; i < roomArray.size(); i++) {

            Room r = roomArray.get(i);

            // Add to observable
            rooms.add("£"+String.valueOf(r.getPrice()));
        }
        return rooms;
    }

    /**
     * Shows all details on a room
     */
    public void showRoomDetails(Room thisRoom) throws NullPointerException {
        roomDetailsWrap.setVisible(true);
        lstRooms.setVisible(false);

        // Set status styles and text
        lblCurrStatus.setText(thisRoom.getStatusAsString(thisRoom.getStatus()));
        if(lblCurrStatus.getText().toUpperCase().equals("OCCUPIED")) {
            lblCurrStatus.getStyleClass().remove("green");
            lblCurrStatus.getStyleClass().addAll("encapsulated-small-wide", "orange");
        } else {
            lblCurrStatus.getStyleClass().remove("orange");
            lblCurrStatus.getStyleClass().addAll("encapsulated-small-wide", "green");
        }

        // Populate the room details
        lblRoomDetails.setText(thisRoom.getDetails());

        // Determine if the room is occupied or not
        if(lblCurrStatus.getText().equals("Occupied")) {
            occupiedWrap.setVisible(true);

            // Get the occupant tenant object
            if(thisRoom.getTenant() != null) {
                Tenant occupant = thisRoom.getTenant();
                lblTenantName.setText(occupant.getName());
                btnAddTenant.setVisible(false);
                btnRemoveTenant.setVisible(true);
            }
        } else {
            occupiedWrap.setVisible(true);
            lblTenantName.setText("No occupant");
            btnAddTenant.setVisible(true);
            btnRemoveTenant.setVisible(false);
        }

        lblRoomPrice.setText(String.valueOf("£" + thisRoom.getPrice()));
    }

    /**
     * Shows the current details of the room
     */
    public void showEditDetails() throws NullPointerException {
        animateOverlay();
        roomDetailsWrap.setVisible(false);
        editRoomWrap.setVisible(false);
        propertyDetails.setVisible(true);
        editPropertyWrap.setVisible(true);
        lstRooms.setVisible(false);

        Property p = getCurrProperty();

        lblAddr1.setText(p.getAddressLine1() + ", " + p.getCity());
        lblNumRooms.setText(String.valueOf(p.numRooms()) + " bedroom property");
        lblEditingProperty.setText("Editing: " + p.getAddressLine1());

        // Set the properties name label
        txtChangeAddr1.setText(p.getAddressLine1());
        if(p.getAddressLine2().length() > 0){
            txtChangeAddr2.setText(p.getAddressLine2());
        } else {
            txtChangeAddr2.setText("");
        }
        txtChangeCity.setText(p.getCity());
        txtChangePostcode.setText(p.getPostcode());
        txtChangeMapCode.setText(p.getMapCode());
    }

    /**
     * Sets the current property object
     * @param thisProperty - the property we are setting
     */
    public void setCurrentProperty(Property thisProperty) {
        currProperty = thisProperty != null ? thisProperty : null;
    }

    /**
     * Returns the current property object
     */
    public Property getCurrProperty() {
        return this.currProperty;
    }

    /**
     * Sets the current room object
     * @param thisRoom - the room we are setting
     */
    public void setCurrentRoom(Room thisRoom) {
        currRoom = thisRoom != null ? thisRoom : null;
    }

    /**
     * Returns the current property object
     */
    public Room getCurrRoom() {
        return this.currRoom;
    }

    /******************************************************
     *                ANIMATION CONTROLS
     ******************************************************/
    public void animateOverlay() {
        overlay.setVisible(true);
        final Timeline animateIn = new Timeline();
        animateIn.setCycleCount(1);
        animateIn.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(overlay.opacityProperty(), 1);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        animateIn.getKeyFrames().addAll(kf1);
        animateIn.play();
    }

    public void hideOverlay() {
        final Timeline animateOut = new Timeline();
        animateOut.setCycleCount(1);
        animateOut.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(overlay.opacityProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        animateOut.getKeyFrames().addAll(kf1);
        animateOut.play();

        // Sleep and hide the pane afterward, otherwise unable to use GUI
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    overlay.setVisible(false);
                } catch (InterruptedException e) {
                    ScreensFramework.logError.writeToFile("Error: failed to play the animation, " + e.getMessage());
                } catch (IllegalStateException e) {
                    ScreensFramework.logGeneral.writeToFile("Warning: " + e.getMessage());
                }
            }
        }).start();
    }

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
        slideOut.getKeyFrames().addAll(kf1, kf2, kf3);
        slideOut.play();

        txtUsers_Username.requestFocus();
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
        slideBack.getKeyFrames().addAll(kf1, kf2, kf3);
        slideBack.play();

        txtUsers_Username.setText("");
        spinner_green.setVisible(false);
        btnUserSearch.getStyleClass().remove("searching");
    }

    public void showTenants() {
        tenantWrap.setVisible(true);
        final Timeline slideOut = new Timeline();
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(tenantWrap.translateXProperty(), 302);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(250), kv1);
        final KeyValue kv2 = new KeyValue(propertyDetails.translateXProperty(), -120);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(250), kv2);
        slideOut.getKeyFrames().addAll(kf1, kf2);
        slideOut.play();
    }

    public void hideTenants() throws NullPointerException {
        final Timeline slideOut = new Timeline();
        slideOut.setCycleCount(1);
        slideOut.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(tenantWrap.translateXProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(propertyDetails.translateXProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(250), kv2);
        slideOut.getKeyFrames().addAll(kf1, kf2);
        slideOut.play();
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
                    //animateOut();
                    Thread.sleep(300);
                } catch(Exception e )
                {
                    System.out.println("There was an error handling the animation...");
                }

                // Go to our view.
                myController.setScreen(ID);
            }
        }).start();

        //objectsSet = false;
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
        nav_icon3.getStyleClass().add("active");
        nav_bg3.getStyleClass().add("active");
        accent3.getStyleClass().addAll("active", "show");

        // Default styles for every other nav element
        nav_icon1.getStyleClass().remove("active");
        accent1.getStyleClass().removeAll("active", "show");
        nav_bg1.getStyleClass().remove("active");
        nav_icon4.getStyleClass().remove("active");
        accent4.getStyleClass().removeAll("active", "show");
        nav_bg4.getStyleClass().remove("active");
        nav_icon2.getStyleClass().remove("active");
        accent2.getStyleClass().removeAll("active", "show");
        nav_bg2.getStyleClass().remove("active");
        nav_icon5.getStyleClass().remove("active");
        accent5.getStyleClass().removeAll("active", "show");
        nav_bg5.getStyleClass().remove("active");
    }

    // Set the parent of the new screen
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToDashboard(ActionEvent event){
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



