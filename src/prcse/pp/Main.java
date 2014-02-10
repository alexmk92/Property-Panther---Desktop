package prcse.pp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import prcse.pp.controller.MainController;
import javafx.scene.text.*;


public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainMenu.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/MainMenu.fxml"));
        loader.setController(new MainController());

        //remove window decoration
        primaryStage.initStyle(StageStyle.UNDECORATED);

        // Add stylesheet to the form
        root.getStylesheets().add(this.getClass().getResource("view/MainView.css").toExternalForm());
        Font.loadFont(this.getClass().getResource("view/fonts/OpenSans-Regular.ttf").toExternalForm(), 12);

        // Set the view
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
