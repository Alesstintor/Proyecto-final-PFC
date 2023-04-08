package com.ales.fittrack;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowsManager {

    public static final int WINDOW_WIDTH = 600;

    public static final int WINDOW_HEIGHT = 800;

    private static Stage stage;

    private static String dir = "views/";

    public static void init(Stage newStage) {
        stage = newStage;
    }


    public static void changeWindow(String fxml, String title) {
        try {

        FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.class.getResource(dir + fxml));
        Scene scene = new Scene(fxmlLoader.load(), WindowsManager.WINDOW_HEIGHT, WindowsManager.WINDOW_WIDTH);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeWindow(){
        stage.close();
    }
}
