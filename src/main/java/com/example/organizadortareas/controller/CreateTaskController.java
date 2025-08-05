package com.example.organizadortareas.controller;

import com.example.organizadortareas.view.TaskStage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateTaskController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private Button taskButton;

    @FXML
    public void initialize() {
        titleField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 80) {
                titleField.setText(oldValue);
            }
        });

        descriptionArea.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > 300) {
                descriptionArea.setText(oldValue);
            }
        });
    }

    @FXML
    public void handleCreateTask() {
        try {
            TaskStage.getInstance().show();

            Stage actual = (Stage) taskButton.getScene().getWindow();
            actual.hide();

        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
