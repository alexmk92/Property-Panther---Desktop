package prcse.pp.controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import prcse.pp.logs.Reader;
import prcse.pp.logs.Writer;
import prcse.pp.model.Admin;
import prcse.pp.model.Tenant;
import prcse.pp.model.UserList;
import prcse.pp.model.UserPermission;

/**
 * FXML Controller class
 */
public class LoginController implements Initializable, ControlledScreen {

    /******************************************************
     *       FXML VARIABLES - RELATIVE TO LOGIN.FXML
     ******************************************************/
    @FXML // fx:id="draggable"
    private BorderPane draggable;
    @FXML // fx:id="closeBtn"
    private Button closeBtn;
    @FXML // fx:id="body"
    private AnchorPane body;
    @FXML // fx:id="loginBtn"
    private Button loginBtn;
    @FXML // fx:id="txtUsername"
    private TextField txtUsername;
    @FXML // fx:id="txtPassword"
    private PasswordField txtPassword;
    @FXML // fx:id="spinner"
    private ImageView spinner;
    @FXML // fx:id="spinnerWrap"
    private Pane spinnerWrap;
    @FXML // fx:id="overlay"
    private Pane overlay;
    @FXML // fx:id="errorWrap"
    private Pane errorWrap;
    @FXML // fx:id="lblStatus"
    private Label lblStatus;
    @FXML // fx:id="closeError"
    private Button closeError;
    @FXML // fx:id="mainPanel"
    private Pane mainPanel;
    @FXML // fx:id="mainWrap"
    private Pane mainWrap;
    @FXML // fx:id="remember"
    private CheckBox rememberMe;


    // Set variables to allow for draggable window.
    private double  xOffset   = 0;
    private double  yOffset   = 0;
    private Boolean validated = false;
    private String  error     = "";
    private Boolean tickEnabled;

