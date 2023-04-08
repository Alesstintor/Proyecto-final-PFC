package com.ales.fittrack.controllers;

import com.ales.fittrack.WindowsManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StartScreenController {

    @FXML
    protected void onLoginButton() {
        WindowsManager.changeWindow("trainer-view.fxml", "");
    }
}