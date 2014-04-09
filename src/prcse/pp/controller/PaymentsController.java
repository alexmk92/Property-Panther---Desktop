package prcse.pp.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.util.Duration;
import prcse.pp.model.Tenant;
import prcse.pp.model.UserList;
import prcse.pp.view.NoteCell;
import prcse.pp.view.PaymentFeedCell;
import prcse.pp.model.Payment;
import prcse.pp.view.PaymentCell;

import javax.xml.transform.Result;


/**
 * FXML Controller class
 *
 * @author PRCSE
 */
public class PaymentsController implements Initializable, ControlledScreen {

    /******************************************************
     *       FXML VARIABLES - RELATIVE TO DASHBOARD.XML
     ******************************************************/
    @FXML // fx:id="lstPayments"
    private ListView lstPayments;
    @FXML // fx:id="btnSearchPayments"
    private Button btnSearchPayments;
    @FXML // fx:id="spinner"
    private ImageView spinner;
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
    @FXML // fx:id="spinnr_green"
    private ImageView spinner_green;
    @FXML // fx:id="txtUser_Username"
    private TextField txtUsers_Username;
    @FXML // fx:id="searchBar"
    private Pane searchBar;
    @FXML // fx:id="searchButtons"
    private Pane searchButtons;
    @FXML // fx:id="searchWrap"
    private Pane searchWrap;
    @FXML // fx:id="txtEmail"
    private TextField txtEmail;
    @FXML // fx:id="txtName"
    private TextField txtName;
    @FXML // fx:id="widget_right"
    private Pane widget_right;
    @FXML // fx:id="widget_top_left"
    private Pane widget_top_left;
    @FXML // fx:id="widget_bottom_left"
    private Pane widget_bottom_left;
    @FXML // fx:id="body"
    private AnchorPane body;
    @FXML // fx:id="lstPaymentFeed"
    private ListView lstPaymentFeed;
    @FXML // fx:id="lblOlder"
    private Label lblOlder;
    @FXML // fx:id="lblCurrStatus"
    private Label lblCurrStatus;
    @FXML // fx:id="lblDue"
    private Label lblDue;
    @FXML // fx:id="lblPaid"
    private Label lblPaid;
    @FXML // fx:id="lblAmount"
    private Label lblAmount;
    @FXML // fx:id="lblPayee"
    private Label lblPayee;
    @FXML // fx:id="lblAddress"
    private Label lblAddress;
    @FXML // fx:id="detailsWrap"
    private Pane detailsWrap;
    @FXML // fx:id="btnCloseDetails"
    private Button btnCloseDetails;

    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;
    ScreensController myController;

    // Reference to this controller and a zeroed index
    // for cell factory
    private PaymentsController controller = this;
    private Tenant tenant;
    private int index = 0;

    // Is this screen active? Toggles the refresh thread
    private Boolean pageActive = false;
    private Boolean completed  = false;

    // begin by fetching 5 payments (default)
    private int amount = 5;

    // a new payment list to hold all of the payment objects (payment feed)
    private ArrayList<Payment> paymentFeed = new ArrayList<Payment>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        pageActive = true;

        // Set opacity of widgets
        widget_right.setOpacity(0.3);
        widget_top_left.setOpacity(0.3);
        widget_bottom_left.setOpacity(0.3);

        // Hide the details wrapper
        detailsWrap.setVisible(false);

