package com.ales.fittrack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private boolean authenticated = false;

    @Override
    public void start(Stage stage) throws IOException {

        if (!authenticated) {

            WindowsManager.init(stage);

            WindowsManager.changeWindow("start-screen.fxml", "FitTrack App");

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("start-screen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), WindowsManager.WINDOW_WIDTH, WindowsManager.WINDOW_HEIGHT);
//        stage.setTitle("FitTrack App");
//        stage.setScene(scene);
//        stage.show();


        }

    }

    public static void main(String[] args) {
        launch();
    }
}