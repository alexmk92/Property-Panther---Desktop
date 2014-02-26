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
import prcse.pp.model.Tenant;
import prcse.pp.model.UserList;

/**
 *
 * @author Angie
 */
public class ScreensFramework extends Application  {

    public static String screen1ID = "Dashboard";
    public static String screen1File = "../view/Dashboard.fxml";
    public static String screen2ID = "Tenant";
    public static String screen2File = "../view/AllUsers.fxml";
    public static String screen3ID = "Properties";
    public static String screen3File = "../view/Properties.fxml";
    public static String screen4ID = "Payments";
    public static String screen4File = "../view/Payments.fxml";
    public static String screen5ID = "Messages";
    public static String screen5File = "../view/Messages.fxml";
    public static String screen6ID = "Settings";
    public static String screen6File = "../view/Settings.fxml";
    public static String screen7ID = "Add Tenant";
    public static String screen7File = "../view/AddUser.fxml";
    public static String screen8ID = "View Tenant";
    public static String screen8File = "../view/UserDetails.fxml";
    public static Boolean connected = false;
    public static Database db;
    public static UserList users = new UserList();
    public static Searcher searchObj = new Searcher();


    @Override
    public void start(Stage primaryStage) {


        // Make a connection to the database (SINGLETON)
        //connectToDb();
        buildUsers();

        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        mainContainer.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
        mainContainer.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
        mainContainer.loadScreen(ScreensFramework.screen7ID, ScreensFramework.screen7File);
        mainContainer.loadScreen(ScreensFramework.screen8ID, ScreensFramework.screen8File);

        mainContainer.setScreen(ScreensFramework.screen1ID);

        //remove window decoration
        primaryStage.initStyle(StageStyle.UNDECORATED);

        //instanciate window
        Group root = new Group();
        root.getStylesheets().add(this.getClass().getResource("../view/MainView.css").toExternalForm());
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 1200,720);
        primaryStage.setScene(scene);
        primaryStage.show();

       //System.out.println("UI Loaded");
        //System.out.println(db.getDb_host());

        //db.connectionDemo();
        //db.buildUsers();

    }

    public void buildUsers()
    {
        Tenant a = new Tenant();
        Tenant b = new Tenant();
        Tenant c = new Tenant();
        Tenant d = new Tenant();
        Tenant e = new Tenant();
        Tenant f = new Tenant();

        a.setForename("Jamie");
        a.setSurname("Shepherd");
        a.setAddr_line_1("6 Morland Drive");

        b.setForename("Alex");
        b.setSurname("Sims");
        b.setAddr_line_1("Flat 5,");
        b.setAddr_line_2("8 Laira Place");

        c.setForename("Thomas");
        c.setSurname("Knowles");
        c.setAddr_line_1("26 Newland Area");

        d.setForename("Jason");
        d.setSurname("Dee");
        d.setAddr_line_1("32 Apricot Road");

        e.setForename("Adam");
        e.setSurname("Stevenson");
        e.setAddr_line_1("12 Peters Way");

        f.setForename("Alex");
        f.setSurname("Peters");
        f.setAddr_line_1("16 Tomato Street");

        users.addUser(a);
        users.addUser(b);
        users.addUser(c);
        users.addUser(d);
        users.addUser(e);
        users.addUser(f);
    }

    /**
     * Connect to the database and set the global variable "connected" to false
     * to prevent further instantiations.
     */
    public void connectToDb()
    {
        if(this.connected == false) {
            try {
                ScreensFramework.db = new Database(null, null, null);
                System.out.println("Connected to database");
                this.connected = true;
            } catch (Exception e) {
                // If there was an error set the connection to false.
                this.connected = false;
            }
        } else {
            System.out.println("Connection already open.");
        }
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
