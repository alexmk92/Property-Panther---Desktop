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
import prcse.pp.model.Tenant;
import prcse.pp.model.UserList;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class DashboardController implements Initializable, ControlledScreen {

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


    // Set variables to allow for draggable window.
    private double xOffset = 0;
    private double yOffset = 0;
    private UserList users = ScreensFramework.users;
    ScreensController myController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resources)
    {
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

        txtUsers_Username.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER))
                    {
                        goToUsers(new ActionEvent());
                    }
            }
        });

        btnUserSearch.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnUserSearch.getStyleClass().add("searching");
                spinner_green.setVisible(true);
            }
        });
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

    public void slideTitleIn()
    {
            final Timeline slideDown = new Timeline();
            slideDown.setCycleCount(1);
            slideDown.setAutoReverse(false);
            final KeyValue kv1 = new KeyValue(title.translateYProperty(), 120);
            final KeyFrame kf1 = new KeyFrame(Duration.millis(800), kv1);
            slideDown.getKeyFrames().add(kf1);
            slideDown.play();
    }





    /******************************************************
     *              LOAD NEW SCREEN METHODS
     ******************************************************/
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToDashboard(ActionEvent event){
        hideUsers();
        myController.setScreen(ScreensFramework.screen1ID);
}
    @FXML
    private void goToUsers(ActionEvent event)
    {
        if(txtUsers_Username.getText().length() > 0 && txtUsers_Username.getText() != null)
        {
            String[] name = txtUsers_Username.getText().split(" ");
            String forename = null;
            String surname  = null;

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

            ArrayList<Tenant> results = users.getTenant(forename, surname);

            if(results.size() > 1)
            {
                hideUsers();
                myController.setScreen(ScreensFramework.screen2ID);
            } else if(results.size() == 1) {
                Searcher s = ScreensFramework.searchObj;
                Tenant t = results.get(0);

                s.setTenant(t);

                hideUsers();
                myController.setScreen(ScreensFramework.screen8ID);
            } else {
                hideUsers();
                myController.setScreen(ScreensFramework.screen2ID);
            }
        }

    }
    @FXML
    private void goToProperties(ActionEvent event){
        hideUsers();
        myController.setScreen(ScreensFramework.screen3ID);
    }
    @FXML
    private void goToPayments(ActionEvent event){
        hideUsers();
        myController.setScreen(ScreensFramework.screen4ID);
    }
    @FXML
    private void goToMessages(ActionEvent event){
        hideUsers();
        myController.setScreen(ScreensFramework.screen5ID);
    }
    @FXML
    private void goToSettings(ActionEvent event){
        hideUsers();
        myController.setScreen(ScreensFramework.screen6ID);
    }
    @FXML
    private void goToAddUser(ActionEvent event){
        hideUsers();
        myController.setScreen(ScreensFramework.screen7ID);
    }
    @FXML
    private void goToAllUsers(ActionEvent event){
        Searcher s = ScreensFramework.searchObj;
        Tenant t = new Tenant();

        t.setForename("Alexander John");
        t.setSurname("Sims");
        s.setTenant(t);

        hideUsers();
        myController.setScreen(ScreensFramework.screen8ID);
    }
}
