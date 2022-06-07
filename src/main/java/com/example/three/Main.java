package com.example.three;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class is designed to start the user interface.
 *
 * @author Asif Haque, Margaret Knoebel, Lorenzo Ladao
 */
public class Main extends Application {

    /**
     * Readies the system to begin running the JavaFX applications.
     *
     * @param stage creates the stage where everything will be displayed
     * @throws IOException throws if fails to read or write files
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 440);
        stage.setTitle("Tuition Manager!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application
     *
     * @param args arguments from user
     */
    public static void main(String[] args) {
        launch();
    }
}