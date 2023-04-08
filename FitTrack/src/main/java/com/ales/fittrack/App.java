package com.ales.fittrack;

import com.ales.fittrack.Database.AccesDB;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class App extends Application {

    private boolean authenticated = false;

    @Override
    public void start(Stage stage) throws IOException {

        if (!authenticated) {

            WindowsManager.init(stage);
            AccesDB.init();

            stage.setOnCloseRequest(closeEventHandler());

            WindowsManager.changeWindow("start-screen.fxml", "FitTrack App");

//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("start-screen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), WindowsManager.WINDOW_WIDTH, WindowsManager.WINDOW_HEIGHT);
//        stage.setTitle("FitTrack App");
//        stage.setScene(scene);
//        stage.show();


        }

    }

    private EventHandler<WindowEvent> closeEventHandler() {
        return event -> {
            WindowsManager.closeWindow();
            if (AccesDB.isOpen()) {
                AccesDB.close();
            }
            System.exit(0);
        };

        /*return new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                AccesDB.close();
            }
        };*/
    }

    public static void main(String[] args) {
        launch();
    }
}