    // Set up to read and write to/from the config file
    public Writer   logConfig    = new Writer("config", false);
    private Reader  configReader = new Reader("logs/config.txt");

    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {

        // If config file has a value then populate it
        populateUsername();

        // Hide the progress spinner
        hideSpinner();
        hideErrors();

        // Hide the overlay
        hideOverlay();

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
         *                  UTILITY CONTROLS
         ******************************************************/
        closeBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                updateConfig();
                Platform.exit();
            }
        });
        closeError.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                // slide the error panel back in
                hideErrors();

                // Get the correct focus - if both are populated, default
                // to focusing the password field
                if(txtUsername.getText().length() == 0){
                    txtUsername.requestFocus();
                } else if(txtPassword.getText().length() == 0){
                    txtPassword.requestFocus();
                } else {
                    txtPassword.requestFocus();
                }
            }
        });

        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hideErrors();
                handleLogin();
            }
        });

        txtUsername.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    hideErrors();
                    handleLogin();
                }
            }
        });
        txtPassword.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    hideErrors();
                    handleLogin();
                }
            }
        });

        rememberMe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(tickEnabled == true) {
                    tickEnabled = false;
                } else {
                    tickEnabled = true;
                }
            }
        });



    }

    /******************************************************
     *                       METHODS
     ******************************************************/
    protected Boolean verifyUser(String username, String password)
    {
        Boolean verified = false;

        // Check the fields have been set
        if(username.length() > 0 && password.length() > 0) {
            if(ScreensFramework.db.connectToDb()){

                // We have opened a database connection - this enables singleton
                ScreensFramework.connected = true;

                // Get an admin object back the call to the database
                // we use the db reference started in our ScreenFramework as
                // this allows persistence through all controllers
                Admin a = ScreensFramework.db.logIn(username, password);

                // Check if the instantiated admin object is valid
                if(a != null) {

                    // Extra security step (check permission)
                    if(a.getPermission() == UserPermission.ADMIN)
                    {
                        // Set the session
                        ScreensFramework.searchObj.setSession(a);

                        // Update logging window
                        Platform.runLater(() -> showErrors());
                        Platform.runLater(()->error = "Success: Verification successful, setting session.");
                        Platform.runLater(()->updateLabel(error));

                        // The user is valid, grant them permission
                        verified = true;
                    }
                } else {
                    // Update logging window
                    Platform.runLater(()->showErrors());
                    Platform.runLater(()->error = "Invalid user, you are not an admin.");
                    Platform.runLater(()->updateLabel(error));
                }
            }
        }

        // The user is an admin, return true
        return verified;
    }

    private void handleLogin()
    {
        // Show the overlay and spinner objects
        showSpinner();
        showOverlay();

        // Try to verify the user with the provided credentials
        // run this on a new thread as it takes 5-10 seconds to compute
        new Thread(new Runnable() {
            @Override
            public void run() {

                // Set the credentials to pass
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                // friendly message
                showErrors();
                error = "Authenticating: Connecting to the server with your credentials...";
                Platform.runLater(()->updateLabel(error));

                // Is this user an admin
                if(verifyUser(username, password)){

                    // Set the loggedIn variable to true
                    ScreensFramework.connected = true;

                    // friendly message
                    showErrors();
                    error = "Success: Preparing your dashboard!";
                    Platform.runLater(()->updateLabel(error));

                    // Check we build the system successfully (returns boolean)
                    if(ScreensFramework.buildDataModel()){
                        // friendly message
                        error = "Dashboard prepared, logging in...";
                        Platform.runLater(()->updateLabel(error));
                        showErrors();

                        // reset the spinner and forward to dashboard
                        Platform.runLater(()->spinnerWrap.setVisible(false));
                        animateToDashboard();
                    }
                } else {
                    showErrors();
                    error = "Incorrect username or password";
                    Platform.runLater(()->updateLabel(error));
                    Platform.runLater(()->spinnerWrap.setVisible(false));
                    hideOverlay();
                }
            }
        }).start();
    }

    /**
     * Hides the progress spinner and label
     */
    private void hideSpinner(){
        spinnerWrap.setVisible(false);
    }

    /**
     * Shows the progress spinner and label
     */
    private void showSpinner(){
        spinnerWrap.setVisible(true);
    }

    /**
     * Updates the status label
     * @param status the message we wish to set
     */
    private void updateLabel(String status) {
        lblStatus.setText(status);
    }

    /**
     * Populates the username textbox if a value is set in our
     * config file
     */
    private void populateUsername() {

        // Remember me string initialised
        String remember = "";

        // Check that our config file has some contents
        String checkConfig = configReader.readFile();

        // Check we have a string of legnth 4+, then set
        // the remember string
        if(checkConfig.length() > 4){
            rememberMe.setSelected(true);
            tickEnabled = true;
            remember = checkConfig;
            txtUsername.setText(remember);

        } else {
            tickEnabled = false;
        }
    }

    /**
     * updates the config file if the checkbox is ticked
     */
    private void updateConfig() {
        // Check that the checkbox is ticked
        if(tickEnabled == true) {
            logConfig.writeToFile(txtUsername.getText());
        } else {
            logConfig.writeToFile("");
        }
    }

    /******************************************************
     *                ANIMATION CONTROLS
    ******************************************************/
    private void showOverlay()
    {
        final Timeline setOverlay = new Timeline();
        setOverlay.setCycleCount(1);
        setOverlay.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(overlay.opacityProperty(), 1);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(350), kv0);

        // Build the animation
        setOverlay.getKeyFrames().add(kf0);
        setOverlay.play();
    }

    /**
     * hides the overlay
     */
    private void hideOverlay()
    {
        final Timeline removeOverlay = new Timeline();
        removeOverlay.setCycleCount(1);
        removeOverlay.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(overlay.opacityProperty(), 0);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(350), kv0);

        // Build the animation
        removeOverlay.getKeyFrames().add(kf0);
        removeOverlay.play();
    }

    /**
     * shows the error windw
     */
    private void showErrors()
    {
        final Timeline removeOverlay = new Timeline();
        removeOverlay.setCycleCount(1);
        removeOverlay.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(errorWrap.opacityProperty(), 1);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);

        // Build the animation
        removeOverlay.getKeyFrames().add(kf0);
        removeOverlay.play();
    }

    /**
     * hides the error windw
     */
    private void hideErrors()
    {
        final Timeline removeOverlay = new Timeline();
        removeOverlay.setCycleCount(1);
        removeOverlay.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(errorWrap.opacityProperty(), 0);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(250), kv0);

        // Build the animation
        removeOverlay.getKeyFrames().add(kf0);
        removeOverlay.play();
    }

    /**
     * Hides all UI elements accept for the background
     */
    private void hideElements() {
        // Hide
        final Timeline hideScreen = new Timeline();
        hideScreen.setCycleCount(1);
        hideScreen.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(mainWrap.opacityProperty(), 0);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(500), kv0);
        final KeyValue kv1 = new KeyValue(mainPanel.opacityProperty(), 0);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(500), kv1);

        hideScreen.getKeyFrames().addAll(kf0, kf1);
        hideScreen.play();
    }


    /**
     * Transition to the dashboard view
     */
    private void animateToDashboard() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(150);
                    hideErrors();
                    hideElements();
                    Thread.sleep(550);
                } catch(Exception e )
                {
                    ScreensFramework.logError.writeToFile("There was an error handling the animation...");
                }

                // Refresh the config
                updateConfig();

                // Go to our view.
                myController.setScreen(ScreensFramework.screen1ID);
            }
        }).start();

    }

    /******************************************************
     *              LOAD NEW SCREEN METHODS
     ******************************************************/
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToDashboard(ActionEvent event){
        myController.setScreen(ScreensFramework.screen1ID);
    }
}
