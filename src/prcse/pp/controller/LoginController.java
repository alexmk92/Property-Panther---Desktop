package prcse.pp.controller;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


    // Set variables to allow for draggable window.
    private double  xOffset   = 0;
    private double  yOffset   = 0;
    private Boolean validated = false;
    private String  error     = "";

    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
        // Hide the progress spinner
        hideSpinner();


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
                Platform.exit();
            }
        });
        closeError.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                // slide the error panel back in
                slideBackError();

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
                handleLogin();
            }
        });

        txtUsername.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    slideBackError();
                    handleLogin();
                }
            }
        });
        txtPassword.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER))
                {
                    slideBackError();
                    handleLogin();
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
                        System.out.println("session set");

                        // The user is valid, grant them permission
                        verified = true;
                    }
                } else {
                    System.out.println("Invalid user, you are not an admin.");
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
                error = "Authenticating: Connecting to the server with your credentials...";
                Platform.runLater(()->updateLabel(error));
                slideUpError();

                // Is this user an admin
                if(verifyUser(username, password)){

                    // Set the loggedIn variable to true
                    ScreensFramework.connected = true;

                    // friendly message
                    error = "Success: Preparing your dashboard!";
                    Platform.runLater(()->updateLabel(error));
                    slideUpError();

                    // Check we build the system successfully (returns boolean)
                    if(ScreensFramework.buildDataModel()){
                        // friendly message
                        error = "Dashboard prepared, logging in...";
                        Platform.runLater(()->updateLabel(error));
                        slideUpError();

                        // reset the spinner and forward to dashboard
                        Platform.runLater(()->spinnerWrap.setVisible(false));
                        hideOverlay();
                        animateToDashboard();
                    }
                } else {
                    error = "Incorrect username or password";
                    Platform.runLater(()->updateLabel(error));
                    slideUpError();
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

    // Shows the error window
    private void slideUpError() {

        final Timeline showError = new Timeline();
        showError.setCycleCount(1);
        showError.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(errorWrap.opacityProperty(), 1);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(0), kv0);
        final KeyValue kv1 = new KeyValue(errorWrap.translateYProperty(), -46);
        final KeyFrame kf1 = new KeyFrame(Duration.millis(200), kv1);

        // Build the animation
        showError.getKeyFrames().addAll(kf0, kf1);
        showError.play();
    }

    // Hides the error window
    private void slideBackError() {

        final Timeline showError = new Timeline();
        showError.setCycleCount(1);
        showError.setAutoReverse(false);
        final KeyValue kv0 = new KeyValue(errorWrap.translateYProperty(), 0);
        final KeyFrame kf0 = new KeyFrame(Duration.millis(200), kv0);

        // Build the animation
        showError.getKeyFrames().add(kf0);
        showError.play();
    }

    /**
     * Transition to the dashboard view
     */
    private void animateToDashboard() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(750);
                    slideBackError();
                    Thread.sleep(150);
                } catch(Exception e )
                {
                    System.out.println("There was an error handling the animation...");
                }
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
