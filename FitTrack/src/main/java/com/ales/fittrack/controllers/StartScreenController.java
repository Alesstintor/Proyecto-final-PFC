package com.ales.fittrack.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartScreenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}