        // Load the scene in on mouseover
        body.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                populatePaymentFeed();
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
                accent2.setStyle("visibility: visible");
            }
        });
        nav2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent2.setStyle("visibility: hidden");
            }
        });
        nav_icon2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent2.setStyle("visibility: visible");
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
                accent3.setStyle("visibility: visible");
            }
        });
        nav3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: hidden");
            }
        });
        nav_icon3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent3.setStyle("visibility: visible");
                nav_bg3.getStyleClass().add("dark_hover");
            }
        });
        nav_icon3.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav_bg3.getStyleClass().remove("dark_hover");
            }
        });
        nav_icon4.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent4.setStyle("visibility: visible");
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
                accent5.setStyle("visibility: visible");
            }
        });
        nav5.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: hidden");
            }
        });
        nav_icon5.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                accent5.setStyle("visibility: visible");
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
                accent6.setStyle("visibility: visible");
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
                accent6.setStyle("visibility: visible");
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

        btnUserSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnUserSearch.getStyleClass().add("searching");
                spinner_green.setVisible(true);
            }
        });
        btnSearchPayments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String searchBy = "";
                detailsWrap.setVisible(false);

                // Get values set in text boxes
                if(txtName.getText().length() > 0){
                    searchBy = txtName.getText();
                } else if(txtEmail.getText().length() > 0) {
                    searchBy = txtEmail.getText();
                } else {
                    // show some error
                }

                if (searchBy != null) {
                    btnSearchPayments.getStyleClass().add("searching");
                    btnSearchPayments.setText("Searching...");
                    spinner.getStyleClass().remove("hidden");

                    if(fetchPayments(searchBy)){
                        btnSearchPayments.getStyleClass().remove("searching");
                        btnSearchPayments.setText("Search Payments");
                        spinner.getStyleClass().add("hidden");
                    }
                } else {
                    btnSearchPayments.getStyleClass().remove("searching");
                    btnSearchPayments.setText("Search Payments");
                    spinner.getStyleClass().add("hidden");
                }

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
                nav2.getStyleClass().add("light_hover");
                accent2.setStyle("visibility: visible");
            }
        });
        searchWrap.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                nav2.getStyleClass().remove("light_hover");
                accent2.setStyle("visibility: hidden");
                hideUsers();
            }
        });
        lblOlder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                // We only want to clear the array, so pass false
                // check that all payments were removed - if yes then rebuild the payments list
                // with another 5 results
                if (tenant.removeAllPayments(false)) {

                    // 5 was the default amount to fetch, add 5 more
                    amount += 5;

                    // Get another 5 payments
                    ScreensFramework.db.buildPayments(tenant, amount);

                    // Repopulate the list
                    populatePaymentList();
                }
            }
        });
        lstPayments.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selected = lstPayments.getSelectionModel().getSelectedIndex();
                lstPayments.setVisible(false);
                detailsWrap.setVisible(true);
                lblOlder.setVisible(false);

                // Get the current payment object
                Payment p = tenant.getPaymentAt(selected);

                // Set the labels
                lblAddress.setText(tenant.getAddress());
                lblAmount.setText(p.getAmount());
                lblDue.setText(p.getDueDateAsString());
                lblPaid.setText(p.getDateAsString());
                lblCurrStatus.setText(p.getStatus());
                lblPayee.setText(tenant.getName());

                // Set the style on status dependent on result
                if(p.getStatus().equals("PAID")){
                    lblCurrStatus.setStyle("-fx-text-fill: #96CA2E ");
                } else if(p.getStatus().equals("PENDING")) {
                    lblCurrStatus.setStyle("-fx-text-fill: #fe9720");
                } else {
                    lblCurrStatus.setStyle("-fx-text-fill: #f92673");
                }
            }
        });
        lstPaymentFeed.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                int selected = lstPaymentFeed.getSelectionModel().getSelectedIndex();
                Payment p = ScreensFramework.allPayments.get(selected);

                setTenant(p.getTenant());
                populatePaymentList();
            }
        });
        btnCloseDetails.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                detailsWrap.setVisible(false);
                lstPayments.setVisible(true);
                lblOlder.setVisible(true);
            }
        });
    }

    /******************************************************
     *                  CONTROL METHODS
     *****************************************************/

    /**
     * Retrieves all payment objects pertaining to a given user
     * @param searchBy  the email or name we are searching by
     * @return true if results were found, else false
     */
    public Boolean fetchPayments(String searchBy) {
        Boolean paymentsRetrieved = false;

        // Run the query on a new thread
        Runnable getPayments = new Runnable() {
            @Override
            public void run() {
                try {
                    // Name to search by
                    UserList u = ScreensFramework.tenants;
                    Tenant t;

                    // Check if we have searched by email or username
                    if(searchBy.contains("@")){
                        t = u.getTenantByEmail(searchBy);
                    } else {
                        String[] split = searchBy.split(" ");
                        String forename = split[0];
                        String surname = split[1];

                        t = u.getTenantByName(forename.toUpperCase(), surname.toUpperCase());
                    }

                    // Set the global tenant object
                    setTenant(t);

                } catch(Exception e) {
                    ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
                }
            }
        };

        // Assign runnable to the thread
        Thread fetch = new Thread(getPayments);

        // Try to run the thread and join it back to the main thread
        try {
            fetch.start();
            fetch.join();

            paymentsRetrieved = true;

            // Populate the list with the custom CellFactory
            populatePaymentList();
        } catch (Exception e) {
            ScreensFramework.logError.writeToFile("Error: " + e.getMessage());
        }

        return paymentsRetrieved;
    }

    /**
     * Populates the payment ListView
     */
    public void populatePaymentList() {

        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;
        amount = 5;


        // Refresh the lists contents to null
        lstPayments.setItems(null);

        // Observable list containing items to add
        ObservableList payments = populateObservable(tenant.getPayments());

        // Set the items returned by populateObservable(T);
        lstPayments.setItems(payments);
        lstPayments.setFixedCellSize(50);

        // Use a cell factory for custom styling
        lstPayments.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                PaymentCell pCell = new PaymentCell(index, controller);

                // set the background color of cell
                if(index%2==0){
                    pCell.setStyle("-fx-background-color: rgba(38,38,52,0.25)");
                }

                index++;
                return pCell;
            }
        });
    }

    /**
     * Populates the payment feed view
     */
    public void populatePaymentFeed() {
        // Zero the index each time the list view is repopulated to
        // bind to the correct button
        index = 0;
        amount = 5;


        // Observable list containing items to add
        ObservableList payments = populateObservable(ScreensFramework.allPayments);

        // Set the items returned by populateObservable(T);
        lstPaymentFeed.setItems(payments);
        lstPaymentFeed.setFixedCellSize(50);

        // Use a cell factory for custom styling
        lstPaymentFeed.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView listView) {
                PaymentFeedCell pCell = new PaymentFeedCell(index, controller);

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
    public ObservableList populateObservable(ArrayList<Payment> paymentsArray) {

        ObservableList payments = FXCollections.observableArrayList();

        // Loop through the users Payments array and create a listview item
        for(int i = 0; i < paymentsArray.size(); i++) {
            String payee = paymentsArray.get(i).getPayee();

            // Add to observable
            payments.add(payee);
        }

        return payments;
    }

    /**
     * Returns the Payment List
     */
    public ListView<Payment> getPaymentList() {
        return this.lstPayments;
    }

    /**
     *  Returns the Payment Feed List
     */
    public ListView<Payment> getPaymentFeedList() {
        return this.lstPaymentFeed;
    }

    /**
     * Returns the searched Tenant object
     */
    public Tenant getTenant() {
        return this.tenant;
    }

    /**
     * Sets the searched tenant
     * @param t - the tenant we are adding
     * @return added - True if the tenant was added else false
     */
    public Boolean setTenant(Tenant t) {
        Boolean added = false;
        if(t != null) {
            this.tenant = t;
            added = true;
        }
        return added;
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
        final KeyValue kv4 = new KeyValue(widget_top_left.opacityProperty(), 0.5);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(200), kv4);
        final KeyValue kv5 = new KeyValue(widget_bottom_left.opacityProperty(), 0.5);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(200), kv5);
        final KeyValue kv6 = new KeyValue(widget_right.opacityProperty(), 0.5);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(200), kv6);
        slideOut.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6);
        slideOut.play();

        //txtUsers_Username.requestFocus();
    }

    public void hideUsers()
    {
        final Timeline slideBack = new Timeline();
        slideBack.setCycleCount(1);
        slideBack.setAutoReverse(false);
        final KeyValue kv1 = new KeyValue(searchBar.translateXProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(300), kv1);
        final KeyValue kv2 = new KeyValue(searchButtons.translateXProperty(), 0);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(300), kv2);
        final KeyValue kv3 = new KeyValue(searchButtons.translateYProperty(), 0);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(700), kv3);
        final KeyValue kv4 = new KeyValue(widget_top_left.opacityProperty(), 1);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(200), kv4);
        final KeyValue kv5 = new KeyValue(widget_bottom_left.opacityProperty(), 1);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(200), kv5);
        final KeyValue kv6 = new KeyValue(widget_right.opacityProperty(), 1);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(200), kv6);
        slideBack.getKeyFrames().addAll(kf1, kf2, kf3, kf4, kf5, kf6);
        slideBack.play();

        txtUsers_Username.setText("");
        spinner_green.setVisible(false);
        btnUserSearch.getStyleClass().remove("searching");
    }

    public void animateIn()
    {
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(title.layoutYProperty(), 20);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);
        final KeyValue kv1 = new KeyValue(widget_right.opacityProperty(), 1);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(widget_top_left.opacityProperty(), 1);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        final KeyValue kv3 = new KeyValue(widget_bottom_left.opacityProperty(), 1);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(500), kv3);
        final KeyValue kv4 = new KeyValue(widget_right.translateYProperty(), -625);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(350), kv4);
        final KeyValue kv5 = new KeyValue(widget_bottom_left.translateXProperty(), 888);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(350), kv5);
        final KeyValue kv6 = new KeyValue(widget_top_left.translateXProperty(), -937);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(350), kv6);
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4, kf5, kf6);
        load_scene.play();
    }

    public void animateOut()
    {
        final Timeline load_scene = new Timeline();
        load_scene.setCycleCount(1);
        load_scene.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(title.layoutYProperty(), -100);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(200), kv0);
        final KeyValue kv1 = new KeyValue(widget_right.opacityProperty(), 0.3);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);
        final KeyValue kv2 = new KeyValue(widget_top_left.opacityProperty(), 0.3);
        final KeyFrame kf2 = new KeyFrame(Duration.millis(500), kv2);
        final KeyValue kv3 = new KeyValue(widget_bottom_left.opacityProperty(), 0.3);
        final KeyFrame kf3 = new KeyFrame(Duration.millis(500), kv3);
        final KeyValue kv4 = new KeyValue(widget_right.translateYProperty(), 0);
        final KeyFrame kf4 = new KeyFrame(Duration.millis(350), kv4);
        final KeyValue kv5 = new KeyValue(widget_bottom_left.translateXProperty(), 0);
        final KeyFrame kf5 = new KeyFrame(Duration.millis(350), kv5);
        final KeyValue kv6 = new KeyValue(widget_top_left.translateXProperty(), 0);
        final KeyFrame kf6 = new KeyFrame(Duration.millis(350), kv6);
        load_scene.getKeyFrames().addAll(kf0, kf1, kf2, kf3, kf4, kf5, kf6);
        load_scene.play();
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
                // Stop the refresh thread
                pageActive = false;

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
        nav_icon4.getStyleClass().remove("active");
        nav_bg4.getStyleClass().remove("active");
        accent4.getStyleClass().remove("show");
    }

    /**
     * Reset the navigation styles to make this current window the active one, if we don't call this method
     * then the next time we load this window form the HashMap, the wrong active state shall be applied
     */
    private void resetStyles()
    {
        // Active state for this window
        nav_icon4.getStyleClass().add("active");
        nav_bg4.getStyleClass().add("active");
        accent4.getStyleClass().addAll("active", "show");

        // Default styles for every other nav element
        nav_icon1.getStyleClass().remove("active");
        accent1.getStyleClass().removeAll("active", "show");
        nav_bg1.getStyleClass().remove("active");
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

    // Set the parent of the new screen
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    // Navigation Control
    @FXML
    private void goToDashboard(ActionEvent event){
        // If the user panel is open then hide it
        hideUsers();
        UserInfoController u;
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
        nextForm(ScreensFramework.screen2ID);
    }
}



