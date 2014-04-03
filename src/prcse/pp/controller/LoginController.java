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


    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;
    private int tryConnection = 0;

    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {

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

        loginBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Set the credentials to pass
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                // Try to verify the user with the provided credentials
                if(verifyUser(username, password)){
                    // Set the loggedIn variable to true
                    ScreensFramework.loggedIn = true;
                    ScreensFramework.authenticated();
                }
            }
        });



    }

    /******************************************************
     *                       METHODS
     ******************************************************/
    public Boolean verifyUser(String username, String password)
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

    /******************************************************
     *                ANIMATION CONTROLS
     ******************************************************/


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
