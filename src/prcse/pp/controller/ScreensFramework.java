/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package prcse.pp.controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import prcse.pp.db.Database;
import prcse.pp.misc.UploadImage;
import prcse.pp.misc.Validate;
import prcse.pp.misc.Writer;
import prcse.pp.model.*;

import java.util.ArrayList;

/**
* Framework to encapsulate all of our screens
 */
public class ScreensFramework extends Application  {

    // Variables describing views
    public static String screen0ID   = "Login";
    public static String screen0File = "../view/Login.fxml";
    public static String screen1ID   = "Dashboard";
    public static String screen1File = "../view/Dashboard.fxml";
    public static String screen2ID   = "Tenants";
    public static String screen2File = "../view/AllUsers.fxml";
    public static String screen3ID   = "Properties";
    public static String screen3File = "../view/Properties.fxml";
    public static String screen4ID   = "Payments";
    public static String screen4File = "../view/Payments.fxml";
    public static String screen5ID   = "Messages";
    public static String screen5File = "../view/Messages.fxml";
    public static String screen6ID   = "Settings";
    public static String screen6File = "../view/Settings.fxml";
    public static String screen7ID   = "Add Tenant";
    public static String screen7File = "../view/AddUser.fxml";
    public static String screen8ID   = "View Tenant";
    public static String screen8File = "../view/UserDetails.fxml";
    public static Boolean connected  = false;
    public static Database db;

    // Global model list - reference these for persistence,
    // IN FUTURE: once loaded, filter these objects into their dependent objects, i.e.
    //            set all OCCUPIED property objects to their dependent tenant
    public static UserList           tenants     = new UserList();
    public static PropertyList       properties  = new PropertyList();
    public static ArrayList<Admin>   adminList   = new ArrayList<>();
    public static ArrayList<Payment> allPayments = new ArrayList<>();
    public static Admin              loggedIn    = new Admin();

    // Global search object to pass data between forms
    public static Searcher searchObj = new Searcher();

    // Global write objects
    public static Writer logError   = new Writer("error", true);
    public static Writer logGeneral = new Writer("general", true);

    // Global Image Upload object
    public static UploadImage uploader = new UploadImage();

    // Global validation object
    public static Validate validateThis = new Validate();

    @Override
    public void start(Stage primaryStage) {

        // Set up a new control object to hold all of our screens
        ScreensController mainContainer = new ScreensController();

        // TEST CASES
        //uploader.upload("C:\\Users\\Alex\\Desktop\\desktop-application\\src\\prcse\\pp\\controller\\test.txt", ".txt", "properties/");
        //uploader.download("test", ".txt", "properties");

        // Load each screen into the hash map
        mainContainer.loadScreen(ScreensFramework.screen0ID, ScreensFramework.screen0File);
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        mainContainer.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
        mainContainer.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
        mainContainer.loadScreen(ScreensFramework.screen7ID, ScreensFramework.screen7File);
        mainContainer.loadScreen(ScreensFramework.screen8ID, ScreensFramework.screen8File);

        // Sets the first screen to be shown
        mainContainer.setScreen(ScreensFramework.screen0ID);

        // Removes the border and any other screen decorations
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        // Instanciate window
        Group root = new Group();
        root.getStylesheets().add(this.getClass().getResource("../view/MainView.css").toExternalForm());
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 1200, 720);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * If the user was authenticated, build all the objects and load the system
     * before redirecting them to their dashboard.
     * @return the string of either a success message or  error
     */
    public static Boolean buildDataModel() {

        Boolean systemResponse = false;

        // Check if an admin has logged in
        // the action for this is trigger in LoginController.java - it will set the loggedIn var
        // to true
        if(connected == true) {
            // Build the objects for the datamodel on a new thread
            if(db.buildObjects()) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Build session holders inbox and sent box
                        loggedIn = searchObj.getSessionUser();
                        loggedIn.buildInbox(25, "ALL");
                        loggedIn.buildSent(25);
                    }
                }).start();

                // Validate and allow the user to see the dashboard
                systemResponse = true;
            } else {
                systemResponse = false;
            }

        }

        return systemResponse;
    }

    /**
     * The main() method is ignored in a correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
